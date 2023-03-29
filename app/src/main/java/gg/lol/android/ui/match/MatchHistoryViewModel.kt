package gg.lol.android.ui.match

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.op.lol.domain.interactor.GetLatestVersionUseCase
import gg.op.lol.domain.interactor.GetLocalChampionsUseCase
import gg.op.lol.domain.interactor.GetLocalItemUseCase
import gg.op.lol.domain.interactor.GetLocalRunesUseCase
import gg.op.lol.domain.interactor.GetLocalSpellsUseCase
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import gg.op.lol.domain.interactor.GetSummonerMatchHistoryUseCase
import gg.op.lol.domain.interactor.InsertSearchHistoryUseCase
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MatchHistoryViewModel @Inject internal constructor(
    private val summonerInfoUseCase: GetSummonerInfoUseCase,
    private val summonerMatchHistoryUseCase: GetSummonerMatchHistoryUseCase,
    private val localChampionsUseCase: GetLocalChampionsUseCase,
    private val spellUseCase: GetLocalSpellsUseCase,
    private val runeUseCase: GetLocalRunesUseCase,
    private val itemUseCase: GetLocalItemUseCase,
    private val latestVersionUseCase: GetLatestVersionUseCase,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase
) : BaseViewModel() {

    private val _summonerName = MutableStateFlow("")
    val summonerName: StateFlow<String> get() = _summonerName

    private val _appbarBackground = MutableStateFlow(Color.White)
    val appbarBackground: StateFlow<Color> get() = _appbarBackground

    private val _uiState = MutableStateFlow<UiState<Summoner>>(UiState.Loading)
    val uiState: StateFlow<UiState<Summoner>> get() = _uiState

    private val _matchHistories = MutableStateFlow<PagingData<MatchHistory>>(PagingData.empty())
    val matchHistories: StateFlow<PagingData<MatchHistory>> = _matchHistories

    private val _latestVersion = MutableStateFlow("")
    val latestVersion: StateFlow<String> = _latestVersion

    private val _champions = arrayListOf<Champion>()
    val champions: List<Champion> = _champions

    private val _spells = arrayListOf<Spell>()
    val spells: List<Spell> = _spells

    private val _runes = arrayListOf<Rune>()
    val runes: List<Rune> = _runes

    private val _items = arrayListOf<Item>()
    val items: List<Item> = _items

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(exception)
    }

    init {
        launchCoroutineIO {
            getLatestVersion()
            getRemoteSummoner()
            loadChampions()
            loadSpell()
            loadRune()
            loadItem()
        }
    }

    fun insertSearchHistory(searchHistory: SearchHistory) {
        launchCoroutineIO {
            insertSearchHistoryUseCase.invoke(searchHistory)
        }
    }

    fun getMatchHistories(puuid: String) {
        _matchHistories.value = PagingData.empty()
        launchCoroutineIO {
            summonerMatchHistoryUseCase.invoke(puuid).cachedIn(viewModelScope).collect {
                _matchHistories.value = it
            }
        }
    }

    private suspend fun getLatestVersion() {
        latestVersionUseCase.invoke(Unit).collect {
            _latestVersion.value = it
        }
    }

    private suspend fun getRemoteSummoner() {
        summonerInfoUseCase.invoke(summonerName.value).collect {
            _uiState.value = UiState.Success(it)
            getMatchHistories(it.puuid)
            insertSearchHistory(SearchHistory(it.summonerName, it.profileIconId))
        }
    }

    private suspend fun loadChampions() {
        localChampionsUseCase.invoke(Unit).collect {
            _champions.addAll(it)
        }
    }

    private suspend fun loadSpell() {
        spellUseCase.invoke(Unit).collect {
            _spells.addAll(it)
        }
    }

    private suspend fun loadRune() {
        runeUseCase.invoke(Unit).collect {
            _runes.addAll(it)
        }
    }

    private suspend fun loadItem() {
        itemUseCase.invoke(Unit).collect {
            _items.addAll(it)
        }
    }

    fun setAppBarBackground(value: Color) {
        _appbarBackground.value = value
    }

    private val _screenCloseCheck = mutableStateOf(false)
    val screenCloseCheck get() = _screenCloseCheck.value

//    private val _summonerResponse = MutableLiveData<SummonerResponse>()
//    val summonerResponse: LiveData<SummonerResponse> get() = _summonerResponse

    fun setScreenCloseCheck(value: Boolean) {
        this._screenCloseCheck.value = value
    }

    fun setNickName(value: String) {
        _summonerName.value = value
    }

    fun getSummoner(name: String) {
        viewModelScope.launch {
            // TODO
        }
    }
}

package gg.lol.android.ui.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.lol.android.ui.navigation.LOLMatchHistoryRoute
import gg.lol.android.util.Functions
import gg.lol.android.util.PreferencesHelper
import gg.op.lol.domain.interactor.GetFavoriteSummonerUseCase
import gg.op.lol.domain.interactor.GetLocalChampionsUseCase
import gg.op.lol.domain.interactor.GetLocalItemUseCase
import gg.op.lol.domain.interactor.GetLocalRunesUseCase
import gg.op.lol.domain.interactor.GetLocalSpellsUseCase
import gg.op.lol.domain.interactor.GetPagingMatchHistoriesUseCase
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import gg.op.lol.domain.interactor.InsertSearchHistoryUseCase
import gg.op.lol.domain.interactor.UpdateFavoriteSummonerUseCase
import gg.op.lol.domain.models.Champion
import gg.op.lol.domain.models.Item
import gg.op.lol.domain.models.MatchHistory
import gg.op.lol.domain.models.Rune
import gg.op.lol.domain.models.SearchHistory
import gg.op.lol.domain.models.Spell
import gg.op.lol.domain.models.Summoner
import gg.op.lol.domain.models.Tier
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MatchHistoryViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val summonerInfoUseCase: GetSummonerInfoUseCase,
    private val summonerMatchHistoryUseCase: GetPagingMatchHistoriesUseCase,
    private val localChampionsUseCase: GetLocalChampionsUseCase,
    private val spellUseCase: GetLocalSpellsUseCase,
    private val runeUseCase: GetLocalRunesUseCase,
    private val itemUseCase: GetLocalItemUseCase,
    private val updateFavoriteSummonerUseCase: UpdateFavoriteSummonerUseCase,
    private val getFavoriteSummonerUseCase: GetFavoriteSummonerUseCase,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    private val preferencesHelper: PreferencesHelper
) : BaseViewModel() {

    private val summonerName = Functions.getArg<String>(
        savedStateHandle,
        LOLMatchHistoryRoute.ARG_SUMMONER_NAME
    ) ?: ""

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
        exception.printStackTrace()
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

    fun updateFavoriteSummoner(summoner: Summoner) {
        launchCoroutineIO {
            updateFavoriteSummonerUseCase.invoke(summoner)
            getFavoriteSummoner(summoner.summonerName)
        }
    }

    fun getFavoriteSummoner(summonerName: String) {
        launchCoroutineIO {
            val result = getFavoriteSummonerUseCase.invoke(summonerName)
            result ?: return@launchCoroutineIO
            val state = uiState.value
            if (state is UiState.Success<Summoner>) {
                _uiState.value = state.copy(
                    data = state.data.copy(isFavorite = result.isFavorite)
                )
            }
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

    private fun getLatestVersion() {
        _latestVersion.value = preferencesHelper.currentVersion
    }

    private suspend fun getRemoteSummoner() {
        summonerInfoUseCase.invoke(summonerName).collect {
            _uiState.value = UiState.Success(it)
            getMatchHistories(it.puuid)
            insertSearchHistory(
                SearchHistory(
                    summonerName = it.summonerName,
                    summonerLevel = it.summonerLevel,
                    profileIconId = it.profileIconId,
                    tier = Tier.valueOf(it.histories.first().tier, it.histories.first().rank),
                    lastSearchedAt = System.currentTimeMillis()
                )
            )
            getFavoriteSummoner(it.summonerName)
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

    fun getSummoner(name: String) {
        viewModelScope.launch {
            // TODO
        }
    }
}

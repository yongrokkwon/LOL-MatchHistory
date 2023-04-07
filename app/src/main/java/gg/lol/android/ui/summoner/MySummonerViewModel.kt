package gg.lol.android.ui.summoner

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.op.lol.domain.interactor.GetSummonerInfoUseCase
import gg.op.lol.domain.interactor.UpdateFavoriteSummonerUseCase
import gg.op.lol.domain.models.Summoner
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MySummonerViewModel @Inject internal constructor(
    private val summonerInfoUseCase: GetSummonerInfoUseCase,
    private val updateFavoriteSummonerUseCase: UpdateFavoriteSummonerUseCase
) : BaseViewModel() {

    private val _summonerName = mutableStateOf("")
    val summonerName: State<String> get() = _summonerName

    private val _uiState = MutableStateFlow<UiState<Summoner?>>(UiState.Success(null))
    val uiState: StateFlow<UiState<Summoner?>> get() = _uiState

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _uiState.value = UiState.Error(exception)
    }

    fun setSummonerName(summonerName: String) {
        _summonerName.value = summonerName
    }

    fun getRemoteSummoner() {
        _uiState.value = UiState.Loading
        launchCoroutineIO {
            summonerInfoUseCase.invoke(summonerName.value).collect {
                _uiState.value = UiState.Success(it)
                updateFavoriteSummonerUseCase.invoke(it)
            }
        }
    }
}

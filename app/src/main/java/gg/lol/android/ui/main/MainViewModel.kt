package gg.lol.android.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import gg.lol.android.ui.BaseViewModel
import gg.lol.android.ui.UiState
import gg.op.lol.domain.interactor.GetChampionsUseCase
import gg.op.lol.domain.interactor.GetItemUseCase
import gg.op.lol.domain.interactor.GetRuneUseCase
import gg.op.lol.domain.interactor.GetSpellUseCase
import gg.op.lol.domain.models.Champion
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val getChampionsUseCase: GetChampionsUseCase,
    private val getSpellUseCase: GetSpellUseCase,
    private val getRuneUseCase: GetRuneUseCase,
    private val getItemUseCase: GetItemUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<Champion>>(UiState.Loading)
    val uiState: StateFlow<UiState<Champion>> get() = _uiState

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _uiState.value = UiState.Error(exception)
    }

    init {
        launchCoroutineIO {
            getChampionsUseCase.invoke(Unit).collect()
            getSpellUseCase.invoke(Unit).collect()
            getRuneUseCase.invoke(Unit).collect()
            getItemUseCase.invoke(Unit).collect()
        }
    }
}

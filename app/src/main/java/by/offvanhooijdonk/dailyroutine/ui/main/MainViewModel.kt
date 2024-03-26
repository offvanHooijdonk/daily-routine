package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class MainViewModel(private val navHolder: NavHolder) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()

    val sideEffectFlow = MutableSharedFlow<SideEffect>(replay = 0, extraBufferCapacity = 4)

    fun onAction(action: Action) {
        when (action) {
            Action.OnAddTaskClick -> _uiState.update { it.copy(isShowAddTaskForm = true) }
            is Action.OnNavClick -> navHolder.goToRoot(action.route)
            Action.OnAddTaskDialogDismissRequest -> _uiState.update { it.copy(isShowAddTaskForm = false) }
            Action.OnAddTaskSaveClick -> {
                // todo save to DAO
                // todo send to UI that modal can be closed
                //_uiState.update { it.copy(isShowAddTaskForm = false) }
                sideEffectFlow.tryEmit(SideEffect.CloseAddTaskDialogEffect)
            }
        }
    }

    sealed interface Action {
        data class OnNavClick(val route: String) : Action
        data object OnAddTaskClick : Action
        data object OnAddTaskDialogDismissRequest : Action
        data object OnAddTaskSaveClick : Action
    }

    sealed interface SideEffect {
        data object CloseAddTaskDialogEffect: SideEffect
    }

    data class State(
        val isShowAddTaskForm: Boolean = false,
        //val is: Boolean = false,
    )
}
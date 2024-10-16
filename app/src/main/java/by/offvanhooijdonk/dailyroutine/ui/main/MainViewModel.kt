package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.offvanhooijdonk.dailyroutine.ui.nav.EditEventTransmitter
import by.offvanhooijdonk.dailyroutine.ui.nav.NavHolder
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pro.respawn.flowmvi.api.MVIAction
import pro.respawn.flowmvi.api.MVIIntent
import pro.respawn.flowmvi.api.MVIState

class MainViewModel(
    private val editEventTransmitter: EditEventTransmitter,
    private val navHolder: NavHolder,
    //private val addTaskContainer: AddTaskContainer,
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()

    init {
        editEventTransmitter.events.onEach { _ ->
            _uiState.update { it.copy(isShowAddTaskForm = true) }
        }.launchIn(viewModelScope)

        /*viewModelScope.launch {
            subscribe(addTaskContainer.store) { state ->
                _uiState.update { state }
            }
        }*/
    }

    fun onAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                Action.OnAddTaskClick -> { _uiState.update { it.copy(isShowAddTaskForm = true) } }
                is Action.OnNavClick -> navHolder.goToRoot(action.route)
                Action.OnAddTaskDialogDismissRequest -> {
                    _uiState.update { it.copy(isShowAddTaskForm = false) }
                    clearTaskForm()
                }
                is Action.OnTaskTitleInput -> _uiState.update { it.copy(taskTitleInput = action.input) }
                Action.OnAddTaskSaveClick -> {
                    /*viewModelScope.launch {
                        taskDao.insert(TaskModel(0, _uiState.value.taskTitleInput, isMarked = false).toNewEntity())
                        clearTaskForm()
                    }*/
                    /*Log.d("👀", "Intent passed")
                    addTaskContainer.store.intent(action)*/
                }
            }
        }
    }

    private fun clearTaskForm() {
        _uiState.update { it.copy(taskTitleInput = "") }
    }

    sealed interface Action: MVIIntent {
        data class OnNavClick(val route: String) : Action
        data object OnAddTaskClick : Action
        data object OnAddTaskDialogDismissRequest : Action
        data object OnAddTaskSaveClick : Action
        data class OnTaskTitleInput(val input: String) : Action
    }

    sealed interface SideEffect : MVIAction {
        data class ErrorMessage(val message: String) : SideEffect
    }

    sealed interface EditEvent {
        data class EditTask(val id: Int) : EditEvent
        data object CreateTask : EditEvent
    }

    data class State(
        val isShowAddTaskForm: Boolean = false,

        val taskTitleInput: String = "",
    ) : MVIState
}

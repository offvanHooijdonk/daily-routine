package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskDao
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskEntity
import by.offvanhooijdonk.dailyroutine.domain.model.TaskModel
import by.offvanhooijdonk.dailyroutine.domain.model.toNewEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val navHolder: NavHolder,
    private val taskDao: TaskDao,
) : ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            Action.OnAddTaskClick -> {
                clearTaskForm()
                _uiState.update { it.copy(isShowAddTaskForm = true) }
            }
            is Action.OnNavClick -> navHolder.goToRoot(action.route)
            Action.OnAddTaskDialogDismissRequest -> _uiState.update { it.copy(isShowAddTaskForm = false) }
            is Action.OnTaskTitleInput -> _uiState.update { it.copy(taskTitleInput = action.input) }
            Action.OnAddTaskSaveClick -> {
                viewModelScope.launch {
                    taskDao.insert(TaskModel(0, _uiState.value.taskTitleInput).toNewEntity())
                    clearTaskForm()
                }
            }
        }
    }

    private fun clearTaskForm() {
        _uiState.update { it.copy(taskTitleInput = "") }
    }

    sealed interface Action {
        data class OnNavClick(val route: String) : Action
        data object OnAddTaskClick : Action
        data object OnAddTaskDialogDismissRequest : Action
        data object OnAddTaskSaveClick : Action
        data class OnTaskTitleInput(val input: String) : Action
    }

    data class State(
        val isShowAddTaskForm: Boolean = false,

        val taskTitleInput: String = "",
    )
}

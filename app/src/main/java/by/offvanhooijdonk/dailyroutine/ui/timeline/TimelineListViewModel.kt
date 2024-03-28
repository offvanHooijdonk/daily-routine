package by.offvanhooijdonk.dailyroutine.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskDao
import by.offvanhooijdonk.dailyroutine.domain.model.TaskModel
import by.offvanhooijdonk.dailyroutine.domain.model.toModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimelineListViewModel(
    private val taskDao: TaskDao,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            taskDao.getAll().collect { list ->
                _uiState.update { state -> state.copy(tasksList = list.map { it.toModel() }) }
            }
        }
        _uiState.update { it.copy(tasksList = previewTasks) }
    }

    data class UiState(
        val tasksList: List<TaskModel> = listOf()
    )
}
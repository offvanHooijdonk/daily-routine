package by.offvanhooijdonk.dailyroutine.ui.timeline

import androidx.lifecycle.ViewModel
import by.offvanhooijdonk.dailyroutine.domain.model.TaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TimelineListViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(tasksList = previewTasks) }
    }

    data class UiState(
        val tasksList: List<TaskModel> = listOf()
    )
}
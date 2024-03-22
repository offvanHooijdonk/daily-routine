package by.offvanhooijdonk.dailyroutine.ui.timeline

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.offvanhooijdonk.dailyroutine.domain.model.TaskModel
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme

@Deprecated("Remove")
@Composable
fun TimelineListScreenWrapper(viewModel: TimelineListViewModel) {
    TimelineListScreen(state = viewModel.uiState.collectAsState().value)
}

@Composable
fun TimelineListScreen(state: TimelineListViewModel.UiState) {
    LazyColumn {
        items(items = state.tasksList, key = { it.id }) { task ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)) {
                Text(text = task.text, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_TimelineListScreen() {
    DailyRoutineTheme {
        TimelineListScreen(state = TimelineListViewModel.UiState(tasksList = previewTasks))
    }
}

internal val previewTasks = listOf(
    TaskModel(1, "Today you do something"),
    TaskModel(2, "Review Pull Requests for God's sake!"),
    TaskModel(4, "Think deeply"),
    TaskModel(5, "Make a pet project"),
    TaskModel(6, "Find out what to do"),
    TaskModel(17, "Relax eventually"),
)
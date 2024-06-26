package by.offvanhooijdonk.dailyroutine.ui.timeline

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.offvanhooijdonk.dailyroutine.R
import by.offvanhooijdonk.dailyroutine.domain.model.TaskModel
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme

@Composable
fun TimelineListScreen(state: TimelineListViewModel.UiState, onAction: (TimelineListViewModel.Action) -> Unit) {
    LazyColumn {
        items(items = state.tasksList, key = { it.id }) { task ->
            TimelineTaskItem(onAction, task)
        }
    }
}

@Composable
private fun TimelineTaskItem(
    onAction: (TimelineListViewModel.Action) -> Unit,
    task: TaskModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAction(TimelineListViewModel.Action.OnTaskClick(task.id)) }
            .padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .padding(4.dp)
                    .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onSurface), shape = CircleShape)
                    .clickable { }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.weight(1.0f))
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { onAction(TimelineListViewModel.Action.OnTaskToggleMarked(task.id)) }) {
                Icon(
                    painter = painterResource(if (task.isMarked) R.drawable.ic_star else R.drawable.ic_star_border),
                    contentDescription = null,
                    tint = animateColorAsState(
                        targetValue = if (task.isMarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                        label = "star_color",
                    ).value,
                )
            }
        }
        Row {
            Spacer(modifier = Modifier.width(36.dp))
            AssistChip(onClick = { /*TODO*/ }, label = { Text(text = "Today 12:55") })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_TimelineListScreen() {
    DailyRoutineTheme {
        TimelineListScreen(state = TimelineListViewModel.UiState(tasksList = previewTasks)) {}
    }
}

internal val previewTasks = listOf(
    TaskModel(1, "Today you do something", isMarked = false),
    TaskModel(2, "Review Pull Requests for God's sake!", isMarked = false),
    TaskModel(4, "Think deeply", isMarked = true),
    TaskModel(5, "Make a pet project", isMarked = false),
    TaskModel(6, "Find out what to do", isMarked = true),
    TaskModel(17, "Relax eventually", isMarked = false),
)
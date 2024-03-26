@file:OptIn(ExperimentalMaterial3Api::class)

package by.offvanhooijdonk.dailyroutine.ui.main.addtask

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.offvanhooijdonk.dailyroutine.R
import by.offvanhooijdonk.dailyroutine.ui.main.MainViewModel
import by.offvanhooijdonk.dailyroutine.ui.theme.DailyRoutineTheme

@Composable
fun AddTaskForm(modifier: Modifier, onAction: (MainViewModel.Action) -> Unit, onDismiss: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val focusRequester = FocusRequester()
        TextField(modifier = Modifier.focusRequester(focusRequester).weight(1.0f), value = "", onValueChange = {})
        Spacer(modifier = Modifier.width(40.dp))
        FloatingActionButton(onClick = {
            onAction(MainViewModel.Action.OnAddTaskSaveClick)
            onDismiss()
        }) {
            Icon(painter = painterResource(R.drawable.ic_save_task), contentDescription = null)
        }

        LaunchedEffect(key1 = Unit) { focusRequester.requestFocus() }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_AddTaskForm() {
    DailyRoutineTheme {
        AddTaskForm(modifier = Modifier.fillMaxWidth(), onAction = {}, onDismiss = {})
    }
}
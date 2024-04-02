@file:OptIn(ExperimentalMaterial3Api::class)

package by.offvanhooijdonk.dailyroutine.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import by.offvanhooijdonk.dailyroutine.ui.main.addtask.AddTaskForm
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel, navHolder: NavHolder) {
    val navController = rememberNavController()
    navHolder.storeNavController(navController)
    val state = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar(navHolder.getBackStackEntryAsState().value, viewModel::onAction) }
    ) { contentPaddings ->
        Surface(modifier = Modifier.padding(contentPaddings)) {
            if (state.isShowAddTaskForm) {
                AddTaskModalDialog(state = state, onAction = viewModel::onAction)
            }

            AppNavigation(navController)
        }
    }
}

@Composable
fun AddTaskModalDialog(state: MainViewModel.State, onAction: (MainViewModel.Action) -> Unit) {
    val modalSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    fun onClosed() {
        onAction(MainViewModel.Action.OnAddTaskDialogDismissRequest)
    }

    ModalBottomSheet(
        onDismissRequest = ::onClosed,
        sheetState = modalSheetState,
    ) {
        AddTaskForm(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            state = state,
            onAction = onAction,
        ) {
            scope.launch { modalSheetState.hide() }.invokeOnCompletion { onClosed() }
        }
    }
}
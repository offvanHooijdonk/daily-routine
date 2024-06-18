package by.offvanhooijdonk.dailyroutine.ui.main

import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskDao
import kotlinx.coroutines.Dispatchers
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.dsl.updateState
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.plugins.reduce

class AddTaskContainer(private val taskDao: TaskDao) {
    val store = store<MainViewModel.State, MainViewModel.Action, MainViewModel.SideEffect>(MainViewModel.State()) {
        configure {
            parallelIntents = true
            coroutineContext = Dispatchers.Default
            atomicStateUpdates = true
        }

        recover { action(MainViewModel.SideEffect.ErrorMessage(it.message ?: "Error")); null }

        reduce { intent ->
            updateState<MainViewModel.State, _> { copy() }
        }
    }

}
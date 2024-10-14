package by.offvanhooijdonk.dailyroutine.ui.main

import android.util.Log
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskDao
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskEntity
import kotlinx.coroutines.Dispatchers
import pro.respawn.flowmvi.api.Container
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.dsl.updateState
import pro.respawn.flowmvi.dsl.withState
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.plugins.reduce
import pro.respawn.flowmvi.plugins.whileSubscribed

class AddTaskContainer(private val taskDao: TaskDao): Container<MainViewModel.State, MainViewModel.Action, MainViewModel.SideEffect> {
    override val store = store(MainViewModel.State()) {
        configure {
            parallelIntents = true
            coroutineContext = Dispatchers.Default
            atomicStateUpdates = true
        }

        recover { action(MainViewModel.SideEffect.ErrorMessage(it.message ?: "Error")); null }

        reduce { intent ->
            Log.d("👀", "In reducer")
            when (intent) {
                MainViewModel.Action.OnAddTaskSaveClick -> {
                    Log.d("👀", "Reducing")
                    withState<MainViewModel.State, _> {
                        Log.d("👀", "Saving '$taskTitleInput'")
                        taskDao.insert(TaskEntity(id = 0, title = taskTitleInput, createdAt = System.currentTimeMillis()))
                    }
                    updateState<MainViewModel.State, _> {
                        Log.d("👀", "Updating state")
                        copy(isShowAddTaskForm = false, taskTitleInput = "")
                    }
                }
                else -> Unit
            }
        }
    }
}
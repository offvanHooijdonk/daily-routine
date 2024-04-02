package by.offvanhooijdonk.dailyroutine.domain.model

import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskEntity

data class TaskModel(
    val id: Int,
    val title: String,
    val isMarked: Boolean,
)

fun TaskModel.toNewEntity(): TaskEntity = TaskEntity(0, title, System.currentTimeMillis())

fun TaskEntity.toModel(): TaskModel = TaskModel(id, title, isMarked)
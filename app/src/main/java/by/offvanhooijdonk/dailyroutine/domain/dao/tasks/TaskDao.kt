package by.offvanhooijdonk.dailyroutine.domain.dao.tasks

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(entity: TaskEntity)

    @Delete
    suspend fun delete(entity: TaskEntity)

    @Query("select * from TaskEntity order by createdAt")
    fun getAll(): Flow<List<TaskEntity>>
}
package by.offvanhooijdonk.dailyroutine.domain.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskDao
import by.offvanhooijdonk.dailyroutine.domain.dao.tasks.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        fun create(applicationContext: Context) = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME).build()

        private const val DB_NAME = "daily-routine-0.0.1"
    }
}
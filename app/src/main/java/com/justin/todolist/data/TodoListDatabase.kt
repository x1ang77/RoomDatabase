package com.justin.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justin.todolist.data.models.Task

@Database(entities = [Task::class], version = 1)
abstract class TodoListDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "todo_list_database"
    }
}
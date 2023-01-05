package com.joel.todolistfragments.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.data.model.User

@Database(entities = [Task::class, User::class], version = 2)
abstract class TodoListDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "todo_list_database"
    }
}
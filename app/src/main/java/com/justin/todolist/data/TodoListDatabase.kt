package com.justin.todolist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justin.todolist.data.models.Task
import com.justin.todolist.data.models.User

// to update the application, need to update the version code/number
@Database(entities = [Task::class, User::class], version = 2)
abstract class TodoListDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "todo_list_database"
    }
}
package com.caaron.todolistfragment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.caaron.todolistfragment.data.model.Task
import com.caaron.todolistfragment.data.model.User

@Database(entities = [Task::class, User::class], version = 1)
abstract  class TodoListDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object{
        const val DATABASE_NAME = "todo_list_database"
    }
}
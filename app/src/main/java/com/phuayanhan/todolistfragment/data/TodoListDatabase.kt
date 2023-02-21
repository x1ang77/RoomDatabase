package com.phuayanhan.todolistfragment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.data.model.User

@Database(entities = [Task::class, User::class], version = 2)
abstract class TodoListDatabase:RoomDatabase() {
    abstract val taskDao:TaskDao

    companion object{
        const val DATABASE_NAME = "todo_list_database"
    }

}
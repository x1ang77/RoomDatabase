package com.justin.todolist

import android.app.Application
import androidx.room.Room
import com.justin.todolist.data.TodoListDatabase
import com.justin.todolist.repository.TaskRepository

class MyApplication : Application() {
    lateinit var taskRepo: TaskRepository

    override fun onCreate() {
        super.onCreate()

        val todoListDatabase =
            Room.databaseBuilder(this, TodoListDatabase::class.java, TodoListDatabase.DATABASE_NAME)
                .build()

        taskRepo = TaskRepository(todoListDatabase.taskDao)
    }
}
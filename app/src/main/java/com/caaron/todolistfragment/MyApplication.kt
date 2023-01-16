package com.caaron.todolistfragment

import android.app.Application
import androidx.room.Room
import com.caaron.todolistfragment.data.TodoListDatabase
import com.caaron.todolistfragment.repository.TaskRepository
import com.caaron.todolistfragment.repository.TaskRepositoryFake

class MyApplication : Application() {
    val taskRepoFake = TaskRepositoryFake.getInstance()

    lateinit var taskRepo: TaskRepository

    override fun onCreate() {

        super.onCreate()
        val todoListDatabase = Room.databaseBuilder(
            this,
            TodoListDatabase::class.java,
            TodoListDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

        taskRepo = TaskRepository(todoListDatabase.taskDao)
    }
}
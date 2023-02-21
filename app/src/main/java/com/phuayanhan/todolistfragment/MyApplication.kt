
package com.phuayanhan.todolistfragment

import android.app.Application
import androidx.room.Room
import com.phuayanhan.todolistfragment.data.TodoListDatabase
import com.phuayanhan.todolistfragment.repository.TaskRepository
import com.phuayanhan.todolistfragment.repository.TaskRepositoryFake

class MyApplication: Application() {
    val taskRepoFake = TaskRepositoryFake.createInstance()

    lateinit var taskRepo:TaskRepository

    override fun onCreate() {
        super.onCreate()
        val todoListDatabase = Room.databaseBuilder(this,
            TodoListDatabase::class.java,TodoListDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()
        taskRepo = TaskRepository(todoListDatabase.taskDao)
    }
}
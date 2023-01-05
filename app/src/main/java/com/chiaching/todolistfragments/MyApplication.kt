package com.chiaching.todolistfragments

import android.app.Application
import androidx.room.Room
import com.chiaching.todolistfragments.data.TodoListDatabase
import com.chiaching.todolistfragments.repository.TaskRepository
import com.chiaching.todolistfragments.repository.TaskRepositoryFake

class MyApplication: Application(){
    val taskRepoFake = TaskRepositoryFake.getInstance()

    lateinit var taskRepo: TaskRepository

    override fun onCreate() {
        super.onCreate()
        val todoListDatabase = Room.databaseBuilder(
            this,
            TodoListDatabase::class.java,
            TodoListDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

        taskRepo = TaskRepository(todoListDatabase.taskDao)
    }


}
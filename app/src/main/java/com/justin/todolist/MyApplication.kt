package com.justin.todolist

import android.app.Application
import com.justin.todolist.repository.TaskRepository

class MyApplication : Application() {
    val taskRepo = TaskRepository.getInstance()
}
package com.joel.todolistfragments

import android.app.Application
import com.joel.todolistfragments.repository.TaskRepository

class MyApplication: Application() {
    val taskRepo = TaskRepository.getInstance()
}
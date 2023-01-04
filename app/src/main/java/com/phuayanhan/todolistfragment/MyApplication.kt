package com.phuayanhan.todolistfragment

import android.app.Application
import com.phuayanhan.todolistfragment.repository.TaskRepository

class MyApplication: Application() {
    val taskRepo = TaskRepository.createInstance()
}
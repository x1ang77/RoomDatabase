package com.xiangze.todolistfragments

import android.app.Application
import com.xiangze.todolistfragments.repository.TaskRepository

class MyApplication: Application() {
    val taskRepo = TaskRepository.getInstance()

}
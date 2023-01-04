package com.chiaching.todolistfragments

import android.app.Application
import com.chiaching.todolistfragments.repository.TaskRepository

class MyApplication: Application(){
    val taskRepo = TaskRepository.getInstance()
}
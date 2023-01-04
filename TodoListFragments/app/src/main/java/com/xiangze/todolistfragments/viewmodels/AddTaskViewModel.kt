package com.xiangze.todolistfragments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xiangze.todolistfragments.model.Task
import com.xiangze.todolistfragments.repository.TaskRepository

class AddTaskViewModel(private val repo: TaskRepository): ViewModel() {
    fun addTask(task: Task) {
        repo.addTask(task)
    }

    class Provider(private val repo: TaskRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTaskViewModel(repo) as T
        }
    }
}
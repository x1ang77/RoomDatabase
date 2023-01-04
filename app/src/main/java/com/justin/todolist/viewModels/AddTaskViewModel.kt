package com.justin.todolist.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justin.todolist.data.models.Task
import com.justin.todolist.repository.TaskRepository

class AddTaskViewModel(private val repo: TaskRepository) : ViewModel() {
    fun addTask(task: Task) {
        repo.addTask(task)
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel>
                create(modelClass: Class<T>): T {
            return AddTaskViewModel(repo) as T
        }
    }
}
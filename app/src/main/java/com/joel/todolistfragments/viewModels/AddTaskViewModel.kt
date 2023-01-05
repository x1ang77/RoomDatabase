package com.joel.todolistfragments.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.repository.TaskRepository
import com.joel.todolistfragments.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class AddTaskViewModel(private val repo: TaskRepository) : ViewModel() {
    fun addTask(task: Task) {
        viewModelScope.launch {
            repo.addTask(task)
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTaskViewModel(repo) as T
        }
    }
}
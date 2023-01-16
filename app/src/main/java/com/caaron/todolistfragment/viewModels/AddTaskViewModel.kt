package com.caaron.todolistfragment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.caaron.todolistfragment.data.model.Task
import com.caaron.todolistfragment.repository.TaskRepository
import com.caaron.todolistfragment.repository.TaskRepositoryFake
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
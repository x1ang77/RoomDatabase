package com.joel.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.repository.TaskRepository
import com.joel.todolistfragments.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: TaskRepository) : ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            val res = repo.getTasks()
            tasks.value = res
        }
    }

    fun getTasksByTitle(title: String) {
        viewModelScope.launch {
            val res = repo.getTasksByTitle(title)
            tasks.value = res
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}
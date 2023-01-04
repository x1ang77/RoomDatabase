package com.justin.todolist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justin.todolist.data.models.Task
import com.justin.todolist.repository.TaskRepository

class HomeViewModel(val repo: TaskRepository) : ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getTasks()
    }

    fun getTasks() {
        val res = repo.getTasks()
        tasks.value = res
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}
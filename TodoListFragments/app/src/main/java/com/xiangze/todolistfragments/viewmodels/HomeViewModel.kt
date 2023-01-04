package com.xiangze.todolistfragments.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xiangze.todolistfragments.model.Task
import com.xiangze.todolistfragments.repository.TaskRepository
import com.xiangze.todolistfragments.ui.HomeFragment

class HomeViewModel(private val repo: TaskRepository): ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getTasks()
    }

    fun getTasks() {
        val res = repo.getTasks()
        tasks.value = res
    }
    class Provider(private val repo: TaskRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T

        }
    }
}
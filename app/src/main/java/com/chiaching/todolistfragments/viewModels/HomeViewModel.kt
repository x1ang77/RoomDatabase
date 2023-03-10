package com.chiaching.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.chiaching.todolistfragments.data.model.Task
import com.chiaching.todolistfragments.repository.TaskRepository
import com.chiaching.todolistfragments.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: TaskRepository): ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getTasks()
    }

    fun getTasks(){
        viewModelScope.launch{
            val res = repo.getTasks()
            tasks.value = res
        }
    }

    class Provider(private val repo: TaskRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}
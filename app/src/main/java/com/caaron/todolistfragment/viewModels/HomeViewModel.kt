package com.caaron.todolistfragment.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.caaron.todolistfragment.data.model.Task
import com.caaron.todolistfragment.repository.TaskRepository
import com.caaron.todolistfragment.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class HomeViewModel(val repo: TaskRepository): ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init{
        getTasks()
    }

    fun getTasks(){
        viewModelScope.launch {
        val res = repo.getTasks()
        tasks.value = res
        }
    }

    class Provider(val repo:TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}
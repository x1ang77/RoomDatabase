package com.phuayanhan.todolistfragment.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.repository.TaskRepository
import com.phuayanhan.todolistfragment.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class HomeViewModel(val repo: TaskRepository): ViewModel(){
    val tasks:MutableLiveData<List<Task>> = MutableLiveData()

    init{
        getTasks()
    }

    fun getTasks(){
        viewModelScope.launch{
            val res=repo.getTasks()
            tasks.value=res
        }
    }

    class Provider(val repo:TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Log.d("repo",repo.toString())
            return HomeViewModel(repo) as T
        }
    }
}
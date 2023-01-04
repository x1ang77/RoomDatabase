package com.phuayanhan.todolistfragment.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.phuayanhan.todolistfragment.model.Task
import com.phuayanhan.todolistfragment.repository.TaskRepository

class HomeViewModel(val repo:TaskRepository): ViewModel(){
    val tasks:MutableLiveData<List<Task>> = MutableLiveData()

    init{
        getTasks()
    }

    fun getTasks(){
        val res=repo.getTasks()
        tasks.value=res
    }

    class Provider(val repo:TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Log.d("repo",repo.toString())
            return HomeViewModel(repo) as T
        }
    }
}
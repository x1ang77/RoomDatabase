package com.phuayanhan.todolistfragment.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.repository.TaskRepository
import com.phuayanhan.todolistfragment.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class DetailsViewModel(private val repo: TaskRepository): ViewModel() {
    val task:MutableLiveData<Task> = MutableLiveData()

    fun getTaskById(id:Long){
        viewModelScope.launch {
            val res = repo.getTaskById(id)
            res?.let{
                task.value=it

            }
        }
    }

    fun deleteTask(id:Long){
        viewModelScope.launch {
            repo.deleteTask(id)
        }
    }
    class Provider(val repo: TaskRepository):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(repo) as T
        }
    }
}
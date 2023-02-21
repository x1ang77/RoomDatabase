package com.phuayanhan.todolistfragment.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.repository.TaskRepository
import kotlinx.coroutines.launch

class UpdateViewModel(private val repo: TaskRepository):ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()
    fun getTaskById(id:Long){
        viewModelScope.launch {
            val res = repo.getTaskById(id)
            res?.let{
                task.value=it

            }
        }
    }
    fun UpdateTask(id:Long,task: Task){
        viewModelScope.launch {
            repo.updateTask(id,task)
        }
    }
    class Provider(val repo: TaskRepository):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UpdateViewModel(repo) as T
        }
    }
}
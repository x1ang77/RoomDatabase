package com.chiaching.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.chiaching.todolistfragments.data.model.Task
import com.chiaching.todolistfragments.repository.TaskRepository
import kotlinx.coroutines.launch

class EditItemViewModel(private val repo: TaskRepository): ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun editTask(id: Long, task: Task){
        viewModelScope.launch {
            repo.updateTask(id, task)
        }
    }

    fun getTaskById(id:Long){
        viewModelScope.launch{
            val res = repo.getTaskById(id.toInt())
            res?.let{
                task.value = it
            }
        }
    }



    class Provider(private val repo: TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditItemViewModel(repo) as T
        }
    }
}
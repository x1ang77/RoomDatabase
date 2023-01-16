package com.caaron.todolistfragment.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.caaron.todolistfragment.data.model.Task
import com.caaron.todolistfragment.repository.TaskRepository
import kotlinx.coroutines.launch

class UpdateViewModel(private val repo: TaskRepository) : ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun updateTask(id: Long, task: Task) {
        viewModelScope.launch {
            repo.updateTask(id, task)
        }
    }

    fun getTaskById(id: Long) {
        viewModelScope.launch {
            repo.getTaskById(id)
            val res = repo.getTaskById(id)
            res?.let {
                task.value = it
            }
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UpdateViewModel(repo) as T
        }
    }
}
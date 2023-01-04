package com.justin.todolist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.justin.todolist.data.models.Task
import com.justin.todolist.repository.TaskRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repo: TaskRepository) : ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            val res = repo.getTaskById(id)
            res?.let {
                task.value = it
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            repo.deleteTask(id)
        }
    }

    class Provider(private val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(repo) as T
        }
    }
}
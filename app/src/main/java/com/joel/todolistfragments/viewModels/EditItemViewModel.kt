package com.joel.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.repository.TaskRepository
import com.joel.todolistfragments.ui.EditItemFragment
import kotlinx.coroutines.launch

class EditItemViewModel(private val repo: TaskRepository) : ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun getTaskById(id: Long) {
        viewModelScope.launch {
            val res = repo.getTaskById(id)
            res?.let {
                task.value = it
            }
        }
    }

    fun updateTask(id: Long, task: Task) {
        viewModelScope.launch {
            repo.updateTask(id, task)
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditItemViewModel(repo) as T
        }
    }
}
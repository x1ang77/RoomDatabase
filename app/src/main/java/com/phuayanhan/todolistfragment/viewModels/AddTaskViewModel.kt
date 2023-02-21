package com.phuayanhan.todolistfragment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.phuayanhan.todolistfragment.data.model.Task
import com.phuayanhan.todolistfragment.repository.TaskRepository
import com.phuayanhan.todolistfragment.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class AddTaskViewModel(private val repo: TaskRepository):ViewModel() {
    fun addTask(task: Task){
        viewModelScope.launch{
            repo.addTask(task)
        }
    }
    class Provider(private val repo:TaskRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTaskViewModel(repo) as T
        }
    }
}
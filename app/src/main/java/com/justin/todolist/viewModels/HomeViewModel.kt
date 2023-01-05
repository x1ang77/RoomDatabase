package com.justin.todolist.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.justin.todolist.data.models.Task
import com.justin.todolist.repository.TaskRepository
import com.justin.todolist.ui.HomeFragmentDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(val repo: TaskRepository) : ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val goToAddTask: MutableSharedFlow<Unit> = MutableSharedFlow()

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            val res = repo.getTasks()
            tasks.value = res
        }
    }

    fun navigateToAddTask() {
        viewModelScope.launch {
            goToAddTask.emit(Unit)
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            repo.deleteTask(id)
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}
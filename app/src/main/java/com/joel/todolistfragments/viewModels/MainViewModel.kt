package com.joel.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joel.todolistfragments.data.model.Task
import com.joel.todolistfragments.repository.TaskRepository

class MainViewModel : ViewModel() {
    val refreshWords: MutableLiveData<Boolean> = MutableLiveData(false)

    fun shouldRefreshWords(refresh: Boolean) {
        refreshWords.value = refresh
    }

}
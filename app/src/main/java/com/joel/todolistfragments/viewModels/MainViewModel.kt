package com.joel.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joel.todolistfragments.model.Task

class MainViewModel : ViewModel() {
    private val tasksMap: MutableMap<Int, Task> = mutableMapOf(
        0 to Task(0, "bug fix", "1/1/22", "task 1", 4)
    )

    val tasks: MutableLiveData<List<Task>> = MutableLiveData(mutableListOf())

    init {
        tasks.value = tasksMap.values.toList()
    }
}
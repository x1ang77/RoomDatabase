package com.chiaching.todolistfragments.repository

import androidx.lifecycle.MutableLiveData
import com.chiaching.todolistfragments.data.TaskDao
import com.chiaching.todolistfragments.data.model.Task

class TaskRepository(private val taskDao: TaskDao){

    suspend fun getTasks(): List<Task> {
        return taskDao.getTasks()
    }

    suspend fun addTask(task: Task){
        taskDao.insert(task)
    }

    suspend fun getTaskById(id: Int) : Task?{
        return taskDao.getTaskById(id)
    }

    suspend fun updateTask(id: Long, task: Task){
        taskDao.insert(task.copy(id.toInt()))
    }

    suspend fun deleteTask(id:Int){
        taskDao.delete(id)
    }

    suspend fun getTaskByTitle(title: String):List<Task>{
        return taskDao.getTaskByTitle(title)
    }

}

// Singleton
package com.caaron.todolistfragment.repository

import com.caaron.todolistfragment.data.TaskDao
import com.caaron.todolistfragment.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getTasks(): List<Task> {
        return taskDao.getTasks()
    }

    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun getTaskById(id: Long): Task? {
        return taskDao.getTaskById(id)
    }

    suspend fun updateTask(id: Long, task: Task) {
        taskDao.insert(task.copy(id = id))
    }

    suspend fun deleteTask(id: Long) {
        taskDao.delete(id)
    }

    suspend fun getTaskByTitle(title: String): List<Task>{
        return taskDao.getTaskByTitle(title)
    }

}
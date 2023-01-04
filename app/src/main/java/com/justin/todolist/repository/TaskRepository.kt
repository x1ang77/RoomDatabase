package com.justin.todolist.repository

import com.justin.todolist.data.TaskDao
import com.justin.todolist.data.models.Task

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun getTasks(): List<Task> {
        return taskDao.getTasks()
    }

    suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTaskById(id)
    }

    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(id: Int, task: Task) {
        taskDao.insert(task.copy(id = id))
    }

    suspend fun deleteTask(id: Int) {
        taskDao.delete(id)
    }
}
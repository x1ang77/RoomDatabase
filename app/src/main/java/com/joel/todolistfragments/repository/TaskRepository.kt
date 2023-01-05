package com.joel.todolistfragments.repository

import com.joel.todolistfragments.data.TaskDao
import com.joel.todolistfragments.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    private var counter = 0L
    private val tasksMap: MutableMap<Long, Task> = mutableMapOf(
        0L to Task(0L, "Bug 10001", "13/12/2022", "Bug fix", 2)
    )

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

    suspend fun getTasksByTitle(title: String): List<Task> {
        return taskDao.getTasksByTitle(title)
    }
}

// Singleton
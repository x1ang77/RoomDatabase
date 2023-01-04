package com.justin.todolist.repository

import com.justin.todolist.data.models.Task

class TaskRepository {
    private var counter = 0
    private val taskMap: MutableMap<Int, Task> = mutableMapOf(
        0 to Task(0, "Bug 1001", "13/12/22", "Fix Bugs")
    )
    fun getTasks(): List<Task> {
        return taskMap.values.toList()
    }

    fun getTaskById(id: Int): Task? {
        return taskMap[id]
    }

    fun addTask(task: Task): Task? {
//        taskMap[++counter] = task
        taskMap[++counter] = task.copy(id = counter)
        return taskMap[counter]
    }

    fun updateTask(id: Int, task: Task): Task? {
        taskMap[id] = task
        return taskMap[id]
    }

    fun deleteTask(id: Int) {
        taskMap.remove(id)
    }

    companion object {
        private var taskRepository: TaskRepository? = null
        fun getInstance(): TaskRepository {
            if (taskRepository == null) {
                taskRepository = TaskRepository()
            }

            return taskRepository!!
        }
    }
}

// Singleton
package com.chiaching.todolistfragments.repository

import com.chiaching.todolistfragments.data.model.Task

class TaskRepositoryFake {
    var counter = 0L
    val taskMap: MutableMap<Long, Task> = mutableMapOf(
        0L to Task(0,"Bug Fixed","21/12/22","Fix Bug",9)
    )

    fun getTasks(): List<Task> {
        return taskMap.values.toList()
    }

    fun addTask(task: Task): Task?{
        taskMap[++counter] = task.copy(id = counter.toInt())
        return taskMap[counter]
    }

    fun getTaskById(id: Long) : Task?{
        return taskMap[id]
    }

    fun updateTask(id:Long, task: Task): Task?{
        taskMap[id] = task
        return taskMap[id]
    }

    fun deleteTask(id:Long){
        taskMap.remove(id)
    }

    companion object{
        private var taskRepository: TaskRepositoryFake? = null

        fun getInstance(): TaskRepositoryFake{
            if(taskRepository == null){
                taskRepository = TaskRepositoryFake()
            }
            return taskRepository!!
        }
    }
}

// Singleton
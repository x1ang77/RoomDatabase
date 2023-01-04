package com.chiaching.todolistfragments.repository

import com.chiaching.todolistfragments.model.Task

class TaskRepository {
    var counter = 0L
    val taskMap: MutableMap<Long, Task> = mutableMapOf(
        0L to Task(0L,"Bug Fixed","21/12/22","Fix Bug",9)
    )

    fun getTasks(): List<Task> {
        return taskMap.values.toList()
    }

    fun addTask(task: Task): Task?{
        taskMap[++counter] = task.copy(id = counter)
        return taskMap[counter]
    }

    fun getTaskById(id: Long) :Task?{
        return taskMap[id]
    }

    fun updateTask(id:Long, task:Task): Task?{
        taskMap[id] = task
        return taskMap[id]
    }

    fun deleteTask(id:Long){
        taskMap.remove(id)
    }

    companion object{
        private var taskRepository: TaskRepository? = null

        fun getInstance(): TaskRepository{
            if(taskRepository == null){
                taskRepository = TaskRepository()
            }
            return taskRepository!!
        }
    }
}

// Singleton
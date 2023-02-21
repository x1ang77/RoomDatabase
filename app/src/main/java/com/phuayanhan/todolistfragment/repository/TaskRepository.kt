package com.phuayanhan.todolistfragment.repository

import com.phuayanhan.todolistfragment.data.TaskDao
import com.phuayanhan.todolistfragment.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getTasks():List<Task>{
        return taskDao.getTasks()
    }
    suspend fun addTask(task: Task){
        taskDao.insert(task)
    }
    suspend fun getTaskById(id:Long): Task? {
        return taskDao.getTaskById(id.toInt())
    }
    suspend fun updateTask(id:Long,task: Task){
        return taskDao.insert(task.copy(id= id))
    }
    suspend fun deleteTask(id:Long){
        taskDao.delete(id.toInt())
    }
    suspend fun getTasksByTitle(title:String): List<Task>{
        return taskDao.getTasksByTitle(title)
    }
}
package com.phuayanhan.todolistfragment.repository

import com.phuayanhan.todolistfragment.data.model.Task

class TaskRepositoryFake {
    var counter = 0L
    val taskMap:MutableMap<Long, Task> = mutableMapOf(
        0L to Task(0L,"bug 10001","23/12/22","Fix Bug",0)
    )
    fun getTasks():List<Task>{
        return taskMap.values.toList()
    }
    fun addTask(task: Task): Task?{
        taskMap[++counter]=task.copy(id = counter)
        return taskMap[counter]
    }
    fun getTaskById(id:Long): Task? {
        return taskMap[id]
    }
    fun updateTask(id:Long,task: Task): Task?{
        taskMap[id]=task
        return taskMap[id]
    }
    fun deleteTask(id:Long){
        taskMap.remove(id)
    }
    companion object{
        private var taskRepositoryFake:TaskRepositoryFake?=null

        fun createInstance():TaskRepositoryFake{
            if(taskRepositoryFake==null){
                taskRepositoryFake = TaskRepositoryFake()
            }
            return taskRepositoryFake!!
        }
    }
}
//Singleton
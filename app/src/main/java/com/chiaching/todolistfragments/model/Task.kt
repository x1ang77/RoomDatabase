package com.chiaching.todolistfragments.model

data class Task(
    val id: Long? = null,
    val title: String,
    val date: String,
    val details: String,
    val priority: Short
)

//{
//    fun getTaskWithId(_id:Long): Task{
//        return Task(
//            _id,
//            title,
//            date,
//            details,
//            priority
//        )
//    }
//}

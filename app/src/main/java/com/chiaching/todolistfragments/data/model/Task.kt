package com.chiaching.todolistfragments.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val date: String,
    val details: String,
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

package com.phuayanhan.todolistfragment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id:Long?=null,
    val title:String,
    val date:String,
    val detail:String,
    val priority:Int
)

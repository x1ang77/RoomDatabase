package com.phuayanhan.todolistfragment.model

data class Task(
    val id:Long?=null,
    val title:String,
    val date:String,
    val detail:String,
    val priority:Int
)

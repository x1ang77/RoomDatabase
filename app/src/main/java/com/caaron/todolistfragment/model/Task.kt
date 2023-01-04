package com.caaron.todolistfragment.model

data class Task(
    val id: Long? = null,
    val title: String,
    val date: String,
    val details: String,
    val priority: Int
)
package com.justin.todolist.data.models

import androidx.room.Entity

@Entity
data class Task(
    val id: Int? = null,
    val title: String,
    val date: String,
    val details: String,
)
package com.justin.todolist.data.models

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
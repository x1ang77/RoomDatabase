package com.joel.todolistfragments.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int? = null,
    val name: String
)
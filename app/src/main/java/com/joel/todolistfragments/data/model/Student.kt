package com.joel.todolistfragments.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    val studentId: Int? = null,
    val name: String,
    val deptId: Int
)
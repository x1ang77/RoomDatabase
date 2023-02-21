package com.joel.todolistfragments.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dept(
    @PrimaryKey(autoGenerate = true)
    val deptId: Int? = null,
    val name: String
)
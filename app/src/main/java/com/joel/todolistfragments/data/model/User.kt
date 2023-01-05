package com.joel.todolistfragments.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Long? = null,
    val username: String,
    val email: String,
    @ColumnInfo(name = "pass")
    val password: String
)
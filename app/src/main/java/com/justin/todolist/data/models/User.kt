package com.justin.todolist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Long?,
    val name: String,
    val email: String,

    // @ColumnInfo changes the name of the table column header
    @ColumnInfo(name = "pass") val password: String
)

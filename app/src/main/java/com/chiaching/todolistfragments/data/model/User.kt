package com.chiaching.todolistfragments.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Long?,
    val name: String?,
    val email: String?,
    @ColumnInfo(name = "pass")
    val password: String?
)

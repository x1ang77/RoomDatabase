package com.nathalie.todolistfragments.Model

data class Note(
    val id: Long? = null,
    val title: String,
    val details: String,
    val color: String? = null
)

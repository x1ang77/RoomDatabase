package com.joel.todolistfragments.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["studentId", "subjectId"])
data class StudentSubjectCrossRef(
    val studentId: Int,
    val subjectId: Int
)
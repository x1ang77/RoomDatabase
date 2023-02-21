package com.joel.todolistfragments.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class DeptWithStudents(
    @Embedded val dept: Dept,
    @Relation(
        parentColumn = "deptId",
        entityColumn = "deptId"
    )
    val students: List<Student>
)
package com.joel.todolistfragments.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentsWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "subjectId",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)
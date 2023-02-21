package com.joel.todolistfragments.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joel.todolistfragments.data.model.*

@Database(
    entities = [Task::class, User::class, Student::class, Dept::class, Subject::class, StudentSubjectCrossRef::class],
    version = 4
)
abstract class TodoListDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "todo_list_database"
    }
}
package com.chiaching.todolistfragments.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chiaching.todolistfragments.data.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Query ("SELECT * from task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(id: Task)

    @Query ("DELETE FROM task WHERE id = :id")
    suspend fun delete(id: Int)

    @Query ("SELECT * FROM task WHERE title LIKE :title")
    suspend fun getTaskByTitle(title: String): List<Task>
}
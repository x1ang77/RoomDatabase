package com.caaron.todolistfragment.data

import android.provider.UserDictionary.Words
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.caaron.todolistfragment.data.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM task where id = :id")
    suspend fun getTaskById(id: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun delete(id:Long)

    @Query("SELECT * FROM task WHERE title LIKE :title")
    suspend fun getTaskByTitle(title: String): List<Task>

}
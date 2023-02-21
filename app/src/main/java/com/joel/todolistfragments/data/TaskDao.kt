package com.joel.todolistfragments.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.joel.todolistfragments.data.model.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM task WHERE title LIKE :title")
    suspend fun getTasksByTitle(title: String): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDept(dept: Dept)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM Dept")
    suspend fun getDepartmentWithStudents(): List<DeptWithStudents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Transaction
    @Query("SELECT * FROM Student")
    suspend fun getStudentsWithSubjects(): List<StudentsWithSubjects>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(studentSubjectCrossRef: StudentSubjectCrossRef)
}
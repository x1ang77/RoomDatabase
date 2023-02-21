package com.joel.todolistfragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.joel.todolistfragments.data.model.*
import com.joel.todolistfragments.repository.TaskRepository
import com.joel.todolistfragments.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: TaskRepository) : ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()
    private val departments = listOf(Dept(name = "CSE"), Dept(name = "EEE"))
    private val students = listOf(
        Student(name = "Aang", deptId = 1),
        Student(name = "Katara", deptId = 1),
        Student(name = "Sokka", deptId = 2),
    )
    private val subjects = listOf(
        Subject(name = "Algorithm"),
        Subject(name = "Vector Analysis"),
        Subject(name = "Cryptography"),
        Subject(name = "Number Theory")
    )
    private val studentsSubjects = listOf(
        StudentSubjectCrossRef(1, 1),
        StudentSubjectCrossRef(1, 2),
        StudentSubjectCrossRef(1, 4),
        StudentSubjectCrossRef(2, 1),
        StudentSubjectCrossRef(2, 3),
        StudentSubjectCrossRef(3, 2),
        StudentSubjectCrossRef(3, 3),
        StudentSubjectCrossRef(3, 4),
    )

    init {
        getTasks()
        viewModelScope.launch {
            departments.forEach {
                repo.insertDept(it)
            }
            students.forEach {
                repo.insertStudent(it)
            }
//            val res = repo.getDepartmentWithStudents()
//            Log.d("debugging", res.toString())
//            res.forEach {
//                Log.d("debugging", "${it.dept}")
//                Log.d("debugging", "${it.students}")
//            }
            subjects.forEach {
                repo.insertSubject(it)
            }
            studentsSubjects.forEach {
                repo.insertStudentSubjectCrossRef(it)
            }
            val res = repo.getStudentsWithSubjects()
            res.forEach {
                Log.d("debugging", "${it.student}")
                Log.d("debugging", "${it.subjects}")
            }
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            val res = repo.getTasks()
            tasks.value = res
        }
    }

    fun getTasksByTitle(title: String) {
        viewModelScope.launch {
            val res = repo.getTasksByTitle(title)
            tasks.value = res
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }
}
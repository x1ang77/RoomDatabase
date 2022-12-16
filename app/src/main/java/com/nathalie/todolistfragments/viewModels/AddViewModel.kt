package com.nathalie.todolistfragments.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nathalie.todolistfragments.Model.Note
import com.nathalie.todolistfragments.repository.NoteRepository

class AddViewModel(private val repo: NoteRepository): ViewModel() {
    fun addTask(note: Note){
        Log.d("Add", note.toString())
        repo.addNote(note)
    }

    class Provider(private val repo: NoteRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddViewModel(repo) as T
        }
    }
}
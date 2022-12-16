package com.nathalie.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nathalie.todolistfragments.Model.Note
import com.nathalie.todolistfragments.repository.NoteRepository

class EditViewModel(private val repo: NoteRepository): ViewModel() {
    val note: MutableLiveData<Note> = MutableLiveData()

    fun getNoteById(id: Long) {
        val res = repo.getNoteById(id)
        res?.let {
            note.value = it
        }
    }


    fun deleteNote(id: Long) {
        repo.deleteNote(id)
    }


    fun updateNote(id: Long, task: Note) {
        repo.updateNote(id, task)
    }

    class Provider(val repo: NoteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditViewModel(repo) as T
        }
    }
}
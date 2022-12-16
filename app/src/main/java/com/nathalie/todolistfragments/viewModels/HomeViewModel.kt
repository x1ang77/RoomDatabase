package com.nathalie.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nathalie.todolistfragments.Model.Note
import com.nathalie.todolistfragments.repository.NoteRepository

class HomeViewModel(val repo: NoteRepository) : ViewModel() {
    val notes: MutableLiveData<List<Note>> = MutableLiveData()

    init {
        getNotes()
    }

     fun getNotes() {
        val res = repo.getNotes()
        notes.value = res
    }

    class Provider(val repo: NoteRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }

}
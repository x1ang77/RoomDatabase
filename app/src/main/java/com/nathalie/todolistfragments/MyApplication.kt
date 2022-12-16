package com.nathalie.todolistfragments

import android.app.Application
import com.nathalie.todolistfragments.repository.NoteRepository

class MyApplication: Application() {
    val taskRepo = NoteRepository.getInstance()
}
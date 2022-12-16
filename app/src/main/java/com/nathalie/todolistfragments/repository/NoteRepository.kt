package com.nathalie.todolistfragments.repository

import com.nathalie.todolistfragments.Model.Note

class NoteRepository {
    private var counter = 4L
    private val notesMap: MutableMap<Long, Note> = mutableMapOf(
        0L to Note(0L, "William Wallace", "Every man dies. Not every man lives.", "#5f5449"),
        1L to Note(1L, "Maya Angelou", "We need much less than we think we need.", "#9b6a6c"),
        2L to Note(
            2L,
            "Mark Twain",
            "Never let your best friends get lonely, keep disturbing them.",
            "#b09398"
        ),
        3L to Note(
            3L,
            "Lazy",
            "Lazy people fact #2347827309018287. You were too lazy to read that number.",
            "#cedfd9"
        ),
        4L to Note(
            4L,
            "Barack Obama",
            "At night, I can’t fall asleep. In the morning, I can’t get up.",
            "#ebfcfb"
        ),
    )

    fun getNotes(): List<Note> {
        return notesMap.values.toList()
    }

    fun addNote(note: Note): Note? {
        notesMap[++counter] = note.copy(id = counter)
        return notesMap[counter]
    }

    fun getNoteById(id: Long): Note? {
        return notesMap[id]
    }

    fun updateNote(id: Long, note: Note): Note? {
        notesMap[id] = note
        return notesMap[id]
    }

    fun deleteNote(id: Long) {
        notesMap.remove(id)
    }

    companion object {
        var noteRepository: NoteRepository? = null

        fun getInstance(): NoteRepository {
            if (noteRepository == null) {
                noteRepository = NoteRepository()
            }

            return noteRepository!!
        }
    }
}


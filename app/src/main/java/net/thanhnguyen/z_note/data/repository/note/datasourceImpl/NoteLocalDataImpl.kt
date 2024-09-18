package net.thanhnguyen.z_note.data.repository.note.datasourceImpl

import net.thanhnguyen.z_note.data.db.NoteDAO
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteLocalData

class NoteLocalDataImpl(private val noteDao: NoteDAO): NoteLocalData {
    override suspend fun getNoteFromDB(): List<NoteItem> {
        return noteDao.getAll()
    }

    override fun getNoteFlow() = noteDao.flows

    override suspend fun insertNoteToDB(list: List<NoteItem>) {
        list.map { noteDao.insertNote(it) }
    }

    override suspend fun updateNoteToDB(list: List<NoteItem>) {
        list.map { noteDao.updateNote(it) }
    }

    override suspend fun deleteNoteD(noteItem: NoteItem) {
        noteDao.deleteNote(noteItem)
    }
}
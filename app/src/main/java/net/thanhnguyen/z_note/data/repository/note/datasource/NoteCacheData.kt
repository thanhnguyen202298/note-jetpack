package net.thanhnguyen.z_note.data.repository.note.datasource

import net.thanhnguyen.z_note.data.model.NoteItem

interface NoteCacheData {
    fun getNoteFromCache(): List<NoteItem>
    fun saveNoteToCache(list: List<NoteItem>)
}
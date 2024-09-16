package net.thanhnguyen.z_note.data.repository.note.datasource

import net.thanhnguyen.z_note.data.model.NoteItem

interface NoteCacheData {
    suspend fun getNoteFromCache(): List<NoteItem>
    suspend fun saveNoteToCache(list: List<NoteItem>)
}
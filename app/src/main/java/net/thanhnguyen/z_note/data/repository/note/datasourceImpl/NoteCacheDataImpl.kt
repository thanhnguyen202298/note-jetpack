package net.thanhnguyen.z_note.data.repository.note.datasourceImpl

import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteCacheData

class NoteCacheDataImpl: NoteCacheData {
    private var noteList = ArrayList<NoteItem>()
    override suspend fun getNoteFromCache(): List<NoteItem> {
        return noteList
    }

    override suspend fun saveNoteToCache(list: List<NoteItem>) {
        noteList.clear()
        noteList = ArrayList(list)
    }
}
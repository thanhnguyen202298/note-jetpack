package net.thanhnguyen.z_note.data.repository.note.datasource

import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import net.thanhnguyen.z_note.data.model.Note
import net.thanhnguyen.z_note.data.model.NoteItem

interface NoteLocalData {
    suspend fun getNoteFromDB(): List<NoteItem>
    suspend fun insertNoteToDB(list: List<NoteItem>)
    suspend fun updateNoteToDB(list: List<NoteItem>)
    suspend fun deleteNoteD(noteItem: NoteItem)
    fun getNoteFlow(): Flow<ResultsChange<Note>>
}
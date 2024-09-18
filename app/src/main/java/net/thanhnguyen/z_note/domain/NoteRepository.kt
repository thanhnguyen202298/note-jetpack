package net.thanhnguyen.z_note.domain

import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import net.thanhnguyen.z_note.data.model.Note
import net.thanhnguyen.z_note.data.model.NoteItem

interface NoteRepository {
    suspend fun getNote(): List<NoteItem>
    suspend fun refreshData(): List<NoteItem>
    suspend fun updateNote(list: List<NoteItem>)
    suspend fun deleteNote(noteItem: NoteItem)
    suspend fun insertNote(noteItem: NoteItem)
    val flows: Flow<ResultsChange<Note>>
}
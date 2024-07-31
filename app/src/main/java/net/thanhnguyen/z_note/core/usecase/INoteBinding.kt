package net.thanhnguyen.z_note.core.usecase

import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.Flow
import net.thanhnguyen.z_note.core.model.Note

interface INoteBinding<T> {
    suspend fun insertNote(note: T)
    suspend fun updateNote(note: T)
    suspend fun deleteNote(note: T)
    suspend fun findNote(noteId: String):Note?
}
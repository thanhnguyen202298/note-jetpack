package net.thanhnguyen.z_note.data.db

import net.thanhnguyen.z_note.data.model.Note
import org.mongodb.kbson.ObjectId

interface IDAO<T> {
    suspend fun insertNote(note: T)
    suspend fun updateNote(note: T)
    suspend fun deleteNote(note: T)
    suspend fun findNote(noteId: ObjectId): Note?
}
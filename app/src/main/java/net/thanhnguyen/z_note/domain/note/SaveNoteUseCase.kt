package net.thanhnguyen.z_note.domain.note

import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.domain.NoteRepository

class SaveNoteUseCase(private val noteRepos: NoteRepository) {
    suspend fun execute(note: NoteItem) = noteRepos.insertNote(note)
}
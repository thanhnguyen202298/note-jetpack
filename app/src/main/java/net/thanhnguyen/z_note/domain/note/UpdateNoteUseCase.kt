package net.thanhnguyen.z_note.domain.note

import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.domain.NoteRepository

class UpdateNoteUseCase(private val noteRepos: NoteRepository) {
    suspend fun execute(list: List<NoteItem>) = noteRepos.updateNote(list)
}
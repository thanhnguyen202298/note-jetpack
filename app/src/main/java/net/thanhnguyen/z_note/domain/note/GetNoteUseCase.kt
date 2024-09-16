package net.thanhnguyen.z_note.domain.note

import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.domain.NoteRepository

class GetNoteUseCase(private val noteRespository: NoteRepository) {
    suspend fun execute(): List<NoteItem> = noteRespository.getNote()
    fun getNoteFlows() = noteRespository.getNoteFlow()
}
package net.thanhnguyen.z_note.data.repository.note.datasource

import net.thanhnguyen.z_note.data.model.NoteItem
import retrofit2.Response

interface NoteApiData {
    suspend fun getNoteData():Response<List<NoteItem>>
}
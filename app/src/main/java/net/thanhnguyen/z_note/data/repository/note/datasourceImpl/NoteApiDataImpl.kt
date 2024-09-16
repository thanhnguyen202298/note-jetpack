package net.thanhnguyen.z_note.data.repository.note.datasourceImpl

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import net.thanhnguyen.z_note.data.api.IService
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteApiData
import retrofit2.Response

class NoteApiDataImpl(private val noteService: IService): NoteApiData {
    override suspend fun getNoteData(): Response<List<NoteItem>> {
        return withContext(IO) { noteService.getQuery("") }
    }
}
package net.thanhnguyen.z_note.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import net.thanhnguyen.z_note.core.model.Note
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.core.usecase.INoteViewModel
import net.thanhnguyen.z_note.repository.NoteRepository
import org.mongodb.kbson.ObjectId

class NoteViewModel(val noteRepository: NoteRepository):ViewModel(), INoteViewModel {
    override val coroutineScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)
    override val jobManager: MutableList<Job> = mutableListOf()

    val noteState: MutableState<NoteModel> = mutableStateOf(NoteModel())

    val flows: Flow<ResultsChange<Note>>
        get() = noteRepository.flows

    fun insertNote(note: NoteModel) = runCoroutine { noteRepository.insertNote(note) }

    fun updateNote(note: NoteModel) = runCoroutine { delay(200L); noteRepository.updateNote(note) }

    fun deleteNote(note: NoteModel) = runCoroutine { delay(200L); noteRepository.deleteNote(note) }

    suspend fun find(noteId: ObjectId):Note? = withContext(coroutineScope.coroutineContext){ noteRepository.findNote(noteId) }
}
package net.thanhnguyen.z_note.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.util.fastMap
import androidx.lifecycle.ViewModel
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import net.thanhnguyen.z_note.core.model.Note
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.core.usecase.INoteViewModel
import net.thanhnguyen.z_note.core.usecase.UseCase
import net.thanhnguyen.z_note.repository.NoteRepository
import org.mongodb.kbson.ObjectId
import org.junit.Assert.*

class NoteViewModel(val noteRepository: NoteRepository):ViewModel(), INoteViewModel {
    override val coroutineScope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)
    override val jobManager: MutableList<Job> = mutableListOf()

    val noteState: MutableState<NoteModel> = mutableStateOf(NoteModel())

    val flows: Flow<ResultsChange<Note>>
        get() = noteRepository.flows

    val countnumber:MutableState<Int> = mutableStateOf(0)
    private val action = mutableStateOf(UseCase.NONE)

    fun insertNote(note: NoteModel) = runCoroutine {
        action.value = UseCase.INSERT
        noteRepository.insertNote(note)
    }

    fun updateNote(note: NoteModel) = runCoroutine {
        action.value = UseCase.UPDATE
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: NoteModel) = runCoroutine {
        action.value = UseCase.DELETE
        noteRepository.deleteNote(note)
    }

    suspend fun find(noteId: ObjectId):Note? = withContext(coroutineScope.coroutineContext){ noteRepository.findNote(noteId) }

    override fun onCleared() {
        destroyJob()
        super.onCleared()
    }
}
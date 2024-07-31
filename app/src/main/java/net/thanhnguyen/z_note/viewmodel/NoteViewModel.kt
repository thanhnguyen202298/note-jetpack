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
        runCoroutine {
            countnumber.value += 1
            flows.collect{
                if (action.value != UseCase.INSERT)return@collect
                val newcout = it.list.toList().count()
                assertEquals(countnumber.value, newcout)
            }
        }
    }

    fun updateNote(note: NoteModel) = runCoroutine {
        action.value = UseCase.UPDATE
        noteRepository.updateNote(note)
        runCoroutine {
            flows.collect{
                if (action.value != UseCase.UPDATE)return@collect
                val newcout = it.list.toList().find { it.note == note.note && it.title == it.title }
                assert(newcout != null)
            }
        }
    }

    fun deleteNote(note: NoteModel) = runCoroutine {
        action.value = UseCase.DELETE
        noteRepository.deleteNote(note)
        runCoroutine {
            countnumber.value -=1
            flows.collect{
                if (action.value != UseCase.DELETE)return@collect
                val newcout = it.list.toList().count()
                assertEquals(countnumber.value, newcout)
            }
        }
    }

    suspend fun find(noteId: ObjectId):Note? = withContext(coroutineScope.coroutineContext){ noteRepository.findNote(noteId) }
}
package net.thanhnguyen.z_note.presenter.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import net.thanhnguyen.z_note.data.model.Note
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.domain.events.UIEvent
import net.thanhnguyen.z_note.domain.note.DeleteNoteUseCase
import net.thanhnguyen.z_note.domain.note.GetNoteUseCase
import net.thanhnguyen.z_note.domain.note.SaveNoteUseCase
import net.thanhnguyen.z_note.domain.note.UpdateNoteUseCase
import net.thanhnguyen.z_note.presenter.ui.BottomNavItem

class NoteViewModel(
    private val getNoteUseCase: GetNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    val itemEditingState: MutableState<NoteItem> = mutableStateOf(NoteItem())
    val flows: Flow<ResultsChange<Note>> = getNoteUseCase.getNoteFlows()

    fun updateNote(noteItem: NoteItem) = viewModelScope.launch {
        updateNoteUseCase.execute(listOf(noteItem))
    }

    fun insertNote(noteItem: NoteItem) = viewModelScope.launch { saveNoteUseCase.execute(noteItem) }
    fun deleteNote(noteItem: NoteItem) = viewModelScope.launch { deleteNoteUseCase.execute(noteItem) }

    fun itemClick(noteItem: NoteItem) {
        itemEditingState.value = noteItem
        viewModelScope.launch {
            sendEvent(UIEvent.NAVIGATION(BottomNavItem.CreateNote.route, ""))
        }
    }
}
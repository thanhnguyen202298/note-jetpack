package net.thanhnguyen.z_note.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.core.validate
import net.thanhnguyen.z_note.ui.BottomNavItem
import net.thanhnguyen.z_note.ui.composable.GemButton
import net.thanhnguyen.z_note.ui.composable.PageHeader
import net.thanhnguyen.z_note.ui.composable.TextInputView
import net.thanhnguyen.z_note.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditScreen(
    navController: NavController){
    val noteVM: NoteViewModel = koinViewModel()
    val notestate = remember {
        noteVM.noteState
    }

    EditNote(navController = navController, notestate){
        note ->
        noteVM.destroyJob()
        if (note.id == null) {
            noteVM.insertNote(note)
        } else {
            noteVM.updateNote(note)
        }
        noteVM.noteState.value = NoteModel()
    }
}

@Composable
fun EditNote(
    navController: NavController,
    noteState: MutableState<NoteModel> = remember { mutableStateOf(NoteModel()) },
    onSave: (NoteModel)->Unit
) {
    var title by rememberSaveable { mutableStateOf(noteState.value.title) }
    var titleErrorMessage by rememberSaveable { mutableStateOf("") }
    var note by rememberSaveable { mutableStateOf(noteState.value.note) }
    var noteErrorMessage by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val onDone = {
        noteState.value.title = title
        noteState.value.note = note
    }

    SideEffect {
        titleErrorMessage = title.validate()
        noteErrorMessage = note.validate()
    }

    BaseScreen {
        PageHeader(navController = navController, title = "Edit Note", iconRight = Icons.Default.Edit)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    start = 10.dp,
                    top = 10.dp,
                    end = 10.dp,
                    bottom = BottomNavItem.bottomHeight.plus(10.dp)
                )
        ){
            TextInputView(
                label = "Title",
                value = title,
                { value ->
                    title = value
                    titleErrorMessage = value.validate()
                },
                errorMessage = titleErrorMessage,
                onDone = onDone,
                onFocus = { onDone() }
            )
            Spacer(modifier = Modifier.height(10.dp))


            TextInputView(
                label = "Notes",
                value = note,
                { value ->
                    note = value
                    noteErrorMessage = value.validate()
                },
                errorMessage = noteErrorMessage,
                lines = 40,
                minHeight = 300.dp,
                maxHeight = 536.dp,
                onDone = onDone,
                onFocus = { onDone() }
            )
            Spacer(modifier = Modifier.height(20.dp))

            GemButton(enable = "$noteErrorMessage$titleErrorMessage".isEmpty(),label = "Save Notes", modifier = Modifier.fillMaxWidth(0.5f), onClick = {
                noteErrorMessage = note.validate()
                titleErrorMessage = title.validate()
                if (noteErrorMessage.isNotEmpty() || titleErrorMessage.isNotEmpty()) return@GemButton
                onDone.invoke()
                onSave(noteState.value)
                title = ""
                note = ""
            })

        }
    }
}

@Preview
@Composable
fun preveiwEdit(){
    EditNote(navController = rememberNavController()){}
}
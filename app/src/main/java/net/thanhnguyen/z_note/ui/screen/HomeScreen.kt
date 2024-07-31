package net.thanhnguyen.z_note.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastMap
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.realm.kotlin.notifications.ResultsChange
import net.thanhnguyen.z_note.core.model.Note
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.ui.BottomNavItem
import net.thanhnguyen.z_note.ui.composable.NoteListUi
import net.thanhnguyen.z_note.ui.composable.PageHeader
import net.thanhnguyen.z_note.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val noteVM: NoteViewModel = koinViewModel()
    val list: State<ResultsChange<Note>?> = noteVM.flows.collectAsState(initial = null)
    val truncate = {(list.value?.list?.toList() ?: listOf()).fastMap { it.toNoteModel() }}
    val listNote : List<NoteModel> = truncate()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = listNote.size) {
        noteVM.countnumber.value = listNote.size
    }

   BaseScreen {
        PageHeader(navController = navController, title = "Notes", iconRight = Icons.Default.Home, isHaveBackButton = false)
        NoteListUi(listNote, {n-> noteVM.noteState.value = n
            navController.navigate(BottomNavItem.CreateNote.route)
        }, {note ->
            noteVM.deleteNote(note)
        })
    }
}

@Preview
@Composable
fun Home(){
    val listNote = ArrayList<NoteModel>()
    listNote.add(NoteModel(id = null,"new", "hua new tokyo love"))
    listNote.add(NoteModel(id=null,"new", "hua new tokyo love"))
    listNote.add(NoteModel(id = null, "new", "hua new tokyo love"))
    listNote.add(NoteModel(id = null, "new", "hua new tokyo love"))

   BaseScreen() {
       PageHeader(navController = rememberNavController(), title = "Notes", iconRight = Icons.Default.Home)
       NoteListUi(listNote,{}, {})
    }
}
package net.thanhnguyen.z_note.presenter.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastMap
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.realm.kotlin.notifications.ResultsChange
import net.thanhnguyen.z_note.data.model.Note
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.presenter.ui.BottomNavItem
import net.thanhnguyen.z_note.presenter.ui.composable.NoteListUi
import net.thanhnguyen.z_note.presenter.ui.composable.PageHeader
import net.thanhnguyen.z_note.presenter.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val noteVM: NoteViewModel = koinViewModel()
    val list: State<ResultsChange<Note>?> = noteVM.flows.collectAsState(initial = null)
    val truncate = {(list.value?.list?.toList() ?: listOf()).fastMap { it.toNoteModel() }}
    val listNote : List<NoteItem> = truncate()

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
    val listNote = ArrayList<NoteItem>()
    listNote.add(NoteItem(id = null,"new", "hua new tokyo love"))
    listNote.add(NoteItem(id = null,"new", "hua new tokyo love"))
    listNote.add(NoteItem(id = null, "new", "hua new tokyo love"))
    listNote.add(NoteItem(id = null, "new", "hua new tokyo love"))

   BaseScreen() {
       PageHeader(navController = rememberNavController(), title = "Notes", iconRight = Icons.Default.Home)
       NoteListUi(listNote,{}, {})
    }
}
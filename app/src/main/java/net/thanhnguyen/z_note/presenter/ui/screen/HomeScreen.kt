package net.thanhnguyen.z_note.presenter.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastMap
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavController
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.presenter.ui.composable.NoteListUi
import net.thanhnguyen.z_note.presenter.ui.composable.PageHeader
import net.thanhnguyen.z_note.presenter.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val noteVM: NoteViewModel = koinViewModel()
    val list: List<NoteItem> = noteVM.flows.collectAsState(initial = null).value.let {
        it?.list?.toList()?.fastMap { it.toNoteModel() } ?: listOf()
    }
    LifecycleStartEffect(key1 = true) {
        noteVM.resetEditingState()
        onStopOrDispose {  }
    }

    BaseScreen(noteVM, navController) {
        PageHeader(
            navController = navController,
            title = "Notes",
            iconRight = Icons.Default.Home,
            isHaveBackButton = false
        )
        NoteListUi(list, noteVM::itemClick, noteVM::deleteNote)
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

}
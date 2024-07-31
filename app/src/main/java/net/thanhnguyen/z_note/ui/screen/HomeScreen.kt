package net.thanhnguyen.z_note.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.ui.BottomNavItem
import net.thanhnguyen.z_note.ui.composable.NoteListUi
import net.thanhnguyen.z_note.ui.composable.PageHeader
import net.thanhnguyen.z_note.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val noteVM: NoteViewModel = koinViewModel()
    val list: State<List<NoteModel>> = remember{noteVM.flows}

   BaseScreen {
        PageHeader(navController = navController, title = "Notes", iconRight = Icons.Default.Home, isHaveBackButton = false)
        NoteListUi(list.value, {n-> noteVM.noteState.value = n
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
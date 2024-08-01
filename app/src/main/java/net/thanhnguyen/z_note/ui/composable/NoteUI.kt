package net.thanhnguyen.z_note.ui.composable

import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import net.thanhnguyen.z_note.core.getSpacer
import net.thanhnguyen.z_note.core.model.Note
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.ui.theme.LightColorScheme
import net.thanhnguyen.z_note.ui.theme.primary
import net.thanhnguyen.z_note.ui.theme.secondary

@Composable
fun NoteItemUi(note: NoteModel, onEdit:(NoteModel)->Unit, onDelete: (NoteModel) -> Unit) {

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (boxTitle, boxDesc, boxDate, gap, ebtn, dbtn) = createRefs()
        TitleText(
            note.title,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 3.dp)
                .constrainAs(boxTitle) {})
        Text(color = Color.Black,
            text = note.note.let { it.substring(0, it.getSpacer(14)) },
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .constrainAs(boxDesc) { top.linkTo(boxTitle.bottom, margin = 2.dp) })
        Text(color = Color.Black,
            text = note.formatDate(),
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .constrainAs(boxDate) { top.linkTo(boxDesc.bottom, margin = 2.dp) })
        Spacer(
            modifier = Modifier
                .background(LightColorScheme.primary)
                .padding(2.dp)
                .fillMaxWidth()
                .constrainAs(gap) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(boxDate.bottom, margin = 1.dp)
                })

        Button(onClick = { onEdit(note) }, modifier = Modifier.constrainAs(ebtn) {
            bottom.linkTo(parent.bottom)
            top.linkTo(parent.top)
            start.linkTo(dbtn.end, margin = 5.dp)
        }
            .padding(0.dp), colors = ButtonDefaults.buttonColors(
            containerColor = primary,
            contentColor = secondary,
            disabledContainerColor = LightColorScheme.primaryContainer,
            disabledContentColor = secondary
        ))
        { Icon(imageVector = Icons.Default.Edit, contentDescription = "", Modifier.size(20.dp).padding(0.dp)) }


        Button(onClick = { onDelete(note) }, modifier = Modifier.constrainAs(dbtn) {
            bottom.linkTo(parent.bottom)
            top.linkTo(parent.top)
            end.linkTo(parent.end, margin = 120.dp)
        }
            .padding(0.dp), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta,
            contentColor = secondary,
            disabledContainerColor = LightColorScheme.primaryContainer,
            disabledContentColor = secondary
        ))
        { Icon(imageVector = Icons.Default.Delete, contentDescription = "", Modifier.size(20.dp).padding(0.dp)) }
    }
}

@Composable
fun NoteListUi(list: List<NoteModel> = listOf(), onEdit: (NoteModel) -> Unit, onDelete: (NoteModel) -> Unit){

    val chatListState = rememberLazyListState()

    LazyColumn(modifier = Modifier
        .fillMaxWidth(),
        state = chatListState) {
        items(list) {
            NoteItemUi(it, onEdit, onDelete)
        }
    }
}
package net.thanhnguyen.z_note.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.thanhnguyen.z_note.R


@Composable
fun DateInputText(
    label: String,
    togglePicker: () -> Unit,
    dateValue: String,
    dateChange: (String) -> Unit,
    errorMessage: String = "",
    placeHolder: String = ""
) = Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start, modifier = Modifier
        .padding(0.dp)
        .fillMaxWidth()
) {
    TitleText(label, Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp))
    TextButton(
        onClick = togglePicker,
        Modifier
            .clip(RoundedCornerShape(0.dp))
            .padding(0.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent, Color.Black, Color.Transparent, Color.Transparent),
    ) {
        TextInputView("",
            value = dateValue,
            dateChange,
            readOnly = true, enable = false,
            trailingIconRes =  R.drawable.calendar,
            errorMessage = errorMessage,
            placeHolder = placeHolder
        )
    }
}
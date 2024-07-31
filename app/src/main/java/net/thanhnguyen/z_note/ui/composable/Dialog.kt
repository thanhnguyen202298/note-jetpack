package net.thanhnguyen.z_note.ui.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import net.thanhnguyen.z_note.ui.theme.primary
import net.thanhnguyen.z_note.ui.theme.secondary


@Composable
fun LoadingDialog(title: String?, content: String? , onConfirm: (() -> Unit)? = null, onDismiss: (() -> Unit)? = null) {

    Dialog(onDismissRequest = onDismiss?:{}){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(secondary, RoundedCornerShape(12.dp))
                .padding(24.dp)) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                modifier = Modifier.size(30.dp, 30.dp)
                    .padding(22.dp),
                tint = primary
            )
            if(!title.isNullOrEmpty()){
                TitleText(label = title)
            }
            if(!content.isNullOrEmpty()){
                Text(content, textAlign = TextAlign.Center)
            }
            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(32.dp, 2.dp)) {
                onDismiss?.let {
                    Button(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("Close")
                    }
                }
                onConfirm?.let {
                    Button(
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text("Ok")
                    }
                }
            }
        }
    }
}
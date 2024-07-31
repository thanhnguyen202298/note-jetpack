package net.thanhnguyen.z_note.ui.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.thanhnguyen.z_note.ui.theme.CustomRippleTheme
import net.thanhnguyen.z_note.ui.theme.LightColorScheme
import net.thanhnguyen.z_note.ui.theme.secondary


@Composable
fun GemButton(
    label: String = "",
    modifier: Modifier,
    background: Color = LightColorScheme.primary,
    iconRes: Int? = null,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    CompositionLocalProvider(
        LocalRippleTheme provides CustomRippleTheme(Color.White)
    ) {
        TextButton(
            onClick = onClick,
            enabled = enable,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enable) background else LightColorScheme.tertiary,
                contentColor = secondary,
                disabledContainerColor = LightColorScheme.primaryContainer,
                disabledContentColor = secondary
            ),
            modifier = modifier
                .height(44.dp),
            shape = RoundedCornerShape(40.dp)
        ) {
            iconRes?.let {
                Icon(
                    imageVector = ImageVector.vectorResource(id = iconRes),
                    contentDescription = ""
                )
            }?: Icon(imageVector = Icons.Default.Done, contentDescription = "")
            TitleText(label, fontWeight = FontWeight.W600, modifier = Modifier.padding(8.dp, 0.dp))
        }
    }
}
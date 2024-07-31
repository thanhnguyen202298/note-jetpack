package net.thanhnguyen.z_note.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.thanhnguyen.z_note.core.replaceFirsUpperCase

@Composable
fun TitleText(label: String, modifier: Modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp), fontWeight: FontWeight = FontWeight.W500, fontSize: TextUnit = 16.sp) = Text(
    text = label.replaceFirsUpperCase(),
    fontFamily = FontFamily.SansSerif,
    fontSize = fontSize,
    fontWeight = fontWeight,
    modifier = modifier, color = Color.Black,
)

@Composable
fun MapIcon(resId: Int) = Icon(imageVector = ImageVector.vectorResource(id = resId), contentDescription = "", tint = Color.Black)
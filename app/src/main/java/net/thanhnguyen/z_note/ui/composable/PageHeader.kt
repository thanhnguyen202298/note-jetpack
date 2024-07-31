package net.thanhnguyen.z_note.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.thanhnguyen.z_note.ui.theme.secondary

@Composable
fun PageHeader(navController: NavController?, title: String, iconRight: ImageVector? = null, isHaveBackButton:Boolean = true){
    Box(
        modifier = Modifier.background(secondary)
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp, start = 12.dp, end = 12.dp)
    ) {
        if(isHaveBackButton){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = title,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { navController?.popBackStack() }, tint = Color.Black,
            )
        }
        Text(color = Color.Black,
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.align(Alignment.Center)
        )
        Box(
            modifier = Modifier.align(Alignment.CenterEnd)
        ){
            iconRight?.let {icoR->
                Icon(tint = Color.Black,
                    imageVector = icoR,
                    contentDescription = title,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
        }
    }
}
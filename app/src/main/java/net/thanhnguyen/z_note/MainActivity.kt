package net.thanhnguyen.z_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.thanhnguyen.z_note.ui.BottomNavItem
import net.thanhnguyen.z_note.ui.BottomNavigationBar
import net.thanhnguyen.z_note.ui.screen.EditScreen
import net.thanhnguyen.z_note.ui.screen.HomeScreen
import net.thanhnguyen.z_note.ui.theme.LightColorScheme
import net.thanhnguyen.z_note.ui.theme.ZnoteTheme
import net.thanhnguyen.z_note.ui.theme.inversePrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZnoteTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                val destination = navController.currentBackStackEntryAsState()
                val getCurrentScreen = { destination.value?.destination?.route }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightColorScheme.background
                ) {

                    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) },
                        floatingActionButton = {
                            if (getCurrentScreen() == "Home")
                                FloatingActionButton(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(30.dp))
                                        .height(44.dp),
                                    containerColor = inversePrimary,
                                    contentColor = Color.White,
                                    onClick = { navController.navigate(BottomNavItem.CreateNote.route) },
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(10.dp, 0.dp),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = ""
                                        )
                                        Text(
                                            modifier = Modifier.padding(start = 5.dp),
                                            text = "Add Notes",
                                            fontFamily = FontFamily.Monospace,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.W700
                                        )
                                    }

                                }
                        }) { _ ->
                        NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
                            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
                            composable(BottomNavItem.CreateNote.route) {EditScreen(navController) }

                        }
                    }
                }
            }
        }
    }
}

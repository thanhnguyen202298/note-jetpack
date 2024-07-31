package net.thanhnguyen.z_note.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.thanhnguyen.z_note.ui.theme.LightColorScheme
import net.thanhnguyen.z_note.ui.theme.secondary


sealed class BottomNavItem(val route: String, var icon: ImageVector, val label: String) {
    data object Home : BottomNavItem("Home", Icons.Default.Home, "Home")
    data object CreateNote : BottomNavItem("CreateNote", Icons.Default.Edit, "CreateNote")

    companion object {
        fun toArrays() = arrayOf(Home, CreateNote)
        var bottomHeight = 20.dp
        fun setBottomHeightDp(heigh: Dp){
            bottomHeight = heigh}
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val localDensity = LocalDensity.current
    NavigationBar(Modifier.onGloballyPositioned {
        BottomNavItem.setBottomHeightDp(with(localDensity) { it.size.height.toDp() })
    }, containerColor = LightColorScheme.primary) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.toArrays().forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LightColorScheme.tertiary,
                    unselectedIconColor = LightColorScheme.primaryContainer,
                    selectedTextColor = secondary,
                    unselectedTextColor = LightColorScheme.primaryContainer,
                    indicatorColor = LightColorScheme.onTertiary,
                    disabledIconColor = LightColorScheme.onTertiary,
                    disabledTextColor = LightColorScheme.onTertiary
                )
            )
        }
    }
}

@Composable
fun mapIcon(bottomNavItem: BottomNavItem) = when(bottomNavItem.route){
    else -> Icons.Default.Face
}
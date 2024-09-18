package net.thanhnguyen.z_note.presenter.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import net.thanhnguyen.z_note.domain.events.UIEvent
import net.thanhnguyen.z_note.presenter.ui.theme.LightColorScheme
import net.thanhnguyen.z_note.presenter.viewmodel.BaseViewModel

@Composable
fun BaseScreen(baseViewModel: BaseViewModel,navController: NavController? = null,
    children: @Composable () -> Unit
) {

    val coroutine = rememberCoroutineScope()

    LifecycleStartEffect(key1 = true){
        coroutine.launch{
            baseViewModel.eventState.collect{
                when(it){
                    is UIEvent.SHOW_MSG -> {

                    }

                    is UIEvent.NAVIGATION -> {
                        val (route, _) = it
                        navController?.navigate(route)
                    }
                    else -> {}
                }
            }
        }
        onStopOrDispose {}
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(LightColorScheme.secondary)
    ) {

        children()
    }
}
package net.thanhnguyen.z_note.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import net.thanhnguyen.z_note.domain.events.UIEvent

open class BaseViewModel: ViewModel() {
    private val _eventState = MutableSharedFlow<UIEvent>()
    val eventState = _eventState.asSharedFlow()
    suspend fun sendEvent(event: UIEvent) {
        _eventState.emit(event)
    }
    protected fun getEventState() = _eventState
}
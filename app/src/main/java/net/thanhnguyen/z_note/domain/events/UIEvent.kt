package net.thanhnguyen.z_note.domain.events

sealed class UIEvent {
    data class SHOW_MSG(val msg: String): UIEvent()
    data class SHOW_LOADING(val msg: String): UIEvent()
}
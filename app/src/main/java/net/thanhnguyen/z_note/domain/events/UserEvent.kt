package net.thanhnguyen.z_note.domain.events

sealed class UserEvent {
    data class INPUT(val value: String): UserEvent()
    data class CLICK(val id: Int): UserEvent()
    data class SWIPE(val id: Int, val direction: Direction): UserEvent()
}

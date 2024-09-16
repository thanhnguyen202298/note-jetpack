package net.thanhnguyen.z_note.domain.events

sealed class ScreenState {
    data class DATA_STATE(val data: String, val visibleState: Boolean): ScreenState()
    object IDLE: ScreenState()
    object LOADING: ScreenState()
}


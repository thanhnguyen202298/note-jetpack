package net.thanhnguyen.z_note.domain.events;

sealed class Direction  {
    object UP:Direction()
    object DOWN:Direction()
    object LEFT:Direction()
    object RIGHT:Direction()
}
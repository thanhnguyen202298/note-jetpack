package net.thanhnguyen.z_note.ui.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

class CustomRippleTheme(private val color: Color) : RippleTheme {
    @Composable
    @ReadOnlyComposable
    override fun defaultColor() = color

    @Composable
    @ReadOnlyComposable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.5f,
        focusedAlpha = 0.5f,
        hoveredAlpha = 0.5f,
        pressedAlpha = 0.5f,
    )
}
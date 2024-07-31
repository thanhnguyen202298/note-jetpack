package net.thanhnguyen.z_note.ui.composable

import android.graphics.ColorFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.thanhnguyen.z_note.R
import net.thanhnguyen.z_note.core.replaceFirsUpperCase
import net.thanhnguyen.z_note.ui.theme.CustomRippleTheme
import net.thanhnguyen.z_note.ui.theme.LightColorScheme
import net.thanhnguyen.z_note.ui.theme.secondary


@Composable
fun TextInputView(
    label: String = "",
    value: String,
    valChange: (String) -> Unit,
    onDone: () -> Unit = {},
    lines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    isEndField: Boolean = false,
    readOnly: Boolean = false,
    enable: Boolean = true,
    placeHolder: String ="",
    trailingIconRes: Int? = null,
    modifier: Modifier = Modifier,
    minHeight: Dp = 56.dp,
    maxHeight: Dp = 56.dp,
    trailingIcon: @Composable (()->Unit)? = null,
    onFocus: (Boolean) -> Unit = {},
    errorMessage: String= ""
) =
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = (if (label.isNullOrEmpty()) modifier.padding(
            0.dp,
            0.dp,
            0.dp,
            10.dp
        ) else modifier.padding(10.dp)).fillMaxWidth()
    ) {
        val keyboard = LocalSoftwareKeyboardController.current
        if (label.isNotEmpty()) TitleText(label)
        OutlinedTextField(
            value = value,
            onValueChange = valChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = if (isEndField) ImeAction.Done else ImeAction.Next,
                keyboardType = keyboardType,
            ),
            keyboardActions = KeyboardActions(onDone = {
                onDone.invoke();
                keyboard?.hide()
            }),
            maxLines = lines,
            enabled = enable,
            readOnly = readOnly,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(minHeight, maxHeight)
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, color = if (errorMessage.isNotEmpty()) Color.Magenta else Color.Black, shape = RoundedCornerShape(12.dp))
                .onFocusChanged {
                    onFocus.invoke(it.isFocused)
                },
            placeholder = { Text(text = placeHolder) },
            trailingIcon = {
                trailingIcon ?: trailingIconRes?.let { MapIcon(resId = trailingIconRes) }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = secondary,
                focusedContainerColor = secondary,
                disabledContainerColor = secondary,
                disabledTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Magenta,
                fontSize = 12.sp,
            )
        }
    }


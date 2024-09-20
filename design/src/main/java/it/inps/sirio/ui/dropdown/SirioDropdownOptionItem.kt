//
// SirioDropdownOptionItem.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdown

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownBorderWidth
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon

@Composable
fun SirioDropdownOptionItem(
    text: String,
    selected: Boolean,
    enabled: Boolean = true,
    onCLick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor = SirioTheme.colors.dropdown.option.background.get(
        disabled = !enabled,
        focused = isFocused,
        pressed = isPressed,
        hovered = isHovered,
        valued = selected
    )

    val contentColor = SirioTheme.colors.dropdown.option.content.get(
        disabled = !enabled,
        focused = isFocused,
        pressed = isPressed,
        hovered = isHovered,
        valued = selected
    )

    val borderColor = SirioTheme.colors.dropdown.option.border.get(
        disabled = !enabled,
        focused = isFocused,
        pressed = isPressed,
        hovered = isHovered,
        valued = selected
    )

    DropdownMenuItem(
        text = {
            SirioText(
                text = text,
                color = contentColor,
                typography = SirioTheme.typography.dropdown.option.text,
            )
        },
        onClick = onCLick,
        modifier = Modifier
            .background(backgroundColor)
            .border(width = dropdownBorderWidth.dp, color = borderColor),
        trailingIcon = if (selected) {
            {
                SirioIcon(faIcon = FaIcons.Check, iconColor = contentColor)
            }
        } else null,
        enabled = enabled,
        interactionSource = interactionSource,
    )
}

@Keep
data class SirioDropdownOptionColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val content: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownOptionColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
        )
    }
}

@Keep
data class SirioDropdownOptionTypography(
    val text: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioDropdownOptionTypography(
            text = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioDropdownOptionItemPreview() {
    SirioTheme {
        Column {
            val text = "Option Value"
            SirioDropdownOptionItem(text = text, selected = false, enabled = true, onCLick = {})
            SirioDropdownOptionItem(text = text, selected = true, enabled = true, onCLick = {})
            SirioDropdownOptionItem(text = text, selected = false, enabled = false, onCLick = {})
            SirioDropdownOptionItem(text = text, selected = true, enabled = false, onCLick = {})
        }
    }
}
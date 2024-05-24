//
// SirioRadioButtonCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.radiobutton

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.radioBorderWidth
import it.inps.sirio.theme.radioDotSize
import it.inps.sirio.theme.radioFocusBorderPadding
import it.inps.sirio.theme.radioFocusExtraBorderWidth
import it.inps.sirio.theme.radioSafeAreaPadding
import it.inps.sirio.theme.radioSize
import it.inps.sirio.ui.text.SirioTextCommon

/**
 * Radio button implementation
 *
 * @param text The optional string on the right of the radio
 * @param isSelected Whether the radio button is selected
 * @param enabled Whether the radio button is clickable
 * @param onClick The callback invoked when the component is tapped
 */
@Composable
internal fun SirioRadioButtonCommon(
    text: String? = null,
    isSelected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor =
        SirioTheme.colors.radio.background.get(enabled.not(), isFocused, isPressed, isHovered)
    val borderColor =
        SirioTheme.colors.radio.border.get(enabled.not(), isFocused, isPressed, isHovered)
    val textColor =
        SirioTheme.colors.radio.text.get(enabled.not(), isFocused, isPressed, isHovered)
    val dotColor =
        SirioTheme.colors.radio.dot.get(enabled.not(), isFocused, isPressed, isHovered)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .focusable(enabled = true, interactionSource = interactionSource)
            .selectable(
                selected = isSelected,
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.RadioButton,
            ) {
                onClick()
            }
    ) {
        CustomRadioButton(
            selected = isSelected,
            isFocused = isFocused,
            backgroundColor = backgroundColor,
            dotColor = dotColor,
            borderColor = borderColor
        )
        text?.let {
            SirioTextCommon(
                text = it,
                color = textColor,
                typography = SirioTheme.typography.radio.text,
            )
        }
    }
}

/**
 * A custom implementation of the radio button respecting Sirio colors and sizes
 *
 * @param selected Whether the radio button is checked
 * @param isFocused Whether the radio button has focus
 * @param dotColor The color of the inner dot
 * @param borderColor The color of the button border
 */
@Composable
private fun CustomRadioButton(
    selected: Boolean,
    isFocused: Boolean,
    backgroundColor: Color,
    dotColor: Color,
    borderColor: Color,
) {
    val focusPadding = remember {
        radioFocusBorderPadding + radioFocusExtraBorderWidth
    }

    val size = Modifier
        .padding(radioSafeAreaPadding)
        .size(radioSize)
    Box(
        modifier = if (isFocused) {
            size
                .border(
                    radioFocusExtraBorderWidth,
                    color = SirioTheme.colors.radio.borderFocusExtra,
                    shape = CircleShape,
                )
                .padding(focusPadding)
        } else size,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .border(width = radioBorderWidth, color = borderColor, shape = CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(radioDotSize)
                        .background(dotColor, CircleShape)
                )
            }
        }
    }
}

@Keep
data class SirioRadioButtonColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val borderFocusExtra: Color,
    val dot: SirioColorState,
    val text: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioRadioButtonColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            borderFocusExtra = Color.Unspecified,
            dot = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
        )
    }
}

@Keep
data class SirioRadioButtonTypography(
    val text: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioRadioButtonTypography(
            text = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun RadioCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            val text = "Title"
            SirioRadioButtonCommon(isSelected = false, onClick = {}, enabled = true)
            SirioRadioButtonCommon(isSelected = true, onClick = {}, enabled = true)
            SirioRadioButtonCommon(isSelected = false, onClick = {}, enabled = false)
            SirioRadioButtonCommon(isSelected = true, onClick = {}, enabled = false)
            SirioRadioButtonCommon(text = text, isSelected = false, onClick = {}, enabled = true)
            SirioRadioButtonCommon(text = text, isSelected = true, onClick = {}, enabled = true)
            SirioRadioButtonCommon(text = text, isSelected = false, onClick = {}, enabled = false)
            SirioRadioButtonCommon(text = text, isSelected = true, onClick = {}, enabled = false)
        }
    }
}

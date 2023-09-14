//
// SirioRadioButtonCommon.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.radiobutton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.*
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

    val borderColor = if (!enabled) SirioTheme.colors.radioDisabledBorder else when {
        isFocused -> SirioTheme.colors.radioFocus
        isPressed -> SirioTheme.colors.radioPressed
        isHovered -> SirioTheme.colors.radioHover
        else -> SirioTheme.colors.radioDefault
    }
    val dotColor =
        if (enabled) SirioTheme.colors.radioPressed else SirioTheme.colors.radioDisabled
    val textColor =
        if (!enabled) SirioTheme.colors.radioDisabled else borderColor

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
            dotColor = dotColor,
            borderColor = borderColor
        )
        text?.let {
            SirioTextCommon(
                text = it,
                color = textColor,
                typography = SirioTheme.typography.radioLabelText,
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
                    color = SirioTheme.colors.radioFocusBorder,
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
                .background(SirioTheme.colors.radioBackground),
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


@Preview
@Composable
private fun RadioCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
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

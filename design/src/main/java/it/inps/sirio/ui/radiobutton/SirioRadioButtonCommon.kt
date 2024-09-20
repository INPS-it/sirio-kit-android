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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.minimumInteractiveComponentSize
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
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.radioBorderWidth
import it.inps.sirio.theme.radioDotSize
import it.inps.sirio.theme.radioFocusBorderPadding
import it.inps.sirio.theme.radioFocusExtraBorderWidth
import it.inps.sirio.theme.radioPaddingText
import it.inps.sirio.theme.radioSize
import it.inps.sirio.ui.text.SirioTextCommon

/**
 * Radio button implementation
 *
 * @param selected Whether the radio button is selected
 * @param modifier the Modifier to be applied to this radio button
 * @param text The optional string on the right of the radio
 * @param enabled Whether the radio button is clickable
 * @param onClick The callback invoked when the component is tapped
 */
@Composable
internal fun SirioRadioButtonCommon(
    selected: Boolean,
    modifier: Modifier = Modifier,
    text: String? = null,
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
        modifier = modifier
            .focusable(enabled = enabled, interactionSource = interactionSource)
            .selectable(
                selected = selected,
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.RadioButton,
            ) {
                onClick()
            }
    ) {
        CustomRadioButton(
            selected = selected,
            isFocused = isFocused,
            backgroundColor = backgroundColor,
            dotColor = dotColor,
            borderColor = borderColor
        )
        text?.let {
            Spacer(modifier = Modifier.width(radioPaddingText.dp))
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
        .size(radioSize.dp)
        .minimumInteractiveComponentSize()
    Box(
        modifier = if (isFocused) {
            size
                .border(
                    radioFocusExtraBorderWidth.dp,
                    color = SirioTheme.colors.radio.borderFocusExtra,
                    shape = CircleShape,
                )
                .padding(focusPadding.dp)
        } else size,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .border(width = radioBorderWidth.dp, color = borderColor, shape = CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(radioDotSize.dp)
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
            SirioRadioButtonCommon(selected = false, enabled = true, onClick = {})
            SirioRadioButtonCommon(selected = true, enabled = true, onClick = {})
            SirioRadioButtonCommon(selected = false, enabled = false, onClick = {})
            SirioRadioButtonCommon(selected = true, enabled = false, onClick = {})
            SirioRadioButtonCommon(selected = false, text = text, enabled = true, onClick = {})
            SirioRadioButtonCommon(selected = true, text = text, enabled = true, onClick = {})
            SirioRadioButtonCommon(selected = false, text = text, enabled = false, onClick = {})
            SirioRadioButtonCommon(selected = true, text = text, enabled = false, onClick = {})
        }
    }
}

//
// SirioToggleCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.toggle

import androidx.compose.animation.core.Animatable as AnimatableF
import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.*

/**
 * Sirio toggle implementation
 *
 * @param text The string on the toggle right
 * @param isOn Whether the toggle is selected
 * @param enabled Whether the toggle is enabled
 * @param onToggleChange The callback when the toggle state change
 */
@Composable
internal fun SirioToggleCommon(
    text: String? = null,
    isOn: Boolean,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(verticalAlignment = Alignment.CenterVertically) {
        val borderColor = when {
            !enabled -> SirioTheme.colors.toggleDisabledBorder
            isFocused -> if (isOn) SirioTheme.colors.toggleFocusOn else SirioTheme.colors.toggleFocusOff
            isHovered -> if (isOn) SirioTheme.colors.toggleHoverOn else SirioTheme.colors.toggleHoverOff
            else -> if (isOn) SirioTheme.colors.toggleDefaultOn else SirioTheme.colors.toggleDefaultOff
        }
        val contentColor = if (enabled)borderColor else SirioTheme.colors.toggleDisabled
        val offset: Float = with(LocalDensity.current) {
            toggleIndicatorHorizontalOffset.dp.toPx()
        }
        val modifier = Modifier
            .padding(toggleSafeAreaPadding)
            .height(height = toggleHeight)
            .width(width = toggleWidth)
        Box(
            modifier = if (isFocused) {
                modifier
                    .border(
                        toggleFocusExtraBorderWidth,
                        color = SirioTheme.colors.toggleFocusExtraBorder,
                        shape = CircleShape,
                    )
                    .padding(toggleFocusExtraBorderPadding)
            } else modifier,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .toggleable(
                        value = isOn,
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = enabled,
                        role = Role.Switch,
                        onValueChange = onToggleChange,
                    ),
                shape = CircleShape,
                color = SirioTheme.colors.toggleBackground,
                border = BorderStroke(width = toggleBorderWidth, color = borderColor)
            ) {
                val currentX = if (isOn) offset else -offset
                val color = remember { Animatable(contentColor) }
                val xPos = remember { AnimatableF(currentX) }
                LaunchedEffect(isOn) {
                    color.animateTo(contentColor)
                    xPos.animateTo(currentX)
                }
                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .offset { IntOffset(xPos.value.toInt(), 0) }
                        .requiredSize(toggleIndicatorSize)
                        .clip(CircleShape)
                        .background(color.value)
                )
            }
        }
        text?.let {
            Text(
                text = it,
                color = contentColor,
                style = SirioTheme.typography.toggleLabelText,
            )
        }
    }
}

@Preview
@Composable
private fun ToggleCommonPreview() {
    SirioTheme {
        Column {
            SirioToggleCommon(text = "Title", isOn = false, onToggleChange = {})
            SirioToggleCommon(text = "Title", isOn = true, onToggleChange = {})
        }
    }
}

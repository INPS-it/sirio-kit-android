//
// SirioToggleCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.toggle

import androidx.annotation.Keep
import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.toggleBorderWidth
import it.inps.sirio.theme.toggleFocusExtraBorderPadding
import it.inps.sirio.theme.toggleFocusExtraBorderWidth
import it.inps.sirio.theme.toggleHeight
import it.inps.sirio.theme.toggleIndicatorHorizontalOffset
import it.inps.sirio.theme.toggleIndicatorSize
import it.inps.sirio.theme.togglePaddingText
import it.inps.sirio.theme.toggleWidth
import it.inps.sirio.ui.text.SirioTextCommon
import androidx.compose.animation.core.Animatable as AnimatableF

/**
 * Sirio toggle implementation
 *
 * @param text The string on the toggle right
 * @param modifier the Modifier to be applied to this toggle
 * @param isOn Whether the toggle is selected
 * @param enabled Whether the toggle is enabled
 * @param onToggleChange The callback when the toggle state change
 */
@Composable
internal fun SirioToggleCommon(
    isOn: Boolean,
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        val backgroundColor =
            SirioTheme.colors.toggle.background.get(enabled.not(), isFocused, isPressed, isHovered)
        val onColor =
            SirioTheme.colors.toggle.on.get(enabled.not(), isFocused, isPressed, isHovered)
        val offColor =
            SirioTheme.colors.toggle.off.get(enabled.not(), isFocused, isPressed, isHovered)
        val stateColor by remember(isOn, onColor, offColor) {
            derivedStateOf { if (isOn) onColor else offColor }
        }
        val borderColor by remember(enabled, stateColor, backgroundColor) {
            derivedStateOf { if (enabled) stateColor else backgroundColor }
        }
        val offset: Float = with(LocalDensity.current) {
            toggleIndicatorHorizontalOffset.dp.toPx()
        }
        val sizeModifier = Modifier
            .height(height = toggleHeight.dp)
            .width(width = toggleWidth.dp)
            .minimumInteractiveComponentSize()
        Box(
            modifier = if (isFocused) {
                sizeModifier
                    .border(
                        toggleFocusExtraBorderWidth.dp,
                        color = SirioTheme.colors.toggle.borderFocusExtra,
                        shape = CircleShape,
                    )
                    .padding(toggleFocusExtraBorderPadding.dp)
            } else sizeModifier,
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
                color = backgroundColor,
                border = BorderStroke(width = toggleBorderWidth.dp, color = borderColor)
            ) {
                val currentX = if (isOn) offset else -offset
                val animatedColor = remember { Animatable(stateColor) }
                val xPos = remember { AnimatableF(currentX) }
                LaunchedEffect(isOn) {
                    animatedColor.animateTo(stateColor)
                    xPos.animateTo(currentX)
                }
                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .offset { IntOffset(xPos.value.toInt(), 0) }
                        .requiredSize(toggleIndicatorSize.dp)
                        .clip(CircleShape)
                        .background(animatedColor.value)
                )
            }
        }
        text?.let {
            Spacer(modifier = Modifier.width(togglePaddingText.dp))
            SirioTextCommon(
                text = it,
                color = stateColor,
                typography = SirioTheme.typography.toggle.text,
            )
        }
    }
}

@Keep
data class SirioToggleColors(
    val background: SirioColorState,
    val borderFocusExtra: Color,
    val off: SirioColorState,
    val on: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioToggleColors(
            background = SirioColorState.Unspecified,
            borderFocusExtra = Color.Unspecified,
            on = SirioColorState.Unspecified,
            off = SirioColorState.Unspecified,
        )
    }
}

@Keep
data class SirioToggleTypography(
    val text: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioToggleTypography(
            text = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun ToggleCommonPreview() {
    SirioTheme {
        Column {
            SirioToggleCommon(
                isOn = false,
                modifier = Modifier.fillMaxWidth(),
                text = "Title",
                onToggleChange = {})
            SirioToggleCommon(isOn = true, text = "Title", onToggleChange = {})
            SirioToggleCommon(isOn = false, text = "Title", enabled = false, onToggleChange = {})
            SirioToggleCommon(isOn = true, text = "Title", enabled = false, onToggleChange = {})
        }
    }
}

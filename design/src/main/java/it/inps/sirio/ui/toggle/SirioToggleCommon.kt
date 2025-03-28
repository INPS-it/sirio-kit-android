//
// SirioToggleCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.toggle

import androidx.annotation.Keep
import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.toggleBorderWidth
import it.inps.sirio.theme.toggleHeight
import it.inps.sirio.theme.toggleIndicatorHorizontalPadding
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
 * @param checked Whether the toggle is checked
 * @param enabled Whether the toggle is enabled
 * @param onToggleChange The callback when the toggle state change
 */
@Composable
internal fun SirioToggleCommon(
    checked: Boolean,
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        val containerColor = SirioTheme.colors.toggle.container.get(disabled = enabled.not())
        val dorOnColor = SirioTheme.colors.toggle.dotOn.get(disabled = enabled.not())
        val dotOffColor = SirioTheme.colors.toggle.dotOff.get(disabled = enabled.not())
        val borderOnColor = SirioTheme.colors.toggle.borderOn.get(disabled = enabled.not())
        val borderOffColor = SirioTheme.colors.toggle.borderOff.get(disabled = enabled.not())
        val stateColor by remember(checked, dorOnColor, dotOffColor) {
            derivedStateOf { if (checked) dorOnColor else dotOffColor }
        }
        val borderColor by remember(enabled, checked, containerColor) {
            derivedStateOf {
                when {
                    checked -> borderOnColor
                    !checked -> borderOffColor
                    !enabled -> containerColor
                    else -> containerColor
                }
            }
        }
        val offset: Float = with(LocalDensity.current) {
            (toggleWidth / 2 - toggleIndicatorHorizontalPadding - toggleIndicatorSize / 2).dp.toPx()
        }
        Surface(
            checked = checked,
            onCheckedChange = onToggleChange,
            modifier = Modifier
                .height(height = toggleHeight.dp)
                .width(width = toggleWidth.dp),
            shape = CircleShape,
            color = containerColor,
            border = BorderStroke(width = toggleBorderWidth.dp, color = borderColor)
        ) {
            val currentX = if (checked) offset else -offset
            val animatedColor = remember { Animatable(stateColor) }
            val xPos = remember { AnimatableF(currentX) }
            LaunchedEffect(checked) {
                animatedColor.animateTo(stateColor)
                xPos.animateTo(currentX)
            }
            Box(
                Modifier
                    .offset { IntOffset(xPos.value.toInt(), 0) }
                    .requiredSize(toggleIndicatorSize.dp)
                    .clip(CircleShape)
                    .background(animatedColor.value)
            )
        }
        text?.let {
            Spacer(modifier = Modifier.width(togglePaddingText.dp))
            SirioTextCommon(
                text = it,
                color = stateColor,
                typography = SirioTheme.foundationTypography.labelMdRegular,
            )
        }
    }
}

@Keep
data class SirioToggleColors(
    val container: SirioColorState,
    val borderOn: SirioColorState,
    val borderOff: SirioColorState,
    val dotOff: SirioColorState,
    val dotOn: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioToggleColors(
            container = SirioColorState.Unspecified,
            borderOn = SirioColorState.Unspecified,
            borderOff = SirioColorState.Unspecified,
            dotOn = SirioColorState.Unspecified,
            dotOff = SirioColorState.Unspecified,
        )
    }
}

internal val toggleLightColors = SirioToggleColors(
    container = SirioColorState.all(
        color = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    borderOn = SirioColorState.all(
        color = FoundationColor.colorAliasInteractivePrimaryDefault,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    borderOff = SirioColorState.all(
        color = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    dotOn = SirioColorState.all(
        color = FoundationColor.colorAliasInteractivePrimaryDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    dotOff = SirioColorState.all(
        color = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    )
)

internal val toggleDarkColors = toggleLightColors

@Preview
@Composable
private fun ToggleCommonPreview() {
    SirioTheme {
        Column {
            SirioToggleCommon(
                checked = false,
                modifier = Modifier.fillMaxWidth(),
                text = "Title",
                onToggleChange = {})
            SirioToggleCommon(checked = true, text = "Title", onToggleChange = {})
            SirioToggleCommon(checked = false, text = "Title", enabled = false, onToggleChange = {})
            SirioToggleCommon(checked = true, text = "Title", enabled = false, onToggleChange = {})
        }
    }
}

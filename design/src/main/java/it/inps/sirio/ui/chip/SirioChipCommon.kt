//
// SirioChipCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.chipActiveBorderWidth
import it.inps.sirio.theme.chipBigHeight
import it.inps.sirio.theme.chipButtonIconSize
import it.inps.sirio.theme.chipButtonSize
import it.inps.sirio.theme.chipCloseEndPadding
import it.inps.sirio.theme.chipDefaultHorizontalPadding
import it.inps.sirio.theme.chipDefaultVerticalPadding
import it.inps.sirio.theme.chipDisactiveBorderWidth
import it.inps.sirio.theme.chipFocusBorderPadding
import it.inps.sirio.theme.chipFocusBorderWidth
import it.inps.sirio.theme.chipIconEndPadding
import it.inps.sirio.theme.chipIconSize
import it.inps.sirio.theme.chipIconStartPadding
import it.inps.sirio.theme.chipWithCloseButtonBorderWidth
import it.inps.sirio.theme.chipWithCloseButtonContentPadding
import it.inps.sirio.theme.chipWithCloseButtonStartPadding
import it.inps.sirio.theme.chipWithCloseIconEndPadding
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * The Sirio chip implementation
 *
 * @param text The string on the chip
 * @param icon The icon at the start of the chip
 * @param withClose Whether the chip has the close button on the right
 * @param isActive Whether the chip is active
 * @param enabled Whether the chip is enabled
 * @param closeContentDescription The content description of the close button
 * @param onClose The callback when the chip close button is clicked
 * @param onStateChange The callback when the chip active state change
 */
@Composable
internal fun SirioChipCommon(
    text: String,
    icon: FaIconType?,
    withClose: Boolean,
    isActive: Boolean,
    enabled: Boolean,
    closeContentDescription: String? = null,
    onClose: () -> Unit = {},
    onStateChange: (active: Boolean) -> Unit,
) {
    val withIcon = icon != null
    val active by remember(withClose, isActive) { mutableStateOf(withClose || isActive) }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val (backgroundColor, contentColor, borderColor, buttonBorderColor, buttonBackgroundColor) = getChipParams(
        enabled = enabled,
        isFocused = isFocused,
        isPressed = isPressed,
        isHovered = isHovered,
        active = active,
        withClose = withClose,
    )

    val (startPadding: Dp, endPadding: Dp, iconEndPadding: Dp) = getChipPadding(withIcon, withClose)

    Box(
        modifier = if (isFocused) {
            Modifier
                .border(
                    chipFocusBorderWidth,
                    color = SirioTheme.colors.chipFocusBorder,
                    shape = CircleShape,
                )
                .padding(chipFocusBorderPadding)
        } else Modifier.padding(0.dp),
    ) {
        Surface(
            onClick = {
                onStateChange(!active)
            },
            modifier = Modifier
                .height(chipBigHeight)
                .width(IntrinsicSize.Max)
                .focusable(enabled = true, interactionSource = interactionSource),
            enabled = enabled,
            shape = CircleShape,
            border = BorderStroke(
                width = if (active) chipActiveBorderWidth else chipDisactiveBorderWidth,
                color = borderColor
            ),
            color = backgroundColor,
            interactionSource = interactionSource,
        ) {
            Row(
                Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .padding(
                        start = startPadding,
                        end = endPadding,
                        top = chipDefaultVerticalPadding,
                        bottom = chipDefaultVerticalPadding
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                icon?.let {
                    SirioIcon(faIcon = it, size = chipIconSize, iconColor = contentColor)
                    Spacer(modifier = Modifier.width(iconEndPadding))
                }
                SirioTextCommon(
                    text = text,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f, false)
                        .offset(y = (-2).dp),
                    color = contentColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.chipText,
                )
                if (withClose) {
                    Spacer(modifier = Modifier.width(chipWithCloseButtonStartPadding))
                    OutlinedButton(
                        onClick = onClose,
                        modifier = Modifier.size(chipButtonSize),
                        shape = CircleShape,
                        border = BorderStroke(chipWithCloseButtonBorderWidth, buttonBorderColor),
                        contentPadding = PaddingValues(chipWithCloseButtonContentPadding),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = buttonBackgroundColor)
                    ) {
                        SirioIcon(
                            faIcon = FaIcons.Times,
                            size = chipButtonIconSize,
                            iconColor = contentColor,
                            contentDescription = closeContentDescription,
                        )
                    }
                }
            }
        }
    }
}

/**
 * Internal chip padding depend on icon and close presence
 *
 * @param withIcon Whether the chip has icon
 * @param withClose Whether the chip has close button
 */
@Composable
private fun getChipPadding(
    withIcon: Boolean,
    withClose: Boolean,
): Triple<Dp, Dp, Dp> {
    val endPadding: Dp
    val iconEndPadding: Dp
    val startPadding: Dp = if (withIcon) chipIconStartPadding else chipDefaultHorizontalPadding
    if (withClose) {
        endPadding = chipCloseEndPadding
        iconEndPadding = chipWithCloseIconEndPadding
    } else {
        endPadding = chipDefaultHorizontalPadding
        iconEndPadding = chipIconEndPadding
    }
    return Triple(startPadding, endPadding, iconEndPadding)
}

/**
 * Chip color based on state and close button presence
 *
 * @param enabled Whether the chip is enabled
 * @param isFocused Whether the chip is focused
 * @param isPressed Whether the chip is pressed
 * @param isHovered Whether the chip is hovered
 * @param active Whether the chip is in active state
 * @param withClose Whether the chip has the close button
 */
@Composable
private fun getChipParams(
    enabled: Boolean,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    active: Boolean,
    withClose: Boolean,
) = if (!enabled) {
    ChipParams(
        backgroundColor = SirioTheme.colors.chipDisabledBackground,
        contentColor = SirioTheme.colors.chipDisabledContent,
        buttonBorderColor = SirioTheme.colors.chipDisabledButtonBackground,
        buttonBackgroundColor = SirioTheme.colors.chipDisabledButtonBackground
    )
} else if (withClose) {
    when {
        isFocused -> ChipParams(
            backgroundColor = SirioTheme.colors.chipWithCloseFocusBackground,
            contentColor = SirioTheme.colors.chipContent,
            buttonBorderColor = SirioTheme.colors.chipButtonBorder,
            buttonBackgroundColor = SirioTheme.colors.chipFocusButtonBackground,
        )

        isPressed -> ChipParams(
            backgroundColor = SirioTheme.colors.chipWithClosePressedBackground,
            contentColor = SirioTheme.colors.chipContent,
            buttonBorderColor = SirioTheme.colors.chipButtonBorder,
            buttonBackgroundColor = SirioTheme.colors.chipPressedButtonBackground,
        )

        isHovered -> ChipParams(
            backgroundColor = SirioTheme.colors.chipWithCloseHoverBackground,
            contentColor = SirioTheme.colors.chipContent,
            buttonBorderColor = SirioTheme.colors.chipButtonBorder,
            buttonBackgroundColor = SirioTheme.colors.chipHoverButtonBackground,
        )

        else -> ChipParams(
            backgroundColor = SirioTheme.colors.chipWithCloseDefaultBackground,
            contentColor = SirioTheme.colors.chipContent,
            buttonBorderColor = SirioTheme.colors.chipButtonBorder,
            buttonBackgroundColor = SirioTheme.colors.chipDefaultButtonBackground,
        )
    }
} else if (active) {
    when {
        isFocused -> ChipParams(
            backgroundColor = SirioTheme.colors.chipFocusBackground,
            contentColor = SirioTheme.colors.chipContent,
        )

        isPressed -> ChipParams(
            backgroundColor = SirioTheme.colors.chipPressedBackground,
            contentColor = SirioTheme.colors.chipContent,
        )

        isHovered -> ChipParams(
            backgroundColor = SirioTheme.colors.chipHoverBackground,
            contentColor = SirioTheme.colors.chipContent,
        )

        else -> ChipParams(
            backgroundColor = SirioTheme.colors.chipDefaultBackground,
            contentColor = SirioTheme.colors.chipContent,
        )
    }
} else {
    when {
        isFocused -> ChipParams(
            backgroundColor = SirioTheme.colors.chipDisactiveBackground,
            contentColor = SirioTheme.colors.chipFocusBackground,
            borderColor = SirioTheme.colors.chipFocusBackground,
        )

        isPressed -> ChipParams(
            backgroundColor = SirioTheme.colors.chipDisactiveBackground,
            contentColor = SirioTheme.colors.chipPressedBackground,
            borderColor = SirioTheme.colors.chipPressedBackground,
        )

        isHovered -> ChipParams(
            backgroundColor = SirioTheme.colors.chipDisactiveBackground,
            contentColor = SirioTheme.colors.chipHoverBackground,
            borderColor = SirioTheme.colors.chipHoverBackground,
        )

        else -> ChipParams(
            backgroundColor = SirioTheme.colors.chipDisactiveBackground,
            contentColor = SirioTheme.colors.chipDefaultBackground,
            borderColor = SirioTheme.colors.chipDefaultBackground,
        )
    }
}


data class ChipParams(
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color = Color.Unspecified,
    val buttonBorderColor: Color = Color.Unspecified,
    val buttonBackgroundColor: Color = Color.Unspecified,
)

@Preview
@Composable
private fun SirioChipCommonPreview() {
    SirioTheme {
        Column {
            SirioChipCommon(
                text = "Chips",
                icon = FaIcons.User,
                withClose = true,
                isActive = true,
                onClose = {},
                enabled = true,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = FaIcons.User,
                withClose = true,
                isActive = true,
                onClose = {},
                enabled = false,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = null,
                withClose = true,
                isActive = true,
                onClose = {},
                enabled = true,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = null,
                withClose = true,
                isActive = true,
                onClose = {},
                enabled = false,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = FaIcons.Check,
                withClose = false,
                isActive = true,
                onClose = {},
                enabled = true,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = FaIcons.Check,
                withClose = false,
                isActive = true,
                onClose = {},
                enabled = false,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = FaIcons.Check,
                withClose = false,
                isActive = false,
                onClose = {},
                enabled = true,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = null,
                withClose = false,
                isActive = true,
                onClose = {},
                enabled = true,
                onStateChange = {})
            SirioChipCommon(
                text = "Chips",
                icon = null,
                withClose = false,
                isActive = true,
                onClose = {},
                enabled = false,
                onStateChange = {}
            )
            SirioChipCommon(
                text = "Chips",
                icon = null,
                withClose = false,
                isActive = false,
                onClose = {},
                enabled = true,
                onStateChange = {})
        }
    }
}
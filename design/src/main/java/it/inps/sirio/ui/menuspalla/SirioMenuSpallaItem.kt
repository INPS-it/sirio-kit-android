//
// SirioMenuSpallaItem.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.menuspalla

import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioBaseColors
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.menuSpallaItemBorderWidth
import it.inps.sirio.theme.menuSpallaItemDividerHeight
import it.inps.sirio.theme.menuSpallaItemHeight
import it.inps.sirio.theme.menuSpallaItemIndicatorWidth
import it.inps.sirio.theme.menuSpallaItemPaddingEnd
import it.inps.sirio.theme.menuSpallaItemPrimaryPaddingStart
import it.inps.sirio.theme.menuSpallaItemSecondaryPaddingStart
import it.inps.sirio.theme.menuSpallaItemTertiaryPaddingStart
import it.inps.sirio.ui.tag.SirioTagCommon
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.border
import it.inps.sirio.utils.ifElse

@Composable
fun SirioMenuSpallaItem(
    title: String,
    level: SirioMenuSpallaItemLevel,
    tagText: String? = null,
    selected: Boolean = false,
    enabled: Boolean = true,
    hasSubItems: Boolean = false,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed = pressed || selected

    val itemColors = when (level) {
        SirioMenuSpallaItemLevel.FIRST -> SirioTheme.colors.menuSpalla.itemPrimary
        SirioMenuSpallaItemLevel.SECOND -> SirioTheme.colors.menuSpalla.itemSecondary
        SirioMenuSpallaItemLevel.THIRD -> SirioTheme.colors.menuSpalla.itemTertiary
    }
    val paddingStart = when (level) {
        SirioMenuSpallaItemLevel.FIRST -> menuSpallaItemPrimaryPaddingStart
        SirioMenuSpallaItemLevel.SECOND -> menuSpallaItemSecondaryPaddingStart
        SirioMenuSpallaItemLevel.THIRD -> menuSpallaItemTertiaryPaddingStart
    }

    val backgroundColor: Color = itemColors.background
        .get(
            disabled = enabled.not(),
            focused = isFocused,
            pressed = isPressed,
            hovered = isHovered
        )
    val contentColor: Color = itemColors.content
        .get(
            disabled = enabled.not(),
            focused = isFocused,
            pressed = isPressed,
            hovered = isHovered
        )
    val borderColor: Color = itemColors.border
        .get(
            disabled = enabled.not(),
            focused = isFocused,
            pressed = isPressed,
            hovered = isHovered
        )
    val indicatorColor: Color = itemColors.indicator
        .get(
            disabled = enabled.not(),
            focused = isFocused,
            pressed = isPressed,
            hovered = isHovered
        )
    val dividerColor: Color = itemColors.divider
        .get(
            disabled = enabled.not(),
            focused = isFocused,
            pressed = isPressed,
            hovered = isHovered
        )

    val hasArrow = level != SirioMenuSpallaItemLevel.THIRD && hasSubItems

    val hasTag = hasArrow.not()
            && level == SirioMenuSpallaItemLevel.FIRST
            && tagText.isNullOrBlank().not()

    val hasDivider = dividerColor != Color.Unspecified

    val hasIndicator = indicatorColor != Color.Unspecified && (hasArrow && isPressed).not()

    Surface(
        checked = selected,
        onCheckedChange = { onClick() },
        modifier = Modifier
            .height(menuSpallaItemHeight.dp)
            .fillMaxWidth()
            .ifElse(
                condition = hasDivider,
                ifTrueModifier = Modifier.border(
                    top = Border(
                        menuSpallaItemDividerHeight.dp,
                        dividerColor,
                    )
                )
            )
            .ifElse(
                condition = hasIndicator,
                ifTrueModifier = Modifier.border(
                    start = Border(
                        menuSpallaItemIndicatorWidth.dp,
                        indicatorColor,
                    )
                )
            ),
        enabled = enabled,
        color = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(width = menuSpallaItemBorderWidth.dp, color = borderColor),
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = paddingStart.dp, end = menuSpallaItemPaddingEnd.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioTextCommon(
                text = title,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                typography = SirioTheme.typography.menuSpalla.item.title,
            )
            if (hasTag) {
                val tagColors =
                    if (enabled) SirioTheme.colors.menuSpalla.itemPrimary.tag
                    else SirioTheme.colors.menuSpalla.itemPrimary.tagDisabled
                SirioTagCommon(
                    text = tagText!!,
                    colors = tagColors
                )
            }
            if (hasArrow) {
                val icon = if (selected) FaIcons.AngleUp else FaIcons.AngleDown
                SirioIcon(
                    faIcon = icon,
                    iconColor = LocalContentColor.current
                )
            }
        }
    }
}

enum class SirioMenuSpallaItemLevel {
    FIRST,
    SECOND,
    THIRD;

    companion object {
        fun fromInt(value: Int): SirioMenuSpallaItemLevel = when (value) {
            0 -> FIRST
            1 -> SECOND
            2 -> THIRD
            else -> throw IllegalArgumentException("Invalid value: $value")
        }
    }
}

@Keep
data class SirioMenuSpallaItemColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val indicator: SirioColorState,
    val divider: SirioColorState,
    val content: SirioColorState,
    val tag: SirioBaseColors = SirioBaseColors.Unspecified,
    val tagDisabled: SirioBaseColors = SirioBaseColors.Unspecified,
) {
    companion object {
        @Stable
        val Unspecified = SirioMenuSpallaItemColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            indicator = SirioColorState.Unspecified,
            divider = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
            tag = SirioBaseColors.Unspecified,
            tagDisabled = SirioBaseColors.Unspecified,
        )
    }
}

@Keep
data class SirioMenuSpallaItemTypography(
    val title: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioMenuSpallaItemTypography(
            title = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioMenuSpallaItemPreview() {
    SirioTheme {
        Column {
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.FIRST,
                tagText = "3",
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.FIRST,
                tagText = "3",
                hasSubItems = true,
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.FIRST,
                tagText = "3",
                selected = true,
                hasSubItems = true,
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.FIRST,
                tagText = "3",
                selected = true,
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.FIRST,
                tagText = "3",
                enabled = false,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.SECOND,
                tagText = "3",
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.SECOND,
                tagText = "3",
                selected = true,
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.SECOND,
                tagText = "3",
                enabled = false,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.THIRD,
                tagText = "3",
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.THIRD,
                tagText = "3",
                selected = true,
                enabled = true,
                onClick = {},
            )
            SirioMenuSpallaItem(
                title = "Label",
                level = SirioMenuSpallaItemLevel.THIRD,
                tagText = "3",
                enabled = false,
                onClick = {},
            )
        }
    }
}
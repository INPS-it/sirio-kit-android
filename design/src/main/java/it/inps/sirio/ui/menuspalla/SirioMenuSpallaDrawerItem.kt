//
// SirioMenuSpallaDrawerItem.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.foundation.layout.Spacer
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
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.menuSpallaDrawerItemBorderWidth
import it.inps.sirio.theme.menuSpallaDrawerItemHeight
import it.inps.sirio.theme.menuSpallaDrawerItemIconSize
import it.inps.sirio.theme.menuSpallaDrawerItemPaddingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

@Composable
fun SirioMenuSpallaDrawerItem(
    title: String,
    subtitle: String,
    open: Boolean,
    onStateChange: (open: Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor: Color = SirioTheme.colors.menuSpalla.drawerItem.background
        .get(disabled = false, focused = isFocused, pressed = isPressed, hovered = isHovered)
    val contentColor: Color = SirioTheme.colors.menuSpalla.drawerItem.content
        .get(disabled = false, focused = isFocused, pressed = isPressed, hovered = isHovered)
    val borderColor: Color = SirioTheme.colors.menuSpalla.drawerItem.border
        .get(disabled = false, focused = isFocused, pressed = isPressed, hovered = isHovered)

    val icon = if (open) FaIcons.Times else FaIcons.AngleUp

    Surface(
        checked = open,
        onCheckedChange = onStateChange,
        modifier = Modifier
            .height(menuSpallaDrawerItemHeight.dp)
            .fillMaxWidth(),
        color = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(menuSpallaDrawerItemBorderWidth.dp, borderColor),
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = menuSpallaDrawerItemPaddingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                SirioTextCommon(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.menuSpalla.drawerItem.title,
                )
                SirioTextCommon(
                    text = subtitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.menuSpalla.drawerItem.subtitle,
                )
            }
            Spacer(Modifier.weight(1f))
            SirioIcon(
                faIcon = icon,
                iconColor = LocalContentColor.current,
                size = menuSpallaDrawerItemIconSize.dp,
            )
        }
    }
}

@Keep
data class SirioMenuSpallaDrawerItemColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val content: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioMenuSpallaDrawerItemColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
        )
    }
}

@Keep
data class SirioMenuSpallaDrawerItemTypography(
    val title: TextStyle,
    val subtitle: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioMenuSpallaDrawerItemTypography(
            title = TextStyle.Default,
            subtitle = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioMenuSpallaDrawerItemPreview() {
    SirioTheme {
        Column {
            val title = "Action Label"
            val subtitle = "Placeholder Label"
            SirioMenuSpallaDrawerItem(
                title = title,
                subtitle = subtitle,
                open = true,
                onStateChange = {},
            )
            SirioMenuSpallaDrawerItem(
                title = title,
                subtitle = subtitle,
                open = false,
                onStateChange = {},
            )
        }
    }
}
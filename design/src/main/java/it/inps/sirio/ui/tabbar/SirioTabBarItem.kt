//
// SirioTabBarItem.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabBarItemIconContainerSize
import it.inps.sirio.theme.tabBarItemIconSize
import it.inps.sirio.theme.tabBarItemPaddingHorizontal
import it.inps.sirio.theme.tabBarItemPaddingTop
import it.inps.sirio.theme.tabBarItemStateIndicatorHeight
import it.inps.sirio.ui.badge.SirioBadgeBox
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.ifElse
import it.inps.sirio.utils.takeTwoWords

/**
 * Displays a single item inside a Sirio Tab Bar.
 *
 * The item shows an icon with optional badge and highlight, a text label and
 * a visual indicator for the selected state. The entire item is clickable
 * and evenly distributed inside the tab bar row.
 *
 * @param selected Whether this tab item is currently selected.
 * @param tabItem Data model describing the tab item content and behavior.
 */
@Composable
internal fun RowScope.SirioTabBarItem(
    selected: Boolean,
    tabItem: SirioTabBarItemData,
) {
    Surface(
        onClick = tabItem.action,
        modifier = Modifier
            .width(intrinsicSize = IntrinsicSize.Max)
            .weight(1f)
            .testTag("SirioTabBar${tabItem.label.takeTwoWords()}"),
        color = SirioTheme.colors.tabBar.item.container,
        contentColor = SirioTheme.colors.tabBar.item.content.get(pressed = selected),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SirioTabBarStateIndicator(selected)
            Column(
                modifier = Modifier.padding(horizontal = tabBarItemPaddingHorizontal.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(tabBarItemPaddingTop.dp))
                SirioTabBarIconContainer(tabItem.highlighted) {
                    SirioBadgeBox(hasBadge = tabItem.badge) {
                        SirioIcon(
                            icon = SirioIconSource.FaIcon(tabItem.icon),
                            size = tabBarItemIconSize.dp,
                            iconColor = LocalContentColor.current,
                        )
                    }
                }
                SirioText(
                    text = tabItem.label,
                    color = LocalContentColor.current,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
                )
            }
        }
    }
}

/**
 * Visual indicator displayed at the top of a tab bar item when selected.
 *
 * When the item is not selected, an empty spacer is used to preserve layout
 * consistency across tab items.
 *
 * @param selected Whether the tab item is currently selected.
 */
@Composable
private fun SirioTabBarStateIndicator(selected: Boolean) {
    if (selected) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(tabBarItemStateIndicatorHeight.dp)
                .background(LocalContentColor.current)
        )
    } else {
        Spacer(modifier = Modifier.height(tabBarItemStateIndicatorHeight.dp))
    }
}

/**
 * Container for a tab bar icon with optional highlighted background.
 *
 * The icon is centered inside a fixed-size container. When [highlighted] is
 * true, a circular background is applied behind the icon.
 *
 * This composable is used as a lightweight layout wrapper for icon content.
 *
 * @param highlighted Whether the icon container should be visually highlighted.
 * @param content Slot used to emit the icon and optional decorations.
 */
@Composable
private inline fun SirioTabBarIconContainer(
    highlighted: Boolean,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .size(tabBarItemIconContainerSize.dp)
            .ifElse(
                condition = highlighted,
                ifTrueModifier = Modifier.background(
                    color = SirioTheme.colors.tabBar.item.highlighted,
                    shape = CircleShape,
                ),
            ),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

@Keep
data class SirioTabBarItemColors(
    val container: Color,
    val highlighted: Color,
    val content: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioTabBarItemColors(
            container = Color.Unspecified,
            highlighted = Color.Unspecified,
            content = SirioColorState.Unspecified,
        )
    }
}

internal val tabBarItemLightColors = SirioTabBarItemColors(
    container = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    highlighted = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    content = SirioColorState(
        default = FoundationColor.colorAliasAppInteractiveSecondaryDefault,
        pressed = FoundationColor.colorAliasAppInteractivePrimaryActive,
    ),
)

internal val tabBarItemDarkColors = tabBarItemLightColors

@Preview
@Composable
private fun SirioTabBarItemPreview() {
    SirioTheme {
        Row {
            SirioTabBarItem(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
            )
            SirioTabBarItem(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
            )
            SirioTabBarItem(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Notifiche",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
            SirioTabBarItem(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Notifiche",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
            SirioTabBarItem(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Servizi",
                    icon = FaIcons.GripHorizontal,
                    highlighted = true,
                    action = {},
                ),
            )
            SirioTabBarItem(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Servizi",
                    icon = FaIcons.GripHorizontal,
                    highlighted = true,
                    action = {},
                ),
            )
        }
    }
}

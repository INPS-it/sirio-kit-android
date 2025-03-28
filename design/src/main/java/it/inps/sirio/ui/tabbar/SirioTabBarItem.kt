//
// SirioTabBarItem.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseStateColors
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabBarItemHorizontalContentPaddingBottom
import it.inps.sirio.theme.tabBarItemHorizontalContentPaddingTop
import it.inps.sirio.theme.tabBarItemHorizontalPaddingBottom
import it.inps.sirio.theme.tabBarItemHorizontalSpacing
import it.inps.sirio.theme.tabBarItemIconSize
import it.inps.sirio.theme.tabBarItemPaddingHorizontal
import it.inps.sirio.theme.tabBarItemPaddingTop
import it.inps.sirio.theme.tabBarItemStateIndicatorHeight
import it.inps.sirio.theme.tabBarItemVerticalPaddingBottom
import it.inps.sirio.theme.tabBarItemVerticalSpacing
import it.inps.sirio.ui.badge.SirioBadgeBox
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

@Composable
internal fun RowScope.SirioTabBarItemVertical(
    selected: Boolean,
    tabItem: SirioTabBarItemData,
) {
    Surface(
        onClick = tabItem.action,
        modifier = Modifier
            .width(intrinsicSize = IntrinsicSize.Max)
            .weight(1f),
        color = SirioTheme.colors.tabBar.item.container.get(pressed = selected),
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
                SirioBadgeBox(hasBadge = tabItem.badge) {
                    SirioIcon(
                        icon = SirioIconSource.FaIcon(tabItem.icon),
                        size = tabBarItemIconSize.dp,
                        iconColor = LocalContentColor.current
                    )
                }
                Spacer(modifier = Modifier.height(tabBarItemVerticalSpacing.dp))
                SirioText(
                    text = tabItem.label,
                    color = LocalContentColor.current,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
                )
                Spacer(modifier = Modifier.height(tabBarItemVerticalPaddingBottom.dp))
            }
        }
    }
}

@Composable
internal fun SirioTabBarItemHorizontal(
    selected: Boolean,
    tabItem: SirioTabBarItemData,
) {
    Surface(
        onClick = tabItem.action,
        modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max),
        color = SirioTheme.colors.tabBar.item.container.get(pressed = selected),
        contentColor = SirioTheme.colors.tabBar.item.content.get(pressed = selected),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SirioTabBarStateIndicator(selected)
            Spacer(modifier = Modifier.height(tabBarItemPaddingTop.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = tabBarItemPaddingHorizontal.dp)
                    .padding(
                        top = tabBarItemHorizontalContentPaddingTop.dp,
                        bottom = tabBarItemHorizontalContentPaddingBottom.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SirioBadgeBox(hasBadge = tabItem.badge) {
                    SirioIcon(
                        icon = SirioIconSource.FaIcon(tabItem.icon),
                        size = tabBarItemIconSize.dp,
                        iconColor = LocalContentColor.current
                    )
                }
                Spacer(modifier = Modifier.width(tabBarItemHorizontalSpacing.dp))
                SirioText(
                    text = tabItem.label,
                    color = LocalContentColor.current,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
                )
            }
            Spacer(modifier = Modifier.height(tabBarItemHorizontalPaddingBottom.dp))
        }
    }
}


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

internal val tabBarItemLightColors = SirioBaseStateColors(
    container = SirioColorState.all(FoundationColor.colorAliasBackgroundColorPrimaryLight0),
    content = SirioColorState(
        FoundationColor.colorAliasAppInteractiveSecondaryDefault,
        pressed = FoundationColor.colorAliasAppInteractivePrimaryActive,
    ),
)

internal val tabBarItemDarkColors = tabBarItemLightColors

@Preview
@Composable
private fun SirioTabBarItemVerticalPreview() {
    SirioTheme {
        Row {
            SirioTabBarItemVertical(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
            )
            SirioTabBarItemVertical(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
            )
            SirioTabBarItemVertical(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Notifiche",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
            SirioTabBarItemVertical(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Notifiche",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
        }
    }
}

@Preview
@Composable
private fun SirioTabBarItemHorizontalPreview() {
    SirioTheme {
        Row {
            SirioTabBarItemHorizontal(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
            )
            SirioTabBarItemHorizontal(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
            )
            SirioTabBarItemHorizontal(
                selected = false,
                tabItem = SirioTabBarItemData(
                    label = "Notifiche",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
            SirioTabBarItemHorizontal(
                selected = true,
                tabItem = SirioTabBarItemData(
                    label = "Notifiche",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
        }
    }
}
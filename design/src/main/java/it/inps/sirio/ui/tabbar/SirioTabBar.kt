//
// SirioTabBar.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabBarHeight
import it.inps.sirio.theme.tabBarItemIconSize
import it.inps.sirio.theme.tabBarItemStateIndicatorHeight
import it.inps.sirio.ui.badge.SirioBadgeCommon
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * A bottom navigation with tabs
 *
 * @param items The items [SirioTabBarItemData] to be shown. Min 3 - Max 5
 * @param selectedIndex The index of the selected tab. Default 0
 */
@Composable
fun SirioTabBar(
    items: List<SirioTabBarItemData>,
    selectedIndex: Int = 0,
) {
    //TabBar should contain 3-5 tabs
    assert(items.size in 3..5)
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(tabBarHeight.dp),
        containerColor = SirioTheme.colors.tabBarBackground,
    ) {
        val labelTypography = with(SirioTheme.typography.tabBarItemText) {
            if (LocalConfiguration.current.fontScale > 1) copy(fontSize = 9.sp) else this
        }

        items.take(5).forEachIndexed { index, tabItem ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = tabItem.action,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(tabBarItemStateIndicatorHeight.dp)
                                .background(if (index == selectedIndex) SirioTheme.colors.tabBarActive else SirioTheme.colors.tabBarBackground)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        BadgedBox(
                            badge = {
                                if (tabItem.badge) {
                                    SirioBadgeCommon()
                                }
                            },
                        ) {
                            SirioIcon(
                                faIcon = tabItem.icon,
                                size = tabBarItemIconSize.dp,
                                iconColor = LocalContentColor.current
                            )
                        }
                    }
                },
                label = {
                    SirioTextCommon(
                        text = tabItem.label,
                        color = LocalContentColor.current,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        typography = labelTypography,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SirioTheme.colors.tabBarActive,
                    selectedTextColor = SirioTheme.colors.tabBarActive,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = SirioTheme.colors.tabBarContent,
                    unselectedTextColor = SirioTheme.colors.tabBarContent,
                )
            )
        }
    }
}
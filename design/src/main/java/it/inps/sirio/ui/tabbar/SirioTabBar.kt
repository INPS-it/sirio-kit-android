//
// SirioTabBar.kt
//
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabBarDividerHeight
import it.inps.sirio.theme.tabBarHeight
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border

/**
 * A bottom navigation with tabs
 *
 * @param items The items [SirioTabBarItemData] to be shown. Min 3 - Max 5
 * @param selectedIndex The index of the selected tab. Default 0
 * @param windowInsets Insets protecting the tabs from system navigation, defaulting to
 * Material 3 navigation bar insets (bottom and horizontal). Insets already consumed by
 * ancestors are excluded. Pass `WindowInsets(0, 0, 0, 0)` when handled externally.
 * The tab area remains 57 dp high; unconsumed insets add to the overall size.
 */
@Composable
fun SirioTabBar(
    items: List<SirioTabBarItemData>,
    selectedIndex: Int = 0,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
) {
    //TabBar should contain 3-5 tabs
    assert(items.size in 3..5)
    Surface(
        modifier = Modifier
            .semantics { testTagsAsResourceId = true }
            .border(
                top = Border(
                    tabBarDividerHeight.dp,
                    SirioTheme.colors.tabBar.divider,
                )
            ),
        color = SirioTheme.colors.tabBar.background,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .height(tabBarHeight.dp)
                .padding(top = tabBarDividerHeight.dp)
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, tabItem ->
                SirioTabBarItem(index == selectedIndex, tabItem)
            }
        }
    }
}

@Keep
data class SirioTabBarColors(
    val background: Color,
    val divider: Color,
    val item: SirioTabBarItemColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioTabBarColors(
            background = Color.Unspecified,
            divider = Color.Unspecified,
            item = SirioTabBarItemColors.Unspecified,
        )
    }
}

internal val tabBarLightColors = SirioTabBarColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    divider = FoundationColor.colorAliasBackgroundColorPrimaryLight60,
    item = tabBarItemLightColors,
)
internal val tabBarDarkColors = SirioTabBarColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    divider = FoundationColor.colorAliasBackgroundColorPrimaryLight60,
    item = tabBarItemDarkColors,
)

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun SirioTabBarPreview(
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
) {
    SirioTheme {
        SirioTabBar(
            listOf(
                SirioTabBarItemData(
                    label = "Home",
                    icon = FaIcons.Home,
                    badge = false,
                    action = {},
                ),
                SirioTabBarItemData(
                    label = "Notizie",
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
                SirioTabBarItemData(
                    label = "Mappe",
                    icon = FaIcons.Globe,
                    badge = false,
                    action = {},
                ),
                SirioTabBarItemData(
                    label = "Contatti",
                    icon = FaIcons.CommentAlt,
                    badge = false,
                    action = {},
                ),
                SirioTabBarItemData(
                    label = "Servizi",
                    icon = FaIcons.GripHorizontal,
                    badge = false,
                    action = {},
                )
            ),
            windowInsets = windowInsets,
        )
    }
}

@Preview(showBackground = true, name = "No insets")
@Composable
private fun SirioTabBarNoInsetsPreview() {
    SirioTabBarPreview(WindowInsets(0, 0, 0, 0))
}

@Preview(showBackground = true, name = "Bottom inset")
@Composable
private fun SirioTabBarBottomInsetPreview() {
    SirioTabBarPreview(WindowInsets(bottom = 24.dp))
}

@Preview(showBackground = true, name = "Side insets")
@Composable
private fun SirioTabBarSideInsetsPreview() {
    SirioTabBarPreview(WindowInsets(left = 24.dp, right = 24.dp))
}

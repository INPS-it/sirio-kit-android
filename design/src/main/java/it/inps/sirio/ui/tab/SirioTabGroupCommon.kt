//
// SirioTabGroupCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tab

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabGroupDividerHeight
import it.inps.sirio.theme.tabGroupEdgePadding
import it.inps.sirio.theme.tabGroupItemSpacing
import it.inps.sirio.theme.tabGroupPaddingBottom
import it.inps.sirio.theme.tabGroupPaddingTop
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.ifElse

/**
 * Sirio tab group implementation
 *
 * @param items List of tab [TabItemData] contained in group
 * @param selectedIndex The current selected index
 * @param modifier The [Modifier] to be applied to this tab group
 * @param onTabSelected The tab selection callback
 */
@Composable
internal fun SirioTabGroupCommon(
    items: List<TabItemData>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit,
) {
    require(items.isNotEmpty()) { "TabGroup cannot be empty" }
    require(selectedIndex <= items.size) { "SelectedIndex out of items range" }

    PrimaryScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(SirioTheme.colors.tab.group.background)
            .padding(top = tabGroupPaddingTop.dp, bottom = tabGroupPaddingBottom.dp),
        containerColor = SirioTheme.colors.tab.group.background,
        edgePadding = tabGroupEdgePadding.dp,
        indicator = {},
        divider = {
            HorizontalDivider(
                thickness = tabGroupDividerHeight.dp,
                color = SirioTheme.colors.tab.group.divider,
            )
        }
    ) {
        items.forEachIndexed { index, item ->
            Box(
                modifier = Modifier.ifElse(
                    index < items.size - 1,
                    Modifier.padding(end = tabGroupItemSpacing.dp)
                ),
            ) {
                SirioTab(
                    label = item.label,
                    icon = item.icon,
                    enabled = item.enabled,
                    selected = index == selectedIndex,
                    onSelect = { onTabSelected(index) }
                )
            }
        }
    }
}

@Keep
data class SirioTabGroupColors(
    val background: Color,
    val divider: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTabGroupColors(
            background = Color.Unspecified,
            divider = Color.Unspecified,
        )
    }
}

internal val tabGroupLightColors = SirioTabGroupColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    divider = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
)

internal val tabGroupDarkColors = tabGroupLightColors

@Keep
data class SirioTabColors(
    val item: SirioTabItemColors,
    val group: SirioTabGroupColors,
) {

    companion object {
        @Stable
        val Unspecified = SirioTabColors(
            item = SirioTabItemColors.Unspecified,
            group = SirioTabGroupColors.Unspecified,
        )
    }
}

internal val tabLightColors = SirioTabColors(
    item = tabItemLightColors,
    group = tabGroupLightColors,
)

internal val tabDarkColors = SirioTabColors(
    item = tabItemDarkColors,
    group = tabGroupDarkColors,
)

@Preview(showSystemUi = true)
@Composable
private fun TabsCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTabGroupCommon(
                items = listOf(
                    TabItemData(
                        label = "Label tab 1",
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        enabled = true,
                    ),
                    TabItemData(
                        label = "Label tab 2",
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        enabled = true,
                    ),
                    TabItemData(
                        label = "Label tab 3",
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        enabled = false,
                    ),
                    TabItemData(
                        label = "Label tab 4",
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        enabled = true,
                    ),
                ),
                selectedIndex = 0,
                onTabSelected = {},
            )
        }
    }
}
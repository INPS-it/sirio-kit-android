//
// SirioTabGroupCommon.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabEdgePadding

/**
 * Sirio tab group implementation
 *
 * @param items List of tab [TabItemData] contained in group
 * @param selectedIndex The current selected index
 * @param selection A [TabSelectionIndicatorPosition] for top or bottom selection indicator
 * @param onTabSelected The tab selection callback
 */
@Composable
internal fun SirioTabGroupCommon(
    items: List<TabItemData>,
    selectedIndex: Int,
    selection: TabSelectionIndicatorPosition,
    onTabSelected: (Int) -> Unit,
) {
    require(items.isNotEmpty()) { "TabGroup cannot be empty" }
    require(selectedIndex <= items.size) { "SelectedIndex out of items range" }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        edgePadding = tabEdgePadding,
        containerColor = if (selection == TabSelectionIndicatorPosition.TOP) Color.Transparent else Color.White,
        indicator = { },
        divider = {}
    ) {
        items.forEachIndexed { index, item ->
            SirioTabCommon(
                label = item.label,
                icon = item.icon,
                enabled = item.enabled,
                selected = index == selectedIndex,
                selection = selection,
                onSelect = { onTabSelected(index) }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TabsCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTabGroupCommon(
                items = listOf(
                    TabItemData(
                        label = "Label tab 1",
                        icon = FaIcons.Check,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 2",
//                        icon = FaIcons.File,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 3",
                        icon = FaIcons.Globe,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 4",
                        icon = FaIcons.Lightbulb,
                        enabled = false
                    ),
                    TabItemData(
                        label = "Label tab 5",
                        icon = FaIcons.PiggyBank,
                        enabled = true
                    ),
                ),
                selection = TabSelectionIndicatorPosition.BOTTOM,
                selectedIndex = 0,
                onTabSelected = {})
            SirioTabGroupCommon(
                items = listOf(
                    TabItemData(
                        label = "Label tab 1",
                        icon = FaIcons.Check,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 2",
//                        icon = FaIcons.File,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 3",
                        icon = FaIcons.Globe,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 4",
                        icon = FaIcons.Lightbulb,
                        enabled = false
                    ),
                    TabItemData(
                        label = "Label tab 5",
                        icon = FaIcons.PiggyBank,
                        enabled = true
                    ),
                ),
                selection = TabSelectionIndicatorPosition.TOP,
                selectedIndex = 0,
                onTabSelected = {})
        }
    }
}

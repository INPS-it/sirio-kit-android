//
// SirioFilterTabs.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.tabs.SirioTabGroupCommon
import it.inps.sirio.ui.tabs.TabItemData
import it.inps.sirio.ui.tabs.TabSelectionIndicatorPosition

/**
 * A composable function that displays a row of tabs for filtering.
 *
 * @param items The list of tab data to display.
 * @param selectedIndex The index of the currently selected tab.
 * @param withScroll Whether to display scroll buttons for navigating between tabs.
 * @param onTabSelected The callback to be invoked when a tab is selected.
 */
@Composable
fun SirioFilterTabs(
    items: List<TabItemData>,
    selectedIndex: Int = 0,
    withScroll: Boolean = false,
    onTabSelected: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background),
        verticalAlignment = Alignment.Bottom,
    ) {
        if (withScroll) {
            SirioButton(
                size = ButtonSize.Medium,
                style = ButtonStyle.Ghost,
                icon = FaIcons.ChevronLeft,
                enabled = selectedIndex != 0,
                onClick = { onTabSelected(selectedIndex - 1) }
            )
        }
        SirioTabGroupCommon(
            items = items,
            selectedIndex = selectedIndex,
            selection = TabSelectionIndicatorPosition.TOP,
            modifier = Modifier.weight(1f),
            onTabSelected = onTabSelected,
        )
        if (withScroll) {
            SirioButton(
                size = ButtonSize.Medium,
                style = ButtonStyle.Ghost,
                icon = FaIcons.ChevronRight,
                enabled = selectedIndex != items.lastIndex,
                onClick = { onTabSelected(selectedIndex + 1) }
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterTabsPreview() {
    SirioTheme {
        Column {
            SirioFilterTabs(
                items = listOf(
                    TabItemData("Label tab"),
                    TabItemData("Label tab"),
                ),
                onTabSelected = {},
            )
            SirioFilterTabs(
                items = listOf(
                    TabItemData("Label tab"),
                    TabItemData("Label tab"),
                ),
                selectedIndex = 1,
                onTabSelected = {},
                withScroll = true,
            )
        }
    }
}
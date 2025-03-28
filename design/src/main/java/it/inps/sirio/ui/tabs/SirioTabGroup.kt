//
// SirioTabGroup.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio tab group component
 *
 * @param items List of tab [TabItemData] contained in group
 * @param selectedIndex The current selected index, starting from 0
 * @param selection A [TabSelectionIndicatorPosition] for top or bottom selection indicator
 * @param onTabSelected The tab selection callback
 */
@Composable
fun SirioTabGroup(
    items: List<TabItemData>,
    selectedIndex: Int = 0,
    selection: TabSelectionIndicatorPosition = TabSelectionIndicatorPosition.BOTTOM,
    onTabSelected: (Int) -> Unit,
) {
    SirioTabGroupCommon(
        items = items,
        selectedIndex = selectedIndex,
        selection = selection,
        onTabSelected = onTabSelected,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TabGroupPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTabGroup(
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
                onTabSelected = {},
            )
        }
    }
}
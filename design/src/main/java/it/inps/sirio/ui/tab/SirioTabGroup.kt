//
// SirioTabGroup.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.utils.SirioIconSource

/**
 * Sirio tab group component
 *
 * @param items List of tab [TabItemData] contained in group
 * @param selectedIndex The current selected index, starting from 0
 * @param onTabSelected The tab selection callback
 */
@Composable
fun SirioTabGroup(
    items: List<TabItemData>,
    selectedIndex: Int = 0,
    onTabSelected: (Int) -> Unit,
) {
    SirioTabGroupCommon(
        items = items,
        selectedIndex = selectedIndex,
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
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SirioTabGroup(
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
                        enabled = true,
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
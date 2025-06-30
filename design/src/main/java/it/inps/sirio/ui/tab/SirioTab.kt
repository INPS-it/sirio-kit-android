//
// SirioTab.kt
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
import it.inps.sirio.utils.SirioIconSource

/**
 * Sirio tab to be used in [SirioTabGroup]
 *
 * @param label The tab text
 * @param icon The tab optional FA icon [FaIcons]
 * @param enabled Whether the tab can be selected by user. Default `true`
 * @param selected Whether the tab is the selected one. Default `false`
 * @param onSelect The callback for the tab selection
 */
@Composable
fun SirioTab(
    label: String,
    icon: SirioIconSource? = null,
    enabled: Boolean = true,
    selected: Boolean = false,
    onSelect: () -> Unit,
) {
    SirioTabCommon(
        label = label,
        icon = icon,
        enabled = enabled,
        selected = selected,
        onSelect = onSelect
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TabsPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTab(
                label = "Label tab",
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                enabled = true,
                selected = true,
                onSelect = {},
            )
        }
    }
}
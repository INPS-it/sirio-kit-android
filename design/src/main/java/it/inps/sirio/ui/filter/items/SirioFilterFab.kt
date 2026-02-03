//
// SirioFilterFab.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.fab.SirioFab
import it.inps.sirio.ui.fab.SirioFabData

/**
 * A floating action button with text "Filtra" and a filter icon
 *
 * @param text The text to be displayed on the FAB, default is "Filtra"
 * @param onClick The callback to be invoked when the button is pressed
 *
 */
@Composable
fun SirioFilterFab(
    text: String = "Filtra",
    onClick: () -> Unit,
) {
    SirioFab(
        fabData = SirioFabData(
            icon = FaIcons.Filter,
            text = text,
            action = onClick,
        )
    )
}

/**
 * The position of the filter FAB, which is centered.
 */
val SirioFilterFabPosition = FabPosition.Center

@Preview
@Composable
private fun SirioFilterFabPreview() {
    SirioTheme {
        Scaffold(
            floatingActionButton = {
                SirioFilterFab(onClick = {})
            },
            floatingActionButtonPosition = SirioFilterFabPosition,
            content = { Box(modifier = Modifier.padding(it)) }
        )
    }
}
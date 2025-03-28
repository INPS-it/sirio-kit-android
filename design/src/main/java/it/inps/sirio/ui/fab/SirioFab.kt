//
// SirioFab.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.fab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A composable function that renders a customizable Floating Action Button (FAB) using the provided [SirioFabData].
 *
 * @param fabData The data required to configure the FAB, including its icon, text, content description, size, and click action.
 */
@Composable
fun SirioFab(
    fabData: SirioFabData,
) {
    SirioFabCommon(
        icon = fabData.icon,
        text = fabData.text,
        iconContentDescription = fabData.iconContentDescription,
        size = fabData.size,
        onClick = fabData.action,
    )
}

@Preview
@Composable
private fun SirioFabPreview() {
    SirioTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SirioFab(
                fabData = SirioFabData(
                    icon = FaIcons.Plus,
                    size = SirioFabSize.REGULAR,
                    action = {},
                )
            )
            SirioFab(
                fabData = SirioFabData(
                    icon = FaIcons.Plus,
                    size = SirioFabSize.SMALL,
                    action = {},
                )
            )
            SirioFab(
                fabData = SirioFabData(
                    icon = FaIcons.Plus,
                    text = "Text",
                    action = {},
                )
            )
        }
    }
}
//
// FabExtended.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A Floating action button with icon and text
 * @param fabExtendedData the FAB content
 */
@Composable
fun FabExtended(
    fabExtendedData: FabExtendedItemData,
) {
    FabCommon(
        onClick = fabExtendedData.action,
        icon = fabExtendedData.icon,
        text = fabExtendedData.text,
        type = FABType.EXTENDED
    )
}

@Preview
@Composable
private fun FabExtendedPreview() {
    SirioTheme {
        FabExtended(
            fabExtendedData = FabExtendedItemData(
                text = "Text",
                icon = FaIcons.Plus,
                action = {})
        )
    }
}
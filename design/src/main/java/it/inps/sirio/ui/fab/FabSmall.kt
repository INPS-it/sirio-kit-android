//
// FabSmall.kt
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
 * A small Floating action button with icon only
 * @param fabData the FAB content
 */
@Composable
fun FabSmall(
    fabData: FabItemData,
) {
    FabCommon(onClick = fabData.action, icon = fabData.icon, type = FABType.SMALL)
}

@Preview
@Composable
private fun FabSmallPreview() {
    SirioTheme {
        FabSmall(fabData = FabItemData(icon = FaIcons.Plus, action = {}))
    }
}
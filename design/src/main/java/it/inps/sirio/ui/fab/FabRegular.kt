//
// FabRegular.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A Floating action button with icon only
 * @param fabData the FAB content
 */
@Composable
fun FabRegular(
    fabData: FabItemData,
) {
    FabCommon(onClick = fabData.action, icon = fabData.icon, type = FABType.REGULAR)
}

@Preview
@Composable
private fun FabRegularPreview() {
    SirioTheme {
        FabRegular(fabData = FabItemData(icon = FaIcons.Plus, action = {}))
    }
}
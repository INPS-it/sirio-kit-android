//
// SirioAppNavigationSelection.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A scope control app navigation
 *
 * @param title The string to show
 * @param leftItem An [SirioAppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [SirioAppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [SirioAppNavigationItemData] with the content of the second right item
 */
@Composable
fun SirioAppNavigationSelection(
    title: String,
    leftItem: SirioAppNavigationItemData? = null,
    rightFirstItem: SirioAppNavigationItemData? = null,
    rightSecondItem: SirioAppNavigationItemData? = null,
) {
    SirioTheme(darkTheme = !SirioTheme.colors.isDark) {
        SirioTopAppBar(
            title = {
                SirioAppNavigationTitle(title)
            },
            navigationIcon = {
                leftItem?.let {
                    SirioAppNavigationIconButton(
                        icon = it.icon,
                        badge = it.badge,
                        action = it.action,
                    )
                }
            },
            actions = sirioAppNavigationActions(
                rightFirstItem = rightFirstItem,
                rightSecondItem = rightSecondItem,
            ),
        )
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SirioAppNavigationSelectionPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val title = "1 Elemento Selezionato"
            SirioAppNavigationSelection(
                title = title,
                leftItem = SirioAppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
                rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.Download, action = {}),
                rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Trash, action = {}),
            )
        }
    }
}
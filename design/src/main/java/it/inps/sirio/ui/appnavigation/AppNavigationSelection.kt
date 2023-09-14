//
// AppNavigationSelection.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A scope control app navigation
 *
 * @param title The string to show
 * @param leftItem An [AppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [AppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [AppNavigationItemData] with the content of the second right item
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationSelection(
    title: String,
    leftItem: AppNavigationItemData? = null,
    rightFirstItem: AppNavigationItemData? = null,
    rightSecondItem: AppNavigationItemData? = null,
) {
    SirioTheme(darkTheme = !SirioTheme.colors.isDark) {
        TopAppBar(
            title = {
                AppNavigationTitle(title)
            },
            navigationIcon = {
                leftItem?.let {
                    AppNavigationIconButton(
                        icon = it.icon,
                        action = it.action,
                    )
                }
            },
            actions = appNavigationActions(
                rightFirstItem = rightFirstItem,
                rightSecondItem = rightSecondItem,
            ),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = SirioTheme.colors.appNavigationBackground,
                navigationIconContentColor = SirioTheme.colors.brand,
                titleContentColor = SirioTheme.colors.appNavigationText,
                actionIconContentColor = SirioTheme.colors.appNavigationIcon,
            ),
        )
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppNavigationSelectionPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val title = "1 Elemento Selezionato"
            AppNavigationSelection(
                title = title,
                leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
                rightFirstItem = AppNavigationItemData(icon = FaIcons.Download, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Trash, action = {}),
            )
        }
    }
}
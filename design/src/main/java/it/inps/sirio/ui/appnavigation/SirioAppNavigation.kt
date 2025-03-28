//
// SirioAppNavigation.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A standard app navigation
 *
 * @param title The string title to show in the navigation bar
 * @param leftItem An optional [SirioAppNavigationItemData] to set on the left of the navigation bar
 * @param rightFirstItem An optional [SirioAppNavigationItemData] to set as first item on the right of the navigation bar
 * @param rightSecondItem An optional [SirioAppNavigationItemData] to set as second item on the right of the navigation bar
 */
@Composable
fun SirioAppNavigation(
    title: String,
    leftItem: SirioAppNavigationItemData? = null,
    rightFirstItem: SirioAppNavigationItemData? = null,
    rightSecondItem: SirioAppNavigationItemData? = null,
) {
    SirioTopAppBar(
        title = {
            SirioAppNavigationTitle(title)
        },
        navigationIcon = {
            leftItem?.let {
                SirioAppNavigationIconButton(
                    icon = it.icon,
                    contentDescription = it.contentDescription,
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

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SirioAppNavigationPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            val title = "Titolo pagina"
            val longTitle =
                "Titolo di pagina molto molto molto lungo su due righe con sospensione del testo"
            SirioAppNavigation(
                title = title,
                rightFirstItem = SirioAppNavigationItemData(username = "MC", action = {}),
                rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
            )
            Spacer(modifier = Modifier.height(8.dp))
            SirioAppNavigation(
                title = title,
                leftItem = SirioAppNavigationItemData(icon = FaIcons.ChevronLeft, action = {}),
                rightFirstItem = SirioAppNavigationItemData(
                    icon = FaIcons.User,
                    badge = false,
                    action = {},
                ),
                rightSecondItem = SirioAppNavigationItemData(
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            SirioAppNavigation(
                title = longTitle,
                rightFirstItem = SirioAppNavigationItemData(username = "MC", action = {}),
                rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
            )
            Spacer(modifier = Modifier.height(8.dp))
            SirioAppNavigation(
                title = longTitle,
                leftItem = SirioAppNavigationItemData(icon = FaIcons.ChevronLeft, action = {}),
                rightFirstItem = SirioAppNavigationItemData(
                    icon = FaIcons.User,
                    badge = false,
                    action = {},
                ),
                rightSecondItem = SirioAppNavigationItemData(
                    icon = FaIcons.Bell,
                    badge = true,
                    action = {},
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

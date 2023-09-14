//
// AppNavigation.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
 * @param title The string to show
 * @param leftItem An [AppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [AppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [AppNavigationItemData] with the content of the second right item
 * @param scrollBehavior A [TopAppBarScrollBehavior] which holds various offset values that will be
 * applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 * work in conjunction with a scrolled content to change the top app bar appearance as the content
 * scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    title: String,
    leftItem: AppNavigationItemData? = null,
    rightFirstItem: AppNavigationItemData? = null,
    rightSecondItem: AppNavigationItemData? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = {
            AppNavigationTitle(title)
        },
        navigationIcon = {
            leftItem?.let {
                AppNavigationIconButton(
                    icon = it.icon,
                    action = it.action,
                    contentDescription = it.contentDescription,
                )
            }
        },
        actions = appNavigationActions(
            rightFirstItem = rightFirstItem,
            rightSecondItem = rightSecondItem,
        ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = SirioTheme.colors.appNavigationBackground,
            navigationIconContentColor = SirioTheme.colors.appNavigationIcon,
            titleContentColor = SirioTheme.colors.appNavigationText,
            actionIconContentColor = SirioTheme.colors.appNavigationIcon
        ),
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppNavigationPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            val title = "Titolo pagina"
            AppNavigation(
                title = title,
                rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppNavigation(
                title = title,
                leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
                rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//
// SirioAppNavigationBig.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A big app navigation
 *
 * @param title The string to show
 * @param leftItem An [SirioAppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [SirioAppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [SirioAppNavigationItemData] with the content of the second right item
 * @param scrollBehavior A [TopAppBarScrollBehavior] which holds various offset values that will be
 * applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 * work in conjunction with a scrolled content to change the top app bar appearance as the content
 * scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Deprecated(
    "Use SirioAppNavigation instead",
    replaceWith = ReplaceWith("SirioAppNavigation(title = title, leftItem = leftItem, rightFirstItem = rightFirstItem, rightSecondItem = rightSecondItem)")
)
fun SirioAppNavigationBig(
    title: String,
    leftItem: SirioAppNavigationItemData? = null,
    rightFirstItem: SirioAppNavigationItemData? = null,
    rightSecondItem: SirioAppNavigationItemData? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    LargeTopAppBar(
        title = {
            SirioAppNavigationTitle(title = title, big = true)
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
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = SirioTheme.colors.appNavigation.background,
            navigationIconContentColor = SirioTheme.colors.appNavigation.actionContent,
            titleContentColor = SirioTheme.colors.appNavigation.text,
            actionIconContentColor = SirioTheme.colors.appNavigation.actionContent,
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SirioAppNavigationBigPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            val title = "Titolo Grande"
            SirioAppNavigationBig(
                title = title,
                leftItem = SirioAppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
                rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
            )
        }
    }
}
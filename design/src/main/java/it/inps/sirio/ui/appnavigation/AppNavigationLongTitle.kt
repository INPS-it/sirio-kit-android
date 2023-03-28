//
// AppNavigationLongTitle.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * A long title app navigation
 *
 * @param title The string to show
 * @param leftItem An [AppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [AppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [AppNavigationItemData] with the content of the second right item
 * @param scrollBehavior a [TopAppBarScrollBehavior] which holds various offset values that will be
 * applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 * work in conjunction with a scrolled content to change the top app bar appearance as the content
 * scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationLongTitle(
    title: String,
    leftItem: AppNavigationItemData? = null,
    rightFirstItem: AppNavigationItemData? = null,
    rightSecondItem: AppNavigationItemData? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    LargeTopAppBar(
        title = {
            AppNavigationTitle(title, long = true)
        },
        Modifier.wrapContentHeight(),
        navigationIcon = {
            leftItem?.let {
                AppNavigationIconButton(
                    icon = it.icon,
                    contentDescription = it.contentDescription,
                    action = it.action,
                )
            }
        },
        actions = appNavigationActions(
            rightFirstItem = rightFirstItem,
            rightSecondItem = rightSecondItem,
        ),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = SirioTheme.colors.appNavigationBackground,
            navigationIconContentColor = SirioTheme.colors.appNavigationIcon,
            titleContentColor = SirioTheme.colors.appNavigationText,
            actionIconContentColor = SirioTheme.colors.appNavigationIcon,
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppNavigationMediumPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            val title = LoremIpsum(10).values.joinToString()
            AppNavigationLongTitle(
                title = title,
                leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
                rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
            )
        }
    }
}

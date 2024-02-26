//
// AppNavigationLogo.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.R
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationLogoSize

/**
 * A standard app navigation with INPS logo at the center
 *
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
fun AppNavigationLogo(
    leftItem: AppNavigationItemData? = null,
    rightFirstItem: AppNavigationItemData? = null,
    rightSecondItem: AppNavigationItemData? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Icon(
                painter = painterResource(id = if (SirioTheme.colors.isDark) R.drawable.inps_logotipo_negativo_colore_rgb else R.drawable.inps_logotipo_positivo_colore_rgb),
                contentDescription = null,
                modifier = Modifier.size(appNavigationLogoSize),
                tint = Color.Unspecified,
            )
        },
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = SirioTheme.colors.appNavigationBackground,
            navigationIconContentColor = SirioTheme.colors.appNavigationIcon,
            titleContentColor = SirioTheme.colors.brand,
            actionIconContentColor = SirioTheme.colors.appNavigationIcon,
        ),
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppNavigationLogoPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            AppNavigationLogo(
                rightFirstItem = AppNavigationItemData(
                    icon = FaIcons.User,
                    action = {}),
                rightSecondItem = AppNavigationItemData(
                    icon = FaIcons.Bell,
                    action = {},
                )
            )
        }
    }
}

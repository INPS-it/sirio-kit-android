//
// SirioAppNavigationLogo.kt
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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.R
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationLogoSize

/**
 * A standard app navigation with INPS logo at the center
 *
 * @param leftItem An [SirioAppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [SirioAppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [SirioAppNavigationItemData] with the content of the second right item
 */
@Composable
fun SirioAppNavigationLogo(
    leftItem: SirioAppNavigationItemData? = null,
    rightFirstItem: SirioAppNavigationItemData? = null,
    rightSecondItem: SirioAppNavigationItemData? = null,
) {
    SirioTopAppBar(
        title = {
            Icon(
                painter = painterResource(id = if (SirioTheme.colors.isDark) R.drawable.inps_logotipo_negativo_colore_rgb else R.drawable.inps_logotipo_positivo_colore_rgb),
                contentDescription = null,
                modifier = Modifier.requiredSize(appNavigationLogoSize.dp),
                tint = Color.Unspecified,
            )
        },
        centerTitle = true,
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
private fun SirioAppNavigationLogoPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            SirioAppNavigationLogo(
//                leftItem = SirioAppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
                rightFirstItem = SirioAppNavigationItemData(
                    icon = FaIcons.User,
                    action = {},
                ),
                rightSecondItem = SirioAppNavigationItemData(
                    icon = FaIcons.Bell,
                    action = {},
                )
            )
        }
    }
}

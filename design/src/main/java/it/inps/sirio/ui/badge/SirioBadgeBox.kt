//
// SirioBadgeBox.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.badge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.badgeBoxPaddingEnd
import it.inps.sirio.theme.badgeBoxPaddingTop
import it.inps.sirio.theme.tabBarItemIconSize
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

@Composable
internal inline fun SirioBadgeBox(
    hasBadge: Boolean = true,
    topPadding: Dp = badgeBoxPaddingTop.dp,
    endPadding: Dp = badgeBoxPaddingEnd.dp,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        content()
        if (hasBadge) {
            SirioBadgeCommon(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = topPadding, end = endPadding)
            )
        }
    }
}

@Preview
@Composable
private fun SirioBadgeBoxPreview() {
    SirioTheme {
        Column {
            SirioBadgeBox {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(FaIcons.Bell),
                    size = tabBarItemIconSize.dp,
                    iconColor = LocalContentColor.current
                )
            }
            SirioBadgeBox(false) {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(FaIcons.Bell),
                    size = tabBarItemIconSize.dp,
                    iconColor = LocalContentColor.current
                )
            }
        }
    }
}

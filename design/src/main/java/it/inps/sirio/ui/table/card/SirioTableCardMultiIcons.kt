//
// SirioTableCardMultiIcons.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableCardMultiIconsPaddingBottom
import it.inps.sirio.theme.tableCardMultiIconsPaddingTop
import it.inps.sirio.theme.tableComponentBorderWidth
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.border
import it.inps.sirio.utils.ifElse

@Composable
fun SirioTableCardMultiIcons(
    icons: List<SirioTableActionData>,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTheme(themeMode) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SirioTheme.colors.table.card.background)
                .ifElse(
                    condition = showDivider,
                    ifTrueModifier = Modifier.border(
                        bottom = Border(
                            strokeWidth = tableComponentBorderWidth.dp,
                            color = SirioTheme.colors.table.card.border,
                        )
                    )
                )
                .padding(
                    top = tableCardMultiIconsPaddingTop.dp,
                    bottom = tableCardMultiIconsPaddingBottom.dp,
                ),
            horizontalArrangement = Arrangement.End,
        ) {
            icons.forEach {
                SirioButton(
                    size = SirioButtonSize.Small,
                    hierarchy = SirioButtonHierarchy.GhostLight,
                    icon = it.icon,
                    iconContentDescription = it.contentDescription,
                    onClick = it.action
                )
            }
        }
    }
}

@Composable
internal fun SirioTableCardMultiIcons(
    data: SirioTableCardType.MultiIcons,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardMultiIcons(
        icons = data.actions,
        themeMode = themeMode,
        showDivider = showDivider,
    )
}

@Preview
@Composable
private fun SirioTableCardMultiIconsPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableCardMultiIcons(
                icons = listOf(
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.FilePdf),
                        action = {}),
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Download),
                        action = {}),
                    SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
                )
            )
            SirioTableCardMultiIcons(
                icons = listOf(
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.FilePdf),
                        action = {}),
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Download),
                        action = {}),
                    SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
                ),
                themeMode = SirioThemeMode.Dark,
            )
        }
    }
}

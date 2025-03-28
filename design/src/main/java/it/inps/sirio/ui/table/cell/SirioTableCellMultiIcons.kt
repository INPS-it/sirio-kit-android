//
// SirioTableCellMultiIcons.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.table.SirioTableIconData

@Composable
fun RowScope.SirioTableCellMultiIcons(
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
    icons: List<SirioTableIconData>,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            icons.forEach {
                SirioButton(
                    size = SirioButtonSize.Medium,
                    style = ButtonStyle.Ghost,
                    icon = it.icon,
                    iconContentDescription = it.contentDescription,
                    onClick = it.action,
                )
            }
        }
    }
}

@Composable
internal fun RowScope.SirioTableCellMultiIcons(
    data: SirioTableCellType.MultiIcons,
    weight: Float = 1f,
) {
    SirioTableCellMultiIcons(
        size = data.size,
        weight = weight,
        scroll = data.scroll,
        icons = data.iconsData,
    )
}

@Preview
@Composable
private fun SirioTableCellMultiIconsPreview() {
    SirioTheme {
        Column {
            val icons = listOf(
                SirioTableIconData(FaIcons.FilePdf, action = {}),
                SirioTableIconData(FaIcons.Download, action = {}),
                SirioTableIconData(FaIcons.Trash, action = {})
            )
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellMultiIcons(
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    icons = icons,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellMultiIcons(
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                    icons = icons,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellMultiIcons(
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                    icons = icons,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellMultiIcons(
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                    icons = icons,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellMultiIcons(
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                    icons = icons,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellMultiIcons(
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                    icons = icons,
                )
            }
        }
    }
}
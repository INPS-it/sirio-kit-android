//
// SirioTableCellMultiIcons.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.table.SirioTableIconData

@Composable
fun SirioTableCellMultiIcons(
    size: SirioTableContentSize,
    scroll: Boolean = false,
    icons: List<SirioTableIconData>,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            icons.forEach {
                SirioButton(
                    size = ButtonSize.Small,
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
internal fun SirioTableCellMultiIcons(data: SirioTableCellType.MultiIcons) {
    SirioTableCellMultiIcons(
        size = data.size,
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
            SirioTableCellMultiIcons(
                size = SirioTableContentSize.LARGE,
                scroll = false,
                icons = icons,
            )
            SirioTableCellMultiIcons(
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
                icons = icons,
            )
            SirioTableCellMultiIcons(
                size = SirioTableContentSize.SMALL,
                scroll = false,
                icons = icons,
            )
            SirioTableCellMultiIcons(
                size = SirioTableContentSize.LARGE,
                scroll = true,
                icons = icons,
            )
            SirioTableCellMultiIcons(
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
                icons = icons,
            )
            SirioTableCellMultiIcons(
                size = SirioTableContentSize.SMALL,
                scroll = true,
                icons = icons,
            )
        }
    }
}
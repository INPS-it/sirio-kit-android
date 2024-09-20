//
// SirioTableVerticalCell.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.vertical

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableVerticalItemBorderWidth
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.table.SirioTableIconData

@Composable
fun SirioTableVerticalCell(cellData: SirioTableVerticalCellData) {
    Column(
        Modifier
            .background(SirioTheme.colors.table.vertical.background)
            .border(tableVerticalItemBorderWidth.dp, SirioTheme.colors.table.vertical.border)
    ) {
        cellData.items.forEach { item ->
            SirioTableVerticalCellItem(data = item)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            cellData.icons.forEach {
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

@Preview
@Composable
private fun SirioTableVerticalPreview() {
    SirioTheme {
        val icons = listOf(
            SirioTableIconData(FaIcons.FilePdf, action = {}),
            SirioTableIconData(FaIcons.Download, action = {}),
            SirioTableIconData(FaIcons.Trash, action = {})
        )
        SirioTableVerticalCell(
            SirioTableVerticalCellData(
                items = listOf(
                    SirioTableVerticalCellItemData(
                        "Header",
                        "Link",
                        SirioTableVerticalCellItemType.LINK
                    ),
                    SirioTableVerticalCellItemData(
                        "Header",
                        "Lorem ipsum",
                        SirioTableVerticalCellItemType.TEXT
                    ),
                    SirioTableVerticalCellItemData(
                        "Header",
                        "00",
                        SirioTableVerticalCellItemType.NUMBER
                    ),
                    SirioTableVerticalCellItemData(
                        "Header",
                        "Lorem ipsum",
                        SirioTableVerticalCellItemType.TEXT
                    ),
                    SirioTableVerticalCellItemData(
                        "Header",
                        "Label Tag",
                        SirioTableVerticalCellItemType.TAG
                    ),
                ),
                icons = icons,
            ),
        )
    }
}
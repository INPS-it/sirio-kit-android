//
// SirioTableVertical.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.vertical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableVerticalPaddingCells
import it.inps.sirio.theme.tableVerticalPaddingHorizontal
import it.inps.sirio.ui.table.SirioTableIconData

@Composable
fun SirioTableVertical(cells: List<SirioTableVerticalCellData>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = tableVerticalPaddingHorizontal.dp),
        verticalArrangement = Arrangement.spacedBy(tableVerticalPaddingCells.dp)
    ) {
        items(cells) { cellData ->
            SirioTableVerticalCell(cellData = cellData)
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
        SirioTableVertical(
            cells = listOf(
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
            ),
        )
    }
}
//
// SirioTable.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.cell.SirioTableCellAvatar
import it.inps.sirio.ui.table.cell.SirioTableCellLink
import it.inps.sirio.ui.table.cell.SirioTableCellMultiIcons
import it.inps.sirio.ui.table.cell.SirioTableCellNumber
import it.inps.sirio.ui.table.cell.SirioTableCellNumberOnly
import it.inps.sirio.ui.table.cell.SirioTableCellTag
import it.inps.sirio.ui.table.cell.SirioTableCellText
import it.inps.sirio.ui.table.cell.SirioTableCellTextOnly
import it.inps.sirio.ui.titlebar.SirioTitleBar
import it.inps.sirio.ui.titlebar.SirioTitleBarItemData

@Composable
fun SirioTable(
    title: String,
    titleBarItems: List<SirioTitleBarItemData> = emptyList(),
    headers: List<SirioTableCellType.Header>,
    rows: List<SirioTableRowData>,
) {
    Column {
        SirioTitleBar(title = title, titleBarItems)
        Row {
            headers.indices.forEach { index ->
                val header = headers[index]
                Column(Modifier.weight(1f)) {
                    SirioTableHeader(
                        title = header.title,
                        size = header.size,
                        alignment = header.alignment,
                        scroll = header.scroll,
                        withCheckBox = header.withCheckBox,
                        checked = header.checked,
                        onCheckedChange = header.onCheckedChange,
                        onIconClick = header.onIconClick,
                    )
                    rows.forEach { row ->
                        if (index > row.cells.size - 1) {
                            SirioTableCellTextOnly(text = "", size = header.size)
                        } else {
                            CellTypeToCell(row.cells[index])
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CellTypeToCell(cell: SirioTableCellType) {
    when (cell) {
        is SirioTableCellType.Avatar -> SirioTableCellAvatar(cell)
        is SirioTableCellType.Link -> SirioTableCellLink(cell)
        is SirioTableCellType.MultiIcons -> SirioTableCellMultiIcons(cell)
        is SirioTableCellType.Number -> SirioTableCellNumber(cell)
        is SirioTableCellType.NumberOnly -> SirioTableCellNumberOnly(cell)
        is SirioTableCellType.Tag -> SirioTableCellTag(cell)
        is SirioTableCellType.Text -> SirioTableCellText(cell)
        is SirioTableCellType.TextOnly -> SirioTableCellTextOnly(cell)
        else -> {}
    }
}

@Preview
@Composable
private fun SirioTablePreview() {
    SirioTheme {
        SirioTable(
            title = "Title",
            titleBarItems = listOf(
                SirioTitleBarItemData(
                    icon = FaIcons.Filter,
                    text = "Mostra filtri",
                    contentDescription = null,
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    title = "id",
                    size = SirioTableContentSize.LARGE,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    title = "Nome",
                    size = SirioTableContentSize.LARGE,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    title = "Cognome",
                    size = SirioTableContentSize.LARGE,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = false,
                ),
            ),
            rows = listOf(
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Id 0",
                            SirioTableContentSize.LARGE,
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Nome 0",
                            SirioTableContentSize.LARGE,
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Cognome 0",
                            SirioTableContentSize.LARGE,
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Id 1",
                            SirioTableContentSize.LARGE,
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Nome 1",
                            SirioTableContentSize.LARGE,
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Cognome 1",
                            SirioTableContentSize.LARGE,
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Id 2",
                            SirioTableContentSize.LARGE,
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Nome 2",
                            SirioTableContentSize.LARGE,
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Cognome 2",
                            SirioTableContentSize.LARGE,
                        ),
                    )
                ),
            ),
        )
    }
}
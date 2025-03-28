//
// SirioTable.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableComponentBorderWidth
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
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border

/**
 * A composable function that displays a table with customizable headers and rows.
 *
 * This composable allows you to create a table with a title bar (optional), headers, and rows.
 * You can customize the appearance and behavior of the table using the provided parameters.
 *
 * @param headers A list of [SirioTableCellType.Header] objects representing the table headers.
 * @param rows A list of [SirioTableRowData] objects representing the table rows.
 * @param title An optional title for the table, displayed in a title bar above the table.
 * @param titleBarItems An optional list of [SirioTitleBarItemData] objects to display in the title bar.
 */
@Composable
fun SirioTable(
    headers: List<SirioTableCellType.Header>,
    rows: List<SirioTableRowData>,
    title: String? = null,
    titleBarItems: List<SirioTitleBarItemData> = emptyList(),
) {
    val tableBorder = Border(
        strokeWidth = tableComponentBorderWidth.dp,
        color = SirioTheme.colors.table.component.border,
    )
    Column {
        title?.let { SirioTitleBar(title = it, titleBarItems) }
        Column(
            Modifier
                .border(top = tableBorder)
                .border(start = tableBorder),
        ) {
            val rowCount = rows.maxOf { it.cells.size }
            Row(Modifier.height(IntrinsicSize.Max)) {
                headers.forEach { header ->
                    SirioTableHeader(
                        title = header.title,
                        size = header.size,
                        weight = header.weight,
                        alignment = header.alignment,
                        scroll = header.scroll,
                        withCheckBox = header.withCheckBox,
                        checked = header.checked,
                        onCheckedChange = header.onCheckedChange,
                        withSortIcon = header.withSortIcon,
                        onIconClick = header.onIconClick,
                    )
                }
            }
            rows.forEach { row ->
                Row(Modifier.height(IntrinsicSize.Max)) {
                    row.cells.forEachIndexed { columnIndex, cell ->
                        if (columnIndex > rowCount) {
                            SirioTableCellTextOnly(text = "", size = headers[columnIndex].size)
                        } else {
                            CellTypeToCell(cell = cell, weight = headers[columnIndex].weight)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.CellTypeToCell(cell: SirioTableCellType, weight: Float) {
    when (cell) {
        is SirioTableCellType.Avatar -> SirioTableCellAvatar(cell, weight)
        is SirioTableCellType.Link -> SirioTableCellLink(cell, weight)
        is SirioTableCellType.MultiIcons -> SirioTableCellMultiIcons(cell, weight)
        is SirioTableCellType.Number -> SirioTableCellNumber(cell, weight)
        is SirioTableCellType.NumberOnly -> SirioTableCellNumberOnly(cell, weight)
        is SirioTableCellType.Tag -> SirioTableCellTag(cell, weight)
        is SirioTableCellType.Text -> SirioTableCellText(cell, weight)
        is SirioTableCellType.TextOnly -> SirioTableCellTextOnly(cell, weight)
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
                    weight = 0.5f,
                    withCheckBox = false,
                    withSortIcon = false,
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
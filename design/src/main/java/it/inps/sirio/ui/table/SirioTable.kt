//
// SirioTable.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableComponentBorderWidth
import it.inps.sirio.theme.tablePaddingHorizontal
import it.inps.sirio.theme.tableScrollBorderWidth
import it.inps.sirio.ui.table.cell.SirioTableCellAction
import it.inps.sirio.ui.table.cell.SirioTableCellLink
import it.inps.sirio.ui.table.cell.SirioTableCellNumberOnly
import it.inps.sirio.ui.table.cell.SirioTableCellTag
import it.inps.sirio.ui.table.cell.SirioTableCellText
import it.inps.sirio.ui.table.cell.SirioTableCellTextOnly
import it.inps.sirio.ui.table.cell.SirioTableCellType
import it.inps.sirio.ui.table.cell.SirioTableContentAlignment
import it.inps.sirio.ui.table.cell.SirioTableContentSize
import it.inps.sirio.ui.table.header.SirioTableHeader
import it.inps.sirio.ui.table.header.SirioTableSortDirection
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.border
import it.inps.sirio.utils.ifElse

/**
 * A composable function that displays a table with customizable headers and rows.
 *
 * This composable allows you to create a table with a title bar (optional), headers, and rows.
 * You can customize the appearance and behavior of the table using the provided parameters.
 *
 * @param headers A list of [SirioTableCellType.Header] objects representing the table headers.
 * @param rows A list of [SirioTableRowData] objects representing the table rows.
 * @param size The size of the content within the table cells, affecting padding and text size.
 * @param themeMode The theme mode, either [SirioThemeMode.Light] or [SirioThemeMode.Dark].
 * @param modifier The modifier to be applied to the component.
 * @param title An optional title for the table, displayed above it.
 * @param actions An optional list of [SirioTableActionData] objects representing actions displayed below the title, on the right.
 * @param horizontalScroll A boolean to enable or disable horizontal scroll. Default is `true`.
 */
@Composable
fun SirioTable(
    headers: List<SirioTableCellType.Header>,
    rows: List<SirioTableRowData>,
    size: SirioTableContentSize,
    themeMode: SirioThemeMode,
    modifier: Modifier = Modifier,
    title: String? = null,
    actions: List<SirioTableActionData> = emptyList(),
    horizontalScroll: Boolean = false,
    alternateRows: Boolean = false,
    sort: SirioTableSort? = null,
    onSortChange: (SirioTableSort) -> Unit = {},
) {
    val tableBorder = Border(
        strokeWidth = tableComponentBorderWidth.dp,
        color = SirioTheme.colors.table.component.border,
    )
    val scrollBorder = Border(
        strokeWidth = tableScrollBorderWidth.dp,
        color = SirioTheme.colors.table.component.scrollIndicator,
    )
    val horizontalScrollState = rememberScrollState()
    val canScrollBackward by remember {
        derivedStateOf { horizontalScroll && horizontalScrollState.canScrollBackward }
    }
    val canScrollForward by remember {
        derivedStateOf { horizontalScroll && horizontalScrollState.canScrollForward }
    }
    //Main container
    Column(modifier = modifier) {
        title?.let { SirioTableTitle(it) }
        if (actions.isNotEmpty()) {
            SirioTableActions(actions = actions)
        }
        //Handle horizontal scroll, needed for table width
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .background(SirioTheme.colors.table.background)
                .padding(horizontal = tablePaddingHorizontal.dp)
                .border(top = tableBorder)
                .ifElse(
                    condition = canScrollBackward,
                    ifTrueModifier = Modifier.border(start = scrollBorder),
                )
                .ifElse(
                    condition = canScrollForward,
                    ifTrueModifier = Modifier.border(end = scrollBorder),
                )
                .ifElse(
                    condition = horizontalScroll,
                    ifTrueModifier = Modifier.horizontalScroll(horizontalScrollState),
                )
        ) {
            Column(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .then(
                        if (canScrollBackward || canScrollForward) {
                            Modifier.width(IntrinsicSize.Max)
                        } else {
                            Modifier.width(minWidth)
                        }
                    ),
            ) {
                val verticalScrollState = rememberScrollState()
                Column(
                    Modifier.verticalScroll(verticalScrollState),
                ) {
                    Headers(
                        headers = headers,
                        size = size,
                        themeMode = themeMode,
                        sort = sort,
                        onSortChange = onSortChange,
                    )
                    rows.forEachIndexed { rowIndex, row ->
                        val rowTheme = when {
                            alternateRows && rowIndex % 2 == 1 -> SirioThemeMode.Dark
                            else -> SirioThemeMode.Light
                        }
                        SirioTableRow(
                            row = row,
                            headers = headers,
                            size = size,
                            themeMode = rowTheme,
                            tableBorder = tableBorder,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Headers(
    headers: List<SirioTableCellType.Header>,
    size: SirioTableContentSize,
    themeMode: SirioThemeMode,
    sort: SirioTableSort?,
    onSortChange: (SirioTableSort) -> Unit,
) {
    Row(modifier = Modifier.height(IntrinsicSize.Max)) {
        headers.forEachIndexed { index, header ->
            val direction = when (sort?.columnIndex) {
                index -> sort.direction
                else -> SirioTableSortDirection.None
            }
            SirioTableHeader(
                data = header,
                size = size,
                themeMode = themeMode,
                showBorderEnd = index < headers.lastIndex,
                sortDirection = direction,
                onSortChange = { dir ->
                    if (header.sortable)
                        onSortChange(
                            SirioTableSort(
                                columnIndex = index,
                                direction = dir,
                            )
                        )
                },
            )
        }
    }
}

@Composable
private fun SirioTableRow(
    row: SirioTableRowData,
    headers: List<SirioTableCellType.Header>,
    size: SirioTableContentSize,
    themeMode: SirioThemeMode,
    tableBorder: Border,
) {
    val headersCount = headers.size
    Row(
        modifier = Modifier
            .border(start = tableBorder)
            .height(IntrinsicSize.Max)
    ) {
        repeat(headersCount) { columnIndex ->
            val cell = row.cells.getOrNull(columnIndex) ?: SirioTableCellType.TextOnly("")
            CellTypeToCell(
                cell = cell,
                size = size,
                weight = headers[columnIndex].weight,
                themeMode = themeMode,
            )
        }
    }
}

@Composable
private fun RowScope.CellTypeToCell(
    cell: SirioTableCellType,
    size: SirioTableContentSize,
    weight: Float,
    themeMode: SirioThemeMode,
) {
    when (cell) {
        is SirioTableCellType.Link ->
            SirioTableCellLink(data = cell, size = size, weight = weight, themeMode = themeMode)

        is SirioTableCellType.NumberOnly ->
            SirioTableCellNumberOnly(
                data = cell,
                size = size,
                weight = weight,
                themeMode = themeMode,
            )

        is SirioTableCellType.Tag ->
            SirioTableCellTag(data = cell, weight = weight, themeMode = themeMode)

        is SirioTableCellType.Text,
            -> SirioTableCellText(data = cell, size = size, weight = weight, themeMode = themeMode)

        is SirioTableCellType.TextOnly ->
            SirioTableCellTextOnly(data = cell, size = size, weight = weight, themeMode = themeMode)

        is SirioTableCellType.Action ->
            SirioTableCellAction(data = cell, size = size, weight = weight, themeMode = themeMode)

        is SirioTableCellType.Header -> {}
    }
}

data class SirioTableSort(
    val columnIndex: Int,
    val direction: SirioTableSortDirection,
)

@Preview
@Composable
private fun SirioTableSmallDarkPreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo tabella",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 1f,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
            ),
            size = SirioTableContentSize.Small,
            themeMode = SirioThemeMode.Dark,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link",
                            onLinkClick = {},
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "00",
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Lorem ipsum",
                        ),
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun SirioTableSmallLightPreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo tabella",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 1f,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
            ),
            size = SirioTableContentSize.Small,
            themeMode = SirioThemeMode.Light,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link",
                            onLinkClick = {},
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "00",
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Lorem ipsum",
                        ),
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun SirioTableSmallDarkExtraPreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo tabella",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Action #1",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Action #2",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Action #3",
                    action = {},
                ),
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 1f,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 2f,
                    withCheckBox = false,
                ),
            ),
            size = SirioTableContentSize.Small,
            themeMode = SirioThemeMode.Dark,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link",
                            onLinkClick = {},
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "00",
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Lorem ipsum sagittis egestas at",
                        ),
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun SirioTableLargeDarkOneActionPreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo tabella",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 0.6f,
                    withCheckBox = false,
                    sortable = false,
                ),
            ),
            size = SirioTableContentSize.Large,
            themeMode = SirioThemeMode.Dark,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Title",
                            checked = false,
                            onCheckedChange = {},
                        ),
                        SirioTableCellType.Tag(
                            text = "Label Tag",
                        ),
                        SirioTableCellType.Action(
                            icon = SirioIconSource.FaIcon(FaIcons.Eye),
                            onClick = {},
                        ),
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun SirioTableLargeLightMultiActionPreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo tabella",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 1f,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 0.6f,
                    withCheckBox = false,
                    sortable = false,
                ),
            ),
            size = SirioTableContentSize.Large,
            themeMode = SirioThemeMode.Light,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Title",
                            checked = false,
                            onCheckedChange = {},
                        ),
                        SirioTableCellType.Tag(
                            text = "Label Tag",
                        ),
                        SirioTableCellType.Action(
                            icon = SirioIconSource.FaIcon(FaIcons.Eye),
                            actions = listOf(
                                SirioTableActionData(
                                    text = "Action #1",
                                    action = {},
                                ),
                                SirioTableActionData(
                                    text = "Action #2",
                                    action = {},
                                ),
                                SirioTableActionData(
                                    text = "Action #3",
                                    action = {},
                                ),
                            ),
                            onClick = {},
                        ),
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun SirioTableLargeDarkOneActionLongePreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo della tabella molto molto molto molto lungo su due righe",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 1f,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Azioni",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 0.6f,
                    withCheckBox = false,
                    sortable = false,
                ),
            ),
            size = SirioTableContentSize.Large,
            themeMode = SirioThemeMode.Dark,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Vivamus at nunc non arcu",
                            checked = false,
                            onCheckedChange = {},
                        ),
                        SirioTableCellType.Tag(
                            text = "Tag con testo lungo",
                        ),
                        SirioTableCellType.Action(
                            icon = SirioIconSource.FaIcon(FaIcons.Eye),
                            onClick = {},
                        ),
                    )
                )
            },
        )
    }
}

@Preview
@Composable
private fun SirioTableAlternateRowPreview() {
    SirioTheme {
        SirioTable(
            title = "Titolo tabella",
            actions = listOf(
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                ),
                SirioTableActionData(
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    text = "Label",
                    action = {},
                )
            ),
            headers = listOf(
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                    sortable = true,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = false,
                ),
                SirioTableCellType.Header(
                    text = "Header",
                    alignment = SirioTableContentAlignment.Start,
                    weight = 0.6f,
                    withCheckBox = false,
                    sortable = false,
                ),
            ),
            size = SirioTableContentSize.Large,
            themeMode = SirioThemeMode.Dark,
            alternateRows = true,
            rows = List(8) {
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Title",
                            checked = false,
                            onCheckedChange = {},
                        ),
                        SirioTableCellType.Tag(
                            text = "Label Tag",
                        ),
                        SirioTableCellType.Action(
                            icon = SirioIconSource.FaIcon(FaIcons.Eye),
                            onClick = {},
                        ),
                    )
                )
            },
        )
    }
}

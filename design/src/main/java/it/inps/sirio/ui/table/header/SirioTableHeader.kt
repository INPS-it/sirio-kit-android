//
// SirioTableHeader.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalSmall
import it.inps.sirio.theme.tableHeaderPaddingVerticalLarge
import it.inps.sirio.theme.tableHeaderPaddingVerticalSmall
import it.inps.sirio.ui.table.cell.SirioTableCellType
import it.inps.sirio.ui.table.cell.SirioTableContentAlignment
import it.inps.sirio.ui.table.cell.SirioTableContentSize

@Composable
fun RowScope.SirioTableHeader(
    text: String,
    size: SirioTableContentSize,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    weight: Float = 1f,
    alignment: SirioTableContentAlignment = SirioTableContentAlignment.Start,
    withCheckBox: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    showBorderEnd: Boolean = true,
    sortable: Boolean = false,
    sortDirection: SirioTableSortDirection = SirioTableSortDirection.None,
    sortContentDescriptionAsc: String = "Ordine crescente",
    sortContentDescriptionDesc: String = "Ordine decrescente",
    sortContentDescriptionNone: String = "Nessun ordinamento",
    sortStateDescriptionAsc: String = "Ordinamento: crescente",
    sortStateDescriptionDesc: String = "Ordinamento: decrescente",
    sortStateDescriptionNone: String = "Ordinamento: nessuno",
    onSortChange: (SirioTableSortDirection) -> Unit = {},
) {
    val (verticalPadding, horizontalPadding) = remember(size) {
        when (size) {
            SirioTableContentSize.Small -> tableHeaderPaddingVerticalSmall to tableComponentPaddingHorizontalSmall
            SirioTableContentSize.Large -> tableHeaderPaddingVerticalLarge to tableComponentPaddingHorizontalLarge
        }
    }
    SirioTableHeaderCommon(
        text = text,
        verticalPadding = verticalPadding,
        horizontalPadding = horizontalPadding,
        alignment = alignment,
        themeMode = themeMode,
        weight = weight,
        withCheckBox = withCheckBox,
        checked = checked,
        onCheckedChange = onCheckedChange,
        showBorderEnd = showBorderEnd,
        sortable = sortable,
        sortDirection = sortDirection,
        sortContentDescriptionAsc = sortContentDescriptionAsc,
        sortContentDescriptionDesc = sortContentDescriptionDesc,
        sortContentDescriptionNone = sortContentDescriptionNone,
        sortStateDescriptionAsc = sortStateDescriptionAsc,
        sortStateDescriptionDesc = sortStateDescriptionDesc,
        sortStateDescriptionNone = sortStateDescriptionNone,
        onSortClick = onSortChange,
    )
}

@Composable
internal fun RowScope.SirioTableHeader(
    data: SirioTableCellType.Header,
    size: SirioTableContentSize,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showBorderEnd: Boolean = true,
    sortDirection: SirioTableSortDirection = SirioTableSortDirection.None,
    onSortChange: (SirioTableSortDirection) -> Unit = {},
) {
    SirioTableHeader(
        text = data.text,
        size = size,
        themeMode = themeMode,
        weight = data.weight,
        alignment = data.alignment,
        withCheckBox = data.withCheckBox,
        checked = data.checked,
        onCheckedChange = data.onCheckedChange,
        showBorderEnd = showBorderEnd,
        sortable = data.sortable,
        sortDirection = sortDirection,
        onSortChange = onSortChange,
    )
}

@Preview(showBackground = true)
@Composable
private fun SirioTableHeaderPreview() {
    SirioTheme {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Box(Modifier.weight(1f)) {
                DemoPreview()
            }
            Box(Modifier.weight(1f)) {
                DemoPreview(SirioThemeMode.Dark)
            }
        }
    }
}

@Composable
private fun DemoPreview(themeMode: SirioThemeMode = SirioThemeMode.Light) {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row {
            SirioTableHeader(
                text = "Header",
                size = SirioTableContentSize.Large,
                themeMode = themeMode,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onSortChange = {},
            )
        }
        Row {
            SirioTableHeader(
                text = "Header",
                size = SirioTableContentSize.Large,
                themeMode = themeMode,
                alignment = SirioTableContentAlignment.End,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onSortChange = {},
            )
        }
        Row {
            SirioTableHeader(
                text = "Header",
                size = SirioTableContentSize.Small,
                themeMode = themeMode,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onSortChange = {},
            )
        }
        Row {
            SirioTableHeader(
                text = "Header",
                size = SirioTableContentSize.Small,
                themeMode = themeMode,
                alignment = SirioTableContentAlignment.End,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onSortChange = {},
            )
        }
    }
}

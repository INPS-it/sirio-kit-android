//
// SirioTableCellText.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize

@Composable
fun RowScope.SirioTableCellText(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        SirioCheckboxCommon(
            checked = checked,
            text = text,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellText(data: SirioTableCellType.Text, weight: Float = 1f) {
    SirioTableCellText(
        text = data.text,
        size = data.size,
        weight = weight,
        scroll = data.scroll,
        checked = data.checked,
        onCheckedChange = data.onCheckedChange,
    )
}

@Preview
@Composable
private fun SirioTableCellTextPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title\nTitle",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    checked = true,
                    onCheckedChange = {}
                )
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    checked = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    checked = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                    checked = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                    checked = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                    checked = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                    checked = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                    checked = true,
                    onCheckedChange = {}
                )
            }
        }
    }
}
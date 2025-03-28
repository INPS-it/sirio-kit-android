//
// SirioTableCellNumber.kt
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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun RowScope.SirioTableCellNumber(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    checked: Boolean = false,
    scroll: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        Row(
            Modifier.toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = onCheckedChange
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioCheckboxCommon(
                checked = checked
            ) {}
            SirioTextCommon(
                text = text,
                modifier = Modifier.weight(1f),
                color = SirioTheme.colors.table.cell.number,
                textAlign = TextAlign.End,
                typography = SirioTheme.typography.table.cell.number,
            )
        }
    }
}

@Composable
internal fun RowScope.SirioTableCellNumber(data: SirioTableCellType.Number, weight: Float = 1f) {
    SirioTableCellNumber(
        text = data.text,
        weight = weight,
        size = data.size,
        checked = data.checked,
        scroll = data.scroll,
        onCheckedChange = data.onCheckedChange
    )
}

@Preview
@Composable
private fun SirioTableCellNumberPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00\n00",
                    size = SirioTableContentSize.LARGE,
                    checked = true,
                    scroll = false,
                    onCheckedChange = {}
                )
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.LARGE,
                    checked = true,
                    scroll = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.LARGE,
                    checked = true,
                    scroll = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.MEDIUM,
                    checked = true,
                    scroll = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.SMALL,
                    checked = true,
                    scroll = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.LARGE,
                    checked = true,
                    scroll = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.MEDIUM,
                    checked = true,
                    scroll = true,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumber(
                    text = "00",
                    size = SirioTableContentSize.SMALL,
                    checked = true,
                    scroll = true,
                    onCheckedChange = {}
                )
            }
        }
    }
}
//
// SirioTableCellNumber.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun SirioTableCellNumber(
    text: String,
    size: SirioTableContentSize,
    checked: Boolean = false,
    scroll: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
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
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                typography = SirioTheme.typography.table.cell.number,
            )
        }
    }
}

@Composable
internal fun SirioTableCellNumber(data: SirioTableCellType.Number) {
    SirioTableCellNumber(
        text = data.text,
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
            SirioTableCellNumber(
                text = "00",
                size = SirioTableContentSize.LARGE,
                checked = true,
                scroll = false,
                onCheckedChange = {}
            )
            SirioTableCellNumber(
                text = "00",
                size = SirioTableContentSize.MEDIUM,
                checked = true,
                scroll = false,
                onCheckedChange = {}
            )
            SirioTableCellNumber(
                text = "00",
                size = SirioTableContentSize.SMALL,
                checked = true,
                scroll = false,
                onCheckedChange = {}
            )
            SirioTableCellNumber(
                text = "00",
                size = SirioTableContentSize.LARGE,
                checked = true,
                scroll = true,
                onCheckedChange = {}
            )
            SirioTableCellNumber(
                text = "00",
                size = SirioTableContentSize.MEDIUM,
                checked = true,
                scroll = true,
                onCheckedChange = {}
            )
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
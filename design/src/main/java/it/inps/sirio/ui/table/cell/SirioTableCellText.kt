//
// SirioTableCellText.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize

@Composable
fun SirioTableCellText(
    text: String,
    size: SirioTableContentSize,
    scroll: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        SirioCheckboxCommon(
            checked = checked,
            text = text,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
internal fun SirioTableCellText(data: SirioTableCellType.Text) {
    SirioTableCellText(
        text = data.text,
        size = data.size,
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
            SirioTableCellText(
                text = "Title",
                size = SirioTableContentSize.LARGE,
                scroll = false,
                checked = true,
                onCheckedChange = {}
            )
            SirioTableCellText(
                text = "Title",
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
                checked = true,
                onCheckedChange = {}
            )
            SirioTableCellText(
                text = "Title",
                size = SirioTableContentSize.SMALL,
                scroll = false,
                checked = true,
                onCheckedChange = {}
            )
            SirioTableCellText(
                text = "Title",
                size = SirioTableContentSize.LARGE,
                scroll = true,
                checked = true,
                onCheckedChange = {}
            )
            SirioTableCellText(
                text = "Title",
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
                checked = true,
                onCheckedChange = {}
            )
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
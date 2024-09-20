//
// SirioTableCellTextOnly.kt
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
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun SirioTableCellTextOnly(
    text: String,
    size: SirioTableContentSize,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        SirioTextCommon(
            text = text,
            color = SirioTheme.colors.table.cell.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            typography = SirioTheme.typography.table.cell.text,
        )
    }
}

@Composable
internal fun SirioTableCellTextOnly(data: SirioTableCellType.TextOnly) {
    SirioTableCellTextOnly(
        text = data.text,
        size = data.size,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellTextOnlyPreview() {
    SirioTheme {
        Column {
            SirioTableCellTextOnly(
                text = "Lorem ipsum",
                size = SirioTableContentSize.LARGE,
                scroll = false,
            )
            SirioTableCellTextOnly(
                text = "Lorem ipsum",
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
            )
            SirioTableCellTextOnly(
                text = "Lorem ipsum",
                size = SirioTableContentSize.SMALL,
                scroll = false,
            )
            SirioTableCellTextOnly(
                text = "Lorem ipsum",
                size = SirioTableContentSize.LARGE,
                scroll = true,
            )
            SirioTableCellTextOnly(
                text = "Lorem ipsum",
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
            )
            SirioTableCellTextOnly(
                text = "Lorem ipsum",
                size = SirioTableContentSize.SMALL,
                scroll = true,
            )
        }
    }
}
//
// SirioTableCellNumberOnly.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun SirioTableCellNumberOnly(
    text: String,
    size: SirioTableContentSize,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        SirioTextCommon(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            color = SirioTheme.colors.table.cell.number,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            typography = SirioTheme.typography.table.cell.number,
        )
    }
}

@Composable
internal fun SirioTableCellNumberOnly(data: SirioTableCellType.NumberOnly) {
    SirioTableCellNumberOnly(
        text = data.text,
        size = data.size,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellNumberOnlyPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            val text = "00"
            SirioTableCellNumberOnly(
                text = text,
                size = SirioTableContentSize.LARGE,
                scroll = false,
            )
            SirioTableCellNumberOnly(
                text = text,
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
            )
            SirioTableCellNumberOnly(
                text = text,
                size = SirioTableContentSize.SMALL,
                scroll = false,
            )
            SirioTableCellNumberOnly(
                text = text,
                size = SirioTableContentSize.LARGE,
                scroll = true,
            )
            SirioTableCellNumberOnly(
                text = text,
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
            )
            SirioTableCellNumberOnly(
                text = text,
                size = SirioTableContentSize.SMALL,
                scroll = true,
            )
        }
    }
}
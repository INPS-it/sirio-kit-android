//
// SirioTableCellNumberOnly.kt
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun RowScope.SirioTableCellNumberOnly(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        SirioTextCommon(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            color = SirioTheme.colors.table.cell.number,
            textAlign = TextAlign.End,
            typography = SirioTheme.typography.table.cell.number,
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellNumberOnly(
    data: SirioTableCellType.NumberOnly,
    weight: Float = 1f,
) {
    SirioTableCellNumberOnly(
        text = data.text,
        size = data.size,
        weight = weight,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellNumberOnlyPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            val text = "00"
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = "$text\n$text",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                )
            }
        }
    }
}
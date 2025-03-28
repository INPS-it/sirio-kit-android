//
// SirioTableCellTextOnly.kt
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
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun RowScope.SirioTableCellTextOnly(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        SirioTextCommon(
            text = text,
            color = SirioTheme.colors.table.cell.title,
            typography = SirioTheme.typography.table.cell.text,
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellTextOnly(
    data: SirioTableCellType.TextOnly,
    weight: Float = 1f,
) {
    SirioTableCellTextOnly(
        text = data.text,
        size = data.size,
        weight = weight,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellTextOnlyPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum\nLorem ipsum",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                )
            }
        }
    }
}
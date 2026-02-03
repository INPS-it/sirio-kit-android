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
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun RowScope.SirioTableCellNumberOnly(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellCommon(
        size = size,
        weight = weight,
        themeMode = themeMode
    ) {
        SirioTextCommon(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            color = SirioTheme.colors.table.cell.number,
            textAlign = TextAlign.End,
            typography = SirioTheme.foundationTypography.labelNumberMdRegular,
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellNumberOnly(
    data: SirioTableCellType.NumberOnly,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellNumberOnly(
        text = data.text,
        size = size,
        weight = weight,
        themeMode = themeMode,
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
                    size = SirioTableContentSize.Large,
                )
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.Large,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.Large,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.Large,
                    themeMode = SirioThemeMode.Dark,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.Small,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellNumberOnly(
                    text = text,
                    size = SirioTableContentSize.Small,
                    themeMode = SirioThemeMode.Dark,
                )
            }
        }
    }
}

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
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun RowScope.SirioTableCellTextOnly(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellCommon(
        size = size,
        weight = weight,
        themeMode = themeMode,
    ) {
        SirioTextCommon(
            text = text,
            color = SirioTheme.colors.table.cell.title,
            typography = SirioTheme.foundationTypography.labelMdRegular,
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellTextOnly(
    data: SirioTableCellType.TextOnly,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellTextOnly(
        text = data.text,
        size = size,
        weight = weight,
        themeMode = themeMode,
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
                    size = SirioTableContentSize.Large,
                )
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.Large,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.Large,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.Large,
                    themeMode = SirioThemeMode.Dark,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.Small,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTextOnly(
                    text = "Lorem ipsum",
                    size = SirioTableContentSize.Small,
                    themeMode = SirioThemeMode.Dark,
                )
            }
        }
    }
}

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
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon

@Composable
fun RowScope.SirioTableCellText(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    checked: Boolean = false,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioTableCellCommon(
        size = size,
        weight = weight,
        themeMode = themeMode,
    ) {
        SirioCheckboxCommon(
            checked = checked,
            text = text,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellText(
    data: SirioTableCellType.Text,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellText(
        text = data.text,
        size = size,
        weight = weight,
        checked = data.checked,
        onCheckedChange = data.onCheckedChange,
        themeMode = themeMode,
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
                    size = SirioTableContentSize.Large,
                    checked = false,
                    onCheckedChange = {}
                )
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.Large,
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.Large,
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.Large,
                    checked = false,
                    themeMode = SirioThemeMode.Dark,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.Small,
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellText(
                    text = "Title",
                    size = SirioTableContentSize.Small,
                    checked = false,
                    themeMode = SirioThemeMode.Dark,
                    onCheckedChange = {}
                )
            }
        }
    }
}

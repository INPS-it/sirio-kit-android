//
// SirioTableCellTag.kt
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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.tag.SirioTag

@Composable
fun RowScope.SirioTableCellTag(
    text: String,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellCommon(
        size = SirioTableContentSize.Large,
        weight = weight,
        themeMode = themeMode,
    ) {
        SirioTag(text = text, color = SirioTheme.colors.table.cell.tag)
    }
}

@Composable
internal fun RowScope.SirioTableCellTag(
    data: SirioTableCellType.Tag,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellTag(
        text = data.text,
        weight = weight,
        themeMode = themeMode,
    )
}

@Preview
@Composable
private fun SirioTableCellTagPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            val text = "Label Tag"
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(text = text)
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    themeMode = SirioThemeMode.Dark,
                )
            }
        }
    }
}

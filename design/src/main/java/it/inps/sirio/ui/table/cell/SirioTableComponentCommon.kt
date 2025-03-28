//
// SirioTableComponentCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableComponentBorderWidth
import it.inps.sirio.theme.tableComponentScrollIndicatorWidth
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border

@Composable
internal fun RowScope.SirioTableComponentCommon(
    weight: Float = 1f,
    scroll: Boolean = false,
    content: @Composable () -> Unit,
) {
    val tableBorder = Border(
        strokeWidth = tableComponentBorderWidth.dp,
        color = SirioTheme.colors.table.component.border
    )
    val scrollBorder = Border(
        strokeWidth = tableComponentScrollIndicatorWidth.dp,
        color = SirioTheme.colors.table.component.scrollIndicator
    )
    val endBorder = if (scroll) scrollBorder else tableBorder
    Box(
        Modifier
            .weight(weight)
            .border(bottom = tableBorder)
            .border(end = endBorder),
        contentAlignment = Alignment.CenterStart,
    ) {
        content()
    }
}

@Keep
data class SirioTableComponentColors(
    val border: Color,
    val scrollIndicator: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableComponentColors(
            border = Color.Unspecified,
            scrollIndicator = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun SirioTableComponentCommonPreview() {
    SirioTheme {
        Column {
            Row {
                SirioTableComponentCommon {
                    SirioTextCommon(text = "Test")
                }
            }
            Row {
                SirioTableComponentCommon(
                    scroll = true
                ) {
                    SirioTextCommon(text = "Test")
                }
            }
        }
    }
}
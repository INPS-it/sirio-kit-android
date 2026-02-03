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
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableComponentBorderWidth
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border
import it.inps.sirio.utils.ifElse

@Composable
internal fun RowScope.SirioTableComponentCommon(
    borderColor: Color,
    weight: Float = 1f,
    showBorderEnd: Boolean = true,
    content: @Composable () -> Unit,
) {
    val tableBorder = Border(
        strokeWidth = tableComponentBorderWidth.dp,
        color = borderColor,
    )
    Box(
        Modifier
            .weight(weight)
            .border(bottom = tableBorder)
            .ifElse(
                condition = showBorderEnd,
                ifTrueModifier = Modifier.border(end = tableBorder),
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        content()
    }
}

enum class SirioTableContentSize {
    Small,
    Large,
}

enum class SirioTableContentAlignment {
    Start,
    End,
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

internal val tableComponentLightColor = SirioTableComponentColors(
    border = FoundationColor.colorAliasBackgroundColorPrimaryLight60,
    scrollIndicator = FoundationColor.colorAliasInteractiveAccentDefault,
)

internal val tableComponentDarkColor = tableComponentLightColor

@Preview
@Composable
private fun SirioTableComponentCommonPreview() {
    SirioTheme {
        Column {
            Row {
                SirioTableComponentCommon(borderColor = SirioTheme.colors.table.component.border) {
                    SirioTextCommon(text = "Test")
                }
            }
        }
    }
}

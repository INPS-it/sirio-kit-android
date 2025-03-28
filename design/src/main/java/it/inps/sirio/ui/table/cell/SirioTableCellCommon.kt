//
// SirioTableCellCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableCellPaddingVerticalExtraSmall
import it.inps.sirio.theme.tableCellPaddingVerticalLarge
import it.inps.sirio.theme.tableCellPaddingVerticalMedium
import it.inps.sirio.theme.tableCellPaddingVerticalSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalExtraSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalMedium
import it.inps.sirio.theme.tableComponentPaddingHorizontalSmall
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
internal fun RowScope.SirioTableCellCommon(
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
    content: @Composable () -> Unit,
) {
    val verticalPadding = remember(size) {
        when (size) {
            SirioTableContentSize.EXTRASMALL -> tableCellPaddingVerticalExtraSmall
            SirioTableContentSize.SMALL -> tableCellPaddingVerticalSmall
            SirioTableContentSize.MEDIUM -> tableCellPaddingVerticalMedium
            SirioTableContentSize.LARGE -> tableCellPaddingVerticalLarge
        }
    }
    val horizontalPadding = remember(size) {
        when (size) {
            SirioTableContentSize.EXTRASMALL -> tableComponentPaddingHorizontalExtraSmall
            SirioTableContentSize.SMALL -> tableComponentPaddingHorizontalSmall
            SirioTableContentSize.MEDIUM -> tableComponentPaddingHorizontalMedium
            SirioTableContentSize.LARGE -> tableComponentPaddingHorizontalLarge
        }
    }
    SirioTableComponentCommon(weight = weight, scroll = scroll) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = SirioTheme.colors.table.cell.background,
        ) {
            Box(
                modifier = Modifier.padding(
                    horizontal = horizontalPadding.dp,
                    vertical = verticalPadding.dp
                ),
                contentAlignment = Alignment.CenterStart,
            ) {
                content()
            }
        }
    }
}

@Keep
data class SirioTableCellColors(
    val avatarSubtitle: Color,
    val avatarTitle: Color,
    val background: Color,
    val icon: Color,
    val link: Color,
    val number: Color,
    val title: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableCellColors(
            avatarSubtitle = Color.Unspecified,
            avatarTitle = Color.Unspecified,
            background = Color.Unspecified,
            icon = Color.Unspecified,
            link = Color.Unspecified,
            number = Color.Unspecified,
            title = Color.Unspecified,
        )
    }
}

@Keep
data class SirioTableCellTypography(
    val text: TextStyle,
    val number: TextStyle,
    val link: TextStyle,
    val avatarTitle: TextStyle,
    val avatarSubtitle: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioTableCellTypography(
            text = TextStyle.Default,
            number = TextStyle.Default,
            link = TextStyle.Default,
            avatarTitle = TextStyle.Default,
            avatarSubtitle = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioTableCellCommonPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellCommon(
                    size = SirioTableContentSize.LARGE,
                ) {
                    SirioTextCommon(text = "Test", Modifier.weight(1f))
                }
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellCommon(
                    size = SirioTableContentSize.LARGE,
                    scroll = true
                ) {
                    SirioTextCommon(text = "Test")
                }
            }
        }
    }
}
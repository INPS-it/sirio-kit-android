//
// SirioTableCellCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import it.inps.sirio.theme.tableComponentHeightExtraSmall
import it.inps.sirio.theme.tableComponentHeightLarge
import it.inps.sirio.theme.tableComponentHeightMedium
import it.inps.sirio.theme.tableComponentHeightSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalExtraSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalMedium
import it.inps.sirio.theme.tableComponentPaddingHorizontalSmall
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
internal fun SirioTableCellCommon(
    size: SirioTableContentSize,
    scroll: Boolean = false,
    content: @Composable () -> Unit,
) {
    val height = remember(size) {
        when (size) {
            SirioTableContentSize.EXTRASMALL -> tableComponentHeightExtraSmall
            SirioTableContentSize.SMALL -> tableComponentHeightSmall
            SirioTableContentSize.MEDIUM -> tableComponentHeightMedium
            SirioTableContentSize.LARGE -> tableComponentHeightLarge
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
    SirioTableComponentCommon(scroll = scroll) {
        Surface(
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth(),
            color = SirioTheme.colors.table.cell.background,
        ) {
            Box(
                modifier = Modifier.padding(horizontal = horizontalPadding.dp),
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
            SirioTableCellCommon(
                size = SirioTableContentSize.LARGE,
            ) {
                SirioTextCommon(text = "Test", Modifier.weight(1f))
            }
            SirioTableCellCommon(
                size = SirioTableContentSize.LARGE,
                scroll = true
            ) {
                SirioTextCommon(text = "Test")
            }
        }
    }
}
//
// SirioTableCellCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableCellPaddingVerticalLarge
import it.inps.sirio.theme.tableCellPaddingVerticalSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalSmall
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.ui.tag.SirioTagColor
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIconSource

@Composable
internal fun RowScope.SirioTableCellCommon(
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = null,
    alignment: Alignment = Alignment.CenterStart,
    content: @Composable () -> Unit,
) {
    val verticalPadding = remember(size) {
        when (size) {
            SirioTableContentSize.Small -> tableCellPaddingVerticalSmall
            SirioTableContentSize.Large -> tableCellPaddingVerticalLarge
        }
    }
    val horizontalPadding = remember(size) {
        when (size) {
            SirioTableContentSize.Small -> tableComponentPaddingHorizontalSmall
            SirioTableContentSize.Large -> tableComponentPaddingHorizontalLarge
        }
    }
    SirioTheme(themeMode) {
        SirioTableComponentCommon(
            borderColor = SirioTheme.colors.table.cell.border,
            weight = weight,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SirioTheme.colors.table.cell.background)
                    .padding(
                        horizontal = horizontalPadding.dp,
                        vertical = verticalPadding.dp
                    )
                ,
                contentAlignment = alignment,
            ) {
                content()
            }
        }
    }
}

sealed class SirioTableCellType {
    data class Header(
        val text: String,
        val alignment: SirioTableContentAlignment,
        val weight: Float = 1.0f,
        val withCheckBox: Boolean = false,
        val checked: Boolean = false,
        val onCheckedChange: (Boolean) -> Unit = {},
        val sortable: Boolean = false,
    ) : SirioTableCellType()

    data class Action(
        val icon: SirioIconSource? = null,
        val actions: List<SirioTableActionData> = emptyList(),
        val onClick: () -> Unit,
    ) : SirioTableCellType()

    data class Link(
        val text: String,
        val onLinkClick: () -> Unit,
    ) : SirioTableCellType()

    data class NumberOnly(
        val text: String,
    ) : SirioTableCellType()

    data class Tag(
        val text: String,
    ) : SirioTableCellType()

    data class Text(
        val text: String,
        val checked: Boolean = false,
        val onCheckedChange: (Boolean) -> Unit = {},
    ) : SirioTableCellType()

    data class TextOnly(
        val text: String,
    ) : SirioTableCellType()
}

@Keep
data class SirioTableCellColors(
    val border: Color,
    val background: Color,
    val action: SirioButtonHierarchy,
    val link: Color,
    val number: Color,
    val tag: SirioTagColor,
    val title: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableCellColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            action = SirioButtonHierarchy.GhostLight,
            link = Color.Unspecified,
            number = Color.Unspecified,
            tag = SirioTagColor.Light,
            title = Color.Unspecified,
        )
    }
}

internal val tableCellLightColors = SirioTableCellColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    border = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    action = SirioButtonHierarchy.GhostLight,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
    number = FoundationColor.colorAliasTextColorSecondaryDark100,
    tag = SirioTagColor.Light,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
)

internal val tableCellDarkColors = SirioTableCellColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    border = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    action = SirioButtonHierarchy.GhostLight,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
    number = FoundationColor.colorAliasTextColorSecondaryDark100,
    tag = SirioTagColor.Light,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
)


@Preview
@Composable
private fun SirioTableCellCommonPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellCommon(
                    size = SirioTableContentSize.Large,
                ) {
                    SirioTextCommon(text = "Test", Modifier.weight(1f))
                }
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellCommon(size = SirioTableContentSize.Large) {
                    SirioTextCommon(text = "Test")
                }
            }
        }
    }
}

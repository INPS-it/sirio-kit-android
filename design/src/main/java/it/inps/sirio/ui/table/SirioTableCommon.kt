//
// SirioTableCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import com.guru.fontawesomecomposelib.FaIconType
import it.inps.sirio.ui.table.cell.SirioTableCellColors
import it.inps.sirio.ui.table.cell.SirioTableCellTypography
import it.inps.sirio.ui.table.cell.SirioTableComponentColors
import it.inps.sirio.ui.table.drawer.SirioTableDrawerColors
import it.inps.sirio.ui.table.drawer.SirioTableDrawerTypography
import it.inps.sirio.ui.table.vertical.SirioTableVerticalColors
import it.inps.sirio.ui.table.vertical.SirioTableVerticalTypography
import it.inps.sirio.ui.tag.TagType

sealed class SirioTableCellType {
    data class Header(
        val title: String,
        val size: SirioTableContentSize,
        val alignment: SirioTableContentAlignment,
        val weight: Float = 1.0f,
        val scroll: Boolean = false,
        val withCheckBox: Boolean = false,
        val checked: Boolean = false,
        val onCheckedChange: (Boolean) -> Unit = {},
        val withSortIcon: Boolean = true,
        val onIconClick: () -> Unit = {},
    ) : SirioTableCellType()

    data class Avatar(
        val icon: FaIconType,
        val title: String,
        val subtitle: String,
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
    ) : SirioTableCellType()

    data class Link(
        val text: String,
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
        val onLinkClick: () -> Unit,
    ) : SirioTableCellType()

    data class MultiIcons(
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
        val iconsData: List<SirioTableIconData>,
    ) : SirioTableCellType()

    data class Number(
        val text: String,
        val size: SirioTableContentSize,
        val checked: Boolean = false,
        val scroll: Boolean = false,
        val onCheckedChange: (Boolean) -> Unit = {},
    ) : SirioTableCellType()

    data class NumberOnly(
        val text: String,
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
    ) : SirioTableCellType()

    data class Tag(
        val text: String,
        val tagType: TagType,
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
    ) : SirioTableCellType()

    data class Text(
        val text: String,
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
        val checked: Boolean = false,
        val onCheckedChange: (Boolean) -> Unit = {},
    ) : SirioTableCellType()

    data class TextOnly(
        val text: String,
        val size: SirioTableContentSize,
        val scroll: Boolean = false,
    ) : SirioTableCellType()
}

@Keep
data class SirioTableColors(
    val cell: SirioTableCellColors,
    val component: SirioTableComponentColors,
    val drawer: SirioTableDrawerColors,
    val header: SirioTableHeaderColors,
    val vertical: SirioTableVerticalColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableColors(
            cell = SirioTableCellColors.Unspecified,
            component = SirioTableComponentColors.Unspecified,
            drawer = SirioTableDrawerColors.Unspecified,
            header = SirioTableHeaderColors.Unspecified,
            vertical = SirioTableVerticalColors.Unspecified,
        )
    }
}

@Keep
data class SirioTableTypography(
    val cell: SirioTableCellTypography,
    val drawer: SirioTableDrawerTypography,
    val header: SirioTableHeaderTypography,
    val vertical: SirioTableVerticalTypography,
) {
    companion object {
        @Stable
        val Default = SirioTableTypography(
            cell = SirioTableCellTypography.Default,
            drawer = SirioTableDrawerTypography.Default,
            header = SirioTableHeaderTypography.Default,
            vertical = SirioTableVerticalTypography.Default,
        )
    }
}

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
import androidx.compose.ui.graphics.Color
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.ui.table.card.SirioTableCardColors
import it.inps.sirio.ui.table.card.SirioTableCardSortColors
import it.inps.sirio.ui.table.card.tableCardDarkColors
import it.inps.sirio.ui.table.card.tableCardLightColors
import it.inps.sirio.ui.table.card.tableCardSortDarkColors
import it.inps.sirio.ui.table.card.tableCardSortLightColors
import it.inps.sirio.ui.table.cell.SirioTableCellColors
import it.inps.sirio.ui.table.cell.SirioTableComponentColors
import it.inps.sirio.ui.table.cell.tableCellDarkColors
import it.inps.sirio.ui.table.cell.tableCellLightColors
import it.inps.sirio.ui.table.cell.tableComponentDarkColor
import it.inps.sirio.ui.table.cell.tableComponentLightColor
import it.inps.sirio.ui.table.drawer.SirioTableDrawerColors
import it.inps.sirio.ui.table.drawer.tableDrawerDarkColors
import it.inps.sirio.ui.table.drawer.tableDrawerLightColors
import it.inps.sirio.ui.table.header.SirioTableHeaderColors
import it.inps.sirio.ui.table.header.tableHeaderDarkColors
import it.inps.sirio.ui.table.header.tableHeaderLightColors

@Keep
data class SirioTableColors(
    val cell: SirioTableCellColors,
    val card: SirioTableCardColors,
    val component: SirioTableComponentColors,
    val sort: SirioTableCardSortColors,
    val drawer: SirioTableDrawerColors,
    val header: SirioTableHeaderColors,
    val background: Color,
    val title: Color,
    val action: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableColors(
            cell = SirioTableCellColors.Unspecified,
            card = SirioTableCardColors.Unspecified,
            component = SirioTableComponentColors.Unspecified,
            sort = SirioTableCardSortColors.Unspecified,
            drawer = SirioTableDrawerColors.Unspecified,
            header = SirioTableHeaderColors.Unspecified,
            background = Color.Unspecified,
            title = Color.Unspecified,
            action = Color.Unspecified,
        )
    }
}

internal val tableLightColors = SirioTableColors(
    cell = tableCellLightColors,
    card = tableCardLightColors,
    component = tableComponentLightColor,
    sort = tableCardSortLightColors,
    drawer = tableDrawerLightColors,
    header = tableHeaderLightColors,
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    title = FoundationColor.colorAliasTextColorPrimaryDark110,
    action = FoundationColor.colorAliasInteractivePrimaryDefault,
)
internal val tableDarkColors = SirioTableColors(
    cell = tableCellDarkColors,
    card = tableCardDarkColors,
    component = tableComponentDarkColor,
    sort = tableCardSortDarkColors,
    drawer = tableDrawerDarkColors,
    header = tableHeaderDarkColors,
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    title = FoundationColor.colorAliasTextColorPrimaryDark110,
    action = FoundationColor.colorAliasInteractivePrimaryDefault,
)

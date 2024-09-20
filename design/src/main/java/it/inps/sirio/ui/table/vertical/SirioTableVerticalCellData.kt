//
// SirioTableVerticalCellData.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.vertical

import it.inps.sirio.ui.table.SirioTableIconData

data class SirioTableVerticalCellData(
    val items: List<SirioTableVerticalCellItemData>,
    val icons: List<SirioTableIconData> = emptyList(),
)
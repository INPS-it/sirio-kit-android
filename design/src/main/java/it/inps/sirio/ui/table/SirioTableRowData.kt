//
// SirioTableRowData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import it.inps.sirio.ui.table.cell.SirioTableCellType

data class SirioTableRowData(
    val cells: List<SirioTableCellType>,
)

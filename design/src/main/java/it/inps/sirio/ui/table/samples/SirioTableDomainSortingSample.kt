//
// SirioTableDomainSortingSample.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.samples

import it.inps.sirio.ui.table.SirioTableSort
import it.inps.sirio.ui.table.SirioTableSortKey
import it.inps.sirio.ui.table.header.SirioTableSortDirection
import it.inps.sirio.ui.table.sortedForSirioTable
import java.util.Locale

@Suppress("UNUSED_VARIABLE")
fun SirioTableDomainSortingSample() {
    data class Item(val title: String, val number: Int)

    val items = listOf(
        Item(title = "Mario", number = 10),
        Item(title = "Anna", number = 2),
    )

    val sort = SirioTableSort(columnIndex = 0, direction = SirioTableSortDirection.Asc)

    val sorted = items.sortedForSirioTable(sort = sort, locale = Locale.ITALIAN) { item, columnIndex, _ ->
        when (columnIndex) {
            0 -> SirioTableSortKey.StringKey(item.title)
            1 -> SirioTableSortKey.NumberKey(item.number.toDouble())
            else -> null
        }
    }

    // Map `sorted` to SirioTableRowData before passing to SirioTable(...)
}

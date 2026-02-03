//
// SirioTableSortKey.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import it.inps.sirio.ui.table.header.SirioTableSortDirection
import java.text.Collator
import java.util.Locale

/**
 * A comparable key used for table sorting.
 *
 * Consumers should convert domain values to a [SirioTableSortKey] so sorting can be applied
 * consistently across columns:
 * - [NumberKey] for numeric values (compared numerically)
 * - [StringKey] for textual values (compared with a locale-aware [Collator])
 */
sealed class SirioTableSortKey : Comparable<SirioTableSortKey> {

    data class NumberKey(val value: Double) : SirioTableSortKey()
    data class StringKey(val value: String) : SirioTableSortKey()

    override fun compareTo(other: SirioTableSortKey): Int = when (this) {
        is NumberKey if other is NumberKey -> value.compareTo(other.value)
        is StringKey if other is StringKey -> value.compareTo(other.value)
        // Deterministic cross-type ordering (numbers before strings)
        is NumberKey if other is StringKey -> -1
        is StringKey if other is NumberKey -> 1
        else -> 0
    }
}

/**
 * Sorts a list of **domain items** according to the current [SirioTableSort].
 *
 * This is the **recommended and supported** way to apply sorting when using [SirioTable]:
 * sort your domain data first, then map the sorted items to [SirioTableRowData].
 *
 * Sorting on UI row data is intentionally **not supported** to avoid fragile behavior
 * (e.g., sorting on formatted strings, non-comparable cell types, locale/currency issues).
 *
 * ### Behavior
 * - If [SirioTableSort.direction] is [SirioTableSortDirection.None], the original list is returned
 *   unchanged (no copy is created).
 * - Sorting is **single-column by design** (the active column is [SirioTableSort.columnIndex]).
 * - Sorting is **stable**.
 * - Items with a missing key (`null`) are always placed **last** (both in ascending and descending order).
 *
 * ### Custom sorting
 * Provide [keyForColumn] to map an item and a column index to a comparable [SirioTableSortKey].
 * For example:
 * - return [SirioTableSortKey.NumberKey] for numeric columns
 * - return [SirioTableSortKey.StringKey] for text columns
 * - return `null` to mark an item as non-sortable for that column (it will go last)
 *
 * @param sort Current table sort. Use direction [SirioTableSortDirection.None] to disable sorting.
 * @param locale Locale used for locale-aware string comparison.
 * @param keyForColumn Extracts the sort key for the given item and active column.
 *
 * @return A sorted list of items, or the original list if sorting is disabled.
 *
 * @sample it.inps.sirio.ui.table.samples.SirioTableDomainSortingSample
 */
fun <T> List<T>.sortedForSirioTable(
    sort: SirioTableSort,
    locale: Locale = Locale.getDefault(),
    keyForColumn: (item: T, columnIndex: Int, locale: Locale) -> SirioTableSortKey?,
): List<T> {
    if (sort.direction == SirioTableSortDirection.None) return this

    val collator = Collator.getInstance(locale).apply {
        // PRIMARY: ignores case/accents reasonably for UI sorting
        strength = Collator.PRIMARY
    }

    val comparator = Comparator<T> { a, b ->
        val ka = keyForColumn(a, sort.columnIndex, locale)
        val kb = keyForColumn(b, sort.columnIndex, locale)

        // Nulls last
        if (ka == null && kb == null) return@Comparator 0
        if (ka == null) return@Comparator 1
        if (kb == null) return@Comparator -1

        val base = when {
            ka is SirioTableSortKey.StringKey && kb is SirioTableSortKey.StringKey ->
                collator.compare(ka.value, kb.value)
            else ->
                ka.compareTo(kb)
        }

        when (sort.direction) {
            SirioTableSortDirection.Asc -> base
            SirioTableSortDirection.Desc -> -base
            SirioTableSortDirection.None -> 0
        }
    }

    return this.sortedWith(comparator)
}

//
// SirioFilterState.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import it.inps.sirio.ui.filter.items.SirioFilterSelectedChipData

/**
 * Holds and manages the state for a [SirioFilterDrawer].
 *
 * This class represents the current state of the filter drawer, including
 * the list of filters and their individual values (checkboxes, toggles, chips, etc.).
 *
 * It is designed to be used outside of the UI layer — typically by the caller of
 * [SirioFilterDrawer] — and allows the component to remain stateless.
 *
 * Example usage:
 * ```
 * val filterState = rememberSirioFilterState(initialFilters)
 *
 * SirioFilterDrawer(
 *     state = filterState,
 *     onClose = { /* chiudi drawer */ },
 *     onRemoveFilters = { viewModel.clearFilters() },
 *     onApplyFilters = { viewModel.updateFilters(it) },
 * )
 * ```
 *
 * @param initialFilters The initial list of [SirioFilterDrawerType] items used to populate the drawer.
 */
class SirioFilterState(
    initialFilters: List<SirioFilterDrawerType>,
) {
    /**
     * The current list of filters displayed in the drawer.
     */
    var filters by mutableStateOf(initialFilters)
        private set

    /**
     * Updates a single filter in the current list.
     *
     * @param updated The filter item that has changed.
     */
    fun update(updated: SirioFilterDrawerType) {
        filters = filters.map { if (it.id == updated.id) updated else it }
    }

    /**
     * Replaces all filters with a new list.
     *
     * Useful when you want to restore an external state or reset to a saved configuration.
     */
    fun setAll(newFilters: List<SirioFilterDrawerType>) {
        filters = newFilters
    }

    /**
     * Clears the values of all filters, restoring them to their default “unselected” state.
     */
    fun clear() {
        filters = filters.map { it.reset() }
    }

    fun resetFilter(id: String) {
        filters = filters.map { if (it.id == id) it.reset() else it }
    }

    /**
     * Crea una copia completa e indipendente di questo stato
     */
    fun copy(): SirioFilterState = SirioFilterState(filters.map { it.copyDeep() })

    fun toPayloadMap(): Map<String, Any> =
        filters.filter { it.isActive() }
            .mapNotNull { filter ->
                val value = when (filter) {
                    is SirioFilterDrawerType.Chip -> filter.selectedValues.toList()
                    is SirioFilterDrawerType.Select -> filter.selectedValue
                    is SirioFilterDrawerType.RadioGroup -> filter.selectedValue
                    is SirioFilterDrawerType.Checkbox -> filter.checked
                    is SirioFilterDrawerType.Toggle -> filter.checked
                    is SirioFilterDrawerType.Date -> filter.selectedDate
                    else -> null
                }
                value?.let { filter.id to it }
            }
            .toMap()
}

fun SirioFilterState.activeChips(
    onStateChanged: (List<SirioFilterDrawerType>) -> Unit,
): List<SirioFilterSelectedChipData> =
    filters.flatMap { filter ->
        when (filter) {
            is SirioFilterDrawerType.Chip ->
                filter.selectedValues.map { selected ->
                    SirioFilterSelectedChipData(
                        key = "${filter.id}:$selected",
                        text = selected,
                        onClose = {
                            update(filter.copy(selectedValues = filter.selectedValues - selected))
                            onStateChanged(filters)
                        }
                    )
                }

            is SirioFilterDrawerType.Select ->
                filter.selectedValue?.let { selected ->
                    listOf(
                        SirioFilterSelectedChipData(
                            key = filter.id,
                            text = filter.getDisplayValue() ?: selected, // include label se presente
                            onClose = {
                                update(filter.copy(selectedValue = null))
                                onStateChanged(filters)
                            }
                        )
                    )
                } ?: emptyList()

            is SirioFilterDrawerType.Date ->
                filter.selectedDate?.let { _ ->
                    listOf(
                        SirioFilterSelectedChipData(
                            key = filter.id,
                            text = filter.getDisplayValue() ?: "",
                            onClose = {
                                update(filter.copy(selectedDate = null))
                                onStateChanged(filters)
                            }
                        )
                    )
                } ?: emptyList()

            is SirioFilterDrawerType.RadioGroup ->
                filter.selectedValue?.let { selected ->
                    listOf(
                        SirioFilterSelectedChipData(
                            key = filter.id,
                            text = selected,
                            onClose = {
                                update(filter.copy(selectedValue = null))
                                onStateChanged(filters)
                            }
                        )
                    )
                } ?: emptyList()

            is SirioFilterDrawerType.Checkbox ->
                if (filter.checked)
                    listOf(
                        SirioFilterSelectedChipData(
                            key = filter.id,
                            text = filter.text ?: "Selezionato",
                            onClose = {
                                update(filter.copy(checked = false))
                                onStateChanged(filters)
                            }
                        )
                    )
                else emptyList()

            is SirioFilterDrawerType.Toggle ->
                if (filter.checked)
                    listOf(
                        SirioFilterSelectedChipData(
                            key = filter.id,
                            text = filter.text ?: "Attivo",
                            onClose = {
                                update(filter.copy(checked = false))
                                onStateChanged(filters)
                            }
                        )
                    )
                else emptyList()

            else -> emptyList()
        }
    }


/**
 * Creates and remembers a [SirioFilterState] across recompositions.
 *
 * This should be used to create the state instance that you pass to [SirioFilterDrawer].
 *
 * Example:
 * ```
 * val filterState = rememberSirioFilterState(initialFilters)
 * SirioFilterDrawer(state = filterState, ...)
 * ```
 *
 * @param initialFilters The initial list of [SirioFilterDrawerType] items.
 */
@Composable
fun rememberSirioFilterState(
    initialFilters: List<SirioFilterDrawerType>,
): SirioFilterState {
    return rememberSaveable(
        saver = listSaver(
            save = { it.filters },
            restore = { SirioFilterState(it) }
        )
    ) {
        SirioFilterState(initialFilters)
    }
}

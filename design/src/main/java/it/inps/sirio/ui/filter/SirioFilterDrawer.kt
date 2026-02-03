//
// SirioFilterDrawer.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.filter.items.SirioFilterChips
import it.inps.sirio.ui.filter.items.SirioFilterDrawerButton
import it.inps.sirio.ui.filter.items.SirioFilterDrawerCheckbox
import it.inps.sirio.ui.filter.items.SirioFilterDrawerDate
import it.inps.sirio.ui.filter.items.SirioFilterDrawerHeader
import it.inps.sirio.ui.filter.items.SirioFilterDrawerRadioGroup
import it.inps.sirio.ui.filter.items.SirioFilterDrawerSelect
import it.inps.sirio.ui.filter.items.SirioFilterDrawerTitle
import it.inps.sirio.ui.filter.items.SirioFilterDrawerToggle

/**
 * Drawer composable displaying a list of filters and action buttons.
 *
 * It is fully stateless — the current state is managed externally through [SirioFilterState].
 */
@Composable
fun SirioFilterDrawer(
    state: SirioFilterState,
    onClose: () -> Unit,
    onRemoveFilters: (() -> Unit)? = null,
    onApplyFilters: (List<SirioFilterDrawerType>) -> Unit,
    title: String = "Filtri",
    closeContentDescription: String? = null,
) {
    var tempState by remember(state.filters) { mutableStateOf(state.copy()) }

    Column(
        modifier = Modifier
            .background(SirioTheme.colors.filter.background)
    ) {
        SirioFilterDrawerHeader(
            title = title,
            closeContentDescription = closeContentDescription,
            onClose = {
                onClose()
                tempState = state.copy()
            },
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tempState.filters, key = { it.id }) { filter ->
                FilterDrawerTypeToItem(
                    item = filter,
                    onValueChanged = { updated -> tempState.update(updated) }
                )
            }
        }
        SirioFilterDrawerButton(
            leftButtonText = "Elimina filtri",
            rightButtonText = "Applica filtri",
            onLeftButtonClick = {
                tempState.clear()
                onRemoveFilters?.invoke()
            },
            onRightButtonClick = {
                state.setAll(tempState.filters)
                onApplyFilters(tempState.filters)
            },
        )
    }
}

@Composable
private fun FilterDrawerTypeToItem(
    item: SirioFilterDrawerType,
    onValueChanged: (SirioFilterDrawerType) -> Unit,
) {
    when (item) {
        is SirioFilterDrawerType.Checkbox -> SirioFilterDrawerCheckbox(
            checked = item.checked,
            text = item.text,
            onCheckedChange = { onValueChanged(item.copy(checked = it)) }
        )

        is SirioFilterDrawerType.Chip -> SirioFilterChips(
            values = item.values,
            selectedValues = item.selectedValues,
            onSelectionChanged = { onValueChanged(item.copy(selectedValues = it)) }
        )

        is SirioFilterDrawerType.Date -> SirioFilterDrawerDate(
            dateFormat = item.dateFormat,
            label = item.label,
            selectedDate = item.selectedDate,
            placeholder = item.placeholder,
            iconContentDescription = item.iconContentDescription,
            onDateSelected = { onValueChanged(item.copy(selectedDate = it)) }
        )

        is SirioFilterDrawerType.RadioGroup -> SirioFilterDrawerRadioGroup(
            values = item.values,
            selectedValue = item.selectedValue,
            onSelectionChange = { onValueChanged(item.copy(selectedValue = it)) },
        )

        is SirioFilterDrawerType.Select -> SirioFilterDrawerSelect(
            values = item.values,
            selectedValue = item.selectedValue,
            label = item.label,
            placeholder = item.placeholder,
            iconContentDescription = item.iconContentDescription,
            onValueChange = { onValueChanged(item.copy(selectedValue = it)) }
        )

        is SirioFilterDrawerType.Title -> SirioFilterDrawerTitle(
            title = item.title,
            text = item.text,
        )

        is SirioFilterDrawerType.Toggle -> SirioFilterDrawerToggle(
            checked = item.checked,
            text = item.text,
            onToggleChange = { onValueChanged(item.copy(checked = it)) }
        )
    }
}

@Preview
@Composable
private fun SirioFilterDrawerPreview() {
    SirioTheme {
        val initialFilters = listOf(
            SirioFilterDrawerType.Title(
                id = "title-1",
                title = "Section Title",
                text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor."
            ),
            SirioFilterDrawerType.Chip(
                id = "chip-1",
                values = listOf("Valore selezionato", "Valore 1", "Valore 2", "Valore 3"),
                selectedValues = setOf("Valore selezionato"),
            ),
            SirioFilterDrawerType.Title(
                id = "title-2",
                title = "Section Title",
            ),
            SirioFilterDrawerType.Select(
                id = "select-1",
                label = "Label",
                values = listOf(),
                placeholder = "Text",
            ),
            SirioFilterDrawerType.Date(
                id = "date-1",
                label = "Label",
                placeholder = "Text",
                dateFormat = "dd/MM/yyyy",
            ),
            SirioFilterDrawerType.Title(
                id = "title-3",
                title = "Section Title",
            ),
            SirioFilterDrawerType.Toggle(
                id = "toggle-1",
                checked = false,
                text = "Label",
            ),
            SirioFilterDrawerType.Toggle(
                id = "toggle-2",
                checked = false,
                text = "Label",
            ),
            SirioFilterDrawerType.Title(
                id = "title-4",
                title = "Section Title",
            ),
            SirioFilterDrawerType.RadioGroup(
                id = "radio-1",
                values = listOf("Label 1", "Label 2"),
            ),
            SirioFilterDrawerType.Title(
                id = "title-5",
                title = "Section Title",
            ),
            SirioFilterDrawerType.Checkbox(
                id = "checkbox-1",
                checked = false,
                text = "Label",
            ),
            SirioFilterDrawerType.Checkbox(
                id = "checkbox-2",
                checked = false,
                text = "Label",
            ),
        )
        val state = rememberSirioFilterState(initialFilters)
        SirioFilterDrawer(
            onClose = {},
            onApplyFilters = {},
            state = state,
        )
    }
}
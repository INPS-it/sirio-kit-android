//
// SirioFilterChips.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterChipsPaddingHorizontal
import it.inps.sirio.theme.filterChipsPaddingVertical
import it.inps.sirio.ui.chip.SirioChipsSelectable
import it.inps.sirio.ui.text.SirioText

/**
 * Displays a horizontal list of selectable filter chips (single selection).
 *
 * Use this overload when the user must select at most one value at a time.
 *
 * @param values The list of available filter values to display.
 * @param selectedValue The currently selected value, or `null` if no selection is active.
 * @param onSelectionChanged Callback invoked when the selection changes.
 * When a chip is selected, the callback receives its value; when the selected chip is deselected,
 * the callback receives `null`.
 */
@Composable
fun SirioFilterChips(
    values: List<String>,
    selectedValue: String? = null,
    onSelectionChanged: (String?) -> Unit,
) {
    val onSelectionChangedState by rememberUpdatedState(onSelectionChanged)
    val selectedValueState by rememberUpdatedState(selectedValue)

    SirioFilterChipsCommon(
        values = values,
        isSelected = { text -> text == selectedValueState },
        onChipSelectedChange = { text, isSelected ->
            onSelectionChangedState(
                when {
                    isSelected -> text
                    selectedValueState == text -> null
                    else -> selectedValueState
                }
            )
        }
    )
}

/**
 * Displays a horizontal list of selectable filter chips (multiple selection).
 *
 * Use this overload when the user can select multiple values at the same time.
 *
 * @param values The list of available filter values to display.
 * @param selectedValues The set of currently selected values.
 * @param onSelectionChanged Callback invoked when the selection changes, receiving the updated set.
 */
@Composable
fun SirioFilterChips(
    values: List<String>,
    selectedValues: Set<String> = emptySet(),
    onSelectionChanged: (Set<String>) -> Unit,
) {
    val onSelectionChangedState by rememberUpdatedState(onSelectionChanged)
    val selectedValuesState by rememberUpdatedState(selectedValues)

    SirioFilterChipsCommon(
        values = values,
        isSelected = { text -> text in selectedValuesState },
        onChipSelectedChange = { text, isSelected ->
            val current = selectedValuesState
            val newSelection = if (isSelected) current + text else current - text
            onSelectionChangedState(newSelection)
        }
    )
}

@Composable
private fun SirioFilterChipsCommon(
    values: List<String>,
    isSelected: (String) -> Boolean,
    onChipSelectedChange: (text: String, isSelected: Boolean) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(vertical = filterChipsPaddingVertical.dp),
        contentPadding = PaddingValues(horizontal = filterChipsPaddingHorizontal.dp),
        horizontalArrangement = Arrangement.spacedBy(filterChipsPaddingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(
            items = values,
            key = { index, text -> "$text-$index" }
        ) { _, text ->
            SirioChipsSelectable(
                text = text,
                active = isSelected(text),
                onSelectedChange = { isSelected ->
                    onChipSelectedChange(text, isSelected)
                },
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterChipsPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            val values = listOf(
                "Valore selezionato",
                "Valore 1",
                "Valore 2",
                "Valore 3",
                "Valore 4"
            )

            // MULTI selection state
            SirioText(
                text = "Multi selection",
                typography = SirioTheme.foundationTypography.bodySmRegular,
            )

            var selectedMulti by remember {
                mutableStateOf(setOf(values.first()))
            }

            SirioFilterChips(
                values = values,
                selectedValues = selectedMulti,
                onSelectionChanged = { selectedMulti = it },
            )

            // SINGLE selection state
            SirioText(
                text = "Single selection",
                typography = SirioTheme.foundationTypography.bodySmRegular,
            )

            var selectedSingle: String? by remember { mutableStateOf(values.first()) }

            SirioFilterChips(
                values = values,
                selectedValue = selectedSingle,
                onSelectionChanged = { selectedSingle = it },
            )
        }
    }
}

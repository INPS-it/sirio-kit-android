//
// SirioFilterChips.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterChipsPaddingHorizontal
import it.inps.sirio.theme.filterChipsPaddingVertical
import it.inps.sirio.ui.chip.SirioChipsSelectable

/**
 * A filter row of chips with horizontal scrolling.
 *
 * @param values The list of the chips text labels.
 * @param selectedValues The set of currently selected text labels.
 * @param onSelectionChanged The callback that is invoked when the set of selected chips changes.
 */
@Composable
fun SirioFilterChips(
    values: List<String>,
    selectedValues: Set<String> = emptySet(),
    onSelectionChanged: (Set<String>) -> Unit,
) {
    val onChipSelectionChanged = remember(selectedValues, onSelectionChanged) {
        { text: String, isSelected: Boolean ->
            val newSelection = if (isSelected) {
                selectedValues + text
            } else {
                selectedValues - text
            }
            onSelectionChanged(newSelection)
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(vertical = filterChipsPaddingVertical.dp),
        contentPadding = PaddingValues(horizontal = filterChipsPaddingHorizontal.dp),
        horizontalArrangement = Arrangement.spacedBy(filterChipsPaddingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            items = values,
            key = { it }
        ) { text ->
            SirioChipsSelectable(
                text = text,
                active = text in selectedValues,
                onSelectedChange = { isSelected ->
                    onChipSelectionChanged(text, isSelected)
                },
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterChipsPreview() {
    SirioTheme {
        Column {
            SirioFilterChips(
                values = listOf(
                    "Valore selezionato",
                    "Valore 1",
                    "Valore 2",
                    "Valore 3",
                    "Valore 4"
                ),
                selectedValues = setOf("Valore selezionato"),
                onSelectionChanged = {},
            )
        }
    }
}
//
// SirioFilterSelected.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalLayoutApi::class)

package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterChipsPadding
import it.inps.sirio.theme.filterPadding
import it.inps.sirio.theme.filterSelectedPaddingVertical
import it.inps.sirio.ui.chip.SirioChip

/**
 * A composable function that displays a row of selected filter values as chips with close icons.
 *
 * @param values The list of selected filter values to display.
 * @param closeContentDescription The content description for the close icon, for accessibility purposes.
 * @param onDeleteValue The callback to be invoked when a chip's close icon is clicked.
 */
@Composable
fun SirioFilterSelected(
    values: List<String>,
    closeContentDescription: String? = null,
    onDeleteValue: (String) -> Unit,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.selectedBackground)
            .padding(horizontal = filterPadding.dp, vertical = filterSelectedPaddingVertical.dp),
        horizontalArrangement = Arrangement.spacedBy(filterChipsPadding.dp),
        verticalArrangement = Arrangement.spacedBy(filterChipsPadding.dp),
    ) {
        values.forEach { text ->
            SirioChip(
                text = text,
                enabled = true,
                closeContentDescription = closeContentDescription,
                onClose = { onDeleteValue(text) },
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterSelectedPreview() {
    SirioTheme {
        Column {
            SirioFilterSelected(
                values = listOf(
                    "Valore A",
                    "Valore B",
                    "Valore C",
                    "Valore D",
                    "Valore E"
                ),
                onDeleteValue = {},
            )
        }
    }
}
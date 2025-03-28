//
// SirioFilterChipsWrap.kt
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
import it.inps.sirio.ui.chip.SirioChipsSelectable

/**
 * A component that wraps a list of chips.
 *
 * @param texts The list of text labels for the chips.
 * @param selectedTexts The list of currently selected text labels.
 * @param onSelectedChanges The callback to be invoked when the selected chips change.
 */
@Composable
fun SirioFilterChipsWrap(
    texts: List<String>,
    selectedTexts: List<String> = emptyList(),
    onSelectedChanges: (List<String>) -> Unit,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(filterPadding.dp),
        horizontalArrangement = Arrangement.spacedBy(filterChipsPadding.dp),
        verticalArrangement = Arrangement.spacedBy(filterChipsPadding.dp),
    ) {
        texts.forEach { text ->
            SirioChipsSelectable(
                text = text,
                enabled = true,
                active = selectedTexts.contains(text),
                onStateChange = {
                    if (it) {
                        onSelectedChanges(selectedTexts + text)
                    } else {
                        onSelectedChanges(selectedTexts - text)
                    }
                },
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterChipsWrapPreview() {
    SirioTheme {
        Column {
            SirioFilterChipsWrap(
                texts = listOf(
                    "Valore selezionato",
                    "Valore",
                    "Valore",
                    "Valore",
                    "Valore"
                ),
                selectedTexts = listOf("Valore selezionato"),
                onSelectedChanges = {},
            )
        }
    }
}
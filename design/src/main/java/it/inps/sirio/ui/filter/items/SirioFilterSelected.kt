//
// SirioFilterSelected.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalLayoutApi::class)

package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterSelectedPaddingHorizontal
import it.inps.sirio.theme.filterSelectedPaddingVertical
import it.inps.sirio.ui.chip.SirioChip

/**
 * A composable function that displays a row of selected filter values as chips with close icons.
 *
 * @param chips The list of [SirioFilterSelectedChipData] objects representing the selected filters. Each item contains the key, the label text, and the close action.
 * @param closeContentDescription The content description for the close icon within the chips, used for accessibility. Defaults to null.
 */
@Composable
fun SirioFilterSelected(
    chips: List<SirioFilterSelectedChipData>,
    closeContentDescription: String? = null,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(vertical = filterSelectedPaddingVertical.dp),
        contentPadding = PaddingValues(horizontal = filterSelectedPaddingHorizontal.dp),
        horizontalArrangement = Arrangement.spacedBy(filterSelectedPaddingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(
            items = chips,
            key = { it.key }
        ) { chip ->
            SirioChip(
                text = chip.text,
                closeContentDescription = closeContentDescription,
                onClose = chip.onClose,
            )
        }
    }
}

@Immutable
data class SirioFilterSelectedChipData(
    val key: String,                 // univoca e stabile (es: "chip-1:Valore 2", "date-1")
    val text: String,                // label mostrata
    val onClose: () -> Unit,         // azione di rimozione mirata
)

@Preview
@Composable
private fun SirioFilterSelectedPreview() {
    SirioTheme {
        Column {
            SirioFilterSelected(
                chips = listOf(
                    SirioFilterSelectedChipData(
                        key = "chip-1:Valore A",
                        text = "Valore A",
                        onClose = {},
                    ),
                    SirioFilterSelectedChipData(
                        key = "chip-1:Valore B",
                        text = "Valore B",
                        onClose = {},
                    ),
                )
            )
        }
    }
}

//
// SirioSearchBarAsync.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme

//Search No List
/**
 * Public entry point for a search bar used as a generic input field async.
 *
 * This variant:
 * - Can display a label.
 * - Can show suggestion values (autocomplete).
 * - Can show a fallback message when no suggestions match.
 * - Can trigger an explicit search action via [onSearch].
 *
 * @param searchText Current text inside the search field.
 * @param onSearchTextChange Callback invoked on each text change.
 * @param label Optional label associated with the search field.
 * @param placeholder Optional placeholder displayed when the field is empty.
 * @param optionValues Suggestion items for autocomplete.
 * @param noOptionText Text shown when no suggestions match the user's query.
 * @param onSearch Callback invoked when the user triggers a search.
 */
@Composable
fun SirioSearchBarAsync(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    optionValues: List<String> = emptyList(),
    noOptionText: String = "Nessun suggerimento per",
    onSearch: ((text: String) -> Unit)? = null,
) {
    SirioSearchBarCommon(
        searchText = searchText,
        onSearchTextChange = onSearchTextChange,
        placeholder = placeholder,
        mode = SirioSearchMode.Async(
            label = label,
            optionValues = optionValues,
            noOptionText = noOptionText,
            onSearch = onSearch,
        ),
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SearchBarNoListPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var searchText by remember { mutableStateOf("") }
            SirioSearchBarAsync(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                label = "Label",
                placeholder = "Inserisci il testo",
                optionValues = listOf(
                    "Risultato #1",
                    "Risultato #2",
                    "Risultato #3",
                    "Risultato #4",
                    "Risultato #5",
                )
            )
        }
    }
}

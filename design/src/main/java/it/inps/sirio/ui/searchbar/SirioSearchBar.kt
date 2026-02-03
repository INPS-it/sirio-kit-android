//
// SirioSearchBar.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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

//Search List
/**
 * Public entry point for a search bar used to filter a list.
 *
 * This variant:
 * - Always shows the search icon when the text is empty.
 * - Is intended for "inline search over a list of items".
 *
 * @param searchText Current text shown in the search field.
 * @param onSearchTextChange Callback invoked on each text change.
 * @param placeholder Optional placeholder text shown when the field is empty.
 */
@Composable
fun SirioSearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String? = null,
) {
    SirioSearchBarCommon(
        searchText = searchText,
        onSearchTextChange = onSearchTextChange,
        placeholder = placeholder,
        mode = SirioSearchMode.Default,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SearchBarPreview() {
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
            SirioSearchBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                placeholder = "Inserisci il testo",
            )
        }
    }
}

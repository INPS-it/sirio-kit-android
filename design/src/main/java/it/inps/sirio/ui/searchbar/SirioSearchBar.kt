//
// SirioSearchBar.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio search bar with title, text field, helper text and queries list
 *
 * @param searchText The string in the text field
 * @param onSearchTextChange The callback invoked when search text change
 * @param placeholder The placeholder in text field when it's empty
 * @param label Optional string on top of the search bar
 * @param helperText Optional string on bottom of the search bar
 * @param optionValues The hints based on current [searchText]
 * @param queries The list of already searched queries
 * @param onQueriesChange The callback invoked when the [queries] list change
 */
@Composable
fun SirioSearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String? = null,
    label: String? = null,
    helperText: String? = null,
    optionValues: Array<String> = emptyArray(),
    queries: Array<String> = emptyArray(),
    onQueriesChange: (queries: Array<String>) -> Unit,
) {
    SirioSearchBarCommon(
        searchText = searchText,
        onSearchTextChange = onSearchTextChange,
        placeholder = placeholder,
        label = label,
        helperText = helperText,
        optionValues = optionValues,
        queries = queries,
        onQueriesChange = onQueriesChange,
        enabled = true
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
            SirioSearchBar(
                searchText = "Text",
                onSearchTextChange = {},
                label = "Label",
                placeholder = "Placeholder",
                helperText = "*Helper text",
                queries = arrayOf("Ricerca 1", "Ricerca 2", "Ricerca 3", "Ricerca 4"),
                optionValues = arrayOf(
                    "Option Value 1",
                    "Option Value 2",
                    "Option Value 3",
                    "Option Value 4",
                ),
                onQueriesChange = {},
            )
        }
    }
}
//
// SirioSearchListAsync.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.searchbar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem

@Composable
fun <T> SirioSearchListAsync(
    items: List<T> = emptyList(),
    itemContent: @Composable (LazyItemScope.(isLastItem: Boolean, item: T) -> Unit),
    itemKey: ((T) -> Any)? = null,
    itemContentType: (T) -> Any? = { null },
    searchText: String = "",
    label: String? = null,
    placeholder: String? = null,
    noResultTitle: String = "Nessun risultato trovato",
    noResultText: String? = "Prova ad effettuare una nuova ricerca",
    optionValues: List<String> = emptyList(),
    noOptionText: String = "Nessun suggerimento per",
    onSearch: (text: String) -> Unit,
) {
    var currentSearchText by remember(searchText) { mutableStateOf(searchText) }
    var searchPerformed by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.background(SirioTheme.colors.searchBar.background),
    ) {
        SirioSearchBarAsync(
            searchText = currentSearchText,
            label = label,
            placeholder = placeholder,
            onSearchTextChange = {
                searchPerformed = false
                currentSearchText = it
            },
            optionValues = optionValues,
            noOptionText = noOptionText,
            onSearch = {
                onSearch(it)
                searchPerformed = true
            },
        )
        Crossfade(
            searchPerformed && items.isEmpty()
        ) { isEmpty ->
            if (isEmpty) {
                SirioSearchNoResult(title = noResultTitle, text = noResultText)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    itemsIndexed(
                        items = items,
                        key = itemKey?.let { keyFn ->
                            { _: Int, item: T -> keyFn(item) }
                        },
                        contentType = { _, item -> itemContentType(item) },
                        itemContent = { index, item ->
                            itemContent(index == items.lastIndex, item)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SirioSearchListAsync(
    items: List<String> = emptyList(),
    searchText: String = "",
    label: String? = null,
    placeholder: String? = null,
    noResultTitle: String = "Nessun risultato trovato",
    noResultText: String? = null,
    optionValues: List<String> = emptyList(),
    onItemClick: (String) -> Unit,
    onSearch: (text: String) -> Unit,
) {
    SirioSearchListAsync(
        items = items,
        itemContent = { isLastItem, item ->
            SirioListItem(
                title = item,
                showDivider = !isLastItem,
                onClick = { onItemClick(item) },
            )
        },
        itemKey = { it },
        searchText = searchText,
        label = label,
        placeholder = placeholder,
        noResultTitle = noResultTitle,
        noResultText = noResultText,
        optionValues = optionValues,
        onSearch = onSearch,
    )
}

@Preview
@Composable
private fun SirioSearchListAsyncNoSuggestionsPreview() {
    SirioTheme {
        val options = listOf(
            "Pensione",
            "Pensioni: ricostituzione per variazione dati supplemento",
            "Pensionati: il cedolino di gennaio",
            "Simulazione pensione",
        )
        val items = listOf(
            "Amministrazione",
            "Amministrazione trasparente",
            "Amministrazione digitale",
            "Amministrazione attiva",
            "Amministrazione online",
            "Amministrazione documentale",
        )
        var searchText by remember { mutableStateOf("") }
        val itemsToShow = if (searchText.isNotBlank()) items else emptyList()

        SirioSearchListAsync(
            items = itemsToShow,
            searchText = searchText,
            placeholder = "Inserisci il testo",
            optionValues = options,
            onItemClick = {},
            onSearch = { searchText = it }
        )
    }
}

//
// SirioSearchList.kt
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.filter.items.SirioFilterChips
import it.inps.sirio.ui.listItem.SirioListItem

@Composable
fun <T> SirioSearchList(
    items: List<T> = emptyList(),
    itemContent: @Composable (LazyItemScope.(isLastItem: Boolean, item: T) -> Unit),
    onFilter: (T, String) -> Boolean,
    itemKey: ((T) -> Any)? = null,
    itemContentType: (T) -> Any? = { null },
    searchText: String = "",
    placeholder: String? = null,
    noResultTitle: String = "Nessun risultato trovato",
    noResultText: String? = "Prova ad effettuare una nuova ricerca",
    filters: List<String> = emptyList(),
    onFilterSelectionChanged: (Set<String>) -> Unit = {},
) {
    var currentSearchText by remember { mutableStateOf(searchText) }
    val currentItems: List<T> by remember(items, currentSearchText) {
        derivedStateOf { items.filter { onFilter(it, currentSearchText) } }
    }
    Column(
        modifier = Modifier.background(SirioTheme.colors.searchBar.background),
    ) {
        SirioSearchBar(
            searchText = currentSearchText,
            placeholder = placeholder,
            onSearchTextChange = { currentSearchText = it },
        )
        if (filters.isNotEmpty()) {
            var currentFilters by remember(filters) { mutableStateOf(setOf<String>()) }
            SirioFilterChips(
                values = filters,
                selectedValues = currentFilters,
                onSelectionChanged = {
                    currentFilters = it
                    onFilterSelectionChanged(it)
                },
            )
        }
        Crossfade((items.isNotEmpty() && currentItems.isEmpty()) || items.isEmpty() && currentSearchText.isNotBlank()) { isEmpty ->
            if (isEmpty) {
                SirioSearchNoResult(title = noResultTitle, text = noResultText)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    itemsIndexed(
                        items = currentItems,
                        key = itemKey?.let { keyFn ->
                            { _: Int, item: T -> keyFn(item) }
                        },
                        contentType = { _, item -> itemContentType(item) },
                        itemContent = { index, item ->
                            itemContent(index == currentItems.lastIndex, item)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SirioSearchList(
    items: List<String> = emptyList(),
    searchText: String = "",
    placeholder: String? = null,
    noResultTitle: String = "Nessun risultato trovato",
    noResultText: String? = null,
    filters: List<String> = emptyList(),
    onFilterSelectionChanged: (Set<String>) -> Unit = {},
    onItemClick: (String) -> Unit,
) {
    SirioSearchList(
        items = items,
        itemContent = { isLastItem, item ->
            SirioListItem(
                title = item,
                showDivider = !isLastItem,
                onClick = { onItemClick(item) },
            )
        },
        onFilter = { item, searchText -> item.contains(searchText, ignoreCase = true) },
        itemKey = { it },
        searchText = searchText,
        placeholder = placeholder,
        noResultTitle = noResultTitle,
        noResultText = noResultText,
        filters = filters,
        onFilterSelectionChanged = onFilterSelectionChanged,
    )
}

@Preview
@Composable
private fun SirioSearchPreview() {
    SirioTheme {
        val items = listOf(
            "Vitae elit, dolor sit amet",
            "Eiusmod tempor incididunt",
            "Minim veniam, exercitation laboris",
            "Irure dolor in voluptate",
            "Sint occaecat cupidatat",
            "Pretium tincidunt",
            "Quis ante",
            "Sit amet orci",
            "Amministratio Perspicua",
            "Fringilla sit amet",
            "A est",
        )
        SirioSearchList(
            items = items,
            onItemClick = {},
        )
    }
}

@Preview
@Composable
private fun SirioSearchChipPreview() {
    SirioTheme {
        val items = listOf(
            "Vitae elit, dolor sit amet",
            "Eiusmod tempor incididunt",
            "Minim veniam, exercitation laboris",
            "Irure dolor in voluptate",
            "Sint occaecat cupidatat",
            "Pretium tincidunt",
            "Quis ante",
            "Sit amet orci",
            "Amministratio Perspicua",
            "Fringilla sit amet",
            "A est",
        )
        var selectedFilters by remember { mutableStateOf(setOf<String>()) }
        SirioSearchList(
            items = items.filterIndexed { index, _ ->
                when {
                    selectedFilters.isEmpty() || selectedFilters.size == 2 -> true
                    selectedFilters.first() == "Pari" -> index % 2 == 0
                    else -> index % 2 == 1
                }
            },
            filters = listOf("Pari", "Dispari"),
            onFilterSelectionChanged = { selectedFilters = it },
            onItemClick = {},
        )
    }
}

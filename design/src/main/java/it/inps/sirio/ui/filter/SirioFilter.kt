//
// SirioFilter.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterFabPaddingBottom
import it.inps.sirio.theme.filterItemSpacing
import it.inps.sirio.ui.card.SirioCardColor
import it.inps.sirio.ui.card.SirioProcessCard
import it.inps.sirio.ui.card.SirioProcessCardItemData
import it.inps.sirio.ui.card.SirioProcessCardType
import it.inps.sirio.ui.filter.items.SirioFilterAction
import it.inps.sirio.ui.filter.items.SirioFilterFab
import it.inps.sirio.ui.filter.items.SirioFilterSectionTitle
import it.inps.sirio.ui.filter.items.SirioFilterSelected

/**
 * Main filter composable combining a list of items with a filter drawer.
 *
 * [SirioFilter] displays a scrollable list of content (e.g., cards or filterable items)
 * and provides access to a [SirioFilterDrawer] via a FAB or a top button.
 *
 * The actual filters are managed outside of this composable using [SirioFilterState].
 *
 * @param title Optional title displayed above the list.
 * @param buttonFilterText Label for the main filter button.
 * @param items List of items to display in the scrollable area.
 * @param key Optional key extractor for list items.
 * @param contentType Optional type extractor for list items.
 * @param itemContent Composable content for each item.
 * @param filterState State holder for the filter drawer.
 * @param onApplyFilters Callback triggered when filters are applied.
 * @param onRemoveFilters Callback triggered when filters are cleared.
 */
@Composable
fun <T> SirioFilter(
    items: List<T>,
    itemContent: @Composable (LazyItemScope.(T) -> Unit),
    filterState: SirioFilterState,
    onApplyFilters: (List<SirioFilterDrawerType>) -> Unit,
    onRemoveFilters: (() -> Unit)? = null,
    title: String? = null,
    buttonFilterText: String = "Filtra",
    key: ((T) -> Any)? = null,
    contentType: (T) -> Any? = { null },
) {
    var showDrawer by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val currentOnApplyFilters by rememberUpdatedState(onApplyFilters)
    val currentOnRemoveFilters by rememberUpdatedState(onRemoveFilters)

    val showFab by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
    val activeChips by remember {
        derivedStateOf {
            filterState.activeChips(onStateChanged = currentOnApplyFilters)
        }
    }

    Box {
        Column(
            modifier = Modifier.background(SirioTheme.colors.filter.background),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
                contentPadding = PaddingValues(filterItemSpacing.dp),
                verticalArrangement = Arrangement.spacedBy(filterItemSpacing.dp),
            ) {
                item(key = "header", contentType = "header") {
                    title?.let { SirioFilterSectionTitle(it) }
                    SirioFilterAction(buttonText = buttonFilterText) { showDrawer = true }
                    if (activeChips.isNotEmpty()) {
                        SirioFilterSelected(chips = activeChips)
                    }
                }
                items(
                    items = items,
                    key = key,
                    contentType = contentType,
                    itemContent = itemContent,
                )
            }
        }
        AnimatedVisibility(
            visible = showFab,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = filterFabPaddingBottom.dp),
            enter = slideInVertically { it },
            exit = slideOutVertically { it * 2 },
        ) {
            SirioFilterFab { showDrawer = true }
        }
    }

    if (showDrawer) {
        SirioFilterFullScreenDialog(
            state = filterState,
            onDismissRequest = { showDrawer = false },
            onRemoveFilters = currentOnRemoveFilters,
            onApplyFilters = { filters ->
                currentOnApplyFilters(filters)
                // la chiusura vera avviene dopo l'animazione dentro la dialog
            }
        )
    }
}

@Preview
@Composable
private fun SirioFilterPreview() {
    SirioTheme {
        SirioFilter(
            title = "Titolo della sezione",
            items = listOf(
                "Titolo della card 1",
                "Titolo della card 2",
                "Titolo della card 3",
                "Titolo della card 4",
                "Titolo della card 5"
            ),
            key = { it },
            itemContent = {
                SirioProcessCard(
                    title = it,
                    type = SirioProcessCardType.Standard(
                        firstAction = SirioProcessCardItemData(
                            text = "Text",
                            action = {},
                        )
                    ),
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
                    color = SirioCardColor.LIGHT,
                    onClickCard = { },
                )
            },
            filterState = rememberSirioFilterState(listOf()),
            onApplyFilters = {},
        )
    }
}

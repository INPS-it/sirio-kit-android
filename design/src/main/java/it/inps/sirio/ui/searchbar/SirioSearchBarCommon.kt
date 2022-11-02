//
// SirioSearchBarCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.searchbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.searchBarQueriesPadding
import it.inps.sirio.theme.searchBarQueriesVerticalPadding
import it.inps.sirio.ui.chip.ChipLabelClose
import it.inps.sirio.ui.textfield.SirioTextFieldCommon

/**
 * Sirio custom search bar with title, text field, helper text and queries list
 *
 * @param searchText The string in the text field
 * @param onSearchTextChange The callback invoked when search text change
 * @param placeholder The placeholder in text field when it's empty
 * @param label Optional string on top of the search bar
 * @param helperText Optional string on bottom of the search bar
 * @param optionValues The hints based on current [searchText]
 * @param queries The list of already searched queries
 * @param onQueriesChange The callback invoked when the [queries] list change
 * @param onQueryAdded The callback invoked when the user add a query by search bar
 * @param enabled Whether the search bar allow to insert/remove [queries]
 */
@Composable
internal fun SirioSearchBarCommon(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String?,
    label: String?,
    helperText: String?,
    optionValues: Array<String>,
    queries: Array<String>,
    onQueriesChange: (queries: Array<String>) -> Unit,
    onQueryAdded: ((newQuery: String) -> Unit)? = null,
    enabled: Boolean,
//    onSearchBarClick: (() -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current
    val chips = remember { mutableStateListOf(elements = queries) }
    var showChips by remember { mutableStateOf(true) }

    Column {
        SirioTextFieldCommon(
            text = searchText,
            onValueChange = {
                showChips = false
                onSearchTextChange(it)
            },
            placeholder = placeholder,
            icon = if (searchText.isNotEmpty()) FaIcons.Times else null,
            iconButton = FaIcons.Search,
            label = label,
            onInfoClick = null,
            helperText = helperText,
            optionValues = optionValues,
            onOptionValueSelected = {
                if (it.isNotBlank()) {
                    focusManager.clearFocus()
                    showChips = true
                    if (!chips.contains(it)) {
                        onQueryAdded?.invoke(it)
                        chips.add(it)
                        onQueriesChange(chips.toTypedArray())
                    }
                }
            },
            type = null,
            enabled = enabled,
            disableExtraBorder = true,
            backgroundColor = SirioTheme.colors.searchBar.background,
            onDropdownStateChange = { open -> showChips = !open },
            onIconClick = { onSearchTextChange("") },
            onIconButtonClick = {
                if (it.isNotBlank()) {
                    focusManager.clearFocus()
                    showChips = true
                    if (!chips.contains(it)) {
                        onQueryAdded?.invoke(it)
                        chips.add(it)
                        onQueriesChange(chips.toTypedArray())
                    }
                }
            },
            imeAction = ImeAction.Search,
            keyboardActionOnAny = {
                if (it.isNotBlank()) {
                    focusManager.clearFocus()
                    showChips = true
                    if (!chips.contains(it)) {
                        onQueryAdded?.invoke(it)
                        chips.add(it)
                        onQueriesChange(chips.toTypedArray())
                    }
                }
            },
        )
        if (showChips) {
            Spacer(modifier = Modifier.height(searchBarQueriesVerticalPadding))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                mainAxisSpacing = searchBarQueriesPadding,
                crossAxisSpacing = searchBarQueriesPadding,
                mainAxisAlignment = FlowMainAxisAlignment.Start,
                lastLineMainAxisAlignment = FlowMainAxisAlignment.Start,
            ) {
                chips.forEachIndexed { index, item ->
                    ChipLabelClose(label = item, enabled = enabled) {
                        chips.removeAt(index)
                        onQueriesChange(chips.toTypedArray())
                    }
                }
            }
        }
    }
}

@Keep
data class SirioSearchBarColors(
    var background: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioSearchBarColors(
            background = Color.Unspecified,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchBarCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioSearchBarCommon(
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
                enabled = true,
            )
        }
    }
}
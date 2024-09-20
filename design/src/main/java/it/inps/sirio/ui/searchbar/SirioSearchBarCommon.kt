//
// SirioSearchBarCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalLayoutApi::class)

package it.inps.sirio.ui.searchbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.searchBarQueriesPadding
import it.inps.sirio.theme.searchBarQueriesVerticalPadding
import it.inps.sirio.ui.chip.SirioChipLabelClose
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
 * @param onSearch The callback invoked when the user click on search button
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
    onSearch: ((text: String) -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current
    var showChips by remember { mutableStateOf(true) }

    fun addQuery(value: String) {
        if (value.isNotBlank()) {
            focusManager.clearFocus()
            showChips = true
            if (!queries.contains(value)) {
                onQueryAdded?.invoke(value)
                onQueriesChange((queries.plus(value)))
            }
        }
    }
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
            onOptionValueSelected = { addQuery(it) },
            type = null,
            enabled = enabled,
            disableExtraBorder = true,
            backgroundColor = SirioTheme.colors.searchBar.background,
            onDropdownStateChange = { open -> showChips = !open },
            onIconClick = {
                onSearchTextChange("")
                showChips = true
            },
            onIconButtonClick = {
                onSearch?.invoke(it)
                addQuery(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onAny = {
                    onSearch?.invoke(searchText)
                    addQuery(searchText)
                }
            ),
        )
        if (showChips) {
            Spacer(modifier = Modifier.height(searchBarQueriesVerticalPadding.dp))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(
                    searchBarQueriesPadding.dp,
                    Alignment.Start
                ),
                verticalArrangement = Arrangement.spacedBy(
                    searchBarQueriesPadding.dp,
                    Alignment.Top
                )
            ) {
                queries.forEach { item ->
                    SirioChipLabelClose(label = item, enabled = enabled) {
                        onQueriesChange(queries.filter { it != item }.toTypedArray())
                    }
                }
            }
        }
    }
}

@Keep
data class SirioSearchBarColors(
    val background: Color,
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

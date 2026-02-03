//
// SirioSearchBarCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.searchBarPadding
import it.inps.sirio.ui.textfield.SirioTextFieldCommon
import it.inps.sirio.utils.SirioIconSource

/**
 * Internal shared implementation of the Sirio search bar.
 *
 * This function centralizes:
 * - Trailing icon logic (search / clear).
 * - Clear action behavior.
 * - Mode-specific configuration extraction.
 *
 * Public consumers should use [SirioSearchBar] or [SirioSearchBarAsync].
 */
@Composable
internal fun SirioSearchBarCommon(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String? = null,
    mode: SirioSearchMode,
) {
    val searchIcon = remember { SirioIconSource.FaIcon(FaIcons.Search) }
    val clearIcon = remember { SirioIconSource.FaIcon(FaIcons.Times) }

    val isAsync = mode is SirioSearchMode.Async

    /*
     * Trailing icon logic (identical to the original):
     *
     * Input mode:
     *   - if empty → null
     *   - if not empty → clear
     *
     * Default/List mode:
     *   - if empty → search
     *   - if not empty → clear
     */
    val trailingIcon by remember(searchText, mode) {
        derivedStateOf {
            when {
                !isAsync && searchText.isEmpty() -> searchIcon     // LIST empty
                searchText.isNotEmpty() -> clearIcon              // ANY full
                isAsync && searchText.isEmpty() -> null           // INPUT empty
                else -> searchIcon
            }
        }
    }

    val onTrailingIconClick by remember(searchText, onSearchTextChange) {
        derivedStateOf {
            if (searchText.isNotEmpty()) {
                { onSearchTextChange("") }
            } else null
        }
    }

    // Icon color logic
    val searchBarColors = SirioTheme.colors.searchBar
    val iconColor by remember(searchText) {
        derivedStateOf {
            if (searchText.isNotEmpty()) {
                searchBarColors.clearIcon
            } else {
                searchBarColors.icon
            }
        }
    }

    // Extract Input-mode specific data
    val asyncMode = mode as? SirioSearchMode.Async

    val label by remember(mode) { derivedStateOf { asyncMode?.label } }
    val onSearch by remember(mode) { derivedStateOf { asyncMode?.onSearch } }
    val baseOptionValues by remember(mode) { derivedStateOf { asyncMode?.optionValues.orEmpty() } }
    val noOptionText by remember(mode) {
        derivedStateOf {
            asyncMode?.noOptionText ?: "Nessun suggerimento per"
        }
    }

    val iconButton: SirioIconSource? = if (isAsync) searchIcon else null
    val onIconButtonClick: ((String) -> Unit)? = if (isAsync) {
        { onSearch?.invoke(searchText) }
    } else null

    val filteredOptionValues by remember(searchText, baseOptionValues, noOptionText, asyncMode) {
        derivedStateOf {
            if (
                asyncMode != null && searchText.isNotEmpty() && baseOptionValues.isNotEmpty()
            ) {
                baseOptionValues
                    .filter { it.contains(searchText, ignoreCase = true) }
                    .ifEmpty { listOf("$noOptionText $searchText") }
            } else emptyList()
        }
    }

    SirioTextFieldCommon(
        modifier = Modifier.padding(all = searchBarPadding.dp),
        text = searchText,
        onValueChange = onSearchTextChange,
        placeholder = placeholder,
        icon = trailingIcon,
        iconColor = iconColor,
        iconButton = iconButton,
        label = label,
        optionValues = filteredOptionValues,
        onOptionValueSelected = { onSearch?.invoke(it) },
        backgroundColor = SirioTheme.colors.searchBar.background,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onAny = { onSearch?.invoke(searchText) }
        ),
        onIconClick = onTrailingIconClick,
        onIconButtonClick = onIconButtonClick,
    )
}

/**
 * Defines the behavior and visual mode of a Sirio search bar.
 *
 * Use [SirioSearchMode.Default] when the search field is tightly coupled
 * with a list (e.g. filtering items on type).
 *
 * Use [SirioSearchMode.Async] when the search field behaves like a
 * form input (with label, autocomplete options, or an explicit search
 * action).
 */
sealed interface SirioSearchMode {

    /**
     * Default mode:
     *
     * - Always shows a search icon when the text is empty.
     * - Intended for list filtering use cases (e.g. search within a list).
     */
    data object Default : SirioSearchMode

    /**
     * Async mode:
     *
     * - Behaves like a form input field.
     * - Supports label, suggestion values, explicit search,
     *   and a fallback message when no suggestions are available.
     *
     * @property label Optional label associated with the search field.
     * @property optionValues Suggestion list used for autocomplete or dropdown behavior.
     * @property noOptionText Text displayed when no suggestion matches the user's input.
     * @property onSearch Optional callback invoked when the user triggers an explicit search action.
     */
    data class Async(
        val label: String? = null,
        val optionValues: List<String> = emptyList(),
        val noOptionText: String = "Nessun suggerimento per",
        val onSearch: ((String) -> Unit)? = null,
    ) : SirioSearchMode
}

@Keep
data class SirioSearchBarColors(
    val background: Color,
    val icon: Color,
    val clearIcon: Color,
    val noResult: SirioSearchNoResultColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioSearchBarColors(
            background = Color.Unspecified,
            icon = Color.Unspecified,
            clearIcon = Color.Unspecified,
            noResult = SirioSearchNoResultColors.Unspecified,
        )
    }
}

internal val searchBarLightColors = SirioSearchBarColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    icon = FoundationColor.colorAliasTextColorSecondaryDark100,
    clearIcon = FoundationColor.colorAliasInteractivePrimaryDefault,
    noResult = searchNoResultLightColors,
)

internal val searchBarDarkColors = SirioSearchBarColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    icon = FoundationColor.colorAliasTextColorSecondaryDark100,
    clearIcon = FoundationColor.colorAliasInteractivePrimaryDefault,
    noResult = searchNoResultDarkColors,
)

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
            var searchText by remember { mutableStateOf("") }
            SirioSearchBarCommon(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                placeholder = "Inserisci il testo",
                mode = SirioSearchMode.Default,
            )
            SirioSearchBarCommon(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                placeholder = "Inserisci il testo",
                mode = SirioSearchMode.Async(
                    label = "Label", optionValues = listOf(
                        "Risultato #1",
                        "Risultato #2",
                        "Risultato #3",
                        "Risultato #4",
                        "Risultato #5",
                    )
                ),
            )
        }
    }
}

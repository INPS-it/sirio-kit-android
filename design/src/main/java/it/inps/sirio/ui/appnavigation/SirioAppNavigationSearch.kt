//
// SirioAppNavigationSearch.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationHeight
import it.inps.sirio.theme.appNavigationSearchCornerRadius
import it.inps.sirio.theme.appNavigationSearchIconSize
import it.inps.sirio.theme.appNavigationSearchPadding
import it.inps.sirio.theme.appNavigationSearchTextPaddingStart
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
 * An app navigation with searchbar
 *
 * @param title The string to show
 * @param leftItem An [SirioAppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [SirioAppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [SirioAppNavigationItemData] with the content of the second right item
 * @param searchText The string in the searchbar
 * @param placeholderText The placeholder when search text is empty
 * @param onSearchTextChanged The callback when the search text is edited by the user
 * @param clearButtonContentDescription The content description for the search bar clear button
 * @param textFieldEnabled controls the enabled state of the search text field. When false, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param onDoneClick The callback when user press the done button in the keyboard
 */
@Composable
fun SirioAppNavigationSearch(
    title: String,
    leftItem: SirioAppNavigationItemData? = null,
    rightFirstItem: SirioAppNavigationItemData? = null,
    rightSecondItem: SirioAppNavigationItemData? = null,
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit,
    clearButtonContentDescription: String? = null,
    textFieldEnabled: Boolean = true,
    onDoneClick: (KeyboardActionScope.() -> Unit)? = null,
) {
    Column {
        SirioAppNavigation(
            title = title,
            leftItem = leftItem,
            rightFirstItem = rightFirstItem,
            rightSecondItem = rightSecondItem,
        )
        Box(
            Modifier
                .height(appNavigationHeight.dp)
                .background(color = SirioTheme.colors.appNavigation.searchBackground)
                .padding(appNavigationSearchPadding.dp)
        ) {
            val textFieldColors = TextFieldDefaults.colors(
                focusedIndicatorColor = SirioTheme.colors.appNavigation.searchBackground,
                unfocusedIndicatorColor = SirioTheme.colors.appNavigation.searchBackground,
                focusedContainerColor = SirioTheme.colors.appNavigation.searchTextFieldBackground,
                unfocusedContainerColor = SirioTheme.colors.appNavigation.searchTextFieldBackground,
                cursorColor = SirioTheme.colors.appNavigation.searchText,
                focusedTextColor = SirioTheme.colors.appNavigation.searchText,
                unfocusedTextColor = SirioTheme.colors.appNavigation.searchText,
                focusedPlaceholderColor = SirioTheme.colors.appNavigation.searchText,
                unfocusedPlaceholderColor = SirioTheme.colors.appNavigation.searchText,
            )
            val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = searchText,
                onValueChange = onSearchTextChanged,
                modifier = Modifier.fillMaxSize(),
                enabled = textFieldEnabled,
                textStyle = SirioTheme.foundationTypography.labelMdMiddle,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = onDoneClick),
                singleLine = true,
                maxLines = 1,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    @OptIn(ExperimentalMaterial3Api::class)
                    TextFieldDefaults.DecorationBox(
                        value = searchText,
                        innerTextField = innerTextField,
                        enabled = textFieldEnabled,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = {
                            SirioTextCommon(
                                text = placeholderText,
                                typography = SirioTheme.foundationTypography.labelMdMiddle,
                            )
                        },
                        trailingIcon = {
                            TrailingIcon(
                                searchText = searchText,
                                onSearchTextChanged = onSearchTextChanged,
                                clearButtonContentDescription = clearButtonContentDescription
                            )
                        },
                        shape = RoundedCornerShape(appNavigationSearchCornerRadius.dp),
                        colors = textFieldColors,
                        contentPadding = PaddingValues(start = appNavigationSearchTextPaddingStart.dp),
                    )
                }
            )
        }
    }
}

@Composable
private fun TrailingIcon(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    clearButtonContentDescription: String?,
) {
    if (searchText.isBlank())
        SirioIcon(
            icon = SirioIconSource.FaIcon(FaIcons.Search),
            iconColor = SirioTheme.colors.appNavigation.searchContent,
            size = appNavigationSearchIconSize.dp,
        )
    else
        IconButton(onClick = { onSearchTextChanged("") }) {
            SirioIcon(
                icon = SirioIconSource.FaIcon(FaIcons.Times),
                iconColor = SirioTheme.colors.appNavigation.searchContent,
                size = appNavigationSearchIconSize.dp,
                contentDescription = clearButtonContentDescription,
            )
        }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SirioAppNavigationSearchPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val title = "Titolo pagina"
            SirioAppNavigationSearch(
                title = title,
                rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
                searchText = "",
                placeholderText = "Placeholder search bar",
                onSearchTextChanged = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            SirioAppNavigationSearch(
                title = title,
                rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
                searchText = "Lorem ipsum ",
                placeholderText = "Ricerca",
                onSearchTextChanged = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
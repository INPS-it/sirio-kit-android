//
// AppNavigationSearch.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationSearchCornerRadius
import it.inps.sirio.theme.appNavigationSearchPadding
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * An app navigation with searchbar
 *
 * @param title The string to show
 * @param leftItem An [AppNavigationItemData] with the content of the left item
 * @param rightFirstItem An [AppNavigationItemData] with the content of the first right item
 * @param rightSecondItem An [AppNavigationItemData] with the content of the second right item
 * @param scrollBehavior a [TopAppBarScrollBehavior] which holds various offset values that will be
 * applied by this top app bar to set up its height and colors. A scroll behavior is designed to
 * work in conjunction with a scrolled content to change the top app bar appearance as the content
 * scrolls. See [TopAppBarScrollBehavior.nestedScrollConnection].
 * @param searchText The string in the searchbar
 * @param placeholderText The placeholder when search text is empty
 * @param onSearchTextChanged The callback when the search text is edited by the user
 * @param clearButtonContentDescription The content description for the search bar clear button
 * @param textFieldEnabled controls the enabled state of the search text field. When false, this component will not respond to user input, and it will appear visually disabled and disabled to accessibility services.
 * @param onDoneClick The callback when user press the done button in the keyboard
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationSearch(
    title: String,
    leftItem: AppNavigationItemData? = null,
    rightFirstItem: AppNavigationItemData? = null,
    rightSecondItem: AppNavigationItemData? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit,
    clearButtonContentDescription: String? = null,
    textFieldEnabled: Boolean = true,
    onDoneClick: (KeyboardActionScope.() -> Unit)? = null,
) {
    Column {
        AppNavigation(
            title = title,
            leftItem = leftItem,
            rightFirstItem = rightFirstItem,
            rightSecondItem = rightSecondItem,
            scrollBehavior = scrollBehavior
        )
        Box(
            Modifier
                .background(color = SirioTheme.colors.appNavigationSearchBackground2)
                .padding(appNavigationSearchPadding)
        ) {
            TextField(
                value = searchText,
                onValueChange = onSearchTextChanged,
                modifier = Modifier.fillMaxWidth(),
                enabled = textFieldEnabled,
                placeholder = {
                    SirioTextCommon(
                        text = placeholderText,
                        typography = SirioTheme.typography.appNavigationSearchPlaceholder,
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = SirioTheme.colors.appNavigationSearchBackground2,
                    unfocusedIndicatorColor = SirioTheme.colors.appNavigationSearchBackground2,
                    focusedContainerColor = SirioTheme.colors.appNavigationSearchBackground1,
                    unfocusedContainerColor = SirioTheme.colors.appNavigationSearchBackground1,
                    cursorColor = SirioTheme.colors.appNavigationSearchText,
                    focusedTextColor = SirioTheme.colors.appNavigationSearchText,
                    unfocusedTextColor = SirioTheme.colors.appNavigationSearchText,
                    focusedPlaceholderColor = SirioTheme.colors.appNavigationSearchText,
                    unfocusedPlaceholderColor = SirioTheme.colors.appNavigationSearchText,
                ),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = onDoneClick),
                textStyle = SirioTheme.typography.appNavigationSearch,
                shape = RoundedCornerShape(appNavigationSearchCornerRadius),
                trailingIcon = {
                    if (searchText.isBlank())
                        SirioIcon(
                            faIcon = FaIcons.Search,
                            iconColor = SirioTheme.colors.appNavigationSearchIcon,
                        )
                    else
                        IconButton(onClick = { onSearchTextChanged("") }) {
                            SirioIcon(
                                faIcon = FaIcons.Times,
                                iconColor = SirioTheme.colors.appNavigationSearchIcon,
                                contentDescription = clearButtonContentDescription,
                            )
                        }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppNavigationSearchPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val title = "Titolo pagina"
            AppNavigationSearch(
                title = title,
                rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
                searchText = "",
                placeholderText = "Placeholder search bar",
                onSearchTextChanged = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            AppNavigationSearch(
                title = title,
                rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
                searchText = "Lorem ipsum ",
                placeholderText = "Ricerca",
                onSearchTextChanged = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//
// SirioFilterInput.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterPadding
import it.inps.sirio.ui.textfield.SirioTextFieldCommon
import it.inps.sirio.ui.textfield.TextFieldState

/**
 * A filter input component.
 *
 * @param label The label to display above the input field.
 * @param text The current text value of the input field.
 * @param placeholder The placeholder text to display when the input field is empty.
 * @param secureText Whether the input field should display text securely (e.g., for passwords).
 * @param icon The optional icon to display within the input field.
 * @param iconContentDescription The content description for the icon, for accessibility purposes.
 * @param onInfoClick The callback to be invoked when the info icon is clicked.
 * @param infoContentDescription The content description for the info icon, for accessibility purposes.
 * @param helperText The helper text to display below the input field.
 * @param type The semantic type of the text field [@see TextFieldSemantic]
 * @param keyboardOptions The keyboard options to configure the soft keyboard.
 * @param keyboardActions The keyboard actions to handle specific keyboard events.
 * @param onIconClick The callback to be invoked when the icon is clicked.
 * @param onTextFieldClick The callback to be invoked when the text field is clicked.
 * @param onValueChange The callback to be invoked when the text value of the input field changes.
 */
@Composable
fun SirioFilterInput(
    label: String,
    text: String,
    placeholder: String? = null,
    secureText: Boolean = false,
    icon: FaIconType? = null,
    iconContentDescription: String? = null,
    onInfoClick: (() -> Unit)? = null,
    infoContentDescription: String? = null,
    helperText: String? = null,
    type: TextFieldState? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onIconClick: (() -> Unit)? = null,
    onTextFieldClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(SirioTheme.colors.filter.background)
            .padding(filterPadding.dp)
    ) {
        SirioTextFieldCommon(
            text = text,
            secureText = secureText,
            onValueChange = onValueChange,
            placeholder = placeholder,
            icon = icon,
            iconContentDescription = iconContentDescription,
            label = label,
            onInfoClick = onInfoClick,
            infoContentDescription = infoContentDescription,
            helperText = helperText,
            state = type,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onIconClick = onIconClick,
            onTextFieldClick = onTextFieldClick,
        )
    }
}

@Preview
@Composable
private fun SirioFilterInputPreview() {
    SirioTheme {
        Column {
            SirioFilterInput(
                label = "Label",
                text = "Text",
                onValueChange = {},
            )
        }
    }
}
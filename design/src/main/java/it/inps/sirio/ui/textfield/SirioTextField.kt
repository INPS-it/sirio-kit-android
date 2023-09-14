//
// SirioTextField.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio text field component
 *
 * @param text The current text field text
 * @param modifier A [Modifier] for customizing the appearance and behavior of the text field component
 * @param secureText Whether the text should be obfuscated, eg password
 * @param onValueChange The callback on text changed
 * @param placeholder The string in text field when [text] is empty
 * @param icon The FA icon [FaIcons] placed at the end of text field
 * @param iconContentDescription The content description for the icon
 * @param label The optional text on top of text field
 * @param onInfoClick The optional callback on info icon click
 * @param infoContentDescription The content description for the info icon
 * @param helperText The optionl text on bottom of text field
 * @param type The semantic [TextFieldSemantic] of text field
 * @param enabled Whether the text field can be edited by user
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardOptions.KeyboardType] and [KeyboardOptions.ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what you specified in [KeyboardOptions.imeAction].
 * @param onIconClick The callback on [icon] click
 */
@Composable
fun SirioTextField(
    text: String,
    modifier: Modifier = Modifier,
    secureText: Boolean = false,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    icon: FaIconType? = null,
    iconContentDescription: String? = null,
    label: String? = null,
    onInfoClick: (() -> Unit)? = null,
    infoContentDescription: String? = null,
    helperText: String? = null,
    type: TextFieldSemantic? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onIconClick: (() -> Unit)? = null,
    onTextFieldClick: (() -> Unit)? = null,
) {
    SirioTextFieldCommon(
        modifier = modifier,
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
        type = type,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onIconClick = onIconClick,
        onTextFieldClick = onTextFieldClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.ExclamationCircle,
                type = TextFieldSemantic.WARNING,
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.ExclamationTriangle,
                type = TextFieldSemantic.ALERT,
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.Check,
                type = TextFieldSemantic.SUCCESS,
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.Calendar,
                type = TextFieldSemantic.INFO,
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.Calendar,
                enabled = false,
            )
            SirioTextField(
                text = "Text",
                secureText = true,
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.EyeSlash,
                enabled = false,
            )
        }
    }
}
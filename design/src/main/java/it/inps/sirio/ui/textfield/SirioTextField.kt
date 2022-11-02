//
// SirioTextField.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
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
 * @param onValueChange The callback on text changed
 * @param placeholder The string in text field when [text] is empty
 * @param icon The FA icon [FaIcons] placed at the end of text field
 * @param label The optional text on top of text field
 * @param onInfoClick The optional callback on info icon click
 * @param helperText The optionl text on bottom of text field
 * @param type The semantic [TextFieldSemantic] of text field
 * @param enabled Whether the text field can be edited by user
 * @param onIconClick The callback on [icon] click
 */
@Composable
fun SirioTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    icon: FaIconType? = null,
    label: String? = null,
    onInfoClick: (() -> Unit)? = null,
    helperText: String? = null,
    type: TextFieldSemantic? = null,
    enabled: Boolean = true,
    onIconClick: (() -> Unit)? = null,
//    onTextFieldClick: (() -> Unit)? = null,
) {
    SirioTextFieldCommon(
        text = text,
        onValueChange = onValueChange,
        placeholder = placeholder,
        icon = icon,
        label = label,
        onInfoClick = onInfoClick,
        helperText = helperText,
        type = type,
        enabled = enabled,
        onIconClick = onIconClick,
//        onTextFieldClick = onTextFieldClick,
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
        }
    }
}
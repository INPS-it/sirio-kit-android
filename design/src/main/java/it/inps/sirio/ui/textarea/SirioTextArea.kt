//
// SirioTextArea.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.textarea

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio text area component
 *
 * @param text The current text area text
 * @param onValueChange The callback on text changed
 * @param placeholder The string in text area when [text] is empty
 * @param label The optional text on top of text area
 * @param onInfoClick The optional callback on info icon click
 * @param infoContentDescription The content description for the info icon
 * @param helperText The optional text on bottom of text area
 * @param type The semantic [TextAreaSemantic] of text area
 * @param enabled Whether the text area can be edited by user
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what you specified in [KeyboardOptions.imeAction].
 * @param onIconClick The callback on icon click
 * @param onTextAreaClick The callback on text area click
 */
@Composable
fun SirioTextArea(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    label: String? = null,
    onInfoClick: (() -> Unit)? = null,
    infoContentDescription: String? = null,
    helperText: String? = null,
    type: TextAreaSemantic? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onIconClick: (() -> Unit)? = null,
    onTextAreaClick: (() -> Unit)? = null,
) {
    SirioTextAreaCommon(
        text = text,
        onValueChange = onValueChange,
        placeholder = placeholder,
        label = label,
        onInfoClick = onInfoClick,
        infoContentDescription = infoContentDescription,
        helperText = helperText,
        type = type,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onIconClick = onIconClick,
        onTextAreaClick = onTextAreaClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TextAreaPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextArea(
                text = "Text",
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
            )
            SirioTextArea(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = null,
                type = TextAreaSemantic.ALERT,
            )
            SirioTextArea(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                type = TextAreaSemantic.SUCCESS,
            )
            SirioTextArea(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                enabled = false,
                type = TextAreaSemantic.SUCCESS
            )
        }
    }
}
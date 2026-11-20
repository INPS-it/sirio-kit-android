//
// SirioTextArea.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme

/**
 * A Sirio-styled multi-line text input component.
 *
 * This overload uses a simple [String] as value, making it easy to use for
 * standard text input scenarios where advanced editing features (such as
 * cursor position, selection, or IME composition) are not required.
 *
 * For more advanced use cases, prefer the overload that accepts a [TextFieldValue].
 *
 * @param text The current text displayed in the text area.
 * @param onValueChange Callback invoked when the text changes.
 * @param label Optional label displayed above the text area.
 * @param placeholder Optional placeholder shown when the text is empty.
 * @param onInfoClick Optional callback triggered when the info icon is clicked.
 * @param infoContentDescription Content description for the info icon (accessibility).
 * @param helperText Optional helper text displayed below the text area.
 * @param state Optional state of the text area (e.g. [TextAreaState.Alert], [TextAreaState.Success]).
 * @param enabled Whether the text area is enabled for user input.
 * @param keyboardOptions Software keyboard configuration.
 * @param keyboardActions Actions triggered by the keyboard.
 * @param onIconClick Optional callback triggered when the trailing icon is clicked.
 * @param onTextAreaClick Optional callback triggered when the text area is tapped.
 * If provided, the text area becomes read-only and acts as a clickable container.
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
    state: TextAreaState? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onIconClick: (() -> Unit)? = null,
    onTextAreaClick: (() -> Unit)? = null,
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = text)) }
    val textFieldValue = textFieldValueState.copy(text = text)

    SideEffect {
        if (
            textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }
    var lastTextValue by remember(text) { mutableStateOf(text) }

    SirioTextAreaCommon(
        text = textFieldValue,
        onValueChange = { newTextFieldValueState ->
            textFieldValueState = newTextFieldValueState

            val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
            lastTextValue = newTextFieldValueState.text

            if (stringChangedSinceLastInvocation) {
                onValueChange(newTextFieldValueState.text)
            }
        },
        placeholder = placeholder,
        label = label,
        onInfoClick = onInfoClick,
        infoContentDescription = infoContentDescription,
        helperText = helperText,
        state = state,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onIconClick = onIconClick,
        onTextAreaClick = onTextAreaClick,
    )
}

/**
 * A Sirio-styled multi-line text input component.
 *
 * This overload uses [TextFieldValue], allowing full control over the text input state,
 * including cursor position, text selection, and IME composition.
 *
 * Use this version when you need advanced text editing behavior or need to preserve
 * selection and cursor across recompositions.
 *
 * @param text The current [TextFieldValue] representing the text and its editing state.
 * @param onValueChange Callback invoked when the text or its editing state changes.
 * @param label Optional label displayed above the text area.
 * @param placeholder Optional placeholder shown when the text is empty.
 * @param onInfoClick Optional callback triggered when the info icon is clicked.
 * @param infoContentDescription Content description for the info icon (accessibility).
 * @param helperText Optional helper text displayed below the text area.
 * @param state Optional state of the text area (e.g. [TextAreaState.Alert], [TextAreaState.Success]).
 * @param enabled Whether the text area is enabled for user input.
 * @param keyboardOptions Software keyboard configuration.
 * @param keyboardActions Actions triggered by the keyboard.
 * @param onIconClick Optional callback triggered when the trailing icon is clicked.
 * @param onTextAreaClick Optional callback triggered when the text area is tapped.
 * If provided, the text area becomes read-only and acts as a clickable container.
 */
@Composable
fun SirioTextArea(
    text: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    onInfoClick: (() -> Unit)? = null,
    infoContentDescription: String? = null,
    helperText: String? = null,
    state: TextAreaState? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onIconClick: (() -> Unit)? = null,
    onTextAreaClick: (() -> Unit)? = null,
) {
    SirioTextAreaCommon(
        text = text,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        onInfoClick = onInfoClick,
        infoContentDescription = infoContentDescription,
        helperText = helperText,
        state = state,
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
                helperText = "Helper text",
                onInfoClick = null,
                state = TextAreaState.Alert,
            )
            SirioTextArea(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                onInfoClick = {},
                state = TextAreaState.Success,
            )
        }
    }
}

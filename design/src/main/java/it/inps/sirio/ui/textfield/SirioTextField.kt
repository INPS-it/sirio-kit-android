//
// SirioTextField.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.textfield

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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.popover.SirioPopoverData
import it.inps.sirio.utils.SirioIconSource

/**
 * A customizable text input field that adheres to the Sirio design system.
 *
 * It provides a wrapper around [SirioTextFieldCommon] and supports features like labels,
 * helper text, icons, state management, and password masking.
 *
 * @param text The text to be displayed and edited in the text field.
 * @param onValueChange A callback that is triggered when the text value changes.
 * @param modifier The [Modifier] to be applied to the text field.
 * @param secureText A boolean indicating whether the text should be visually masked for password entry.
 * @param placeholder The optional text to be displayed when the text field is empty.
 * @param icon An optional [SirioIconSource] to be displayed at the start of the text field.
 * @param iconContentDescription The content description for the leading icon, for accessibility.
 * @param label An optional label displayed above the text field.
 * @param popoverData Optional data to display a [SirioPopover] next to the label for providing extra information.
 * @param helperText Optional descriptive text displayed below the text field.
 * @param type The current state of the text field, such as [TextFieldState.Alert] or [TextFieldState.Success].
 * @param enabled A boolean indicating whether the text field is enabled for user interaction.
 * @param keyboardOptions Software keyboard options that configure the keyboard type and IME action.
 * @param keyboardActions Callbacks to be invoked when the input service emits an IME action.
 * @param visualTransformation A transformation to apply to the visual representation of the input text, such as [PasswordVisualTransformation].
 * @param onIconClick An optional callback to be invoked when the leading icon is clicked.
 * @param onTextFieldClick An optional callback to be invoked when the text field is clicked. Setting this makes the field read-only and triggers this lambda.
 *
 * @see SirioTextFieldCommon
 * @see SirioPopover
 * @see TextFieldState
 */
@Composable
fun SirioTextField(
    text: String,
    modifier: Modifier = Modifier,
    secureText: Boolean = false,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    icon: SirioIconSource? = null,
    iconContentDescription: String? = null,
    label: String? = null,
    popoverData: SirioPopoverData? = null,
    helperText: String? = null,
    type: TextFieldState? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
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
        popoverData = popoverData,
        helperText = helperText,
        state = type,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        onIconClick = onIconClick,
        onTextFieldClick = onTextFieldClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldTextInfoIconHelperTextPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
                popoverData = SirioPopoverData(text = "Popover text"),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SemanticTextFieldTextIconHelperTextPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
                type = TextFieldState.Alert,
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
                type = TextFieldState.Warning,
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
                type = TextFieldState.Success,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldTextHelperTextPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                enabled = false,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldTextIconHelperTextPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "Helper text",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
                enabled = false,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldTextIconPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
                enabled = false,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldTextPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                label = "Label",
                enabled = false,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldWithoutLabelPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "Text",
                onValueChange = {},
            )
            SirioTextField(
                text = "Text",
                onValueChange = {},
                enabled = false,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldNumberPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextField(
                text = "0123",
                onValueChange = {},
                icon = SirioIconSource.FaIcon(FaIcons.Sort),
            )
            SirioTextField(
                text = "0123",
                onValueChange = {},
                icon = SirioIconSource.FaIcon(FaIcons.Sort),
                enabled = false,
            )
        }
    }
}

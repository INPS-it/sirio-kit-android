//
// SirioTextFieldCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.textfield

import androidx.annotation.Keep
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownMenuCornerSize
import it.inps.sirio.theme.textFieldBorderCornerRadius
import it.inps.sirio.theme.textFieldBorderWidth
import it.inps.sirio.theme.textFieldDropdownBorderWidth
import it.inps.sirio.theme.textFieldDropdownMaxHeight
import it.inps.sirio.theme.textFieldDropdownOptionHeight
import it.inps.sirio.theme.textFieldDropdownOptionHorizontalPadding
import it.inps.sirio.theme.textFieldHeight
import it.inps.sirio.theme.textFieldIconSize
import it.inps.sirio.theme.textFieldInfoIconSize
import it.inps.sirio.theme.textFieldLabelVerticalPadding
import it.inps.sirio.theme.textFieldPaddingBottom
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
 * A custom composable function representing a common text field component in the Sirio UI.
 *
 * This function creates a versatile text field that can be customized with various properties
 * such as text content, secure text input, placeholder, icons, labels, helper text, and more.
 * It supports optional dropdown functionality for selecting from a list of predefined values.
 *
 * @param modifier The modifier for the text field layout.
 * @param text The current text value of the text field.
 * @param secureText A boolean indicating whether the text should be visually masked (e.g., for passwords).
 * @param onValueChange A callback invoked when the text value changes, passing the new text as a parameter.
 * @param placeholder An optional string to display as a placeholder when the text field is empty.
 * @param icon An optional Font Awesome icon to display on the leading side of the text field.
 * @param iconButton An optional Font Awesome icon button to display on the trailing side of the text field.
 * @param iconContentDescription Content description for the icon, for accessibility.
 * @param iconButtonContentDescription Content description for the icon button, for accessibility.
 * @param label An optional label string to display above the text field.
 * @param onInfoClick An optional callback for when the info icon (if present) is clicked.
 * @param infoContentDescription Content description for the info icon, for accessibility.
 * @param helperText An optional string to display as a helper text below the text field.
 * @param optionValues An array of string values used for the dropdown list, if needed.
 * @param onOptionValueSelected A callback invoked when an option value is selected from the dropdown list.
 * @param state An optional [TextFieldState] to represent the state (e.g., warning, error) of the text field.
 * @param backgroundColor The background color for the text field.
 * @param enabled A boolean indicating whether the text field is enabled for interaction.
 * @param keyboardOptions The keyboard options for the text field (e.g., keyboard type, IME actions).
 * @param keyboardActions The keyboard actions for the text field (e.g., submit action).
 * @param onDropdownStateChange A callback invoked when the dropdown state (open/closed) changes.
 * @param onIconClick A callback invoked when the main icon (if present) is clicked.
 * @param onIconButtonClick A callback invoked when the icon button (if present) is clicked, passing the current text value as a parameter.
 * @param onTextFieldClick A callback invoked when the text field itself is clicked.
 *
 * @see TextFieldState
 * @see SirioIcon
 * @see SirioButton
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioTextFieldCommon(
    modifier: Modifier = Modifier,
    text: String = "",
    secureText: Boolean = false,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    icon: FaIconType? = null,
    iconButton: FaIconType? = null,
    iconContentDescription: String? = null,
    iconButtonContentDescription: String? = null,
    label: String? = null,
    onInfoClick: (() -> Unit)? = null,
    infoContentDescription: String? = null,
    helperText: String? = null,
    optionValues: Array<String> = emptyArray(),
    onOptionValueSelected: ((value: String) -> Unit)? = null,
    state: TextFieldState? = null,
    backgroundColor: Color? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onDropdownStateChange: ((open: Boolean) -> Unit)? = null,
    onIconClick: (() -> Unit)? = null,
    onIconButtonClick: ((text: String) -> Unit)? = null,
    onTextFieldClick: (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val isNumber = text.toDoubleOrNull() != null
    val stateColor: Color? = if (enabled) state?.toColor() else null
    val stateIcon: FaIconType? = if (optionValues.isNotEmpty()) icon else state?.toIcon()
    val labelColor = stateColor ?: SirioTheme.colors.textField.label.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val placeholderTextColor = stateColor ?: SirioTheme.colors.textField.placeholder.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val textColor = stateColor ?: SirioTheme.colors.textField.text.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val containerColor = backgroundColor ?: SirioTheme.colors.textField.background.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val borderColor = stateColor ?: SirioTheme.colors.textField.border.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val infoIconColor = SirioTheme.colors.textField.infoIcon.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val iconColor = stateColor ?: SirioTheme.colors.textField.icon.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val helperTextColor = stateColor ?: SirioTheme.colors.textField.helperText.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val dropdownBorderColor = SirioTheme.colors.textField.dropdownBorder.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )

    val textStyle =
        if (isNumber) SirioTheme.foundationTypography.labelNumberMdRegular
        else SirioTheme.foundationTypography.labelMdRegular

    Column(modifier = modifier) {
        label?.let {
            Row(Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
                SirioTextCommon(
                    text = it,
                    color = labelColor,
                    typography = SirioTheme.foundationTypography.labelMdMiddle,
                )
                onInfoClick?.let { infoClickAction ->
                    IconButton(onClick = infoClickAction) {
                        SirioIcon(
                            icon = SirioIconSource.FaIcon(FaIcons.InfoCircle),
                            size = textFieldInfoIconSize.dp,
                            iconColor = infoIconColor,
                            contentDescription = infoContentDescription,
                        )
                    }
                }
            }
            if (onInfoClick == null) Spacer(modifier = Modifier.height(textFieldLabelVerticalPadding.dp))
        }
        val colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = SirioTheme.colors.textField.text.disabled,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = SirioTheme.colors.textField.background.disabled,
            cursorColor = SirioTheme.colors.textField.text.default,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            disabledBorderColor = SirioTheme.colors.textField.border.disabled,
            errorBorderColor = SirioTheme.colors.textField.border.alert,
            focusedTrailingIconColor = iconColor,
            unfocusedTrailingIconColor = iconColor,
            disabledTrailingIconColor = SirioTheme.colors.textField.icon.disabled,
            errorTrailingIconColor = SirioTheme.colors.textField.icon.alert,
            focusedPlaceholderColor = placeholderTextColor,
            unfocusedPlaceholderColor = placeholderTextColor,
            disabledPlaceholderColor = SirioTheme.colors.textField.placeholder.disabled,
        )

        val clickable =
            if (onTextFieldClick != null) {
                Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                        onClick = onTextFieldClick,
                    )
            } else {
                Modifier
            }
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .height(textFieldHeight.dp)
                .fillMaxWidth()
                .onFocusChanged {
                    onDropdownStateChange?.invoke(it.hasFocus && optionValues.isNotEmpty())
                }
                .then(clickable),
            enabled = enabled && onTextFieldClick == null,
            readOnly = onTextFieldClick != null,
            textStyle = textStyle.merge(TextStyle(color = textColor)),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            visualTransformation = if (secureText) PasswordVisualTransformation() else VisualTransformation.None,
//            cursorBrush = SolidColor(textColor),
            interactionSource = interactionSource,
            decorationBox = @Composable { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = text,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    placeholder = {
                        placeholder?.let {
                            SirioTextCommon(
                                text = it,
                                color = placeholderTextColor,
                                typography = SirioTheme.foundationTypography.labelMdRegular,
                            )
                        }
                    },
                    trailingIcon = if (icon == null && iconButton == null && stateIcon == null) null
                    else {
                        {
                            TrailingIcon(
                                icon = stateIcon ?: icon,
                                iconColor = iconColor,
                                iconButton = iconButton,
                                iconContentDescription = iconContentDescription,
                                iconButtonContentDescription = iconButtonContentDescription,
                                enabled = enabled,
                                onIconClick = onIconClick,
                                onIconButtonClick = { onIconButtonClick?.invoke(text) },
                            )
                        }
                    },
                    colors = colors,
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                    container = {
                        OutlinedTextFieldDefaults.Container(
                            enabled = enabled,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = colors,
                            shape = RoundedCornerShape(textFieldBorderCornerRadius.dp),
                            focusedBorderThickness = textFieldBorderWidth.dp,
                            unfocusedBorderThickness = textFieldBorderWidth.dp,
                        )
                    },
                )
            },
        )

        Spacer(modifier = Modifier.height(textFieldPaddingBottom.dp))
        if (isFocused && optionValues.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .heightIn(max = textFieldDropdownMaxHeight.dp)
                    .border(
                        width = textFieldDropdownBorderWidth.dp,
                        color = dropdownBorderColor,
                        shape = RoundedCornerShape(dropdownMenuCornerSize.dp),
                    )
            ) {
                items(items = optionValues, key = { it }, contentType = { String }) {
                    SirioTextFieldOptionItem(
                        value = it,
                        enabled = enabled,
                        onItemClick = { onOptionValueSelected?.invoke(it) }
                    )
                }
            }
        } else {
            helperText?.let {
                SirioTextCommon(
                    text = it,
                    color = helperTextColor,
                    typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
                )
            }
        }
    }
}

/**
 * View containing a single [SirioTextFieldCommon]'s option value
 *
 * @param value The option value string
 * @param enabled Whether the option value can be selected
 * @param onItemClick The callback when user select this option value
 */
@Composable
private fun SirioTextFieldOptionItem(
    value: String,
    enabled: Boolean,
    onItemClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val optionBackgroundColor =
        SirioTheme.colors.textField.optionBackground.get(pressed = isPressed, disabled = !enabled)
    val optionTextColor =
        SirioTheme.colors.textField.optionText.get(pressed = isPressed, disabled = !enabled)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(textFieldDropdownOptionHeight.dp)
            .background(color = optionBackgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onItemClick,
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        SirioTextCommon(
            text = value,
            modifier = Modifier.padding(horizontal = textFieldDropdownOptionHorizontalPadding.dp),
            color = optionTextColor,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            typography = SirioTheme.foundationTypography.labelMdRegular,
        )
    }
}

/**
 * [SirioTextFieldCommon] trailing. It contain the internal clickable icon and an external icon button, both are optional
 *
 * @param icon The optional clickable [FaIcons]
 * @param iconColor The tint color of [icon]
 * @param iconButton The [FaIcons] in the button, if null no button is placed in trailing
 * @param enabled Whether the icons can be pressed
 * @param iconContentDescription The content description for the icon
 * @param iconButtonContentDescription The content description for the icon button
 * @param onIconClick The callback invoked on [icon] tap
 * @param onIconButtonClick The [iconButton] on click callback
 */
@Composable
private fun TrailingIcon(
    icon: FaIconType?,
    iconColor: Color,
    iconButton: FaIconType?,
    enabled: Boolean,
    iconContentDescription: String? = null,
    iconButtonContentDescription: String? = null,
    onIconClick: (() -> Unit)?,
    onIconButtonClick: (() -> Unit)?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        icon?.let {
            IconButton(onClick = onIconClick ?: {}, enabled = enabled) {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(it),
                    size = textFieldIconSize.dp,
                    iconColor = iconColor,
                    contentDescription = iconContentDescription,
                )
            }
        }
        iconButton?.let {
            SirioButton(
                size = SirioButtonSize.Large,
                hierarchy = SirioButtonHierarchy.Primary,
                icon = SirioIconSource.FaIcon(iconButton),
                enabled = enabled,
                iconContentDescription = iconButtonContentDescription,
                onClick = onIconButtonClick ?: {},
            )
        }
    }
}

@Keep
data class SirioTextFieldColors(
    var background: SirioColorState,
    var border: SirioColorState,
    var label: SirioColorState,
    var infoIcon: SirioColorState,
    var icon: SirioColorState,
    var helperText: SirioColorState,
    var placeholder: SirioColorState,
    var text: SirioColorState,
    var dropdownBorder: SirioColorState,
    var optionBackground: SirioColorState,
    var optionText: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioTextFieldColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            label = SirioColorState.Unspecified,
            infoIcon = SirioColorState.Unspecified,
            icon = SirioColorState.Unspecified,
            helperText = SirioColorState.Unspecified,
            placeholder = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
            dropdownBorder = SirioColorState.Unspecified,
            optionBackground = SirioColorState.Unspecified,
            optionText = SirioColorState.Unspecified,
        )
    }
}

internal val textFieldLightColors = SirioTextFieldColors(
    background = SirioColorState.all(
        color = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    border = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        alert = FoundationColor.colorSpecificDataEntryBorderColorAlert,
        warning = FoundationColor.colorSpecificDataEntryBorderColorWarning,
        success = FoundationColor.colorSpecificDataEntryBorderColorSuccess,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryBorderColorDefault,
    ),
    label = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryLabelColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryLabelColorValued,
        alert = FoundationColor.colorSpecificDataEntryLabelColorAlert,
        warning = FoundationColor.colorSpecificDataEntryLabelColorWarning,
        success = FoundationColor.colorSpecificDataEntryLabelColorSuccess,
    ),
    infoIcon = SirioColorState.all(color = FoundationColor.colorGlobalSemanticInfo100),
    icon = SirioColorState(
        disabled = FoundationColor.colorAliasInteractiveSecondaryDefault,
        default = FoundationColor.colorAliasInteractiveSecondaryDefault,
        valued = FoundationColor.colorAliasInteractiveSecondaryDefault,
        alert = FoundationColor.colorGlobalSemanticAlert100,
        warning = FoundationColor.colorGlobalSemanticWarning100,
        success = FoundationColor.colorGlobalSemanticSuccess100,
    ),
    helperText = SirioColorState(
        default = FoundationColor.colorAliasInteractiveSecondaryDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorAliasInteractiveSecondaryDefault,
        alert = FoundationColor.colorSpecificDataEntryLabelColorAlert,
        warning = FoundationColor.colorSpecificDataEntryLabelColorWarning,
        success = FoundationColor.colorSpecificDataEntryLabelColorSuccess,
    ),
    placeholder = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryPlaceholderColorValued,
        alert = FoundationColor.colorSpecificDataEntryPlaceholderColorAlert,
        warning = FoundationColor.colorSpecificDataEntryPlaceholderColorWarning,
        success = FoundationColor.colorSpecificDataEntryPlaceholderColorSuccess,
    ),
    text = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryLabelColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryLabelColorValued,
        alert = FoundationColor.colorSpecificDataEntryLabelColorAlert,
        warning = FoundationColor.colorSpecificDataEntryLabelColorWarning,
        success = FoundationColor.colorSpecificDataEntryLabelColorSuccess,
    ),
    dropdownBorder = SirioColorState.all(FoundationColor.colorSpecificDataEntryBorderColorDefault),
    optionBackground = SirioColorState.all(FoundationColor.colorAliasBackgroundColorPrimaryLight0),
    optionText = SirioColorState.all(FoundationColor.colorSpecificDataEntryPlaceholderColorDefault),
)

internal val textFieldDarkColors = textFieldLightColors

@Composable
internal fun TextFieldState.toColor(): Color = when (this) {
    TextFieldState.Alert -> SirioTheme.colors.textField.border.alert
    TextFieldState.Warning -> SirioTheme.colors.textField.border.warning
    TextFieldState.Success -> SirioTheme.colors.textField.border.success
}

internal fun TextFieldState.toIcon(): FaIconType = when (this) {
    TextFieldState.Alert -> FaIcons.ExclamationTriangle
    TextFieldState.Warning -> FaIcons.ExclamationCircle
    TextFieldState.Success -> FaIcons.Check
}

enum class TextFieldState {
    Warning,
    Alert,
    Success,
}

//@Preview(showSystemUi = false)
//@Composable
//private fun SirioTextFieldOptionItemPreview() {
//    SirioTheme {
//        SirioTextFieldOptionItem(value = "Value", enabled = true, onItemClick = {})
//    }
//}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var text by remember { mutableStateOf("Text") }
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                icon = FaIcons.Times,
                iconButton = FaIcons.Search,
                label = "Label",
                helperText = "*Helper text",
                optionValues = arrayOf(
                    "Option Value 1",
                    "Option Value 2",
                    "Option Value 3",
                    "Option Value 4",
                ),
                state = TextFieldState.Warning,
            )
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                icon = FaIcons.ExclamationCircle,
                label = "Label",
                onInfoClick = {},
                helperText = "*Helper text",
                state = TextFieldState.Warning,
            )
            SirioTextFieldCommon(
                text = text,
                placeholder = "Placeholder",
                onValueChange = { text = it },
                icon = FaIcons.ExclamationTriangle,
                label = "Label",
                onInfoClick = {},
                helperText = "*Helper text",
                state = TextFieldState.Alert,
            )
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                icon = FaIcons.Check,
                label = "Label",
                onInfoClick = {},
                helperText = "*Helper text",
                state = TextFieldState.Success,
            )
            SirioTextFieldCommon(
                state = TextFieldState.Success,
                text = "Text",
                onValueChange = {},
                icon = FaIcons.CalendarDay,
                label = "Label",
                onInfoClick = {},
                helperText = "*Helper text",
                enabled = false,
            )
        }
    }
}

@Preview
@Composable
private fun TextFieldInRowPreview() {
    SirioTheme {
        Row(Modifier.background(Color.White)) {
            SirioTextFieldCommon(
                modifier = Modifier.weight(1f),
                text = "",
                onValueChange = {},
                label = "RC",
            )
            Text(
                text = "/",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Bottom)
            )
            SirioTextFieldCommon(
                modifier = Modifier.weight(2f),
                text = "",
                onValueChange = {},
                label = "ANNO",
            )
        }
    }
}
//
// SirioTextFieldCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.textFieldBorderWidth
import it.inps.sirio.theme.textFieldDropdownBorderWidth
import it.inps.sirio.theme.textFieldDropdownMaxHeight
import it.inps.sirio.theme.textFieldDropdownOptionHeight
import it.inps.sirio.theme.textFieldDropdownOptionHorizontalPadding
import it.inps.sirio.theme.textFieldFocusedBorderPadding
import it.inps.sirio.theme.textFieldFocusedExtraBorderWidth
import it.inps.sirio.theme.textFieldIconSize
import it.inps.sirio.theme.textFieldInfoIconSize
import it.inps.sirio.theme.textFieldLabelVerticalPadding
import it.inps.sirio.theme.textFieldPaddingBottom
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio text field implementation
 *
 * @param modifier A [Modifier] for customizing the appearance and behavior of the text field component
 * @param text The current text field text
 * @param secureText Whether the text should be obfuscated, eg password
 * @param onValueChange The callback on text changed
 * @param placeholder The string in text field when [text] is empty
 * @param icon The FA icon [FaIcons] placed at the end of text field
 * @param iconButton The FA icon [FaIcons] in the button on the right of text field
 * @param iconContentDescription The content description for the icon
 * @param iconButtonContentDescription The content description for the icon button
 * @param label The optional text on top of text field
 * @param onInfoClick The optional callback on info icon click
 * @param infoContentDescription The content description for the info icon
 * @param helperText The optional text on bottom of text field
 * @param optionValues An array of string as hints for user
 * @param onOptionValueSelected The callback when user select an option value from [optionValues]
 * @param type The semantic [TextFieldSemantic] of text field
 * @param backgroundColor The optional color used to force the background instead of default one
 * @param enabled Whether the text field can be edited by user
 * @param disableExtraBorder Used by other Sirio component to disable extra border in focus status
 * @param keyboardOptions software keyboard options that contains configuration such as [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback is called. Note that this IME action may be different from what you specified in [KeyboardOptions.imeAction].
 * @param onDropdownStateChange The callback invoked when dropdown with [optionValues] is opened/closed
 * @param onIconClick The callback on [icon] click
 * @param onIconButtonClick The callback on [iconButton] button click
 * @param onTextFieldClick The callback on TextField click used to handle custom input. If the callback is provided the textfield is disabled to allow the action, normal behaviour otherwise
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
    type: TextFieldSemantic? = null,
    backgroundColor: Color? = null,
    enabled: Boolean = true,
    disableExtraBorder: Boolean = false,
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
    val isHovered by interactionSource.collectIsHoveredAsState()

    val textFieldParams = getTextFieldParams(enabled, type, isFocused, isPressed, isHovered)

    Column(modifier = modifier) {
        label?.let {
            Row(Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
                SirioTextCommon(
                    text = it,
                    color = textFieldParams.labelColor,
                    typography = SirioTheme.typography.textFieldLabel,
                )
                onInfoClick?.let { itc ->
                    IconButton(onClick = itc) {
                        SirioIcon(
                            faIcon = FaIcons.InfoCircle,
                            size = textFieldInfoIconSize,
                            iconColor = textFieldParams.infoIconColor,
                            contentDescription = infoContentDescription,
                        )
                    }
                }
            }
            if (onInfoClick == null) Spacer(modifier = Modifier.height(textFieldLabelVerticalPadding))
        }
        Box(
            modifier = if (type == null && isFocused && !disableExtraBorder) {
                Modifier
                    .border(
                        width = textFieldFocusedExtraBorderWidth,
                        color = SirioTheme.colors.textField.extraBorder,
                        shape = Shapes.small,
                    )
                    .padding(textFieldFocusedBorderPadding)
            } else Modifier.padding(0.dp),
        ) {
            val colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textFieldParams.textColor,
                unfocusedTextColor = textFieldParams.textColor,
                disabledTextColor = textFieldParams.textColor,
                focusedContainerColor = backgroundColor ?: textFieldParams.backgroundColor,
                unfocusedContainerColor = backgroundColor ?: textFieldParams.backgroundColor,
                cursorColor = textFieldParams.textColor,
                focusedBorderColor = textFieldParams.borderColor,
                unfocusedBorderColor = textFieldParams.borderColor,
                disabledBorderColor = textFieldParams.borderColor,
                errorBorderColor = textFieldParams.borderColor,
                unfocusedTrailingIconColor = textFieldParams.iconColor,
                disabledTrailingIconColor = textFieldParams.iconColor,
                errorTrailingIconColor = textFieldParams.iconColor,
                focusedTrailingIconColor = textFieldParams.iconColor,
                focusedPlaceholderColor = textFieldParams.placeholderTextColor,
                unfocusedPlaceholderColor = textFieldParams.placeholderTextColor,
                disabledPlaceholderColor = textFieldParams.placeholderTextColor,
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
                    .fillMaxWidth()
                    .onFocusChanged {
                        onDropdownStateChange?.invoke(it.hasFocus && optionValues.isNotEmpty())
                    }
                    .then(clickable),
                enabled = enabled && onTextFieldClick == null,
                readOnly = onTextFieldClick != null,
                textStyle = SirioTheme.typography.textFieldText.merge(TextStyle(color = textFieldParams.textColor)),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                visualTransformation = if (secureText) PasswordVisualTransformation() else VisualTransformation.None,
                cursorBrush = SolidColor(textFieldParams.textColor),
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
                                    color = textFieldParams.placeholderTextColor,
                                    typography = SirioTheme.typography.textFieldPlaceholder,
                                )
                            }
                        },
                        trailingIcon = if (icon == null && iconButton == null) null
                        else {
                            {
                                TrailingIcon(
                                    icon = icon,
                                    iconColor = textFieldParams.iconColor,
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
                        contentPadding = OutlinedTextFieldDefaults.contentPadding(),
                        container = {
                            OutlinedTextFieldDefaults.ContainerBox(
                                enabled = enabled,
                                isError = false,
                                interactionSource = interactionSource,
                                colors = colors,
                                shape = Shapes.small,
                                focusedBorderThickness = textFieldBorderWidth,
                                unfocusedBorderThickness = textFieldBorderWidth,
                            )
                        },
                    )
                },
            )
        }
        Spacer(modifier = Modifier.height(textFieldPaddingBottom))
        if (isFocused && optionValues.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .heightIn(max = textFieldDropdownMaxHeight)
                    .border(
                        width = textFieldDropdownBorderWidth,
                        color = textFieldParams.dropdownBorderColor,
                        shape = Shapes.small,
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
                    color = textFieldParams.helperTextColor,
                    typography = SirioTheme.typography.textFieldHelperText,
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
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val optionItemParams = getOptionItemParams(enabled, isFocused, isPressed, isHovered)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(textFieldDropdownOptionHeight)
            .background(color = optionItemParams.optionBackgroundColor)
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
            modifier = Modifier.padding(horizontal = textFieldDropdownOptionHorizontalPadding),
            color = optionItemParams.optionTextColor,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            typography = SirioTheme.typography.textFieldDropdownLabel,
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
                    faIcon = it,
                    size = textFieldIconSize,
                    iconColor = iconColor,
                    contentDescription = iconContentDescription,
                )
            }
        }
        iconButton?.let {
            SirioButton(
                size = ButtonSize.Large,
                style = ButtonStyle.Primary,
                icon = iconButton,
                enabled = enabled,
                iconContentDescription = iconButtonContentDescription,
                onClick = onIconButtonClick ?: {},
            )
        }
    }
}

/**
 * @return The text field color based on its status
 */
@Composable
private fun getTextFieldParams(
    enabled: Boolean,
    type: TextFieldSemantic?,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
): TextFieldParams = when {
    !enabled -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.disabled,
        infoIconColor = SirioTheme.colors.textField.label.disabled,
        labelColor = SirioTheme.colors.textField.label.disabled,
        helperTextColor = SirioTheme.colors.textField.helperText.disabled,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.disabled,
        textColor = SirioTheme.colors.textField.text.disabled,
        iconColor = SirioTheme.colors.textField.icon.disabled,
        borderColor = SirioTheme.colors.textField.border.disabled,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.disabled,
    )

    type == TextFieldSemantic.ALERT -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.alert,
        infoIconColor = SirioTheme.colors.textField.label.alert,
        labelColor = SirioTheme.colors.textField.label.alert,
        helperTextColor = SirioTheme.colors.textField.helperText.alert,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.alert,
        textColor = SirioTheme.colors.textField.text.alert,
        iconColor = SirioTheme.colors.textField.icon.alert,
        borderColor = SirioTheme.colors.textField.border.alert,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.alert,
    )

    type == TextFieldSemantic.WARNING -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.warning,
        infoIconColor = SirioTheme.colors.textField.label.warning,
        labelColor = SirioTheme.colors.textField.label.warning,
        helperTextColor = SirioTheme.colors.textField.helperText.warning,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.warning,
        textColor = SirioTheme.colors.textField.text.warning,
        iconColor = SirioTheme.colors.textField.icon.warning,
        borderColor = SirioTheme.colors.textField.border.warning,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.warning,
    )

    type == TextFieldSemantic.SUCCESS -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.success,
        infoIconColor = SirioTheme.colors.textField.label.success,
        labelColor = SirioTheme.colors.textField.label.success,
        helperTextColor = SirioTheme.colors.textField.helperText.success,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.success,
        textColor = SirioTheme.colors.textField.text.success,
        iconColor = SirioTheme.colors.textField.icon.success,
        borderColor = SirioTheme.colors.textField.border.success,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.success,
    )

    type == TextFieldSemantic.INFO -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.info,
        infoIconColor = SirioTheme.colors.textField.label.info,
        labelColor = SirioTheme.colors.textField.label.info,
        helperTextColor = SirioTheme.colors.textField.helperText.info,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.info,
        textColor = SirioTheme.colors.textField.text.info,
        iconColor = SirioTheme.colors.textField.icon.info,
        borderColor = SirioTheme.colors.textField.border.info,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.info,
    )

    isFocused -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.focused,
        infoIconColor = SirioTheme.colors.textField.label.focused,
        labelColor = SirioTheme.colors.textField.label.focused,
        helperTextColor = SirioTheme.colors.textField.helperText.focused,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.focused,
        textColor = SirioTheme.colors.textField.text.focused,
        iconColor = SirioTheme.colors.textField.icon.focused,
        borderColor = SirioTheme.colors.textField.border.focused,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.focused,
    )

    isPressed -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.pressed,
        infoIconColor = SirioTheme.colors.textField.label.pressed,
        labelColor = SirioTheme.colors.textField.label.pressed,
        helperTextColor = SirioTheme.colors.textField.helperText.pressed,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.pressed,
        textColor = SirioTheme.colors.textField.text.pressed,
        iconColor = SirioTheme.colors.textField.icon.pressed,
        borderColor = SirioTheme.colors.textField.border.pressed,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.pressed,
    )

    isHovered -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.hovered,
        infoIconColor = SirioTheme.colors.textField.label.hovered,
        labelColor = SirioTheme.colors.textField.label.hovered,
        helperTextColor = SirioTheme.colors.textField.helperText.hovered,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.hovered,
        textColor = SirioTheme.colors.textField.text.hovered,
        iconColor = SirioTheme.colors.textField.icon.hovered,
        borderColor = SirioTheme.colors.textField.border.hovered,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.hovered,
    )

    else -> TextFieldParams(
        backgroundColor = SirioTheme.colors.textField.background.default,
        infoIconColor = SirioTheme.colors.textField.label.hovered,
        labelColor = SirioTheme.colors.textField.label.default,
        helperTextColor = SirioTheme.colors.textField.helperText.default,
        placeholderTextColor = SirioTheme.colors.textField.placeholder.default,
        textColor = SirioTheme.colors.textField.text.default,
        iconColor = SirioTheme.colors.textField.icon.default,
        borderColor = SirioTheme.colors.textField.border.default,
        dropdownBorderColor = SirioTheme.colors.textField.dropdown.default,
    )
}

/**
 * @return The option item colors
 */
@Composable
private fun getOptionItemParams(
    enabled: Boolean,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
): OptionItemParams = when {
    !enabled -> OptionItemParams(
        optionBackgroundColor = SirioTheme.colors.textField.optionBackground.disabled,
        optionTextColor = SirioTheme.colors.textField.optionText.disabled,
    )

    isFocused -> OptionItemParams(
        optionBackgroundColor = SirioTheme.colors.textField.optionBackground.focused,
        optionTextColor = SirioTheme.colors.textField.optionText.focused,
    )

    isPressed -> OptionItemParams(
        optionBackgroundColor = SirioTheme.colors.textField.optionBackground.pressed,
        optionTextColor = SirioTheme.colors.textField.optionText.pressed,
    )

    isHovered -> OptionItemParams(
        optionBackgroundColor = SirioTheme.colors.textField.optionBackground.hovered,
        optionTextColor = SirioTheme.colors.textField.optionText.hovered,
    )

    else -> OptionItemParams(
        optionBackgroundColor = SirioTheme.colors.textField.optionBackground.default,
        optionTextColor = SirioTheme.colors.textField.optionText.default,
    )
}

@Keep
data class TextFieldParams(
    val backgroundColor: Color,
    val infoIconColor: Color,
    val labelColor: Color,
    val helperTextColor: Color,
    val textColor: Color,
    val placeholderTextColor: Color,
    val iconColor: Color,
    val borderColor: Color,
    val dropdownBorderColor: Color,
)

@Keep
data class OptionItemParams(
    val optionBackgroundColor: Color,
    val optionTextColor: Color,
)

@Keep
data class SirioTextFieldColors(
    var background: SirioColorState,
    var border: SirioColorState,
    var label: SirioColorState,
    var icon: SirioColorState,
    var helperText: SirioColorState,
    var placeholder: SirioColorState,
    var text: SirioColorState,
    var dropdown: SirioColorState,
    var optionBackground: SirioColorState,
    var optionText: SirioColorState,
    var extraBorder: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTextFieldColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            label = SirioColorState.Unspecified,
            icon = SirioColorState.Unspecified,
            helperText = SirioColorState.Unspecified,
            placeholder = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
            dropdown = SirioColorState.Unspecified,
            optionBackground = SirioColorState.Unspecified,
            optionText = SirioColorState.Unspecified,
            extraBorder = Color.Unspecified,
        )
    }
}

enum class TextFieldSemantic {
    WARNING,
    ALERT,
    SUCCESS,
    INFO,
}

@Preview(showSystemUi = false)
@Composable
private fun SirioTextFieldOptionItemPreview() {
    SirioTheme {
        SirioTextFieldOptionItem(value = "Value", enabled = true, onItemClick = {})
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                icon = FaIcons.Times,
                iconButton = FaIcons.Search,
                optionValues = arrayOf(
                    "Option Value 1",
                    "Option Value 2",
                    "Option Value 3",
                    "Option Value 4",
                )
            )
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.ExclamationCircle,
                type = TextFieldSemantic.WARNING,
            )
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.ExclamationTriangle,
                type = TextFieldSemantic.ALERT,
            )
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.Check,
                type = TextFieldSemantic.SUCCESS,
            )
            SirioTextFieldCommon(
                text = "Text",
                onValueChange = {},
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                icon = FaIcons.Calendar,
                type = TextFieldSemantic.INFO,
            )
            SirioTextFieldCommon(
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

@Preview
@Composable
private fun TextFieldInRowPreview() {
    SirioTheme {
        Row(Modifier.background(Color.White)) {
            SirioTextFieldCommon(
                text = "",
                onValueChange = {},
                label = "XX",
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "/",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Bottom)
            )
            SirioTextFieldCommon(
                text = "",
                onValueChange = {},
                label = "YYYY",
                modifier = Modifier.weight(2f),
            )
        }
    }
}

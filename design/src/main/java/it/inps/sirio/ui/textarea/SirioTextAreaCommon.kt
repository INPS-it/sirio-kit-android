//
// SirioTextAreaCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.textarea


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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.textAreaBorderWidth
import it.inps.sirio.theme.textAreaDefaultHeight
import it.inps.sirio.theme.textAreaFocusedBorderPadding
import it.inps.sirio.theme.textAreaFocusedExtraBorderWidth
import it.inps.sirio.theme.textAreaIconSize
import it.inps.sirio.theme.textAreaIconTopPadding
import it.inps.sirio.theme.textAreaInfoIconSize
import it.inps.sirio.theme.textAreaLabelVerticalPadding
import it.inps.sirio.theme.textAreaPaddingBottom
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio text area common implementation
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
 * @param onTextAreaClick The callback on text area click used to handle custom input. If the callback is provided the text area is disabled to allow the action, normal behaviour otherwise
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioTextAreaCommon(
    text: String = "",
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val textAreaParams = getTextAreaParams(
        enabled = enabled,
        type = type,
        isFocused = isFocused,
        isPressed = isPressed,
        isHovered = isHovered,
        isValued = text.isNotEmpty()
    )

    Column {
        label?.let {
            Row(Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
                SirioTextCommon(
                    text = it,
                    color = textAreaParams.labelColor,
                    typography = SirioTheme.typography.textAreaLabel,
                )
                onInfoClick?.let { itc ->
                    IconButton(onClick = itc) {
                        SirioIcon(
                            faIcon = FaIcons.InfoCircle,
                            size = textAreaInfoIconSize,
                            iconColor = textAreaParams.infoIconColor,
                            contentDescription = infoContentDescription,
                        )
                    }
                }
            }
            if (onInfoClick == null) Spacer(modifier = Modifier.height(textAreaLabelVerticalPadding))
        }
        Box(
            modifier = if (type == null && isFocused) {
                Modifier
                    .border(
                        width = textAreaFocusedExtraBorderWidth,
                        color = SirioTheme.colors.textArea.extraBorder,
                        shape = Shapes.small,
                    )
                    .padding(textAreaFocusedBorderPadding)
            } else Modifier.padding(0.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textAreaParams.textColor,
                unfocusedTextColor = textAreaParams.textColor,
                disabledTextColor = textAreaParams.textColor,
                focusedContainerColor = textAreaParams.backgroundColor,
                unfocusedContainerColor = textAreaParams.backgroundColor,
                cursorColor = textAreaParams.textColor,
                focusedBorderColor = textAreaParams.borderColor,
                unfocusedBorderColor = textAreaParams.borderColor,
                disabledBorderColor = textAreaParams.borderColor,
                errorBorderColor = textAreaParams.borderColor,
                unfocusedTrailingIconColor = textAreaParams.iconColor,
                disabledTrailingIconColor = textAreaParams.iconColor,
                errorTrailingIconColor = textAreaParams.iconColor,
                focusedTrailingIconColor = textAreaParams.iconColor,
                focusedPlaceholderColor = textAreaParams.placeholderTextColor,
                unfocusedPlaceholderColor = textAreaParams.placeholderTextColor,
                disabledPlaceholderColor = textAreaParams.placeholderTextColor,
            )

            val clickable =
                if (onTextAreaClick != null) {
                    Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = LocalIndication.current,
                            onClick = onTextAreaClick,
                        )
                } else {
                    Modifier
                }
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight,
                    )
                    .height(textAreaDefaultHeight)
                    .then(clickable),
                enabled = enabled && onTextAreaClick == null,
                readOnly = onTextAreaClick != null,
                textStyle = SirioTheme.typography.textAreaText.merge(TextStyle(color = textAreaParams.textColor)),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = false,
                visualTransformation = VisualTransformation.None,
                cursorBrush = SolidColor(textAreaParams.textColor),
                interactionSource = interactionSource,
                decorationBox = @Composable { innerTextField ->
                    OutlinedTextFieldDefaults.DecorationBox(
                        value = text,
                        innerTextField = innerTextField,
                        enabled = enabled,
                        singleLine = false,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = {
                            placeholder?.let {
                                SirioTextCommon(
                                    text = it,
                                    color = textAreaParams.placeholderTextColor,
                                    typography = SirioTheme.typography.textAreaPlaceholder,
                                )
                            }
                        },
                        trailingIcon = type?.getIcon()?.let {
                            {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(top = textAreaIconTopPadding),
                                    contentAlignment = Alignment.TopCenter,
                                ) {
                                    IconButton(
                                        onClick = onIconClick ?: {},
                                        enabled = enabled
                                    ) {
                                        SirioIcon(
                                            faIcon = it,
                                            size = textAreaIconSize,
                                            iconColor = textAreaParams.iconColor,
                                        )
                                    }
                                }
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
                                focusedBorderThickness = textAreaBorderWidth,
                                unfocusedBorderThickness = textAreaBorderWidth,
                            )
                        },
                    )
                },
            )
        }
        Spacer(modifier = Modifier.height(textAreaPaddingBottom))
        helperText?.let {
            SirioTextCommon(
                text = it,
                color = textAreaParams.helperTextColor,
                typography = SirioTheme.typography.textAreaHelperText,
            )
        }
    }
}

/**
 * @return The text field color based on its status
 */
@Composable
private fun getTextAreaParams(
    enabled: Boolean,
    type: TextAreaSemantic?,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    isValued: Boolean,
): TextAreaParams = when {
    !enabled -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.disabled,
        infoIconColor = SirioTheme.colors.textArea.label.disabled,
        labelColor = SirioTheme.colors.textArea.label.disabled,
        helperTextColor = SirioTheme.colors.textArea.helperText.disabled,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.disabled,
        textColor = SirioTheme.colors.textArea.text.disabled,
        iconColor = SirioTheme.colors.textArea.icon.disabled,
        borderColor = SirioTheme.colors.textArea.border.disabled,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.disabled,
    )

    type == TextAreaSemantic.ALERT -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.alert,
        infoIconColor = SirioTheme.colors.textArea.label.alert,
        labelColor = SirioTheme.colors.textArea.label.alert,
        helperTextColor = SirioTheme.colors.textArea.helperText.alert,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.alert,
        textColor = SirioTheme.colors.textArea.text.alert,
        iconColor = SirioTheme.colors.textArea.icon.alert,
        borderColor = SirioTheme.colors.textArea.border.alert,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.alert,
    )

    type == TextAreaSemantic.SUCCESS -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.success,
        infoIconColor = SirioTheme.colors.textArea.label.success,
        labelColor = SirioTheme.colors.textArea.label.success,
        helperTextColor = SirioTheme.colors.textArea.helperText.success,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.success,
        textColor = SirioTheme.colors.textArea.text.success,
        iconColor = SirioTheme.colors.textArea.icon.success,
        borderColor = SirioTheme.colors.textArea.border.success,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.success,
    )

    isFocused -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.focused,
        infoIconColor = SirioTheme.colors.textArea.label.focused,
        labelColor = SirioTheme.colors.textArea.label.focused,
        helperTextColor = SirioTheme.colors.textArea.helperText.focused,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.focused,
        textColor = SirioTheme.colors.textArea.text.focused,
        iconColor = SirioTheme.colors.textArea.icon.focused,
        borderColor = SirioTheme.colors.textArea.border.focused,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.focused,
    )

    isValued -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.valued,
        infoIconColor = SirioTheme.colors.textArea.label.valued,
        labelColor = SirioTheme.colors.textArea.label.valued,
        helperTextColor = SirioTheme.colors.textArea.helperText.valued,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.valued,
        textColor = SirioTheme.colors.textArea.text.valued,
        iconColor = SirioTheme.colors.textArea.icon.valued,
        borderColor = SirioTheme.colors.textArea.border.valued,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.valued,
    )

    isHovered -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.hovered,
        infoIconColor = SirioTheme.colors.textArea.label.hovered,
        labelColor = SirioTheme.colors.textArea.label.hovered,
        helperTextColor = SirioTheme.colors.textArea.helperText.hovered,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.hovered,
        textColor = SirioTheme.colors.textArea.text.hovered,
        iconColor = SirioTheme.colors.textArea.icon.hovered,
        borderColor = SirioTheme.colors.textArea.border.hovered,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.hovered,
    )

    else -> TextAreaParams(
        backgroundColor = SirioTheme.colors.textArea.background.default,
        infoIconColor = SirioTheme.colors.textArea.label.hovered,
        labelColor = SirioTheme.colors.textArea.label.default,
        helperTextColor = SirioTheme.colors.textArea.helperText.default,
        placeholderTextColor = SirioTheme.colors.textArea.placeholder.default,
        textColor = SirioTheme.colors.textArea.text.default,
        iconColor = SirioTheme.colors.textArea.icon.default,
        borderColor = SirioTheme.colors.textArea.border.default,
        dropdownBorderColor = SirioTheme.colors.textArea.dropdown.default,
    )
}

@Keep
data class TextAreaParams(
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
data class SirioTextAreaColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val label: SirioColorState,
    val icon: SirioColorState,
    val helperText: SirioColorState,
    val placeholder: SirioColorState,
    val text: SirioColorState,
    val dropdown: SirioColorState,
    val optionBackground: SirioColorState,
    val optionText: SirioColorState,
    val extraBorder: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTextAreaColors(
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

enum class TextAreaSemantic {
    ALERT {
        override fun getIcon(): FaIconType {
            return FaIcons.ExclamationTriangle
        }
    },
    SUCCESS {
        override fun getIcon(): FaIconType {
            return FaIcons.Check
        }
    };

    abstract fun getIcon(): FaIconType
}

@Preview(showSystemUi = true)
@Composable
private fun TextAreaCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextAreaCommon(
                text = "Text",
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
            )
            SirioTextAreaCommon(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = null,
                type = TextAreaSemantic.ALERT,
            )
            SirioTextAreaCommon(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "*Helper text",
                onInfoClick = {},
                type = TextAreaSemantic.SUCCESS,
            )
            SirioTextAreaCommon(
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

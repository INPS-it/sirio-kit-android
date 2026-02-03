//
// SirioTextAreaCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.textarea

import androidx.annotation.Keep
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.textAreaBorderWidth
import it.inps.sirio.theme.textAreaDefaultHeight
import it.inps.sirio.theme.textAreaIconSize
import it.inps.sirio.theme.textAreaIconTopPadding
import it.inps.sirio.theme.textAreaInfoIconSize
import it.inps.sirio.theme.textAreaLabelVerticalPadding
import it.inps.sirio.theme.textAreaPaddingBottom
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

/**
 * A common composable function for creating a Sirio-styled text area.
 *
 * @param text The text to display in the text area.
 * @param onValueChange The callback that is triggered when the text changes.
 * @param placeholder The placeholder text to display when the text area is empty.
 * @param label The label text to display above the text area.
 * @param onInfoClick An optional callback that is triggered when the info icon is clicked.
 * @param infoContentDescription The content description for the info icon.
 * @param helperText The helper text to display below the text area.
 * @param state The state of the text area, such as Alert or Success.
 * @param enabled Whether the text area is enabled.
 * @param keyboardOptions Options for the software keyboard.
 * @param keyboardActions Actions for the software keyboard.
 * @param onIconClick An optional callback that is triggered when the trailing icon is clicked.
 * @param onTextAreaClick An optional callback that is triggered when the text area is clicked.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioTextAreaCommon(
    onValueChange: (String) -> Unit,
    label: String? = null,
    text: String = "",
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val stateColor: Color? = if (enabled) state?.toColor() else null
    val stateIcon: FaIconType? = state?.toIcon()
    val labelColor = stateColor ?: SirioTheme.colors.textArea.label.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val placeholderTextColor = stateColor ?: SirioTheme.colors.textArea.placeholder.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val textColor = stateColor ?: SirioTheme.colors.textArea.text.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val containerColor = SirioTheme.colors.textArea.background.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val borderColor = stateColor ?: SirioTheme.colors.textArea.border.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val infoIconColor = SirioTheme.colors.textArea.infoIcon.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    val helperTextColor = stateColor ?: SirioTheme.colors.textArea.helperText.get(
        pressed = isPressed,
        disabled = !enabled,
        valued = text.isNotEmpty(),
    )
    Column {
        label?.let {
            Row(Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
                SirioTextCommon(
                    text = it,
                    color = labelColor,
                    typography = SirioTheme.foundationTypography.labelMdMiddle,
                )
                onInfoClick?.let { infoClickAction ->
                    IconButton(onClick = infoClickAction, modifier =if(label.isNotBlank()) Modifier.testTag("buttonInfoTextArea${label.takeTwoWords()}") else Modifier) {
                        SirioIcon(
                            icon = SirioIconSource.FaIcon(FaIcons.InfoCircle),
                            size = textAreaInfoIconSize.dp,
                            iconColor = infoIconColor,
                            contentDescription = infoContentDescription,
                        )
                    }
                }
            }
            if (onInfoClick == null) Spacer(modifier = Modifier.height(textAreaLabelVerticalPadding.dp))
        }
        val colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = SirioTheme.colors.textArea.text.disabled,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = SirioTheme.colors.textArea.background.disabled,
            cursorColor = SirioTheme.colors.textArea.text.default,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            disabledBorderColor = SirioTheme.colors.textArea.border.disabled,
            errorBorderColor = SirioTheme.colors.textArea.border.alert,
            unfocusedTrailingIconColor = stateColor ?: Color.Transparent,
            disabledTrailingIconColor = stateColor ?: Color.Transparent,
            errorTrailingIconColor = stateColor ?: Color.Transparent,
            focusedTrailingIconColor = stateColor ?: Color.Transparent,
            focusedPlaceholderColor = placeholderTextColor,
            unfocusedPlaceholderColor = placeholderTextColor,
            disabledPlaceholderColor = SirioTheme.colors.textArea.placeholder.disabled,
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
                .height(textAreaDefaultHeight.dp)
                .then(clickable).testTag("textArea${label.takeTwoWords()}"),
            enabled = enabled && onTextAreaClick == null,
            readOnly = onTextAreaClick != null,
            textStyle = SirioTheme.foundationTypography.bodyMdRegular.merge(TextStyle(color = textColor)),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = false,
            visualTransformation = VisualTransformation.None,
//            cursorBrush = SolidColor(textAreaParams.textColor),
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
                                color = placeholderTextColor,
                                typography = SirioTheme.foundationTypography.bodyMdRegular,
                            )
                        }
                    },
                    trailingIcon = stateIcon?.let {
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(top = textAreaIconTopPadding.dp),
                                contentAlignment = Alignment.TopCenter,
                            ) {
                                IconButton(
                                    onClick = onIconClick ?: {},
                                    enabled = enabled
                                ) {
                                    SirioIcon(
                                        icon = SirioIconSource.FaIcon(it),
                                        size = textAreaIconSize.dp,
                                        iconColor = stateColor ?: Color.Transparent,
                                    )
                                }
                            }
                        }
                    },
                    colors = colors,
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(),
                    container = {
                        OutlinedTextFieldDefaults.Container(
                            enabled = enabled,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = colors,
                            shape = Shapes.small,
                            focusedBorderThickness = textAreaBorderWidth.dp,
                            unfocusedBorderThickness = textAreaBorderWidth.dp,
                        )
                    },
                )
            },
        )
        Spacer(modifier = Modifier.height(textAreaPaddingBottom.dp))
        helperText?.let {
            SirioTextCommon(
                text = it,
                color = helperTextColor,
                typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
            )
        }
    }
}

@Keep
data class SirioTextAreaColors(
    var background: SirioColorState,
    var border: SirioColorState,
    var label: SirioColorState,
    var infoIcon: SirioColorState,
    var helperText: SirioColorState,
    var placeholder: SirioColorState,
    var text: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioTextAreaColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            label = SirioColorState.Unspecified,
            infoIcon = SirioColorState.Unspecified,
            helperText = SirioColorState.Unspecified,
            placeholder = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
        )
    }
}

internal val textAreaLightColors = SirioTextAreaColors(
    background = SirioColorState.all(
        color = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    border = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        alert = FoundationColor.colorSpecificDataEntryBorderColorAlert,
        success = FoundationColor.colorSpecificDataEntryBorderColorSuccess,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryBorderColorValued,
    ),
    label = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryLabelColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryLabelColorValued,
        alert = FoundationColor.colorSpecificDataEntryLabelColorDefault,
        success = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    ),
    infoIcon = SirioColorState.all(color = FoundationColor.colorGlobalSemanticInfo100),
    helperText = SirioColorState(
        default = FoundationColor.colorAliasInteractiveSecondaryDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorAliasInteractiveSecondaryDefault,
        alert = FoundationColor.colorSpecificDataEntryLabelColorAlert,
        success = FoundationColor.colorSpecificDataEntryLabelColorSuccess,
    ),
    placeholder = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryPlaceholderColorValued,
        alert = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
        success = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
    ),
    text = SirioColorState(
        default = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
        valued = FoundationColor.colorSpecificDataEntryPlaceholderColorValued,
        alert = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
        success = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
    ),
)

internal val textAreaDarkColors = textAreaLightColors

@Composable
internal fun TextAreaState.toColor(): Color = when (this) {
    TextAreaState.Alert -> SirioTheme.colors.textArea.border.alert
    TextAreaState.Success -> SirioTheme.colors.textArea.border.success
}

internal fun TextAreaState.toIcon(): FaIconType = when (this) {
    TextAreaState.Alert -> FaIcons.ExclamationTriangle
    TextAreaState.Success -> FaIcons.Check
}

enum class TextAreaState {
    Alert,
    Success,
}

@Preview(showSystemUi = true)
@Composable
private fun TextAreaCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTextAreaCommon(
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
            )
            SirioTextAreaCommon(
                text = "Text",
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
            )
            SirioTextAreaCommon(
                text = "Text",
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                enabled = false,
            )
            SirioTextAreaCommon(
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                onIconClick = {},
            )
            SirioTextAreaCommon(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                onInfoClick = null,
                state = TextAreaState.Alert
            )
            SirioTextAreaCommon(
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                state = TextAreaState.Success
            )
        }
    }
}

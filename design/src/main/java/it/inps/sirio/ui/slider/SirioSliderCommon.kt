//
// SirioSliderCommon.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.*
import it.inps.sirio.ui.text.SirioTextCommon
import java.lang.Integer.min
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Sirio slider implementation with title and text
 *
 * @param title The optional slider title
 * @param text The optional slider text
 * @param value The slider current value
 * @param minValue The slider minimum allowed value
 * @param maxValue The slider maximum allowed value
 * @param enabled Whether the slider value can be modified by user
 * @param onValueChange The callback when value is changed by user
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioSliderCommon(
    title: String? = null,
    text: String? = null,
    value: Int,
    minValue: Int,
    maxValue: Int,
    enabled: Boolean = true,
    onValueChange: (Int) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val thumbColor = when {
        !enabled -> SirioTheme.colors.sliderDisabled
        isPressed -> SirioTheme.colors.sliderPressedThumb
        isFocused -> SirioTheme.colors.sliderThumb
        isHovered -> SirioTheme.colors.sliderThumb
        else -> SirioTheme.colors.sliderThumb
    }
    val sliderActiveColor = when {
        !enabled -> SirioTheme.colors.sliderActive
        isPressed -> SirioTheme.colors.sliderPressedActive
        isFocused -> SirioTheme.colors.sliderFocusActive
        isHovered -> SirioTheme.colors.sliderActive
        else -> SirioTheme.colors.sliderActive
    }
    val textFieldBorderColor = when {
        !enabled -> SirioTheme.colors.sliderDisabledTextFieldBorder
        isPressed -> SirioTheme.colors.sliderPressedTextFieldBorder
        isFocused -> SirioTheme.colors.sliderFocusTextFieldBorder
        isHovered -> SirioTheme.colors.sliderHoverTextFieldBorder
        else -> SirioTheme.colors.sliderDefaultTextFieldBorder
    }
    val textFieldTextColor = when {
        !enabled -> SirioTheme.colors.sliderDisabledTextFieldText
        isPressed -> SirioTheme.colors.sliderPressedTextFieldText
        isFocused -> SirioTheme.colors.sliderFocusTextFieldText
        isHovered -> SirioTheme.colors.sliderHoverTextFieldText
        else -> SirioTheme.colors.sliderDefaultTextFieldText
    }

    Column {
        title?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.sliderTitle,
                typography = SirioTheme.typography.sliderTitle,
            )
            Spacer(modifier = Modifier.height(sliderTitlePaddingBottom))
        }
        text?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.sliderText,
                typography = SirioTheme.typography.sliderText,
            )
            Spacer(modifier = Modifier.height(sliderTextPaddingBottom))
        }
        Row {
            var currentValue by remember(value) { mutableStateOf(value) }
            val focusManager = LocalFocusManager.current
            Column(Modifier.weight(1f)) {
                Slider(
                    value = value.toFloat(),
                    onValueChange = {
                        val newIntValue = it.roundToInt()
                        currentValue = newIntValue
                        onValueChange(newIntValue)
                    },
                    modifier = Modifier.height(20.dp),
                    enabled = enabled,
                    valueRange = minValue.toFloat()..maxValue.toFloat(),
                    interactionSource = interactionSource,
                    colors = SliderDefaults.colors(
                        thumbColor = thumbColor,
                        disabledThumbColor = SirioTheme.colors.sliderDisabled,
                        activeTrackColor = sliderActiveColor,
                        inactiveTrackColor = SirioTheme.colors.sliderInactive,
                        disabledActiveTrackColor = sliderActiveColor,
                        disabledInactiveTrackColor = SirioTheme.colors.sliderInactive,
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SirioTextCommon(
                        text = "$minValue",
                        modifier = Modifier.padding(start = 8.dp),
                        color = SirioTheme.colors.sliderNumbers,
                        typography = SirioTheme.typography.sliderNumber,
                    )
                    SirioTextCommon(
                        text = "$maxValue",
                        modifier = Modifier.padding(end = 8.dp),
                        color = SirioTheme.colors.sliderNumbers,
                        typography = SirioTheme.typography.sliderNumber,
                    )
                }
            }
            Spacer(modifier = Modifier.width(sliderTextFieldPaddingStart))
            Box(
                modifier = if (isFocused) {
                    Modifier
                        .width(sliderTextFieldWidth)
                        .height(sliderTextFieldHeight)
                        .border(
                            width = sliderTextFieldFocusExtraBorderWidth,
                            color = SirioTheme.colors.sliderFocusTextFieldExtraBorder,
                            shape = RoundedCornerShape(sliderTextFieldCornerRadius),
                        )
                        .padding(sliderTextFieldFocusExtraBorderPadding)
                } else Modifier
                    .padding(0.dp)
                    .width(sliderTextFieldWidth)
                    .height(sliderTextFieldHeight),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = "$currentValue",
                    onValueChange = {
                        it.toIntOrNull()?.let { int ->
                            currentValue = int
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .border(
                            width = sliderTextFieldBorderWidth,
                            color = textFieldBorderColor,
                            shape = RoundedCornerShape(sliderTextFieldCornerRadius),
                        )
                        .background(
                            color = if (enabled) SirioTheme.colors.sliderDefaultTextFieldBackground else SirioTheme.colors.sliderDisabledTextFieldBackground,
                            shape = RoundedCornerShape(sliderTextFieldCornerRadius),
                        )
                        .onFocusChanged {
                            if (!it.hasFocus) {
                                //Return minValue if int < minValue, maxValue if int > maxValue, int otherwise
                                onValueChange(max(min(currentValue, maxValue), minValue))
                            }
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    readOnly = !enabled,
                    textStyle = SirioTheme.typography.sliderNumber.copy(
                        color = textFieldTextColor,
                        textAlign = TextAlign.Center,
                    ),
                    interactionSource = interactionSource,
                )
                { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = "$currentValue",
                        visualTransformation = VisualTransformation.None,
                        innerTextField = innerTextField,
                        singleLine = true,
                        enabled = enabled,
                        interactionSource = interactionSource,
                        contentPadding = PaddingValues(12.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SliderCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            SirioSliderCommon(
                title = "Slider Label",
                text = "*Info upload file",
                value = 20,
                minValue = 50,
                maxValue = 100,
                enabled = true,
                onValueChange = {})
        }
    }
}
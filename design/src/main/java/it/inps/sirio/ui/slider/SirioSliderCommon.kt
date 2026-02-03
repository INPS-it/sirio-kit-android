//
// SirioSliderCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.slider

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.Container
import androidx.compose.material3.TextFieldDefaults.FocusedIndicatorThickness
import androidx.compose.material3.TextFieldDefaults.UnfocusedIndicatorThickness
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.sliderHeight
import it.inps.sirio.theme.sliderNumberPaddingTop
import it.inps.sirio.theme.sliderPaddingEnd
import it.inps.sirio.theme.sliderTextFieldBorderWidth
import it.inps.sirio.theme.sliderTextFieldCornerRadius
import it.inps.sirio.theme.sliderTextFieldHeight
import it.inps.sirio.theme.sliderTextFieldPaddingHorizontal
import it.inps.sirio.theme.sliderTextFieldPaddingVertical
import it.inps.sirio.theme.sliderTextFieldWidth
import it.inps.sirio.theme.sliderTextPaddingBottom
import it.inps.sirio.theme.sliderThumbBorderSize
import it.inps.sirio.theme.sliderTitlePaddingBottom
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.takeTwoWords
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
    val textFieldBorderColor = SirioTheme.colors.slider.textFieldBorder.get(disabled = !enabled)
    val textFieldContentColor = SirioTheme.colors.slider.textFieldContent.get(disabled = !enabled)

    Column {
        title?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.slider.title,
                typography = SirioTheme.foundationTypography.labelMdMiddle,
            )
            Spacer(modifier = Modifier.height(sliderTitlePaddingBottom.dp))
        }
        text?.let {
            SirioTextCommon(
                text = it,
                color = SirioTheme.colors.slider.text,
                typography = SirioTheme.foundationTypography.tabbarLabelXsRegular,
            )
            Spacer(modifier = Modifier.height(sliderTextPaddingBottom.dp))
        }
        Row {
            var currentValue by remember(value) { mutableIntStateOf(value) }
            val focusManager = LocalFocusManager.current
            Column(Modifier.weight(1f)) {
                Slider(
                    value = currentValue.toFloat(),
                    onValueChange = {
                        val newIntValue = it.roundToInt()
                        currentValue = newIntValue
                        onValueChange(newIntValue)
                    },
                    modifier = Modifier.height(sliderHeight.dp),
                    enabled = enabled,
                    valueRange = minValue.toFloat()..maxValue.toFloat(),
                    colors = SliderDefaults.colors(),
                    thumb = {
                        SirioSliderThumb(enabled = enabled)
                    },
                    track = { sliderState ->
                        SirioSliderTrack(
                            value = sliderState.value,
                            min = sliderState.valueRange.start,
                            max = sliderState.valueRange.endInclusive,
                            enabled = enabled,
                        )
                    }
                )
                Spacer(modifier = Modifier.height(sliderNumberPaddingTop.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SirioTextCommon(
                        text = "$minValue",
                        modifier = Modifier.padding(start = (sliderThumbBorderSize / 2).dp),
                        color = SirioTheme.colors.slider.numbers,
                        typography = SirioTheme.foundationTypography.labelNumberMdRegular,
                    )
                    SirioTextCommon(
                        text = "$maxValue",
                        modifier = Modifier.padding(end = (sliderThumbBorderSize / 2).dp),
                        color = SirioTheme.colors.slider.numbers,
                        typography = SirioTheme.foundationTypography.labelNumberMdRegular,
                    )
                }
            }
            Spacer(modifier = Modifier.width(sliderPaddingEnd.dp))
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .width(sliderTextFieldWidth.dp)
                    .height(sliderTextFieldHeight.dp),
                contentAlignment = Alignment.Center,
            ) {
                var tempValue: String by remember(currentValue) { mutableStateOf(currentValue.toString()) }
                BasicTextField(
                    value = tempValue,
                    onValueChange = { newValueString ->
                        val newValueFiltered = newValueString.filter { it.isDigit() }
                        tempValue = newValueFiltered
                        newValueFiltered.toIntOrNull()?.let { newValue ->
                            currentValue = newValue
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("sliderTextField${title.takeTwoWords()}")
                        .align(Alignment.Center)
                        .border(
                            width = sliderTextFieldBorderWidth.dp,
                            color = textFieldBorderColor,
                            shape = RoundedCornerShape(sliderTextFieldCornerRadius.dp),
                        )
                        .onFocusChanged {
                            if (!it.hasFocus) {
                                onValueChange(currentValue.coerceIn(minValue, maxValue))
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
                    textStyle = SirioTheme.foundationTypography.labelNumberMdRegular.copy(
                        color = textFieldContentColor,
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
                        contentPadding = PaddingValues(
                            horizontal = sliderTextFieldPaddingHorizontal.dp,
                            vertical = sliderTextFieldPaddingVertical.dp,
                        ),
                        container = {
                            Container(
                                enabled = enabled,
                                isError = false,
                                interactionSource = interactionSource,
                                modifier = Modifier,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = SirioTheme.colors.slider.textFieldContainer.default,
                                    focusedContainerColor = SirioTheme.colors.slider.textFieldContainer.focused,
                                    disabledContainerColor = SirioTheme.colors.slider.textFieldContainer.disabled,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent,
                                ),
                                shape = RoundedCornerShape(sliderTextFieldCornerRadius.dp),
                                focusedIndicatorLineThickness = FocusedIndicatorThickness,
                                unfocusedIndicatorLineThickness = UnfocusedIndicatorThickness,
                            )
                        }
                    )
                }
            }
        }
    }
}

@Keep
data class SirioSliderColors(
    val title: Color,
    val text: Color,
    val thumb: SirioColorState,
    val trackBackground: SirioColorState,
    val trackProgress: SirioColorState,
    val numbers: Color,
    val textFieldContainer: SirioColorState,
    val textFieldBorder: SirioColorState,
    val textFieldContent: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioSliderColors(
            title = Color.Unspecified,
            text = Color.Unspecified,
            thumb = SirioColorState.Unspecified,
            trackBackground = SirioColorState.Unspecified,
            trackProgress = SirioColorState.Unspecified,
            numbers = Color.Unspecified,
            textFieldContainer = SirioColorState.Unspecified,
            textFieldBorder = SirioColorState.Unspecified,
            textFieldContent = SirioColorState.Unspecified,
        )
    }
}

internal val sliderLightColors = SirioSliderColors(
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    text = FoundationColor.colorAliasInteractiveSecondaryDefault,
    thumb = SirioColorState.all(
        color = FoundationColor.colorAliasInteractivePrimaryDefault,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled
    ),
    trackBackground = SirioColorState.all(FoundationColor.colorAliasBackgroundColorDisabled),
    trackProgress = SirioColorState.all(FoundationColor.colorAliasInteractiveSecondaryDefault),
    numbers = FoundationColor.colorAliasInteractiveSecondaryDefault,
    textFieldContainer = SirioColorState.all(
        color = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    textFieldBorder = SirioColorState.all(
        color = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    textFieldContent = SirioColorState.all(
        color = FoundationColor.colorAliasInteractiveSecondaryDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
)

internal val sliderDarkColors = sliderLightColors

@Preview
@Composable
private fun SliderCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            var value by remember { mutableIntStateOf(2) }
            SirioSliderCommon(
                title = "Slider Label",
                text = "Info slider",
                value = value,
                minValue = 0,
                maxValue = 10,
                enabled = true,
                onValueChange = { value = it },
            )
            SirioSliderCommon(
                title = "Slider Label",
                text = "Info slider",
                value = value,
                minValue = 0,
                maxValue = 10,
                enabled = false,
                onValueChange = { value = it },
            )
        }
    }
}

//
// SirioSlider.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import java.lang.Integer.min

/**
 * Sirio slider component with title and text
 *
 * @param title The optional slider title
 * @param text The optional slider text
 * @param value The slider current value
 * @param minValue The slider minimum allowed value
 * @param maxValue The slider maximum allowed value
 * @param enabled Whether the slider value can be modified by user
 * @param onValueChange The callback when value is changed by user
 */
@Composable
fun SirioSlider(
    title: String? = null,
    text: String? = null,
    value: Int,
    minValue: Int,
    maxValue: Int,
    enabled: Boolean = true,
    onValueChange: (Int) -> Unit,
) {
    SirioSliderCommon(
        title = title,
        text = text,
        value = value,
        minValue = minValue,
        maxValue = min(maxValue, Int.MAX_VALUE),
        enabled = enabled,
        onValueChange = onValueChange
    )
}

@Preview
@Composable
private fun SliderPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            SirioSlider(
                title = "Slider label",
                text = "*Info upload file",
                value = 20,
                minValue = 0,
                maxValue = 100,
                onValueChange = {},
            )
            SirioSlider(
                title = "Slider label",
                text = "*Info upload file",
                value = 20,
                minValue = 0,
                maxValue = 100,
                enabled = false,
                onValueChange = {},
            )
        }
    }
}

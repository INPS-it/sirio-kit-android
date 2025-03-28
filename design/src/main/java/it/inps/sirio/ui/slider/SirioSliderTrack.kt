//
// SirioSliderTrack.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.sliderTrackHeight

/**
 * Represents the track of a slider component.
 *
 * @param value The current value of the slider.
 * @param min The minimum value of the slider.
 * @param max The maximum value of the slider.
 * @param enabled Whether the slider is enabled or disabled.
 */
@Composable
internal fun SirioSliderTrack(
    value: Float,
    min: Float,
    max: Float,
    enabled: Boolean,
) {
    val backgroundColor = SirioTheme.colors.slider.trackBackground.get(disabled = !enabled)
    val progressColor = SirioTheme.colors.slider.trackProgress.get(disabled = !enabled)
    Box {
        Box(
            Modifier
                .fillMaxWidth()
                .height(sliderTrackHeight.dp)
                .background(backgroundColor, CircleShape)
        )
        val fraction = (value - min) / (max - min)
        Box(
            Modifier
                .fillMaxWidth(fraction)
                .height(sliderTrackHeight.dp)
                .background(progressColor, CircleShape)
        )
    }
}

@Preview
@Composable
private fun SirioSliderTrackPreview() {
    SirioTheme {
        SirioSliderTrack(2f, 0f, 10f, true)
    }
}
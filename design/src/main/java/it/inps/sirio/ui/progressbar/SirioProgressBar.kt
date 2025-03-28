//
// SirioProgressBar.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * Progress bar with float params
 *
 * @param label The label above the progress
 * @param progress The progress between 0 and [maxValue]
 * @param maxValue The max value allowed
 * @param showPercentage A boolean value indicating if the percentage of progress should be displayed. Defaults to true
 */
@Composable
fun SirioProgressBar(
    label: String,
    progress: Float,
    maxValue: Float = 100f,
    showPercentage: Boolean = true,
) {
    SirioProgressBarCommon(
        label = label,
        progress = progress,
        maxValue = maxValue,
        showPercentage = showPercentage,
    )
}

/**
 * Progress bar with int params
 *
 * @param label The label above the progress
 * @param progress The progress between 0 and [maxValue]
 * @param maxValue The max value allowed
 */
@Composable
fun SirioProgressBar(
    label: String,
    progress: Int,
    maxValue: Int = 100,
    showPercentage: Boolean = true,
) {
    SirioProgressBarCommon(
        label = label,
        progress = progress.toFloat(),
        maxValue = maxValue.toFloat(),
        showPercentage = showPercentage,
    )
}

@Preview
@Composable
private fun ProgressBarPreview() {
    SirioTheme {
        val label = "Label"
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            SirioProgressBar(
                label = label,
                progress = 0,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 50,
            )
            SirioProgressBar(
                label = label,
                progress = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 0,
                maxValue = 10,
            )
            SirioProgressBar(
                label = label,
                progress = 5,
                maxValue = 10,
            )
            SirioProgressBar(
                label = label,
                progress = 10,
                maxValue = 10,
            )
            SirioProgressBar(
                label = label,
                progress = 20,
                maxValue = 10,
            )
            SirioProgressBar(
                label = label,
                progress = -2,
                maxValue = 10,
            )
        }
    }
}
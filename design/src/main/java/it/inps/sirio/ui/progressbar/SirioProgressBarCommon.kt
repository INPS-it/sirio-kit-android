//
// SirioProgressBarCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.progressbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import java.lang.Float.min
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Progress bar implementation
 *
 * @param label The label above the progress
 * @param progress The progress between 0 and [maxValue]
 * @param maxValue The max value allowed
 */
@Composable
internal fun SirioProgressBarCommon(label: String, progress: Float, maxValue: Float) {
    val checkedProgress = max(min(progress, maxValue), 0f)
    val animatedProgress by animateFloatAsState(
        targetValue = checkedProgress / 100f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    val fraction = animatedProgress * 100 / maxValue
    Column {
        Text(
            text = label,
            modifier = Modifier.padding(start = 4.dp),
            color = SirioTheme.colors.progressBarLabel,
            style = SirioTheme.typography.progressBarLabel,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .height(9.dp)
                .fillMaxWidth()
                .padding(end = 4.dp)
                .clip(CircleShape)
                .background(color = SirioTheme.colors.progressBarBackground),
            contentAlignment = Alignment.CenterStart,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (fraction == 0f) 0.01f else fraction)
                    .height(6.dp)
                    .padding(horizontal = 1.5.dp)
                    .clip(CircleShape)
                    .background(Brush.horizontalGradient(SirioTheme.colors.progressBarGradient))
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "${(fraction * 100f).roundToInt()}%",
            modifier = Modifier.align(Alignment.End),
            color = SirioTheme.colors.progressBarNumber,
            style = SirioTheme.typography.progressBarNumber,
        )
    }
}

@Preview
@Composable
private fun ProgressBarCommonPreview() {
    SirioTheme {
        Column {
            SirioProgressBarCommon("Label", 0f, 100f)
            SirioProgressBarCommon("Label", 50f, 100f)
            SirioProgressBarCommon("Label", 100f, 100f)
            SirioProgressBarCommon("Label", 5f, 10f)
        }
    }
}
//
// SirioProgressBarCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.progressbar

import androidx.annotation.Keep
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.progressBarHeight
import it.inps.sirio.theme.progressBarLabelPaddingBottom
import it.inps.sirio.theme.progressBarLabelPaddingStart
import it.inps.sirio.theme.progressBarNumberPaddingTop
import it.inps.sirio.theme.progressBarPaddingEnd
import it.inps.sirio.theme.progressBarProgressHeight
import it.inps.sirio.theme.progressBarProgressPaddingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon
import java.lang.Float.min
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Progress bar implementation
 *
 * @param label The label above the progress
 * @param progress The progress between 0 and [maxValue]
 * @param maxValue The max value allowed
 * @param showPercentage A boolean value indicating if the percentage of progress should be displayed. Defaults to true
 */
@Composable
internal fun SirioProgressBarCommon(
    label: String,
    progress: Float,
    maxValue: Float,
    showPercentage: Boolean = true,
) {
    val checkedProgress = max(min(progress, maxValue), 0f)
    val animatedProgress by animateFloatAsState(
        targetValue = checkedProgress / 100f,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "progress",
    )
    val fraction = animatedProgress * 100 / maxValue
    Column {
        SirioTextCommon(
            text = label,
            modifier = Modifier.padding(start = progressBarLabelPaddingStart.dp),
            color = SirioTheme.colors.progressBar.label,
            typography = SirioTheme.foundationTypography.labelMdMiddle,
        )
        Spacer(modifier = Modifier.height(progressBarLabelPaddingBottom.dp))
        Box(
            modifier = Modifier
                .height(progressBarHeight.dp)
                .fillMaxWidth()
                .padding(end = progressBarPaddingEnd.dp)
                .clip(CircleShape)
                .background(color = SirioTheme.colors.progressBar.background),
            contentAlignment = Alignment.CenterStart,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (fraction == 0f) 0.01f else fraction)
                    .height(progressBarProgressHeight.dp)
                    .padding(horizontal = progressBarProgressPaddingHorizontal.dp)
                    .clip(CircleShape)
                    .background(Brush.horizontalGradient(SirioTheme.colors.progressBar.gradient))
            )
        }
        if (showPercentage) {
            Spacer(modifier = Modifier.height(progressBarNumberPaddingTop.dp))
            SirioTextCommon(
                text = "${(fraction * 100f).roundToInt()}%",
                modifier = Modifier.align(Alignment.End),
                color = SirioTheme.colors.progressBar.number,
                typography = SirioTheme.foundationTypography.labelNumberSmRegular
                    .copy(fontSize = 10.sp, lineHeight = 12.sp),
            )
        }
    }
}

@Keep
data class SirioProgressBarColors(
    val label: Color,
    val background: Color,
    val gradient: List<Color>,
    val number: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioProgressBarColors(
            label = Color.Unspecified,
            background = Color.Unspecified,
            gradient = emptyList(),
            number = Color.Unspecified,
        )
    }
}

internal val progressBarLightColors = SirioProgressBarColors(
    label = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    background = Color(0xFFEDF4F7),
    gradient = FoundationColor.colorSpecificProgressbarBackgroundColor,
    number = FoundationColor.colorAliasInteractiveSecondaryDefault,
)

internal val progressBarDarkColors = progressBarLightColors

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
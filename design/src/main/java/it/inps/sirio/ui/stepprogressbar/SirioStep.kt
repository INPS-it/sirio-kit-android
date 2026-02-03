//
// SirioStepMobile.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepPathWidth
import it.inps.sirio.theme.stepPointPadding
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.takeTwoWords

@Composable
fun SirioStep(
    type: SirioStepPointType,
    pointText: String,
    stepText: String,
    interactive: Boolean,
    position: SirioStepPosition,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("progressBar${stepText.takeTwoWords()}")
            .wrapContentHeight()
            .background(SirioTheme.colors.stepProgressBar.step.background)
            .clickable(enabled = interactive, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.height(IntrinsicSize.Max)) {
            SirioStepPath(position)
            SirioStepPoint(
                type = type,
                modifier = Modifier.padding(stepPointPadding.dp),
                text = pointText,
            )
        }
        SirioStepLabel(interactive, stepText)
    }
}

data class SirioStepData(
    val type: SirioStepPointType,
    val stepText: String,
)

@Composable
private fun BoxScope.SirioStepPath(position: SirioStepPosition) {
    val pathFraction = when (position) {
        SirioStepPosition.START -> 0.5f
        SirioStepPosition.MIDDLE -> 1f
        SirioStepPosition.END -> 0.5f
    }
    val pathAlign: Alignment = when (position) {
        SirioStepPosition.START -> Alignment.BottomCenter
        SirioStepPosition.MIDDLE -> Alignment.Center
        SirioStepPosition.END -> Alignment.TopCenter
    }
    Box(
        Modifier
            .width(stepPathWidth.dp)
            .fillMaxHeight(pathFraction)
            .background(SirioTheme.colors.stepProgressBar.step.path)
            .align(pathAlign)
    )
}

@Composable
private fun SirioStepLabel(interactive: Boolean, stepText: String) {
    val typography =
        if (interactive) SirioTheme.foundationTypography.linkMdHeavy
        else SirioTheme.foundationTypography.labelMdRegular
    val color =
        if (interactive) SirioTheme.colors.stepProgressBar.step.labelInteractive
        else SirioTheme.colors.stepProgressBar.step.labelDefault
    SirioText(
        text = stepText,
        color = color,
        typography = typography,
    )
}

enum class SirioStepPosition {
    START,
    MIDDLE,
    END
}

@Keep
data class SirioStepColors(
    val background: Color,
    val labelDefault: Color,
    val labelInteractive: Color,
    val path: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepColors(
            background = Color.Unspecified,
            labelDefault = Color.Unspecified,
            labelInteractive = Color.Unspecified,
            path = Color.Unspecified,
        )
    }
}

internal val stepLightColors = SirioStepColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    labelDefault = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    labelInteractive = FoundationColor.colorAliasInteractiveSecondaryDefault,
    path = FoundationColor.colorAliasBackgroundColorDisabled,
)

internal val stepDarkColors = stepLightColors

@Preview
@Composable
private fun SirioStepPreview() {
    SirioTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Column(Modifier.weight(0.5f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SirioStep(
                    type = SirioStepPointType.TODO,
                    pointText = "00",
                    stepText = "Label",
                    interactive = false,
                    position = SirioStepPosition.START,
                    onClick = {},
                )
                SirioStep(
                    type = SirioStepPointType.TODO,
                    pointText = "00",
                    stepText = "Label",
                    interactive = false,
                    position = SirioStepPosition.MIDDLE,
                    onClick = {},
                )
                SirioStep(
                    type = SirioStepPointType.TODO,
                    pointText = "00",
                    stepText = "Label",
                    interactive = false,
                    position = SirioStepPosition.END,
                    onClick = {},
                )
            }
            Column(Modifier.weight(0.5f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SirioStep(
                    type = SirioStepPointType.TODO,
                    pointText = "00",
                    stepText = "Label",
                    interactive = true,
                    position = SirioStepPosition.START,
                    onClick = {},
                )
                SirioStep(
                    type = SirioStepPointType.TODO,
                    pointText = "00",
                    stepText = "Label",
                    interactive = true,
                    position = SirioStepPosition.MIDDLE,
                    onClick = {},
                )
                SirioStep(
                    type = SirioStepPointType.TODO,
                    pointText = "00",
                    stepText = "Label",
                    interactive = true,
                    position = SirioStepPosition.END,
                    onClick = {},
                )
            }
        }
    }
}

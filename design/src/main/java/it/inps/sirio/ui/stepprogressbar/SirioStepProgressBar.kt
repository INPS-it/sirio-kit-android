//
// SirioStepProgressBar.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepSelectionExpandedShadowHeight
import it.inps.sirio.ui.textfield.SirioTextField
import java.util.Locale

@Composable
fun SirioStepProgressBar(
    steps: List<SirioStepData>,
    currentStepIndex: Int,
    back: SirioStepControlData,
    next: SirioStepControlData,
    modifier: Modifier = Modifier,
    other: List<SirioStepControlData> = emptyList(),
    onStepSelected: (index: Int) -> Boolean,
    content: @Composable () -> Unit,
) {
    var selectionExpanded by remember(currentStepIndex) { mutableStateOf(false) }
    Column(modifier = modifier) {
        SirioStepSelection(
            title = steps[currentStepIndex].stepText,
            currentStep = currentStepIndex,
            totalSteps = steps.size,
            expanded = selectionExpanded,
            onClick = { selectionExpanded = !selectionExpanded },
        )
        Box(
            Modifier
                .weight(1f)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    enabled = selectionExpanded,
                    onClick = { selectionExpanded = false })
        ) {
            content()
            StepProgressBarSelectionExpanded(
                selectionExpanded = selectionExpanded,
                steps = steps,
                currentStepIndex = currentStepIndex,
                onStepSelected = { index ->
                    if (onStepSelected(index)) {
                        selectionExpanded = false
                    }
                }
            )
        }
        SirioStepControls(
            back = back,
            next = next,
            other = other,
        )
    }
}

@Composable
private fun StepProgressBarSelectionExpanded(
    selectionExpanded: Boolean,
    steps: List<SirioStepData>,
    currentStepIndex: Int,
    onStepSelected: (index: Int) -> Unit,
) {
    AnimatedVisibility(
        visible = selectionExpanded,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = stepSelectionExpandedShadowHeight.dp)
                .background(SirioTheme.colors.stepProgressBar.selection.shadow)
        ) {
            LazyColumn {
                itemsIndexed(steps) { index, step ->
                    val position = when (index) {
                        0 -> SirioStepPosition.START
                        steps.lastIndex -> SirioStepPosition.END
                        else -> SirioStepPosition.MIDDLE
                    }
                    val pointText = String.format(locale = Locale.getDefault(), "%02d", index + 1)
                    val interactive = index < currentStepIndex
                    val type = when {
                        index == currentStepIndex -> SirioStepPointType.TODO
                        index < currentStepIndex -> step.type
                        else -> SirioStepPointType.INACTIVE
                    }
                    SirioStep(
                        type = type,
                        pointText = pointText,
                        stepText = step.stepText,
                        interactive = interactive,
                        position = position,
                        onClick = { onStepSelected(index) },
                    )
                }
            }
        }
    }
}

@Keep
data class SirioStepProgressBarColors(
    val step: SirioStepColors,
    val point: SirioStepPointStateColors,
    val selection: SirioStepSelectionColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepProgressBarColors(
            step = SirioStepColors.Unspecified,
            point = SirioStepPointStateColors.Unspecified,
            selection = SirioStepSelectionColors.Unspecified,
        )
    }
}

internal val stepProgressBarLightColors = SirioStepProgressBarColors(
    step = stepLightColors,
    point = stepPointLightColors,
    selection = stepSelectionLightColors,
)

internal val stepProgressBarDarkColors = SirioStepProgressBarColors(
    step = stepDarkColors,
    point = stepPointDarkColors,
    selection = stepSelectionDarkColors,
)

@Preview
@Composable
private fun SirioStepProgressBarPreview() {
    SirioTheme {
        Box(Modifier.background(Color.White)) {
            SirioStepProgressBar(
                steps = listOf(
                    SirioStepData(SirioStepPointType.DONE, "Step 1"),
                    SirioStepData(SirioStepPointType.WARNING, "Step 2"),
                    SirioStepData(SirioStepPointType.ERROR, "Step 3"),
                    SirioStepData(SirioStepPointType.TODO, "Step 4"),
                    SirioStepData(SirioStepPointType.TODO, "Step 5"),
                    SirioStepData(SirioStepPointType.TODO, "Step 6"),
                    SirioStepData(SirioStepPointType.TODO, "Step 7"),
                ),
                currentStepIndex = 4,
                back = SirioStepControlData(text = "Indietro", enabled = true, action = {}),
                next = SirioStepControlData(text = "Avanti", enabled = true, action = {}),
                onStepSelected = { _ -> true },
            ) {
                Column(Modifier.padding(8.dp)) {
                    SirioTextField(text = "text", label = "Label", onValueChange = {})
                }
            }
        }
    }
}
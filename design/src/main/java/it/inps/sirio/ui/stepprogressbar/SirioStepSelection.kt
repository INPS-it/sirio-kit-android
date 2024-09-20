//
// SirioStepSelection.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.R
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepSelectionHeight
import it.inps.sirio.theme.stepSelectionPaddingHorizontal
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioStepSelection(
    progress: Int,
    total: Int,
    currentStep: String,
) {
    Row(
        Modifier
            .height(stepSelectionHeight.dp)
            .fillMaxWidth()
            .background(SirioTheme.colors.stepProgressBar.selection.background)
            .padding(horizontal = stepSelectionPaddingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SirioText(
            text = stringResource(id = R.string.sirio_step_selection_progress, progress, total),
            color = SirioTheme.colors.stepProgressBar.selection.progress,
            typography = SirioTheme.typography.stepProgressBar.selection.progress,
        )
        Spacer(modifier = Modifier.weight(1f))
        SirioText(
            text = currentStep,
            color = SirioTheme.colors.stepProgressBar.selection.currentStep,
            typography = SirioTheme.typography.stepProgressBar.selection.currentStep,
        )
    }
}

@Keep
data class SirioStepSelectionColors(
    val background: Color,
    val progress: Color,
    val currentStep: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepSelectionColors(
            background = Color.Unspecified,
            progress = Color.Unspecified,
            currentStep = Color.Unspecified,
        )
    }
}

@Keep
data class SirioStepSelectionTypography(
    val progress: TextStyle,
    val currentStep: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioStepSelectionTypography(
            progress = TextStyle.Default,
            currentStep = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioStepSelectionPreview() {
    SirioTheme {
        SirioStepSelection(progress = 1, total = 6, currentStep = "Nome Passaggio")
    }
}
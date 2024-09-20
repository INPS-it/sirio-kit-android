//
// SirioStepProgressBar.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Keep
data class SirioStepProgressBarColors(
    val action: Color,
    val selection: SirioStepSelectionColors,
    val controls: SirioStepControlsColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepProgressBarColors(
            action = Color.Unspecified,
            selection = SirioStepSelectionColors.Unspecified,
            controls = SirioStepControlsColors.Unspecified,
        )
    }
}

@Keep
data class SirioStepProgressBarTypography(
    val selection: SirioStepSelectionTypography,
    val controls: SirioStepControlsTypography,
) {
    companion object {
        @Stable
        val Default = SirioStepProgressBarTypography(
            selection = SirioStepSelectionTypography.Default,
            controls = SirioStepControlsTypography.Default,
        )
    }
}
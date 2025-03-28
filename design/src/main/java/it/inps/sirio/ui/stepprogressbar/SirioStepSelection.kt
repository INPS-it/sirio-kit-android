//
// SirioStepMobileSelection.kt
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepSelectionHeight
import it.inps.sirio.theme.stepSelectionIconSize
import it.inps.sirio.theme.stepSelectionPaddingEnd
import it.inps.sirio.theme.stepSelectionPaddingIntra
import it.inps.sirio.theme.stepSelectionPaddingStart
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioStepSelection(
    title: String,
    currentStep: Int,
    totalSteps: Int,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(stepSelectionHeight.dp)
            .background(SirioTheme.colors.stepProgressBar.selection.background)
            .clickable(onClick = onClick)
            .padding(
                start = stepSelectionPaddingStart.dp,
                end = stepSelectionPaddingEnd.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SirioText(
            text = title,
            modifier = Modifier.weight(1f),
            color = SirioTheme.colors.stepProgressBar.selection.label,
            typography = SirioTheme.foundationTypography.headlineMdMiddle,
        )
        Spacer(Modifier.width(stepSelectionPaddingIntra.dp))
        SirioText(
            "${currentStep + 1} / $totalSteps",
            color = SirioTheme.colors.stepProgressBar.selection.progress,
            typography = SirioTheme.foundationTypography.labelMdRegular,
        )
        Spacer(Modifier.width(8.dp))
        val icon = if (expanded) FaIcons.ChevronUp else FaIcons.ChevronDown
        SirioIcon(
            icon = SirioIconSource.FaIcon(icon),
            iconColor = SirioTheme.colors.stepProgressBar.selection.icon,
            size = stepSelectionIconSize.dp,
        )
    }
}

@Keep
data class SirioStepSelectionColors(
    val background: Color,
    val label: Color,
    val progress: Color,
    val icon: Color,
    val shadow: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepSelectionColors(
            background = Color.Unspecified,
            label = Color.Unspecified,
            progress = Color.Unspecified,
            icon = Color.Unspecified,
            shadow = Color.Unspecified,
        )
    }
}

internal val stepSelectionLightColors = SirioStepSelectionColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    label = FoundationColor.colorGlobalSecondary100,
    progress = FoundationColor.colorAliasInteractivePrimaryDefault,
    icon = FoundationColor.colorAliasInteractivePrimaryDefault,
    shadow = FoundationColor.colorAliasBackgroundColorSecondaryDark130.copy(alpha = 0.1f),
)

internal val stepSelectionDarkColors = stepSelectionLightColors

@Preview
@Composable
private fun SirioStepSelectionPreview() {
    SirioTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SirioStepSelection(
                title = "Nome funzione",
                currentStep = 2,
                totalSteps = 7,
                expanded = false,
                onClick = {},
            )
            SirioStepSelection(
                title = "Nome funzione",
                currentStep = 2,
                totalSteps = 7,
                expanded = true,
                onClick = {},
            )
        }
    }
}
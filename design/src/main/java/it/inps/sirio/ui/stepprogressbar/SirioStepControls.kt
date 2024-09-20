//
// SirioStepControls.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.R
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepControlsHeight
import it.inps.sirio.theme.stepControlsHorizontalPadding
import it.inps.sirio.theme.stepControlsIconSize
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon

@Composable
fun SirioStepControls(
    previousEnabled: Boolean = true,
    nextEnabled: Boolean = true,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        Modifier
            .height(stepControlsHeight.dp)
            .fillMaxWidth()
            .background(SirioTheme.colors.stepProgressBar.controls.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = stepControlsHorizontalPadding.dp)
                .clickable(enabled = previousEnabled, role = Role.Button, onClick = onPrevious),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(stepControlsHorizontalPadding.dp)
        ) {
            SirioIcon(
                faIcon = FaIcons.AngleLeft,
                iconColor = SirioTheme.colors.stepProgressBar.action,
                size = stepControlsIconSize.dp,
            )
            SirioText(
                text = stringResource(id = R.string.sirio_step_controls_previous),
                color = SirioTheme.colors.stepProgressBar.controls.previous,
                typography = SirioTheme.typography.stepProgressBar.controls.previous,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = stepControlsHorizontalPadding.dp)
                .clickable(enabled = nextEnabled, role = Role.Button, onClick = onNext),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(stepControlsHorizontalPadding.dp)
        ) {
            SirioText(
                text = stringResource(id = R.string.sirio_step_controls_next),
                color = SirioTheme.colors.stepProgressBar.controls.next,
                typography = SirioTheme.typography.stepProgressBar.controls.next,
            )
            SirioIcon(
                faIcon = FaIcons.AngleRight,
                iconColor = SirioTheme.colors.stepProgressBar.action,
                size = stepControlsIconSize.dp,
            )
        }
    }
}

@Keep
data class SirioStepControlsColors(
    val background: Color,
    val previous: Color,
    val next: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepControlsColors(
            background = Color.Unspecified,
            previous = Color.Unspecified,
            next = Color.Unspecified,
        )
    }
}

@Keep
data class SirioStepControlsTypography(
    val previous: TextStyle,
    val next: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioStepControlsTypography(
            previous = TextStyle.Default,
            next = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioStepControlsPreview() {
    SirioTheme {
        SirioStepControls(onPrevious = {}, onNext = {})
    }
}
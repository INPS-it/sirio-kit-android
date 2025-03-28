//
// SirioStepPoint.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.stepprogressbar

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseColors
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.stepPointIconSize
import it.inps.sirio.theme.stepPointSize
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

@Composable
internal fun SirioStepPoint(
    type: SirioStepPointType,
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Box(
        modifier = modifier
            .size(stepPointSize.dp)
            .clip(CircleShape)
            .background(type.toContainerColor()),
        contentAlignment = Alignment.Center,
    ) {
        type.toIcon()?.let {
            SirioIcon(
                icon = SirioIconSource.FaIcon(it),
                iconColor = type.toContentColor(),
                size = stepPointIconSize.dp,
            )
        } ?: Text(
            text = text.orEmpty(),
            color = type.toContentColor(),
            style = SirioTheme.foundationTypography.labelNumberMdHeavy,
        )
    }
}

enum class SirioStepPointType {
    TODO,
    DONE,
    ERROR,
    WARNING,
    INACTIVE
}

@Composable
fun SirioStepPointType.toContainerColor(): Color = when (this) {
    SirioStepPointType.TODO -> SirioTheme.colors.stepProgressBar.point.todo.container
    SirioStepPointType.DONE -> SirioTheme.colors.stepProgressBar.point.done.container
    SirioStepPointType.ERROR -> SirioTheme.colors.stepProgressBar.point.error.container
    SirioStepPointType.WARNING -> SirioTheme.colors.stepProgressBar.point.warning.container
    SirioStepPointType.INACTIVE -> SirioTheme.colors.stepProgressBar.point.inactive.container
}

@Composable
fun SirioStepPointType.toContentColor(): Color = when (this) {
    SirioStepPointType.TODO -> SirioTheme.colors.stepProgressBar.point.todo.content
    SirioStepPointType.DONE -> SirioTheme.colors.stepProgressBar.point.done.content
    SirioStepPointType.ERROR -> SirioTheme.colors.stepProgressBar.point.error.content
    SirioStepPointType.WARNING -> SirioTheme.colors.stepProgressBar.point.warning.content
    SirioStepPointType.INACTIVE -> SirioTheme.colors.stepProgressBar.point.inactive.content
}

fun SirioStepPointType.toIcon(): FaIconType? = when (this) {
    SirioStepPointType.TODO -> null
    SirioStepPointType.DONE -> FaIcons.Check
    SirioStepPointType.ERROR -> FaIcons.ExclamationTriangle
    SirioStepPointType.WARNING -> FaIcons.ExclamationCircle
    SirioStepPointType.INACTIVE -> null
}

@Keep
data class SirioStepPointStateColors(
    val todo: SirioBaseColors,
    val done: SirioBaseColors,
    val error: SirioBaseColors,
    val warning: SirioBaseColors,
    val inactive: SirioBaseColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioStepPointStateColors(
            todo = SirioBaseColors.Unspecified,
            done = SirioBaseColors.Unspecified,
            error = SirioBaseColors.Unspecified,
            warning = SirioBaseColors.Unspecified,
            inactive = SirioBaseColors.Unspecified,
        )
    }
}

val stepPointLightColors = SirioStepPointStateColors(
    todo = SirioBaseColors(
        container = FoundationColor.colorAliasInteractivePrimaryDefault,
        content = FoundationColor.colorAliasAppInteractivePrimary000Default,
    ),
    done = SirioBaseColors(
        container = FoundationColor.colorAliasInteractiveSuccessDefault,
        content = FoundationColor.colorGlobalLightPrimary40,
    ),
    error = SirioBaseColors(
        container = FoundationColor.colorAliasInteractiveAlertDefault,
        content = FoundationColor.colorGlobalLightPrimary40,
    ),
    warning = SirioBaseColors(
        container = FoundationColor.colorAliasInteractiveWarningDefault,
        content = FoundationColor.colorGlobalLightPrimary40,
    ),
    inactive = SirioBaseColors(
        container = FoundationColor.colorAliasBackgroundColorDisabled,
        content = FoundationColor.colorAliasTextColorDisabled,
    )
)

val stepPointDarkColors = stepPointLightColors

@Preview
@Composable
private fun SirioStepPointPreview() {
    SirioTheme {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            SirioStepPoint(type = SirioStepPointType.TODO, text = "00")
            SirioStepPoint(type = SirioStepPointType.DONE)
            SirioStepPoint(type = SirioStepPointType.ERROR)
            SirioStepPoint(type = SirioStepPointType.WARNING)
            SirioStepPoint(type = SirioStepPointType.INACTIVE, text = "00")
        }
    }
}
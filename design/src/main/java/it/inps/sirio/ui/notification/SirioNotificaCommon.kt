//
// SirioNotificaCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.notification

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme

enum class SirioNotificaState {
    Alert,
    Info,
    Warning,
    Success
}

internal fun SirioNotificaState.toIcon(): FaIconType = when (this) {
    SirioNotificaState.Alert -> FaIcons.ExclamationTriangle
    SirioNotificaState.Info -> FaIcons.InfoCircle
    SirioNotificaState.Warning -> FaIcons.ExclamationCircle
    SirioNotificaState.Success -> FaIcons.CheckCircle
}

@Composable
internal fun SirioNotificaState.toColor(): Color = when (this) {
    SirioNotificaState.Alert -> SirioTheme.colors.notifica.state.alert
    SirioNotificaState.Info -> SirioTheme.colors.notifica.state.info
    SirioNotificaState.Warning -> SirioTheme.colors.notifica.state.warning
    SirioNotificaState.Success -> SirioTheme.colors.notifica.state.success
}

@Keep
data class SirioNotificaColors(
    val pagina: SirioNotificaTypeColors,
    val inLinea: SirioNotificaTypeColors,
    val state: SirioNotificaStateColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioNotificaColors(
            pagina = SirioNotificaTypeColors.Unspecified,
            inLinea = SirioNotificaTypeColors.Unspecified,
            state = SirioNotificaStateColors.Unspecified,
        )
    }
}

@Keep
data class SirioNotificaTypeColors(
    val background: Color,
    val icon: Color?,
    val close: Color,
    val title: Color,
    val text: Color,
    val link: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioNotificaTypeColors(
            background = Color.Unspecified,
            icon = Color.Unspecified,
            close = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            link = Color.Unspecified,
        )
    }
}

@Keep
data class SirioNotificaStateColors(
    val alert: Color,
    val info: Color,
    val warning: Color,
    val success: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioNotificaStateColors(
            alert = Color.Unspecified,
            info = Color.Unspecified,
            warning = Color.Unspecified,
            success = Color.Unspecified,
        )
    }
}

internal val notificaStateColor = SirioNotificaStateColors(
    alert = FoundationColor.colorGlobalSemanticAlert100,
    info = FoundationColor.colorGlobalSemanticInfo100,
    warning = FoundationColor.colorGlobalSemanticWarning100,
    success = FoundationColor.colorGlobalSemanticSuccess100,
)

internal val notificaLightPaginaColors = SirioNotificaTypeColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    icon = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    close = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
)

internal val notificaLightInLineaColors = SirioNotificaTypeColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    icon = null,
    close = FoundationColor.colorAliasInteractiveSecondaryDefault,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
)

internal val notificaLightColors = SirioNotificaColors(
    pagina = notificaLightPaginaColors,
    inLinea = notificaLightInLineaColors,
    state = notificaStateColor,
)

internal val notificaDarkColors = notificaLightColors

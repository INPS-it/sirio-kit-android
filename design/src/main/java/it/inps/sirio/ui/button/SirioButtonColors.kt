//
// SirioButtonColors.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState

@Keep
data class SirioButtonColors(
    val primary: SirioButtonStateColors,
    val secondary: SirioButtonStateColors,
    val tertiaryDark: SirioButtonStateColors,
    val tertiaryLight: SirioButtonStateColors,
    val ghostLight: SirioButtonStateColors,
    val ghostDark: SirioButtonStateColors,
    val danger: SirioButtonStateColors,
    val dangerTertiary: SirioButtonStateColors,
    val dangerGhost: SirioButtonStateColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioButtonColors(
            primary = SirioButtonStateColors.Unspecified,
            secondary = SirioButtonStateColors.Unspecified,
            tertiaryDark = SirioButtonStateColors.Unspecified,
            tertiaryLight = SirioButtonStateColors.Unspecified,
            ghostLight = SirioButtonStateColors.Unspecified,
            ghostDark = SirioButtonStateColors.Unspecified,
            danger = SirioButtonStateColors.Unspecified,
            dangerTertiary = SirioButtonStateColors.Unspecified,
            dangerGhost = SirioButtonStateColors.Unspecified,
        )
    }
}

@Keep
data class SirioButtonStateColors(
    val container: SirioColorState,
    val content: SirioColorState,
    val border: SirioColorState?,
) {
    companion object {
        @Stable
        val Unspecified = SirioButtonStateColors(
            container = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
        )
    }
}

val buttonLightColors = SirioButtonColors(
    primary = SirioButtonStateColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasTextColorPrimaryLight0,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null,
    ),
    secondary = SirioButtonStateColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractiveSecondaryDefault,
            pressed = FoundationColor.colorAliasInteractiveSecondaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasTextColorPrimaryLight0,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null,
    ),
    tertiaryDark = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = Color.Transparent,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAccentDefault,
            pressed = FoundationColor.colorAliasInteractiveAccentPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAccentDefault,
            pressed = FoundationColor.colorAliasInteractiveAccentPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
    ),
    tertiaryLight = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = Color.Transparent,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
    ),
    ghostLight = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null,
    ),
    ghostDark = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = FoundationColor.colorAliasInteractiveAccentPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAccentDefault,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null,
    ),
    danger = SirioButtonStateColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAlertDefault,
            pressed = FoundationColor.colorAliasInteractiveAlertPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasTextColorPrimaryLight0,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null,
    ),
    dangerTertiary = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = Color.Transparent,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAlertDefault,
            pressed = FoundationColor.colorAliasInteractiveAlertPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAlertDefault,
            pressed = FoundationColor.colorAliasInteractiveAlertPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
    ),
    dangerGhost = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = FoundationColor.colorAliasInteractiveAlertPressed,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAlertDefault,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null,
    ),
)

val buttonDarkColors = buttonLightColors

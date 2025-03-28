//
// SirioChipCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.chip

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState

@Keep
data class SirioChipColors(
    val close: SirioChipCloseColors,
    val selectable: SirioChipSelectableColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioChipColors(
            close = SirioChipCloseColors.Unspecified,
            selectable = SirioChipSelectableColors.Unspecified,
        )
    }
}

@Keep
data class SirioChipCloseColors(
    val container: SirioColorState,
    val content: SirioColorState,
    val button: SirioColorState,
    val buttonBorder: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioChipCloseColors(
            container = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
            button = SirioColorState.Unspecified,
            buttonBorder = SirioColorState.Unspecified,
        )
    }
}

@Keep
data class SirioChipSelectableColors(
    val container: SirioColorState,
    val containerSelected: SirioColorState,
    val content: SirioColorState,
    val contentSelected: SirioColorState,
    val border: SirioColorState,
    val borderSelected: SirioColorState?,
) {
    companion object {
        @Stable
        val Unspecified = SirioChipSelectableColors(
            container = SirioColorState.Unspecified,
            containerSelected = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
            contentSelected = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            borderSelected = SirioColorState.Unspecified,
        )
    }
}

internal val chipLightColors = SirioChipColors(
    close = SirioChipCloseColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractiveSecondaryDefault,
            pressed = FoundationColor.colorAliasInteractiveSecondaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
            pressed = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        button = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        buttonBorder = SirioColorState(
            default = FoundationColor.colorGlobalMidPrimary70,
            pressed = FoundationColor.colorGlobalMidPrimary70,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
    ),
    selectable = SirioChipSelectableColors(
        container = SirioColorState.all(Color.Transparent),
        containerSelected = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        contentSelected = SirioColorState(
            default = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
            pressed = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        borderSelected = null
    )
)

internal
val chipDarkColors = chipLightColors
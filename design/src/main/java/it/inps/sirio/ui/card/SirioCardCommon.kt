//
// SirioCardCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.card

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import it.inps.sirio.theme.SirioBaseColors
import it.inps.sirio.ui.button.SirioButtonLegacyColors

enum class SirioCardColor {
    DARK,
    LIGHT,
}

@Keep
data class SirioCardsColors(
    val editorial: SirioEditorialCardColors,
    val process: SirioProcessCardColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioCardsColors(
            editorial = SirioEditorialCardColors.Unspecified,
            process = SirioProcessCardColors.Unspecified,
        )
    }
}

internal val cardLightColors = SirioCardsColors(
    editorial = cardEditorialLightColors,
    process = cardProcessLightColors,
)

internal val cardDarkColors = SirioCardsColors(
    editorial = cardEditorialDarkColors,
    process = cardProcessDarkColors,
)

@Keep
data class SirioCardColors(
    val background: Color,
    val category: SirioBaseColors,
    val icon: Color,
    val date: Color,
    val title: Color,
    val subtitle: Color,
    val text: Color,
    val signature: Color,
    val button: SirioButtonLegacyColors,
    val iconButton: SirioButtonLegacyColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioCardColors(
            background = Color.Unspecified,
            category = SirioBaseColors.Unspecified,
            icon = Color.Unspecified,
            date = Color.Unspecified,
            title = Color.Unspecified,
            subtitle = Color.Unspecified,
            text = Color.Unspecified,
            signature = Color.Unspecified,
            button = SirioButtonLegacyColors.Unspecified,
            iconButton = SirioButtonLegacyColors.Unspecified,
        )
    }
}

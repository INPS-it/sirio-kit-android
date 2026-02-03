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

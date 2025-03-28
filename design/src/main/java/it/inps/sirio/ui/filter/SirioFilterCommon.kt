//
// SirioFilterCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Keep
data class SirioFilterColors(
    val background: Color,
    val close: Color,
    val header: Color,
    val info: Color,
    val selectedBackground: Color,
    val title: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioFilterColors(
            background = Color.Unspecified,
            close = Color.Unspecified,
            header = Color.Unspecified,
            info = Color.Unspecified,
            selectedBackground = Color.Unspecified,
            title = Color.Unspecified
        )
    }
}

@Keep
data class SirioFilterTypography(
    val header: TextStyle,
    val title: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioFilterTypography(
            header = TextStyle.Default,
            title = TextStyle.Default,
        )
    }
}

//
// Type.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

internal val Titillium_Web = TitilliumWebFamily
internal val Lora = LoraFamily
internal val Roboto_Mono = RobotoMonoFamily

@Keep
enum class TypographyStyle {
    Regular {
        override fun getFontWeight(): FontWeight = FontWeight.Normal
        override fun getFontStyle(): FontStyle? = null
    },
    Bold {
        override fun getFontWeight(): FontWeight = FontWeight.Bold
        override fun getFontStyle(): FontStyle? = null
    },
    Light {
        override fun getFontWeight(): FontWeight = FontWeight.Light
        override fun getFontStyle(): FontStyle? = null
    },
    Italic {
        override fun getFontWeight(): FontWeight? = null
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    Medium {
        override fun getFontWeight(): FontWeight = FontWeight.Medium
        override fun getFontStyle(): FontStyle? = null
    },
    SemiBold {
        override fun getFontWeight(): FontWeight = FontWeight.SemiBold
        override fun getFontStyle(): FontStyle? = null
    },
    LightItalic {
        override fun getFontWeight(): FontWeight = FontWeight.Light
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    BoldItalic {
        override fun getFontWeight(): FontWeight = FontWeight.Bold
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    SemiBoldItalic {
        override fun getFontWeight(): FontWeight = FontWeight.SemiBold
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    MediumItalic {
        override fun getFontWeight(): FontWeight = FontWeight.Medium
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    };

    abstract fun getFontWeight(): FontWeight?
    abstract fun getFontStyle(): FontStyle?
}

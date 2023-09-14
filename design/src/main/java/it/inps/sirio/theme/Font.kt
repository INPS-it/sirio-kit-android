//
// Font.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import it.inps.sirio.R

internal val TitilliumWebFamily = FontFamily(
    Font(R.font.titilliumweb_regular, FontWeight.W400, FontStyle.Normal),
    Font(R.font.titilliumweb_bold, FontWeight.W700, FontStyle.Normal),
    Font(R.font.titilliumweb_italic, FontWeight.W400, FontStyle.Italic),
    Font(R.font.titilliumweb_light, FontWeight.W300, FontStyle.Normal),
    Font(R.font.titilliumweb_semibold, FontWeight.W600, FontStyle.Normal),
)

internal val RobotoMonoFamily = FontFamily(
    Font(R.font.robotomono_bold, FontWeight.W700, FontStyle.Normal),
    Font(R.font.robotomono_light, FontWeight.W300, FontStyle.Normal),
    Font(R.font.robotomono_medium, FontWeight.W500, FontStyle.Normal),
    Font(R.font.robotomono_regular, FontWeight.W400, FontStyle.Normal),
    Font(R.font.robotomono_thin, FontWeight.W100, FontStyle.Normal),
)

internal val LoraFamily = FontFamily(
    Font(R.font.lora),
)
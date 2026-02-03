//
// SirioIconSource.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.utils

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType

@Keep
sealed class SirioIconSource {
    data class FaIcon(val faIcon: FaIconType) : SirioIconSource()
    data class Drawable(@param:DrawableRes val iconResId: Int) : SirioIconSource()
}

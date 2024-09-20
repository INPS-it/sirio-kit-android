//
// SirioIconData.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType

/**
 * Data class representing an icon that can be displayed using either a FontAwesome icon
 * or a drawable resource.
 *
 * @property faIcon The FontAwesome icon type to display.
 * @property iconResId The resource ID of the drawable to display.
 * @property iconColor The color of the icon.
 * @property size The size of the icon.
 * @property contentDescription The content description for accessibility.
 *
 *@constructor Creates a new [SirioIconData] instance.
 *
 * This class provides two convenience constructors:
 *
 * * `SirioIconData(faIcon: FaIconType, iconColor: Color, size: Dp = 24.dp, contentDescription: String? = null)`
 *   for creating an icon from a FontAwesome icon type.
 *
 * * `SirioIconData(@DrawableRes iconResId: Int, iconColor: Color, size: Dp = 24.dp, contentDescription: String? = null)`
 *   for creating an icon from adrawable resource ID.
 */
data class SirioIconData internal constructor(
    val faIcon: FaIconType?,
    @DrawableRes val iconResId: Int?,
    val iconColor: Color,
    val size: Dp,
    val contentDescription: String?,
) {
    constructor(
        faIcon: FaIconType,
        iconColor: Color,
        size: Dp = 24.dp,
        contentDescription: String? = null,
    ) : this(
        faIcon = faIcon,
        iconResId = null,
        iconColor = iconColor,
        size = size,
        contentDescription = contentDescription
    )

    constructor(
        @DrawableRes iconResId: Int,
        iconColor: Color,
        size: Dp = 24.dp,
        contentDescription: String? = null,
    ) : this(
        faIcon = null,
        iconResId = iconResId,
        iconColor = iconColor,
        size = size,
        contentDescription = contentDescription
    )
}
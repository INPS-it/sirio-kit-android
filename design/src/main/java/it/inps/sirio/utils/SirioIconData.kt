//
// SirioIconData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.utils

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType

/**
 * Contain data for displaying Sirio icons.
 *
 * This class holds the necessary information to render an icon, including the icon source,
 * color, size, content description, and an optional click handler.
 *
 * @property icon The source of the icon to be displayed, represented by a [SirioIconSource].
 * @property iconColor The color to apply to the icon.
 * @property size The size of the icon in Dp. Defaults to 24.dp.
 * @property contentDescription An optional description of the icon for accessibility purposes.
 * @property onclick An optional lambda function to be executed when the icon is clicked.
 *
 * @constructor Creates a new SirioIconData instance.
 */
@Keep
data class SirioIconData(
    val icon: SirioIconSource,
    val iconColor: Color,
    val size: Dp = 24.dp,
    val contentDescription: String? = null,
    val onclick: (() -> Unit)? = null,
) {
    @Deprecated("Use primary constructor with SirioIconSource instead")
    constructor(
        faIcon: FaIconType,
        iconColor: Color,
        size: Dp = 24.dp,
        contentDescription: String? = null,
        onclick: (() -> Unit)? = null,
    ) : this(
        icon = SirioIconSource.FaIcon(faIcon),
        iconColor = iconColor,
        size = size,
        contentDescription = contentDescription,
        onclick = onclick,
    )

    @Deprecated("Use primary constructor with SirioIconSource instead")
    constructor(
        @DrawableRes iconResId: Int,
        iconColor: Color,
        size: Dp = 24.dp,
        contentDescription: String? = null,
        onclick: (() -> Unit)? = null,
    ) : this(
        icon = SirioIconSource.Drawable(iconResId),
        iconColor = iconColor,
        size = size,
        contentDescription = contentDescription,
        onclick = onclick,
    )
}
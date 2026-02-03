//
// SirioIcon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays an icon from either a FontAwesome icon or a drawable resource.
 *
 * @param icon The source of the icon to display. Can be either a [SirioIconSource.FaIcon] or a [SirioIconSource.Drawable].
 * @param iconColor The color of the icon.
 * @param size The size of the icon. Defaults to 24.dp.
 * @param contentDescription The content description for accessibility.
 * @param onClick An optional lambda function to be executed when the icon is clicked. If null, the icon is not clickable.
 */
@Composable
fun SirioIcon(
    icon: SirioIconSource,
    iconColor: Color,
    size: Dp = 24.dp,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    Box(
        Modifier
            .requiredSize(size)
            .ifElse(
                condition = onClick != null,
                ifTrueModifier = Modifier.clickable(
                    enabled = onClick != null,
                    role = Role.Button,
                    onClick = { onClick?.invoke() })
            ),
        contentAlignment = Alignment.Center
    ) {
        when (icon) {
            is SirioIconSource.FaIcon -> SirioFaIcon(
                faIcon = icon.faIcon,
                size = size,
                tint = iconColor,
                contentDescription = contentDescription,
            )

            is SirioIconSource.Drawable -> Icon(
                painter = painterResource(id = icon.iconResId),
                contentDescription = contentDescription,
                modifier = Modifier.size(size),
                tint = iconColor,
            )
        }
    }
}

/**
 * Creates a custom icon using a [SirioIconData] object.
 *
 * @param iconData The [SirioIconData] object containing the icon parameters.
 */
@Composable
fun SirioIcon(iconData: SirioIconData) {
    SirioIcon(
        icon = iconData.icon,
        iconColor = iconData.iconColor,
        size = iconData.size,
        contentDescription = iconData.contentDescription,
        onClick = iconData.onClick,
    )
}

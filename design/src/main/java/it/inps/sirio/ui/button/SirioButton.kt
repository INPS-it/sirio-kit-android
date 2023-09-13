//
// SirioButton.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.DarkColorPalette
import it.inps.sirio.theme.LightColorPalette
import it.inps.sirio.theme.SirioTheme

/**
 * The Sirio Button
 *
 * @param modifier the Modifier to be applied to this button
 * @param text The string on the button
 * @param size The [ButtonSize]
 * @param style The [ButtonStyle]
 * @param icon The icon
 * @param iconResId Resources object to query the image file from
 * @param enabled Whether the button is enabled
 * @param onClick The callback when the button is clicked
 */
@Composable
fun SirioButton(
    modifier: Modifier = Modifier,
    text: String? = "",
    size: ButtonSize,
    style: ButtonStyle,
    icon: FaIconType? = null,
    @DrawableRes iconResId: Int? = null,
    enabled: Boolean = true,
    iconContentDescription: String? = null,
    onClick: () -> Unit,
) {
    val colors: SirioButtonColors = when (style) {
        ButtonStyle.Primary -> SirioTheme.colors.buttons.primary
        ButtonStyle.Secondary -> SirioTheme.colors.buttons.secondary
        ButtonStyle.Tertiary -> SirioTheme.colors.buttons.tertiary
        ButtonStyle.TertiaryDark -> DarkColorPalette.buttons.tertiary
        ButtonStyle.TertiaryLight -> LightColorPalette.buttons.tertiary
        ButtonStyle.Ghost -> SirioTheme.colors.buttons.ghost
        ButtonStyle.Danger -> SirioTheme.colors.buttons.danger
    }

    SirioButtonCommon(
        size = size,
        colors = colors,
        modifier = modifier,
        text = text,
        faIcon = icon,
        iconResId = iconResId,
        enabled = enabled,
        iconContentDescription = iconContentDescription,
        onClick = onClick,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ButtonTextIconPreview() {
    SirioTheme {
        val text = "Text"
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            Row {
                SirioButton(
                    text = text,
                    style = ButtonStyle.Tertiary,
                    size = ButtonSize.Large,
                ) {}
                SirioButton(
                    text = text,
                    icon = FaIcons.ArrowRight,
                    style = ButtonStyle.Tertiary,
                    size = ButtonSize.Large,
                ) {}
                SirioButton(
                    text = "",
                    icon = FaIcons.ArrowRight,
                    style = ButtonStyle.Tertiary,
                    size = ButtonSize.Large,
                ) {}
            }
        }
    }
}
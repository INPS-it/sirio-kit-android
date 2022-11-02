//
// SirioButton.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * The Sirio Button
 *
 * @param text The string on the button
 * @param size The [ButtonSize]
 * @param style The [ButtonStyle]
 * @param icon The icon
 * @param enabled Whether the button is enabled
 * @param useMaxWidth Whether the button had to fill the parent width
 * @param onClick The callback when the button is clicked
 */
@Composable
fun SirioButton(
    text: String? = "",
    size: ButtonSize,
    style: ButtonStyle,
    icon: FaIconType? = null,
    enabled: Boolean = true,
    useMaxWidth: Boolean = false,
    onClick: () -> Unit
) {
    val colors: SirioButtonColors = when (style) {
        ButtonStyle.Primary -> SirioTheme.colors.buttons.buttonPrimary
        ButtonStyle.Secondary -> SirioTheme.colors.buttons.buttonSecondary
        ButtonStyle.Tertiary -> SirioTheme.colors.buttons.buttonTertiary
        ButtonStyle.Ghost -> SirioTheme.colors.buttons.buttonGhost
        ButtonStyle.Danger -> SirioTheme.colors.buttons.buttonDanger
    }

    SirioButtonCommon(
        text = text,
        icon = icon,
        enabled = enabled,
        colors = colors,
        useMaxWidth = useMaxWidth,
        onClick = onClick,
        size = size,
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
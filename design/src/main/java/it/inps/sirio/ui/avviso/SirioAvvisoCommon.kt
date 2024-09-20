//
// SirioAvvisoCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.avviso

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.avvisoIconPaddingEnd
import it.inps.sirio.theme.avvisoIconPaddingTop
import it.inps.sirio.theme.avvisoPaddingBottom
import it.inps.sirio.theme.avvisoPaddingHorizontal
import it.inps.sirio.theme.avvisoPaddingTop
import it.inps.sirio.theme.avvisoSpacerTextButton
import it.inps.sirio.theme.avvisoSpacerTitleText
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.SirioButtonCommon
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioFaIcon

@Composable
internal fun SirioAvvisoCommon(
    title: String,
    text: String,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier.background(SirioTheme.colors.avviso.background),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    avvisoPaddingHorizontal.dp,
                    avvisoPaddingTop.dp,
                    avvisoPaddingHorizontal.dp,
                    avvisoPaddingBottom.dp
                )
        ) {
            SirioFaIcon(
                faIcon = FaIcons.InfoCircle,
                modifier = Modifier.padding(
                    top = avvisoIconPaddingTop.dp,
                    end = avvisoIconPaddingEnd.dp
                ),
                tint = SirioTheme.colors.avviso.icon,
            )
            Column {
                SirioTextCommon(
                    text = title,
                    color = SirioTheme.colors.avviso.title,
                    typography = SirioTheme.typography.avviso.title,
                )
                Spacer(modifier = Modifier.height(avvisoSpacerTitleText.dp))
                SirioTextCommon(
                    text = text,
                    color = SirioTheme.colors.avviso.text,
                    typography = SirioTheme.typography.avviso.text,
                )
                Spacer(modifier = Modifier.height(avvisoSpacerTextButton.dp))
                SirioButtonCommon(
                    size = ButtonSize.Large,
                    colors = SirioTheme.colors.buttons.tertiary,
                    text = buttonText,
                    onClick = onButtonClick,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SirioTheme.colors.avviso.borderBottom)
        )
    }
}

@Keep
data class SirioAvvisoColors(
    val background: Color,
    val borderBottom: Color,
    val title: Color,
    val text: Color,
    val icon: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAvvisoColors(
            background = Color.Unspecified,
            borderBottom = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            icon = Color.Unspecified,
        )
    }
}

@Keep
data class SirioAvvisoTypography(
    val title: TextStyle,
    val text: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioAvvisoTypography(
            title = TextStyle.Default,
            text = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioAvvisoCommonPreview() {
    Column {
        val title = "Titolo avviso"
        val text =
            "Ac iaculis posuere turpis diam mi non viverra tempus eget. Nunc volutpat nunc erat risus eleifend convallis viverra bibendum. Mattis ante mauris sit montes. Scelerisque dui arcu tempus proin massa massa ultricies nunc duis."
        val buttonText = "Text"
        val onButtonClick = {}
        SirioTheme {
            SirioAvvisoCommon(
                title = title,
                text = text,
                buttonText = buttonText,
                onButtonClick = onButtonClick,
            )
        }
        SirioTheme(darkTheme = true) {
            SirioAvvisoCommon(
                title = title,
                text = text,
                buttonText = buttonText,
                onButtonClick = onButtonClick,
            )
        }
    }
}
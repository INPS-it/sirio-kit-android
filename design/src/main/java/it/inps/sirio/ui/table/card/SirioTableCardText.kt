//
// SirioTableCardText.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioTableCardText(
    title: String,
    text: String,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardCommon(
        title = title,
        themeMode = themeMode,
        showDivider = showDivider,
        content = textContent(text),
    )
}

@Composable
private fun textContent(
    text: String,
): @Composable (RowScope.() -> Unit)? = text.takeIf { it.isNotBlank() }?.let {
    {
        SirioText(
            text = text,
            modifier = Modifier.weight(1f),
            color = SirioTheme.colors.table.card.text,
            typography = SirioTheme.foundationTypography.labelMdRegular,
        )
    }
}

@Composable
internal fun SirioTableCardText(
    data: SirioTableCardType.Text,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardText(
        title = data.title,
        text = data.text,
        themeMode = themeMode,
        showDivider = showDivider,
    )
}

@Preview
@Composable
private fun SirioTableCardTextPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableCardText(
                title = "Header",
                text = "Lorem ipsum",
            )
            SirioTableCardText(
                title = "Header",
                text = "Lorem ipsum",
                themeMode = SirioThemeMode.Dark,
            )
            SirioTableCardText(
                title = "Lorem ipsum dolor isit amen ipsum dolor isit amen ipsum dolor isit amen ipsum dolor isit amen lorem ipsum dolor isit amen ipsum lorem ipsum dolor isit amen ipsum lorem ipsum dolor isit amen ipsum",
                text = "",
                themeMode = SirioThemeMode.Dark,
            )
            SirioTableCardText(
                title = "",
                text = "Lorem ipsum dolor isit amen ipsum dolor isit amen ipsum dolor isit amen ipsum dolor isit amen lorem ipsum dolor isit amen ipsum lorem ipsum dolor isit amen ipsum lorem ipsum dolor isit amen ipsum",
                themeMode = SirioThemeMode.Dark,
            )
        }
    }
}

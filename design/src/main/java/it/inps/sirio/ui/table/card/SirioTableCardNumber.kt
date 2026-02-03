//
// SirioTableCardNumber.kt
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
fun SirioTableCardNumber(
    title: String,
    text: String,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardCommon(
        title = title,
        themeMode = themeMode,
        showDivider = showDivider,
        content = numberContent(text)
    )
}

@Composable
private fun numberContent(
    text: String,
): @Composable (RowScope.() -> Unit)? = text.takeIf { it.isNotBlank() }?.let {
    {
        SirioText(
            text = text,
            modifier = Modifier.weight(1f),
            color = SirioTheme.colors.table.card.number,
            typography = SirioTheme.foundationTypography.labelNumberMdRegular,
        )
    }
}

@Composable
internal fun SirioTableCardNumber(
    data: SirioTableCardType.Number,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardNumber(
        title = data.title,
        text = data.text,
        themeMode = themeMode,
        showDivider = showDivider,
    )
}

@Preview
@Composable
private fun SirioTableCardNumberPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableCardNumber(
                title = "Header",
                text = "00",
            )
            SirioTableCardNumber(
                title = "Header",
                text = "00",
                themeMode = SirioThemeMode.Dark,
            )
            SirioTableCardNumber(
                title = "Header",
                text = "",
            )
            SirioTableCardNumber(
                title = "",
                text = "00",
                themeMode = SirioThemeMode.Dark,
            )
        }
    }
}

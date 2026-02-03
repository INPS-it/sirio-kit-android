//
// SirioTableDrawerText.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioTableDrawerText(
    title: String,
    text: String,
) {
    SirioTableDrawerCommon(
        title = title,
    ) {
        SirioText(
            text = text,
            modifier = Modifier.weight(1f),
            color = SirioTheme.colors.table.drawer.text,
            typography = SirioTheme.foundationTypography.labelMdRegular,
        )
    }
}

@Composable
internal fun SirioTableDrawerText(
    data: SirioTableDrawerType.Text,
) {
    SirioTableDrawerText(
        title = data.title,
        text = data.text,
    )
}

@Preview
@Composable
private fun SirioTableDrawerTextPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableDrawerText(
                title = "Header",
                text = "Lorem ipsum",
            )
        }
    }
}
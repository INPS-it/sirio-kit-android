//
// SirioTableDrawerNumber.kt
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
fun SirioTableDrawerNumber(
    title: String,
    text: String,
) {
    SirioTableDrawerCommon(
        title = title,
    ) {
        SirioText(
            text = text,
            modifier = Modifier.weight(1f),
            color = SirioTheme.colors.table.drawer.number,
            typography = SirioTheme.foundationTypography.labelNumberMdRegular,
        )
    }
}

@Composable
internal fun SirioTableDrawerNumber(
    data: SirioTableDrawerType.Number,
) {
    SirioTableDrawerNumber(
        title = data.title,
        text = data.text,
    )
}

@Preview
@Composable
private fun SirioTableDrawerNumberPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableDrawerNumber(
                title = "Header",
                text = "00",
            )
        }
    }
}
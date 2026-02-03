//
// SirioTableTitle.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableTitlePaddingBottom
import it.inps.sirio.theme.tableTitlePaddingHorizontal
import it.inps.sirio.theme.tableTitlePaddingTop
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioTableTitle(
    title: String,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.table.background)
            .padding(horizontal = tableTitlePaddingHorizontal.dp)
            .padding(top = tableTitlePaddingTop.dp, bottom = tableTitlePaddingBottom.dp),
    ) {
        SirioText(
            text = title,
            color = SirioTheme.colors.table.title,
            typography = SirioTheme.foundationTypography.headlineSmHeavy,
        )
    }
}

@Preview
@Composable
private fun SirioTableTitlePreview() {
    SirioTheme {
        Column {
            SirioTableTitle(
                title = "Titolo Tabella",
            )
            SirioTableTitle(
                title = "Titolo Tabella",
            )
        }
    }
}
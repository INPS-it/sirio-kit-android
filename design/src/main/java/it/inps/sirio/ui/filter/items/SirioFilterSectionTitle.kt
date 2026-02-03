//
// SirioFilterSectionTitle.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterSectionTitlePadding
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioFilterSectionTitle(
    title: String,
) {
    SirioText(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(filterSectionTitlePadding.dp),
        color = SirioTheme.colors.filter.sectionTitle,
        typography = SirioTheme.foundationTypography.headlineMdHeavy,
    )
}

@Preview
@Composable
private fun SirioFilterSectionTitlePreview() {
    SirioTheme {
        SirioFilterSectionTitle("Titolo della sezione")
    }
}
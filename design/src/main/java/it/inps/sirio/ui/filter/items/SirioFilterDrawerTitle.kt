//
// SirioFilterDrawerTitle.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterDrawerTitlePaddingHorizontal
import it.inps.sirio.theme.filterDrawerTitlePaddingVertical
import it.inps.sirio.theme.filterDrawerTitleSpacing
import it.inps.sirio.ui.text.SirioText

/**
 * A title component for filter sections.
 *
 * @param title The text to display as the title.
 * @param text The text to display as a description.
 */
@Composable
fun SirioFilterDrawerTitle(
    title: String? = null,
    text: String? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterDrawerTitlePaddingHorizontal.dp,
                vertical = filterDrawerTitlePaddingVertical.dp
            ),
        verticalArrangement = Arrangement.spacedBy(filterDrawerTitleSpacing.dp)
    ) {
        if (!title.isNullOrBlank()) {
            SirioText(
                text = title,
                color = SirioTheme.colors.filter.title,
                typography = SirioTheme.foundationTypography.labelMdHeavy,
            )
        }
        if (!text.isNullOrBlank()) {
            SirioText(
                text = text,
                color = SirioTheme.colors.filter.text,
                typography = SirioTheme.foundationTypography.bodyMdRegular,
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterDrawerTitlePreview() {
    SirioTheme {
        Column {
            SirioFilterDrawerTitle(
                title = "Section Title",
                text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
            )
        }
    }
}
//
// SirioFilterDrawerHeader.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterDrawerHeaderPaddingBottom
import it.inps.sirio.theme.filterDrawerHeaderPaddingHorizontal
import it.inps.sirio.theme.filterDrawerHeaderPaddingVertical
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource

/**
 * A header component for filter sections.
 *
 * @param title The title text to display in the header.
 * @param closeContentDescription The content description for the close icon, for accessibility purposes.
 * @param onClose The callback to be invoked when the close icon is clicked.
 */
@Composable
fun SirioFilterDrawerHeader(
    title: String,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(horizontal = filterDrawerHeaderPaddingHorizontal.dp)
            .padding(bottom = filterDrawerHeaderPaddingBottom.dp)
    ) {
        SirioButton(
            size = SirioButtonSize.Medium,
            hierarchy = SirioTheme.colors.filter.close,
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = filterDrawerHeaderPaddingVertical.dp),
            icon = SirioIconSource.FaIcon(FaIcons.Times),
            iconContentDescription = closeContentDescription,
            onClick = onClose
        )
        SirioText(
            text = title,
            color = SirioTheme.colors.filter.header,
            typography = SirioTheme.foundationTypography.headlineSmHeavy,
        )
    }
}

@Preview
@Composable
private fun SirioFilterDrawerHeaderPreview() {
    SirioTheme {
        Column {
            SirioFilterDrawerHeader(title = "Filtri", onClose = {})
        }
    }
}
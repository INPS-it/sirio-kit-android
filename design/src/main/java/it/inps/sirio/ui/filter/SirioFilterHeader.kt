//
// SirioFilterHeader.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterHeaderCloseSize
import it.inps.sirio.theme.filterHeaderPaddingHorizontal
import it.inps.sirio.theme.filterHeaderPaddingVertical
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioFaIcon

/**
 * A header component for filter sections.
 *
 * @param title The title text to display in the header.
 * @param closeContentDescription The content description for the close icon, for accessibility purposes.
 * @param onClose The callback to be invoked when the close icon is clicked.
 */
@Composable
fun SirioFilterHeader(
    title: String,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterHeaderPaddingHorizontal.dp,
                vertical = filterHeaderPaddingVertical.dp
            ),
        verticalArrangement = Arrangement.spacedBy(filterHeaderPaddingVertical.dp)
    ) {
        SirioFaIcon(
            faIcon = FaIcons.Times,
            modifier = Modifier
                .align(Alignment.End)
                .clickable(role = Role.Button, onClick = onClose),
            size = filterHeaderCloseSize.dp,
            tint = SirioTheme.colors.filter.close,
            contentDescription = closeContentDescription,
        )
        SirioText(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            color = SirioTheme.colors.filter.header,
            typography = SirioTheme.typography.filter.header,
        )
    }
}

@Preview
@Composable
private fun SirioFilterHeaderPreview() {
    SirioTheme {
        Column {
            SirioFilterHeader(title = "Filtri", onClose = {})
        }
    }
}
//
// SirioFilterTitle.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterInfoPadding
import it.inps.sirio.theme.filterInfoSize
import it.inps.sirio.theme.filterPadding
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * A title component for filter sections.
 *
 * @param text The text to display as the title.
 */
@Composable
fun SirioFilterTitle(
    text: String,
    onInfoClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(filterPadding.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SirioTextCommon(
            text = text,
            color = SirioTheme.colors.filter.title,
            typography = SirioTheme.typography.filter.title,
        )
        onInfoClick?.let {
            Spacer(modifier = Modifier.width(filterInfoPadding.dp))
            SirioIcon(
                faIcon = FaIcons.InfoCircle,
                iconColor = SirioTheme.colors.filter.info,
                size = filterInfoSize.dp,
                onclick = onInfoClick,
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterTitlePreview() {
    SirioTheme {
        Column {
            SirioFilterTitle(text = "Section Title #")
        }
    }
}
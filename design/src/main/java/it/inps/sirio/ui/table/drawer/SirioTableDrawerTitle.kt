//
// SirioTableDrawerTitle.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

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
import it.inps.sirio.theme.tableDrawerPaddingHorizontal
import it.inps.sirio.theme.tableDrawerPaddingVertical
import it.inps.sirio.theme.tableDrawerTitleLabelPaddingBottom
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioTableDrawerTitle(
    title: String,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.table.drawer.background)
            .padding(horizontal = tableDrawerPaddingHorizontal.dp)
            .padding(bottom = tableDrawerTitleLabelPaddingBottom.dp)
    ) {
        SirioButton(
            size = SirioButtonSize.Medium,
            hierarchy = SirioTheme.colors.table.drawer.close,
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = tableDrawerPaddingVertical.dp),
            icon = SirioIconSource.FaIcon(FaIcons.Times),
            iconContentDescription = closeContentDescription,
            onClick = onClose
        )
        SirioText(
            text = title,
            color = SirioTheme.colors.table.drawer.header,
            typography = SirioTheme.foundationTypography.headlineSmHeavy,
        )
    }
}

@Composable
internal fun SirioTableDrawerTitle(
    data: SirioTableDrawerType.Title,
) {
    SirioTableDrawerTitle(
        title = data.title,
        closeContentDescription = data.closeContentDescription,
        onClose = data.onClose,
    )
}

@Preview
@Composable
private fun SirioTableDrawerTitlePreview() {
    SirioTheme {
        SirioTableDrawerTitle(
            title = "Titolo Tabella",
            closeContentDescription = null,
            onClose = {},
        )
    }
}
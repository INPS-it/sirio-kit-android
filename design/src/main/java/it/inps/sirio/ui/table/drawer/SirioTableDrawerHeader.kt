//
// SirioTableDrawerHeader.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableDrawerPaddingTitle
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioTableDrawerHeader(
    title: String,
    closeContentDescription: String?,
    onClose: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.table.drawer.background)) {
        SirioButton(
            modifier = Modifier.align(Alignment.End),
            size = SirioButtonSize.Large,
            style = ButtonStyle.Ghost,
            icon = FaIcons.Times,
            iconContentDescription = closeContentDescription,
            onClick = onClose,
        )
        Spacer(modifier = Modifier.height(tableDrawerPaddingTitle.dp))
        SirioText(
            text = title,
            color = SirioTheme.colors.table.drawer.title,
            typography = SirioTheme.typography.table.drawer.title,
        )
    }
}

@Preview
@Composable
private fun SirioTableDrawerHeaderPreview() {
    SirioTheme {
        SirioTableDrawerHeader(title = "Titolo", closeContentDescription = null, onClose = {})
    }
}
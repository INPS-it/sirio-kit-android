//
// SirioTableDrawerLink.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioTableDrawerLink(
    title: String,
    text: String,
    onLinkClick: () -> Unit,
) {
    SirioTableDrawerCommon(
        title = title,
    ) {
        SirioText(
            text = text,
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onLinkClick),
            color = SirioTheme.colors.table.drawer.link,
            textDecoration = TextDecoration.Underline,
            typography = SirioTheme.foundationTypography.linkMdHeavy,
        )
    }
}

@Composable
internal fun SirioTableDrawerLink(
    data: SirioTableDrawerType.Link,
) {
    SirioTableDrawerLink(
        title = data.title,
        text = data.text,
        onLinkClick = data.onLinkClick,
    )
}

@Preview
@Composable
private fun SirioTableDrawerLinkPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableDrawerLink(
                title = "Header",
                text = "Link",
                onLinkClick = {},
            )
        }
    }
}
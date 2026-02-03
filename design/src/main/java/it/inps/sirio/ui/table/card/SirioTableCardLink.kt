//
// SirioTableCardLink.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.ifElse

@Composable
fun SirioTableCardLink(
    title: String,
    text: String,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    SirioTableCardCommon(
        title = title,
        themeMode = themeMode,
        showDivider = showDivider,
        content = linkContent(text = text, onClick = onClick)
    )
}

@Composable
private fun linkContent(
    text: String,
    onClick: (() -> Unit)?,
): @Composable (RowScope.() -> Unit)? = text.takeIf { it.isNotBlank() }?.let {
    {
        SirioText(
            text = text,
            modifier = Modifier
                .weight(1f)
                .ifElse(
                    condition = onClick != null,
                    ifTrueModifier = Modifier.clickable(onClick = onClick!!)
                ),
            color = SirioTheme.colors.table.card.link,
            textDecoration = TextDecoration.Underline,
            typography = SirioTheme.foundationTypography.linkMdHeavy,
        )
    }
}

@Composable
internal fun SirioTableCardLink(
    data: SirioTableCardType.Link,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardLink(
        title = data.title,
        text = data.text,
        themeMode = themeMode,
        showDivider = showDivider,
        onClick = data.onClick,
    )
}

@Preview
@Composable
private fun SirioTableCardLinkPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableCardLink(
                title = "Header",
                text = "Link",
            )
            SirioTableCardLink(
                title = "Header",
                text = "Link",
            )
            SirioTableCardLink(
                title = "",
                text = "Link",
            )
            SirioTableCardLink(
                title = "Header",
                text = "",
            )
        }
    }
}

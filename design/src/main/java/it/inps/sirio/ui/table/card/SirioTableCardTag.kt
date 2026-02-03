//
// SirioTableCardTag.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.tag.SirioTag
import it.inps.sirio.ui.tag.SirioTagSemantic

@Composable
fun SirioTableCardTag(
    title: String,
    text: String,
    semantic: SirioTagSemantic? = null,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardCommon(
        title = title,
        themeMode = themeMode,
        showDivider = showDivider,
        content = tagContent(text = text, semantic = semantic)
    )
}

@Composable
private fun tagContent(
    text: String,
    semantic: SirioTagSemantic? = null,
): @Composable (RowScope.() -> Unit)? = text.takeIf { it.isNotBlank() }?.let {
    {
        SirioTag(
            text = text,
            modifier = Modifier.weight(1f, false),
            color = SirioTheme.colors.table.card.tag,
            semantic = semantic,
        )
    }
}

@Composable
internal fun SirioTableCardTag(
    data: SirioTableCardType.Tag,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    showDivider: Boolean = true,
) {
    SirioTableCardTag(
        title = data.title,
        text = data.text,
        semantic = data.semantic,
        themeMode = themeMode,
        showDivider = showDivider,
    )
}

@Preview
@Composable
private fun SirioTableCardTagPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            SirioTableCardTag(
                title = "Header",
                text = "Label Tag",
            )
            SirioTableCardTag(
                title = "Header",
                text = "Label Tag",
            )
            SirioTableCardTag(
                title = "Header",
                text = "",
            )
            SirioTableCardTag(
                title = "",
                text = "Label Tag",
            )
        }
    }
}

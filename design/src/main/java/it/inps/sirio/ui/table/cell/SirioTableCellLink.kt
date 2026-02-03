//
// SirioTableCellLink.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.takeTwoWords

@Composable
fun RowScope.SirioTableCellLink(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    onLinkClick: () -> Unit,
) {
    SirioTableCellCommon(
        size = size,
        weight = weight,
        themeMode = themeMode,
    ) {
        SirioTextCommon(
            text = text,
            modifier = Modifier
                .clickable(onClick = onLinkClick)
                .testTag("tableLink${text.takeTwoWords()}"),
            color = SirioTheme.colors.table.cell.link,
            textDecoration = TextDecoration.Underline,
            typography = SirioTheme.foundationTypography.linkMdHeavy,
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellLink(
    data: SirioTableCellType.Link,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellLink(
        text = data.text,
        weight = weight,
        size = size,
        themeMode = themeMode,
        onLinkClick = data.onLinkClick,
    )
}

@Preview
@Composable
private fun SirioTableCellLinkPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link\nLink",
                    size = SirioTableContentSize.Large,
                    onLinkClick = {},
                )
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.Large,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.Large,
                    themeMode = SirioThemeMode.Light,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.Large,
                    themeMode = SirioThemeMode.Dark,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.Small,
                    themeMode = SirioThemeMode.Light,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.Small,
                    themeMode = SirioThemeMode.Dark,
                    onLinkClick = {},
                )
            }
        }
    }
}

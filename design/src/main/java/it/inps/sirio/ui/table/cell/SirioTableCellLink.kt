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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun RowScope.SirioTableCellLink(
    text: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
    onLinkClick: () -> Unit,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        SirioTextCommon(
            text = text,
            modifier = Modifier.clickable(onClick = onLinkClick),
            color = SirioTheme.colors.table.cell.link,
            typography = SirioTheme.typography.table.cell.link,
        )
    }
}

@Composable
internal fun RowScope.SirioTableCellLink(data: SirioTableCellType.Link, weight: Float = 1f) {
    SirioTableCellLink(
        text = data.text,
        weight = weight,
        size = data.size,
        scroll = data.scroll,
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
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    onLinkClick = {},
                )
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                    onLinkClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellLink(
                    text = "Link",
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                    onLinkClick = {},
                )
            }
        }
    }
}
//
// SirioTableCellLink.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon

@Composable
fun SirioTableCellLink(
    text: String,
    size: SirioTableContentSize,
    scroll: Boolean = false,
    onLinkClick: () -> Unit,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        SirioTextCommon(
            text = text,
            modifier = Modifier.clickable(onClick = onLinkClick),
            color = SirioTheme.colors.table.cell.link,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            typography = SirioTheme.typography.table.cell.link,
        )
    }
}

@Composable
internal fun SirioTableCellLink(data: SirioTableCellType.Link) {
    SirioTableCellLink(
        text = data.text,
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
            SirioTableCellLink(
                text = "Link",
                size = SirioTableContentSize.LARGE,
                scroll = false,
                onLinkClick = {},
            )
            SirioTableCellLink(
                text = "Link",
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
                onLinkClick = {},
            )
            SirioTableCellLink(
                text = "Link",
                size = SirioTableContentSize.SMALL,
                scroll = false,
                onLinkClick = {},
            )
            SirioTableCellLink(
                text = "Link",
                size = SirioTableContentSize.LARGE,
                scroll = true,
                onLinkClick = {},
            )
            SirioTableCellLink(
                text = "Link",
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
                onLinkClick = {},
            )
            SirioTableCellLink(
                text = "Link",
                size = SirioTableContentSize.SMALL,
                scroll = true,
                onLinkClick = {},
            )
        }
    }
}
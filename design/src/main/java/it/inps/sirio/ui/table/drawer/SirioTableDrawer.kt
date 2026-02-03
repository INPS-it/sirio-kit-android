//
// SirioTableDrawer.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioTableDrawer(
    title: String,
    closeContentDescription: String? = null,
    data: List<SirioTableDrawerType>,
    icons: List<SirioTableActionData> = emptyList(),
    onClose: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(SirioTheme.colors.table.drawer.background)
    ) {
        SirioTableDrawerTitle(
            title = title,
            closeContentDescription = closeContentDescription,
            onClose = onClose,
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(data) {
                DrawerTypeToCell(it)
            }
        }
        if (icons.isNotEmpty()) {
            SirioTableDrawerStickyBottomBar(icons)
        }
    }
}

@Composable
private fun DrawerTypeToCell(
    item: SirioTableDrawerType,
) {
    when (item) {
        is SirioTableDrawerType.Link -> SirioTableDrawerLink(item)
        is SirioTableDrawerType.Number -> SirioTableDrawerNumber(item)
        is SirioTableDrawerType.Tag -> SirioTableDrawerTag(item)
        is SirioTableDrawerType.Text -> SirioTableDrawerText(item)
        is SirioTableDrawerType.Title -> SirioTableDrawerTitle(item)
    }
}

@Preview
@Composable
private fun SirioTableDrawerPreview() {
    SirioTheme {
        SirioTableDrawer(
            title = "Titolo Tabella",
            onClose = {},
            data = listOf(
                SirioTableDrawerType.Link(
                    title = "Header",
                    text = "Link",
                    onLinkClick = {},
                ),
                SirioTableDrawerType.Number(
                    title = "Header",
                    text = "00",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header molto lungo su due righe",
                    text = "Lorem ipsum sagittis egestas at proin",
                ),
                SirioTableDrawerType.Tag(
                    title = "Header",
                    text = "Label Tag",
                ),
            ),
            icons = listOf(
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Print), action = {}),
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Download), action = {}),
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
            )
        )
    }
}

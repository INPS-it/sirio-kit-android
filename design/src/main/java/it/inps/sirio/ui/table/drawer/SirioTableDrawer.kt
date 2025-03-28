//
// SirioTableDrawer.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableDrawerPaddingHorizontal
import it.inps.sirio.theme.tableDrawerPaddingTitle
import it.inps.sirio.theme.tableDrawerPaddingTop
import it.inps.sirio.ui.table.SirioTableIconData

@Composable
fun SirioTableDrawer(
    title: String,
    closeContentDescription: String? = null,
    data: List<SirioTableDrawerItemData>,
    icons: List<SirioTableIconData> = emptyList(),
    onClose: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(SirioTheme.colors.table.drawer.background)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = tableDrawerPaddingHorizontal.dp)
                .padding(top = tableDrawerPaddingTop.dp)
        ) {
            SirioTableDrawerHeader(
                title = title,
                closeContentDescription = closeContentDescription,
                onClose = onClose,
            )
            Spacer(modifier = Modifier.height(tableDrawerPaddingTitle.dp))
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(data) {
                    SirioTableDrawerItem(data = it)
                }
            }
        }
        if (icons.isNotEmpty()) {
            SirioTableDrawerStickyBottomBar(icons)
        }
    }
}

@Keep
data class SirioTableDrawerColors(
    val actionsText: Color,
    val background: Color,
    val border: Color,
    val iconsBackground: Color,
    val title: Color,
    val itemTitle: Color,
    val itemText: Color,
    val itemNumber: Color,
    val itemLink: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableDrawerColors(
            actionsText = Color.Unspecified,
            background = Color.Unspecified,
            border = Color.Unspecified,
            iconsBackground = Color.Unspecified,
            title = Color.Unspecified,
            itemTitle = Color.Unspecified,
            itemText = Color.Unspecified,
            itemNumber = Color.Unspecified,
            itemLink = Color.Unspecified,
        )
    }
}

@Keep
data class SirioTableDrawerTypography(
    val actionsText: TextStyle,
    val title: TextStyle,
    val itemTitle: TextStyle,
    val itemText: TextStyle,
    val itemNumber: TextStyle,
    val itemLink: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioTableDrawerTypography(
            actionsText = TextStyle.Default,
            title = TextStyle.Default,
            itemTitle = TextStyle.Default,
            itemText = TextStyle.Default,
            itemNumber = TextStyle.Default,
            itemLink = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioTableDrawerPreview() {
    SirioTheme {
        SirioTableDrawer(
            title = "Titolo 1",
            onClose = {},
            data = listOf(
                SirioTableDrawerItemData(
                    title = "Titolo colonna di dettaglio",
                    text = "Suspendisse purus lectus, accumsan vitae malesuada lobortis, maximus vel enim. Proin nulla augue, ultricies quis mattis at, fringilla id sem.",
                    type = SirioTableDrawerItemType.TEXT,
                    onClick = {}
                ),
                SirioTableDrawerItemData(
                    title = "Titolo colonna di dettaglio",
                    text = "11/03/2023",
                    type = SirioTableDrawerItemType.NUMBER,
                    onClick = {}
                ),
                SirioTableDrawerItemData(
                    title = "Titolo colonna di dettaglio",
                    text = "Lorem Ipsum dolor sit amet",
                    type = SirioTableDrawerItemType.LINK,
                    onClick = {}
                )
            ),
            icons = listOf(
                SirioTableIconData(FaIcons.Print, action = {}),
                SirioTableIconData(FaIcons.Download, action = {}),
                SirioTableIconData(FaIcons.Trash, action = {}),
                SirioTableIconData(FaIcons.Trash, action = {}),
            )
        )
    }
}
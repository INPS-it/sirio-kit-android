//
// SirioTableDrawerItem.kt
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
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableDrawerItemBorderWidth
import it.inps.sirio.theme.tableDrawerItemPaddingInternal
import it.inps.sirio.theme.tableDrawerItemPaddingVertical
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border

@Composable
internal fun SirioTableDrawerItem(data: SirioTableDrawerItemData) {
    val drawerTypography = SirioTheme.typography.table.drawer
    val drawerColor = SirioTheme.colors.table.drawer
    val (typography, color) = remember(data.type) {
        when (data.type) {
            SirioTableDrawerItemType.TEXT -> drawerTypography.itemText to drawerColor.itemText
            SirioTableDrawerItemType.NUMBER -> drawerTypography.itemNumber to drawerColor.itemNumber
            SirioTableDrawerItemType.LINK -> drawerTypography.itemLink to drawerColor.itemLink
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(bottom = Border(tableDrawerItemBorderWidth.dp, drawerColor.border))
            .padding(vertical = tableDrawerItemPaddingVertical.dp)
    ) {
        SirioText(
            text = data.title,
            typography = drawerTypography.itemTitle,
            color = drawerColor.itemTitle,
        )
        Spacer(modifier = Modifier.height(tableDrawerItemPaddingInternal.dp))
        SirioText(
            text = data.text,
            typography = typography,
            color = color,
        )
    }
}

enum class SirioTableDrawerItemType {
    TEXT, NUMBER, LINK,
}

@Preview
@Composable
private fun SirioTableDrawerItemPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            SirioTableDrawerItem(
                SirioTableDrawerItemData(
                    title = "Titolo colonna di dettaglio",
                    text = "Suspendisse purus lectus, accumsan vitae malesuada lobortis, maximus vel enim. Proin nulla augue, ultricies quis mattis at, fringilla id sem.",
                    type = SirioTableDrawerItemType.TEXT,
                    onClick = {}
                )
            )
            SirioTableDrawerItem(
                SirioTableDrawerItemData(
                    title = "Titolo colonna di dettaglio",
                    text = "11/03/2023",
                    type = SirioTableDrawerItemType.NUMBER,
                    onClick = {}
                )
            )
            SirioTableDrawerItem(
                SirioTableDrawerItemData(
                    title = "Titolo colonna di dettaglio",
                    text = "Lorem Ipsum dolor sit amet",
                    type = SirioTableDrawerItemType.LINK,
                    onClick = {}
                )
            )
        }
    }
}
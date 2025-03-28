//
// SirioTableDrawerItem.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.vertical

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableVerticalItemPadding
import it.inps.sirio.theme.tableVerticalItemTagPadding
import it.inps.sirio.ui.tag.SirioTag
import it.inps.sirio.ui.tag.TagType
import it.inps.sirio.ui.text.SirioText

@Composable
internal fun SirioTableVerticalCellItem(data: SirioTableVerticalCellItemData) {
    val verticalTypography = SirioTheme.typography.table.vertical
    val verticalColor = SirioTheme.colors.table.vertical
    val (typography, color) = remember(data.type) {
        when (data.type) {
            SirioTableVerticalCellItemType.TEXT -> verticalTypography.itemText to verticalColor.itemText
            SirioTableVerticalCellItemType.NUMBER -> verticalTypography.itemNumber to verticalColor.itemNumber
            SirioTableVerticalCellItemType.LINK -> verticalTypography.itemLink to verticalColor.itemLink
            SirioTableVerticalCellItemType.TAG -> null to null
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(tableVerticalItemPadding.dp)
    ) {
        SirioText(
            text = data.title,
            typography = verticalTypography.itemTitle,
            color = verticalColor.itemTitle,
        )
        if (typography != null && color != null) {
            SirioText(
                text = data.text,
                typography = typography,
                color = color,
            )
        } else {
            Spacer(modifier = Modifier.height(tableVerticalItemTagPadding.dp))
            SirioTag(text = data.text, tagType = TagType.GRAY)
        }
    }
}

enum class SirioTableVerticalCellItemType {
    TEXT, NUMBER, LINK, TAG,
}

@Keep
data class SirioTableVerticalColors(
    val background: Color,
    val border: Color,
    val itemTitle: Color,
    val itemText: Color,
    val itemNumber: Color,
    val itemLink: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableVerticalColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            itemTitle = Color.Unspecified,
            itemText = Color.Unspecified,
            itemNumber = Color.Unspecified,
            itemLink = Color.Unspecified,
        )
    }
}

@Keep
data class SirioTableVerticalTypography(
    val itemTitle: TextStyle,
    val itemText: TextStyle,
    val itemNumber: TextStyle,
    val itemLink: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioTableVerticalTypography(
            itemTitle = TextStyle.Default,
            itemText = TextStyle.Default,
            itemNumber = TextStyle.Default,
            itemLink = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioTableVerticalCellItemPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            SirioTableVerticalCellItem(
                SirioTableVerticalCellItemData(
                    title = "Header",
                    text = "Lorem Ipsum",
                    type = SirioTableVerticalCellItemType.TEXT,
                )
            )
            SirioTableVerticalCellItem(
                SirioTableVerticalCellItemData(
                    title = "Header",
                    text = "00",
                    type = SirioTableVerticalCellItemType.NUMBER,
                )
            )
            SirioTableVerticalCellItem(
                SirioTableVerticalCellItemData(
                    title = "Header",
                    text = "Link",
                    type = SirioTableVerticalCellItemType.LINK,
                )
            )
            SirioTableVerticalCellItem(
                SirioTableVerticalCellItemData(
                    title = "Header",
                    text = "Label Tag",
                    type = SirioTableVerticalCellItemType.TAG,
                )
            )
        }
    }
}
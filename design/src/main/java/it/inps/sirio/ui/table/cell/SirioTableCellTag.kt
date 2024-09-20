//
// SirioTableCellTag.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.tag.SirioTag
import it.inps.sirio.ui.tag.TagType

@Composable
fun SirioTableCellTag(
    text: String,
    tagType: TagType,
    size: SirioTableContentSize,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        SirioTag(text = text, tagType = tagType)
    }
}

@Composable
internal fun SirioTableCellTag(data: SirioTableCellType.Tag) {
    SirioTableCellTag(
        text = data.text,
        tagType = data.tagType,
        size = data.size,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellTagPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            val text = "Label Tag"
            SirioTableCellTag(
                text = text,
                tagType = TagType.GRAY,
                size = SirioTableContentSize.LARGE,
                scroll = false,
            )
            SirioTableCellTag(
                text = text,
                tagType = TagType.GRAY,
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
            )
            SirioTableCellTag(
                text = text,
                tagType = TagType.GRAY,
                size = SirioTableContentSize.SMALL,
                scroll = false,
            )
            SirioTableCellTag(
                text = text,
                tagType = TagType.GRAY,
                size = SirioTableContentSize.LARGE,
                scroll = true,
            )
            SirioTableCellTag(
                text = text,
                tagType = TagType.GRAY,
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
            )
            SirioTableCellTag(
                text = text,
                tagType = TagType.GRAY,
                size = SirioTableContentSize.SMALL,
                scroll = true,
            )
        }
    }
}
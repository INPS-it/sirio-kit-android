//
// SirioTableCellTag.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

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
import it.inps.sirio.ui.tag.SirioTag
import it.inps.sirio.ui.tag.SirioTagType

@Composable
fun RowScope.SirioTableCellTag(
    text: String,
    tagType: SirioTagType,
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        SirioTag(text = text, tagType = tagType)
    }
}

@Composable
internal fun RowScope.SirioTableCellTag(data: SirioTableCellType.Tag, weight: Float = 1f) {
    SirioTableCellTag(
        text = data.text,
        tagType = data.tagType,
        size = data.size,
        weight = weight,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellTagPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            val text = "Label Tag"
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    tagType = SirioTagType.GRAY,
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    tagType = SirioTagType.GRAY,
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    tagType = SirioTagType.GRAY,
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    tagType = SirioTagType.GRAY,
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    tagType = SirioTagType.GRAY,
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellTag(
                    text = text,
                    tagType = SirioTagType.GRAY,
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                )
            }
        }
    }
}

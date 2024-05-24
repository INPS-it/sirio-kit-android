//
// SirioTag.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tag

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio tag component
 *
 * @param text The tag label
 * @param tagType The tag color type [TagType]
 * @param modifier A [Modifier] for customizing the appearance and behavior of the tag component
 */
@Composable
fun SirioTag(
    text: String,
    tagType: TagType,
    modifier: Modifier = Modifier,
) {
    val sirioTagColors = when (tagType) {
        TagType.GRAY -> SirioTheme.colors.tag.gray
        TagType.BLUE -> SirioTheme.colors.tag.blue
        TagType.RED -> SirioTheme.colors.tag.red
        TagType.ORANGE -> SirioTheme.colors.tag.orange
        TagType.GREEN -> SirioTheme.colors.tag.green
        TagType.WHITE -> SirioTheme.colors.tag.white
    }
    SirioTagCommon(
        text = text,
        colors = sirioTagColors,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun SmallTagPreview() {
    SirioTheme {
        Column {
            SirioTag(text = "Label Tag", tagType = TagType.GRAY)
            SirioTag(text = "Label Tag", tagType = TagType.BLUE)
            SirioTag(text = "Label Tag", tagType = TagType.RED)
            SirioTag(text = "Label Tag", tagType = TagType.ORANGE)
            SirioTag(text = "Label Tag", tagType = TagType.GREEN)
            SirioTag(text = "Label Tag", tagType = TagType.WHITE)
        }
    }
}
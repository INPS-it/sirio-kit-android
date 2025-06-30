//
// SirioTag.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
 * @param tagType The tag color type [SirioTagType]
 * @param modifier A [Modifier] for customizing the appearance and behavior of the tag component
 */
@Composable
fun SirioTag(
    text: String,
    tagType: SirioTagType,
    modifier: Modifier = Modifier,
) {
    val sirioTagColors = when (tagType) {
        SirioTagType.GRAY -> SirioTheme.colors.tag.gray
        SirioTagType.BLUE -> SirioTheme.colors.tag.blue
        SirioTagType.RED -> SirioTheme.colors.tag.red
        SirioTagType.ORANGE -> SirioTheme.colors.tag.orange
        SirioTagType.GREEN -> SirioTheme.colors.tag.green
        SirioTagType.WHITE -> SirioTheme.colors.tag.white
    }
    SirioTagCommon(
        text = text,
        colors = sirioTagColors,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun TagPreview() {
    SirioTheme {
        Column {
            SirioTag(text = "Label Tag", tagType = SirioTagType.GRAY)
            SirioTag(text = "Label Tag", tagType = SirioTagType.BLUE)
            SirioTag(text = "Label Tag", tagType = SirioTagType.RED)
            SirioTag(text = "Label Tag", tagType = SirioTagType.ORANGE)
            SirioTag(text = "Label Tag", tagType = SirioTagType.GREEN)
            SirioTag(text = "Label Tag", tagType = SirioTagType.WHITE)
        }
    }
}

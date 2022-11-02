//
// SmallTag.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tag

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio tag component
 *
 * @param text The tag label
 * @param tagType The tag color type [TagType]
 */
@Composable
fun SmallTag(text: String, tagType: TagType) {
    val (backgroundColor, textColor) = when (tagType) {
        TagType.GRAY -> Pair(SirioTheme.colors.tagGrayBackground, SirioTheme.colors.tagGrayText)
        TagType.BLUE -> Pair(SirioTheme.colors.tagBlueBackground, SirioTheme.colors.tagBlueText)
        TagType.RED -> Pair(SirioTheme.colors.tagRedBackground, SirioTheme.colors.tagRedText)
        TagType.ORANGE -> Pair(
            SirioTheme.colors.tagOrangeBackground,
            SirioTheme.colors.tagOrangeText
        )
        TagType.GREEN -> Pair(SirioTheme.colors.tagGreenBackground, SirioTheme.colors.tagRedText)
        TagType.WHITE -> Pair(SirioTheme.colors.tagWhiteBackground, SirioTheme.colors.tagWhiteText)
    }
    TagCommon(text = text, backgroundColor = backgroundColor, textColor = textColor)
}

@Preview
@Composable
private fun SmallTagPreview() {
    SirioTheme {
        Column {
            SmallTag("Label Tag", TagType.GRAY)
            SmallTag("Label Tag", TagType.BLUE)
            SmallTag("Label Tag", TagType.RED)
            SmallTag("Label Tag", TagType.ORANGE)
            SmallTag("Label Tag", TagType.GREEN)
            SmallTag("Label Tag", TagType.WHITE)
        }
    }
}
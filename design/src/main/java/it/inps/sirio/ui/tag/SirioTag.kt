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
 * @param text The tag label.
 * @param modifier A [Modifier] for customizing the appearance and behavior of the tag component
 * @param color The specific color for the tag. It's an instance of [SirioTagColor]. Default is [SirioTagColor.Light].
 * @param semantic The semantic purpose of the tag, which also determines its color. It's an instance of [SirioTagSemantic]. Default is null.
 */
@Composable
fun SirioTag(
    text: String,
    modifier: Modifier = Modifier,
    color: SirioTagColor? = SirioTagColor.Light,
    semantic: SirioTagSemantic? = null,
) {
    val tagType = when (semantic) {
        SirioTagSemantic.Info -> SirioTagType.Blue
        SirioTagSemantic.Alert -> SirioTagType.Red
        SirioTagSemantic.Warning -> SirioTagType.Orange
        SirioTagSemantic.Success -> SirioTagType.Green
        null -> when (color) {
            SirioTagColor.Dark -> SirioTagType.White
            SirioTagColor.Light -> SirioTagType.Gray
            else -> SirioTagType.Gray
        }
    }
    val sirioTagColors = when (tagType) {
        SirioTagType.Gray -> SirioTheme.colors.tag.gray
        SirioTagType.Blue -> SirioTheme.colors.tag.blue
        SirioTagType.Red -> SirioTheme.colors.tag.red
        SirioTagType.Orange -> SirioTheme.colors.tag.orange
        SirioTagType.Green -> SirioTheme.colors.tag.green
        SirioTagType.White -> SirioTheme.colors.tag.white
    }
    SirioTagCommon(
        text = text,
        colors = sirioTagColors,
        modifier = modifier,
    )
}

enum class SirioTagColor {
    Dark,
    Light,
}

enum class SirioTagSemantic {
    Info,
    Alert,
    Warning,
    Success,
}

@Preview
@Composable
private fun TagPreview() {
    SirioTheme {
        Column {
            SirioTag(text = "Label Tag", color = SirioTagColor.Light)
            SirioTag(text = "Label Tag", color = SirioTagColor.Dark)
            SirioTag(text = "Label Tag", semantic = SirioTagSemantic.Info)
            SirioTag(text = "Label Tag", semantic = SirioTagSemantic.Alert)
            SirioTag(text = "Label Tag", semantic = SirioTagSemantic.Warning)
            SirioTag(text = "Label Tag", semantic = SirioTagSemantic.Success)
        }
    }
}

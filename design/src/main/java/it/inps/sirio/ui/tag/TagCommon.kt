//
// TagCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tag

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tagElevation
import it.inps.sirio.theme.tagHorizontalPadding
import it.inps.sirio.theme.tagVerticalPadding
import it.inps.sirio.ui.text.SirioTextCommon

/**
 * Sirio tag implementation
 *
 * @param text The tag label
 * @param colors The tag colors
 * @param modifier A [Modifier] for customizing the appearance and behavior of the tag component
 */
@Composable
internal fun TagCommon(
    text: String,
    colors: SirioTagColors,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        elevation = tagElevation,
        shape = CircleShape,
        color = colors.background
    ) {
        Row(
            modifier = Modifier.padding(tagHorizontalPadding, tagVerticalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SirioTextCommon(
                text = text,
                color = colors.text,
                typography = SirioTheme.typography.tagText,
            )
        }
    }
}

@Keep
data class SirioTagsColors(
    var gray: SirioTagColors,
    var blue: SirioTagColors,
    var red: SirioTagColors,
    var orange: SirioTagColors,
    var green: SirioTagColors,
    var white: SirioTagColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioTagsColors(
            gray = SirioTagColors.Unspecified,
            blue = SirioTagColors.Unspecified,
            red = SirioTagColors.Unspecified,
            orange = SirioTagColors.Unspecified,
            green = SirioTagColors.Unspecified,
            white = SirioTagColors.Unspecified,
        )
    }
}

@Keep
data class SirioTagColors(
    var background: Color,
    var text: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTagColors(
            background = Color.Unspecified,
            text = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun TagCommonPreview() {
    SirioTheme {
        TagCommon("Label Tag", SirioTheme.colors.tag.red)
    }
}

enum class TagType {
    GRAY,
    BLUE,
    RED,
    ORANGE,
    GREEN,
    WHITE,
}
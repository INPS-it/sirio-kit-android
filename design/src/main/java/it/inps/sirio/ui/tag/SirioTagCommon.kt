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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tagElevation
import it.inps.sirio.theme.tagHeight
import it.inps.sirio.theme.tagPaddingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon

/**
 * Sirio tag implementation
 *
 * @param text The tag label
 * @param colors The tag colors
 * @param modifier A [Modifier] for customizing the appearance and behavior of the tag component
 */
@Composable
internal fun SirioTagCommon(
    text: String,
    colors: SirioTagColors,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = colors.background,
        shadowElevation = tagElevation.dp,
    ) {
        Row(
            modifier = Modifier
                .height(tagHeight.dp)
                .padding(horizontal = tagPaddingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SirioTextCommon(
                text = text,
                color = colors.text,
                overflow = TextOverflow.Ellipsis,
                typography = SirioTheme.typography.tagText,
            )
        }
    }
}

@Keep
data class SirioTagsColors(
    val gray: SirioTagColors,
    val blue: SirioTagColors,
    val red: SirioTagColors,
    val orange: SirioTagColors,
    val green: SirioTagColors,
    val white: SirioTagColors,
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
    val background: Color,
    val text: Color,
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
        SirioTagCommon("Label Tag", SirioTheme.colors.tag.red)
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
//
// TagCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tag

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
 * @param backgroundColor The tag background color
 * @param textColor The tag label color
 */
@Composable
internal fun TagCommon(text: String, backgroundColor: Color, textColor: Color) {
    Surface(
        elevation = tagElevation,
        shape = CircleShape,
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(tagHorizontalPadding, tagVerticalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SirioTextCommon(
                text = text,
                color = textColor,
                typography = SirioTheme.typography.tagText,
            )
        }
    }
}

@Preview
@Composable
private fun TagCommonPreview() {
    SirioTheme {
        TagCommon("Label Tag", Color.Red, Color.White)
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

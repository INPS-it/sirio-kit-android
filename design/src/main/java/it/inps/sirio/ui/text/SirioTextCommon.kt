//
// SirioTextCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * SirioTextCommon is a composable function that represents a text component using the typography provided by Sirio
 *
 * @param text A string representing the text to display
 * @param modifier A [Modifier] for customizing the appearance and behavior of the text component
 * @param color A [Color] for specifying the color of the text. Defaults to [Color.Unspecified]
 * @param maxLines An integer value indicating the maximum number of lines to display. Defaults to [Int.MAX_VALUE]
 * @param overflow A [TextOverflow] value indicating how to handle overflow text. Defaults to [TextOverflow.Clip]
 * @param typography A [TextStyle] value for specifying the typography of the text.
 */
@Composable
internal fun SirioTextCommon(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    typography: TextStyle,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        style = typography,
    )
}

@Preview
@Composable
fun SirioTextCommonPreview() {
    SirioTheme {
        SirioTextCommon(text = "Testo", typography = SirioTheme.typography.textFieldText)
    }
}
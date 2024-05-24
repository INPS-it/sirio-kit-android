//
// SirioTextCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.text

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.labelMd400

/**
 * SirioTextCommon is a composable function that represents a text component using the typography provided by Sirio
 *
 * @param text A string representing the text to display
 * @param modifier A [Modifier] for customizing the appearance and behavior of the text component
 * @param color A [Color] for specifying the color of the text. Defaults to [Color.Unspecified]
 * @param textDecoration the [TextStyle.textDecoration] to paint on the text (e.g., an underline).
 * @param textAlign the alignment of the text within the lines of the paragraph. See [TextStyle.textAlign].
 * @param overflow A [TextOverflow] value indicating how to handle overflow text. Defaults to [TextOverflow.Clip]
 * @param maxLines An integer value indicating the maximum number of lines to display. Defaults to [Int.MAX_VALUE]
 * @param typography A [TextStyle] value for specifying the typography of the text.
 */
@Composable
internal fun SirioTextCommon(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    typography: TextStyle = labelMd400,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
        style = typography,
    )
}

/**
 * SirioTextCommon is a composable function that represents a text component using the typography provided by Sirio
 *
 * @param text A string representing the text to display
 * @param modifier A [Modifier] for customizing the appearance and behavior of the text component
 * @param color A [Color] for specifying the color of the text. Defaults to [Color.Unspecified]
 * @param textDecoration the [TextStyle.textDecoration] to paint on the text (e.g., an underline).
 * @param textAlign the alignment of the text within the lines of the paragraph. See [TextStyle.textAlign].
 * @param overflow A [TextOverflow] value indicating how to handle overflow text. Defaults to [TextOverflow.Clip]
 * @param maxLines An integer value indicating the maximum number of lines to display. Defaults to [Int.MAX_VALUE]
 * @param typography A [TextStyle] value for specifying the typography of the text.
 */
@Composable
internal fun SirioTextCommon(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    typography: TextStyle = labelMd400,
) {
    Text(
        text = text,
        modifier = modifier.offset(y = (-2).dp),
        color = color,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
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

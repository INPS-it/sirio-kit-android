//
// TagCommon.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tag

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseColors
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tagPaddingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon

/**
 * Sirio tag implementation
 *
 * @param text The tag label
 * @param colors The tag colors
 * @param modifier A [Modifier] for customizing the appearance and behavior of the tag component
 * @param contentPadding The inner padding around the tag label
 * @param shape The shape used by the tag container
 * @param typography The text style used by the tag label
 */
@Composable
internal fun SirioTagCommon(
    text: String,
    colors: SirioBaseColors,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = tagPaddingHorizontal.dp),
    shape: Shape = CircleShape,
    typography: TextStyle = SirioTheme.foundationTypography.labelMdHeavy,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = colors.container,
    ) {
        Row(
            modifier = Modifier
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioTextCommon(
                text = text,
                color = colors.content,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                typography = typography,
            )
        }
    }
}

@Keep
data class SirioTagsColors(
    val gray: SirioBaseColors,
    val blue: SirioBaseColors,
    val red: SirioBaseColors,
    val orange: SirioBaseColors,
    val green: SirioBaseColors,
    val white: SirioBaseColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioTagsColors(
            gray = SirioBaseColors.Unspecified,
            blue = SirioBaseColors.Unspecified,
            red = SirioBaseColors.Unspecified,
            orange = SirioBaseColors.Unspecified,
            green = SirioBaseColors.Unspecified,
            white = SirioBaseColors.Unspecified,
        )
    }
}


internal val tagLightColors = SirioTagsColors(
    gray = SirioBaseColors(
        container = FoundationColor.colorAliasInteractiveSecondaryDefault,
        content = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    blue = SirioBaseColors(
        container = FoundationColor.colorAliasInteractivePrimaryDefault,
        content = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    red = SirioBaseColors(
        container = FoundationColor.colorAliasInteractiveAlertDefault,
        content = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    orange = SirioBaseColors(
        container = FoundationColor.colorGlobalSemanticWarning80,
        content = FoundationColor.colorAliasTextColorSecondaryDark130,
    ),
    green = SirioBaseColors(
        container = FoundationColor.colorGlobalSemanticSuccess100,
        content = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    white = SirioBaseColors(
        container = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        content = FoundationColor.colorAliasInteractiveSecondaryDefault,
    ),
)

internal
val tagDarkColors = tagLightColors

@Preview
@Composable
private fun TagCommonPreview() {
    SirioTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SirioTagCommon("Label Tag", SirioTheme.colors.tag.red)
            SirioTagCommon(
                text = "Sono un giovane",
                colors = SirioTheme.colors.tag.gray,
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 4.dp,
                ),
            )
        }
    }
}

internal enum class SirioTagType {
    Gray,
    Blue,
    Red,
    Orange,
    Green,
    White,
}

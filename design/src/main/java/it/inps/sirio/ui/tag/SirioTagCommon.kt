//
// TagCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tag

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
 */
@Composable
internal fun SirioTagCommon(
    text: String,
    colors: SirioBaseColors,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = colors.container,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = tagPaddingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SirioTextCommon(
                text = text,
                color = colors.content,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                typography = SirioTheme.foundationTypography.labelMdHeavy,
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
        SirioTagCommon("Label Tag", SirioTheme.colors.tag.red)
    }
}

enum class SirioTagType {
    GRAY,
    BLUE,
    RED,
    ORANGE,
    GREEN,
    WHITE,
}

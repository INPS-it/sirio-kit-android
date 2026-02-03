//
// SirioTableCardCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableCardItemPaddingVertical
import it.inps.sirio.theme.tableCardItemSpacing
import it.inps.sirio.theme.tableComponentBorderWidth
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.ui.tag.SirioTagColor
import it.inps.sirio.ui.tag.SirioTagSemantic
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border
import it.inps.sirio.utils.ifElse

/**
 * A common component for table card items
 *
 * @param title The string on the left side of the row
 * @param themeMode The theme mode: light, dark, or auto
 * @param showDivider whether a bottom line should be displayed
 * @param content The composable on the right side of the row
 */
@Composable
internal fun SirioTableCardCommon(
    title: String?,
    themeMode: SirioThemeMode? = null,
    showDivider: Boolean = true,
    content: @Composable (RowScope.() -> Unit)?,
) {
    SirioTheme(themeMode) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SirioTheme.colors.table.card.background)
                .ifElse(
                    condition = showDivider,
                    ifTrueModifier = Modifier.border(
                        bottom = Border(
                            strokeWidth = tableComponentBorderWidth.dp,
                            color = SirioTheme.colors.table.card.border,
                        )
                    )
                )
                .padding(vertical = tableCardItemPaddingVertical.dp),
        ) {
            title?.takeIf { it.isNotBlank() }?.let {
                SirioText(
                    text = it,
                    modifier = Modifier.weight(1f),
                    typography = SirioTheme.foundationTypography.labelMdHeavy,
                    color = SirioTheme.colors.table.card.title,
                )
                if (content != null) {
                    Spacer(modifier = Modifier.width(tableCardItemSpacing.dp))
                }
            }
            content?.let { it() }
        }
    }
}

/**
 * The different types of data that can be displayed in a table card row.
 */
sealed class SirioTableCardType {
    /**
     * A row with a title and a simple text.
     * @param title The title of the row.
     * @param text The text content.
     */
    data class Text(
        val title: String,
        val text: String,
    ) : SirioTableCardType()

    /**
     * A row with a title and a number.
     * @param title The title of the row.
     * @param text The number to be displayed as text.
     */
    data class Number(
        val title: String,
        val text: String,
    ) : SirioTableCardType()

    /**
     * A row with a title and a link.
     * @param title The title of the row.
     * @param text The text of the link.
     * @param onClick The callback to be invoked when the link is clicked.
     */
    data class Link(
        val title: String,
        val text: String,
        val onClick: (() -> Unit)? = null,
    ) : SirioTableCardType()

    /**
     * A row with a title and a tag.
     * @param title The title of the row.
     * @param text The text of the tag.
     * @param semantic The semantic of the tag.
     */
    data class Tag(
        val title: String,
        val text: String,
        val semantic: SirioTagSemantic? = null,
    ) : SirioTableCardType()

    /**
     * A row with a title "Azioni" and multiple icons.
     * Used to represent a set of actions that can be performed on the item.
     * @param actions The list of actions, each represented by [SirioTableActionData].
     */
    data class MultiIcons(
        val actions: List<SirioTableActionData>,
    ) : SirioTableCardType()
}

@Keep
data class SirioTableCardColors(
    val background: Color,
    val border: Color,
    val title: Color,
    val text: Color,
    val link: Color,
    val number: Color,
    val tag: SirioTagColor,
) {
    companion object Companion {
        @Stable
        val Unspecified = SirioTableCardColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            number = Color.Unspecified,
            link = Color.Unspecified,
            tag = SirioTagColor.Light,
        )
    }

}

val tableCardLightColors = SirioTableCardColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    border = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    number = FoundationColor.colorAliasTextColorSecondaryDark100,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
    tag = SirioTagColor.Light,
)

val tableCardDarkColors = SirioTableCardColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    border = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    number = FoundationColor.colorAliasTextColorSecondaryDark100,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
    tag = SirioTagColor.Light,
)

@Preview
@Composable
private fun SirioTableCardCommonPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCardCommon("Header") {
                    SirioTextCommon(text = "Test", Modifier.weight(1f))
                }
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCardCommon("Header", SirioThemeMode.Dark) {
                    SirioTextCommon(text = "Test")
                }
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCardCommon("Header", SirioThemeMode.Dark, content = null)
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCardCommon("") {
                    SirioTextCommon(text = "Test", Modifier.weight(1f))
                }
            }
        }
    }
}

//
// SirioTableDrawerCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.annotation.Keep
import androidx.compose.foundation.background
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
import it.inps.sirio.theme.tableComponentBorderWidth
import it.inps.sirio.theme.tableDrawerItemSpacing
import it.inps.sirio.theme.tableDrawerPaddingHorizontal
import it.inps.sirio.theme.tableDrawerPaddingVertical
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.tag.SirioTagColor
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.border

@Composable
internal fun SirioTableDrawerCommon(
    title: String,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    content: @Composable RowScope.() -> Unit,
) {
    SirioTheme(themeMode) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SirioTheme.colors.table.drawer.background)
                .padding(horizontal = tableDrawerPaddingHorizontal.dp)
                .border(
                    bottom = Border(
                        strokeWidth = tableComponentBorderWidth.dp,
                        color = SirioTheme.colors.table.drawer.border,
                    )
                )
                .padding(vertical = tableDrawerPaddingVertical.dp),
        ) {
            SirioText(
                text = title,
                modifier = Modifier.weight(1f),
                typography = SirioTheme.foundationTypography.labelMdHeavy,
                color = SirioTheme.colors.table.drawer.title,
            )
            Spacer(modifier = Modifier.width(tableDrawerItemSpacing.dp))
            content()
        }
    }
}

sealed class SirioTableDrawerType {
    data class Title(
        val title: String,
        val closeContentDescription: String?,
        val onClose: () -> Unit,
    ) : SirioTableDrawerType()

    data class Text(
        val title: String,
        val text: String,
    ) : SirioTableDrawerType()

    data class Number(
        val title: String,
        val text: String,
    ) : SirioTableDrawerType()

    data class Link(
        val title: String,
        val text: String,
        val onLinkClick: () -> Unit,
    ) : SirioTableDrawerType()

    data class Tag(
        val title: String,
        val text: String,
    ) : SirioTableDrawerType()
}

val tableDrawerLightColors = SirioTableDrawerColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    border = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    header = FoundationColor.colorAliasTextColorPrimaryDark110,
    close = SirioButtonHierarchy.GhostLight,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    number = FoundationColor.colorAliasTextColorSecondaryDark100,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
    tag = SirioTagColor.Light,
    bottomBar = FoundationColor.colorGlobalPrimary100,
    action = SirioButtonHierarchy.Primary,
)

val tableDrawerDarkColors = tableDrawerLightColors

@Keep
data class SirioTableDrawerColors(
    val background: Color,
    val border: Color,
    val header: Color,
    val close: SirioButtonHierarchy,
    val title: Color,
    val text: Color,
    val link: Color,
    val number: Color,
    val tag: SirioTagColor,
    val bottomBar: Color,
    val action: SirioButtonHierarchy,
) {
    companion object Companion {
        @Stable
        val Unspecified = SirioTableDrawerColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            header = Color.Unspecified,
            close = SirioButtonHierarchy.GhostLight,
            text = Color.Unspecified,
            title = Color.Unspecified,
            number = Color.Unspecified,
            link = Color.Unspecified,
            tag = SirioTagColor.Light,
            bottomBar = Color.Unspecified,
            action = SirioButtonHierarchy.Primary,
        )
    }
}

@Preview
@Composable
private fun SirioTableDrawerCommonPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableDrawerCommon("Header") {
                    SirioTextCommon(text = "Test", Modifier.weight(1f))
                }
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableDrawerCommon("Header", SirioThemeMode.Dark) {
                    SirioTextCommon(text = "Test")
                }
            }
        }
    }
}
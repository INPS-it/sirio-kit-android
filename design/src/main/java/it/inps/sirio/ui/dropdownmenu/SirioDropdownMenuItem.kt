//
// SirioDropdownMenuItem.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdownmenu

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownMenuContentPadding
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.ifElse
import it.inps.sirio.utils.takeTwoWords

/**
 * Sirio Dropdown Menu Item is an item contained within a dropdown menu.
 *
 * @param text The text to be displayed for the item.
 * @param contentDescription An optional description for accessibility services.
 * @param action The lambda to be executed when the item is clicked.
 */
@Composable
internal fun SirioDropdownMenuItem(
    text: String,
    contentDescription: String? = null,
    action: () -> Unit,
) {
    val backgroundColor = SirioTheme.colors.dropdownMenu.item.background
    val contentColor = SirioTheme.colors.dropdownMenu.item.content

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("item${text.takeTwoWords()}")
            .clickable(enabled = true, role = Role.DropdownList, onClick = action)
            .ifElse(
                condition = contentDescription != null,
                ifTrueModifier = Modifier.semantics {
                    this.contentDescription = contentDescription!!
                }
            )
            .background(backgroundColor)
            .padding(dropdownMenuContentPadding.dp)
    ) {
        SirioText(
            text = text,
            color = contentColor,
            typography = SirioTheme.foundationTypography.labelMdRegular,
        )
    }
}

/**
 * @param background The background color
 * @param content The content color
 *
 */
@Keep
data class SirioDropdownMenuItemColors(
    val background: Color,
    val content: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownMenuItemColors(
            background = Color.Unspecified,
            content = Color.Unspecified,
        )
    }
}

internal val dropdownMenuItemLightColors = SirioDropdownMenuItemColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    content = FoundationColor.colorAliasTextColorSecondaryDark100,
)

internal val dropdownMenuItemDarkColors = dropdownMenuItemLightColors

@Preview
@Composable
private fun SirioDropdownMenuItemPreview() {
    SirioTheme {
        Column {
            val text = "Action #1"
            SirioDropdownMenuItem(
                text = text,
                action = {},
            )
        }
    }
}
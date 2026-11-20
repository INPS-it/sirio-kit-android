//
// SirioButton.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.buttonBorderWidth
import it.inps.sirio.theme.buttonCornerRadius
import it.inps.sirio.theme.buttonIconSize
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource

/**
 * Sirio button
 *
 * This composable provides a customizable button with different sizes, hierarchies, and content options.
 * It supports text, icons, or both, and handles interactions like pressing and disabling.
 *
 * @param size The size of the button (Small, Medium, Large).
 * @param hierarchy The hierarchy of the button (Primary, Secondary, Tertiary).
 * @param modifier Modifier to be applied to the button.
 * @param text The text to be displayed on the button.
 * @param icon The icon to be displayed on the button.
 * @param enabled Whether the button is enabled or disabled.
 * @param iconContentDescription Content description for the icon, for accessibility purposes.
 * @param iconPosition The position of the icon relative to the text (Left, Right).
 * @param onClick The callback function to be executed when the button is clicked.
 */
@Composable
fun SirioButton(
    size: SirioButtonSize,
    hierarchy: SirioButtonHierarchy,
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: SirioIconSource? = null,
    enabled: Boolean = true,
    iconContentDescription: String? = null,
    iconPosition: SirioButtonIconPosition = SirioButtonIconPosition.Right,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val contentColor = hierarchy.toContentColor(isPressed, !enabled)
    val containerColor = hierarchy.toContainerColor(isPressed, !enabled)
    val borderColor = hierarchy.toBorderColor(isPressed, !enabled)
    val iconData = if (icon != null) {
        SirioIconData(
            icon = icon,
            iconColor = contentColor,
            size = buttonIconSize.dp,
            contentDescription = iconContentDescription,
        )
    } else null
    val border = borderColor?.let { BorderStroke(width = buttonBorderWidth.dp, color = it) }

    require(iconData != null || text != null) { "At least one of text or icon must be non-null" }

    val height = size.toHeight()
    Surface(
        onClick = onClick,
        modifier = modifier.height(height).semantics {
            role = Role.Button
            testTagsAsResourceId = true
        },
        enabled = enabled,
        shape = RoundedCornerShape(buttonCornerRadius.dp),
        color = containerColor,
        border = border,
        contentColor = contentColor,
        interactionSource = interactionSource,
    ) {
        if (text != null && iconPosition != SirioButtonIconPosition.Center) {
            ButtonWithTextContent(
                text = text,
                icon = iconData,
                iconPosition = iconPosition,
                contentColor = contentColor,
                minWidth = size.toMinWidth(),
                height = height,
            )
        } else {
            ButtonIconOnlyContent(size = size, icon = iconData!!)
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, fontScale = 3f)
@Composable
private fun SirioButtonFontScalePreview() {
    SirioTheme {
        SirioButton(
            size = SirioButtonSize.Small,
            hierarchy = SirioButtonHierarchy.GhostLight,
            text = "Apri",
            icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
            onClick = {},
        )
    }
}

@Preview(name = "Medium long text with trailing icon", showBackground = true, widthDp = 240)
@Composable
private fun SirioButtonLongTextWithTrailingIconPreview() {
    SirioTheme {
        SirioButton(
            size = SirioButtonSize.Medium,
            hierarchy = SirioButtonHierarchy.Primary,
            modifier = Modifier.width(208.dp),
            text = "PORTALE DELLE FAMIGLIE",
            icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
            onClick = {},
        )
    }
}

@Preview(widthDp = 1500, heightDp = 1850, backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun SirioButtonPreview() {
    SirioTheme {
        val text = "Text"
        Row(horizontalArrangement = Arrangement.spacedBy(100.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                ButtonPreviewColumn(text, SirioButtonSize.Large)
            }
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                ButtonPreviewColumn(text, SirioButtonSize.Medium)
            }
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                ButtonPreviewColumn(text, SirioButtonSize.Small)
            }
        }
    }
}

@Composable
private fun ButtonPreviewColumn(text: String, size: SirioButtonSize) {
    SirioButtonHierarchy.entries.forEachIndexed { index, hierarchy ->
        ButtonPreviewContent(text = text, size = size, style = hierarchy)
        if (index != SirioButtonHierarchy.entries.lastIndex) Spacer(Modifier.height(60.dp))
    }
}

@Composable
private fun ButtonPreviewContent(text: String, size: SirioButtonSize, style: SirioButtonHierarchy) {
    Row(horizontalArrangement = Arrangement.spacedBy(28.dp)) {
        SirioButton(size = size, hierarchy = style, text = text, onClick = {})
        SirioButton(size = size, hierarchy = style, text = text, icon = SirioIconSource.FaIcon(FaIcons.ArrowRight), onClick = {})
        SirioButton(size = size, hierarchy = style, text = text, icon = SirioIconSource.FaIcon(FaIcons.ArrowLeft), iconPosition = SirioButtonIconPosition.Left, onClick = {})
        SirioButton(size = size, hierarchy = style, icon = SirioIconSource.FaIcon(FaIcons.ArrowRight), iconPosition = SirioButtonIconPosition.Left, onClick = {})
    }
    Row(horizontalArrangement = Arrangement.spacedBy(28.dp)) {
        SirioButton(size = size, hierarchy = style, text = text, enabled = false, onClick = {})
        SirioButton(size = size, hierarchy = style, text = text, icon = SirioIconSource.FaIcon(FaIcons.ArrowRight), enabled = false, onClick = {})
        SirioButton(size = size, hierarchy = style, text = text, icon = SirioIconSource.FaIcon(FaIcons.ArrowLeft), iconPosition = SirioButtonIconPosition.Left, enabled = false, onClick = {})
        SirioButton(size = size, hierarchy = style, icon = SirioIconSource.FaIcon(FaIcons.ArrowRight), iconPosition = SirioButtonIconPosition.Left, enabled = false, onClick = {})
    }
}

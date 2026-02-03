//
// SirioButton.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.buttonBorderWidth
import it.inps.sirio.theme.buttonCornerRadius
import it.inps.sirio.theme.buttonIconSize
import it.inps.sirio.theme.buttonItemSpacing
import it.inps.sirio.theme.buttonMinWidthLarge
import it.inps.sirio.theme.buttonMinWidthMedium
import it.inps.sirio.theme.buttonMinWidthSmall
import it.inps.sirio.theme.buttonNewHeightLarge
import it.inps.sirio.theme.buttonNewHeightMedium
import it.inps.sirio.theme.buttonNewHeightSmall
import it.inps.sirio.theme.buttonPaddingHorizontal
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

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

    val iconData = if (icon != null)
        SirioIconData(
            icon = icon,
            iconColor = contentColor,
            size = buttonIconSize.dp,
            contentDescription = iconContentDescription,
        ) else null

    val border = borderColor?.let {
        BorderStroke(width = buttonBorderWidth.dp, color = it)
    }

    require(iconData != null || text != null) { "At least one of text or icon must be non-null" }

    val height = size.toHeight()
    Surface(
        onClick = onClick,
        modifier = modifier
            .height(height)
            .semantics {
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

@Composable
private fun ButtonIconOnlyContent(
    size: SirioButtonSize,
    icon: SirioIconData,
) {
    Box(
        modifier = Modifier
            .size(size.toHeight())
            .aspectRatio(1f)
            .testTag("buttonIcon"),
        contentAlignment = Alignment.Center,
    ) {
        SirioIcon(iconData = icon)
    }
}

@Composable
private fun ButtonWithTextContent(
    text: String,
    icon: SirioIconData?,
    iconPosition: SirioButtonIconPosition,
    contentColor: Color,
    minWidth: Dp,
    height: Dp,
) {
    Row(
        modifier = Modifier
            .height(height)
            .defaultMinSize(minWidth = minWidth)
            .padding(horizontal = buttonPaddingHorizontal.dp)
            .testTag("button${text.takeTwoWords()}"),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null && iconPosition == SirioButtonIconPosition.Left) {
            SirioIcon(iconData = icon)
            Spacer(Modifier.width(buttonItemSpacing.dp))
        }
        SirioText(
            text = text,
            color = contentColor,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            typography = SirioTheme.foundationTypography.labelMdHeavy,
        )
        if (icon != null && iconPosition == SirioButtonIconPosition.Right) {
            Spacer(Modifier.width(buttonItemSpacing.dp))
            SirioIcon(iconData = icon)
        }
    }
}

/**
 * Button style, when Dark/Light is provided it force the related theme, device theme is used otherwise
 */
enum class SirioButtonHierarchy {
    Primary,
    Secondary,
    TertiaryDark,
    TertiaryLight,
    GhostLight,
    GhostDark,
    Danger,
}

enum class SirioButtonSize {
    Large,
    Medium,
    Small,
}

enum class SirioButtonIconPosition {
    Left,
    Center,
    Right,
}

fun SirioButtonSize.toMinWidth(): Dp = when (this) {
    SirioButtonSize.Large -> buttonMinWidthLarge.dp
    SirioButtonSize.Medium -> buttonMinWidthMedium.dp
    SirioButtonSize.Small -> buttonMinWidthSmall.dp
}

fun SirioButtonSize.toHeight(): Dp = when (this) {
    SirioButtonSize.Large -> buttonNewHeightLarge.dp
    SirioButtonSize.Medium -> buttonNewHeightMedium.dp
    SirioButtonSize.Small -> buttonNewHeightSmall.dp
}

@Composable
fun SirioButtonHierarchy.toContainerColor(pressed: Boolean, disabled: Boolean): Color =
    when (this) {
        SirioButtonHierarchy.Primary -> SirioTheme.colors.button.primary.container
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.Secondary -> SirioTheme.colors.button.secondary.container
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.TertiaryDark -> SirioTheme.colors.button.tertiaryDark.container
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.TertiaryLight -> SirioTheme.colors.button.tertiaryLight.container
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.GhostLight,
            -> SirioTheme.colors.button.ghostLight.container
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.GhostDark -> SirioTheme.colors.button.ghostDark.container
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.Danger -> SirioTheme.colors.button.danger.container
            .get(pressed = pressed, disabled = disabled)
    }

@Composable
fun SirioButtonHierarchy.toContentColor(pressed: Boolean, disabled: Boolean): Color =
    when (this) {
        SirioButtonHierarchy.Primary -> SirioTheme.colors.button.primary.content
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.Secondary -> SirioTheme.colors.button.secondary.content
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.TertiaryDark -> SirioTheme.colors.button.tertiaryDark.content
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.TertiaryLight -> SirioTheme.colors.button.tertiaryLight.content
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.GhostLight, -> SirioTheme.colors.button.ghostLight.content
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.GhostDark -> SirioTheme.colors.button.ghostDark.content
            .get(pressed = pressed, disabled = disabled)

        SirioButtonHierarchy.Danger -> SirioTheme.colors.button.danger.content
            .get(pressed = pressed, disabled = disabled)
    }

@Composable
fun SirioButtonHierarchy.toBorderColor(pressed: Boolean, disabled: Boolean): Color? = when (this) {
    SirioButtonHierarchy.Primary -> SirioTheme.colors.button.primary.border
        ?.get(pressed = pressed, disabled = disabled)

    SirioButtonHierarchy.Secondary -> SirioTheme.colors.button.secondary.border
        ?.get(pressed = pressed, disabled = disabled)

    SirioButtonHierarchy.TertiaryDark -> SirioTheme.colors.button.tertiaryDark.border
        ?.get(pressed = pressed, disabled = disabled)

    SirioButtonHierarchy.TertiaryLight -> SirioTheme.colors.button.tertiaryLight.border
        ?.get(pressed = pressed, disabled = disabled)

    SirioButtonHierarchy.GhostLight -> SirioTheme.colors.button.ghostLight.border
        ?.get(pressed = pressed, disabled = disabled)

    SirioButtonHierarchy.GhostDark -> SirioTheme.colors.button.ghostDark.border
        ?.get(pressed = pressed, disabled = disabled)

    SirioButtonHierarchy.Danger -> SirioTheme.colors.button.danger.border
        ?.get(pressed = pressed, disabled = disabled)
}

@Keep
data class SirioButtonColors(
    val primary: SirioButtonStateColors,
    val secondary: SirioButtonStateColors,
    val tertiaryDark: SirioButtonStateColors,
    val tertiaryLight: SirioButtonStateColors,
    val ghostLight: SirioButtonStateColors,
    val ghostDark: SirioButtonStateColors,
    val danger: SirioButtonStateColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioButtonColors(
            primary = SirioButtonStateColors.Unspecified,
            secondary = SirioButtonStateColors.Unspecified,
            tertiaryDark = SirioButtonStateColors.Unspecified,
            tertiaryLight = SirioButtonStateColors.Unspecified,
            ghostLight = SirioButtonStateColors.Unspecified,
            ghostDark = SirioButtonStateColors.Unspecified,
            danger = SirioButtonStateColors.Unspecified,
        )
    }
}

@Keep
data class SirioButtonStateColors(
    val container: SirioColorState,
    val content: SirioColorState,
    val border: SirioColorState?,
) {

    companion object {
        @Stable
        val Unspecified = SirioButtonStateColors(
            container = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
        )
    }
}

val buttonLightColors = SirioButtonColors(
    primary = SirioButtonStateColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasTextColorPrimaryLight0,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null
    ),
    secondary = SirioButtonStateColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractiveSecondaryDefault,
            pressed = FoundationColor.colorAliasInteractiveSecondaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasTextColorPrimaryLight0,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null
    ),
    tertiaryDark = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = Color.Transparent,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAccentDefault,
            pressed = FoundationColor.colorAliasInteractiveAccentPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAccentDefault,
            pressed = FoundationColor.colorAliasInteractiveAccentPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        )
    ),
    tertiaryLight = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = Color.Transparent,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        )
    ),
    ghostLight = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
            disabled = Color.Transparent,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractivePrimaryDefault,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null
    ),
    ghostDark = SirioButtonStateColors(
        container = SirioColorState(
            default = Color.Transparent,
            pressed = FoundationColor.colorAliasInteractiveAccentPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAccentDefault,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null
    ),
    danger = SirioButtonStateColors(
        container = SirioColorState(
            default = FoundationColor.colorAliasInteractiveAlertDefault,
            pressed = FoundationColor.colorAliasInteractiveAlertPressed,
            disabled = FoundationColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = FoundationColor.colorAliasTextColorPrimaryLight0,
            pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
            disabled = FoundationColor.colorAliasTextColorDisabled,
        ),
        border = null
    )
)

val buttonDarkColors = buttonLightColors

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

@Preview(widthDp = 1500, heightDp = 1450, backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun SirioButtonPreview() {
    SirioTheme {
        val text = "Text"
        Row(horizontalArrangement = Arrangement.spacedBy(100.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                val size = SirioButtonSize.Large
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Primary
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Secondary
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.TertiaryLight
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.TertiaryDark
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.GhostLight
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.GhostDark
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Danger
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                val size = SirioButtonSize.Medium
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Primary
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Secondary
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.TertiaryLight
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.TertiaryDark
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.GhostLight
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.GhostDark
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Danger
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                val size = SirioButtonSize.Small
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Primary
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Secondary
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.TertiaryLight
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.TertiaryDark
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.GhostLight
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.GhostDark
                )
                Spacer(Modifier.height(60.dp))
                ButtonPreviewContent(
                    text = text,
                    size = size,
                    style = SirioButtonHierarchy.Danger
                )
            }
        }
    }
}

@Composable
private fun ButtonPreviewContent(text: String, size: SirioButtonSize, style: SirioButtonHierarchy) {
    Row(horizontalArrangement = Arrangement.spacedBy(28.dp)) {
        SirioButton(
            size = size,
            hierarchy = style,
            text = text,
            onClick = {},
        )
        SirioButton(
            size = size,
            hierarchy = style,
            text = text,
            icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
            onClick = {},
        )
        SirioButton(
            size = size,
            hierarchy = style,
            text = text,
            icon = SirioIconSource.FaIcon(FaIcons.ArrowLeft),
            iconPosition = SirioButtonIconPosition.Left,
            onClick = {},
        )
        SirioButton(
            size = size,
            hierarchy = style,
            icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
            iconPosition = SirioButtonIconPosition.Left,
            onClick = {},
        )
    }
    Row(horizontalArrangement = Arrangement.spacedBy(28.dp)) {
        SirioButton(
            size = size,
            hierarchy = style,
            text = text,
            enabled = false,
            onClick = {},
        )
        SirioButton(
            size = size,
            hierarchy = style,
            text = text,
            icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
            enabled = false,
            onClick = {},
        )
        SirioButton(
            size = size,
            hierarchy = style,
            text = text,
            icon = SirioIconSource.FaIcon(FaIcons.ArrowLeft),
            iconPosition = SirioButtonIconPosition.Left,
            enabled = false,
            onClick = {},
        )
        SirioButton(
            size = size,
            hierarchy = style,
            icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
            iconPosition = SirioButtonIconPosition.Left,
            enabled = false,
            onClick = {},
        )
    }
}

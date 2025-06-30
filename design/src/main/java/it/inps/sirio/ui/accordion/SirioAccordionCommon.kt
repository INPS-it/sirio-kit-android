//
// SirioAccordionCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import androidx.annotation.Keep
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.accordionBorderWidth
import it.inps.sirio.theme.accordionContentBorderWidth
import it.inps.sirio.theme.accordionContentPadding
import it.inps.sirio.theme.accordionHeaderPadding
import it.inps.sirio.theme.accordionHeaderPaddingIconTitle
import it.inps.sirio.theme.accordionHeaderPaddingTag
import it.inps.sirio.theme.accordionHeaderPaddingText
import it.inps.sirio.theme.accordionHeight
import it.inps.sirio.theme.accordionIconSize
import it.inps.sirio.theme.accordionIndicatorSize
import it.inps.sirio.ui.tag.SirioTag
import it.inps.sirio.ui.tag.SirioTagType
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
 * Accordion implementation
 *
 * @param title The title of the accordion
 * @param color The color of the accordion
 * @param icon The optional icon to display
 * @param tag The optional tag to display
 * @param text The optional text to display
 * @param open Whether the accordion is open
 * @param enabled Whether the accordion is enabled
 * @param onTapAccordion Callback that is executed when the accordion is tapped
 * @param content The content of the accordion
 */
@Composable
internal fun SirioAccordionCommon(
    title: String,
    color: SirioAccordionColor,
    icon: SirioIconSource? = null,
    tag: String? = null,
    text: String? = null,
    open: Boolean = false,
    enabled: Boolean = true,
    onTapAccordion: ((Boolean) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    SirioTheme(darkTheme = color == SirioAccordionColor.PRIMARY) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val backgroundColor = SirioTheme.colors.accordion.background.get(
            pressed = open || isPressed,
            disabled = enabled.not(),
        )
        val borderColor = SirioTheme.colors.accordion.border.get(
            pressed = open || isPressed,
            disabled = enabled.not(),
        )
        val iconColor = SirioTheme.colors.accordion.icon.get(
            pressed = open || isPressed,
            disabled = enabled.not(),
        )
        val textColor = SirioTheme.colors.accordion.text.get(
            pressed = open || isPressed,
            disabled = enabled.not(),
        )
        val indicatorColor = SirioTheme.colors.accordion.indicator.get(
            pressed = open || isPressed,
            disabled = enabled.not(),
        )

        Column(
            Modifier
                .wrapContentHeight()
                .background(backgroundColor)
                .border(width = accordionBorderWidth.dp, color = borderColor),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(accordionHeight.dp)
                    .clickable(
                        enabled = enabled,
                        interactionSource = interactionSource,
                        indication = null,
                    ) {
                        onTapAccordion?.let { it(!open) }
                    }
                    .padding(horizontal = accordionHeaderPadding.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    SirioIcon(icon = it, iconColor = iconColor, size = accordionIconSize.dp)
                    Spacer(Modifier.width(accordionHeaderPaddingIconTitle.dp))
                }
                SirioTextCommon(
                    text = title,
                    modifier = Modifier.weight(1f),
                    color = textColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 21,
                    typography = SirioTheme.foundationTypography.labelMdMiddle,
                )
                tag?.let {
                    SirioTag(
                        text = it,
                        tagType = SirioTheme.colors.accordion.tag,
                        modifier = Modifier.padding(horizontal = accordionHeaderPaddingTag.dp),
                    )
                }
                text?.let {
                    SirioText(
                        text = it,
                        modifier = Modifier.padding(horizontal = accordionHeaderPaddingText.dp),
                        color = textColor,
                        typography = SirioTheme.foundationTypography.labelMdRegular,
                    )
                }
                SirioIcon(
                    icon = SirioIconSource.FaIcon(if (open) FaIcons.AngleUp else FaIcons.AngleDown),
                    iconColor = indicatorColor,
                    size = accordionIndicatorSize.dp
                )
            }
            AnimatedVisibility(
                visible = open,
                enter = fadeIn() + expandVertically(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(SirioTheme.colors.accordion.contentBackground)
                        .border(
                            width = accordionContentBorderWidth.dp,
                            color = SirioTheme.colors.accordion.contentBorder,
                        )
                        .padding(accordionContentPadding.dp)
                ) {
                    content()
                }
            }
        }
    }
}

enum class SirioAccordionColor {
    PRIMARY,
    LIGHT,
}

@Keep
data class SirioAccordionColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val icon: SirioColorState,
    val text: SirioColorState,
    val tag: SirioTagType,
    val indicator: SirioColorState,
    val contentBackground: Color,
    val contentBorder: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAccordionColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            icon = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
            tag = SirioTagType.WHITE,
            indicator = SirioColorState.Unspecified,
            contentBackground = Color.Unspecified,
            contentBorder = Color.Unspecified,
        )
    }
}

internal val accordionLightColors = SirioAccordionColors(
    background = SirioColorState(
        default = FoundationColor.colorAliasInteractivePrimary000Default,
        pressed = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    border = SirioColorState(
        default = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
        pressed = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
        disabled = Color.Unspecified,
    ),
    icon = SirioColorState(
        default = FoundationColor.colorAliasTextColorPrimaryDark110,
        pressed = FoundationColor.colorAliasTextColorPrimaryDark110,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    text = SirioColorState(
        default = FoundationColor.colorAliasTextColorPrimaryDark110,
        pressed = FoundationColor.colorAliasTextColorPrimaryDark110,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    tag = SirioTagType.WHITE,
    indicator = SirioColorState(
        default = FoundationColor.colorAliasTextColorPrimaryDark110,
        pressed = FoundationColor.colorAliasTextColorPrimaryDark110,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    contentBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    contentBorder = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
)

internal val accordionDarkColors = SirioAccordionColors(
    background = SirioColorState(
        default = FoundationColor.colorAliasInteractivePrimaryDefault,
        pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    border = SirioColorState.Unspecified,
    icon = SirioColorState(
        default = FoundationColor.colorAliasAppInteractivePrimary000Default,
        pressed = FoundationColor.colorAliasAppInteractivePrimary000Default,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    text = SirioColorState(
        default = FoundationColor.colorAliasTextColorPrimaryLight0,
        pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    tag = SirioTagType.WHITE,
    indicator = SirioColorState(
        default = FoundationColor.colorAliasAppInteractivePrimary000Default,
        pressed = FoundationColor.colorAliasAppInteractivePrimary000Default,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    contentBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    contentBorder = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
)

@Preview(widthDp = 800, heightDp = 900)
@Composable
private fun AccordionCommonPreview() {
    SirioTheme {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val contentText =
                "Lorem ipsum dolor sit amet consectetur. Hendrerit porttitor rutrum accumsan elit. Porttitor viverra eget pretium et lobortis maecenas. Cras venenatis pulvinar faucibus purus facilisis vitae sit at ac."
            val content = @Composable {
                SirioText(
                    text = contentText,
                    color = FoundationColor.colorAliasTextColorSecondaryDark100,
                    typography = SirioTheme.foundationTypography.bodyMdRegular,
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(52.dp),
            ) {
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.PRIMARY,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.PRIMARY,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    open = true,
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.PRIMARY,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = false,
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.LIGHT,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.LIGHT,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    open = true,
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.LIGHT,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = false,
                    content = content,
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(52.dp),
            ) {
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.PRIMARY,
                    tag = "Tag",
                    text = "(Text)",
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.PRIMARY,
                    tag = "Tag",
                    text = "(Text)",
                    open = true,
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.PRIMARY,
                    tag = "Tag",
                    text = "(Text)",
                    enabled = false,
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.LIGHT,
                    tag = "Tag",
                    text = "(Text)",
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.LIGHT,
                    tag = "Tag",
                    text = "(Text)",
                    open = true,
                    content = content,
                )
                SirioAccordionCommon(
                    title = "Title",
                    color = SirioAccordionColor.LIGHT,
                    tag = "Tag",
                    text = "(Text)",
                    enabled = false,
                    content = content,
                )
            }
        }
    }
}
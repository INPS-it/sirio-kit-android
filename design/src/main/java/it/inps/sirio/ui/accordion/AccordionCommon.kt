//
// AccordionCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import androidx.annotation.Keep
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.*
import it.inps.sirio.utils.FaIconCentered


/**
 * Accordion implementation
 *
 * @param text The title of the accordion
 * @param open Whether the accordion is open
 * @param enabled Whether the accordion is enabled
 * @param onTapAccordion Callback that is executed when the accordion is tapped
 */
@Composable
internal fun AccordionCommon(
    text: String,
    open: Boolean = false,
    enabled: Boolean = true,
    onTapAccordion: ((Boolean) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val (backgroundColor, contentColor, borderColor, borderWidth) = getAccordionParams(
        enabled,
        isFocused,
        isPressed,
        isHovered,
        open,
    )

    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
            .border(width = borderWidth, color = borderColor),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(accordionHeight)
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null,
                ) {
                    onTapAccordion?.let { it(!open) }
                }
                .focusable(enabled = true, interactionSource = interactionSource)
                .padding(horizontal = accordionHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = SirioTheme.typography.accordionText,
                color = contentColor,
            )
            FaIconCentered(
                icon = if (open) FaIcons.AngleUp else FaIcons.AngleDown,
                iconColor = contentColor,
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
                        width = accordionContentBorderWidth,
                        color = SirioTheme.colors.accordion.contentBorder,
                    )
                    .padding(accordionContentPadding)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun getAccordionParams(
    enabled: Boolean,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    expanded: Boolean
) = if (!enabled) AccordionParams(
    SirioTheme.colors.accordion.background.disabled,
    SirioTheme.colors.accordion.content.disabled,
    SirioTheme.colors.accordion.border.disabled,
    accordionDefaultBorderWidth
) else
    when {
        isFocused -> AccordionParams(
            SirioTheme.colors.accordion.background.focused,
            SirioTheme.colors.accordion.content.focused,
            SirioTheme.colors.accordion.border.focused,
            accordionFocusBorderWidth
        )
        isPressed -> AccordionParams(
            SirioTheme.colors.accordion.background.pressed,
            SirioTheme.colors.accordion.content.pressed,
            SirioTheme.colors.accordion.border.pressed,
            accordionDefaultBorderWidth
        )
        isHovered -> AccordionParams(
            SirioTheme.colors.accordion.background.hovered,
            SirioTheme.colors.accordion.background.hovered,
            SirioTheme.colors.accordion.border.hovered,
            accordionDefaultBorderWidth
        )
        else -> if (expanded) AccordionParams(
            SirioTheme.colors.accordion.activedBackground,
            SirioTheme.colors.accordion.content.default,
            SirioTheme.colors.accordion.activedBorder,
            accordionDefaultBorderWidth
        ) else AccordionParams(
            SirioTheme.colors.accordion.background.default,
            SirioTheme.colors.accordion.content.default,
            SirioTheme.colors.accordion.border.default,
            accordionDefaultBorderWidth
        )
    }

@Keep
data class AccordionParams(
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val borderWidth: Dp
)

@Keep
data class SirioAccordionColors(
    var background: SirioColorState,
    var border: SirioColorState,
    var content: SirioColorState,
    var activedBackground: Color,
    var activedBorder: Color,
    var contentBackground: Color,
    var contentBorder: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAccordionColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
            activedBackground = Color.Unspecified,
            contentBackground = Color.Unspecified,
            activedBorder = Color.Unspecified,
            contentBorder = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun AccordionCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AccordionCommon(text = "Accordion Item #1") { Text(text = "Content") }
            AccordionCommon(text = "Accordion Item #1", open = true) {
                Column() {
                    for (i in 1..5)
                     {
                        Text(
                            text = "Content $i",
                            Modifier.background(Color.Red)
                        )
                    }
                }
//                Text(
//                    text = "Content",
//                    Modifier.background(Color.Red)
//                )
            }
            AccordionCommon(
                text = "Accordion Item #1",
                enabled = false
            ) { Text(text = "Content") }
        }
    }
}
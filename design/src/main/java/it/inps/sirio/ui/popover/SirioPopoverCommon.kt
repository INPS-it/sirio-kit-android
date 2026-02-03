//
// SirioPopoverCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.popover

import androidx.annotation.Keep
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipScope
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.popoverCornerRadius
import it.inps.sirio.theme.popoverMaxWidth
import it.inps.sirio.theme.popoverPaddingVertical
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import kotlinx.coroutines.launch

/**
 * The common building block for a Sirio-styled popover, implemented using Material 3's [TooltipBox].
 *
 * This composable provides the core functionality for displaying a rich tooltip with a title,
 * text, and an optional action button. It is designed to be used internally by more specific
 * popover implementations like [SirioPopover].
 *
 * @param text The main content text to be displayed inside the popover.
 * @param title An optional title for the popover.
 * @param buttonText An optional label for a button within the popover. If provided, `action` should also be set.
 * @param action An optional lambda to be executed when the button is clicked. The popover is automatically dismissed on click.
 * @param anchor The composable that will trigger the popover when clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioPopoverCommon(
    text: String,
    title: String? = null,
    buttonText: String? = null,
    action: (() -> Unit)? = null,
    anchor: @Composable (() -> Unit),
) {
    val tooltipState = rememberTooltipState(initialIsVisible = false, isPersistent = true)
    val coroutineScope = rememberCoroutineScope()

    TooltipBox(
        positionProvider = rememberSirioPopoverTooltipPositionProvider(),
        tooltip = {
            PopoverContent(
                title = title,
                text = text,
                buttonText = buttonText,
                action = {
                    action?.invoke()
                    coroutineScope.launch {
                        tooltipState.dismiss()
                    }
                },
            )
        },
        state = tooltipState,
        hasAction = action != null,
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    coroutineScope.launch { tooltipState.show() }
                }
        ) {
            anchor()
        }
    }
}

/**
 * A private composable that defines the content layout of the [RichTooltip].
 *
 * @param text The main content text.
 * @param title An optional title.
 * @param buttonText An optional label for the button.
 * @param action The lambda to execute on button click.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TooltipScope.PopoverContent(
    text: String,
    title: String? = null,
    buttonText: String? = null,
    action: (() -> Unit)? = null,
) {
    RichTooltip(
        title = title?.let {
            {
                SirioText(
                    text = title,
                    color = SirioTheme.colors.popover.title,
                    typography = SirioTheme.foundationTypography.labelMdHeavy,
                )
            }
        },
        text = {
            SirioText(
                text = text,
                modifier = Modifier.padding(vertical = popoverPaddingVertical.dp),
                color = SirioTheme.colors.popover.text,
                typography = SirioTheme.foundationTypography.bodySmRegular,
            )
        },
        action = buttonText?.let {
            {
                SirioButton(
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioTheme.colors.popover.button,
                    modifier = Modifier.padding(bottom = popoverPaddingVertical.dp),
                    text = buttonText,
                    onClick = action ?: {},
                )
            }
        },
        maxWidth = popoverMaxWidth.dp,
        shape = RoundedCornerShape(popoverCornerRadius.dp),
        colors = TooltipDefaults.richTooltipColors(
            containerColor = SirioTheme.colors.popover.background,
            contentColor = SirioTheme.colors.popover.text,
            titleContentColor = SirioTheme.colors.popover.title,
        )
    )
}

@Keep
@Immutable
data class SirioPopoverColors(
    val background: Color,
    val title: Color,
    val text: Color,
    val button: SirioButtonHierarchy,
    val anchor: Color,
) {
    companion object {
        val Unspecified = SirioPopoverColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            button = SirioButtonHierarchy.TertiaryDark,
            anchor = Color.Unspecified,
        )
    }
}

internal val popoverLightColors = SirioPopoverColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryDark120,
    title = FoundationColor.colorAliasTextColorPrimaryLight0,
    text = FoundationColor.colorAliasTextColorPrimaryLight0,
    button = SirioButtonHierarchy.TertiaryDark,
    anchor = FoundationColor.colorGlobalSemanticInfo100,
)

internal val popoverDarkColors = popoverLightColors

@Preview(showSystemUi = true)
@Composable
private fun SirioPopoverCommonPreview() {
    SirioTheme() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SirioPopoverCommon(
                title = "Titolo",
                text = "Lorem ipsum sed eu consequat cras vitae eros.",
                buttonText = "Azione",
                anchor = {
                    SirioIcon(
                        icon = SirioIconSource.FaIcon(FaIcons.Camera),
                        iconColor = Color.Black,
                        size = 24.dp,
                        contentDescription = null,
                    )

                }
            )
        }
    }
}
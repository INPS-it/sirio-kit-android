//
// SirioPopoverCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.popover

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.popoverAnchorSize
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
 * A composable that displays a popover with informative content when its anchor is clicked.
 *
 * This version of [SirioPopover] is designed for direct use, accepting individual parameters
 * for its content. The anchor for this popover is a default information circle icon.
 *
 * @param text The main content text to be displayed inside the popover.
 * @param title An optional title for the popover.
 * @param buttonText An optional label for a button within the popover. If provided, `action` should also be set.
 * @param iconContentDescription The accessibility description for the anchor icon.
 * @param action An optional lambda to be executed when the button is clicked.
 */
@Composable
fun SirioPopover(
    text: String,
    title: String? = null,
    buttonText: String? = null,
    iconContentDescription: String? = null,
    action: (() -> Unit)? = null,
) {
    SirioPopoverCommon(
        text = text,
        title = title,
        buttonText = buttonText,
        action = action,
    ) {
        SirioIcon(
            icon = SirioIconSource.FaIcon(FaIcons.InfoCircle),
            iconColor = SirioTheme.colors.popover.anchor,
            size = popoverAnchorSize.dp,
            contentDescription = iconContentDescription,
        )
    }
}

/**
 * A composable that displays a popover with informative content, configured via a [SirioPopoverData] object.
 *
 * This overload is a convenience wrapper that simplifies the usage of [SirioPopover]
 * by accepting a single data object.
 *
 * @param data The [SirioPopoverData] object containing all configuration for the popover.
 */
@Composable
fun SirioPopover(
    data: SirioPopoverData,
) {
    SirioPopover(
        text = data.text,
        title = data.title,
        buttonText = data.buttonText,
        iconContentDescription = data.iconContentDescription,
        action = data.action,
    )
}

/**
 * A data class representing the content and configuration for a [SirioPopover].
 *
 * @property text The main content text of the popover.
 * @property title An optional title for the popover.
 * @property buttonText An optional label for the button.
 * @property iconContentDescription The accessibility description for the anchor icon.
 * @property action An optional lambda to be executed when the button is clicked.
 */
@Keep
data class SirioPopoverData(
    val text: String,
    val title: String? = null,
    val buttonText: String? = null,
    val iconContentDescription: String? = null,
    val action: (() -> Unit)? = null,
)

@Preview(showSystemUi = true)
@Composable
private fun SirioPopoverPreview() {
    SirioTheme() {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(Modifier.align(Alignment.TopStart)) {
                SirioPopover(
                    title = "Titolo",
                    text = "Lorem ipsum sed eu consequat cras vitae eros.",
                    buttonText = "Azione",
                )
            }
            Box(Modifier.align(Alignment.TopEnd)) {
                SirioPopover(
                    title = "Titolo",
                    text = "Lorem ipsum sed eu consequat cras vitae eros.",
                    buttonText = "Azione",
                )
            }
            Box(
                Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.Center)
            ) {
                SirioPopover(
                    title = "Titolo",
                    text = "Lorem ipsum sed eu consequat cras vitae eros.",
                    buttonText = "Azione",
                )
            }
            Box(
                Modifier
                    .fillMaxWidth(0.3f)
                    .align(Alignment.CenterEnd)
            ) {
                SirioPopover(
                    title = "Titolo",
                    text = "Lorem ipsum sed eu consequat cras vitae eros.",
                    buttonText = "Azione",
                )
            }
            Box(Modifier.align(Alignment.BottomStart)) {
                SirioPopover(
                    title = "Titolo",
                    text = "Lorem ipsum sed eu consequat cras vitae eros.",
                    buttonText = "Azione",
                )
            }
            Box(Modifier.align(Alignment.BottomEnd)) {
                SirioPopover(
                    title = "Titolo",
                    text = "Lorem ipsum sed eu consequat cras vitae eros.",
                    buttonText = "Azione",
                )
            }
        }
    }
}
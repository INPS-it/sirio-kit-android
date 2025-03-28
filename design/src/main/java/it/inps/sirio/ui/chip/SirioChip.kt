//
// SirioChip.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.chipButtonSize
import it.inps.sirio.theme.chipHeight
import it.inps.sirio.theme.chipIconSize
import it.inps.sirio.theme.chipSpacingHorizontal
import it.inps.sirio.theme.chipWithCloseButtonBorderWidth
import it.inps.sirio.theme.chipWithCloseButtonContentPadding
import it.inps.sirio.theme.chipWithCloseButtonIconSize
import it.inps.sirio.theme.chipWithClosePaddingEnd
import it.inps.sirio.theme.chipWithClosePaddingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
 * A chip component that can display text, an optional icon, and a close button.
 *
 * @param text The text to be displayed on the chip.
 * @param enabled Whether the chip is enabled or disabled.
 * @param icon The optional icon to be displayed on the chip.
 * @param closeContentDescription The content description for the close button.
 * @param onClose The callback function to be executed when the close button is clicked.
 */
@Composable
fun SirioChip(
    text: String,
    enabled: Boolean = true,
    icon: FaIconType? = null,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val containerColor = SirioTheme.colors.chip.close.container.get(isPressed, !enabled)
    val contentColor = SirioTheme.colors.chip.close.content.get(isPressed, !enabled)
    val buttonColor = SirioTheme.colors.chip.close.button.get(isPressed, !enabled)
    val buttonBorderColor = SirioTheme.colors.chip.close.buttonBorder.get(isPressed, !enabled)

    Surface(
        modifier = Modifier
            .height(chipHeight.dp)
            .width(IntrinsicSize.Max),
        shape = CircleShape,
        color = containerColor,
    ) {
        Row(
            Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .padding(
                    start = chipWithClosePaddingHorizontal.dp,
                    end = chipWithClosePaddingEnd.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(chipSpacingHorizontal.dp)
        ) {
            icon?.let {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(icon),
                    size = chipIconSize.dp,
                    iconColor = contentColor,
                )
            }
            SirioTextCommon(
                text = text,
                modifier = Modifier.wrapContentHeight(),
                color = contentColor,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                typography = SirioTheme.foundationTypography.labelMdHeavy,
            )
            OutlinedButton(
                onClick = onClose,
                modifier = Modifier
                    .size(chipButtonSize.dp)
                    .aspectRatio(1f),
                shape = CircleShape,
                border = BorderStroke(chipWithCloseButtonBorderWidth.dp, buttonBorderColor),
                contentPadding = PaddingValues(chipWithCloseButtonContentPadding.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = buttonColor),
                interactionSource = interactionSource,
            ) {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(FaIcons.Times),
                    size = chipWithCloseButtonIconSize.dp,
                    iconColor = contentColor,
                    contentDescription = closeContentDescription,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SirioChipPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            SirioChip(
                icon = FaIcons.User,
                text = "Chip",
                enabled = true,
                closeContentDescription = null,
                onClose = {},
            )
            SirioChip(
                icon = FaIcons.User,
                text = "Chip",
                enabled = false,
                closeContentDescription = null,
                onClose = {},
            )
        }
    }
}
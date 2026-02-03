//
// SirioChipsSelectable.kt
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.chipHeight
import it.inps.sirio.theme.chipIconSize
import it.inps.sirio.theme.chipSelectionBorderWidth
import it.inps.sirio.theme.chipSelectionPaddingHorizontal
import it.inps.sirio.theme.chipSpacingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

/**
 * A selectable chip component.
 *
 * @param text The text to display inside the chip.
 * @param active Whether the chip is currently active.
 * @param enabled Whether the chip is enabled for user interaction.
 * @param onSelectedChange Callback invoked when the chip's selection state changes. The lambda
 * function receives a boolean parameter, which is `true` if the chip is selected, and `false` if
 * it's not.
 */
@Composable
fun SirioChipsSelectable(
    text: String,
    active: Boolean,
    enabled: Boolean = true,
    onSelectedChange: (isSelected: Boolean) -> Unit,
) {
    val icon = if (active) FaIcons.Check else null

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val containerColorSet =
        if (active) SirioTheme.colors.chip.selectable.containerSelected else SirioTheme.colors.chip.selectable.container
    val contentColorSet =
        if (active) SirioTheme.colors.chip.selectable.contentSelected else SirioTheme.colors.chip.selectable.content
    val borderColorSet =
        if (active) SirioTheme.colors.chip.selectable.borderSelected else SirioTheme.colors.chip.selectable.border
    val containerColor = containerColorSet.get(isPressed, !enabled)
    val contentColor = contentColorSet.get(isPressed, !enabled)
    val borderColor = borderColorSet?.get(isPressed, !enabled)

    Surface(
        checked = active,
        onCheckedChange = { onSelectedChange(!active) },
        modifier = Modifier
            .height(chipHeight.dp)
            .width(IntrinsicSize.Max)
            .testTag("chip${text.takeTwoWords()}")
        ,
        enabled = enabled,
        shape = CircleShape,
        color = containerColor,
        border = borderColor?.let { BorderStroke(chipSelectionBorderWidth.dp, it) },
        interactionSource = interactionSource,
    ) {
        Row(
            Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .padding(horizontal = chipSelectionPaddingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(chipSpacingHorizontal.dp)
        ) {
            icon?.let {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(it),
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
        }
    }
}

@Preview
@Composable
private fun SirioChipPreview() {
    SirioTheme {
        var active by remember { mutableStateOf(true) }
        Column(Modifier.background(Color.White)) {
            Row {
                SirioChipsSelectable(
                    text = "Chip",
                    active = active,
                    enabled = true,
                    onSelectedChange = { active = it },
                )
                SirioChipsSelectable(
                    text = "Chip",
                    active = false,
                    enabled = true,
                    onSelectedChange = { })
            }
            SirioChipsSelectable(
                text = "Chip",
                active = true,
                enabled = false,
                onSelectedChange = { },
            )
            SirioChipsSelectable(
                text = "Chip",
                active = false,
                enabled = false,
                onSelectedChange = { },
            )
        }
    }
}

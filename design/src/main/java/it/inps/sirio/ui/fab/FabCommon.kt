//
// FabCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.*
import it.inps.sirio.theme.*
import it.inps.sirio.utils.FaIconCentered

/**
 * A custom FAB for type handling [FABType]
 *
 * @param icon The FAB icon
 * @param text The optional FAB text
 * @param onClick The callback on fab pressed
 * @param type A [FABType]
 */
@Composable
internal fun FabCommon(
    icon: FaIconType,
    text: String? = null,
    onClick: () -> Unit,
    type: FABType,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val backgroundColor =
        when {
            isFocused -> SirioTheme.colors.fabFocusBackground
            isPressed -> SirioTheme.colors.fabPressedBackground
            isHovered -> SirioTheme.colors.fabHoverBackground
            else -> SirioTheme.colors.fabDefaultBackground
        }
    val contentColor =
        when {
            isFocused -> SirioTheme.colors.fabContent
            isPressed -> SirioTheme.colors.fabContent
            isHovered -> SirioTheme.colors.fabContent
            else -> SirioTheme.colors.fabContent
        }
    val paddingValues = when (type) {
        FABType.REGULAR -> PaddingValues(fabRegularPadding)
        FABType.SMALL -> PaddingValues(fabSmallPadding)
        FABType.EXTENDED -> PaddingValues(fabExtendedHorizontalPadding, fabExtendedVerticalPadding)
    }
    var modifier = if (type == FABType.SMALL) {
        Modifier.sizeIn(
            maxWidth = fabSmallSize,
            maxHeight = fabSmallSize,
        )
    } else {
        Modifier
    }
    modifier = modifier.then(Modifier.focusable(true, interactionSource = interactionSource))
    if (isFocused) {
        val borderPadding =
            if (type == FABType.EXTENDED) fabExtendedFocusedBorderPadding else fabFocusedBorderPadding
        Box(
            Modifier
                .border(
                    fabFocusedBorderWidth,
                    color = SirioTheme.colors.fabBorderFocus,
                    shape = MaterialTheme.shapes.small.copy(CornerSize(percent = fabCornerSizePercent))
                )
                .padding(borderPadding)
        ) {
            FABContent(
                type = type,
                onClick = onClick,
                backgroundColor = backgroundColor,
                icon = icon,
                contentColor = contentColor,
                paddingValues = paddingValues,
                text = text,
                modifier = modifier,
                interactionSource = interactionSource
            )
        }
    } else {
        FABContent(
            type = type,
            onClick = onClick,
            backgroundColor = backgroundColor,
            icon = icon,
            contentColor = contentColor,
            paddingValues = paddingValues,
            text = text,
            modifier = modifier,
            interactionSource = interactionSource,
        )
    }
}

/**
 * The FAB content in row
 *
 * @param type A [FABType]
 * @param onClick The callback on FAB click
 * @param backgroundColor The FAB background color
 * @param icon The FAB icon
 * @param contentColor The color used for icon and text
 * @param paddingValues The padding between content and border
 * @param text The optional FAB text
 * @param modifier The modifier is used internally
 * @param interactionSource The [MutableInteractionSource] to handle state changes
 */
@Composable
private fun FABContent(
    type: FABType,
    onClick: () -> Unit,
    backgroundColor: Color,
    icon: FaIconType,
    contentColor: Color,
    paddingValues: PaddingValues,
    text: String?,
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = backgroundColor,
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = fabCornerSizePercent)),
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        Row(
            Modifier.padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FabIcon(icon, contentColor)
            if (type == FABType.EXTENDED) {
                FabText(text, contentColor)
            }
        }
    }
}

@Composable
private fun FabText(text: String?, contentColor: Color) {
    text?.let {
        Spacer(modifier = Modifier.width(fabExtendedSpacerWidth))
        Text(
            text = it,
            color = contentColor,
            style = SirioTheme.typography.fabText,
        )
    }
}

@Composable
private fun FabIcon(
    icon: FaIconType,
    iconColor: Color
) {
    FaIconCentered(
        icon = icon,
        size = fabIconSize,
        iconColor = iconColor,
    )
}

/**
 * Small type reduce internal padding
 * Only extended type has text
 */
internal enum class FABType {
    REGULAR,
    SMALL,
    EXTENDED
}

@Preview
@Composable
private fun CustomFabPreview() {
    SirioTheme {
        Column {
            FabCommon(onClick = {}, icon = FaIcons.Plus, text = "Text", type = FABType.REGULAR)
            FabCommon(onClick = {}, icon = FaIcons.Plus, text = "Text", type = FABType.SMALL)
            FabCommon(onClick = {}, icon = FaIcons.Plus, text = "Text", type = FABType.EXTENDED)
        }
    }
}
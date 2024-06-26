//
// FabCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.fabCornerSizePercent
import it.inps.sirio.theme.fabExtendedFocusedBorderPadding
import it.inps.sirio.theme.fabExtendedHorizontalPadding
import it.inps.sirio.theme.fabExtendedSpacerWidth
import it.inps.sirio.theme.fabExtendedVerticalPadding
import it.inps.sirio.theme.fabFocusedBorderPadding
import it.inps.sirio.theme.fabFocusedBorderWidth
import it.inps.sirio.theme.fabIconSize
import it.inps.sirio.theme.fabRegularPadding
import it.inps.sirio.theme.fabSmallPadding
import it.inps.sirio.theme.fabSmallSize
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

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
        SirioTextCommon(
            text = it,
            color = contentColor,
            typography = SirioTheme.typography.fabText,
        )
    }
}

@Composable
private fun FabIcon(
    icon: FaIconType,
    iconColor: Color
) {
    SirioIcon(
        faIcon = icon,
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

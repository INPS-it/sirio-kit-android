//
// SirioFabCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseStateColors
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.fabCornerRadius
import it.inps.sirio.theme.fabExtendedIconSize
import it.inps.sirio.theme.fabExtendedPaddingHorizontal
import it.inps.sirio.theme.fabExtendedPaddingVertical
import it.inps.sirio.theme.fabExtendedSpacerWidth
import it.inps.sirio.theme.fabIconSize
import it.inps.sirio.theme.fabRegularPadding
import it.inps.sirio.theme.fabSizeRegular
import it.inps.sirio.theme.fabSizeSmall
import it.inps.sirio.theme.fabSmallPadding
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

/**
 * Common implementation for Sirio Floating Action Button (FAB).
 *
 * This composable creates a FAB with an icon and an optional text.
 *
 * @param icon The icon to display within the FAB.
 * @param text Optional text to display beside the icon. If null, the FAB will be an icon-only button.
 * @param iconContentDescription Content description for the icon.
 * @param size The size of the FAB if text is not provided.
 * @param onClick The callback function for when the FAB is clicked.
 */
@Composable
internal fun SirioFabCommon(
    icon: FaIconType,
    modifier: Modifier = Modifier,
    text: String? = null,
    iconContentDescription: String? = null,
    size: SirioFabSize? = null,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor = SirioTheme.colors.fab.container.get(pressed = isPressed)
    val contentColor = SirioTheme.colors.fab.content.get(pressed = isPressed)
    val iconSize = if (text != null) fabExtendedIconSize else fabIconSize
    val paddingValues = when {
        text == null && size == SirioFabSize.REGULAR -> PaddingValues(fabRegularPadding.dp)
        text == null && size == SirioFabSize.SMALL -> PaddingValues(fabSmallPadding.dp)
        else -> PaddingValues(
            horizontal = fabExtendedPaddingHorizontal.dp,
            vertical = fabExtendedPaddingVertical.dp,
        )
    }
    val sizeModifier = when {
        text == null && size == SirioFabSize.SMALL -> Modifier.sizeIn(
            maxWidth = fabSizeSmall.dp,
            maxHeight = fabSizeSmall.dp,
        )

        text == null && size == SirioFabSize.REGULAR -> Modifier.sizeIn(
            maxWidth = fabSizeRegular.dp,
            maxHeight = fabSizeRegular.dp,
        )

        else -> Modifier
    }
    FloatingActionButton(
        onClick = onClick,
        modifier = sizeModifier.then(modifier).testTag("fab${text.takeTwoWords()}"),
        shape = RoundedCornerShape(fabCornerRadius.dp),
        containerColor = backgroundColor,
        contentColor = contentColor,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier.padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioIcon(
                icon = SirioIconSource.FaIcon(icon),
                iconColor = contentColor,
                size = iconSize.dp,
                contentDescription = iconContentDescription,
            )
            text?.let {
                Spacer(modifier = Modifier.width(fabExtendedSpacerWidth.dp))
                SirioTextCommon(
                    text = it,
                    color = contentColor,
                    typography = SirioTheme.foundationTypography.labelMdHeavy,
                )
            }
        }
    }
}

/**
 * Small type reduce internal padding
 */
enum class SirioFabSize {
    REGULAR,
    SMALL,
}

internal val fabLightColors = SirioBaseStateColors(
    container = SirioColorState(
        default = FoundationColor.colorAliasInteractivePrimaryDefault,
        pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
    ),
    content = SirioColorState(
        default = FoundationColor.colorAliasTextColorPrimaryLight0,
        pressed = FoundationColor.colorAliasTextColorPrimaryLight0,
    )
)

internal val fabDarkColors = SirioBaseStateColors(
    container = SirioColorState(
        default = FoundationColor.colorAliasInteractivePrimary000Default,
        pressed = FoundationColor.colorAliasInteractiveAccentDefault,
    ),
    content = SirioColorState(
        default = FoundationColor.colorGlobalDarkPrimary120,
        pressed = FoundationColor.colorGlobalDarkPrimary120,
    )
)

@Preview
@Composable
private fun SirioFabCommonPreview() {
    SirioTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SirioFabCommon(
                onClick = {},
                icon = FaIcons.Plus,
                size = SirioFabSize.REGULAR
            )
            SirioFabCommon(
                onClick = {},
                icon = FaIcons.Plus,
                size = SirioFabSize.SMALL
            )
            SirioFabCommon(
                onClick = {},
                icon = FaIcons.Plus,
                text = "Text",
            )
        }
    }
}

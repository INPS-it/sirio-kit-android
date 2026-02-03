//
// SirioSegmentedButtonCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.segmentedcontrols

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseStateColors
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.buttonIconSize
import it.inps.sirio.theme.segmentedButtonCornerRadius
import it.inps.sirio.theme.segmentedButtonHeight
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

@Composable
internal fun RowScope.SirioSegmentedButtonCommon(
    selected: Boolean,
    text: String? = null,
    icon: SirioIconSource? = null,
    iconContentDescription: String? = null,
    onClick: () -> Unit,
) {
    val contentColor by animateColorAsState(
        targetValue = SirioTheme.colors.segmentedControls.button.content.get(selected, false),
        label = "contentColor"
    )
    val containerColor by animateColorAsState(
        targetValue = SirioTheme.colors.segmentedControls.button.container.get(selected, false),
        label = "containerColor"
    )
    val iconData = if (icon != null)
        SirioIconData(
            icon = icon,
            iconColor = contentColor,
            size = buttonIconSize.dp,
            contentDescription = iconContentDescription,
        ) else null

    require(iconData != null || text != null) { "At least one of text or icon must be non-null" }

    Surface(
        selected = selected,
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .testTag("segmentedButton${text.takeTwoWords()}")
            .height(segmentedButtonHeight.dp)
            .semantics { role = Role.RadioButton },
        enabled = !selected,
        shape = RoundedCornerShape(segmentedButtonCornerRadius.dp),
        color = containerColor,
        contentColor = contentColor
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (text != null) {
                SirioText(
                    text = text,
                    color = contentColor,
                    typography = SirioTheme.foundationTypography.labelMdHeavy,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            } else {
                SirioIcon(iconData = iconData!!)
            }
        }
    }
}

internal val segmentedControlsButtonLightColors = SirioBaseStateColors(
    container = SirioColorState(
        default = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
        pressed = FoundationColor.colorAliasInteractivePrimaryDefault,
    ),
    content = SirioColorState(
        default = FoundationColor.colorAliasTextColorDisabled,
        pressed = FoundationColor.colorAliasInteractivePrimary000Default,
    )
)

internal val segmentedControlsButtonDarkColors = segmentedControlsButtonLightColors

@Preview
@Composable
private fun SirioSegmentedButtonCommonPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(36.dp)) {
            SirioSegmentedButtonCommon(selected = true, text = "Label", onClick = {})
            SirioSegmentedButtonCommon(
                selected = true,
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                onClick = {},
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(36.dp)) {
            SirioSegmentedButtonCommon(selected = false, text = "Label", onClick = {})
            SirioSegmentedButtonCommon(
                selected = false,
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                onClick = {},
            )
        }
    }
}

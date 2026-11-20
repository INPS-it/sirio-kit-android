//
// SirioButtonCommon.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.buttonItemSpacing
import it.inps.sirio.theme.buttonPaddingHorizontal
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.takeTwoWords

@Composable
internal fun ButtonIconOnlyContent(size: SirioButtonSize, icon: SirioIconData) {
    Box(
        modifier = Modifier
            .size(size.toHeight())
            .aspectRatio(1f)
            .testTag("buttonIcon"),
        contentAlignment = Alignment.Center,
    ) {
        SirioIcon(iconData = icon)
    }
}

@Composable
internal fun ButtonWithTextContent(
    text: String,
    icon: SirioIconData?,
    iconPosition: SirioButtonIconPosition,
    contentColor: Color,
    minWidth: Dp,
    height: Dp,
) {
    Row(
        modifier = Modifier
            .height(height)
            .defaultMinSize(minWidth = minWidth)
            .padding(horizontal = buttonPaddingHorizontal.dp)
            .testTag("button${text.takeTwoWords()}"),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null && iconPosition == SirioButtonIconPosition.Left) {
            SirioIcon(iconData = icon)
            Spacer(Modifier.width(buttonItemSpacing.dp))
        }
        SirioText(
            text = text,
            modifier = Modifier.weight(1f, fill = false),
            color = contentColor,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            typography = SirioTheme.foundationTypography.labelMdHeavy
        )
        if (icon != null && iconPosition == SirioButtonIconPosition.Right) {
            Spacer(Modifier.width(buttonItemSpacing.dp))
            SirioIcon(iconData = icon)
        }
    }
}

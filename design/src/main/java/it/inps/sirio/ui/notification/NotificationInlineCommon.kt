//
// NotificationInlineCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.*
import it.inps.sirio.utils.FaIconCentered

/**
 * Inline notification implementation with an icon on the left and the state color,
 * a title and a text in column and the close button
 *
 * @param title The notification title
 * @param text The notification text
 * @param icon The notification icon, placed on the left
 * @param stateColor The notification state color
 * @param onClose The callback when the close button is pressed
 */
@Composable
internal fun NotificationInlineCommon(
    title: String,
    text: String,
    icon: FaIconType,
    stateColor: Color,
    onClose: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(notificationInlineHeight)
            .background(color = SirioTheme.colors.notificationColors.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .width(notificationInlineStateWidth)
                .fillMaxHeight()
                .background(color = stateColor),
            contentAlignment = Alignment.Center,
        ) {
            FaIconCentered(
                icon = icon,
                size = notificationInlineIconSize,
                iconColor = SirioTheme.colors.notificationColors.icon,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = notificationInlineHorizontalPadding,
                    top = notificationInlineVerticalPadding,
                    bottom = notificationInlineVerticalPadding,
                )
        ) {
            Text(
                text = title,
                color = SirioTheme.colors.notificationColors.title,
                style = SirioTheme.typography.notificationInlineTitle,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(notificationInlineVerticalSpacer))
            Text(
                text = text,
                color = SirioTheme.colors.notificationColors.text,
                style = SirioTheme.typography.notificationInlineText,
                maxLines = 1,
            )
        }
        IconButton(onClick = onClose) {
            FaIconCentered(
                icon = FaIcons.Times,
                size = notificationInlineCloseSize,
                iconColor = SirioTheme.colors.notificationColors.icon,
            )
        }
    }
}

@Preview
@Composable
private fun NotificationInlineCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            NotificationInlineCommon(
                title = "Titolo di errore",
                text = text,
                icon = FaIcons.ExclamationTriangle,
                stateColor = SirioTheme.colors.notificationColors.alert,
            ) {}
            NotificationInlineCommon(
                title = "Titolo informativo",
                text = text,
                icon = FaIcons.ExclamationCircle,
                stateColor = SirioTheme.colors.notificationColors.info,
            ) {}
            NotificationInlineCommon(
                title = "Titolo avviso",
                text = text,
                icon = FaIcons.ExclamationCircle,
                stateColor = SirioTheme.colors.notificationColors.warning,
            ) {}
            NotificationInlineCommon(
                title = "Titolo successo",
                text = text,
                icon = FaIcons.Check,
                stateColor = SirioTheme.colors.notificationColors.success,
            ) {}
        }
    }
}
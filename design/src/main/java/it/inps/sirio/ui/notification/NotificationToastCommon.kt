//
// NotificationToastCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.theme.*
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Toast notification implementation with an icon, the state color,
 * a title, a text, an action and the close button
 *
 * @param title The notification title
 * @param text The notification text
 * @param icon The notification FA icon, placed on top
 * @param buttonText The string inside the action button
 * @param stateColor The notification state color, placed on the left
 * @param onAction The callback when the action button is pressed
 * @param onClose The callback when the close button is pressed
 */
@Composable
internal fun NotificationToastCommon(
    title: String,
    text: String,
    icon: FaIconType,
    buttonText: String? = null,
    stateColor: Color,
    onAction: (() -> Unit)? = null,
    onClose: () -> Unit,
) {
    Box(
        Modifier
            .wrapContentSize()
            .background(color = stateColor),
    ) {
        Column(
            modifier = Modifier
                .padding(start = notificationToastStateWidth)
                .wrapContentSize()
                .background(SirioTheme.colors.notificationColors.background)
                .padding(
                    horizontal = notificationToastHorizontalPadding,
                    vertical = notificationToastVerticalPadding,
                )
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SirioIcon(
                    icon = icon,
                    size = notificationToastIconSize,
                    iconColor = SirioTheme.colors.notificationColors.icon,
                )
                IconButton(onClick = onClose) {
                    SirioIcon(
                        icon = FaIcons.Times,
                        size = notificationToastCloseSize,
                        iconColor = SirioTheme.colors.notificationColors.icon,
                    )
                }
            }
            SirioTextCommon(
                text = title,
                modifier = Modifier.wrapContentSize(),
                color = SirioTheme.colors.notificationColors.title,
                maxLines = 1,
                typography = SirioTheme.typography.notificationToastTitle,
            )
            Spacer(modifier = Modifier.height(notificationToastTitleBottomSpacer))
            SirioTextCommon(
                text = text,
                modifier = Modifier.wrapContentSize(),
                color = SirioTheme.colors.notificationColors.text,
                typography = SirioTheme.typography.notificationToastText,
            )
            if (!buttonText.isNullOrEmpty() && onAction != null) {
                Spacer(modifier = Modifier.height(notificationToastTextBottomSpacer))
                SirioButton(
                    text = buttonText,
                    onClick = onAction,
                    size = ButtonSize.Large,
                    style = ButtonStyle.Tertiary
                )
            }
        }
    }
}

@Preview
@Composable
private fun NotificationToastCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val button = "Azione"
            NotificationToastCommon(
                title = "Titolo di errore",
                text = text,
                icon = FaIcons.ExclamationTriangle,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.alert,
                onAction = {},
            ) {}
            NotificationToastCommon(
                title = "Titolo informativo",
                text = text,
                icon = FaIcons.ExclamationCircle,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.info,
                onAction = {},
            ) {}
            NotificationToastCommon(
                title = "Titolo avviso",
                text = text,
                icon = FaIcons.ExclamationCircle,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.warning,
                onAction = {},
            ) {}
            NotificationToastCommon(
                title = "Titolo successo",
                text = text,
                icon = FaIcons.Check,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.success,
                onAction = {},
            ) {}
        }
    }
}

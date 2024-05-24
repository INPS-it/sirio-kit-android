//
// NotificationInlineCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.notificationInlineCloseSize
import it.inps.sirio.theme.notificationInlineHeight
import it.inps.sirio.theme.notificationInlineHorizontalPadding
import it.inps.sirio.theme.notificationInlineIconSize
import it.inps.sirio.theme.notificationInlineStateWidth
import it.inps.sirio.theme.notificationInlineVerticalPadding
import it.inps.sirio.theme.notificationInlineVerticalSpacer
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Inline notification implementation with an icon on the left and the state color,
 * a title and a text in column and the close button
 *
 * @param title The notification title
 * @param text The notification text
 * @param icon The notification icon, placed on the left
 * @param stateColor The notification state color
 * @param closeContentDescription The content description of the close button
 * @param onClose The callback when the close button is pressed
 */
@Composable
internal fun NotificationInlineCommon(
    title: String,
    text: String,
    icon: FaIconType,
    stateColor: Color,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(notificationInlineHeight)
            .background(color = SirioTheme.colors.notificationColors.background)
            .semantics(mergeDescendants = true) {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = title + text,
                        action = {
                            onClose()
                            true
                        }
                    )
                )
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .width(notificationInlineStateWidth)
                .fillMaxHeight()
                .background(color = stateColor),
            contentAlignment = Alignment.Center,
        ) {
            SirioIcon(
                faIcon = icon,
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
            SirioTextCommon(
                text = title,
                color = SirioTheme.colors.notificationColors.title,
                maxLines = 1,
                typography = SirioTheme.typography.notificationInlineTitle,
            )
            Spacer(modifier = Modifier.height(notificationInlineVerticalSpacer))
            SirioTextCommon(
                text = text,
                color = SirioTheme.colors.notificationColors.text,
                maxLines = 1,
                typography = SirioTheme.typography.notificationInlineText,
            )
        }
        IconButton(onClick = onClose) {
            SirioIcon(
                faIcon = FaIcons.Times,
                size = notificationInlineCloseSize,
                iconColor = SirioTheme.colors.notificationColors.icon,
                contentDescription = closeContentDescription,
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

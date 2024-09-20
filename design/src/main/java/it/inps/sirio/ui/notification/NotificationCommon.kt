//
// NotificationCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.notificationInlineCloseSize
import it.inps.sirio.theme.notificationInlineIconPaddingTop
import it.inps.sirio.theme.notificationInlineIconSize
import it.inps.sirio.theme.notificationInlinePaddingHorizontal
import it.inps.sirio.theme.notificationInlinePaddingVertical
import it.inps.sirio.theme.notificationInlineSpacerVertical
import it.inps.sirio.theme.notificationInlineStateWidth
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Inline notification implementation with an icon on the left and the state color,
 * a title and a text in column and the close button
 *
 * @param title The notification title
 * @param text The notification text
 * @param icon The notification icon, placed on the left
 * @param link The notification link, if any
 * @param buttonText The string inside the action button
 * @param stateColor The notification state color
 * @param closeContentDescription The content description of the close button
 * @param onAction The callback when the action button is pressed
 * @param onClose The callback when the close button is pressed
 */
@Composable
internal fun NotificationCommon(
    title: String,
    text: String,
    icon: FaIconType,
    link: String? = null,
    buttonText: String? = null,
    stateColor: Color,
    closeContentDescription: String? = null,
    onLink: (() -> Unit)? = null,
    onAction: (() -> Unit)? = null,
    onClose: () -> Unit,
) {
    Row(
        Modifier
            .height(IntrinsicSize.Min)
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
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            Modifier
                .width(notificationInlineStateWidth.dp)
                .fillMaxHeight()
                .background(color = stateColor)
                .padding(top = notificationInlineIconPaddingTop.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            SirioIcon(
                faIcon = icon,
                size = notificationInlineIconSize.dp,
                iconColor = SirioTheme.colors.notificationColors.icon,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = notificationInlinePaddingHorizontal.dp,
                    top = notificationInlinePaddingVertical.dp,
                    bottom = notificationInlinePaddingVertical.dp,
                )
        ) {
            SirioTextCommon(
                text = title,
                color = SirioTheme.colors.notificationColors.title,
                maxLines = 1,
                typography = SirioTheme.typography.notification.title,
            )
            Spacer(modifier = Modifier.height(notificationInlineSpacerVertical.dp))
            SirioTextCommon(
                text = text,
                color = SirioTheme.colors.notificationColors.text,
                maxLines = 2,
                typography = SirioTheme.typography.notification.text,
            )
            if (!link.isNullOrEmpty() && onLink != null) {
                Spacer(modifier = Modifier.height(notificationInlineSpacerVertical.dp))
                SirioText(
                    text = link,
                    modifier = Modifier.clickable(role = Role.Button, onClick = onLink),
                    color = SirioTheme.colors.notificationColors.link,
                    typography = SirioTheme.typography.notification.link,
                )
            }
            if (!buttonText.isNullOrEmpty() && onAction != null) {
                Spacer(modifier = Modifier.height(notificationInlineSpacerVertical.dp))
                SirioButton(
                    text = buttonText,
                    onClick = onAction,
                    size = ButtonSize.Large,
                    style = ButtonStyle.Tertiary
                )
            }
        }
        IconButton(onClick = onClose) {
            SirioIcon(
                faIcon = FaIcons.Times,
                size = notificationInlineCloseSize.dp,
                iconColor = SirioTheme.colors.notificationColors.icon,
                contentDescription = closeContentDescription,
            )
        }
    }
}

/**
 * Each type has proper icon and colors
 */
@Composable
internal fun paramsByType(type: NotificationType) =
    when (type) {
        NotificationType.Alert -> Pair(
            FaIcons.ExclamationTriangle,
            SirioTheme.colors.notificationColors.alert
        )

        NotificationType.Info -> Pair(
            FaIcons.InfoCircle,
            SirioTheme.colors.notificationColors.info
        )

        NotificationType.Warning -> Pair(
            FaIcons.ExclamationCircle,
            SirioTheme.colors.notificationColors.warning
        )

        NotificationType.Success -> Pair(
            FaIcons.Check,
            SirioTheme.colors.notificationColors.success
        )
    }

/**
 * Both inline and toast notification type
 */
enum class NotificationType {
    Alert,
    Info,
    Warning,
    Success
}

@Keep
data class SirioNotificationTypography(
    val title: TextStyle,
    val text: TextStyle,
    val link: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioNotificationTypography(
            title = TextStyle.Default,
            text = TextStyle.Default,
            link = TextStyle.Default,
        )
    }
}

@Keep
@Immutable
data class NotificationColors(
    val background: Color,
    val title: Color,
    val text: Color,
    val link: Color,
    val icon: Color,
    val close: Color,
    val alert: Color,
    val info: Color,
    val warning: Color,
    val success: Color,
) {
    companion object {
        @Stable
        val Unspecified = NotificationColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            link = Color.Unspecified,
            icon = Color.Unspecified,
            close = Color.Unspecified,
            alert = Color.Unspecified,
            info = Color.Unspecified,
            warning = Color.Unspecified,
            success = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun NotificationInlineCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            NotificationCommon(
                title = "Titolo di Errore",
                text = text,
                icon = FaIcons.ExclamationTriangle,
                stateColor = SirioTheme.colors.notificationColors.alert,
            ) {}
            NotificationCommon(
                title = "Titolo Informativo",
                text = text,
                icon = FaIcons.InfoCircle,
                stateColor = SirioTheme.colors.notificationColors.info,
            ) {}
            NotificationCommon(
                title = "Titolo di Avviso",
                text = text,
                icon = FaIcons.ExclamationCircle,
                stateColor = SirioTheme.colors.notificationColors.warning,
            ) {}
            NotificationCommon(
                title = "Titolo Successo",
                text = text,
                icon = FaIcons.Check,
                stateColor = SirioTheme.colors.notificationColors.success,
            ) {}
        }
    }
}

@Preview
@Composable
private fun NotificationToastCommonPreview() {
    SirioTheme(darkTheme = true) {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val button = "Azione"
            NotificationCommon(
                title = "Titolo di Errore",
                text = text,
                icon = FaIcons.ExclamationTriangle,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.alert,
                onAction = {},
            ) {}
            NotificationCommon(
                title = "Titolo Informativo",
                text = text,
                icon = FaIcons.InfoCircle,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.info,
                onAction = {},
            ) {}
            NotificationCommon(
                title = "Titolo di Avviso",
                text = text,
                icon = FaIcons.ExclamationCircle,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.warning,
                onAction = {},
            ) {}
            NotificationCommon(
                title = "Titolo Successo",
                text = text,
                icon = FaIcons.Check,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.success,
                onAction = {},
            ) {}
        }
    }
}

@Preview
@Composable
private fun NotificationToastLinkCommonPreview() {
    SirioTheme(darkTheme = true) {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val button = "Azione"
            val link = "Testo Link"
            NotificationCommon(
                title = "Titolo di Errore",
                text = text,
                icon = FaIcons.ExclamationTriangle,
                link = link,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.alert,
                onLink = {},
                onAction = {},
                onClose = {},
            )
            NotificationCommon(
                title = "Titolo Informativo",
                text = text,
                icon = FaIcons.ExclamationCircle,
                link = link,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.info,
                onLink = {},
                onAction = {},
                onClose = {},
            )
            NotificationCommon(
                title = "Titolo di Avviso",
                text = text,
                icon = FaIcons.ExclamationCircle,
                link = link,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.warning,
                onLink = {},
                onAction = {},
                onClose = {},
            )
            NotificationCommon(
                title = "Titolo Successo",
                text = text,
                icon = FaIcons.Check,
                link = link,
                buttonText = button,
                stateColor = SirioTheme.colors.notificationColors.success,
                onLink = {},
                onAction = {},
                onClose = {},
            )
        }
    }
}

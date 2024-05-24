//
// NotificationToast.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.notificationToastPadding

/**
 * Toast notification with an icon, the state color, a title, a text, an action and the close button
 *
 * @param title The notification title
 * @param text The notification text
 * @param buttonText The string inside the action button
 * @param onAction The callback when the action button is pressed
 * @param type The type [NotificationType], with related icon and color
 * @param closeContentDescription The content description of the close button
 * @param onClose The callback when the close button is pressed
 */
@Composable
fun NotificationToast(
    title: String,
    text: String,
    buttonText: String? = null,
    onAction: (() -> Unit)? = null,
    type: NotificationType,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    val (icon: FaIconType, color: Color) = paramsByType(type)
    NotificationToastCommon(
        title = title,
        text = text,
        icon = icon,
        buttonText = buttonText,
        stateColor = color,
        closeContentDescription = closeContentDescription,
        onAction = onAction,
        onClose = onClose,
    )
}

/**
 * SnackbarHost to be used with toast notification
 *
 * @param snackbarHostState The notification state
 */
@Composable
fun NotificationToastSnackbarHost(snackbarHostState: SnackbarHostState) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(notificationToastPadding), Alignment.Center
    ) {
        SnackbarHost(
            hostState = snackbarHostState
        ) { data ->
            val notificationToastVisuals = data.visuals as NotificationToastVisuals
            val type = notificationToastVisuals.type

            NotificationToast(
                title = notificationToastVisuals.title,
                text = notificationToastVisuals.message,
                buttonText = notificationToastVisuals.actionLabel,
                onAction = { data.performAction() },
                type = type
            ) { data.dismiss() }
        }
    }
}

/**
 * Object containing notification data
 *
 * @param title The notification title
 * @param message The notification text
 * @param actionLabel The action button text
 * @param duration The duration [SnackbarDuration] of the notification
 * @param type The type [NotificationType]
 */
class NotificationToastVisuals(
    val title: String,
    override val message: String,
    override val actionLabel: String?,
    override val duration: SnackbarDuration = SnackbarDuration.Long,
    val type: NotificationType,
) : SnackbarVisuals {
    override val withDismissAction: Boolean
        get() = true
}


@Preview
@Composable
private fun NotificationToastPreview() {
    SirioTheme {
        Column(
            Modifier.background(Color(0xFFE5E5E5)),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val button = "Azione"
            NotificationToast(
                title = "Titolo di errore",
                text = text,
                buttonText = button,
                onAction = {},
                type = NotificationType.Alert,
            ) {}
            NotificationToast(
                title = "Titolo informativo",
                text = text,
                buttonText = button,
                onAction = {},
                type = NotificationType.Info,
            ) {}
            NotificationToast(
                title = "Titolo avviso",
                text = text,
                buttonText = button,
                onAction = {},
                type = NotificationType.Warning,
            ) {}
            NotificationToast(
                title = "Titolo successo",
                text = text,
                buttonText = button,
                onAction = {},
                type = NotificationType.Success,
            ) {}
        }
    }
}

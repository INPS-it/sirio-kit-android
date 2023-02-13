//
// NotificationInline.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import it.inps.sirio.theme.SirioTheme

/**
 * Inline notification with an icon on the left and the state color,
 * a title and a text in column and the close button
 *
 * @param title The notification title
 * @param text The notification text
 * @param type The type [NotificationType], with related icon and color
 * @param onClose The callback when the close button is pressed
 */
@Composable
fun NotificationInline(
    title: String,
    text: String,
    type: NotificationType,
    onClose: () -> Unit,
) {
    val (icon: FaIconType, color: Color) = paramsByType(type)
    NotificationInlineCommon(
        title = title,
        text = text,
        icon = icon,
        stateColor = color,
        onClose = onClose,
    )
}

/**
 * SnackbarHost to be used with inline notification
 *
 * @param snackbarHostState The notification state
 * @param alignment Where to place the notification. See [Alignment]
 */
@Composable
fun NotificationInlineSnackbarHost(
    snackbarHostState: SnackbarHostState,
    alignment: Alignment = Alignment.BottomCenter,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = alignment) {
        SnackbarHost(hostState = snackbarHostState) { data ->
            val notificationInlineVisuals = data.visuals as NotificationInlineVisuals
            val type = notificationInlineVisuals.type

            NotificationInline(
                title = notificationInlineVisuals.title,
                text = notificationInlineVisuals.message,
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
 * @param actionLabel Not used for inline notification
 * @param duration The duration [SnackbarDuration] of the notification
 * @param type The type [NotificationType]
 */
class NotificationInlineVisuals(
    val title: String,
    override val message: String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Long,
    val type: NotificationType,
) : SnackbarVisuals {
    override val withDismissAction: Boolean
        get() = false
}

@Preview
@Composable
private fun NotificationInlinePreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val button = "Azione"
            NotificationInline(
                title = "Titolo di errore",
                text = text,
                type = NotificationType.Alert,
            ) {}
            NotificationInline(
                title = "Titolo informativo",
                text = text,
                type = NotificationType.Info,
            ) {}
            NotificationInline(
                title = "Titolo avviso",
                text = text,
                type = NotificationType.Warning,
            ) {}
            NotificationInline(
                title = "Titolo successo",
                text = text,
                type = NotificationType.Success,
            ) {}
        }
    }
}

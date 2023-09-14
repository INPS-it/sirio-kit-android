//
// NotificationCommon.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.notification

import androidx.compose.runtime.Composable
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

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
            FaIcons.ExclamationCircle,
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
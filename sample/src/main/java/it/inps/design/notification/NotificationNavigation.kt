//
// NotificationNavigation.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.notification

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.inps.sirio.ui.notification.NotificationType

@Composable
fun NotificationNavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = NotificationDestinations.NOTIFICATION_MENU_ROUTE
    ) {
        composable(NotificationDestinations.NOTIFICATION_MENU_ROUTE) {
            NotificationMenuDemo(navController = navController)
        }
        composable(NotificationDestinations.NOTIFICATION_INLINE_ALERT_ROUTE) {
            NotificationDemo(inline = true, type = NotificationType.Alert)
        }
        composable(NotificationDestinations.NOTIFICATION_INLINE_INFO_ROUTE) {
            NotificationDemo(inline = true, type = NotificationType.Info)
        }
        composable(NotificationDestinations.NOTIFICATION_INLINE_SUCCESS_ROUTE) {
            NotificationDemo(inline = true, type = NotificationType.Success)
        }
        composable(NotificationDestinations.NOTIFICATION_INLINE_WARNING_ROUTE) {
            NotificationDemo(inline = true, type = NotificationType.Warning)
        }
        composable(NotificationDestinations.NOTIFICATION_TOAST_ALERT_ROUTE) {
            NotificationDemo(inline = false, type = NotificationType.Alert)
        }
        composable(NotificationDestinations.NOTIFICATION_TOAST_INFO_ROUTE) {
            NotificationDemo(inline = false, type = NotificationType.Info)
        }
        composable(NotificationDestinations.NOTIFICATION_TOAST_SUCCESS_ROUTE) {
            NotificationDemo(inline = false, type = NotificationType.Success)
        }
        composable(NotificationDestinations.NOTIFICATION_TOAST_WARNING_ROUTE) {
            NotificationDemo(inline = false, type = NotificationType.Warning)
        }
    }
}

object NotificationDestinations {
    const val NOTIFICATION_MENU_ROUTE = "Notification"
    const val NOTIFICATION_MENU_INLINE_ROUTE = "Notification inline"
    const val NOTIFICATION_MENU_TOAST_ROUTE = "Notification toast"
    const val NOTIFICATION_ALERT_ROUTE = "Alert"
    const val NOTIFICATION_INFO_ROUTE = "Info"
    const val NOTIFICATION_WARNING_ROUTE = "Warning"
    const val NOTIFICATION_SUCCESS_ROUTE = "Success"

    const val NOTIFICATION_INLINE_ALERT_ROUTE = "Notification inline Alert"
    const val NOTIFICATION_INLINE_INFO_ROUTE = "Notification inline Info"
    const val NOTIFICATION_INLINE_WARNING_ROUTE = "Notification inline Warning"
    const val NOTIFICATION_INLINE_SUCCESS_ROUTE = "Notification inline Success"
    const val NOTIFICATION_TOAST_ALERT_ROUTE = "Notification toast Alert"
    const val NOTIFICATION_TOAST_INFO_ROUTE = "Notification toast Info"
    const val NOTIFICATION_TOAST_WARNING_ROUTE = "Notification toast Warning"
    const val NOTIFICATION_TOAST_SUCCESS_ROUTE = "Notification toast Success"
}
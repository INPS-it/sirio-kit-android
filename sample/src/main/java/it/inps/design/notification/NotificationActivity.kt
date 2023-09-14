//
// NotificationActivity.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.notification

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.ui.notification.NotificationInline
import it.inps.sirio.ui.notification.NotificationToast
import it.inps.sirio.ui.notification.NotificationType
import it.inps.sirio.theme.SirioTheme
import it.inps.design.ui.DemoMenuItem

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                NotificationDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Notification") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "Notification"
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = title) }, backgroundColor = SirioTheme.colors.brand)
        },
    ) {
        NotificationNavigationGraph(navController = navController)
    }

}

//@Composable
//fun NotificationMenuTypeDemo(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .padding(0.dp, 16.dp)
//            .verticalScroll(rememberScrollState()),
//    ) {
//        DemoMenuItem("Alert") {
//            type = NotificationType.Alert
//            navController.navigate(NotificationDestinations.NOTIFICATION_ALERT_ROUTE)
//        }
//        Divider()
//        DemoMenuItem("Info") {
//            type = NotificationType.Info
//            navController.navigate(NotificationDestinations.NOTIFICATION_INFO_ROUTE)
//        }
//        Divider()
//        DemoMenuItem("Warning") {
//            type = NotificationType.Warning
//            navController.navigate(NotificationDestinations.NOTIFICATION_WARNING_ROUTE)
//        }
//        Divider()
//        DemoMenuItem("Success") {
//            type = NotificationType.Success
//            navController.navigate(NotificationDestinations.NOTIFICATION_SUCCESS_ROUTE)
//        }
//    }
//}

@Composable
fun NotificationDemo(inline: Boolean, type: NotificationType) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        val button = "Azione"
        val title = "Titolo"
        if (inline) {
            NotificationInline(
                title = title,
                text = text,
                type = type,
            ) {}
        } else {
            NotificationToast(
                title = title,
                text = text,
                buttonText = button,
                onAction = {},
                type = type,
            ) {}
            NotificationToast(
                title = title,
                text = text,
                type = type,
            ) {}
        }
    }
}

@Composable
fun NotificationMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem(NotificationDestinations.NOTIFICATION_INLINE_ALERT_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_INLINE_ALERT_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_INLINE_INFO_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_INLINE_INFO_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_INLINE_SUCCESS_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_INLINE_SUCCESS_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_INLINE_WARNING_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_INLINE_WARNING_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_TOAST_ALERT_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_TOAST_ALERT_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_TOAST_INFO_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_TOAST_INFO_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_TOAST_SUCCESS_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_TOAST_SUCCESS_ROUTE)
        }
        Divider()
        DemoMenuItem(NotificationDestinations.NOTIFICATION_TOAST_WARNING_ROUTE) {
            navController.navigate(NotificationDestinations.NOTIFICATION_TOAST_WARNING_ROUTE)
        }
        Divider()
    }
}

@Preview
@Composable
private fun NotificationDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        NotificationDemo(inline = true, type = NotificationType.Alert)
        NotificationDemo(inline = false, type = NotificationType.Alert)
    }
}
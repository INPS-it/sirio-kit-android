//
// NotificationActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.notifiche

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.ui.notification.SirioNotificaInLinea
import it.inps.sirio.ui.notification.SirioNotificaPagina
import it.inps.sirio.ui.notification.SirioNotificaPaginaDialog
import it.inps.sirio.ui.notification.SirioNotificaState
import it.inps.sirio.ui.text.SirioText

class NotificheActivity : ComponentActivity() {
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
    var title by remember { mutableStateOf(NotificheDestinations.NOTIFICHE_MENU_ROUTE) }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: NotificheDestinations.NOTIFICHE_MENU_ROUTE
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            NotificationNavigationGraph(navController = navController)
        }
    }
}

@Composable
fun NotifichePaginaDemo() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(8.dp, 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        val title = "Titolo notifica"
        val text =
            "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor."
        val link = "Link opzionale"
        var show by remember { mutableStateOf<SirioNotificaState?>(null) }
        SirioText("Alert")
        SirioNotificaPagina(
            state = SirioNotificaState.Alert,
            onClose = {},
            title = title,
            text = text,
            link = link,
            onLinkClick = { show = SirioNotificaState.Alert }
        )
        SirioText("Info")
        SirioNotificaPagina(
            state = SirioNotificaState.Info,
            onClose = {},
            title = title,
            text = text,
            link = link,
            onLinkClick = { show = SirioNotificaState.Info }
        )
        SirioText("Warning")
        SirioNotificaPagina(
            state = SirioNotificaState.Warning,
            onClose = {},
            title = title,
            text = text,
            link = link,
            onLinkClick = { show = SirioNotificaState.Warning }
        )
        SirioText("Success")
        SirioNotificaPagina(
            state = SirioNotificaState.Success,
            onClose = {},
            title = title,
            text = text,
            link = link,
            onLinkClick = { show = SirioNotificaState.Success }
        )
        show?.let {
            SirioNotificaPaginaDialog(
                state = it,
                title = title,
                text = text,
                onClose = { show = null }
            )
        }
    }
}

@Composable
fun NotificheInLineaDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp, 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        val title = "Titolo notifica"
        val text =
            "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor."
        val link = "Link opzionale"
        SirioText("Alert")
        SirioNotificaInLinea(
            state = SirioNotificaState.Alert,
            onClose = {},
            title = title,
            text = text,
            link = link
        )
        SirioText("Info")
        SirioNotificaInLinea(
            state = SirioNotificaState.Info,
            onClose = {},
            title = title,
            text = text,
            link = link
        )
        SirioText("Warning")
        SirioNotificaInLinea(
            state = SirioNotificaState.Warning,
            onClose = {},
            title = title,
            text = text,
            link = link
        )
        SirioText("Success")
        SirioNotificaInLinea(
            state = SirioNotificaState.Success,
            onClose = {},
            title = title,
            text = text,
            link = link
        )
    }
}

@Composable
fun NotificheMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 16.dp),
    ) {
        SirioListItem(NotificheDestinations.NOTIFICHE_IN_LINEA_ROUTE) {
            navController.navigate(NotificheDestinations.NOTIFICHE_IN_LINEA_ROUTE)
        }
        SirioListItem(NotificheDestinations.NOTIFICHE_PAGINA_ROUTE, showDivider = false) {
            navController.navigate(NotificheDestinations.NOTIFICHE_PAGINA_ROUTE)
        }
    }
}

@Composable
fun NotificationNavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = NotificheDestinations.NOTIFICHE_MENU_ROUTE
    ) {
        composable(NotificheDestinations.NOTIFICHE_MENU_ROUTE) {
            NotificheMenuDemo(navController)
        }
        composable(NotificheDestinations.NOTIFICHE_PAGINA_ROUTE) {
            NotifichePaginaDemo()
        }
        composable(NotificheDestinations.NOTIFICHE_IN_LINEA_ROUTE) {
            NotificheInLineaDemo()
        }
    }
}

object NotificheDestinations {
    const val NOTIFICHE_MENU_ROUTE = "Notifiche"
    const val NOTIFICHE_PAGINA_ROUTE = "Notifiche pagina"
    const val NOTIFICHE_IN_LINEA_ROUTE = "Notifiche in linea"
}

@Preview
@Composable
private fun NotificheDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        NotificheInLineaDemo()
        NotifichePaginaDemo()
    }
}

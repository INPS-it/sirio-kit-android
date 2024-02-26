//
// NotificationToastActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.notification.NotificationToast
import it.inps.sirio.ui.notification.NotificationToastSnackbarHost
import it.inps.sirio.ui.notification.NotificationToastVisuals
import it.inps.sirio.ui.notification.NotificationType
import kotlinx.coroutines.launch

class NotificationToastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                NotificationToastDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationToastDemoContent() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification Toast") }, backgroundColor = SirioTheme.colors.brand,
            )
        },
        snackbarHost = {
            NotificationToastSnackbarHost(snackbarHostState)
        },
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val button = "Azione"
            NotificationToast(
                title = "Titolo di errore",
                text = text,
                buttonText = button,
                onAction = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            NotificationToastVisuals(
                                "Titolo di errore",
                                message = "Lorem ipsum",
                                actionLabel = "Azione",
                                type = NotificationType.Alert,
                            )
                        )
                    }
                },
                type = NotificationType.Alert,
            ) {}
            NotificationToast(
                title = "Titolo di errore",
                text = text,
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
                title = "Titolo informativo",
                text = text,
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
                title = "Titolo avviso",
                text = text,
                type = NotificationType.Warning,
            ) {}
            NotificationToast(
                title = "Titolo successo",
                text = text,
                buttonText = button,
                onAction = {},
                type = NotificationType.Success,
            ) {}
            NotificationToast(
                title = "Titolo successo",
                text = text,
                type = NotificationType.Success,
            ) {}
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun NotificationToastActivityPreview() {
    SirioTheme {
        NotificationToastDemoContent()
    }
}

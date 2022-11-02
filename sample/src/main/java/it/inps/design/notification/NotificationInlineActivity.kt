//
// NotificationInlineActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.ui.notification.NotificationInline
import it.inps.sirio.ui.notification.NotificationInlineSnackbarHost
import it.inps.sirio.ui.notification.NotificationInlineVisuals
import it.inps.sirio.ui.notification.NotificationType
import it.inps.sirio.theme.SirioTheme
import kotlinx.coroutines.launch

class NotificationInlineActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                NotificationInlineDemoContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationInlineDemoContent() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification inline") }, backgroundColor = SirioTheme.colors.brand,
            )
        },
        snackbarHost = { NotificationInlineSnackbarHost(snackbarHostState) },
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
            NotificationInline(
                title = "Titolo di errore",
                text = text,
                type = NotificationType.Alert,
            ) {
                scope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        NotificationInlineVisuals(
                            "Titolo di errore",
                            message = "Lorem ipsum",
                            actionLabel = "Azione",
                            type = NotificationType.Alert,
                        )
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> {}
                        SnackbarResult.ActionPerformed -> {}
                    }
                }
            }
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

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun NotificationInlineActivityPreview() {
    SirioTheme {
        NotificationInlineDemoContent()
    }
}
//
// ToggleActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.toggle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.toggle.Toggle

class ToggleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                ToggleDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ToggleDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Toggle") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "Toggle"
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            var isOn1 by remember { mutableStateOf(false) }
            var isOn2 by remember { mutableStateOf(true) }
            var isOn3 by remember { mutableStateOf(false) }
            var isOn4 by remember { mutableStateOf(true) }
            val label = "Title"
            Text(text = "Toggle Only False")
            Toggle(isOn = isOn1, onToggleChange = { isOn1 = it })
            Toggle(isOn = false, onToggleChange = {}, enabled = false)
            Text(text = "Toggle Only True")
            Toggle(isOn = isOn2, onToggleChange = { isOn2 = it })
            Toggle(isOn = true, onToggleChange = {}, enabled = false)
            Text(text = "Toggle Text False")
            Toggle(label = label, isOn = isOn3, onToggleChange = { isOn3 = it })
            Toggle(label = label, isOn = false, onToggleChange = {}, enabled = false)
            Text(text = "Toggle Text True")
            Toggle(label = label, isOn = isOn4, onToggleChange = { isOn4 = it })
            Toggle(label = label, isOn = true, onToggleChange = {}, enabled = false)
        }
    }
}

@Preview
@Composable
private fun ToggleDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SirioTheme {
            ToggleDemoView()
        }
    }
}

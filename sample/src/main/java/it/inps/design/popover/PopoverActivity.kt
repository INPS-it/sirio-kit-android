//
// PopoverActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.popover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.ui.popover.SirioPopover
import it.inps.sirio.ui.popover.SirioPopoverData
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.textfield.SirioTextField

private const val POPOVER = "Popover"
private const val DEMO = "Demo"

class PopoverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                PopoverDemoView()
            }
        }
    }
}

@Composable
private fun PopoverDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Popover") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "/",
            modifier = Modifier.padding(it),
        ) {
            composable("/") {
                PopoverMenuDemo(navController = navController)
            }
            composable(POPOVER) {
                PopoverComponents()
            }
            composable(DEMO) {
                PopoverDemo()
            }
        }
    }
}

@Composable
fun PopoverMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        SirioListItem(POPOVER) {
            navController.navigate(POPOVER)
        }
        SirioListItem(DEMO) {
            navController.navigate(DEMO)
        }
    }
}

@Composable
private fun PopoverComponents() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioText("Text")
        SirioPopover(text = "Lorem ipsum sed eu consequat cras vitae eros.")
        SirioText("Title + Text")
        SirioPopover(title = "Titolo", text = "Lorem ipsum sed eu consequat cras vitae eros.")
        SirioText("Title + Text + Action")
        SirioPopover(
            title = "Titolo",
            text = "Lorem ipsum sed eu consequat cras vitae eros.",
            buttonText = "Azione"
        )
    }
}

@Composable
private fun PopoverDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        SirioTextField(
            label = "Label",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(
                title = "Title",
                text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga"
            )
        )
        SirioTextField(
            label = "Label lorem ipsum dolor sit amet",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(
                title = "Title",
                text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga"
            )
        )
        SirioTextField(
            label = "Label",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga")
        )
        SirioTextField(
            label = "Label lorem ipsum dolor sit amet",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga")
        )
        SirioTextField(
            label = "Label",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(text = "Testo descrizione")
        )
        SirioTextField(
            label = "Label lorem ipsum dolor sit amet",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(text = "Testo descrizione")
        )
        SirioTextField(
            label = "Label",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga e spazio insufficente per essere mostrato sotto la label")
        )
        SirioTextField(
            label = "Label lorem ipsum dolor sit amet",
            text = "Text",
            onValueChange = {},
            popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga e spazio insufficente per essere mostrato sotto la label")
        )
    }
}

@Preview
@Composable
private fun PopoverComponentsPreview() {
    SirioTheme {
        PopoverComponents()
    }
}

@Preview
@Composable
private fun PopoverDemoPreview() {
    SirioTheme {
        PopoverDemo()
    }
}
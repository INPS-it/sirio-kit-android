//
// LoaderActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.loader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.appnavigation.SirioAppNavigation
import it.inps.sirio.ui.appnavigation.SirioAppNavigationItemData
import it.inps.sirio.ui.appnavigation.SirioFunction
import it.inps.sirio.ui.dropdown.SirioDropdown
import it.inps.sirio.ui.loader.SirioLoaderView
import it.inps.sirio.ui.popover.SirioPopoverData
import it.inps.sirio.ui.table.SirioTableTitle
import it.inps.sirio.ui.textfield.SirioTextField

class LoaderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SirioTheme {
                LoaderDemo()
            }
        }
    }
}

@Composable
private fun LoaderDemo() {
    Scaffold(
        topBar = {
            SirioAppNavigation(
                title = "Titolo pagina",
                leftItem = SirioAppNavigationItemData(
                    icon = FaIcons.ChevronLeft,
                    contentDescription = "Indietro",
                    action = {},
                ),
                rightFirstItem = SirioAppNavigationItemData(
                    icon = FaIcons.User,
                    action = {},
                ),
                rightSecondItem = SirioAppNavigationItemData(
                    icon = FaIcons.Bell,
                    action = {},
                ),
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            SirioFunction("Titolo funzione", theme = SirioThemeMode.Dark)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                SirioTableTitle("Title section")
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SirioTextField(label = "Label", text = "Text", onValueChange = {})
                    SirioTextField(
                        label = "Label",
                        text = "Text",
                        onValueChange = {},
                        popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga")
                    )
                    SirioDropdown(
                        values = listOf("Value 1", "Value 2", "Value 3"),
                        selectedValue = "Text",
                        label = "Label",
                        onValueChange = {},
                    )
                }
                SirioTableTitle("Title section")
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SirioTextField(label = "Label", text = "Text", onValueChange = {})
                    SirioTextField(
                        label = "Label lorem ipsum dolor sit amet",
                        text = "Text",
                        onValueChange = {},
                        popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga")
                    )
                    SirioDropdown(
                        values = listOf("Value 1", "Value 2", "Value 3"),
                        selectedValue = "Text",
                        label = "Label",
                        onValueChange = {},
                    )
                }
                SirioTableTitle("Title section")
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SirioTextField(label = "Label", text = "Text", onValueChange = {})
                    SirioTextField(
                        label = "Label con testo",
                        text = "Text",
                        onValueChange = {},
                        popoverData = SirioPopoverData(text = "Testo descrizione")
                    )
                    SirioDropdown(
                        values = listOf("Value 1", "Value 2", "Value 3"),
                        selectedValue = "Text",
                        label = "Label",
                        onValueChange = {},
                    )
                }
                SirioTableTitle("Title section")
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SirioTextField(label = "Label", text = "Text", onValueChange = {})
                    SirioTextField(
                        label = "Label lorem ipsum dolor sit amet ",
                        text = "Text",
                        onValueChange = {},
                        popoverData = SirioPopoverData(text = "Testo descrizione")
                    )
                    SirioDropdown(
                        values = listOf("Value 1", "Value 2", "Value 3"),
                        selectedValue = "Text",
                        label = "Label",
                        onValueChange = {},
                    )
                }
                SirioTableTitle("Title section")
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SirioTextField(label = "Label", text = "Text", onValueChange = {})
                    SirioTextField(
                        label = "Label",
                        text = "Text",
                        onValueChange = {},
                        popoverData = SirioPopoverData(text = "Questo caso d’uso illustra il comportamento del popover con una descrizione lunga e spazio insufficente per essere mostrato sotto la label")
                    )
                }
            }
        }
    }
    SirioLoaderView()
}

@Preview
@Composable
private fun LoaderDemoPreview() {
    SirioTheme {
        LoaderDemo()
    }
}
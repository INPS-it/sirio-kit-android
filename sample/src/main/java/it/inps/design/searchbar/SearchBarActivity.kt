//
// SearchBarActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.searchbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.ui.searchbar.SirioSearchList
import it.inps.sirio.ui.searchbar.SirioSearchListAsync

private const val LIST = "List"
private const val NO_LIST = "No list"

class SearchBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                SearchBarDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBarDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "/",
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            composable("/") {
                SearchListMenuDemo(navController = navController)
            }
            composable(LIST) {
                SearchListDemo()
            }
            composable(NO_LIST) {
                SearchListAsyncDemo()
            }
        }
    }
}

@Composable
fun SearchListMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        SirioListItem(LIST) {
            navController.navigate(LIST)
        }
        SirioListItem(NO_LIST) {
            navController.navigate(NO_LIST)
        }
    }
}

@Composable
private fun SearchListDemo() {
    Column(
        modifier = Modifier.background(Color.White),
    ) {
        val items = listOf(
            "Vitae elit, dolor sit amet" to "A",
            "Eiusmod tempor incididunt" to "B",
            "Minim veniam, exercitation laboris" to "C",
            "Irure dolor in voluptate" to "A",
            "Sint occaecat cupidatat" to "B",
            "Pretium tincidunt" to "C",
            "Quis ante" to "A",
            "Sit amet orci" to "B",
            "Amministratio Perspicua" to "C",
            "Fringilla sit amet" to "A",
            "A est" to "C",
        )
        var selectedFilters by remember { mutableStateOf(setOf<String>()) }
        SirioSearchList(
            items = if (selectedFilters.isEmpty()) items
            else items.filter { selectedFilters.contains(it.second) },
            itemContent = { isLastItem, item ->
                SirioListItem(
                    title = item.first,
                    description = item.second,
                    showDivider = !isLastItem,
                    onClick = {},
                )
            },
            onFilter = { item, filter -> item.first.contains(filter, true) },
            placeholder = "Inserisci il testo",
            filters = listOf("A", "B", "C"),
            onFilterSelectionChanged = { selectedFilters = it },
        )
    }
}

@Composable
private fun SearchListAsyncDemo() {
    Column(
        modifier = Modifier.background(Color.White),
    ) {
        val options = listOf(
            "Pensione",
            "Pensioni: ricostituzione per variazione dati supplemento",
            "Pensionati: il cedolino di gennaio",
            "Simulazione pensione",
        )
        var searchText by remember { mutableStateOf("") }
        val itemsToShow = if (searchText.isNotBlank()) {
            options.filter {
                it.contains(searchText, true)
            }
        } else emptyList()

        SirioSearchListAsync(
            items = itemsToShow,
            searchText = searchText,
            label = "Label",
            placeholder = "Inserisci il testo",
            optionValues = options,
            onItemClick = {},
            onSearch = { searchText = it }
        )
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun SearchBarActivityPreview() {
    SirioTheme {
        SearchBarDemoContent()
    }
}

@Preview
@Composable
private fun SearchListDemoPreview() {
    SirioTheme {
        SearchListDemo()
    }
}

@Preview
@Composable
private fun SearchListAsyncDemoPreview() {
    SirioTheme {
        SearchListAsyncDemo()
    }
}

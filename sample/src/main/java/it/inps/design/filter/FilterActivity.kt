//
// FilterActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.filter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.filter.SirioFilterCheckbox
import it.inps.sirio.ui.filter.SirioFilterChips
import it.inps.sirio.ui.filter.SirioFilterChipsWrap
import it.inps.sirio.ui.filter.SirioFilterFooter
import it.inps.sirio.ui.filter.SirioFilterHeader
import it.inps.sirio.ui.filter.SirioFilterInput
import it.inps.sirio.ui.filter.SirioFilterRadio
import it.inps.sirio.ui.filter.SirioFilterSelected
import it.inps.sirio.ui.filter.SirioFilterTabs
import it.inps.sirio.ui.filter.SirioFilterTitle
import it.inps.sirio.ui.filter.SirioFilterToggle
import it.inps.sirio.ui.tabs.TabItemData

private const val SELECTED = "Selected"
private const val DRAWER = "Drawer"
private const val TABS = "Tabs"

class FilterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                FilterDemoView()
            }
        }
    }
}

@Composable
private fun FilterDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Filtri") },
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
                FilterMenuDemo(navController = navController)
            }
            composable(SELECTED) {
                FilterSelectedDemo()
            }
            composable(DRAWER) {
                FilterDrawerDemo()
            }
            composable(TABS) {
                FilterTabsDemo()
            }
        }
    }
}

@Composable
fun FilterMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem(SELECTED) {
            navController.navigate(SELECTED)
        }
        HorizontalDivider()
        DemoMenuItem(DRAWER) {
            navController.navigate(DRAWER)
        }
        HorizontalDivider()
        DemoMenuItem(TABS) {
            navController.navigate(TABS)
        }
    }
}

@Composable
private fun FilterSelectedDemo() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Selected")
        val state = remember {
            mutableStateListOf(
                elements = arrayOf(
                    "Valore A",
                    "Valore B",
                    "Valore C",
                    "Valore D",
                    "Valore E"
                )
            )
        }
        SirioFilterSelected(
            values = state,
            onDeleteValue = { state.remove(it) }
        )
    }
}

@Composable
private fun FilterDrawerDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .background(StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 20.dp, horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(31.dp)
    ) {
        var radio by remember { mutableStateOf(false) }
        var checkbox by remember { mutableStateOf(false) }
        var toggle by remember { mutableStateOf(false) }
        val chips = remember { mutableStateListOf(elements = arrayOf<String>()) }
        val chipsWrap = remember { mutableStateListOf(elements = arrayOf<String>()) }
        var input by remember { mutableStateOf("") }
        SirioFilterHeader(title = "Filtri", onClose = {})
        SirioFilterRadio(selected = radio, text = "Title", onClick = { radio = !radio })
        SirioFilterCheckbox(checked = checkbox, text = "Title", onCheckedChange = { checkbox = it })
        SirioFilterToggle(isOn = toggle, text = "Title", onToggleChange = { toggle = it })
        SirioFilterTitle(text = "Section Title #")
        SirioFilterChips(
            texts = listOf(
                "Valore 1",
                "Valore 2",
                "Valore 3",
                "Valore 4",
                "Valore 5",
                "Valore 6"
            ),
            selectedTexts = chips,
            onSelectedChanges = {
                chips.clear()
                chips.addAll(it)
            }
        )
        SirioFilterChipsWrap(
            texts = listOf(
                "Valore 1",
                "Valore 2",
                "Valore 3",
                "Valore 4",
                "Valore 5",
                "Valore 6"
            ),
            selectedTexts = chipsWrap,
            onSelectedChanges = {
                chipsWrap.clear()
                chipsWrap.addAll(it)
            },
        )
        SirioFilterInput(
            label = "Label",
            text = input,
            placeholder = "Text",
            onValueChange = { input = it },
        )
        SirioFilterFooter(
            neutralText = "Elimina filtri",
            positiveText = "Applica filtri",
            onNeutral = {},
            onPositive = {}
        )
    }
}

@Composable
private fun FilterTabsDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .background(StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 20.dp, horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(31.dp)
    ) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        SirioFilterTabs(
            items = listOf(
                TabItemData("Label tab 0"),
                TabItemData("Label tab 1"),
                TabItemData("Label tab 2"),
                TabItemData("Label tab 3"),
                TabItemData("Label tab 4"),
            ),
            selectedIndex = selectedTabIndex,
            withScroll = true,
            onTabSelected = { selectedTabIndex = it }
        )
        SirioFilterTabs(
            items = listOf(
                TabItemData("Label tab 0"),
                TabItemData("Label tab 1"),
                TabItemData("Label tab 2"),
                TabItemData("Label tab 3"),
                TabItemData("Label tab 4"),
            ),
            selectedIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it }
        )
        FilterTabDemoContent(selectedTabIndex)
        SirioFilterFooter(
            neutralText = "Elimina",
            positiveText = "Applica",
            onNeutral = {},
            onPositive = {}
        )
    }
}

@Composable
private fun FilterTabDemoContent(i: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(31.dp)) {
        val radio = remember {
            mutableStateListOf(elements = arrayOf(false, false, false, false, false))
        }
        val checkbox = remember {
            mutableStateListOf(elements = arrayOf(false, false, false, false, false))
        }
        val toggle = remember {
            mutableStateListOf(elements = arrayOf(false, false, false, false, false))
        }
        val input = remember {
            mutableStateListOf(elements = arrayOf("", "", "", "", ""))
        }
        SirioFilterInput(
            label = "Label $i",
            text = input[i],
            placeholder = "Text $i",
            onValueChange = { input[i] = it },
        )
        SirioFilterCheckbox(
            checked = checkbox[i],
            text = "Title $i",
            onCheckedChange = { checkbox[i] = it })
        SirioFilterRadio(selected = radio[i], text = "Title $i", onClick = { radio[i] = !radio[i] })
        SirioFilterToggle(isOn = toggle[i], text = "Title $i", onToggleChange = { toggle[i] = it })
    }
}

@Preview
@Composable
private fun FilterActivityPreview() {
    SirioTheme {
        FilterDemoView()
    }
}

@Preview
@Composable
private fun FilterTabsDemoPreview() {
    SirioTheme {
        FilterTabsDemo()
    }
}
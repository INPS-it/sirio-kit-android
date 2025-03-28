//
// TabActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.tabs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.tabs.SirioTab
import it.inps.sirio.ui.tabs.SirioTabGroup
import it.inps.sirio.ui.tabs.TabItemData
import it.inps.sirio.ui.tabs.TabSelectionIndicatorPosition

class TabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                TabDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Tab") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "Tab"
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            TabNavigationGraph(navController = navController)
        }
    }
}

@Composable
fun TabMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem(TabDestinations.TAB_SINGLE) {
            navController.navigate(TabDestinations.TAB_SINGLE)
        }
        HorizontalDivider()
        DemoMenuItem(TabDestinations.TAB_GROUP) {
            navController.navigate(TabDestinations.TAB_GROUP)
        }
        HorizontalDivider()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabDemoSingle() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E5E5))
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Text(text = "Selected - Top")
        SirioTab(
            label = "Tab",
            icon = FaIcons.Check,
            enabled = true,
            selected = true,
            selection = TabSelectionIndicatorPosition.TOP,
            onSelect = {},
        )
        Text(text = "Default - Top")
        SirioTab(
            label = "Tab",
            icon = FaIcons.Check,
            enabled = true,
            selected = false,
            selection = TabSelectionIndicatorPosition.TOP,
            onSelect = {},
        )
        Text(text = "Disabled - Top")
        SirioTab(
            label = "Tab",
            icon = FaIcons.Check,
            enabled = false,
            selected = false,
            selection = TabSelectionIndicatorPosition.TOP,
            onSelect = {},
        )
        Text(text = "Selected - Bottom")
        SirioTab(
            label = "Tab",
            icon = FaIcons.Check,
            enabled = true,
            selected = true,
            selection = TabSelectionIndicatorPosition.BOTTOM,
            onSelect = {},
        )
        Text(text = "Default - Bottom")
        SirioTab(
            label = "Tab",
            icon = FaIcons.Check,
            enabled = true,
            selected = false,
            selection = TabSelectionIndicatorPosition.BOTTOM,
            onSelect = {},
        )
        Text(text = "Disabled - Bottom")
        SirioTab(
            label = "Tab",
            icon = FaIcons.Check,
            enabled = false,
            selected = false,
            selection = TabSelectionIndicatorPosition.BOTTOM,
            onSelect = {},
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabDemoGroup() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        var selectedIndexTop by remember { mutableIntStateOf(0) }
        var selectedIndexBottom by remember { mutableIntStateOf(0) }
        Text(text = "Top")
        SirioTabGroup(
            items = listOf(
                TabItemData(
                    label = "Label tab 0",
                    icon = FaIcons.Check,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 1",
                    icon = FaIcons.File,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 2",
                    icon = FaIcons.GlobeAfrica,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 3",
                    icon = FaIcons.Lightbulb,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 4",
                    icon = FaIcons.PiggyBank,
                    enabled = true
                ),
            ),
            selection = TabSelectionIndicatorPosition.TOP,
            selectedIndex = selectedIndexTop,
            onTabSelected = { selectedIndexTop = it },
        )
        Text(text = "Bottom")
        SirioTabGroup(
            items = listOf(
                TabItemData(
                    label = "Label tab 0",
                    icon = FaIcons.Check,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 1",
                    icon = FaIcons.File,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 2",
                    icon = FaIcons.GlobeAfrica,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 3",
                    icon = FaIcons.Lightbulb,
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 4",
                    icon = FaIcons.PiggyBank,
                    enabled = true
                ),
            ),
            selection = TabSelectionIndicatorPosition.BOTTOM,
            selectedIndex = selectedIndexBottom,
            onTabSelected = { selectedIndexBottom = it },
        )
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun TabActivityPreview() {
    SirioTheme {
        Column {
            TabDemoGroup()
            TabDemoSingle()
        }
    }
}
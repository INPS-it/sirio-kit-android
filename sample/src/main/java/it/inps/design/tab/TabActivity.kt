//
// TabActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.tab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import it.inps.sirio.ui.tab.SirioTab
import it.inps.sirio.ui.tab.SirioTabGroup
import it.inps.sirio.ui.tab.TabItemData
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource

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
            .fillMaxSize()
            .background(Color.White)
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
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Spacer(Modifier.height(10.dp))
        Text(text = "Selected")
        SirioTab(
            label = "Tab",
            icon = SirioIconSource.FaIcon(FaIcons.Cube),
            enabled = true,
            selected = true,
            onSelect = {},
        )
        Text(text = "Default")
        SirioTab(
            label = "Tab",
            icon = SirioIconSource.FaIcon(FaIcons.Cube),
            enabled = true,
            selected = false,
            onSelect = {},
        )
        Text(text = "Disabled")
        SirioTab(
            label = "Tab",
            icon = SirioIconSource.FaIcon(FaIcons.Cube),
            enabled = false,
            selected = false,
            onSelect = {},
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabDemoGroup() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var selectedIndex by remember { mutableIntStateOf(0) }
        SirioTabGroup(
            items = listOf(
                TabItemData(
                    label = "Label tab 0",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 1",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 2",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = false
                ),
                TabItemData(
                    label = "Label tab 3",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = true
                ),
                TabItemData(
                    label = "Label tab 4",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    enabled = true
                ),
            ),
            selectedIndex = selectedIndex,
            onTabSelected = { selectedIndex = it },
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            SirioText("Tab $selectedIndex")
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun TabActivityPreview() {
    SirioTheme {
        Column {
            TabDemoGroup()
        }
    }
}
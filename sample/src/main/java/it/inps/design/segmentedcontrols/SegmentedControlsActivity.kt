//
// SegmentedControlsActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.segmentedcontrols

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.segmentedcontrols.SirioSegmentedControls
import it.inps.sirio.utils.SirioIconSource

private const val LABEL = "Label"
private const val ICON = "Icon"

class SegmentedControlsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SirioTheme {
                SegmentedControlsDemoView()
            }
        }
    }
}

@Composable
private fun SegmentedControlsDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Segmented controls") },
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
                SegmentedControlsMenuDemo(navController = navController)
            }
            composable(LABEL) {
                SirioSegmentedControlsSectionDemo(true)
            }
            composable(ICON) {
                SirioSegmentedControlsSectionDemo(false)
            }
        }
    }
}

@Composable
private fun SegmentedControlsMenuDemo(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            DemoMenuItem(LABEL) {
                navController.navigate(LABEL)
            }
            HorizontalDivider()
            DemoMenuItem(ICON) {
                navController.navigate(ICON)
            }
            HorizontalDivider()
        }
    }
}

@Composable
private fun SirioSegmentedControlsSectionDemo(text: Boolean) {
    SirioTheme {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            var selectedIndex by remember { mutableIntStateOf(0) }
            if (text)
                SirioSegmentedControls(
                    labels = List(5) { "Label" },
                    selectedIndex = selectedIndex,
                    onSelectionChanged = { selectedIndex = it }
                )
            else
                SirioSegmentedControls(
                    icons = List(5) { SirioIconSource.FaIcon(FaIcons.Cube) },
                    selectedIndex = selectedIndex,
                    onSelectionChanged = { selectedIndex = it }
                )
            SirioSegmentedControlsSectionDemo(2, text)
            SirioSegmentedControlsSectionDemo(3, text)
            SirioSegmentedControlsSectionDemo(4, text)
            SirioSegmentedControlsSectionDemo(5, text)
        }
    }
}


@Composable
private fun SirioSegmentedControlsSectionDemo(elements: Int, text: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (i in 0 until elements) {
            if (text)
                SirioSegmentedControls(
                    labels = List(elements) { "Label" },
                    selectedIndex = i,
                    onSelectionChanged = { }
                )
            else
                SirioSegmentedControls(
                    icons = List(elements) { SirioIconSource.FaIcon(FaIcons.Cube) },
                    selectedIndex = i,
                    onSelectionChanged = { }
                )
        }
    }
}

@Preview
@Composable
private fun StepProgressBarActivityPreview() {
    SirioTheme {
        SegmentedControlsDemoView()
    }
}
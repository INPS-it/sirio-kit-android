//
// StepProgressBarActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.stepprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.stepprogressbar.SirioStepControls
import it.inps.sirio.ui.stepprogressbar.SirioStepSelection

private const val DEMO = "Demo"

class StepProgressBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                StepProgressBarDemoView()
            }
        }
    }
}

@Composable
private fun StepProgressBarDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Step progress bar") },
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
                StepProgressBarMenuDemo(navController = navController)
            }
            composable(DEMO) {
                StepProgressBarDemo()
            }
        }
    }
}

@Composable
private fun StepProgressBarMenuDemo(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            DemoMenuItem(DEMO) {
                navController.navigate(DEMO)
            }
            HorizontalDivider()
        }
    }
}

@Composable
private fun StepProgressBarDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        var index by remember { mutableIntStateOf(1) }
        val totalSteps = 6
        SirioStepSelection(progress = index, total = totalSteps, currentStep = "Step $index")
        StepDemo(index)
        SirioStepControls(
            previousEnabled = index > 1,
            nextEnabled = index < totalSteps,
            onPrevious = { --index },
            onNext = { ++index })
    }
}

@Composable
private fun ColumnScope.StepDemo(index: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$index", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun StepProgressBarActivityPreview() {
    SirioTheme {
        StepProgressBarDemoView()
    }
}

@Preview
@Composable
private fun StepProgressBarDemoPreview() {
    SirioTheme {
        StepProgressBarDemo()
    }
}
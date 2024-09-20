//
// ChipActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.chip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.chip.SirioChipLabel
import it.inps.sirio.ui.chip.SirioChipLabelClose
import it.inps.sirio.ui.chip.SirioChipLabelIcon
import it.inps.sirio.ui.chip.SirioChipLabelIconClose

class ChipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                ChipDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ChipDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Chips") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "Chips"
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            var isActive3 by remember { mutableStateOf(false) }
            var isActive4 by remember { mutableStateOf(false) }
            val label = "Chips"
            Text(text = "Big Chips Label + Icon + Close")
            SirioChipLabelIconClose(label = label, icon = FaIcons.User, enabled = true) {}
            Text(text = "Big Chips Label + Close")
            SirioChipLabelClose(label = label, enabled = true) {}
            Text(text = "Big Chips Label + Icon")
            SirioChipLabelIcon(
                label = label,
                icon = FaIcons.Check,
                enabled = true,
                isActive = isActive3,
                onStateChange = { isActive3 = it })
            Text(text = "Big Chips Only Label")
            SirioChipLabel(
                label = label,
                enabled = true,
                active = isActive4,
                onStateChange = { isActive4 = it })
        }
    }
}

@Composable
fun ChipMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem("Label Icon Close") {
            navController.navigate(ChipDestinations.CHIP_LABEL_ICON_CLOSE_ROUTE)
        }
        HorizontalDivider()
        DemoMenuItem("Label Close") {
            navController.navigate(ChipDestinations.CHIP_LABEL_CLOSE_ROUTE)
        }
        HorizontalDivider()
        DemoMenuItem(title = "Label Icon") {
            navController.navigate(ChipDestinations.CHIP_LABEL_ICON_ROUTE)
        }
        HorizontalDivider()
        DemoMenuItem("Label") {
            navController.navigate(ChipDestinations.CHIP_LABEL_ROUTE)
        }
    }
}

@Composable
fun ChipDemo(content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Light",
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        SirioTheme(darkTheme = false) {
            content()
        }
        Text(
            text = "Dark",
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        SirioTheme(darkTheme = true) {
            content()
        }
    }
}

@Composable
fun ChipLabelIconCloseDemoContent() {
    val title = "Chips"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SirioChipLabelIconClose(label = title, icon = FaIcons.User, enabled = true) {}
        SirioChipLabelIconClose(label = title, icon = FaIcons.User, enabled = false) {}
    }
}

@Composable
fun ChipLabelCloseDemoContent() {
    val title = "Chips"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SirioChipLabelClose(label = title, enabled = true) {}
        SirioChipLabelClose(label = title, enabled = false) {}
    }
}

@Composable
fun ChipLabelIconDemoContent() {
    val title = "Chips"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var isActive1 by remember { mutableStateOf(true) }
        var isActive2 by remember { mutableStateOf(true) }
        var isActive3 by remember { mutableStateOf(false) }
        SirioChipLabelIcon(
            label = title,
            icon = FaIcons.User,
            enabled = true,
            isActive = isActive1,
            onStateChange = { isActive1 = it })
        SirioChipLabelIcon(
            label = title,
            icon = FaIcons.User,
            enabled = false,
            isActive = isActive2,
            onStateChange = { isActive2 = it })
        SirioChipLabelIcon(
            label = title,
            icon = FaIcons.User,
            enabled = true,
            isActive = isActive3,
            onStateChange = { isActive3 = it })
    }
}

@Composable
fun ChipLabelDemoContent() {
    val title = "Chips"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var isActive1 by remember { mutableStateOf(true) }
        var isActive2 by remember { mutableStateOf(true) }
        var isActive3 by remember { mutableStateOf(false) }
        SirioChipLabel(
            label = title,
            enabled = true,
            active = isActive1,
            onStateChange = { isActive1 = it })
        SirioChipLabel(
            label = title,
            enabled = false,
            active = isActive2,
            onStateChange = { isActive2 = it })
        SirioChipLabel(
            label = title,
            enabled = true,
            active = isActive3,
            onStateChange = { isActive3 = it })
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun ChipDestinationsPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SirioTheme(darkTheme = false) {
            ChipLabelIconCloseDemoContent()
            ChipLabelCloseDemoContent()
            ChipLabelIconDemoContent()
            ChipLabelDemoContent()
        }
        SirioTheme(darkTheme = true) {
            ChipLabelIconCloseDemoContent()
            ChipLabelCloseDemoContent()
            ChipLabelIconDemoContent()
            ChipLabelDemoContent()
        }
    }
}

@Preview
@Composable
private fun ChipDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SirioTheme(darkTheme = false) {
            ChipDemoView()
        }
    }
}

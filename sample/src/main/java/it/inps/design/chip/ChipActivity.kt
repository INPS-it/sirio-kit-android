//
// ChipActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.chip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
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
import it.inps.sirio.ui.chip.ChipLabel
import it.inps.sirio.ui.chip.ChipLabelClose
import it.inps.sirio.ui.chip.ChipLabelIcon
import it.inps.sirio.ui.chip.ChipLabelIconClose

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
            TopAppBar(title = { Text(text = title) }, backgroundColor = SirioTheme.colors.brand)
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            var isActive3 by remember { mutableStateOf(false) }
            var isActive4 by remember { mutableStateOf(false) }
            val label = "Chips"
            Text(text = "Big Chips Label + Icon + Close")
            ChipLabelIconClose(label = label, icon = FaIcons.User, enabled = true) {}
            Text(text = "Big Chips Label + Close")
            ChipLabelClose(label = label, enabled = true) {}
            Text(text = "Big Chips Label + Icon")
            ChipLabelIcon(
                label = label,
                icon = FaIcons.Check,
                enabled = true,
                isActive = isActive3,
                onStateChange = { isActive3 = it })
            Text(text = "Big Chips Only Label")
            ChipLabel(
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
        Divider()
        DemoMenuItem("Label Close") {
            navController.navigate(ChipDestinations.CHIP_LABEL_CLOSE_ROUTE)
        }
        Divider()
        DemoMenuItem(title = "Label Icon") {
            navController.navigate(ChipDestinations.CHIP_LABEL_ICON_ROUTE)
        }
        Divider()
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
        ChipLabelIconClose(label = title, icon = FaIcons.User, enabled = true) {}
        ChipLabelIconClose(label = title, icon = FaIcons.User, enabled = false) {}
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
        ChipLabelClose(label = title, enabled = true) {}
        ChipLabelClose(label = title, enabled = false) {}
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
        ChipLabelIcon(
            label = title,
            icon = FaIcons.User,
            enabled = true,
            isActive = isActive1,
            onStateChange = { isActive1 = it })
        ChipLabelIcon(
            label = title,
            icon = FaIcons.User,
            enabled = false,
            isActive = isActive2,
            onStateChange = { isActive2 = it })
        ChipLabelIcon(
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
        ChipLabel(
            label = title,
            enabled = true,
            active = isActive1,
            onStateChange = { isActive1 = it })
        ChipLabel(
            label = title,
            enabled = false,
            active = isActive2,
            onStateChange = { isActive2 = it })
        ChipLabel(
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
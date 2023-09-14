//
// ButtonActivity.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.button

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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.*

class ButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                ButtonDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ButtonDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Buttons") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "Buttons"
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = title) }, backgroundColor = SirioTheme.colors.brand)
        }
    ) {
        ButtonNavigationGraph(navController = navController)
    }
}

@Composable
fun ButtonMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem("Primary") {
            navController.navigate(ButtonDestinations.BUTTON_PRIMARY_ROUTE)
        }
        Divider()
        DemoMenuItem(title = "Secondary") {
            navController.navigate(ButtonDestinations.BUTTON_SECONDARY_ROUTE)
        }
        Divider()
        DemoMenuItem("Tertiary Light") {
            navController.navigate(ButtonDestinations.BUTTON_TERTIARY_LIGHT_ROUTE)
        }
        Divider()
        DemoMenuItem("Tertiary Dark") {
            navController.navigate(ButtonDestinations.BUTTON_TERTIARY_DARK_ROUTE)
        }
        Divider()
        DemoMenuItem("Danger") {
            navController.navigate(ButtonDestinations.BUTTON_DANGER_ROUTE)
        }
        Divider()
        DemoMenuItem("Ghost") {
            navController.navigate(ButtonDestinations.BUTTON_GHOST_ROUTE)
        }
    }
}

@Composable
fun ButtonPrimaryDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text = "Text"
        ButtonDemoContent(text, ButtonStyle.Primary, ButtonSize.Large)
        ButtonDemoContent(text, ButtonStyle.Primary, ButtonSize.Medium)
        ButtonDemoContent(text, ButtonStyle.Primary, ButtonSize.Small)
    }
}

@Composable
fun ButtonDangerDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text = "Text"
        ButtonDemoContent(text, ButtonStyle.Danger, ButtonSize.Large)
        ButtonDemoContent(text, ButtonStyle.Danger, ButtonSize.Medium)
        ButtonDemoContent(text, ButtonStyle.Danger, ButtonSize.Small)
    }
}

@Composable
fun ButtonSecondaryDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text = "Text"
        ButtonDemoContent(text, ButtonStyle.Secondary, ButtonSize.Large)
        ButtonDemoContent(text, ButtonStyle.Secondary, ButtonSize.Medium)
        ButtonDemoContent(text, ButtonStyle.Secondary, ButtonSize.Small)
    }
}

@Composable
fun ButtonTertiaryLightDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = false) {
            val text = "Text"
            ButtonDemoContent(text, ButtonStyle.Tertiary, ButtonSize.Large)
            ButtonDemoContent(text, ButtonStyle.Tertiary, ButtonSize.Medium)
            ButtonDemoContent(text, ButtonStyle.Tertiary, ButtonSize.Small)
        }
    }
}

@Composable
fun ButtonTertiaryDarkDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = true) {
            val text = "Text"
            ButtonDemoContent(text, ButtonStyle.Tertiary, ButtonSize.Large)
            ButtonDemoContent(text, ButtonStyle.Tertiary, ButtonSize.Medium)
            ButtonDemoContent(text, ButtonStyle.Tertiary, ButtonSize.Small)
        }
    }
}

@Composable
fun ButtonGhostDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = true) {
            val text = "Text"
            ButtonDemoContent(text, ButtonStyle.Ghost, ButtonSize.Large)
            ButtonDemoContent(text, ButtonStyle.Ghost, ButtonSize.Medium)
            ButtonDemoContent(text, ButtonStyle.Ghost, ButtonSize.Small)
        }
    }
}

@Composable
private fun ButtonDemoContent(text: String, style: ButtonStyle, size: ButtonSize) {
    Text(text = size.name)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            SirioButton(
                text = text,
                icon = null,
                enabled = true,
                style = style,
                size = size,
                onClick = {})
            SirioButton(
                text = text,
                icon = FaIcons.ArrowRight,
                enabled = true,
                style = style,
                size = size,
                onClick = {})
            SirioButton(
                icon = FaIcons.ArrowRight,
                enabled = true,
                style = style,
                size = size,
                onClick = {})
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SirioButton(
                text = text,
                icon = null,
                enabled = false,
                style = style,
                size = size,
                onClick = {})
            SirioButton(
                text = text,
                icon = FaIcons.ArrowRight,
                enabled = false,
                style = style,
                size = size,
                onClick = {})
            SirioButton(
                icon = FaIcons.ArrowRight,
                enabled = false,
                style = style,
                size = size,
                onClick = {})
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun ButtonDestinationsPreview() {
    SirioTheme {
        Column(Modifier.fillMaxSize()) {
            ButtonPrimaryDemoContent()
            ButtonDangerDemoContent()
            ButtonSecondaryDemoContent()
            ButtonTertiaryLightDemoContent()
            ButtonTertiaryDarkDemoContent()
            ButtonGhostDemoContent()
        }
    }
}

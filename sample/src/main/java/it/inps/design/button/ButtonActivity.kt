//
// ButtonActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.button

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonIconPosition
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.utils.SirioIconSource

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
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            ButtonNavigationGraph(navController = navController)
        }
    }
}

@Composable
fun ButtonMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        SirioListItem("Primary") {
            navController.navigate(ButtonDestinations.BUTTON_PRIMARY_ROUTE)
        }
        SirioListItem(title = "Secondary") {
            navController.navigate(ButtonDestinations.BUTTON_SECONDARY_ROUTE)
        }
        SirioListItem("Tertiary Light") {
            navController.navigate(ButtonDestinations.BUTTON_TERTIARY_LIGHT_ROUTE)
        }
        SirioListItem("Tertiary Dark") {
            navController.navigate(ButtonDestinations.BUTTON_TERTIARY_DARK_ROUTE)
        }
        SirioListItem("Danger") {
            navController.navigate(ButtonDestinations.BUTTON_DANGER_ROUTE)
        }
        SirioListItem("Ghost Light") {
            navController.navigate(ButtonDestinations.BUTTON_GHOST_LIGHT_ROUTE)
        }
        SirioListItem("Ghost Dark", showDivider = false) {
            navController.navigate(ButtonDestinations.BUTTON_GHOST_LIGHT_ROUTE)
        }
    }
}

@Composable
fun ButtonPrimaryDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text = "Text"
        ButtonDemoContent(text, SirioButtonHierarchy.Primary, SirioButtonSize.Large)
        ButtonDemoContent(text, SirioButtonHierarchy.Primary, SirioButtonSize.Medium)
        ButtonDemoContent(text, SirioButtonHierarchy.Primary, SirioButtonSize.Small)
    }
}

@Composable
fun ButtonDangerDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text = "Text"
        ButtonDemoContent(text, SirioButtonHierarchy.Danger, SirioButtonSize.Large)
        ButtonDemoContent(text, SirioButtonHierarchy.Danger, SirioButtonSize.Medium)
        ButtonDemoContent(text, SirioButtonHierarchy.Danger, SirioButtonSize.Small)
    }
}

@Composable
fun ButtonSecondaryDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text = "Text"
        ButtonDemoContent(text, SirioButtonHierarchy.Secondary, SirioButtonSize.Large)
        ButtonDemoContent(text, SirioButtonHierarchy.Secondary, SirioButtonSize.Medium)
        ButtonDemoContent(text, SirioButtonHierarchy.Secondary, SirioButtonSize.Small)
    }
}

@Composable
fun ButtonTertiaryLightDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = false) {
            val text = "Text"
            ButtonDemoContent(text, SirioButtonHierarchy.TertiaryLight, SirioButtonSize.Large)
            ButtonDemoContent(text, SirioButtonHierarchy.TertiaryLight, SirioButtonSize.Medium)
            ButtonDemoContent(text, SirioButtonHierarchy.TertiaryLight, SirioButtonSize.Small)
        }
    }
}

@Composable
fun ButtonTertiaryDarkDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = true) {
            val text = "Text"
            ButtonDemoContent(text, SirioButtonHierarchy.TertiaryDark, SirioButtonSize.Large)
            ButtonDemoContent(text, SirioButtonHierarchy.TertiaryDark, SirioButtonSize.Medium)
            ButtonDemoContent(text, SirioButtonHierarchy.TertiaryDark, SirioButtonSize.Small)
        }
    }
}

@Composable
fun ButtonGhostLightDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = true) {
            val text = "Text"
            ButtonDemoContent(text, SirioButtonHierarchy.GhostLight, SirioButtonSize.Large)
            ButtonDemoContent(text, SirioButtonHierarchy.GhostLight, SirioButtonSize.Medium)
            ButtonDemoContent(text, SirioButtonHierarchy.GhostLight, SirioButtonSize.Small)
        }
    }
}

@Composable
fun ButtonGhostDarkDemoContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(0.dp, 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioTheme(darkTheme = true) {
            val text = "Text"
            ButtonDemoContent(text, SirioButtonHierarchy.GhostDark, SirioButtonSize.Large)
            ButtonDemoContent(text, SirioButtonHierarchy.GhostDark, SirioButtonSize.Medium)
            ButtonDemoContent(text, SirioButtonHierarchy.GhostDark, SirioButtonSize.Small)
        }
    }
}

@Composable
private fun ButtonDemoContent(text: String, hierarchy: SirioButtonHierarchy, size: SirioButtonSize) {
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
                size = size,
                hierarchy = hierarchy,
                text = text,
                icon = null,
                enabled = true,
                onClick = {},
            )
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                text = text,
                icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
                enabled = true,
                onClick = {},
            )
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                text = text,
                icon = SirioIconSource.FaIcon(FaIcons.ArrowLeft),
                enabled = true,
                iconPosition = SirioButtonIconPosition.Left,
                onClick = {},
            )
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
                enabled = true,
                onClick = {},
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                text = text,
                icon = null,
                enabled = false,
                onClick = {},
            )
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                text = text,
                icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
                enabled = false,
                onClick = {},
            )
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                text = text,
                icon = SirioIconSource.FaIcon(FaIcons.ArrowLeft),
                enabled = false,
                iconPosition = SirioButtonIconPosition.Left,
                onClick = {},
            )
            SirioButton(
                size = size,
                hierarchy = hierarchy,
                icon = SirioIconSource.FaIcon(FaIcons.ArrowRight),
                enabled = false,
                onClick = {},
            )
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
            ButtonGhostLightDemoContent()
        }
    }
}

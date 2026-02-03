//
// ListItemActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.listitem

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.dialog.SirioDialog
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.utils.SirioIconSource

private const val DEFAULT = "Default"
private const val ICON = "Icon"
private const val INFO = "Info"

class ListItemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                ListItemDemoView()
            }
        }
    }
}

@Composable
private fun ListItemDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "List item") },
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
                ListItemMenuDemo(navController = navController)
            }
            composable(DEFAULT) {
                ListItemDefaultDemo()
            }
            composable(ICON) {
                ListItemIconDemo()
            }
            composable(INFO) {
                ListItemInfoDemo()
            }
        }
    }
}

@Composable
fun ListItemMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        SirioListItem(DEFAULT) {
            navController.navigate(DEFAULT)
        }
        SirioListItem(ICON) {
            navController.navigate(ICON)
        }
        SirioListItem(INFO) {
            navController.navigate(INFO)
        }
    }
}

@Composable
private fun ListItemDefaultDemo() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        demoItems.forEachIndexed { index, item ->
            SirioListItem(
                title = item.title,
                showDivider = index <= demoItems.size,
                onClick = { },
            )
        }
    }
}

@Composable
private fun ListItemIconDemo() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        demoItems.forEachIndexed { index, item ->
            SirioListItem(
                title = item.title,
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                showDivider = index <= demoItems.size,
                onClick = { },
            )
        }
    }
}

@Composable
private fun ListItemInfoDemo() {
    var showDialog: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        demoItems.forEachIndexed { index, item ->
            SirioListItem(
                title = item.title,
                description = item.description,
                onInfoClick = { showDialog = true },
                showDivider = index <= demoItems.size,
                onClick = { },
            )
        }
    }

    if (showDialog) {
        SirioDialog(
            title = "Titolo",
            text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
            onDismiss = { showDialog = false },
        )
    }
}

private data class DemoData(
    val title: String,
    val description: String,
)

private val demoItems = listOf(
    DemoData(
        title = "Titolo list item 1",
        description = "Oggi aperto dalle 8:30 alle 12:30",
    ),
    DemoData(
        title = "Titolo list item 2",
        description = "Lorem ipsum dolor sit amet consectetur. Purus suscipit aliquam habitant.",
    ),
    DemoData(
        title = "Titolo list item 3",
        description = "Lorem ipsum dolor sit amet",
    ),
)

@Preview
@Composable
private fun ListItemActivityPreview() {
    SirioTheme {
        ListItemDemoView()
    }
}

@Preview
@Composable
private fun ListItemDefaultDemoPreview() {
    SirioTheme {
        ListItemDefaultDemo()
    }
}

@Preview
@Composable
private fun ListItemIconDemoPreview() {
    SirioTheme {
        ListItemIconDemo()
    }
}

@Preview
@Composable
private fun ListItemInfoDemoPreview() {
    SirioTheme {
        ListItemInfoDemo()
    }
}
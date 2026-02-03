//
// MenuSpallaActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.menuspalla

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.ui.menuspalla.SirioMenuSpalla
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItem
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemInfo
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItem
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemData
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemLevel
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemTitleSection
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaSectionItemData

private const val INTESTAZIONE = "Intestazione"
private const val FIRST_LEVEL = "Base components - Primo livello"
private const val SECOND_LEVEL = "Base components - Secondo livello"
private const val THIRD_LEVEL = "Base components - Terzo livello"
private const val DRAWER = "Base components - Mobile Drawer"
private const val DEMO = "Demo"

class MenuSpallaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                MenuSpallaDemoView()
            }
        }
    }
}

@Composable
private fun MenuSpallaDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Menu spalla") },
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
                MenuSpallaMenuDemo(navController = navController)
            }
            composable(INTESTAZIONE) {
                MenuSpallaIntestazioneDemo()
            }
            composable(FIRST_LEVEL) {
                MenuSpallaFirstLevelDemo()
            }
            composable(SECOND_LEVEL) {
                MenuSpallaSecondLevelDemo()
            }
            composable(THIRD_LEVEL) {
                MenuSpallaThirdLevelDemo()
            }
            composable(DRAWER) {
                MenuSpallaDrawerDemo()
            }
            composable(DEMO) {
                MenuSpallaDemo()
            }
        }
    }
}

@Composable
fun MenuSpallaMenuDemo(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            SirioListItem(INTESTAZIONE) {
                navController.navigate(INTESTAZIONE)
            }
            SirioListItem(FIRST_LEVEL) {
                navController.navigate(FIRST_LEVEL)
            }
            SirioListItem(SECOND_LEVEL) {
                navController.navigate(SECOND_LEVEL)
            }
            SirioListItem(THIRD_LEVEL) {
                navController.navigate(THIRD_LEVEL)
            }
            SirioListItem(DRAWER) {
                navController.navigate(DRAWER)
            }
            SirioListItem(DEMO, showDivider = false) {
                navController.navigate(DEMO)
            }
        }
    }
}

@Composable
private fun MenuSpallaIntestazioneDemo() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Intestazione")
        SirioMenuSpallaItemTitleSection(title = "Label menu")
    }
}

@Composable
private fun MenuSpallaFirstLevelDemo() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Item / Primario + Chip")
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "3",
            selected = false,
            enabled = true,
            hasSubItems = false,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "3",
            selected = false,
            enabled = false,
            hasSubItems = false,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "3",
            selected = true,
            enabled = true,
            hasSubItems = false,
            onClick = {},
        )
        Text(text = "Item / Primario / Sub-item")
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "3",
            selected = false,
            enabled = true,
            hasSubItems = true,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "3",
            selected = false,
            enabled = false,
            hasSubItems = true,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "3",
            selected = true,
            enabled = true,
            hasSubItems = true,
            onClick = {},
        )
        Text(text = "Item / Primario")
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = null,
            selected = false,
            enabled = true,
            hasSubItems = false,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            selected = false,
            enabled = false,
            hasSubItems = false,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.FIRST,
            tagText = "",
            selected = true,
            enabled = true,
            hasSubItems = false,
            onClick = {},
        )
    }
}

@Composable
private fun MenuSpallaSecondLevelDemo() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Item / Secondario / Sub-item")
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.SECOND,
            tagText = "3",
            selected = false,
            enabled = true,
            hasSubItems = true,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.SECOND,
            tagText = "3",
            selected = false,
            enabled = false,
            hasSubItems = true,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.SECOND,
            tagText = "3",
            selected = true,
            enabled = true,
            hasSubItems = true,
            onClick = {},
        )
        Text(text = "Item / Secondario / Sub-item")
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.SECOND,
            tagText = "3",
            selected = false,
            enabled = true,
            hasSubItems = false,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.SECOND,
            tagText = "3",
            selected = false,
            enabled = false,
            hasSubItems = false,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.SECOND,
            tagText = "3",
            selected = true,
            enabled = true,
            hasSubItems = false,
            onClick = {},
        )
    }
}

@Composable
private fun MenuSpallaThirdLevelDemo() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Item / Terziario")
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.THIRD,
            tagText = "3",
            selected = false,
            enabled = true,
            hasSubItems = true,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.THIRD,
            tagText = "3",
            selected = false,
            enabled = false,
            hasSubItems = true,
            onClick = {},
        )
        SirioMenuSpallaItem(
            title = "Label",
            level = SirioMenuSpallaItemLevel.THIRD,
            tagText = "3",
            selected = true,
            enabled = true,
            hasSubItems = true,
            onClick = {},
        )
    }
}

@Composable
private fun MenuSpallaDrawerDemo() {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Mobile / item")
        val title = "Action Label"
        val subtitle = "Placeholder Label"
        SirioMenuSpallaDrawerItem(
            title = title,
            subtitle = subtitle,
            open = false,
            onStateChange = {}
        )
        SirioMenuSpallaDrawerItem(
            title = title,
            subtitle = subtitle,
            open = true,
            onStateChange = {}
        )
        SirioMenuSpallaDrawerItemInfo(
            title = "Mario Rossi",
            subtitle = "Profilo Cittadino",
            icon = FaIcons.User,
        )
    }
}

@Composable
private fun MenuSpallaDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        var title by remember { mutableStateOf("Apri menu") }
        val sections = listOf(
            SirioMenuSpallaSectionItemData(
                title = "Titolo sezione",
                items = listOf(
                    SirioMenuSpallaItemData(
                        title = "Label tag",
                        tag = "3",
                        onClick = {
                            Log.d(
                                "SirioMenuSpallaPreview",
                                "SirioMenuSpallaPreview: label tag"
                            )
                        },
                    ),
                    SirioMenuSpallaItemData(
                        title = "Label 1",
                        tag = "1",
                        children = listOf(
                            SirioMenuSpallaItemData(
                                title = "Label 1.1",
                                tag = "1.1",
                                onClick = {
                                    Log.d(
                                        "SirioMenuSpallaPreview",
                                        "SirioMenuSpallaPreview: label 1.1"
                                    )
                                }
                            ),
                            SirioMenuSpallaItemData(
                                title = "Label 1.2",
                                tag = "1.2",
                                children = listOf(
                                    SirioMenuSpallaItemData(
                                        title = "Label 1.2.1",
                                        tag = "1.2.1",
                                        onClick = {
                                            Log.d(
                                                "SirioMenuSpallaPreview",
                                                "SirioMenuSpallaPreview: label 1.2.1"
                                            )
                                        }
                                    ),
                                    SirioMenuSpallaItemData(
                                        title = "Label 1.2.2",
                                        tag = "1.2.2",
                                        onClick = {
                                            Log.d(
                                                "SirioMenuSpallaPreview",
                                                "SirioMenuSpallaPreview: label 1.2.2"
                                            )
                                        }
                                    )
                                ),
                                onClick = {
                                    Log.d(
                                        "SirioMenuSpallaPreview",
                                        "SirioMenuSpallaPreview: label 1.2"
                                    )
                                }
                            )
                        ),
                        onClick = {
                            Log.d(
                                "SirioMenuSpallaPreview",
                                "SirioMenuSpallaPreview: label 1"
                            )
                        },
                    ),
                    SirioMenuSpallaItemData(
                        title = "Label 2",
                        tag = "2",
                        children = listOf(
                            SirioMenuSpallaItemData(
                                title = "Label 2.1",
                                tag = "2.1",
                                onClick = {
                                    Log.d(
                                        "SirioMenuSpallaPreview",
                                        "SirioMenuSpallaPreview: label 2.1"
                                    )
                                }
                            )
                        ),
                        onClick = {
                            Log.d(
                                "SirioMenuSpallaPreview",
                                "SirioMenuSpallaPreview: label 2"
                            )
                        },
                    ),
                    SirioMenuSpallaItemData(
                        title = "Label 3",
                        onClick = {
                            Log.d(
                                "SirioMenuSpallaPreview",
                                "SirioMenuSpallaPreview: label 3"
                            )
                        },
                    ),
                ),
            )
        )
        SirioMenuSpalla(
            title = title,
            subtitle = "Titolo sezione",
            sections = sections,
            onStateChange = { open ->
                title = if (open) "Chiudi menu" else "Apri menu"
            }
        )
    }
}

@Preview
@Composable
private fun MenuSpallaActivityPreview() {
    SirioTheme {
        MenuSpallaDemoView()
    }
}

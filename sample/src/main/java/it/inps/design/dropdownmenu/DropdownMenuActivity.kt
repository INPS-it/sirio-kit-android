//
// DropdownMenuActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)

package it.inps.design.dropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.dropdownmenu.SirioDropdownItemData
import it.inps.sirio.ui.dropdownmenu.SirioDropdownMenu
import it.inps.sirio.ui.dropdownmenu.SirioDropdownMenuContent
import it.inps.sirio.ui.dropdownmenu.SirioPopupState
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.utils.SirioIconSource

private const val DROPDOWN_MENU = "DropdownMenu"
private const val DROPDOWN_MENU_MORE = "DropdownMenu - Playground 1"
private const val DROPDOWN_MENU_BUTTON = "DropdownMenu - Playground 2"

class DropdownMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("DropdownMenu") },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
                        )
                    },
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "/",
                        modifier = Modifier.padding(it),
                    ) {
                        composable("/") {
                            DropdownMenuMenuDemo(navController = navController)
                        }
                        composable(DROPDOWN_MENU) {
                            DropdownMenuDemo()
                        }
                        composable(DROPDOWN_MENU_MORE) {
                            MoreDropdownMenuDemo()
                        }
                        composable(DROPDOWN_MENU_BUTTON) {
                            ButtonDropdownMenuDemo()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        SirioListItem(DROPDOWN_MENU) {
            navController.navigate(DROPDOWN_MENU)
        }
        SirioListItem(DROPDOWN_MENU_MORE) {
            navController.navigate(DROPDOWN_MENU_MORE)
        }
        SirioListItem(DROPDOWN_MENU_BUTTON, showDivider = false) {
            navController.navigate(DROPDOWN_MENU_BUTTON)
        }
    }
}

@Composable
fun DropdownMenuDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Box(Modifier.fillMaxWidth().padding(vertical = 20.dp), contentAlignment = Alignment.Center) {
            SirioDropdownMenuContent(
                items = listOf(
                    SirioDropdownItemData("Action #1") {},
                    SirioDropdownItemData("Action #2") {},
                    SirioDropdownItemData("Action #3") {},
                ),
                onItemSelected = {},
            )
        }
    }
}

@Composable
fun MoreDropdownMenuDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomEnd)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = true
                sirioPopupState.horizontalAlignment = Alignment.Start
                SirioButton(
                    icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    offset = DpOffset((-4).dp, 8.dp),
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    ),
                )
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomStart)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = true
                sirioPopupState.horizontalAlignment = Alignment.End
                SirioButton(
                    icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    offset = DpOffset(4.dp, 8.dp),
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    )
                )
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.TopEnd)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = false
                sirioPopupState.horizontalAlignment = Alignment.Start
                SirioButton(
                    icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    offset = DpOffset((-4).dp, 8.dp),
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    )
                )
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.TopStart)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = false
                sirioPopupState.horizontalAlignment = Alignment.End
                SirioButton(
                    icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    offset = DpOffset(4.dp, 8.dp),
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    )
                )
            }
        }
    }
}

@Composable
fun ButtonDropdownMenuDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(40.dp),
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomEnd)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = true
                sirioPopupState.horizontalAlignment = Alignment.Start
                SirioButton(
                    text = "Text",
                    icon = SirioIconSource.FaIcon(FaIcons.ChevronDown),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    ),
                )
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomStart)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = true
                sirioPopupState.horizontalAlignment = Alignment.End
                SirioButton(
                    text = "Text",
                    icon = SirioIconSource.FaIcon(FaIcons.ChevronDown),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    )
                )
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.TopEnd)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = false
                sirioPopupState.horizontalAlignment = Alignment.Start
                SirioButton(
                    text = "Text",
                    icon = SirioIconSource.FaIcon(FaIcons.ChevronDown),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    )
                )
            }
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.TopStart)
            ) {
                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = false
                sirioPopupState.horizontalAlignment = Alignment.End
                SirioButton(
                    text = "Text",
                    icon = SirioIconSource.FaIcon(FaIcons.ChevronDown),
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Primary,
                ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                SirioDropdownMenu(
                    popupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    items = listOf(
                        SirioDropdownItemData("Action #1") {},
                        SirioDropdownItemData("Action #2") {},
                        SirioDropdownItemData("Action #3") {},
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun DropdownMenuDemoViewPreview() {
    SirioTheme {
        DropdownMenuDemo()
    }
}

@Preview
@Composable
private fun MoreDropdownMenuDemoViewPreview() {
    SirioTheme {
        MoreDropdownMenuDemo()
    }
}

@Preview
@Composable
private fun ButtonDropdownMenuDemoViewPreview() {
    SirioTheme {
        ButtonDropdownMenuDemo()
    }
}

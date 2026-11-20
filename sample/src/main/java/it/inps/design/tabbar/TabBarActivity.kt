//
// TabBarActivity.kt
//
// SPDX-FileCopyrightText: 2026 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.tabbar

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.appnavigation.SirioAppNavigation
import it.inps.sirio.ui.tabbar.SirioTabBar
import it.inps.sirio.ui.tabbar.SirioTabBarItemData
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object News

@Serializable
object Options

@Serializable
object Contacts

@Serializable
object Services

class TabBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The Sirio tab bar background is light in both themes.
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = android.graphics.Color.TRANSPARENT,
                darkScrim = 0x801B1B1B.toInt(),
            ),
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        setContent {
            SirioTheme {
                // A surface container using the 'background' color from the theme
                TabBarDemoContent()
            }
        }
    }
}

@Composable
fun TabBarDemoContent(logout: () -> Unit = {}) {
    val navController = rememberNavController()
    var selectedIndex: Int by remember { mutableIntStateOf(0) }
    val listItems = listOf(
        SirioTabBarItemData(
            label = "Home",
            icon = FaIcons.Home,
            action = {
                navController.navigate(Home)
                selectedIndex = 0
            },
        ),
        SirioTabBarItemData(
            label = "Notizie",
            icon = FaIcons.Newspaper,
            badge = false,
            action = {
                navController.navigate(News)
                selectedIndex = 1
            },
        ),
        SirioTabBarItemData(
            label = "Servizi",
            icon = FaIcons.GripHorizontal,
            highlighted = true,
            action = {
                navController.navigate(Services)
                selectedIndex = 2
            }
        ),
        SirioTabBarItemData(
            label = "Contatti",
            icon = FaIcons.CommentAlt,
            action = {
                logout()
                navController.navigate(Contacts)
                selectedIndex = 3
            }
        ),
        SirioTabBarItemData(
            label = "Opzioni",
            icon = FaIcons.List,
            action = {
                navController.navigate(Options)
                selectedIndex = 4
            }
        ),
    )
    Scaffold(
        topBar = {
            SirioAppNavigation(title = "Tab Bar")
        },
        bottomBar = {
            SirioTabBar(items = listItems, selectedIndex = selectedIndex)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
        ) {
            NavigationGraph(navController = navController)
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultPreview() {
    SirioTheme {
        TabBarDemoContent()
    }
}

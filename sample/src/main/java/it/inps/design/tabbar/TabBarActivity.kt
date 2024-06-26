//
// TabBarActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.tabbar

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.tabbar.TabBar
import it.inps.sirio.ui.tabbar.TabBarItemData

val HOME_TAB = TabBarItemData(label = "Home", icon = FaIcons.Home, route = "Home")
val NEWS_TAB = TabBarItemData(label = "News", icon = FaIcons.Bell, route = "Notizie", badge = true)
val MAP_TAB = TabBarItemData(label = "Mappa", icon = FaIcons.Globe, route = "Mappa")
val CONTACTS_TAB =
    TabBarItemData(label = "Contattaci", icon = FaIcons.CommentAlt, route = "Contatti")
val SERVICES_TAB =
    TabBarItemData(label = "Servizi", icon = FaIcons.GripHorizontal, route = "Servizi")

class TabBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                // A surface container using the 'background' color from the theme
                TabBarDemoContent()
            }
        }
    }

}

@Composable
fun TabBarDemoContent() {
    val listItems = listOf(HOME_TAB, NEWS_TAB, MAP_TAB, CONTACTS_TAB, SERVICES_TAB)
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tab Bar") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
        bottomBar = {
            TabBar(items = listItems, navController = navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
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

//
// DemoActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.appnavigation.AppNavigationItemData
import it.inps.sirio.ui.appnavigation.AppNavigationLongTitle
import it.inps.sirio.ui.appnavigation.AppNavigationSearch
import it.inps.sirio.ui.appnavigation.AppNavigationSelection
import it.inps.sirio.ui.tabbar.TabBarItemData

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                DemoView()
            }
        }
    }
}

val HOME_TAB = TabBarItemData(label = "Home", icon = FaIcons.Home, route = "Home")
val NEWS_TAB = TabBarItemData(label = "News", icon = FaIcons.Bell, route = "Notizie", badge = true)
val MAP_TAB = TabBarItemData(label = "Mappa", icon = FaIcons.Globe, route = "Mappa")
val CONTACTS_TAB = TabBarItemData(label = "Contatti", icon = FaIcons.CommentAlt, route = "Contatti")
val SERVICES_TAB =
    TabBarItemData(label = "Servizi", icon = FaIcons.GripHorizontal, route = "Servizi")

enum class TopAppBarDemoType {
    SEARCH,
    LONG,
    SELECTION,
    LOGGED_IN
}

@Composable
private fun DemoView() {
//    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
//    val scrollBehavior = remember(decayAnimationSpec) {
//        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
//    }
//    val navController = rememberNavController()
//    val title = "Titolo\npagina"
//    val listItems = listOf(
//        HOME_TAB,
//        NEWS_TAB,
//        MAP_TAB,
//        CONTACTS_TAB,
//        SERVICES_TAB
//    )
//
//    Scaffold(
//        Modifier
//            .fillMaxSize()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = topBar(title, scrollBehavior),
//        bottomBar = {
//            TabBar(items = listItems, navController = navController)
//        }
//    ) {
//        DemoNavigationGraph(navController = navController)
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun topBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
): @Composable () -> Unit {
    var topBarType by remember { mutableStateOf(TopAppBarDemoType.LONG) }
    var text by remember { mutableStateOf("") }
    return {
        when (topBarType) {
            TopAppBarDemoType.SEARCH -> AppNavigationSearch(
                title = title,
                searchText = text,
                placeholderText = "Ricerca",
                onSearchTextChanged = { text = it },
                rightFirstItem = AppNavigationItemData(
                    icon = FaIcons.Square,
                    action = { topBarType = TopAppBarDemoType.SELECTION })
            )
            TopAppBarDemoType.LONG -> {
                AppNavigationLongTitle(
                    title = title,
                    leftItem = AppNavigationItemData(icon = FaIcons.Bars, action = {}),
                    rightFirstItem = AppNavigationItemData(
                        icon = FaIcons.User,
                        action = { topBarType = TopAppBarDemoType.LOGGED_IN }),
                    rightSecondItem = AppNavigationItemData(
                        icon = FaIcons.Search,
                        action = { topBarType = TopAppBarDemoType.SEARCH }),
                    scrollBehavior = scrollBehavior
                )
            }
            TopAppBarDemoType.LOGGED_IN -> {
                AppNavigationLongTitle(
                    title = title,
                    leftItem = AppNavigationItemData(icon = FaIcons.Bars, action = {}),
                    rightFirstItem = AppNavigationItemData(
                        icon = FaIcons.User,
                        action = { topBarType = TopAppBarDemoType.LONG },
                        username = "MC"
                    ),
                    rightSecondItem = AppNavigationItemData(
                        icon = FaIcons.Search,
                        action = { topBarType = TopAppBarDemoType.SEARCH }),
                    scrollBehavior = scrollBehavior
                )
            }
            TopAppBarDemoType.SELECTION -> AppNavigationSelection(
                title = "0 elementi",
                leftItem = AppNavigationItemData(
                    icon = FaIcons.AngleLeft,
                    action = { topBarType = TopAppBarDemoType.LONG }),
                rightFirstItem = AppNavigationItemData(icon = FaIcons.Download, action = {}),
                rightSecondItem = AppNavigationItemData(icon = FaIcons.Trash, action = {}),
            )
        }
    }
}


@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DemoPreview() {
    SirioTheme {
        DemoView()
    }
}

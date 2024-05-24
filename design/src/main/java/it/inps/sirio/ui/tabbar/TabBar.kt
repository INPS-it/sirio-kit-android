//
// TabBar.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabBarHeight
import it.inps.sirio.theme.tabBarItemIconSize
import it.inps.sirio.theme.tabBarItemStateIndicatorHeight
import it.inps.sirio.ui.badge.SirioBadgeCommon
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * A bottom navigation with tabs
 *
 * @param items The items [TabBarItemData] to be shown. Min 3 - Max 5
 * @param navController The navigation controller in which perform the routes
 */
@Composable
fun TabBar(items: List<TabBarItemData>, navController: NavHostController) {
    //TabBar should contain 3-5 tabs
    assert(items.size in 3..5)
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(tabBarHeight.dp),
        containerColor = SirioTheme.colors.tabBarBackground,
    ) {
        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        val labelTypography = with(SirioTheme.typography.tabBarItemText) {
            if (LocalConfiguration.current.fontScale > 1) copy(fontSize = 9.sp) else this
        }

        items.take(5).forEach { tabItem ->
            val selected = currentRoute == tabItem.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    try {
                        navController.navigate(tabItem.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    } catch (e: Exception) {
                        Log.e("TabBar", "TabBar: onclick exception ", e)
                    }
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(tabBarItemStateIndicatorHeight.dp)
                                .background(if (selected) SirioTheme.colors.tabBarActive else SirioTheme.colors.tabBarBackground)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        BadgedBox(
                            badge = {
                                if (tabItem.badge) {
                                    SirioBadgeCommon()
                                }
                            },
                        ) {
                            SirioIcon(
                                faIcon = tabItem.icon,
                                size = tabBarItemIconSize.dp,
                                iconColor = LocalContentColor.current
                            )
                        }
                    }
                },
                label = {
                    SirioTextCommon(
                        text = tabItem.label,
                        color = LocalContentColor.current,
                        maxLines = 1,
                        typography = labelTypography,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SirioTheme.colors.tabBarActive,
                    selectedTextColor = SirioTheme.colors.tabBarActive,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = SirioTheme.colors.tabBarContent,
                    unselectedTextColor = SirioTheme.colors.tabBarContent,
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, device = Devices.NEXUS_10)
@Composable
private fun TabBarPreview() {
    SirioTheme {
        Scaffold(
            bottomBar = {
                TabBar(
                    items = listOf(
                        TabBarItemData(
                            label = "Home",
                            icon = FaIcons.Home,
                            route = "InpsScreen.HomeScreen.route",
                            badge = false,
                        ),
                        TabBarItemData(
                            label = "News",
                            icon = FaIcons.Newspaper,
                            route = "InpsScreen.NewsScreen.route",
                            badge = false,
                        ),
                        TabBarItemData(
                            label = "Mappe",
                            icon = FaIcons.Globe,
                            route = "InpsScreen.MapsScreen.route",
                            badge = false,
                        ),
                        TabBarItemData(
                            label = "Contattaci",
                            icon = FaIcons.CommentAlt,
                            route = "InpsScreen.ContattaciScreen.route",
                            badge = false,
                        ),
                        TabBarItemData(
                            label = "Servizi",
                            icon = FaIcons.GripHorizontal,
                            route = "InpsScreen.ServiziScreen.route",
                            badge = true,
                        )
                    ),
                    navController = rememberNavController()
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
            }
        }
    }
}

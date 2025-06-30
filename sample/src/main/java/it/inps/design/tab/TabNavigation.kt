//
// TabNavigation.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.tab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun TabNavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = TabDestinations.TAB_MENU_ROUTE
    ) {
        composable(TabDestinations.TAB_MENU_ROUTE) {
            TabMenuDemo(navController = navController)
        }
        composable(TabDestinations.TAB_SINGLE) {
            TabDemoSingle()
        }
        composable(TabDestinations.TAB_GROUP) {
            TabDemoGroup()
        }
    }
}

object TabDestinations {
    const val TAB_MENU_ROUTE = "Tab"
    const val TAB_SINGLE = "Tab Single"
    const val TAB_GROUP = "Tab Group"
}
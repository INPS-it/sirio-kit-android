//
// Navigation.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.appnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = AppNavigationDestinations.APPNAVIGATION_MENU_ROUTE
    ) {
        composable(AppNavigationDestinations.APPNAVIGATION_MENU_ROUTE) {
            AppNavigationMenuDemo(navController = navController)
        }
        composable(AppNavigationDestinations.APPNAVIGATION_LOGO_ROUTE) {
            AppNavigationDemo { AppNavigationLogoDemoContent() }
        }
        composable(AppNavigationDestinations.APPNAVIGATION_STANDARD_ROUTE) {
            AppNavigationDemo { AppNavigationTitleDemoContent() }
        }
        composable(AppNavigationDestinations.APPNAVIGATION_FUNCTION_ROUTE) {
            AppNavigationDemo { AppNavigationFunctionDemoContent() }
        }
        composable(AppNavigationDestinations.APPNAVIGATION_SELECTION_ROUTE) {
            AppNavigationDemo { AppNavigationSelectionDemoContent() }
        }
        composable(AppNavigationDestinations.APPNAVIGATION_SEARCH_ROUTE) {
            AppNavigationDemo { AppNavigationSearchDemoContent() }
        }
    }
}

object AppNavigationDestinations {
    const val APPNAVIGATION_MENU_ROUTE = "App Navigation"
    const val APPNAVIGATION_LOGO_ROUTE = "Logo"
    const val APPNAVIGATION_STANDARD_ROUTE = "Title"
    const val APPNAVIGATION_SELECTION_ROUTE = "Selection"
    const val APPNAVIGATION_SEARCH_ROUTE = "Search"
    const val APPNAVIGATION_FUNCTION_ROUTE = "Function"
}
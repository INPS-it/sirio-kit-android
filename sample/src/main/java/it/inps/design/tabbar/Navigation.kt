//
// Navigation.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.tabbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = HOME_TAB.route) {
        composable(HOME_TAB.route) {
            HomeScreen()
        }
        composable(NEWS_TAB.route) {
            NewsScreen()
        }
        composable(MAP_TAB.route) {
            MapScreen()
        }
        composable(CONTACTS_TAB.route) {
            ContactsScreen()
        }
        composable(SERVICES_TAB.route) {
            ServicesScreen()
        }
    }
}

//const val TABBAR_ROUTE = "tabbar"
//
//fun NavGraphBuilder.tabBarGraph(navController: NavHostController) {
//    navigation(route = TABBAR_ROUTE, startDestination = HOME_TAB.route) {
//        composable(HOME_TAB.route) {
//            HomeScreen()
//        }
//        composable(NEWS_TAB.route) {
//            NewsScreen()
//        }
//        composable(MAP_TAB.route) {
//            MapScreen()
//        }
//        composable(CONTACTS_TAB.route) {
//            ContactsScreen()
//        }
//        composable(SERVICES_TAB.route) {
//            ServicesScreen()
//        }
//    }
//}
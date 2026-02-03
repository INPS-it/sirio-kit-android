//
// Navigation.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
    NavHost(navController, startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }
        composable<News> {
            NewsScreen()
        }
        composable<Options> {
            MapScreen()
        }
        composable<Contacts> {
            ContactsScreen()
        }
        composable<Services> {
            ServicesScreen()
        }
    }
}

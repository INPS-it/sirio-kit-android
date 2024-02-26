//
// AccordionNavigation.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.accordion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AccordionNavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = AccordionDestinations.ACCORDION_MENU_ROUTE
    ) {
        composable(AccordionDestinations.ACCORDION_MENU_ROUTE) {
            AccordionMenuDemo(navController = navController)
        }
        composable(AccordionDestinations.ACCORDION_ROUTE) {
            AccordionDemo { AccordionNavDemoContent() }
        }
        composable(AccordionDestinations.ACCORDION_GROUP_ROUTE) {
            AccordionDemo { AccordionGroupNavDemoContent() }
        }
    }
}

object AccordionDestinations {
    const val ACCORDION_MENU_ROUTE = "Accordion Menu"
    const val ACCORDION_ROUTE = "Accordion"
    const val ACCORDION_GROUP_ROUTE = "Accordion Group"
}
//
// AccordionNavigation.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.accordion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.inps.sirio.ui.accordion.SirioAccordionColor

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
            AccordionDemo(
                { AccordionNavDemoContent(SirioAccordionColor.LIGHT) },
                { AccordionNavDemoContent(SirioAccordionColor.PRIMARY) },
            )
        }
        composable(AccordionDestinations.ACCORDION_GROUP_ROUTE) {
            AccordionDemo(
                { AccordionGroupNavDemoContent(SirioAccordionColor.LIGHT) },
                { AccordionGroupNavDemoContent(SirioAccordionColor.PRIMARY) },
            )
        }
    }
}

object AccordionDestinations {
    const val ACCORDION_MENU_ROUTE = "Accordion Menu"
    const val ACCORDION_ROUTE = "Accordion"
    const val ACCORDION_GROUP_ROUTE = "Accordion Group"
}

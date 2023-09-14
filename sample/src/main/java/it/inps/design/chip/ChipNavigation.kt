//
// ChipNavigation.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.chip

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.inps.design.appnavigation.AppNavigationDestinations

@Composable
fun ChipNavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = ChipDestinations.CHIP_MENU_ROUTE
    ) {
        composable(ChipDestinations.CHIP_MENU_ROUTE) {
            ChipMenuDemo(navController = navController)
        }
        composable(ChipDestinations.CHIP_LABEL_ICON_CLOSE_ROUTE) {
            ChipDemo { ChipLabelIconCloseDemoContent() }
        }
        composable(ChipDestinations.CHIP_LABEL_CLOSE_ROUTE) {
            ChipDemo { ChipLabelCloseDemoContent() }
        }
        composable(ChipDestinations.CHIP_LABEL_ICON_ROUTE) {
            ChipDemo { ChipLabelIconDemoContent() }
        }
        composable(ChipDestinations.CHIP_LABEL_ROUTE) {
            ChipDemo { ChipLabelDemoContent() }
        }
    }
}

object ChipDestinations {
    const val CHIP_MENU_ROUTE = "Chips"
    const val CHIP_LABEL_ICON_CLOSE_ROUTE = "Label Icon Close"
    const val CHIP_LABEL_CLOSE_ROUTE = "Label Close"
    const val CHIP_LABEL_ICON_ROUTE = "Label Icon"
    const val CHIP_LABEL_ROUTE = "Label"
}
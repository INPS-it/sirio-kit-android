package it.inps.design.button

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ButtonNavigationGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = ButtonDestinations.BUTTON_MENU_ROUTE
    ) {
        composable(ButtonDestinations.BUTTON_MENU_ROUTE) {
            ButtonMenuDemo(navController = navController)
        }
        composable(ButtonDestinations.BUTTON_PRIMARY_ROUTE) {
            ButtonPrimaryDemoContent()
        }
        composable(ButtonDestinations.BUTTON_DANGER_ROUTE) {
            ButtonDangerDemoContent()
        }
        composable(ButtonDestinations.BUTTON_SECONDARY_ROUTE) {
            ButtonSecondaryDemoContent()
        }
        composable(ButtonDestinations.BUTTON_TERTIARY_DARK_ROUTE) {
            ButtonTertiaryDarkDemoContent()
        }
        composable(ButtonDestinations.BUTTON_TERTIARY_LIGHT_ROUTE) {
            ButtonTertiaryLightDemoContent()
        }
        composable(ButtonDestinations.BUTTON_GHOST_ROUTE) {
            ButtonGhostDemoContent()
        }
    }
}

object ButtonDestinations {
    const val BUTTON_MENU_ROUTE = "Buttons"
    const val BUTTON_PRIMARY_ROUTE = "Primary"
    const val BUTTON_DANGER_ROUTE = "Danger"
    const val BUTTON_SECONDARY_ROUTE = "Secondary"
    const val BUTTON_TERTIARY_DARK_ROUTE = "Tertiary Dark"
    const val BUTTON_TERTIARY_LIGHT_ROUTE = "Tertiary Light"
    const val BUTTON_GHOST_ROUTE = "Ghost"
}
//
// AppNavigationActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.appnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.appnavigation.SirioAppNavigation
import it.inps.sirio.ui.appnavigation.SirioAppNavigationItemData
import it.inps.sirio.ui.appnavigation.SirioAppNavigationLogo
import it.inps.sirio.ui.appnavigation.SirioAppNavigationSearch
import it.inps.sirio.ui.appnavigation.SirioAppNavigationSelection
import it.inps.sirio.ui.appnavigation.SirioFunction
import it.inps.sirio.ui.listItem.SirioListItem

class AppNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                AppNavigationDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AppNavigationDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("App Navigation") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "App Navigation"
        }
    }
    Scaffold(
        Modifier
            .safeDrawingPadding()
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }

    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun AppNavigationMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        SirioListItem(AppNavigationDestinations.APPNAVIGATION_LOGO_ROUTE) {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_LOGO_ROUTE)
        }
        SirioListItem(AppNavigationDestinations.APPNAVIGATION_STANDARD_ROUTE) {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_STANDARD_ROUTE)
        }
        SirioListItem(AppNavigationDestinations.APPNAVIGATION_SELECTION_ROUTE) {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_SELECTION_ROUTE)
        }
        SirioListItem(AppNavigationDestinations.APPNAVIGATION_SEARCH_ROUTE) {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_SEARCH_ROUTE)
        }
        SirioListItem(AppNavigationDestinations.APPNAVIGATION_FUNCTION_ROUTE, showDivider = false) {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_FUNCTION_ROUTE)
        }
    }
}

@Composable
fun AppNavigationDemo(content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Light",
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        SirioTheme(darkTheme = false) {
            content()
        }
        Text(
            text = "Dark",
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        SirioTheme(darkTheme = true) {
            content()
        }
    }
}

@Composable
fun AppNavigationLogoDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SirioAppNavigationLogo(
            rightFirstItem = SirioAppNavigationItemData(
                icon = FaIcons.User,
                action = {},
            ),
            rightSecondItem = SirioAppNavigationItemData(
                icon = FaIcons.Bell,
                action = {},
            )
        )
    }
}

@Composable
fun AppNavigationTitleDemoContent() {
    val title = "Titolo pagina"
    val longTitle =
        "Titolo di pagina molto molto molto lungo su due righe con sospensione del testo"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SirioAppNavigation(
            title = title,
            rightFirstItem = SirioAppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        SirioAppNavigation(
            title = title,
            leftItem = SirioAppNavigationItemData(icon = FaIcons.ChevronLeft, action = {}),
            rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        SirioAppNavigation(
            title = longTitle,
            rightFirstItem = SirioAppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        SirioAppNavigation(
            title = longTitle,
            leftItem = SirioAppNavigationItemData(icon = FaIcons.ChevronLeft, action = {}),
            rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
    }
}

@Composable
fun AppNavigationFunctionDemoContent() {
    val title = "Titolo funzione"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SirioFunction(
            title = title,
        )
    }
}

@Composable
fun AppNavigationSelectionDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val title = "1 Elemento Selezionato"
        SirioAppNavigationSelection(
            title = title,
            rightFirstItem = SirioAppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Trash, action = {}),
        )
    }
}

@Composable
fun AppNavigationSearchDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val title = "Titolo pagina"
        SirioAppNavigationSearch(
            title = title,
            rightFirstItem = SirioAppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
            searchText = "",
            placeholderText = "Placeholder search bar",
            onSearchTextChanged = {},
        )
        SirioAppNavigationSearch(
            title = title,
            rightFirstItem = SirioAppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = SirioAppNavigationItemData(icon = FaIcons.Bell, action = {}),
            searchText = "Lorem ipsum ",
            placeholderText = "Placeholder search bar",
            onSearchTextChanged = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppNavigationLogoPreview() {
    AppNavigationDemo {
        AppNavigationLogoDemoContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun AppNavigationTitlePreview() {
    AppNavigationDemo {
        AppNavigationTitleDemoContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun AppNavigationSearchPreview() {
    AppNavigationDemo {
        AppNavigationSearchDemoContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun AppNavigationSelectionPreview() {
    AppNavigationDemo {
        AppNavigationSelectionDemoContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun AppNavigationFunctionPreview() {
    AppNavigationDemo {
        AppNavigationFunctionDemoContent()
    }
}

@Preview
@Composable
private fun AppNavigationDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SirioTheme(darkTheme = false) {
            AppNavigationDemoView()
        }
    }
}

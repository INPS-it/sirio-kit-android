//
// AppNavigationActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.appnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.ui.appnavigation.*
import it.inps.sirio.theme.SirioTheme
import it.inps.design.ui.DemoMenuItem

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
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = title) }, backgroundColor = SirioTheme.colors.brand)
        }

    ) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun AppNavigationMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem("Logo") {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_LOGO_ROUTE)
        }
        Divider()
        DemoMenuItem("Standard Title") {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_STANDARD_ROUTE)
        }
        Divider()
        DemoMenuItem(title = "Long Title") {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_LONG_TITLE_ROUTE)
        }
        Divider()
        DemoMenuItem("Big Title") {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_BIG_ROUTE)
        }
        Divider()
        DemoMenuItem("Selection") {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_SELECTION_ROUTE)
        }
        Divider()
        DemoMenuItem("Search") {
            navController.navigate(AppNavigationDestinations.APPNAVIGATION_SEARCH_ROUTE)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationLogoDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppNavigationLogo(
            rightFirstItem = AppNavigationItemData(
                icon = FaIcons.User,
                action = {}),
            rightSecondItem = AppNavigationItemData(
                icon = FaIcons.Bell,
                action = {},
            )
        )
        AppNavigationLogo(
            rightFirstItem = AppNavigationItemData(
                username = "MC",
                action = {}),
            rightSecondItem = AppNavigationItemData(
                icon = FaIcons.Bell,
                action = {},
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationStandardDemoContent() {
    val title = "Titolo pagina"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppNavigation(
            title = title,
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigation(
            title = title,
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigation(
            title = title,
            rightFirstItem = AppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigation(
            title = title,
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationLongTitleDemoContent() {
    val title = "Titolo pagina molto lungo\nsu due righe"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppNavigationLongTitle(
            title = title,
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigationLongTitle(
            title = title,
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigationLongTitle(
            title = title,
            rightFirstItem = AppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigationLongTitle(
            title = title,
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationBigDemoContent() {
    val title = "Titolo Grande"
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppNavigationBig(
            title = title,
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigationBig(
            title = title,
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigationBig(
            title = title,
            rightFirstItem = AppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
        AppNavigationBig(
            title = title,
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(username = "MC", action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
        )
    }
}

@Composable
fun AppNavigationSelectionDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppNavigationSelection(
            title = "1 Elemento",
            rightFirstItem = AppNavigationItemData(icon = FaIcons.Download, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Trash, action = {}),
        )
        AppNavigationSelection(
            title = "1 Elemento",
            leftItem = AppNavigationItemData(icon = FaIcons.AngleLeft, action = {}),
            rightFirstItem = AppNavigationItemData(icon = FaIcons.Download, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Trash, action = {}),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationSearchDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val title = "Titolo pagina"
        AppNavigationSearch(
            title = title,
            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
            searchText = "",
            placeholderText = "Placeholder search bar",
            onSearchTextChanged = {},
        )
//        AppNavigationSearch(
//            title = title,
//            rightFirstItem = AppNavigationItemData(icon = FaIcons.User, action = {}),
//            rightSecondItem = AppNavigationItemData(icon = FaIcons.Bell, action = {}),
//            searchText = "Lorem ipsum ",
//            placeholderText = "Ricerca",
//            onSearchTextChanged = {},
//        )
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun AppNavigationPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SirioTheme(darkTheme = false) {
            AppNavigationLogoDemoContent()
            AppNavigationStandardDemoContent()
            AppNavigationLongTitleDemoContent()
            AppNavigationBigDemoContent()
            AppNavigationSelectionDemoContent()
        }
        SirioTheme(darkTheme = true) {
            AppNavigationLogoDemoContent()
            AppNavigationStandardDemoContent()
            AppNavigationLongTitleDemoContent()
            AppNavigationBigDemoContent()
            AppNavigationSelectionDemoContent()
        }
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
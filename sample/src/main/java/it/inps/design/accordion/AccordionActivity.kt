//
// AccordionActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.accordion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.ui.accordion.Accordion
import it.inps.sirio.ui.accordion.AccordionData
import it.inps.sirio.ui.accordion.AccordionGroup
import it.inps.sirio.theme.SirioTheme
import it.inps.design.ui.DemoMenuItem

class AccordionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                AccordionDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AccordionDemoView() {
    val navController = rememberNavController()
    var title by remember { mutableStateOf("Accordion") }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = backStackEntry.destination.route ?: "Accordion"
        }
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = title) }, backgroundColor = SirioTheme.colors.brand)
        }
    ) {
        AccordionNavigationGraph(navController = navController)
    }
}

@Composable
fun AccordionMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp),
    ) {
        DemoMenuItem("Accordion") {
            navController.navigate(AccordionDestinations.ACCORDION_ROUTE)
        }
        Divider()
        DemoMenuItem("Accordion Group") {
            navController.navigate(AccordionDestinations.ACCORDION_GROUP_ROUTE)
        }
    }
}

@Composable
fun AccordionDemo(content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E5E5))
            .padding(0.dp, 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = "Light")
        SirioTheme(darkTheme = false) {
            content()
        }
        Text(text = "Dark")
        SirioTheme(darkTheme = true) {
            content()
        }
    }
}

@Composable
fun AccordionNavDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(80.dp)
    ) {
        val title = "Accordion #1"
        val content = LoremIpsum(30).values.joinToString()
        var isOpen1 by remember { mutableStateOf(false) }
        var isOpen2 by remember { mutableStateOf(false) }
        Accordion(data = AccordionData(text = title, open = isOpen1, enabled = true,
            onTapAccordion = { isOpen1 = it }) {
            Text(text = content)
        })
        Spacer(modifier = Modifier.height(8.dp))
        Accordion(data = AccordionData(text = title, open = isOpen2, enabled = false,
            onTapAccordion = { isOpen2 = it }) {
            Text(text = content)
        })
    }
}

@Composable
fun AccordionGroupNavDemoContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val content = LoremIpsum(30).values.joinToString()
        var isOpen1 by remember { mutableStateOf(false) }
        var isOpen2 by remember { mutableStateOf(false) }
        var isOpen3 by remember { mutableStateOf(false) }
        AccordionGroup(
            data = listOf(
                AccordionData(text = "Accordion #1",
                    open = isOpen1,
                    enabled = true,
                    onTapAccordion = { isOpen1 = it }) {
                    Text(text = content)
                },
                AccordionData(text = "Accordion #2",
                    open = isOpen2,
                    enabled = true,
                    onTapAccordion = { isOpen2 = it }) {
                    Text(text = content)
                },
                AccordionData(text = "Accordion #3",
                    open = isOpen3,
                    enabled = false,
                    onTapAccordion = { isOpen3 = it }) {
                    Text(text = content)
                }
            ),
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccordionDemoContent() {
    val content = LoremIpsum(30).values.joinToString()
    var isOpen1 by remember { mutableStateOf(false) }
    var isOpen2 by remember { mutableStateOf(false) }
    var isOpen3 by remember { mutableStateOf(true) }
    var isOpen4 by remember { mutableStateOf(true) }
    var isOpen5 by remember { mutableStateOf(false) }
    var isOpen6 by remember { mutableStateOf(false) }
    val accordionData: List<AccordionData> = listOf(
        AccordionData(
            "Accordion Item #1",
            open = isOpen1,
            enabled = true,
            onTapAccordion = { isOpen1 = it }) { Text(text = content) },
        AccordionData(
            "Accordion Item #2",
            open = isOpen2,
            enabled = true,
            onTapAccordion = { isOpen2 = it }) { Text(text = content) },
        AccordionData(
            "Accordion Item #3",
            open = isOpen3,
            enabled = true,
            onTapAccordion = { isOpen3 = it }) { Text(text = content) },
        AccordionData(
            "Accordion Item #4",
            open = isOpen4,
            enabled = true,
            onTapAccordion = { isOpen4 = it }) { Text(text = content) },
        AccordionData(
            "Accordion Item #5",
            open = isOpen5,
            enabled = false,
            onTapAccordion = { isOpen5 = it }) { Text(text = content) },
        AccordionData(
            "Accordion Item #6",
            open = isOpen6,
            enabled = false,
            onTapAccordion = { isOpen6 = it }) { Text(text = content) },
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accordion") },
            )
        }) {
        AccordionGroup(data = accordionData.shuffled())
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun AccordionActivityPreview() {
    SirioTheme {
        AccordionDemoContent()
    }
}
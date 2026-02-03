//
// AccordionActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.accordion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.accordion.SirioAccordion
import it.inps.sirio.ui.accordion.SirioAccordionColor
import it.inps.sirio.ui.accordion.SirioAccordionData
import it.inps.sirio.ui.accordion.SirioAccordionGroup
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.utils.SirioIconSource

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
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SirioTheme.colors.brand,
                ),
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            AccordionNavigationGraph(navController = navController)
        }
    }
}

@Composable
fun AccordionMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(0.dp, 16.dp),
    ) {
        SirioListItem("Accordion") {
            navController.navigate(AccordionDestinations.ACCORDION_ROUTE)
        }
        SirioListItem("Accordion Group", showDivider = false) {
            navController.navigate(AccordionDestinations.ACCORDION_GROUP_ROUTE)
        }
    }
}

@Composable
fun AccordionDemo(contentLight: @Composable () -> Unit, contentDark: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(0.dp, 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = "Light")
        contentLight()
        Text(text = "Dark")
        contentDark()
    }
}

@Composable
fun AccordionNavDemoContent(color: SirioAccordionColor) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(80.dp)
    ) {
        val content = LoremIpsum(30).values.joinToString()
        var isOpen1 by remember { mutableStateOf(false) }
        var isOpen2 by remember { mutableStateOf(false) }
        var isOpen3 by remember { mutableStateOf(false) }
        var isOpen4 by remember { mutableStateOf(false) }
        var isOpen5 by remember { mutableStateOf(false) }
        SirioAccordion(
            data = SirioAccordionData.Default(
                title = "Accordion title #1",
                open = isOpen1,
                onTapAccordion = { isOpen1 = it },
            ) { Text(text = content) },
            color = color,
        )
        Spacer(modifier = Modifier.height(8.dp))
        SirioAccordion(
            data = SirioAccordionData.WithIcon(
                title = "Accordion title #2",
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                open = isOpen2,
                onTapAccordion = { isOpen2 = it },
            ) { Text(text = content) },
            color = color,
        )
        Spacer(modifier = Modifier.height(8.dp))
        SirioAccordion(
            data = SirioAccordionData.WithText(
                title = "Accordion title #3",
                text = "Text",
                open = isOpen3,
                onTapAccordion = { isOpen3 = it },
            ) { Text(text = content) },
            color = color,
        )
        Spacer(modifier = Modifier.height(8.dp))
        SirioAccordion(
            data = SirioAccordionData.WithTag(
                title = "Accordion title #4",
                tag = "Tag",
                open = isOpen4,
                onTapAccordion = { isOpen4 = it },
            ) { Text(text = content) },
            color = color,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun AccordionGroupNavDemoContent(color: SirioAccordionColor) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val content = LoremIpsum(30).values.joinToString()
        var isOpen11 by remember { mutableStateOf(false) }
        var isOpen12 by remember { mutableStateOf(false) }
        var isOpen13 by remember { mutableStateOf(false) }
        var isOpen21 by remember { mutableStateOf(false) }
        var isOpen22 by remember { mutableStateOf(false) }
        var isOpen23 by remember { mutableStateOf(false) }
        var isOpen31 by remember { mutableStateOf(false) }
        var isOpen32 by remember { mutableStateOf(false) }
        var isOpen33 by remember { mutableStateOf(false) }
        var isOpen41 by remember { mutableStateOf(false) }
        var isOpen42 by remember { mutableStateOf(false) }
        var isOpen43 by remember { mutableStateOf(false) }
        var isOpen51 by remember { mutableStateOf(false) }
        var isOpen52 by remember { mutableStateOf(false) }
        var isOpen53 by remember { mutableStateOf(false) }
        SirioAccordionGroup(
            data = listOf(
                SirioAccordionData.Default(
                    title = "Accordion title #1",
                    open = isOpen11,
                    onTapAccordion = { isOpen11 = it },
                    content = { Text(text = content) }
                ),
                SirioAccordionData.Default(
                    title = "Accordion title #2",
                    open = isOpen12,
                    onTapAccordion = { isOpen12 = it }
                ) { Text(text = content) },
                SirioAccordionData.Default(
                    title = "Accordion title #3",
                    open = isOpen13,
                    onTapAccordion = { isOpen13 = it }
                ) { Text(text = content) }
            ),
            color = color,
        )
        SirioAccordionGroup(
            data = listOf(
                SirioAccordionData.WithIcon(
                    title = "Accordion title #1",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    open = isOpen21,
                    onTapAccordion = { isOpen21 = it },
                    content = { Text(text = content) }
                ),
                SirioAccordionData.WithIcon(
                    title = "Accordion title #2",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    open = isOpen22,
                    onTapAccordion = { isOpen22 = it }
                ) { Text(text = content) },
                SirioAccordionData.WithIcon(
                    title = "Accordion title #3",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    open = isOpen23,
                    onTapAccordion = { isOpen23 = it }
                ) { Text(text = content) }
            ),
            color = color,
        )
        SirioAccordionGroup(
            data = listOf(
                SirioAccordionData.WithText(
                    title = "Accordion title #1",
                    text = "Text",
                    open = isOpen31,
                    onTapAccordion = { isOpen31 = it },
                    content = { Text(text = content) }
                ),
                SirioAccordionData.WithText(
                    title = "Accordion title #2",
                    text = "Text",
                    open = isOpen32,
                    onTapAccordion = { isOpen32 = it }
                ) { Text(text = content) },
                SirioAccordionData.WithText(
                    title = "Accordion title #3",
                    text = "Text",
                    open = isOpen33,
                    onTapAccordion = { isOpen33 = it }
                ) { Text(text = content) }
            ),
            color = color,
        )
        SirioAccordionGroup(
            data = listOf(
                SirioAccordionData.WithTag(
                    title = "Accordion title #1",
                    tag = "Tag",
                    open = isOpen41,
                    onTapAccordion = { isOpen41 = it },
                    content = { Text(text = content) }
                ),
                SirioAccordionData.WithTag(
                    title = "Accordion title #2",
                    tag = "Tag",
                    open = isOpen42,
                    onTapAccordion = { isOpen42 = it }
                ) { Text(text = content) },
                SirioAccordionData.WithTag(
                    title = "Accordion title #3",
                    tag = "Tag",
                    open = isOpen43,
                    onTapAccordion = { isOpen43 = it }
                ) { Text(text = content) }
            ),
            color = color,
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
    val sirioAccordionData: List<SirioAccordionData> = listOf(
        SirioAccordionData.Default(
            title = "Accordion Item #1",
            open = isOpen1,
            onTapAccordion = { isOpen1 = it },
            content = { Text(text = content) }
        ),
        SirioAccordionData.Default(
            title = "Accordion Item #2",
            open = isOpen2,
            onTapAccordion = { isOpen2 = it },
            content = { Text(text = content) }
        ),
        SirioAccordionData.Default(
            title = "Accordion Item #3",
            open = isOpen3,
            onTapAccordion = { isOpen3 = it },
            content = { Text(text = content) }
        ),
        SirioAccordionData.Default(
            title = "Accordion Item #4",
            open = isOpen4,
            onTapAccordion = { isOpen4 = it },
            content = { Text(text = content) }
        ),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accordion") },
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                SirioAccordionGroup(data = sirioAccordionData)
            }
        }
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

@Preview
@Composable
private fun AccordionNavDemoContentPreview() {
    SirioTheme {
        AccordionNavDemoContent(SirioAccordionColor.LIGHT)
    }
}

@Preview
@Composable
private fun AccordionGroupNavDemoContentPreview() {
    SirioTheme {
        AccordionGroupNavDemoContent(SirioAccordionColor.LIGHT)
    }
}

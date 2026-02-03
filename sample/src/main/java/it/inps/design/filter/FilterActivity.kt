//
// FilterActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.filter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.card.SirioCardColor
import it.inps.sirio.ui.card.SirioProcessCard
import it.inps.sirio.ui.card.SirioProcessCardItemData
import it.inps.sirio.ui.card.SirioProcessCardType
import it.inps.sirio.ui.filter.SirioFilter
import it.inps.sirio.ui.filter.SirioFilterDrawerType
import it.inps.sirio.ui.filter.items.SirioFilterAction
import it.inps.sirio.ui.filter.items.SirioFilterChips
import it.inps.sirio.ui.filter.items.SirioFilterDrawerButton
import it.inps.sirio.ui.filter.items.SirioFilterDrawerCheckbox
import it.inps.sirio.ui.filter.items.SirioFilterDrawerDate
import it.inps.sirio.ui.filter.items.SirioFilterDrawerHeader
import it.inps.sirio.ui.filter.items.SirioFilterDrawerRadio
import it.inps.sirio.ui.filter.items.SirioFilterDrawerSelect
import it.inps.sirio.ui.filter.items.SirioFilterDrawerTitle
import it.inps.sirio.ui.filter.items.SirioFilterDrawerToggle
import it.inps.sirio.ui.filter.items.SirioFilterFab
import it.inps.sirio.ui.filter.items.SirioFilterSelected
import it.inps.sirio.ui.filter.items.SirioFilterSelectedChipData
import it.inps.sirio.ui.filter.rememberSirioFilterState
import it.inps.sirio.ui.listItem.SirioListItem

private const val COMPONENTS = "Components"
private const val DRAWER = "Drawer"
private const val DEMO = "Demo"

class FilterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                FilterDemoView()
            }
        }
    }
}

@Composable
private fun FilterDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Filtri") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "/",
            modifier = Modifier.padding(it),
        ) {
            composable("/") {
                FilterMenuDemo(navController = navController)
            }
            composable(COMPONENTS) {
                FilterComponentsDemo()
            }
            composable(DRAWER) {
                FilterDrawerDemo()
            }
            composable(DEMO) {
                FilterDemo()
            }
        }
    }
}

@Composable
fun FilterMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        SirioListItem(COMPONENTS) {
            navController.navigate(COMPONENTS)
        }
        SirioListItem(DRAWER) {
            navController.navigate(DRAWER)
        }
        SirioListItem(DEMO, showDivider = false) {
            navController.navigate(DEMO)
        }
    }
}

@Composable
private fun FilterComponentsDemo() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Chips")
        val selectedChips = remember { mutableStateSetOf("Valore Selezionato") }
        val stateChips = remember {
            mutableStateListOf(
                elements = arrayOf(
                    "Valore Selezionato",
                    "Valore B",
                    "Valore C",
                    "Valore D",
                    "Valore E"
                )
            )
        }
        SirioFilterChips(
            values = stateChips,
            selectedValues = selectedChips,
            onSelectionChanged = {
                selectedChips.clear()
                selectedChips.addAll(it)
            },
        )
        Text(text = "Action")
        SirioFilterAction { }
        Text(text = "FAB")
        SirioFilterFab { }
        Text(text = "Selected")
        val chips = remember {
            mutableStateListOf(
                elements = arrayOf(
                    "Valore A",
                    "Valore B",
                    "Valore C",
                    "Valore D",
                    "Valore E"
                )
            )
        }
        SirioFilterSelected(
            chips = chips.map { value ->
                SirioFilterSelectedChipData(
                    key = value,
                    text = value,
                    onClose = { chips.remove(value) }
                )
            },
        )
    }
}

@Composable
private fun FilterDrawerDemo() {
    Column(
        Modifier
            .fillMaxSize()
            .background(StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 20.dp, horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(31.dp)
    ) {
        var radio by remember { mutableStateOf(false) }
        var checkbox by remember { mutableStateOf(false) }
        var toggle by remember { mutableStateOf(false) }
        val chips = remember { mutableStateSetOf(elements = arrayOf<String>()) }
        var select by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        SirioFilterDrawerHeader(title = "Filtri", onClose = {})
        SirioFilterDrawerTitle(
            title = "Section Title",
            text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
        )
        SirioFilterChips(
            values = listOf(
                "Valore 1",
                "Valore 2",
                "Valore 3",
                "Valore 4",
                "Valore 5",
                "Valore 6"
            ),
            selectedValues = chips,
            onSelectionChanged = {
                chips.clear()
                chips.addAll(it)
            }
        )
        SirioFilterDrawerSelect(
            label = "Label",
            selectedValue = select,
            placeholder = "Text",
            values = listOf("Valore 1", "Valore 2", "Valore 3", "Valore 4", "Valore 5"),
            onValueChange = { select = it },
        )
        SirioFilterDrawerDate(
            dateFormat = "dd/MM/yyyy",
            label = "Label",
            selectedDate = date,
            placeholder = "Text",
            onDateSelected = { date = it }
        )
        SirioFilterDrawerToggle(checked = toggle, text = "Title", onToggleChange = { toggle = it })
        SirioFilterDrawerRadio(selected = radio, text = "Title", onClick = { radio = !radio })
        SirioFilterDrawerCheckbox(
            checked = checkbox,
            text = "Title",
            onCheckedChange = { checkbox = it })
        SirioFilterDrawerButton(
            leftButtonText = "Elimina filtri",
            rightButtonText = "Applica filtri",
            onLeftButtonClick = {},
            onRightButtonClick = {}
        )
    }
}

@Composable
private fun FilterDemo() {
    val initialFilters = listOf(
        SirioFilterDrawerType.Title(
            id = "title-1",
            title = "Section Title",
            text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor."
        ),
        SirioFilterDrawerType.Chip(
            id = "chip-1",
            values = listOf("Valore selezionato", "Valore 1", "Valore 2", "Valore 3"),
            selectedValues = setOf(),
        ),
        SirioFilterDrawerType.Title(
            id = "title-2",
            title = "Section Title",
        ),
        SirioFilterDrawerType.Select(
            id = "select-1",
            label = "Select",
            values = listOf("A", "B", "C", "D", "E"),
            placeholder = "Text",
        ),
        SirioFilterDrawerType.Date(
            id = "date-1",
            label = "Date",
            placeholder = "Text",
            dateFormat = "dd/MM/yyyy",
        ),
        SirioFilterDrawerType.Title(
            id = "title-3",
            title = "Section Title",
        ),
        SirioFilterDrawerType.Toggle(
            id = "toggle-1",
            checked = false,
            text = "Toggle A",
        ),
        SirioFilterDrawerType.Toggle(
            id = "toggle-2",
            checked = false,
            text = "Toggle B",
        ),
        SirioFilterDrawerType.Title(
            id = "title-4",
            title = "Section Title",
        ),
        SirioFilterDrawerType.RadioGroup(
            id = "radio-1",
            values = listOf("Radio A", "Radio B"),
        ),
        SirioFilterDrawerType.Title(
            id = "title-5",
            title = "Section Title",
        ),
        SirioFilterDrawerType.Checkbox(
            id = "checkbox-1",
            checked = false,
            text = "Checkbox A",
        ),
        SirioFilterDrawerType.Checkbox(
            id = "checkbox-2",
            checked = false,
            text = "Checkbox B",
        ),
    )
    val state = rememberSirioFilterState(initialFilters)

    data class DemoData(
        val title: String,
        val subtitle: String,
    )

    val demoItems = remember {
        listOf(
            DemoData(
                title = "Chip 1",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Select 1",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Date 1",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Toggle 1",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Radio 1",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Checkbox 1",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Chip 2",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Select 2",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Date 2",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Toggle 2",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Radio 2",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
            DemoData(
                title = "Checkbox 2",
                subtitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
            ),
        )
    }

    var itemsToShow by remember { mutableStateOf(demoItems) }

    SirioFilter(
        title = "Titolo della sezione",
        items = itemsToShow,
        key = { it.title },
        itemContent = {
            SirioProcessCard(
                title = it.title,
                type = SirioProcessCardType.Standard(
                    firstAction = SirioProcessCardItemData(
                        text = "Text",
                        action = {},
                    )
                ),
                text = it.subtitle,
                color = SirioCardColor.LIGHT,
                onClickCard = { },
            )
        },
        filterState = state,
        onApplyFilters = { filters ->
            // 1️⃣ Prendo solo i filtri attivi
            val activeFilters = filters.filter { it.isActive() }

            // 2️⃣ Se nessun filtro attivo → mostra tutto
            if (activeFilters.isEmpty()) {
                itemsToShow = demoItems
                return@SirioFilter
            }

            // 3️⃣ Estrai i prefissi degli ID per semplicità (chip, toggle, ecc.)
            val activeTypes = activeFilters.map { it.id.substringBefore("-").lowercase() }.toSet()

            // 4️⃣ Filtra i dati
            itemsToShow = demoItems.filter { demo ->
                activeTypes.contains(demo.title.substringBefore(" ").lowercase())
            }
        },
    )
}

@Preview
@Composable
private fun FilterActivityPreview() {
    SirioTheme {
        FilterDemoView()
    }
}

@Preview
@Composable
private fun FilterTabsDemoPreview() {
    SirioTheme {
        FilterDemo()
    }
}

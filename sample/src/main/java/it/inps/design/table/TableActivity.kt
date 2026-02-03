@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.table

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.notifiche.NotificheDestinations
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.listItem.SirioListItem
import it.inps.sirio.ui.table.SirioTable
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.ui.table.SirioTableRowData
import it.inps.sirio.ui.table.SirioTableSort
import it.inps.sirio.ui.table.SirioTableSortKey
import it.inps.sirio.ui.table.card.SirioTable
import it.inps.sirio.ui.table.card.SirioTableCardData
import it.inps.sirio.ui.table.card.SirioTableCardSort
import it.inps.sirio.ui.table.card.SirioTableCardType
import it.inps.sirio.ui.table.cell.SirioTableCellType
import it.inps.sirio.ui.table.cell.SirioTableContentAlignment
import it.inps.sirio.ui.table.cell.SirioTableContentSize
import it.inps.sirio.ui.table.drawer.SirioTableDrawer
import it.inps.sirio.ui.table.drawer.SirioTableDrawerType
import it.inps.sirio.ui.table.header.SirioTableSortDirection
import it.inps.sirio.ui.table.sortedForSirioTable
import it.inps.sirio.utils.SirioIconSource
import kotlin.random.Random

private const val SMALL_DARK = "Table - Small - Dark"
private const val SMALL_LIGHT = "Table - Small - Light"
private const val SMALL_DARK_MORE = "Table - Small - Dark - More"
private const val LARGE_DARK = "Table - Large - Dark"
private const val LARGE_LIGHT = "Table - Large - Light"
private const val LARGE_DARK_LONG = "Table - Large - Dark - Long text"
private const val HORIZONTAL_SCROLL = "Table - Horizontal scroll"
private const val ALTERNATE_ROWS = "Table - Alternate rows"
private const val DRAWER = "Table - Drawer"
private const val CARD = "Table - Card"
private const val INTERACTIVE = "Table - Demo"


class TableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                TableDemoView()
            }
        }
    }
}

@Composable
private fun TableDemoView() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SirioTheme.colors.table.card.background,
        topBar = {
            TopAppBar(
                title = { Text(text = "Table") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }
    ) {
        val navController = rememberNavController()
        var title by remember { mutableStateOf(NotificheDestinations.NOTIFICHE_MENU_ROUTE) }
        LaunchedEffect(navController) {
            navController.currentBackStackEntryFlow.collect { backStackEntry ->
                title =
                    backStackEntry.destination.route ?: NotificheDestinations.NOTIFICHE_MENU_ROUTE
            }
        }
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (title != "/") Text(text = title, modifier = Modifier.padding(20.dp))
            NavHost(
                navController = navController,
                startDestination = "/",
            ) {
                composable("/") {
                    TableMenuDemo(navController = navController)
                }
                composable(SMALL_DARK) {
                    TableSampleDemo(
                        size = SirioTableContentSize.Small,
                        themeMode = SirioThemeMode.Dark
                    )
                }
                composable(SMALL_LIGHT) {
                    TableSampleDemo(
                        size = SirioTableContentSize.Small,
                        themeMode = SirioThemeMode.Light
                    )
                }
                composable(SMALL_DARK_MORE) {
                    TableSmallMoreDemo(
                        size = SirioTableContentSize.Small,
                        themeMode = SirioThemeMode.Dark
                    )
                }
                composable(LARGE_DARK) {
                    TableOneActionDemo(
                        size = SirioTableContentSize.Large,
                        themeMode = SirioThemeMode.Dark,
                    )
                }
                composable(LARGE_LIGHT) {
                    TableMultiActionDemo(
                        size = SirioTableContentSize.Large,
                        themeMode = SirioThemeMode.Light
                    )
                }
                composable(LARGE_DARK_LONG) {
                    TableOneActionLongTextDemo(
                        size = SirioTableContentSize.Large,
                        themeMode = SirioThemeMode.Dark
                    )
                }
                composable(HORIZONTAL_SCROLL) {
                    TableHorizontalScrollDemo(
                        size = SirioTableContentSize.Large,
                        themeMode = SirioThemeMode.Dark,
                    )
                }
                composable(ALTERNATE_ROWS) {
                    TableOneActionDemo(
                        size = SirioTableContentSize.Large,
                        themeMode = SirioThemeMode.Dark,
                        alternateRows = true,
                    )
                }
                composable(CARD) {
                    TableCardDemo()
                }
                composable(DRAWER) {
                    TableDrawerDemo()
                }
                composable(INTERACTIVE) {
                    TableInteractiveDemo(
                        size = SirioTableContentSize.Large,
                        themeMode = SirioThemeMode.Dark,
                        alternateRows = true,
                    )
                }
            }
        }
    }
}

@Composable
private fun TableSampleDemo(size: SirioTableContentSize, themeMode: SirioThemeMode) {
    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
                sortable = true,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
        ),
        size = size,
        themeMode = themeMode,
        rows = List(8) {
            SirioTableRowData(
                cells = listOf(
                    SirioTableCellType.Link(
                        text = "Link",
                        onLinkClick = {},
                    ),
                    SirioTableCellType.NumberOnly(
                        text = "00",
                    ),
                    SirioTableCellType.TextOnly(
                        text = "Lorem ipsum",
                    ),
                )
            )
        },
    )
}

@Composable
private fun TableOneActionDemo(
    size: SirioTableContentSize,
    themeMode: SirioThemeMode,
    alternateRows: Boolean = false,
) {
    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
                sortable = true,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 0.6f,
                withCheckBox = false,
                sortable = false,
            ),
        ),
        size = SirioTableContentSize.Large,
        themeMode = SirioThemeMode.Dark,
        alternateRows = alternateRows,
        rows = List(8) {
            SirioTableRowData(
                cells = listOf(
                    SirioTableCellType.Text(
                        text = "Title",
                        checked = false,
                        onCheckedChange = {},
                    ),
                    SirioTableCellType.Tag(
                        text = "Label Tag",
                    ),
                    SirioTableCellType.Action(
                        icon = SirioIconSource.FaIcon(FaIcons.Eye),
                        onClick = {},
                    ),
                )
            )
        },
    )
}

@Composable
private fun TableMultiActionDemo(size: SirioTableContentSize, themeMode: SirioThemeMode) {
    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
                sortable = true,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 0.6f,
                withCheckBox = false,
                sortable = false,
            ),
        ),
        size = SirioTableContentSize.Large,
        themeMode = SirioThemeMode.Light,
        rows = List(8) {
            SirioTableRowData(
                cells = listOf(
                    SirioTableCellType.Text(
                        text = "Title",
                        checked = false,
                        onCheckedChange = {},
                    ),
                    SirioTableCellType.Tag(
                        text = "Label Tag",
                    ),
                    SirioTableCellType.Action(
                        icon = SirioIconSource.FaIcon(FaIcons.Eye),
                        actions = listOf(
                            SirioTableActionData(
                                text = "Action #1",
                                action = {},
                            ),
                            SirioTableActionData(
                                text = "Action #2",
                                action = {},
                            ),
                            SirioTableActionData(
                                text = "Action #3",
                                action = {},
                            ),
                        ),
                        onClick = {},
                    ),
                )
            )
        },
    )
}

@Composable
private fun TableOneActionLongTextDemo(size: SirioTableContentSize, themeMode: SirioThemeMode) {
    SirioTable(
        title = "Titolo della tabella molto molto molto molto lungo su due righe",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
                sortable = true,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Azioni",
                alignment = SirioTableContentAlignment.Start,
                weight = 0.6f,
                withCheckBox = false,
                sortable = false,
            ),
        ),
        size = SirioTableContentSize.Large,
        themeMode = SirioThemeMode.Dark,
        rows = List(8) {
            SirioTableRowData(
                cells = listOf(
                    SirioTableCellType.Text(
                        text = "Vivamus at nunc non arcu",
                        checked = false,
                        onCheckedChange = {},
                    ),
                    SirioTableCellType.Tag(
                        text = "Tag con testo lungo",
                    ),
                    SirioTableCellType.Action(
                        icon = SirioIconSource.FaIcon(FaIcons.Eye),
                        onClick = {},
                    ),
                )
            )
        },
    )
}

@Composable
private fun TableSmallMoreDemo(size: SirioTableContentSize, themeMode: SirioThemeMode) {
    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Action #1",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Action #2",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Action #3",
                action = {},
            ),
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
                sortable = true,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
                weight = 1.6f,
            ),
        ),
        size = size,
        themeMode = themeMode,
        rows = List(8) {
            SirioTableRowData(
                cells = listOf(
                    SirioTableCellType.Link(
                        text = "Link",
                        onLinkClick = {},
                    ),
                    SirioTableCellType.NumberOnly(
                        text = "00",
                    ),
                    SirioTableCellType.TextOnly(
                        text = "Sed at felis quis urna porttitor aliquam",
                    ),
                )
            )
        },
    )
}

@Composable
private fun TableHorizontalScrollDemo(
    size: SirioTableContentSize,
    themeMode: SirioThemeMode,
    alternateRows: Boolean = false,
) {
    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
                sortable = true,
                weight = 1.5f,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                withCheckBox = false,
                weight = 1.8f,
            ),
        ),
        size = SirioTableContentSize.Large,
        themeMode = SirioThemeMode.Dark,
        alternateRows = alternateRows,
        horizontalScroll = true,
        rows = List(8) {
            SirioTableRowData(
                cells = listOf(
                    SirioTableCellType.TextOnly(
                        text = "Lorem ipsum",
                    ),
                    SirioTableCellType.NumberOnly(
                        text = "00"
                    ),
                    SirioTableCellType.Tag(
                        text = "Label Tag",
                    ),
                )
            )
        },
    )
}

@Composable
private fun TableCardDemo() {
    var showSort by remember { mutableStateOf(false) }
    val icons = listOf(
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.FilePdf), action = {}),
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Download), action = {}),
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
    )
    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = { showSort = true },
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        cards = listOf(
            SirioTableCardData(
                items = listOf(
                    SirioTableCardType.Text(
                        title = "Header",
                        text = "Lorem ipsum sagittis egestas at proin",
                    ),
                    SirioTableCardType.Number(
                        title = "Header",
                        text = "00",
                    ),
                    SirioTableCardType.Link(
                        title = "Header",
                        text = "Link",
                    ),
                    SirioTableCardType.Tag(
                        title = "Header",
                        text = "Label Tag",
                    ),
                    SirioTableCardType.MultiIcons(
                        actions = icons,
                    ),
                ),
            ),
            SirioTableCardData(
                items = listOf(
                    SirioTableCardType.Text(
                        title = "Header",
                        text = "Lorem ipsum sagittis egestas at proin",
                    ),
                    SirioTableCardType.Number(
                        title = "Header lungo su due righe",
                        text = "00",
                    ),
                    SirioTableCardType.Link(
                        title = "Header",
                        text = "Link",
                    ),
                    SirioTableCardType.Tag(
                        title = "Header",
                        text = "Label Tag",
                    ),
                    SirioTableCardType.MultiIcons(
                        actions = icons,
                    ),
                ),
            ),
        ),
    )
    if (showSort) {
        SirioTableCardSort(
            title = "Ordina",
            buttonText = "Applica",
            options = List(6) { "Lorem ipsum sagittis" },
            onDismiss = { showSort = false },
            onButtonClick = { showSort = false }
        )
    }
}

@Composable
private fun TableDrawerDemo(
) {
    var showDrawer by remember { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        SirioButton(
            size = SirioButtonSize.Large,
            hierarchy = SirioButtonHierarchy.GhostLight,
            text = "Apri il drawer"
        ) { showDrawer = true }
    }
    AnimatedVisibility(
        visible = showDrawer,
        enter = slideInHorizontally { it },
        exit = slideOutHorizontally { it },
    ) {
        SirioTableDrawer(
            title = "Titolo Tabella",
            onClose = { showDrawer = false },
            data = listOf(
                SirioTableDrawerType.Link(
                    title = "Header",
                    text = "Link",
                    onLinkClick = {},
                ),
                SirioTableDrawerType.Number(
                    title = "Header",
                    text = "00",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableDrawerType.Text(
                    title = "Header molto lungo su due righe",
                    text = "Lorem ipsum sagittis egestas at proin",
                ),
                SirioTableDrawerType.Tag(
                    title = "Header",
                    text = "Label Tag",
                ),
            ),
            icons = listOf(
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Print), action = {}),
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Download), action = {}),
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
                SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
            )
        )
    }
}

@Composable
private fun TableInteractiveDemo(
    size: SirioTableContentSize,
    themeMode: SirioThemeMode,
    alternateRows: Boolean = false,
) {
    data class RowItem(
        val id: Long,
        val title: String,
        val number: Int,
        val letter: Char,
        val checked: Boolean,
    )

    val items = remember {
        mutableStateListOf<RowItem>().apply {
            repeat(8) { i ->
                add(
                    RowItem(
                        id = i.toLong(),
                        title = "Title $i",
                        number = Random.nextInt(0, 11),
                        letter = ('A'..'Z').random(),
                        checked = false
                    )
                )
            }
        }
    }

    var tableSort by remember {
        mutableStateOf(SirioTableSort(columnIndex = 0, direction = SirioTableSortDirection.None))
    }

    val visibleItems = remember(items, tableSort) {
        items.sortedForSirioTable(
            sort = tableSort,
        ) { item, columnIndex, _ ->
            when (columnIndex) {
                0 -> SirioTableSortKey.StringKey(item.title)
                1 -> SirioTableSortKey.NumberKey(item.number.toDouble())
                2 -> SirioTableSortKey.StringKey(item.letter.toString())
                else -> null
            }
        }
    }

    // Mapping model -> righe della SirioTable
    val rows = visibleItems.map { item ->
        SirioTableRowData(
            cells = listOf(
                // Prima colonna con checkbox gestita da state "items"
                SirioTableCellType.Text(
                    text = item.title,
                    checked = item.checked,
                    onCheckedChange = { isChecked ->
                        val idx = items.indexOfFirst { it.id == item.id }
                        if (idx >= 0) {
                            items[idx] = items[idx].copy(checked = isChecked)
                        }
                    },
                ),
                SirioTableCellType.NumberOnly(
                    text = item.number.toString().padStart(2, '0'),
                ),
                SirioTableCellType.TextOnly(
                    text = item.letter.toString()
                ),
            )
        )
    }

    SirioTable(
        title = "Titolo tabella",
        actions = listOf(
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            ),
            SirioTableActionData(
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                text = "Label",
                action = {},
            )
        ),
        headers = listOf(
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
                sortable = true,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
            ),
            SirioTableCellType.Header(
                text = "Header",
                alignment = SirioTableContentAlignment.Start,
                weight = 1f,
                withCheckBox = false,
                sortable = true,
            ),
        ),
        size = SirioTableContentSize.Large,
        themeMode = SirioThemeMode.Dark,
        alternateRows = alternateRows,
        rows = rows,
        sort = tableSort,
        onSortChange = { tableSort = it }
    )
}

@Composable
fun TableMenuDemo(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            SirioListItem(SMALL_DARK) {
                navController.navigate(SMALL_DARK)
            }
            SirioListItem(SMALL_LIGHT) {
                navController.navigate(SMALL_LIGHT)
            }
            SirioListItem(SMALL_DARK_MORE) {
                navController.navigate(SMALL_DARK_MORE)
            }
            SirioListItem(LARGE_DARK) {
                navController.navigate(LARGE_DARK)
            }
            SirioListItem(LARGE_LIGHT) {
                navController.navigate(LARGE_LIGHT)
            }
            SirioListItem(LARGE_DARK_LONG) {
                navController.navigate(LARGE_DARK_LONG)
            }
            SirioListItem(HORIZONTAL_SCROLL) {
                navController.navigate(HORIZONTAL_SCROLL)
            }
            SirioListItem(ALTERNATE_ROWS) {
                navController.navigate(ALTERNATE_ROWS)
            }
            SirioListItem(CARD) {
                navController.navigate(CARD)
            }
            SirioListItem(DRAWER) {
                navController.navigate(DRAWER)
            }
            SirioListItem(INTERACTIVE, showDivider = false) {
                navController.navigate(INTERACTIVE)
            }
        }
    }
}

@Preview
@Composable
private fun TableActivityPreview() {
    SirioTheme {
        TableDemoView()
    }
}

@Preview
@Composable
private fun TableSmallDemoPreview() {
    SirioTheme {
        TableSampleDemo(SirioTableContentSize.Small, SirioThemeMode.Dark)
    }
}

@Preview
@Composable
private fun TableMediumLargePreview() {
    SirioTheme {
        TableSampleDemo(SirioTableContentSize.Large, SirioThemeMode.Dark)
    }
}

@Preview
@Composable
private fun TableCardDemoPreview() {
    SirioTheme {
        TableCardDemo()
    }
}

@Preview
@Composable
private fun TableHorizontalScrollDemoPreview() {
    SirioTheme {
        TableHorizontalScrollDemo(
            size = SirioTableContentSize.Large,
            themeMode = SirioThemeMode.Dark,
        )
    }
}

@Preview
@Composable
private fun TableDrawerPreview() {
    SirioTheme {
        TableDrawerDemo()
    }
}

@Preview
@Composable
private fun TableInteractivePreview() {
    SirioTheme {
        TableInteractiveDemo(
            size = SirioTableContentSize.Large,
            themeMode = SirioThemeMode.Dark,
            alternateRows = true,
        )
    }
}

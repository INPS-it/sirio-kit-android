@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.table

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.table.SirioTable
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentAlignment
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.table.SirioTableIconData
import it.inps.sirio.ui.table.SirioTableRowData
import it.inps.sirio.ui.table.drawer.SirioTableDrawer
import it.inps.sirio.ui.table.drawer.SirioTableDrawerItemData
import it.inps.sirio.ui.table.drawer.SirioTableDrawerItemType
import it.inps.sirio.ui.table.vertical.SirioTableVertical
import it.inps.sirio.ui.table.vertical.SirioTableVerticalCellData
import it.inps.sirio.ui.table.vertical.SirioTableVerticalCellItemData
import it.inps.sirio.ui.table.vertical.SirioTableVerticalCellItemType
import it.inps.sirio.ui.tag.SirioTagType

private const val XSMALL = "Table - xsmall"
private const val SMALL = "Table - small"
private const val MEDIUM = "Table - medium"
private const val LARGE = "Table - large"
private const val VERTICAL = "Table - vertical"
private const val DRAWERBARCOLLAPSED = "Drawer + bottom bar collapsed"
private const val DRAWERBAREXPANDED = "Drawer + bottom bar expanded"
private const val TABLE = "Table"
private const val TABLE1 = "Table 1"
private const val TABLE2 = "Table 2"
private const val TABLE3 = "Table 3"


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
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Menu spalla") },
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
                TableMenuDemo(navController = navController)
            }
            composable(XSMALL) {
                TableSizeDemo(SirioTableContentSize.EXTRASMALL)
            }
            composable(SMALL) {
                TableSizeDemo(SirioTableContentSize.SMALL)
            }
            composable(MEDIUM) {
                TableSizeDemo(SirioTableContentSize.MEDIUM)
            }
            composable(LARGE) {
                TableSizeDemo(SirioTableContentSize.LARGE)
            }
            composable(VERTICAL) {
                TableVerticalDemo()
            }
            composable(DRAWERBARCOLLAPSED) {
                TableDrawerBarCollapsedDemo()
            }
            composable(DRAWERBAREXPANDED) {
                TableDrawerBarExpandedDemo()
            }
            composable(TABLE) {
                TableSubMenuDemo(navController)
            }
            composable(TABLE1) {
                Table1Demo()
            }
            composable(TABLE2) {
                Table2Demo()
            }
            composable(TABLE3) {
                Table3Demo()
            }
        }
    }
}

@Composable
private fun TableSizeDemo(size: SirioTableContentSize) {
    Column(
        modifier = Modifier.background(SirioTheme.colors.table.vertical.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = MEDIUM)
        SirioTable(
            title = "Title",
            headers = listOf(
                SirioTableCellType.Header(
                    title = "Header",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = true,
                ),
                SirioTableCellType.Header(
                    title = "Header",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = true,
                ),
            ),
            rows = listOf(
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Text 0",
                            size = size
                        ),
                        SirioTableCellType.Text(
                            text = "Text 1",
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Number(
                            text = "0",
                            size = size
                        ),
                        SirioTableCellType.Number(
                            text = "1",
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Text Only 0",
                            size = size
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Text Only 1",
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link 0",
                            size = size,
                            onLinkClick = {},
                        ),
                        SirioTableCellType.Link(
                            text = "Link 1",
                            size = size,
                            onLinkClick = {},
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Avatar(
                            icon = FaIcons.AddressBook,
                            title = "Avatar 0",
                            subtitle = "Subtitle",
                            size = size,
                        ),
                        SirioTableCellType.Avatar(
                            icon = FaIcons.AddressBook,
                            title = "Avatar 1",
                            subtitle = "Subtitle",
                            size = size,
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.NumberOnly(
                            text = "0",
                            size = size
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "1",
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Tag(
                            text = "Tag 0",
                            tagType = SirioTagType.GRAY,
                            size = size
                        ),
                        SirioTableCellType.Tag(
                            text = "Tag 1",
                            tagType = SirioTagType.GRAY,
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.MultiIcons(
                            size = size,
                            iconsData = listOf(
                                SirioTableIconData(
                                    icon = FaIcons.FilePdf,
                                    text = "Stampa",
                                    action = {},
                                    contentDescription = null,
                                ),
                                SirioTableIconData(
                                    icon = FaIcons.Download,
                                    text = "Stampa",
                                    action = {},
                                    contentDescription = null,
                                ),
                            )
                        ),
                        SirioTableCellType.MultiIcons(
                            size = size,
                            iconsData = listOf(
                                SirioTableIconData(
                                    icon = FaIcons.FilePdf,
                                    action = {},
                                    contentDescription = null,
                                ),
                                SirioTableIconData(
                                    icon = FaIcons.Download,
                                    action = {},
                                    contentDescription = null,
                                ),
                            )
                        ),
                    )
                ),
            )
        )
    }
}

@Composable
private fun TableVerticalDemo() {
    Column(
        modifier = Modifier.background(SirioTheme.colors.table.vertical.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = VERTICAL)
        SirioTableVertical(
            cells = listOf(
                SirioTableVerticalCellData(
                    items = listOf(
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "Link",
                            type = SirioTableVerticalCellItemType.LINK,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "23/11/2023",
                            type = SirioTableVerticalCellItemType.NUMBER,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "00",
                            type = SirioTableVerticalCellItemType.NUMBER,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "Lorem ipsum",
                            type = SirioTableVerticalCellItemType.TEXT,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "Label tag",
                            type = SirioTableVerticalCellItemType.TAG,
                        )
                    ),
                    icons = listOf(
                        SirioTableIconData(
                            icon = FaIcons.FilePdf,
                            action = {},
                            contentDescription = null
                        ),
                        SirioTableIconData(
                            icon = FaIcons.Download,
                            action = {},
                            contentDescription = null
                        ),
                        SirioTableIconData(
                            icon = FaIcons.Trash,
                            action = {},
                            contentDescription = null
                        ),
                    ),
                ),
                SirioTableVerticalCellData(
                    items = listOf(
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "Link",
                            type = SirioTableVerticalCellItemType.LINK,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "23/11/2023",
                            type = SirioTableVerticalCellItemType.NUMBER,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "00",
                            type = SirioTableVerticalCellItemType.NUMBER,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "Lorem ipsum",
                            type = SirioTableVerticalCellItemType.TEXT,
                        ),
                        SirioTableVerticalCellItemData(
                            title = "Header",
                            text = "Label tag",
                            type = SirioTableVerticalCellItemType.TAG,
                        )
                    ),
                    icons = listOf(
                        SirioTableIconData(
                            icon = FaIcons.FilePdf,
                            action = {},
                            contentDescription = null
                        ),
                        SirioTableIconData(
                            icon = FaIcons.Download,
                            action = {},
                            contentDescription = null
                        ),
                        SirioTableIconData(
                            icon = FaIcons.Trash,
                            action = {},
                            contentDescription = null
                        ),
                    ),
                )
            )
        )
    }
}

@Composable
private fun TableDrawerBarCollapsedDemo() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = DRAWERBARCOLLAPSED)
        SirioTableDrawer(
            title = "Titolo",
            data = listOf(
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "Sottotitolo",
                    type = SirioTableDrawerItemType.TEXT,
                    onClick = {},
                ),
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "00",
                    type = SirioTableDrawerItemType.NUMBER,
                    onClick = {},
                ),
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "11/03/2023",
                    type = SirioTableDrawerItemType.NUMBER,
                    onClick = {},
                ),
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "Lorem Ipsum dolor sit amet",
                    type = SirioTableDrawerItemType.LINK,
                    onClick = {},
                ),
            ),
            icons = listOf(
                SirioTableIconData(
                    icon = FaIcons.Print,
                    text = "Stampa",
                    action = {},
                    contentDescription = null
                ),
                SirioTableIconData(
                    icon = FaIcons.Trash,
                    text = "Cancella",
                    action = {},
                    contentDescription = null
                ),
                SirioTableIconData(
                    icon = FaIcons.Download,
                    text = "Scarica",
                    action = {},
                    contentDescription = null
                ),
                SirioTableIconData(
                    icon = FaIcons.Times,
                    text = "Chiudi",
                    action = { },
                    contentDescription = null
                ),
            ),
            onClose = {}
        )
    }
}

@Composable
private fun TableDrawerBarExpandedDemo() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = DRAWERBAREXPANDED)
        SirioTableDrawer(
            title = "Titolo",
            data = listOf(
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "Sottotitolo",
                    type = SirioTableDrawerItemType.TEXT,
                    onClick = {},
                ),
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "00",
                    type = SirioTableDrawerItemType.NUMBER,
                    onClick = {},
                ),
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "11/03/2023",
                    type = SirioTableDrawerItemType.NUMBER,
                    onClick = {},
                ),
                SirioTableDrawerItemData(
                    title = "Titolo",
                    text = "Lorem Ipsum dolor sit amet",
                    type = SirioTableDrawerItemType.LINK,
                    onClick = {},
                ),
            ),
            icons = listOf(
                SirioTableIconData(
                    icon = FaIcons.Print,
                    text = "Stampa",
                    action = {},
                    contentDescription = null
                ),
                SirioTableIconData(
                    icon = FaIcons.Trash,
                    text = "Cancella",
                    action = {},
                    contentDescription = null
                ),
                SirioTableIconData(
                    icon = FaIcons.Download,
                    text = "Scarica",
                    action = {},
                    contentDescription = null
                ),
            ),
            onClose = {}
        )
    }
}

@Composable
private fun Table1Demo() {
    Column(
        modifier = Modifier.background(SirioTheme.colors.table.vertical.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val size: SirioTableContentSize = SirioTableContentSize.LARGE
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = TABLE1)
        SirioTable(
            title = "Title",
            headers = listOf(
                SirioTableCellType.Header(
                    title = "Title 0",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                ),
                SirioTableCellType.Header(
                    title = "Title 1",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                ),
                SirioTableCellType.Header(
                    title = "Title 2",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                ),
            ),
            rows = listOf(
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Text 0",
                            size = size
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "0",
                            size = size
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Text 0",
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Text 1",
                            size = size
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "1",
                            size = size
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Text 1",
                            size = size
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Text(
                            text = "Text 2",
                            size = size
                        ),
                        SirioTableCellType.NumberOnly(
                            text = "2",
                            size = size
                        ),
                        SirioTableCellType.TextOnly(
                            text = "Text 2",
                            size = size
                        ),
                    )
                ),
            )
        )
    }
}

@Composable
private fun Table2Demo() {
    Column(
        modifier = Modifier.background(SirioTheme.colors.table.vertical.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val size: SirioTableContentSize = SirioTableContentSize.LARGE
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = TABLE2)
        SirioTable(
            title = "Title",
            headers = listOf(
                SirioTableCellType.Header(
                    title = "Title 0",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = true,
                ),
                SirioTableCellType.Header(
                    title = "Title 1",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = true,
                ),
                SirioTableCellType.Header(
                    title = "Title 2",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = true,
                ),
            ),
            rows = listOf(
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link 0",
                            size = size,
                            onLinkClick = {},
                        ),
                        SirioTableCellType.Avatar(
                            icon = FaIcons.AddressBook,
                            title = "Avatar 0",
                            subtitle = "Subtitle",
                            size = size,
                        ),
                        SirioTableCellType.Tag(
                            text = "Tag 0",
                            size = size,
                            tagType = SirioTagType.GRAY,
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link 1",
                            size = size,
                            onLinkClick = {},
                        ),
                        SirioTableCellType.Avatar(
                            icon = FaIcons.AddressBook,
                            title = "Avatar 1",
                            subtitle = "Subtitle",
                            size = size,
                        ),
                        SirioTableCellType.Tag(
                            text = "Tag 1",
                            size = size,
                            tagType = SirioTagType.GRAY,
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.Link(
                            text = "Link 2",
                            size = size,
                            onLinkClick = {},
                        ),
                        SirioTableCellType.Avatar(
                            icon = FaIcons.AddressBook,
                            title = "Avatar 2",
                            subtitle = "Subtitle",
                            size = size,
                        ),
                        SirioTableCellType.Tag(
                            text = "Tag 2",
                            size = size,
                            tagType = SirioTagType.GRAY,
                        ),
                    )
                ),
            )
        )
    }
}

@Composable
private fun Table3Demo() {
    Column(
        modifier = Modifier.background(SirioTheme.colors.table.vertical.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        val size: SirioTableContentSize = SirioTableContentSize.LARGE
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = TABLE1)
        SirioTable(
            title = "Title",
            headers = listOf(
                SirioTableCellType.Header(
                    title = "Title 0",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                ),
                SirioTableCellType.Header(
                    title = "Title 1",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                    withCheckBox = true,
                ),
                SirioTableCellType.Header(
                    title = "Title 2",
                    size = size,
                    alignment = SirioTableContentAlignment.START,
                ),
            ),
            rows = listOf(
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Text 0",
                            size = size
                        ),
                        SirioTableCellType.Number(
                            text = "0",
                            size = size,
                        ),
                        SirioTableCellType.MultiIcons(
                            size = size,
                            iconsData = listOf(
                                SirioTableIconData(
                                    icon = FaIcons.FilePdf,
                                    action = {},
                                    contentDescription = null
                                ),
                                SirioTableIconData(
                                    icon = FaIcons.Download,
                                    action = {},
                                    contentDescription = null
                                )
                            )
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Text 1",
                            size = size
                        ),
                        SirioTableCellType.Number(
                            text = "1",
                            size = size,
                        ),
                        SirioTableCellType.MultiIcons(
                            size = size,
                            iconsData = listOf(
                                SirioTableIconData(
                                    icon = FaIcons.FilePdf,
                                    action = {},
                                    contentDescription = null
                                ),
                                SirioTableIconData(
                                    icon = FaIcons.Download,
                                    action = {},
                                    contentDescription = null
                                )
                            )
                        ),
                    )
                ),
                SirioTableRowData(
                    cells = listOf(
                        SirioTableCellType.TextOnly(
                            text = "Text 2",
                            size = size
                        ),
                        SirioTableCellType.Number(
                            text = "2",
                            size = size,
                        ),
                        SirioTableCellType.MultiIcons(
                            size = size,
                            iconsData = listOf(
                                SirioTableIconData(
                                    icon = FaIcons.FilePdf,
                                    action = {},
                                    contentDescription = null
                                ),
                                SirioTableIconData(
                                    icon = FaIcons.Download,
                                    action = {},
                                    contentDescription = null
                                )
                            )
                        ),
                    )
                ),
            )
        )
    }
}

@Composable
fun TableMenuDemo(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            DemoMenuItem(XSMALL) {
                navController.navigate(XSMALL)
            }
            HorizontalDivider()
            DemoMenuItem(SMALL) {
                navController.navigate(SMALL)
            }
            HorizontalDivider()
            DemoMenuItem(MEDIUM) {
                navController.navigate(MEDIUM)
            }
            HorizontalDivider()
            DemoMenuItem(LARGE) {
                navController.navigate(LARGE)
            }
            HorizontalDivider()
            DemoMenuItem(VERTICAL) {
                navController.navigate(VERTICAL)
            }
            HorizontalDivider()
            DemoMenuItem(DRAWERBARCOLLAPSED) {
                navController.navigate(DRAWERBARCOLLAPSED)
            }
            HorizontalDivider()
            DemoMenuItem(DRAWERBAREXPANDED) {
                navController.navigate(DRAWERBAREXPANDED)
            }
            HorizontalDivider()
            DemoMenuItem(TABLE) {
                navController.navigate(TABLE)
            }
        }
    }
}

@Composable
fun TableSubMenuDemo(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
        ) {
            DemoMenuItem(TABLE1) {
                navController.navigate(TABLE1)
            }
            HorizontalDivider()
            DemoMenuItem(TABLE2) {
                navController.navigate(TABLE2)
            }
            HorizontalDivider()
            DemoMenuItem(TABLE3) {
                navController.navigate(TABLE3)
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
        TableSizeDemo(SirioTableContentSize.SMALL)
    }
}

@Preview
@Composable
private fun TableMediumDemoPreview() {
    SirioTheme {
        TableSizeDemo(SirioTableContentSize.MEDIUM)
    }
}

@Preview
@Composable
private fun TableMediumLargePreview() {
    SirioTheme {
        TableSizeDemo(SirioTableContentSize.LARGE)
    }
}

@Preview
@Composable
private fun TableMediumExtraSmallPreview() {
    SirioTheme {
        TableSizeDemo(SirioTableContentSize.EXTRASMALL)
    }
}

@Preview
@Composable
private fun TableVerticalDemoPreview() {
    SirioTheme {
        TableVerticalDemo()
    }
}

@Preview
@Composable
private fun TableDrawerBarCollapsedDemoPreview() {
    SirioTheme {
        TableDrawerBarCollapsedDemo()
    }
}

@Preview
@Composable
private fun TableDrawerBarExpandedDemoPreview() {
    SirioTheme {
        TableDrawerBarExpandedDemo()
    }
}

@Preview
@Composable
private fun Table1DemoPreview() {
    SirioTheme(darkTheme = true) {
        Table1Demo()
    }
}

@Preview
@Composable
private fun Table2DemoPreview() {
    SirioTheme(darkTheme = true) {
        Table2Demo()
    }
}

@Preview
@Composable
private fun Table3DemoPreview() {
    SirioTheme(darkTheme = true) {
        Table3Demo()
    }
}

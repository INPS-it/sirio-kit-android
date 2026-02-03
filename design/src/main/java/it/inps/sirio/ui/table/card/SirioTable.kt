//
// SirioTable.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableCardPaddingCells
import it.inps.sirio.theme.tableCardPaddingHorizontal
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.ui.table.SirioTableActions
import it.inps.sirio.ui.table.SirioTableTitle
import it.inps.sirio.utils.SirioIconSource

/**
 * Displays a table-like list of cards, optionally preceded by a title and an actions bar.
 *
 * The component renders:
 * - an optional [SirioTableTitle] when [title] is not null,
 * - an optional [SirioTableActions] row when [actions] is not empty,
 * - a vertically spaced [LazyColumn] of [SirioTableCard] items built from [cards].
 *
 * When [alternateRows] is enabled, cards are rendered with alternating theme modes:
 * odd rows use [SirioThemeMode.Dark] while even rows use [SirioThemeMode.Light].
 *
 * @param cards The list of cards to display, in order.
 * @param modifier The modifier to be applied to the component.
 * @param title Optional table title shown at the top. If null, no title is displayed.
 * @param actions Optional list of actions shown below the title (if present). If empty, no actions
 *   bar is displayed.
 * @param alternateRows Whether to alternate the theme mode for each row to improve readability.
 */
@Composable
fun SirioTable(
    cards: List<SirioTableCardData>,
    modifier: Modifier = Modifier,
    title: String? = null,
    actions: List<SirioTableActionData> = emptyList(),
    alternateRows: Boolean = true,
) {
    Column(modifier = modifier.background(SirioTheme.colors.table.background)) {
        title?.let { SirioTableTitle(it) }
        if (actions.isNotEmpty()) {
            SirioTableActions(actions = actions)
        }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = tableCardPaddingHorizontal.dp),
            verticalArrangement = Arrangement.spacedBy(tableCardPaddingCells.dp)
        ) {
            itemsIndexed(cards) { index, cardData ->
                val cardTheme = when {
                    alternateRows && index % 2 == 1 -> SirioThemeMode.Dark
                    else -> SirioThemeMode.Light
                }
                SirioTableCard(cardData = cardData, themeMode = cardTheme)
            }
        }
    }
}

@Preview
@Composable
private fun SirioTableVerticalPreview() {
    SirioTheme {
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
                    action = {},
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
            ),
        )
    }
}

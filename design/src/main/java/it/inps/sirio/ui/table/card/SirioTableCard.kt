//
// SirioTableCard.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableCardInnerPaddingHorizontal
import it.inps.sirio.theme.tableCardInnerPaddingVertical
import it.inps.sirio.theme.tableCardItemBorderWidth
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioTableCard(
    cardData: SirioTableCardData,
    themeMode: SirioThemeMode,
) {
    SirioTheme(themeMode = themeMode) {
        Column(
            Modifier
                .background(SirioTheme.colors.table.card.background)
                .border(tableCardItemBorderWidth.dp, SirioTheme.colors.table.card.border)
                .padding(
                    horizontal = tableCardInnerPaddingHorizontal.dp,
                    vertical = tableCardInnerPaddingVertical.dp
                )
        ) {
            cardData.items.forEachIndexed { index, item ->
                CardTypeToCard(
                    cell = item,
                    themeMode = themeMode,
                    showDivider = index < cardData.items.lastIndex,
                )
            }
        }
    }
}

@Composable
private fun CardTypeToCard(
    cell: SirioTableCardType,
    themeMode: SirioThemeMode,
    showDivider: Boolean,
) {
    when (cell) {
        is SirioTableCardType.Link -> SirioTableCardLink(
            cell,
            themeMode = themeMode,
            showDivider = showDivider,
        )

        is SirioTableCardType.Number -> SirioTableCardNumber(
            cell,
            themeMode = themeMode,
            showDivider = showDivider,
        )

        is SirioTableCardType.Tag -> SirioTableCardTag(
            cell,
            themeMode = themeMode,
            showDivider = showDivider,
        )

        is SirioTableCardType.Text -> SirioTableCardText(
            cell,
            themeMode = themeMode,
            showDivider = showDivider,
        )

        is SirioTableCardType.MultiIcons -> SirioTableCardMultiIcons(
            cell,
            themeMode = themeMode,
            showDivider = showDivider,
        )
    }
}

@Preview
@Composable
private fun SirioTableVerticalPreview() {
    val icons = listOf(
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.FilePdf), action = {}),
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Download), action = {}),
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
    )
    SirioTableCard(
        SirioTableCardData(
            items = listOf(
                SirioTableCardType.Link(
                    title = "Header",
                    text = "Link",
                ),
                SirioTableCardType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableCardType.Number(
                    title = "Header",
                    text = "00",
                ),
                SirioTableCardType.Text(
                    title = "Header",
                    text = "Lorem ipsum",
                ),
                SirioTableCardType.Tag(
                    title = "Header",
                    text = "Label Tag",
                ),
                SirioTableCardType.MultiIcons(
                    icons
                ),
            ),
        ),
        themeMode = SirioThemeMode.Light,
    )
}

//
// SirioProcessCard.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio Process Card implementation
 * @param title The title of the card.
 * @param text The text content of the card.
 * @param modifier The [Modifier] for the card (optional).
 * @param category The category of the card (optional).
 * @param icon The FA icon of the card (optional), used if no category is provided.
 * @param iconResId The resource id of the image to be used as the icon if no [icon] is provided
 * @param date The date of the card (optional).
 * @param buttonText The text for the button (optional).
 * @param onClickButton The action to be performed when the button is clicked (optional).
 * @param item The additional button to be displayed on the card (optional).
 * @param onClickCard The action to be performed when the card is clicked (optional).
 */
@Composable
fun SirioProcessCard(
    title: String,
    text: String,
    buttonText: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
    category: String? = null,
    icon: FaIconType? = null,
    @DrawableRes iconResId: Int? = null,
    date: String? = null,
    item: SirioCardItemData? = null,
    onClickCard: () -> Unit,
) {
    SirioCardCommon(
        title = title,
        text = text,
        modifier = modifier,
        buttonText = buttonText,
        onClickButton = onClickButton,
        colors = SirioTheme.colors.card.process,
        typography = SirioTheme.typography.card.process,
        type = SirioCardType.PROCESS,
        category = category,
        icon = icon,
        iconResId = iconResId,
        date = date,
        items = buildList { item?.let { add(it) } },
        onClickCard = onClickCard,
    )
}

@Preview(widthDp = 1600, heightDp = 1600)
@Composable
fun SirioProcessCardPreview() {
    val category = "Categoria"
    val date = "13 Nov 2021"
    val title = "Titolo della card molto lungo su 2 righe"
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    val item = SirioCardItemData(icon = FaIcons.Share, action = {}, contentDescription = "Share")
    val buttonText = "Text"
    val icon = FaIcons.Book
    val space = 20.dp
    Column {
        SirioTheme {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(space)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        date = date,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        date = date,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        onClickCard = {},
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        date = date,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        date = date,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        onClickCard = {},
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(space))
        SirioTheme(darkTheme = true) {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(space)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        date = date,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        date = date,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        category = category,
                        icon = null,
                        onClickCard = {},
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        date = date,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        date = date,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        item = item,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        text = text,
                        modifier = Modifier.weight(1f),
                        buttonText = buttonText,
                        onClickButton = {},
                        icon = icon,
                        onClickCard = {},
                    )
                }
            }
        }
    }
}

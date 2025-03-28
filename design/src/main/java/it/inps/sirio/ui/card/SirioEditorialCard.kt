//
// SirioEditorialCard.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio Editorial Card implementation
 * @param title The title of the card.
 * @param text The text content of the card.
 * @param modifier The [Modifier] for the card (optional).
 * @param imageUrl The url of the remote image.
 * @param imageContentDescriptor The content descriptor associated with the image
 * @param category The category of the card (optional).
 * @param date The date of the card (optional).
 * @param subtitle The subtitle of the card (optional).
 * @param signature The signature of the card (optional).
 * @param items The list of additional buttons to be displayed on the card (optional).
 * @param onClickCard The action to be performed when the card is clicked (optional).
 */
@Composable
fun SirioEditorialCard(
    title: String,
    text: String,
    date: String,
    modifier: Modifier = Modifier,
    imageUrl: (String)? = null,
    imageContentDescriptor: String? = null,
    category: String? = null,
    subtitle: String? = null,
    signature: String? = null,
    items: List<SirioCardItemData> = listOf(),
    onClickCard: () -> Unit,
) {
    SirioCardCommon(
        title = title,
        text = text,
        colors = SirioTheme.colors.card.editorial,
        typography = SirioTheme.typography.card.editorial,
        type = SirioCardType.EDITORIAL,
        modifier = modifier,
        imageUrl = imageUrl,
        imageContentDescriptor = imageContentDescriptor,
        category = category,
        date = date,
        subtitle = subtitle,
        signature = signature,
        items = items.take(2),
        onClickCard = onClickCard,
    )
}

@Preview(widthDp = 2000, heightDp = 2000)
@Composable
private fun SirioEditorialCardPreview() {
    val category = "Categoria"
    val date = "13 Nov 2021"
    val title = "Titolo della card"
    val subtitle = "Sottotitolo"
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    val signature = "Firma Autore"
    val share =
        SirioCardItemData(icon = FaIcons.Share, action = {}, contentDescription = "Share")
    val like = SirioCardItemData(icon = FaIcons.Heart, action = {}, contentDescription = "Like")
    SirioTheme {
        val space = 20.dp
        Column(
            Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(space)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    subtitle = subtitle,
                    signature = signature,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    subtitle = subtitle,
                    signature = signature,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    subtitle = subtitle,
                    signature = signature,
                    onClickCard = {},
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    signature = signature,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    signature = signature,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    signature = signature,
                    onClickCard = {},
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    subtitle = subtitle,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    subtitle = subtitle,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    subtitle = subtitle,
                    onClickCard = {},
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    category = category,
                    date = date,
                    onClickCard = {},
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    subtitle = subtitle,
                    signature = signature,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    subtitle = subtitle,
                    signature = signature,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    subtitle = subtitle,
                    signature = signature,
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    subtitle = subtitle,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    subtitle = subtitle,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    subtitle = subtitle,
                    onClickCard = {},
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(space)) {
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    signature = signature,
                    items = listOf(share, like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    signature = signature,
                    items = listOf(like),
                    onClickCard = {},
                )
                SirioEditorialCard(
                    title = title,
                    text = text,
                    modifier = Modifier.weight(1f),
                    date = date,
                    signature = signature,
                    onClickCard = {},
                )
            }
        }
    }
}
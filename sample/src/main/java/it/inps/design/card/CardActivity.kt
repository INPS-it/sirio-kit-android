//
// CardActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalLayoutApi::class)

package it.inps.design.card

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.h4Md
import it.inps.sirio.ui.card.SirioCardItemData
import it.inps.sirio.ui.card.SirioEditorialCard
import it.inps.sirio.ui.card.SirioProcessCard
import it.inps.sirio.ui.checkbox.SirioCheckbox
import it.inps.sirio.ui.text.SirioText

private const val EDITORIAL_CARD = "Editorial Card"
private const val PROCESS_CARD = "Process Card"

class CardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Card") }, backgroundColor = SirioTheme.colors.brand,
                        )
                    }) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "/",
                        modifier = Modifier.padding(it),
                    ) {
                        composable("/") {
                            CardMenuDemo(navController = navController)
                        }
                        composable(EDITORIAL_CARD) {
                            EditorialCardDemo()
                        }
                        composable(PROCESS_CARD) {
                            ProcessCardDemo()
                        }
//                        composable("Creator") {
//                            CardDemo()
//                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardMenuDemo(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        DemoMenuItem(EDITORIAL_CARD) {
            navController.navigate(EDITORIAL_CARD)
        }
        Divider()
        DemoMenuItem(PROCESS_CARD) {
            navController.navigate(PROCESS_CARD)
        }
//        Divider()
//        DemoMenuItem("Creator") {
//            navController.navigate("Creator")
//        }
    }
}


const val categoryValue = "Categoria"
const val dateValue = "13 Nov 2021"
const val titleValue = "Titolo della card"
const val longTitleValue = "Titolo della card molto lungo su 2 righe"
const val subtitleValue = "Sottotitolo"
const val textValue =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
const val signatureValue = "Firma Autore"
const val buttonTextValue = "Text"
val otherValue =
    SirioCardItemData(icon = FaIcons.EllipsisH, action = {}, contentDescription = "Other")
val shareValue = SirioCardItemData(icon = FaIcons.Share, action = {}, contentDescription = "Share")
val likeValue = SirioCardItemData(icon = FaIcons.Heart, action = {}, contentDescription = "Like")
val iconValue = FaIcons.Book

@Composable
fun EditorialCardDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.LightGray)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 20.dp)
    ) {
        SirioText(text = EDITORIAL_CARD, typography = h4Md)
        SirioEditorialCard(
            title = titleValue,
            text = textValue,
            date = dateValue,
            imageUrl="https://www.inps.it/content/dam/inps-site/immagini/immagini-news/Cedolino_delle_pensioni.jpg",
            category = categoryValue,
            subtitle = subtitleValue,
            signature = signatureValue,
            items = listOf(shareValue, likeValue),
            onClickCard = {},
        )
        SirioEditorialCard(
            title = titleValue,
            text = textValue,
            date = dateValue,
            category = categoryValue,
            subtitle = subtitleValue,
            signature = signatureValue,
            items = listOf(shareValue, likeValue),
            onClickCard = {},
        )
//        SirioEditorialCard(
//            title = titleValue,
//            text = textValue,
//            date = dateValue,
//            category = categoryValue,
//            signature = signatureValue,
//            onClickCard = {},
//        )
//        SirioEditorialCard(
//            title = titleValue,
//            text = textValue,
//            date = dateValue,
//            category = categoryValue,
//            subtitle = subtitleValue,
//            onClickCard = {},
//        )
//        SirioEditorialCard(
//            title = titleValue,
//            text = textValue,
//            date = dateValue,
//            category = categoryValue,
//            onClickCard = {},
//        )
//        SirioEditorialCard(
//            title = titleValue,
//            text = textValue,
//            date = dateValue,
//            subtitle = subtitleValue,
//            signature = signatureValue,
//            items = listOf(shareValue, likeValue),
//            onClickCard = {},
//        )
    }
}

@Composable
fun ProcessCardDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.LightGray)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 20.dp)
    ) {
        SirioTheme(darkTheme = false) {
            SirioText(text = PROCESS_CARD, typography = h4Md)
//            SirioProcessCard(
//                title = longTitleValue,
//                text = textValue,
//                date = dateValue,
//                buttonText = buttonTextValue,
//                onClickButton = {},
//                category = categoryValue,
//                item = otherValue,
//                onClickCard = {},
//            )
            SirioProcessCard(
                title = longTitleValue,
                text = textValue,
                date = dateValue,
                buttonText = buttonTextValue,
                onClickButton = {},
                icon = iconValue,
                item = otherValue,
                onClickCard = {},
            )
        }
        SirioTheme(darkTheme = true) {
//            SirioProcessCard(
//                title = longTitleValue,
//                text = textValue,
//                date = dateValue,
//                buttonText = buttonTextValue,
//                onClickButton = {},
//                category = categoryValue,
//                item = otherValue,
//                onClickCard = {},
//            )
            SirioProcessCard(
                title = longTitleValue,
                text = textValue,
                date = dateValue,
                buttonText = buttonTextValue,
                onClickButton = {},
                icon = iconValue,
                item = otherValue,
                onClickCard = {},
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardDemo() {
    var dark by remember { mutableStateOf(false) }
    SirioTheme(darkTheme = dark) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.LightGray),
        ) {
            var editorial by remember { mutableStateOf(true) }
            var category by remember { mutableStateOf(true) }
            var date by remember { mutableStateOf(true) }
            var subtitle by remember { mutableStateOf(true) }
            var signature by remember { mutableStateOf(true) }
            var share by remember { mutableStateOf(true) }
            var like by remember { mutableStateOf(true) }
            var icon by remember { mutableStateOf(true) }
            FlowRow(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                SirioCheckbox(
                    text = "Editorial",
                    checked = editorial,
                    onCheckedChange = { checked -> editorial = checked })
                SirioCheckbox(
                    text = "Categoria",
                    checked = category,
                    onCheckedChange = { checked -> category = checked })
                SirioCheckbox(
                    text = "Date",
                    checked = editorial || date,
                    enabled = editorial.not(),
                    onCheckedChange = { checked -> date = checked })
                SirioCheckbox(
                    text = "Condividi",
                    checked = share,
                    onCheckedChange = { checked -> share = checked })
                if (editorial) {
                    SirioCheckbox(
                        text = "Sottotitolo",
                        checked = editorial && subtitle,
                        enabled = editorial,
                        onCheckedChange = { checked -> subtitle = checked })
                    SirioCheckbox(
                        text = "Firma",
                        checked = editorial && signature,
                        enabled = editorial,
                        onCheckedChange = { checked -> signature = checked })
                    SirioCheckbox(
                        text = "Like",
                        checked = editorial && like,
                        enabled = editorial,
                        onCheckedChange = { checked -> like = checked })
                } else {
                    SirioCheckbox(
                        text = "Icona",
                        checked = icon,
                        onCheckedChange = { checked -> icon = checked })
                    SirioCheckbox(
                        text = "Dark Theme",
                        checked = dark,
                        onCheckedChange = { checked -> dark = checked })
                }
            }
            if (editorial) {
                SirioEditorialCard(
                    title = titleValue,
                    text = textValue,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    category = if (category) categoryValue else null,
                    date = dateValue,
                    subtitle = if (subtitle) subtitleValue else null,
                    signature = if (signature) signatureValue else null,
                    items = buildList {
                        if (share) add(shareValue)
                        if (like) add(likeValue)
                    },
                    onClickCard = {},
                )
            } else {
                SirioProcessCard(
                    title = longTitleValue,
                    text = textValue,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    buttonText = buttonTextValue,
                    onClickButton = {},
                    category = if (category) categoryValue else null,
                    icon = if (icon) iconValue else null,
                    date = if (date) dateValue else null,
                    item = if (share) shareValue else null,
                    onClickCard = {},
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SirioTheme(darkTheme = false) {
            CardDemo()
        }
    }
}

@Preview
@Composable
private fun EditorialCardDemoViewPreview() {
    SirioTheme {
        EditorialCardDemo()
    }
}

@Preview
@Composable
private fun ProcessCardDemoViewPreview() {
    ProcessCardDemo()
}
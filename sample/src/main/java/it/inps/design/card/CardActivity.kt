//
// CardActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)

package it.inps.design.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.design.sample.R
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.card.SirioCardColor
import it.inps.sirio.ui.card.SirioEditorialCard
import it.inps.sirio.ui.card.SirioEditorialCardItemData
import it.inps.sirio.ui.card.SirioProcessCard
import it.inps.sirio.ui.card.SirioProcessCardCTA
import it.inps.sirio.ui.card.SirioProcessCardItemData
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource

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
                            title = { Text("Card") },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
                        )
                    },
                ) {
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
        HorizontalDivider()
        DemoMenuItem(PROCESS_CARD) {
            navController.navigate(PROCESS_CARD)
        }
    }
}


const val categoryValue = "Categoria"
const val dateValue = "13 Nov 2021"
const val titleValue = "Titolo della card"
const val longTitleValue = "Titolo della card. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
const val subtitleValue = "Sottotitolo"
const val textValue =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."

val shareValue = SirioEditorialCardItemData(
    icon = SirioIconSource.FaIcon(FaIcons.ShareAlt),
    action = {},
    contentDescription = "Condividi",
)
val bookmarkValue = SirioEditorialCardItemData(
    icon = SirioIconSource.FaIcon(FaIcons.Bookmark),
    action = {},
    contentDescription = "Preferiti",
)
val iconValue = SirioIconSource.FaIcon(FaIcons.InfoCircle)
val firstAction = SirioProcessCardItemData(text = "Text", action = {})
val secondAction = SirioProcessCardItemData(text = "Text2", action = {})
val moreActions = listOf(
    SirioProcessCardItemData(text = "Action #1", action = {}),
    SirioProcessCardItemData(text = "Action #2", action = {}),
    SirioProcessCardItemData(text = "Action #3", action = {}),
)

@Composable
fun EditorialCardDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 20.dp)
    ) {
        SirioText(
            text = EDITORIAL_CARD,
            typography = SirioTheme.foundationTypography.headlineLgHeavy
        )
        SirioEditorialCard(
            title = titleValue,
            text = textValue,
            date = dateValue,
            imageDrawable = ContextCompat.getDrawable(LocalContext.current, R.drawable.image),
            category = categoryValue,
            subtitle = subtitleValue,
            actions = listOf(shareValue, bookmarkValue),
            onClickCard = {},
        )
        SirioEditorialCard(
            title = titleValue,
            text = textValue,
            date = dateValue,
            category = categoryValue,
            subtitle = subtitleValue,
            actions = listOf(shareValue, bookmarkValue),
            onClickCard = {},
        )
    }
}

@Composable
fun ProcessCardDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SirioTheme(darkTheme = false) {
            SirioText(
                text = PROCESS_CARD,
                typography = SirioTheme.foundationTypography.headlineLgHeavy
            )
            SirioText(
                text = "Standard",
                typography = SirioTheme.foundationTypography.headlineSmRegular
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                color = SirioCardColor.LIGHT,
                onClickCard = {},
            )
            SirioProcessCard(
                title = longTitleValue,
                icon = iconValue,
                text = textValue,
                firstAction = firstAction,
                color = SirioCardColor.LIGHT,
                onClickCard = {},
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                color = SirioCardColor.DARK,
                onClickCard = {},
            )
            SirioText(
                text = "CTA inline",
                typography = SirioTheme.foundationTypography.headlineSmRegular
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                secondAction = secondAction,
                cta = SirioProcessCardCTA.INLINE,
                color = SirioCardColor.LIGHT,
                onClickCard = {},
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                secondAction = secondAction,
                cta = SirioProcessCardCTA.INLINE,
                color = SirioCardColor.DARK,
                onClickCard = {},
            )
            SirioText(
                text = "CTA block",
                typography = SirioTheme.foundationTypography.headlineSmRegular
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                secondAction = secondAction,
                cta = SirioProcessCardCTA.BLOCK,
                color = SirioCardColor.LIGHT,
                onClickCard = {},
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                secondAction = secondAction,
                cta = SirioProcessCardCTA.BLOCK,
                color = SirioCardColor.DARK,
                onClickCard = {},
            )
            SirioText(
                text = "CTA action more",
                typography = SirioTheme.foundationTypography.headlineSmRegular
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                secondAction = secondAction,
                moreActions = moreActions,
                cta = SirioProcessCardCTA.BLOCK,
                color = SirioCardColor.LIGHT,
                onClickCard = {},
            )
            SirioProcessCard(
                title = titleValue,
                icon = iconValue,
                subtitle = subtitleValue,
                text = textValue,
                firstAction = firstAction,
                secondAction = secondAction,
                moreActions = moreActions,
                cta = SirioProcessCardCTA.BLOCK,
                color = SirioCardColor.DARK,
                onClickCard = {},
            )
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
    SirioTheme {
        ProcessCardDemo()
    }
}

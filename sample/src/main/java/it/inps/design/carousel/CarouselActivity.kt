//
// CarouselActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.carousel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.card.SirioProcessCard
import it.inps.sirio.ui.card.SirioProcessCardItemData
import it.inps.sirio.ui.carousel.SirioCarousel
import it.inps.sirio.ui.carousel.SirioCarouselBackground
import it.inps.sirio.utils.SirioIconSource

class CarouselActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                CarouselDemoContent()
            }
        }
    }
}


@Composable
fun CarouselDemoContent() {
    data class CarouselSampleData(
        val icon: SirioIconSource,
        val date: String,
        val title: String,
        val text: String,
        val action: SirioProcessCardItemData,
    )
    val icon = SirioIconSource.FaIcon(FaIcons.Book)
    val action = SirioProcessCardItemData(text = "Text", action = {})

    val samples = listOf(
        CarouselSampleData(
            icon = icon,
            date = "13 Nov 2021",
            title = "Titolo della card 1",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            action = action,
        ),
        CarouselSampleData(
            icon = icon,
            date = "13 Nov 2021",
            title = "Titolo della card 2",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            action = action,
        ),
        CarouselSampleData(
            icon = icon,
            date = "13 Nov 2021",
            title = "Titolo della card 3",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            action = action,
        ),
        CarouselSampleData(
            icon = icon,
            date = "13 Nov 2021",
            title = "Titolo della card 4",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            action = action,
        ),
        CarouselSampleData(
            icon = icon,
            date = "13 Nov 2021",
            title = "Titolo della card 3",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            action = action,
        ),
        CarouselSampleData(
            icon = icon,
            date = "13 Nov 2021",
            title = "Titolo della card 4",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            action = action,
        ),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carousel") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            SirioCarousel(
                items = samples,
                index = 0,
                background = SirioCarouselBackground.LIGHT,
            ) { sample ->
                SirioProcessCard(
                    title = sample.title,
                    text = sample.text,
                    icon = sample.icon,
                    firstAction = sample.action,
                    onClickCard = {},
                )
            }
            SirioCarousel(
                items = samples,
                index = 0,
                background = SirioCarouselBackground.MEDIUM,
            ) { sample ->
                SirioProcessCard(
                    title = sample.title,
                    text = sample.text,
                    firstAction = sample.action,
                    icon = sample.icon,
                    onClickCard = {},
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CarouselActivityPreview() {
    SirioTheme {
        CarouselDemoContent()
    }
}

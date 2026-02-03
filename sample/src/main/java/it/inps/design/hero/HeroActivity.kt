//
// HeroActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.hero

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.hero.SirioHero

class HeroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                HeroDemoContent()
            }
        }
    }
}

const val heroTitleValue = "Titolo"
const val heroSubtitleValue = "Sottotitolo"
const val heroLinkValue = "Link"

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hero") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFE5E5E5))
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            SirioHero(
                title = heroTitleValue,
                link = heroLinkValue,
                subtitle = heroSubtitleValue,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeroActivityPreview() {
    SirioTheme {
        HeroDemoContent()
    }
}

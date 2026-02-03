//
// ChipActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.chip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.chip.SirioChip
import it.inps.sirio.ui.chip.SirioChipsSelectable

class ChipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                ChipDemoView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ChipDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Chips") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }
    ) { containerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(containerPadding)
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val label = "Chips"
            Text(text = "Chips Close")
            SirioChip(text = label, icon = FaIcons.User, enabled = true) {}
            SirioChip(text = label, icon = FaIcons.User, enabled = false) {}
            Text(text = "Chips Selection")
            SirioChipsSelectable(
                text = label,
                enabled = true,
                active = true,
                onSelectedChange = {  },
            )
            SirioChipsSelectable(
                text = label,
                enabled = false,
                active = true,
                onSelectedChange = {  },
            )
            SirioChipsSelectable(
                text = label,
                enabled = true,
                active = false,
                onSelectedChange = {  },
            )
            SirioChipsSelectable(
                text = label,
                enabled = false,
                active = false,
                onSelectedChange = {  },
            )
        }
    }
}

@Preview
@Composable
private fun ChipDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SirioTheme(darkTheme = false) {
            ChipDemoView()
        }
    }
}

//
// RadioButtonActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.radiobutton

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.radiobutton.SirioRadioButton

class RadioButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                RadioButtonDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RadioButtonDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Radio button") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
        ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            var checkedWithoutText by remember { mutableIntStateOf(1) }
            var checkedWithText by remember { mutableIntStateOf(1) }
            val text = "Title"
            Text(text = "Box Only")
            SirioRadioButton(
                selected = checkedWithoutText == 0,
                onClick = { checkedWithoutText = 0 },
                enabled = true
            )
            SirioRadioButton(
                selected = checkedWithoutText == 1,
                onClick = { checkedWithoutText = 1 },
                enabled = true
            )
            SirioRadioButton(selected = false, onClick = {}, enabled = false)
            SirioRadioButton(selected = true, onClick = {}, enabled = false)
            Text(text = "Box + Text")
            SirioRadioButton(
                text = text,
                selected = checkedWithText == 0,
                onClick = { checkedWithText = 0 },
                enabled = true
            )
            SirioRadioButton(
                text = text,
                selected = checkedWithText == 1,
                onClick = { checkedWithText = 1 },
                enabled = true
            )
            SirioRadioButton(text = text, selected = false, onClick = {}, enabled = false)
            SirioRadioButton(text = text, selected = true, onClick = {}, enabled = false)
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun RadioButtonActivityPreview() {
    SirioTheme {
        RadioButtonDemoContent()
    }
}

//
// SliderActivity.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.slider

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.ui.slider.SirioSlider
import it.inps.sirio.theme.SirioTheme

class SliderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                SliderDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SliderDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Slider") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            val title = "Slider label"
            val text = "*Info upload file"
            var value1 by remember { mutableStateOf(20) }
            var value2 by remember { mutableStateOf(20) }
            Text(text = "Enabled")
            SirioSlider(
                title = title,
                text = text,
                value = value1,
                minValue = 50,
                maxValue = 100,
                onValueChange = { value1 = it },
            )
            Text(text = "Disabled")
            SirioSlider(
                title = title,
                text = text,
                value = value2,
                minValue = 0,
                maxValue = 100,
                enabled = false,
                onValueChange = { value2 = it },
            )
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun SliderActivityPreview() {
    SirioTheme {
        SliderDemoContent()
    }
}
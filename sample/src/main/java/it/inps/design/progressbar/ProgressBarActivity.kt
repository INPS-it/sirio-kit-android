//
// ProgressBarActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.progressbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.progressbar.SirioProgressBar

class ProgressBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                ProgressBarDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProgressBarDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progress Bar") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        val label = "Label"
        var prog by remember { mutableIntStateOf(0) }
        Column(
            Modifier
                .background(Color(0xFFE5E5E5))
                .padding(10.dp)) {
//            Button(
//                onClick = { prog += 10 },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text(text = "+10")
//            }
            SirioProgressBar(
                label = label,
                progress = prog,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 10,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 20,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 30,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 40,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 50,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 60,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 70,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 80,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 90,
                maxValue = 100,
            )
            SirioProgressBar(
                label = label,
                progress = 100,
                maxValue = 100,
            )
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun ProgressBarActivityPreview() {
    SirioTheme {
        ProgressBarDemoContent()
    }
}

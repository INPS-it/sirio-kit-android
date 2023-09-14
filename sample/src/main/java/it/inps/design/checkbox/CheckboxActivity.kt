//
// CheckboxActivity.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.checkbox

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.ui.checkbox.SirioCheckbox
import it.inps.sirio.theme.SirioTheme

class CheckboxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                CheckboxDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CheckboxDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkbox") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            val checked = remember {
                mutableStateMapOf(
                    pairs = arrayOf(
                        Pair(0, false),
                        Pair(1, true),
                        Pair(4, false),
                        Pair(5, true),
                    )
                )
            }
            val text = "Title"
            Text(text = "Box Only")
            SirioCheckbox(
                checked = checked[0] == true,
                onCheckedChange = { checked[0] = checked[0]?.not() ?: true },
                enabled = true
            )
            SirioCheckbox(
                checked = checked[1] == true,
                onCheckedChange = { checked[1] = checked[1]?.not() ?: true },
                enabled = true
            )
            SirioCheckbox(checked = false, onCheckedChange = {}, enabled = false)
            SirioCheckbox(checked = true, onCheckedChange = {}, enabled = false)
            Text(text = "Box + Text")
            SirioCheckbox(
                text = text,
                checked = checked[4] == true,
                onCheckedChange = { checked[4] = checked[4]?.not() ?: true },
                enabled = true
            )
            SirioCheckbox(
                text = text,
                checked = checked[5] == true,
                onCheckedChange = { checked[5] = checked[5]?.not() ?: true },
                enabled = true
            )
            SirioCheckbox(text = text, checked = false, onCheckedChange = {}, enabled = false)
            SirioCheckbox(text = text, checked = true, onCheckedChange = {}, enabled = false)
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun CheckboxActivityPreview() {
    SirioTheme {
        CheckboxDemoContent()
    }
}
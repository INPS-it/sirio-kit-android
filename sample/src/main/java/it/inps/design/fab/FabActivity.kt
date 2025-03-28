//
// FabActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.fab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.fab.SirioFab
import it.inps.sirio.ui.fab.SirioFabData
import it.inps.sirio.ui.fab.SirioFabSize

class FabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                // A surface container using the 'background' color from the theme
                FabDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FabDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fab") },
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
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Light")
                Text(text = "Dark")
            }
            Text(text = "Small")
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SirioTheme(darkTheme = false) {
                    SirioFab(
                        fabData = SirioFabData(
                            icon = FaIcons.Plus,
                            size = SirioFabSize.SMALL,
                            action = {},
                        )
                    )
                }
                SirioTheme(darkTheme = true) {
                    SirioFab(
                        fabData = SirioFabData(
                            icon = FaIcons.Plus,
                            size = SirioFabSize.SMALL,
                            action = {},
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Regular")
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SirioTheme(darkTheme = false) {
                    SirioFab(
                        fabData = SirioFabData(
                            icon = FaIcons.Plus,
                            size = SirioFabSize.REGULAR,
                            action = {},
                        )
                    )
                }
                SirioTheme(darkTheme = true) {
                    SirioFab(
                        fabData = SirioFabData(
                            icon = FaIcons.Plus,
                            size = SirioFabSize.REGULAR,
                            action = {},
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Extended")
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SirioTheme(darkTheme = false) {
                    SirioFab(
                        fabData = SirioFabData(
                            icon = FaIcons.Plus,
                            text = "Text",
                            action = {},
                        )
                    )
                }
                SirioTheme(darkTheme = true) {
                    SirioFab(
                        fabData = SirioFabData(
                            icon = FaIcons.Plus,
                            text = "Text",
                            action = {},
                        )
                    )
                }
            }
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SirioTheme {
        FabDemoContent()
    }
}
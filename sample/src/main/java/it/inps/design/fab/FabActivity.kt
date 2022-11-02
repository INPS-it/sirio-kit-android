//
// FabActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.fab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.ui.fab.*
import it.inps.sirio.theme.SirioTheme

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
                title = { Text("Fab") }, backgroundColor = SirioTheme.colors.brand
            )
        }) {
        Column(
            Modifier
                .fillMaxSize()
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
                    FabSmall(
                        fabData = FabItemData(
                            icon = FaIcons.Plus,
                            action = {})
                    )
                }
                SirioTheme(darkTheme = true) {
                    FabSmall(
                        fabData = FabItemData(
                            icon = FaIcons.Plus,
                            action = {})
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
                    FabRegular(fabData = FabItemData(icon = FaIcons.Plus, action = {}))
                }
                SirioTheme(darkTheme = true) {
                    FabRegular(fabData = FabItemData(icon = FaIcons.Plus, action = {}))
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
                    FabExtended(
                        fabExtendedData = FabExtendedItemData(
                            text = "Text",
                            icon = FaIcons.Plus,
                            action = {})
                    )
                }
                SirioTheme(darkTheme = true) {
                    FabExtended(
                        fabExtendedData = FabExtendedItemData(
                            text = "Text",
                            icon = FaIcons.Plus,
                            action = {})
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
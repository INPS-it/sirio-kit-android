//
// TagActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.tag

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
import it.inps.sirio.ui.tag.SmallTag
import it.inps.sirio.ui.tag.TagType
import it.inps.sirio.theme.SirioTheme

class TagActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                TagDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TagDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tag") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            SmallTag("Label Tag", TagType.GRAY)
            SmallTag("Label Tag", TagType.BLUE)
            SmallTag("Label Tag", TagType.RED)
            SmallTag("Label Tag", TagType.ORANGE)
            SmallTag("Label Tag", TagType.GREEN)
            SmallTag("Label Tag", TagType.WHITE)
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun TagActivityPreview() {
    SirioTheme {
        TagDemoContent()
    }
}
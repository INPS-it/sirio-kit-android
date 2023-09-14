//
// FileUploadActivity.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.fileupload

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.ui.fileupload.FileUpload
import it.inps.sirio.theme.SirioTheme

class FileUploadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                FileUploadDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FileUploadDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("File Upload") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            val title = "Slider label"
            val text = "*Info upload file"
            val items = remember { mutableStateListOf(elements = arrayOf<String>()) }
            var progress by remember { mutableStateOf(0) }
            Text(text = "Enabled")
            FileUpload(
                title = title,
                text = text,
                enabled = true,
                uploadList = items,
                closeContentDescription = "Elimina",
                onDeleteClick = { index, _ -> items.removeAt(index) }) {
                items.add("File ${++progress}")
            }
            Text(text = "Disabled")
            FileUpload(
                title,
                text,
                enabled = false,
                uploadList = emptyList(),
                onDeleteClick = { _, _ -> }) {}
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun FileUploadActivityPreview() {
    SirioTheme {
        FileUploadDemoContent()
    }
}

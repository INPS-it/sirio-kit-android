//
// TextAreaActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.textarea

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.textarea.SirioTextArea
import it.inps.sirio.ui.textarea.TextAreaState

class TextAreaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                TextAreaDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TextAreaDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TextArea") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            var text by remember { mutableStateOf("Text") }
            val label = "Label"
            val helperText = "Helper text"
            SirioTextArea(
                text = text,
                placeholder = "Placeholder",
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
            )
            Text(text = "Info")
            SirioTextArea(
                text = text,
                placeholder = "Placeholder",
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = { text = "Info click" },
            )
            Text(text = "Alert")
            SirioTextArea(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = null,
                state = TextAreaState.Alert,
            )
            Text(text = "Success")
            SirioTextArea(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                state = TextAreaState.Success,
            )
            Text(text = "Disabled")
            SirioTextArea(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                enabled = false,
                state = TextAreaState.Success,
            )
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun TextAreaActivityPreview() {
    SirioTheme {
        TextAreaDemoContent()
    }
}
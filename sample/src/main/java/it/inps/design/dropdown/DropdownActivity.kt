//
// DropdownActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.dropdown

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.dropdown.SirioDropdown
import it.inps.sirio.ui.popover.SirioPopoverData

class DropdownActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                DropdownDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DropdownDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dropdown") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(10.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            val values by remember { mutableStateOf(listOf("Value 1", "Value 2", "Value 3")) }
            var text by remember { mutableStateOf("") }
            SirioDropdown(
                values = values,
                selectedValue = text,
                onValueChange = { text = it },
                placeholder = "Placeholder",
                label = "Label",
                helperText = "Helper text",
            )
            SirioDropdown(
                values = emptyList(),
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
                enabled = false,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = true,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
                popoverData = SirioPopoverData(text = "Popover text",),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DropdownActivityPreview() {
    SirioTheme {
        DropdownDemoContent()
    }
}

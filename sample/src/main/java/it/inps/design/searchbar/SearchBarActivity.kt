//
// SearchBarActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.searchbar

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
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.searchbar.SirioSearchBar

class SearchBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                SearchBarDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBarDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SearchBar") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp),
        ) {
            val title = "Label"
            val placeholder = "Placeholder"
            val helperText = "*Helper text"
            var text by remember { mutableStateOf("") }
            val queries = remember { mutableStateListOf(elements = arrayOf<String>()) }
            SirioSearchBar(
                searchText = text,
                onSearchTextChange = { text = it },
                placeholder = placeholder,
                label = title,
                helperText = helperText,
                optionValues = if (text.isEmpty()) emptyArray() else
                    (1..8).map { "$text $it" }.filter { !queries.contains(it) }.toTypedArray(),
                queries = queries.toTypedArray(),
                onQueriesChange = {
                    queries.clear()
                    queries.addAll(it)
                },
            )
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun SearchBarActivityPreview() {
    SirioTheme {
        SearchBarDemoContent()
    }
}
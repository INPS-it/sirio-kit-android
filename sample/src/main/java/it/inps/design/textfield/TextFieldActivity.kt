//
// TextFieldActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.textfield

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
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.ui.textfield.TextFieldSemantic
import it.inps.sirio.ui.textfield.SirioTextField
import it.inps.sirio.theme.SirioTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                TextFieldDemoContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TextFieldDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TextField") }, backgroundColor = SirioTheme.colors.brand,
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
            var text by remember { mutableStateOf("Text") }
            val label = "Label"
            val helperText = "*Helper text"
//            SirioTextField(
//                text = text,
//                placeholder = "Placeholder",
//                onValueChange = { text = it },
//                label = label,
//                helperText = helperText,
//                onInfoClick = {},
//            )
            Text(text = "Warning")
            SirioTextField(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = {},
                icon = FaIcons.ExclamationCircle,
                type = TextFieldSemantic.WARNING,
            )
            Text(text = "Alert")
            SirioTextField(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = {},
                icon = FaIcons.ExclamationTriangle,
                type = TextFieldSemantic.ALERT,
            )
            Text(text = "Success")
            SirioTextField(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = {},
                icon = FaIcons.Check,
                type = TextFieldSemantic.SUCCESS,
            )
            Text(text = "Info")
            SirioTextField(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = {},
                icon = FaIcons.Calendar,
                type = TextFieldSemantic.INFO,
            )
            Text(text = "Disabled")
            SirioTextField(
                text = text,
                onValueChange = { text = it },
                label = label,
                helperText = helperText,
                onInfoClick = {},
                icon = FaIcons.Calendar,
                enabled = false,
            )
        }
    }
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun TextFieldActivityPreview() {
    SirioTheme {
        TextFieldDemoContent()
    }
}
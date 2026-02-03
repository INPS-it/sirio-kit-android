//
// SirioFilterDrawerRadioGroup.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

@Composable
fun SirioFilterDrawerRadioGroup(
    values: List<String>,
    selectedValue: String? = null,
    onSelectionChange: (String) -> Unit,
) {
    Column {
        values.forEach { value ->
            SirioFilterDrawerRadio(
                text = value,
                selected = value == selectedValue,
                onClick = { onSelectionChange(value) },
            )
        }
    }
}

@Preview(name = "Selected", showBackground = true)
@Composable
private fun SirioFilterDrawerRadioGroupPreview() {
    SirioTheme {
        SirioFilterDrawerRadioGroup(
            values = listOf("1", "2", "3"),
            selectedValue = null,
            onSelectionChange = {},
        )
    }
}
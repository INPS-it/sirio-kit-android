//
// SirioFilterDrawerSelect.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterDrawerSelectPaddingHorizontal
import it.inps.sirio.theme.filterDrawerSelectPaddingVertical
import it.inps.sirio.ui.dropdown.SirioDropdown

@Composable
fun SirioFilterDrawerSelect(
    values: List<String>,
    onValueChange: (String) -> Unit,
    label: String? = null,
    selectedValue: String? = null,
    placeholder: String? = null,
    iconContentDescription: String? = null,
) {
    SirioDropdown(
        values = values,
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterDrawerSelectPaddingHorizontal.dp,
                vertical = filterDrawerSelectPaddingVertical.dp,
            ),
        selectedValue = selectedValue.orEmpty(),
        onValueChange = onValueChange,
        placeholder = placeholder,
        iconContentDescription = iconContentDescription,
        label = label,
    )
}

@Preview
@Composable
private fun SirioFilterDrawerSelectPreview() {
    SirioTheme {
        SirioFilterDrawerSelect(
            values = listOf("Value 1", "Value 2"),
            label = "Label",
            placeholder = "Text",
            onValueChange = {}
        )
    }
}
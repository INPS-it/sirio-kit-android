//
// SirioFilterCheckbox.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterPadding
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon

/**
 * A filter checkbox component.
 *
 * @param checked Whether the checkbox is currently checked.
 * @param text The text to display next to the checkbox.
 * @param enabled Whether the checkbox is enabled.
 * @param onCheckedChange The callback to be invoked when the checked state of the checkbox changes.
 */
@Composable
fun SirioFilterCheckbox(
    checked: Boolean,
    text: String? = null,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(SirioTheme.colors.filter.background)
            .padding(filterPadding.dp)
    ) {
        SirioCheckboxCommon(
            checked = checked,
            modifier = Modifier.fillMaxWidth(),
            text = text,
            enabled = enabled,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Preview
@Composable
private fun SirioFilterCheckboxPreview() {
    SirioTheme {
        Column {
            SirioFilterCheckbox(
                checked = false,
                text = "Title",
                enabled = true,
                onCheckedChange = {},
            )
            SirioFilterCheckbox(
                checked = true,
                text = "Title",
                enabled = true,
                onCheckedChange = {},
            )
            SirioFilterCheckbox(
                checked = false,
                text = "Title",
                enabled = false,
                onCheckedChange = {},
            )
            SirioFilterCheckbox(
                checked = true,
                text = "Title",
                enabled = false,
                onCheckedChange = {},
            )
        }
    }
}
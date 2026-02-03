//
// SirioFilterDrawerCheckbox.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
import it.inps.sirio.theme.filterDrawerCheckboxPaddingHorizontal
import it.inps.sirio.theme.filterDrawerCheckboxPaddingVertical
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon

/**
 * A filter checkbox component.
 *
 * @param checked Whether the checkbox is currently checked
 * @param onCheckedChange The callback to be invoked when the checked state of the checkbox changes
 * @param text The text to display next to the checkbox
 */
@Composable
fun SirioFilterDrawerCheckbox(
    checked: Boolean,
    text: String? = null,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxCommon(
        checked = checked,
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterDrawerCheckboxPaddingHorizontal.dp,
                vertical = filterDrawerCheckboxPaddingVertical.dp,
            ),
        text = text,
        onCheckedChange = onCheckedChange,
    )
}

@Preview
@Composable
private fun SirioFilterDrawerCheckboxUncheckedPreview() {
    SirioTheme {
        SirioFilterDrawerCheckbox(
            checked = false,
            text = "Title",
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
private fun SirioFilterDrawerCheckboxCheckedPreview() {
    SirioTheme {
        SirioFilterDrawerCheckbox(
            checked = true,
            text = "Title",
            onCheckedChange = {},
        )
    }
}
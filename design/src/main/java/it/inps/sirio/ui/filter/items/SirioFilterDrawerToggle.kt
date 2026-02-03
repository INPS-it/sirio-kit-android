//
// SirioFilterDrawerToggle.kt
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
import it.inps.sirio.theme.filterDrawerTogglePaddingHorizontal
import it.inps.sirio.theme.filterDrawerTogglePaddingVertical
import it.inps.sirio.ui.toggle.SirioToggleCommon

/**
 * A filter toggle component.
 *
 * @param checked Whether the toggle is currently on.
 * @param onToggleChange The callback to be invoked when the toggle state changes.
 * @param text The text to display next to the toggle.
 */
@Composable
fun SirioFilterDrawerToggle(
    checked: Boolean,
    text: String? = null,
    onToggleChange: (Boolean) -> Unit,
) {
    SirioToggleCommon(
        checked = checked,
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterDrawerTogglePaddingHorizontal.dp,
                vertical = filterDrawerTogglePaddingVertical.dp,
            ),
        text = text,
        onToggleChange = onToggleChange,
    )
}

@Preview(showBackground = true)
@Composable
private fun SirioFilterDrawerToggleCheckedPreview() {
    SirioTheme {
        SirioFilterDrawerToggle(
            checked = true,
            onToggleChange = {},
            text = "Title"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SirioFilterDrawerToggleUncheckedPreview() {
    SirioTheme {
        SirioFilterDrawerToggle(
            checked = false,
            onToggleChange = {},
            text = "Title"
        )
    }
}
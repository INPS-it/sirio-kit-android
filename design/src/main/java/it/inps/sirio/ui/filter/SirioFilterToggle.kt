//
// SirioFilterToggle.kt
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
import it.inps.sirio.ui.toggle.SirioToggleCommon

/**
 * A filter toggle component.
 *
 * @param isOn Whether the toggle is currently on.
 * @param text The text to display next to the toggle.
 * @param enabled Whether the toggle is enabled.
 * @param onToggleChange The callback to be invoked when the toggle state changes.
 */
@Composable
fun SirioFilterToggle(
    isOn: Boolean,
    text: String? = null,
    enabled: Boolean = true,
    onToggleChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(SirioTheme.colors.filter.background)
            .padding(filterPadding.dp)
    ) {
        SirioToggleCommon(
            checked = isOn,
            modifier = Modifier.fillMaxWidth(),
            text = text,
            enabled = enabled,
            onToggleChange = onToggleChange,
        )
    }
}

@Preview
@Composable
private fun SirioFilterTogglePreview() {
    SirioTheme {
        Column {
            SirioFilterToggle(
                isOn = false,
                text = "Title",
                enabled = true,
                onToggleChange = {},
            )
            SirioFilterToggle(
                isOn = true,
                text = "Title",
                enabled = true,
                onToggleChange = {},
            )
            SirioFilterToggle(
                isOn = false,
                text = "Title",
                enabled = false,
                onToggleChange = {},
            )
            SirioFilterToggle(
                isOn = true,
                text = "Title",
                enabled = false,
                onToggleChange = {},
            )
        }
    }
}
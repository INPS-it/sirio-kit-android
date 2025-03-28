//
// SirioToggle.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.toggle

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio toggle component
 *
 * @param label The optional string on the toggle right
 * @param checked Whether the toggle is checked
 * @param enabled Whether the toggle is enabled
 * @param onToggleChange The callback when the toggle state change
 */
@Composable
fun SirioToggle(
    checked: Boolean,
    label: String? = null,
    enabled: Boolean = true,
    onToggleChange: (on: Boolean) -> Unit
) {
    SirioToggleCommon(
        checked = checked,
        text = label,
        enabled = enabled,
        onToggleChange = onToggleChange,
    )
}

@Preview
@Composable
private fun TogglePreview() {
    SirioTheme {
        Column {
            SirioToggle(
                label = "Label",
                checked = true,
                enabled = true,
                onToggleChange = {}
            )
        }
    }
}
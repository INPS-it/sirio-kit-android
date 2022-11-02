//
// Toggle.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
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
 * @param isOn Whether the toggle is selected
 * @param enabled Whether the toggle is enabled
 * @param onToggleChange The callback when the toggle state change
 */
@Composable
fun Toggle(
    label: String? = null,
    isOn: Boolean,
    enabled: Boolean = true,
    onToggleChange: (on: Boolean) -> Unit
) {
    SirioToggleCommon(
        text = label,
        isOn = isOn,
        enabled = enabled,
        onToggleChange = onToggleChange,
    )
}

@Preview
@Composable
private fun TogglePreview() {
    SirioTheme {
        Column {
            Toggle(
                label = "Title",
                isOn = true,
                enabled = true,
                onToggleChange = {}
            )
        }
    }
}
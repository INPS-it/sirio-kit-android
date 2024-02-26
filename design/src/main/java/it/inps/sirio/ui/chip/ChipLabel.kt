//
// ChipLabel.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * The Sirio chip with only label
 *
 * @param label The string on the chip
 * @param enabled Whether the chip is enabled
 * @param active Whether the chip is active
 * @param onStateChange The callback when the chip active state change
 */
@Composable
fun ChipLabel(
    label: String,
    enabled: Boolean,
    active: Boolean,
    onStateChange: (active: Boolean) -> Unit
) {
    ChipCommon(
        text = label,
        icon = null,
        withClose = false,
        isActive = active,
        enabled = enabled,
        onStateChange = onStateChange,
    )
}

@Preview
@Composable
private fun ChipLabelPreview() {
    SirioTheme {
        Column {
            ChipLabel(
                label = "Chips",
                enabled = true,
                active = true,
                onStateChange = {},
            )
            ChipLabel(
                label = "Chips",
                enabled = false,
                active = true,
                onStateChange = {},
            )
        }
    }
}
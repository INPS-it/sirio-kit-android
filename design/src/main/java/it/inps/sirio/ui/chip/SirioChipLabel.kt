//
// SirioChipLabel.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * The Sirio chip with only label. If active, the chip has a check icon.
 *
 * @param label The string on the chip
 * @param enabled Whether the chip is enabled
 * @param active Whether the chip is active
 * @param onStateChange The callback when the chip active state change
 */
@Composable
fun SirioChipLabel(
    label: String,
    enabled: Boolean,
    active: Boolean,
    onStateChange: (active: Boolean) -> Unit
) {
    val icon = if (active) FaIcons.Check else null
    SirioChipCommon(
        text = label,
        icon = icon,
        withClose = false,
        isActive = active,
        enabled = enabled,
        onStateChange = onStateChange,
    )
}

@Preview
@Composable
private fun SirioChipLabelPreview() {
    SirioTheme {
        Column {
            SirioChipLabel(
                label = "Chips",
                enabled = true,
                active = true,
                onStateChange = {},
            )
            SirioChipLabel(
                label = "Chips",
                enabled = true,
                active = false,
                onStateChange = {},
            )
            SirioChipLabel(
                label = "Chips",
                enabled = false,
                active = true,
                onStateChange = {},
            )
            SirioChipLabel(
                label = "Chips",
                enabled = false,
                active = false,
                onStateChange = {},
            )
        }
    }
}
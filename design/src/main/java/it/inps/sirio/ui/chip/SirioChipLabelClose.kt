//
// SirioChipLabelClose.kt
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
 * The Sirio chip with label and close button
 *
 * @param label The string on the chip
 * @param enabled Whether the chip is enabled
 * @param closeContentDescription The content description of the close button
 * @param onClose The callback when the chip close button is clicked
 */
@Composable
fun SirioChipLabelClose(
    label: String, enabled: Boolean,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    SirioChipCommon(
        text = label,
        icon = null,
        withClose = true,
        isActive = true,
        enabled = enabled,
        closeContentDescription = closeContentDescription,
        onClose = onClose,
        onStateChange = {},
    )
}

@Preview
@Composable
private fun SirioChipLabelClosePreview() {
    SirioTheme {
        Column {
            SirioChipLabelClose(
                label = "Chips",
                enabled = true,
                onClose = {},
            )
            SirioChipLabelClose(
                label = "Chips",
                enabled = false,
                onClose = {},
            )
        }
    }
}
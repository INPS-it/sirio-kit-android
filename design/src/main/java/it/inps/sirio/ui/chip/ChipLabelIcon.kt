//
// ChipLabelIcon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.chip

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme

/**
 * The Sirio chip with icon and label
 *
 * @param label The string on the chip
 * @param icon The icon at the start of the chip
 * @param enabled Whether the chip is enabled
 * @param isActive Whether the chip is active
 * @param onStateChange The callback when the chip active state change
 */
@Composable
fun ChipLabelIcon(
    label: String,
    icon: FaIconType,
    enabled: Boolean,
    isActive: Boolean,
    onStateChange: (active: Boolean) -> Unit
) {
    ChipCommon(
        text = label,
        icon = icon,
        withClose = false,
        isActive = isActive,
        enabled = enabled,
        onStateChange = onStateChange,
    )
}

@Preview
@Composable
private fun ChipLabelIconPreview() {
    SirioTheme {
        Column {
            ChipLabelIcon(
                label = "Chips",
                icon = FaIcons.User,
                enabled = true,
                isActive = true,
                onStateChange = {},
            )
            ChipLabelIcon(
                label = "Chips",
                icon = FaIcons.User,
                enabled = false,
                isActive = true,
                onStateChange = {},
            )
        }
    }
}
//
// ChipLabelIconClose.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
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
 * The Sirio chip with icon, label and close button
 *
 * @param label The string on the chip
 * @param icon The icon at the start of the chip
 * @param enabled Whether the chip is enabled
 * @param onClose The callback when the chip close button is clicked
 */
@Composable
fun ChipLabelIconClose(label: String, icon: FaIconType, enabled: Boolean, onClose: () -> Unit) {
    ChipCommon(
        text = label,
        icon = icon,
        withClose = true,
        isActive = true,
        enabled = enabled,
        onClose = onClose,
        onStateChange = {},
    )
}

@Preview
@Composable
private fun ChipLabelIconClosePreview() {
    SirioTheme {
        Column {
            ChipLabelIconClose(
                label = "Chips",
                icon = FaIcons.User,
                enabled = true,
                onClose = {},
            )
            ChipLabelIconClose(
                label = "Chips",
                icon = FaIcons.User,
                enabled = false,
                onClose = {},
            )
        }
    }
}
//
// SirioRadioButton.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * Radio button component
 *
 * @param text The optional string on the right of the radio
 * @param selected Whether the radio button is selected
 * @param enabled Whether the radio button is clickable
 * @param onClick The callback invoked when the component is tapped
 */
@Composable
fun SirioRadioButton(
    text: String? = null,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    SirioRadioButtonCommon(
        text = text,
        isSelected = selected,
        enabled = enabled,
        onClick = onClick,
    )
}

@Preview
@Composable
private fun ChipLabelPreview() {
    SirioTheme {
        Column {
            SirioRadioButton(
                text = "Title",
                selected = false,
                onClick = {},
                enabled = true
            )
        }
    }
}
//
// SirioRadioButton.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.radiobutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        selected = selected,
        text = text,
        enabled = enabled,
        onClick = onClick,
    )
}

@Preview
@Composable
private fun ChipLabelPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            SirioRadioButton(
                text = "Label",
                selected = false,
                onClick = {},
                enabled = true,
            )
        }
    }
}
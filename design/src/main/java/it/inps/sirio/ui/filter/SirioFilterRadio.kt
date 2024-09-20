//
// SirioFilterRadio.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
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
import it.inps.sirio.ui.radiobutton.SirioRadioButtonCommon

/**
 * A filter radio button component.
 *
 * @param selected Whether the radio button is currently selected.
 * @param text The text to display next to the radio button.
 * @param enabled Whether the radio button is enabled.
 * @param onClick The callback to be invoked when the radio button is clicked.
 */
@Composable
fun SirioFilterRadio(
    selected: Boolean,
    text: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(SirioTheme.colors.filter.background)
            .padding(filterPadding.dp)
    ) {
        SirioRadioButtonCommon(
            selected = selected,
            modifier = Modifier.fillMaxWidth(),
            text = text,
            enabled = enabled,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun SirioFilterRadioPreview() {
    SirioTheme {
        Column {
            SirioFilterRadio(
                selected = false,
                text = "Title",
                enabled = true,
                onClick = {},
            )
            SirioFilterRadio(
                selected = true,
                text = "Title",
                enabled = true,
                onClick = {},
            )
            SirioFilterRadio(
                selected = false,
                text = "Title",
                enabled = false,
                onClick = {},
            )
            SirioFilterRadio(
                selected = true,
                text = "Title",
                enabled = false,
                onClick = {},
            )
        }
    }
}
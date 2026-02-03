//
// SirioFilterDrawerRadio.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterDrawerRadioPaddingHorizontal
import it.inps.sirio.theme.filterDrawerRadioPaddingVertical
import it.inps.sirio.ui.radiobutton.SirioRadioButtonCommon

/**
 * A filter radio button specific for the filter drawer.
 *
 * @param selected Whether the radio button is selected.
 * @param text The optional text to display next to the radio button.
 * @param onClick The callback to be invoked when the radio button is clicked.
 */
@Composable
fun SirioFilterDrawerRadio(
    selected: Boolean,
    text: String? = null,
    onClick: () -> Unit,
) {
    SirioRadioButtonCommon(
        selected = selected,
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterDrawerRadioPaddingHorizontal.dp,
                vertical = filterDrawerRadioPaddingVertical.dp,
            ),
        text = text,
        onClick = onClick,
    )
}

@Preview(name = "Not selected", showBackground = true)
@Composable
private fun SirioFilterDrawerRadioUnselectedPreview() {
    SirioTheme {
        SirioFilterDrawerRadio(
            selected = false,
            text = "Title",
            onClick = {},
        )
    }
}

@Preview(name = "Selected", showBackground = true)
@Composable
private fun SirioFilterDrawerRadioSelectedPreview() {
    SirioTheme {
        SirioFilterDrawerRadio(
            selected = true,
            text = "Title",
            onClick = {},
        )
    }
}
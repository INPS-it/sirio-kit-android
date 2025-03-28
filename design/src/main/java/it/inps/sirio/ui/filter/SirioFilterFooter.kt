//
// SirioFilterFooter.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterFooterButtonSpacing
import it.inps.sirio.theme.filterFooterPaddingHorizontal
import it.inps.sirio.theme.filterFooterPaddingVertical
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize

@Composable
fun SirioFilterFooter(
    neutralText: String,
    positiveText: String,
    onNeutral: () -> Unit,
    onPositive: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(vertical = filterFooterPaddingVertical.dp, horizontal = filterFooterPaddingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(filterFooterButtonSpacing.dp)
    ) {
        SirioButton(
            modifier = Modifier.weight(1f),
            text = neutralText,
            size = SirioButtonSize.Large,
            style = ButtonStyle.Secondary,
            onClick = onNeutral,
        )
        SirioButton(
            modifier = Modifier.weight(1f),
            text = positiveText,
            size = SirioButtonSize.Large,
            style = ButtonStyle.Primary,
            onClick = onPositive,
        )
    }
}

@Preview
@Composable
private fun SirioFilterFooterPreview() {
    SirioTheme {
        Column {
            SirioFilterFooter(
                neutralText = "Elimina filtri",
                positiveText = "Applica filtri",
                onNeutral = {},
                onPositive = {},
            )
        }
    }
}
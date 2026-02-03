//
// SirioFilterDrawerButton.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.styleDictionary.StyleDictionaryBoxShadow
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterDrawerButtonButtonSpacing
import it.inps.sirio.theme.filterDrawerButtonPaddingHorizontal
import it.inps.sirio.theme.filterDrawerButtonPaddingVertical
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize

@Composable
fun SirioFilterDrawerButton(
    leftButtonText: String,
    rightButtonText: String,
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
) {
    val elevation =
        with(LocalDensity.current) { StyleDictionaryBoxShadow.elevationElevation01.blurRadius.toDp() }

    Surface(
        modifier = Modifier.shadow(
            ambientColor = StyleDictionaryBoxShadow.elevationElevation01.color,
            elevation = elevation
        ),
        tonalElevation = elevation,
        shadowElevation = elevation,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SirioTheme.colors.filter.background)
                .padding(
                    vertical = filterDrawerButtonPaddingVertical.dp,
                    horizontal = filterDrawerButtonPaddingHorizontal.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(filterDrawerButtonButtonSpacing.dp)
        ) {
            SirioButton(
                size = SirioButtonSize.Large,
                hierarchy = SirioButtonHierarchy.Secondary,
                modifier = Modifier.weight(1f),
                text = leftButtonText,
                onClick = onLeftButtonClick,
            )
            SirioButton(
                size = SirioButtonSize.Large,
                hierarchy = SirioButtonHierarchy.Primary,
                modifier = Modifier.weight(1f),
                text = rightButtonText,
                onClick = onRightButtonClick,
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterDrawerButtonPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .height(200.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
        ) {
            SirioFilterDrawerButton(
                leftButtonText = "Elimina filtri",
                rightButtonText = "Applica filtri",
                onLeftButtonClick = {},
                onRightButtonClick = {},
            )
        }
    }
}
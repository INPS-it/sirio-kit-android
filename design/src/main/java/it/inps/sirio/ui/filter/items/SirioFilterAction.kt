//
// SirioFilterAction.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterActionPaddingBottom
import it.inps.sirio.theme.filterActionPaddingHorizontal
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonIconPosition
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.utils.SirioIconSource
/**
 * A component that displays a filter action button.
 *
 * @param buttonText The text to display on the button, default is "Filtra".
 * @param onButtonClick The callback to be invoked when the button is clicked.
 */
@Composable
fun SirioFilterAction(
    buttonText: String = "Filtra",
    onButtonClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(horizontal = filterActionPaddingHorizontal.dp)
            .padding(bottom = filterActionPaddingBottom.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        SirioButton(
            size = SirioButtonSize.Small,
            hierarchy = SirioButtonHierarchy.GhostLight,
            text = buttonText,
            icon= SirioIconSource.FaIcon(FaIcons.Filter),
            iconPosition = SirioButtonIconPosition.Left,
            onClick = onButtonClick,
        )
    }
}

@Preview
@Composable
private fun SirioFilterActionPreview() {
    SirioTheme {
        Column {
            SirioFilterAction(onButtonClick = {})
        }
    }
}
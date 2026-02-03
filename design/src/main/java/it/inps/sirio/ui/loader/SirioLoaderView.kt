//
// SirioLoaderView.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme

@Composable
fun SirioLoaderView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FoundationColor.colorAliasBackgroundColorPrimaryLight0.copy(alpha = 0.95f)),
        contentAlignment = Alignment.Center,
    ) {
        SirioLoaderCommon()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SirioLoaderViewPreview() {
    SirioTheme {
        SirioLoaderView()
    }
}
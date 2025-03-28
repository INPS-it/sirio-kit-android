//
// SirioBadge.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.badge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

@Composable
fun SirioBadge() {
    SirioBadgeCommon()
}

@Preview
@Composable
internal fun SirioBadgePreview() {
    SirioTheme {
        SirioBadge()
    }
}
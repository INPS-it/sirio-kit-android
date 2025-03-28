//
// SirioTitleBar.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.titlebar

import androidx.compose.runtime.Composable

/**
 * Sirio component for displaying a title bar with a title and optional items.
 *
 * @param title The title to be displayed in the title bar.
 * @param items A list of [SirioTitleBarItemData] objects representing the items to be displayed in the title bar.
 */
@Composable
fun SirioTitleBar(
    title: String,
    items: List<SirioTitleBarItemData> = emptyList(),
) {
    SirioTitleBarCommon(title = title, items = items)
}
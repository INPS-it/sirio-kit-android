//
// SirioDropdownItemData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdownmenu

import androidx.annotation.Keep

/**
 * A data class representing an item in a SirioDropdownMenu.
 *
 * @param value The text to be displayed for this dropdown item.
 * @param action The lambda function to be executed when this dropdown item is clicked.
 */
@Keep
data class SirioDropdownItemData(
    val value: String,
    val contentDescription: String? = null,
    val action: () -> Unit,
)
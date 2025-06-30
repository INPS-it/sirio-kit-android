//
// SirioCardItemData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.card

import androidx.annotation.Keep
import it.inps.sirio.utils.SirioIconSource

/**
 * The representation of a process card icon button
 *
 * @param icon The icon source of the item
 * @param action The onClick callback
 * @param contentDescription The content description for the item
 */
@Keep
data class SirioEditorialCardItemData(
    val icon: SirioIconSource,
    val contentDescription: String? = null,
    val action: () -> Unit,
)

/**
 * The representation of an editorial card item (an action button with text).
 *
 * @param text The text to be displayed in the item.
 * @param contentDescription The content description for the item, used for accessibility.
 * @param action The callback function invoked when the item is clicked.
 */
@Keep
data class SirioProcessCardItemData(
    val text: String,
    val contentDescription: String? = null,
    val action: () -> Unit,
)

//
// SirioCardItemData.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.card

import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType

/**
 * The representation of a card icon button
 *
 * @param icon The FA icon of the item
 * @param action The onClick callback
 * @param contentDescription The content description for the item
 */
@Keep
data class SirioCardItemData(
    val icon: FaIconType,
    val contentDescription: String? = null,
    val action: () -> Unit,
)
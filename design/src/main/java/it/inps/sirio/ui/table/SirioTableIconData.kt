//
// SirioTableIconData.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.table

import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType

/**
 * The representation of a table cell icon
 *
 * @param icon The FA icon of the item
 * @param text The text used in the sticky bottom bar popup menu
 * @param action The onClick callback
 * @param contentDescription The content description for the item
 */
@Keep
data class SirioTableIconData(
    val icon: FaIconType,
    val text: String? = null,
    val action: () -> Unit,
    val contentDescription: String? = null,
)
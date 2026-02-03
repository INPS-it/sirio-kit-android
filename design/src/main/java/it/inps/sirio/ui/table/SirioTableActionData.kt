//
// SirioTableIconData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.table

import androidx.annotation.Keep
import it.inps.sirio.utils.SirioIconSource

/**
 * @param text The text used in the action. If null, icon must be not null
 * @param icon The icon of the item
 * @param contentDescription The content description for the icon
 * @param action The onClick callback
 */
@Keep
data class SirioTableActionData(
    val text: String? = null,
    val icon: SirioIconSource? = null,
    val contentDescription: String? = null,
    val action: () -> Unit,
)
//
// SirioTitleBarItemData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.titlebar

import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType

/**
 * A representation of a title bar item
 * @param icon The FA icon of the item
 * @param text The text of the item
 * @param contentDescription The content description for the item
 * @param action The click action callback
 */
@Keep
data class SirioTitleBarItemData(
    val icon: FaIconType,
    val text: String,
    val contentDescription: String? = null,
    val action: () -> Unit,
)

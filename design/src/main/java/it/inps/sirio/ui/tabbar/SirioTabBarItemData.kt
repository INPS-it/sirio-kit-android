//
// SirioTabBarItemData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabbar

import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType

/**
 * A representation of a tab bar item
 * @param label The label of the item
 * @param icon The FA icon of the item
 * @param badge If the item's icon has a badge
 * @param action The action to perform when the item is clicked
 */
@Keep
data class SirioTabBarItemData(
    val label: String,
    val icon: FaIconType,
    val badge: Boolean = false,
    val highlighted: Boolean = false,
    val action: () -> Unit,
)

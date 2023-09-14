//
// TabBarItemData.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
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
 * @param route The route to navigate on item pressed
 * @param badge If the item's icon has a badge
 */
@Keep
data class TabBarItemData(
    val label: String,
    val icon: FaIconType,
    val route: String,
    val badge: Boolean = false,
)
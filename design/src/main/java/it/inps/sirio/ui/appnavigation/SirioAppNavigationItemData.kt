//
// SirioAppNavigationItemData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons

/**
 * The representation of an app navigation item
 *
 * @param icon The FA icon of the item
 * @param action The onClick callback
 * @param username The string for the username item data
 * @param contentDescription The content description for the item
 * @param badge If the item's icon has a badge
 */
@Keep
data class SirioAppNavigationItemData(
    val icon: FaIconType = FaIcons.User,
    val action: () -> Unit,
    val username: String = "",
    val contentDescription: String? = null,
    val badge: Boolean = false,
)
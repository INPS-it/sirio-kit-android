//
// AppNavigationItemData.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons

/**
 * The representation of an app navigation item
 *
 * @param icon The FA icon of the item
 * @param action The onClick callback
 * @param username
 */
data class AppNavigationItemData(
    val icon: FaIconType = FaIcons.User,
    val action: () -> Unit,
    val username: String = "",
)
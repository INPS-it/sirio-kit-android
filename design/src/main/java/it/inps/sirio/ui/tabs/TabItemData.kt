//
// TabItemData.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabs

import androidx.annotation.Keep
import com.guru.fontawesomecomposelib.FaIconType

/**
 * A representation of a tab item
 * @param label The label of the item
 * @param icon The FA icon of the item
 * @param enabled If the tab can be selected
 */
@Keep
data class TabItemData(
    val label: String,
    val icon: FaIconType? = null,
    val enabled: Boolean = false,
)
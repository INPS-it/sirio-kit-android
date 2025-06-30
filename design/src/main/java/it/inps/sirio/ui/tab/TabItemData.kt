//
// TabItemData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tab

import androidx.annotation.Keep
import it.inps.sirio.utils.SirioIconSource

/**
 * A representation of a tab item
 * @param label The label of the item
 * @param icon The icon of the item
 * @param enabled If the tab can be selected
 */
@Keep
data class TabItemData(
    val label: String,
    val icon: SirioIconSource? = null,
    val enabled: Boolean = true,
)
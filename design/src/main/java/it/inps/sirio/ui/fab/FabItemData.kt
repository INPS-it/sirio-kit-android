//
// FabItemData.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import com.guru.fontawesomecomposelib.FaIconType

/**
 * A representation of a Fab item
 * @param icon The FA icon of the item
 * @param action The onClick callback
 */
data class FabItemData(
    val icon: FaIconType,
    val action: () -> Unit,
)

/**
 * A representation of a Fab item
 * @param icon The FA icon of the item
 * @param action The onClick callback
 * @param text The fab label text
 */
data class FabExtendedItemData(
    val icon: FaIconType,
    val action: () -> Unit,
    val text: String,
)

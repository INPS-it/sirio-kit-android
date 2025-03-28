//
// SirioFabData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.fab

import com.guru.fontawesomecomposelib.FaIconType

/**
 * Data class representing the configuration for a Sirio FAB (Floating Action Button).
 *
 * This class encapsulates the visual and functional properties of a Sirio FAB, including its icon, text, size, content description, and the action to be performed when it's clicked.
 *
 * @property icon The Font Awesome icon to be displayed on the FAB.
 * @property text The text to be displayed alongside the icon on the FAB. Can be null if the FAB is icon-only.
 * @property iconContentDescription A descriptive string for the FAB's icon, used for accessibility. Can be null if not needed.
 * @property size The size of the FAB, defined by the `SirioFabSize` enum. Can be null for default sizing.
 * @property action The lambda function to be executed when the FAB is clicked.
 *
 * @constructor Creates a `SirioFabData` object with the specified properties.
 *
 * @param icon The Font Awesome icon to be displayed on the FAB.
 * @param text The text to be displayed alongside the icon on the FAB.
 * @param action The lambda function to be executed when the FAB is clicked.
 *
 * @constructor Creates a `SirioFabData` object with the specified properties for an icon-only FAB.
 *
 * @param icon The Font Awesome icon to be displayed on the FAB.
 * @param size The size of the FAB, defined by the `SirioFabSize` enum.
 * @param iconContentDescription A descriptive string for the FAB's icon, used for accessibility.
 * @param action The lambda function to be executed when the FAB is clicked.
 */
data class SirioFabData internal constructor(
    val icon: FaIconType,
    val text: String?,
    val iconContentDescription: String?,
    val size: SirioFabSize?,
    val action: () -> Unit,
) {
    constructor(
        icon: FaIconType,
        text: String,
        action: () -> Unit,
    ) : this(icon, text, null, null, action)

    constructor(
        icon: FaIconType,
        size: SirioFabSize,
        iconContentDescription: String? = null,
        action: () -> Unit,
    ) : this(icon, null, iconContentDescription, size, action)
}
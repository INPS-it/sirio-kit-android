//
// AccordionData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import androidx.annotation.Keep
import androidx.compose.runtime.Composable

/**
 * A representation of an accordion
 *
 * @param text The title of the accordion
 * @param open Whether the accordion is open
 * @param enabled Whether the accordion is enabled
 * @param onTapAccordion Callback that is executed when the accordion is tapped
 */
@Keep
data class AccordionData(
    val text: String,
    val open: Boolean = false,
    val enabled: Boolean = false,
    val onTapAccordion: ((Boolean) -> Unit)? = null,
    val content: @Composable () -> Unit,
)
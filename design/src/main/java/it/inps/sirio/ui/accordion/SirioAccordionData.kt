//
// SirioAccordionData.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import androidx.compose.runtime.Composable
import it.inps.sirio.utils.SirioIconSource

/**
 * A representation of an accordion, containing data for its various states and configurations.
 * This sealed class provides different types of accordions: Default, WithIcon, WithTag, and WithText.
 *
 * @param title The main title text displayed on the accordion.
 * @param icon An optional icon to be displayed alongside the title.
 * @param tag An optional tag string that can be displayed on the accordion.
 * @param text An optional descriptive text that can be displayed below the title.
 * @param open A boolean indicating whether the accordion is initially open (expanded) or closed. Defaults to `false`.
 * @param onTapAccordion An optional callback function that is executed when the accordion is tapped. It receives a boolean indicating the new open state.
 * @param content A composable function that defines the content to be displayed when the accordion is open.
 */
sealed class SirioAccordionData private constructor(
    val title: String,
    val icon: SirioIconSource?,
    val tag: String?,
    val text: String?,
    val open: Boolean = false,
    val onTapAccordion: ((Boolean) -> Unit)? = null,
    val content: @Composable () -> Unit,
) {
    class Default(
        title: String,
        open: Boolean = false,
        onTapAccordion: ((Boolean) -> Unit)? = null,
        content: @Composable () -> Unit,
    ) : SirioAccordionData(title, null, null, null, open, onTapAccordion, content)

    class WithIcon(
        title: String,
        icon: SirioIconSource,
        open: Boolean = false,
        onTapAccordion: ((Boolean) -> Unit)? = null,
        content: @Composable () -> Unit,
    ) : SirioAccordionData(title, icon, null, null, open, onTapAccordion, content)

    class WithTag(
        title: String,
        tag: String,
        open: Boolean = false,
        onTapAccordion: ((Boolean) -> Unit)? = null,
        content: @Composable () -> Unit,
    ) : SirioAccordionData(title, null, tag, null, open, onTapAccordion, content)

    class WithText(
        title: String,
        text: String,
        open: Boolean = false,
        onTapAccordion: ((Boolean) -> Unit)? = null,
        content: @Composable () -> Unit,
    ) : SirioAccordionData(title, null, null, text, open, onTapAccordion, content)
}

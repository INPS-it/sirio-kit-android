//
// SirioAvviso.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.avviso

import androidx.compose.runtime.Composable

/**
 * Displays a Sirio-style information message.
 *
 * This composable presents a formatted message with an optional link.
 *
 * @param title The title of the message.
 * @param text The main text content of the message.
 * @param linkText Optional text for a clickable link. If null, no link is displayed.
 * @param onLinkClick Optional lambda function to be executed when the link is clicked.
 *                    If null and linkText is provided, the link will be displayed but will not be clickable.
 *                    If both are null, no link is displayed.
 */
@Composable
fun SirioAvviso(
    title: String,
    text: String,
    linkText: String? = null,
    onLinkClick: (() -> Unit)? = null,
) {
    SirioAvvisoCommon(
        title = title,
        text = text,
        link = linkText,
        onLinkClick = onLinkClick,
    )
}
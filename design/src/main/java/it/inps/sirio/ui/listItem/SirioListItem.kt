//
// SirioListItem.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.listItem

import androidx.compose.runtime.Composable
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.utils.SirioIconSource

/**
 * Standard list item without leading icon or info action.
 */
@Composable
fun SirioListItem(
    title: String? = null,
    description: String? = null,
    indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
    showDivider: Boolean = true,
    onClick: () -> Unit,
) {
    SirioListItemCommon(
        title = title,
        description = description,
        indicator = indicator,
        type = SirioListItemType.Default,
        showDivider = showDivider,
        onClick = onClick,
    )
}

/**
 * List item with a leading icon.
 */
@Composable
fun SirioListItem(
    title: String? = null,
    description: String? = null,
    icon: SirioIconSource,
    indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
    showDivider: Boolean = true,
    onClick: () -> Unit,
) {
    SirioListItemCommon(
        title = title,
        description = description,
        indicator = indicator,
        type = SirioListItemType.Icon(icon),
        showDivider = showDivider,
        onClick = onClick,
    )
}

/**
 * List item with an info icon that triggers an action (e.g., dialog).
 */
@Composable
fun SirioListItem(
    title: String? = null,
    description: String? = null,
    indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
    showDivider: Boolean = true,
    onInfoClick: () -> Unit,
    onClick: () -> Unit,
) {
    SirioListItemCommon(
        title = title,
        description = description,
        indicator = indicator,
        type = SirioListItemType.Info(onInfoClick),
        showDivider = showDivider,
        onClick = onClick,
    )
}

@Composable
internal fun SirioListItem(
    data: SirioListItemData,
    showDivider: Boolean = true,
    onInfoClick: ((SirioListItemData.Info) -> Unit)? = null,
) {
    when (data) {
        is SirioListItemData.Default ->
            SirioListItem(
                title = data.title,
                description = data.description,
                indicator = data.indicator,
                showDivider = showDivider,
                onClick = data.onClick,
            )

        is SirioListItemData.Icon ->
            SirioListItem(
                title = data.title,
                description = data.description,
                icon = data.icon,
                indicator = data.indicator,
                showDivider = showDivider,
                onClick = data.onClick,
            )

        is SirioListItemData.Info ->
            SirioListItem(
                title = data.title,
                description = data.description,
                indicator = data.indicator,
                showDivider = showDivider,
                onInfoClick = { onInfoClick?.invoke(data) },
                onClick = data.onClick,
            )
    }
}
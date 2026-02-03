//
// SirioTableCellAction.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.dropdownmenu.SirioDropdownItemData
import it.inps.sirio.ui.dropdownmenu.SirioMoreAction
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.utils.SirioIconSource

@Composable
fun RowScope.SirioTableCellAction(
    size: SirioTableContentSize,
    icon: SirioIconSource? = null,
    actions: List<SirioTableActionData> = emptyList(),
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
    onActionClick: () -> Unit = {},
) {
    require(icon != null || actions.isNotEmpty()) { "At least one of icon or actions must be provided" }
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
        SirioTableCellCommon(
            size = size,
            weight = weight,
            themeMode = themeMode,
            alignment = Alignment.Center,
        ) {
            if (actions.isNotEmpty()) {
                SirioMoreAction(
                    isTop = false,
                    horizontalAlignment = Alignment.Start,
                    size = SirioButtonSize.Small,
                    hierarchy = SirioTheme.colors.table.cell.action,
                    offset = DpOffset((-10).dp, (-4).dp),
                    items = actions.mapNotNull { data ->
                        data.text?.let {
                            SirioDropdownItemData(
                                value = it,
                                contentDescription = data.contentDescription,
                                action = data.action
                            )
                        }
                    }
                )
            } else {
                icon?.let {
                    SirioButton(
                        size = SirioButtonSize.Small,
                        hierarchy = SirioTheme.colors.table.cell.action,
                        icon = it,
                        onClick = onActionClick,
                    )
                }
            }
        }
    }
}

@Composable
internal fun RowScope.SirioTableCellAction(
    data: SirioTableCellType.Action,
    size: SirioTableContentSize,
    weight: Float = 1f,
    themeMode: SirioThemeMode? = SirioThemeMode.Light,
) {
    SirioTableCellAction(
        size = size,
        icon = data.icon,
        actions = data.actions,
        weight = weight,
        themeMode = themeMode,
    )
}

@Preview
@Composable
private fun SirioTableCellTagPreview() {
    SirioTheme {
        Column {
            val icon = SirioIconSource.FaIcon(FaIcons.Eye)
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .width(intrinsicSize = IntrinsicSize.Max)
            ) {
                SirioTableCellAction(
                    icon = icon,
                    size = SirioTableContentSize.Large,
                    themeMode = SirioThemeMode.Light
                )
            }
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .width(intrinsicSize = IntrinsicSize.Max)
            ) {
                SirioTableCellAction(
                    icon = icon,
                    size = SirioTableContentSize.Large,
                    themeMode = SirioThemeMode.Dark,
                )
            }
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .width(intrinsicSize = IntrinsicSize.Max)
            ) {
                SirioTableCellAction(
                    icon = icon,
                    size = SirioTableContentSize.Small,
                    themeMode = SirioThemeMode.Light
                )
            }
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .width(intrinsicSize = IntrinsicSize.Max)
            ) {
                SirioTableCellAction(
                    icon = icon,
                    size = SirioTableContentSize.Small,
                    themeMode = SirioThemeMode.Dark,
                )
            }
            Row(
                Modifier
                    .height(IntrinsicSize.Max)
                    .width(intrinsicSize = IntrinsicSize.Max)
            ) {
                SirioTableCellAction(
                    size = SirioTableContentSize.Small,
                    icon = icon,
                    actions = listOf(
                        SirioTableActionData(
                            text = "Test",
                            action = { },
                        ),
                        SirioTableActionData(
                            text = "Test",
                            action = { },
                        ),
                        SirioTableActionData(
                            text = "Test",
                            action = { },
                        ),
                    ),
                    themeMode = SirioThemeMode.Light,
                )
            }
        }
    }
}
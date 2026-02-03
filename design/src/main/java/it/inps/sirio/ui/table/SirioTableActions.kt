//
// SirioTableActions.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableActionsItemPadding
import it.inps.sirio.theme.tableActionsMorePaddingEnd
import it.inps.sirio.theme.tableActionsPaddingBottom
import it.inps.sirio.theme.tableActionsPaddingHorizontal
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonIconPosition
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.dropdownmenu.SirioDropdownItemData
import it.inps.sirio.ui.dropdownmenu.SirioMoreAction
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioTableActions(
    actions: List<SirioTableActionData>,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.table.background)
            .padding(
                start = tableActionsPaddingHorizontal.dp,
                end = if (actions.size > 2) tableActionsMorePaddingEnd.dp else tableActionsPaddingHorizontal.dp,
            )
            .padding(bottom = tableActionsPaddingBottom.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        if (actions.size > 2) {
            SirioMoreAction(
                size = SirioButtonSize.Small,
                hierarchy = SirioButtonHierarchy.GhostLight,
                isTop = false,
                horizontalAlignment = Alignment.Start,
                offset = DpOffset((-8).dp, 0.dp),
                items = actions.map {
                    SirioDropdownItemData(
                        value = it.text.orEmpty(),
                        contentDescription = it.contentDescription,
                        action = it.action,
                    )
                },
            )
        } else {
            actions.map {
                SirioButton(
                    size = SirioButtonSize.Small,
                    hierarchy = SirioButtonHierarchy.GhostLight,
                    text = it.text,
                    icon = it.icon,
                    iconContentDescription = it.contentDescription,
                    iconPosition = SirioButtonIconPosition.Left,
                    onClick = it.action,
                )
                if (actions.last() != it) {
                    Spacer(modifier = Modifier.width(tableActionsItemPadding.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun SirioTableActionsPreview() {
    SirioTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SirioTableActions(
                listOf(
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        text = "Label",
                        action = {},
                    ),
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        text = "Label",
                        action = {},
                    )
                )
            )
            SirioTableActions(
                listOf(
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        text = "Label",
                        action = {},
                    ),
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        text = "Label",
                        action = {},
                    ),
                    SirioTableActionData(
                        icon = SirioIconSource.FaIcon(FaIcons.Cube),
                        text = "Label",
                        action = {},
                    )
                )
            )
        }
    }
}
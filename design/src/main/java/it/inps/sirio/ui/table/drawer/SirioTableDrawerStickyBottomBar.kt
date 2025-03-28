//
// SirioTableDrawerStickyBottomBar.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableDrawerIconsPaddingHorizontal
import it.inps.sirio.theme.tableDrawerIconsPaddingVertical
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.dropdownmenu.SirioDropdownMenuOptionItem
import it.inps.sirio.ui.dropdownmenu.SirioPopup
import it.inps.sirio.ui.dropdownmenu.SirioPopupState
import it.inps.sirio.ui.table.SirioTableIconData
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioTableDrawerStickyBottomBar(icons: List<SirioTableIconData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.table.drawer.iconsBackground)
            .padding(
                vertical = tableDrawerIconsPaddingVertical.dp,
                horizontal = tableDrawerIconsPaddingHorizontal.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        if (icons.size < 4) {
            icons.forEach {
                SirioButton(
                    size = SirioButtonSize.Large,
                    style = ButtonStyle.Primary,
                    icon = it.icon,
                    iconContentDescription = it.contentDescription,
                    onClick = it.action,
                )
            }
        } else {
            SirioText(
                text = "Azioni",
                color = SirioTheme.colors.table.drawer.actionsText,
                typography = SirioTheme.typography.table.drawer.actionsText,
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
//                SirioDropdown(
//                    expanded = dropdownOpen,
//                    onDismissRequest = { dropdownOpen = false },
//                    offset = DpOffset(0.dp, (-30).dp)
//                ) {
//                    icons.forEach { icon ->
//                        icon.text?.let {
//                            SirioDropdownOptionItem(
//                                text = it,
//                                enabled = true,
//                                selected = false,
//                                onCLick = {
//                                    dropdownOpen = false
//                                    icon.action()
//                                }
//                            )
//                        }
//                    }
//                }

                val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                sirioPopupState.isTop = true
                sirioPopupState.horizontalAlignment = Alignment.End
                SirioPopup(
                    sirioPopupState = sirioPopupState,
                    onDismissRequest = { sirioPopupState.isVisible = false },
                    offset = DpOffset(0.dp, 8.dp),
                ) {
                    icons.forEach { icon ->
                        icon.text?.let {
                            SirioDropdownMenuOptionItem(
                                text = it,
                                enabled = true,
                                selected = false,
                                onCLick = {
                                    icon.action()
                                    sirioPopupState.isVisible = false
                                }
                            )
                        }
                    }
                }
                SirioButton(
                    size = SirioButtonSize.Large,
                    style = ButtonStyle.Primary,
                    icon = FaIcons.EllipsisH,
                    onClick = {
                        sirioPopupState.isVisible = true
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun SirioTableDrawerStickyBottomBarPreview() {
    val icons = listOf(
        SirioTableIconData(FaIcons.Print, action = {}),
        SirioTableIconData(FaIcons.Download, action = {}),
        SirioTableIconData(FaIcons.Trash, action = {}),
        SirioTableIconData(FaIcons.Trash, action = {}),
    )
    SirioTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            SirioTableDrawerStickyBottomBar(icons = icons)
        }
    }
}
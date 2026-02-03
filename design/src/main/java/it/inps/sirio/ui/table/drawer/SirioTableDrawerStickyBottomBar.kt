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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableDrawerIconsPaddingHorizontal
import it.inps.sirio.theme.tableDrawerIconsPaddingVertical
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.table.SirioTableActionData
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioTableDrawerStickyBottomBar(actions: List<SirioTableActionData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.table.drawer.bottomBar)
            .padding(
                vertical = tableDrawerIconsPaddingVertical.dp,
                horizontal = tableDrawerIconsPaddingHorizontal.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        actions.take(3).map {
            SirioButton(
                size = SirioButtonSize.Medium,
                hierarchy = SirioTheme.colors.table.drawer.action,
                icon = it.icon,
                iconContentDescription = it.contentDescription,
                onClick = it.action
            )
        }
    }
}

@Preview
@Composable
private fun SirioTableDrawerStickyBottomBarPreview() {
    val icons = listOf(
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.FilePdf), action = {}),
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Download), action = {}),
        SirioTableActionData(icon = SirioIconSource.FaIcon(FaIcons.Trash), action = {}),
    )
    SirioTheme {
        Box(
            Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            SirioTableDrawerStickyBottomBar(actions = icons)
        }
    }
}

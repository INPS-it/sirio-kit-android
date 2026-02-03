//
// SirioList.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.dialog.SirioDialog
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioList(
    items: List<SirioListItemData>,
) {
    var dialogTitle by remember { mutableStateOf<String?>(null) }
    var dialogText by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.listItem.background),
    ) {
        items.forEachIndexed { index, item ->
            SirioListItem(
                data = item,
                onInfoClick = { infoItem ->
                    dialogTitle = infoItem.infoDialogTitle
                    dialogText = infoItem.infoDialogText
                },
                showDivider = index != items.lastIndex,
            )
        }
    }

    if (dialogText != null) {
        SirioDialog(
            title = dialogTitle,
            text = dialogText,
            onDismiss = {
                dialogTitle = null
                dialogText = null
            },
        )
    }
}

@Preview
@Composable
private fun SirioListPreview() {
    SirioTheme {
        SirioList(
            items = List(3) {
                SirioListItemData.Default(
                    title = "Titolo list item $it",
                    onClick = {},
                )
            }
        )
    }
}

@Preview
@Composable
private fun SirioListIconPreview() {
    SirioTheme {
        SirioList(
            items = List(3) {
                SirioListItemData.Icon(
                    title = "Titolo list item $it",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    onClick = {},
                )
            }
        )
    }
}

@Preview
@Composable
private fun SirioListInfoPreview() {
    SirioTheme {
        SirioList(
            items = listOf(
                SirioListItemData.Info(
                    title = "Titolo list item 1",
                    description = "Oggi aperto dalle 8:30 alle 12:30",
                    infoDialogTitle = "Titolo",
                    infoDialogText = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                ),
                SirioListItemData.Info(
                    title = "Titolo list item 2",
                    description = "Lorem ipsum dolor sit amet consectetur. Purus suscipit aliquam habitant.",
                    infoDialogTitle = "Titolo",
                    infoDialogText = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                ),
                SirioListItemData.Info(
                    title = "Titolo list item 3",
                    description = "Lorem ipsum dolor sit amet",
                    infoDialogTitle = "Titolo",
                    infoDialogText = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                ),
            )
        )
    }
}

@Preview
@Composable
private fun SirioListComplexPreview() {
    SirioTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SirioTheme.colors.listItem.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SirioText("Test")
            SirioList(
                items = List(30) {
                    SirioListItemData.Default(
                        title = "Titolo list item $it",
                        onClick = {},
                    )
                }
            )
        }
    }
}
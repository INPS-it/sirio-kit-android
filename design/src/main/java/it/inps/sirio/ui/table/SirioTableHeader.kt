//
// SirioTableHeader.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableComponentPaddingHorizontalExtraSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalMedium
import it.inps.sirio.theme.tableComponentPaddingHorizontalSmall
import it.inps.sirio.theme.tableHeaderPaddingVerticalExtraSmall
import it.inps.sirio.theme.tableHeaderPaddingVerticalLarge
import it.inps.sirio.theme.tableHeaderPaddingVerticalMedium
import it.inps.sirio.theme.tableHeaderPaddingVerticalSmall

@Composable
fun RowScope.SirioTableHeader(
    title: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    alignment: SirioTableContentAlignment = SirioTableContentAlignment.START,
    scroll: Boolean = false,
    withCheckBox: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    withSortIcon: Boolean = true,
    onIconClick: () -> Unit,
) {
    val (verticalPadding, horizontalPadding) = remember(size) {
        when (size) {
            SirioTableContentSize.EXTRASMALL -> tableHeaderPaddingVerticalExtraSmall to tableComponentPaddingHorizontalExtraSmall
            SirioTableContentSize.SMALL -> tableHeaderPaddingVerticalSmall to tableComponentPaddingHorizontalSmall
            SirioTableContentSize.MEDIUM -> tableHeaderPaddingVerticalMedium to tableComponentPaddingHorizontalMedium
            SirioTableContentSize.LARGE -> tableHeaderPaddingVerticalLarge to tableComponentPaddingHorizontalLarge
        }
    }
    SirioTableHeaderCommon(
        title = title,
        verticalPadding = verticalPadding,
        horizontalPadding = horizontalPadding,
        alignment = alignment,
        weight = weight,
        scroll = scroll,
        withCheckBox = withCheckBox,
        checked = checked,
        iconButton = if (withSortIcon) FaIcons.Sort else null,
        onCheckedChange = onCheckedChange,
        onIconClick = onIconClick,
    )
}

@Preview(heightDp = 1500)
@Composable
private fun SirioTableHeaderPreview() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        SirioTheme {
            Row {
                SirioTableHeader(
                    title = "Header",
                    size = SirioTableContentSize.LARGE,
                    withCheckBox = true,
                    checked = false,
                    onCheckedChange = {},
                    onIconClick = {},
                )
            }
            Row {
                SirioTableHeader(
                    title = "Header",
                    size = SirioTableContentSize.LARGE,
                    alignment = SirioTableContentAlignment.END,
                    withCheckBox = true,
                    checked = false,
                    onCheckedChange = {},
                    onIconClick = {},
                )
            }
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
    }
    SirioTheme(darkTheme = true) {
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        Row {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
    }
}
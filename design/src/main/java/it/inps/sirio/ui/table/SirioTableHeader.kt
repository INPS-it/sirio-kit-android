//
// SirioTableHeader.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableComponentHeightExtraSmall
import it.inps.sirio.theme.tableComponentHeightLarge
import it.inps.sirio.theme.tableComponentHeightMedium
import it.inps.sirio.theme.tableComponentHeightSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalExtraSmall
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalMedium
import it.inps.sirio.theme.tableComponentPaddingHorizontalSmall

@Composable
fun SirioTableHeader(
    title: String,
    size: SirioTableContentSize,
    alignment: SirioTableContentAlignment = SirioTableContentAlignment.START,
    scroll: Boolean = false,
    withCheckBox: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    onIconClick: () -> Unit,
) {
    val (height, horizontalPadding) = remember(size) {
        when (size) {
            SirioTableContentSize.EXTRASMALL -> tableComponentHeightExtraSmall to tableComponentPaddingHorizontalExtraSmall
            SirioTableContentSize.SMALL -> tableComponentHeightSmall to tableComponentPaddingHorizontalSmall
            SirioTableContentSize.MEDIUM -> tableComponentHeightMedium to tableComponentPaddingHorizontalMedium
            SirioTableContentSize.LARGE -> tableComponentHeightLarge to tableComponentPaddingHorizontalLarge
        }
    }
    SirioTableHeaderCommon(
        title = title,
        height = height,
        horizontalPadding = horizontalPadding,
        alignment = alignment,
        scroll = scroll,
        withCheckBox = withCheckBox,
        checked = checked,
        iconButton = FaIcons.Sort,
        onCheckedChange = onCheckedChange,
        onIconClick = onIconClick,
    )
}

@Preview(heightDp = 1500)
@Composable
private fun SirioTableHeaderPreview() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        SirioTheme {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
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
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
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
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
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
        SirioTheme(darkTheme = true) {
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.LARGE,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
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
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.MEDIUM,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
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
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeader(
                title = "Header",
                size = SirioTableContentSize.SMALL,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                checked = false,
                onCheckedChange = {},
                onIconClick = {},
            )
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
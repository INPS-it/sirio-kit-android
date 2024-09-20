//
// SirioTableHeaderCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableComponentHeightLarge
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableHeaderIconSize
import it.inps.sirio.theme.tableHeaderSpacingCheckBoxTitle
import it.inps.sirio.theme.tableHeaderSpacingTitleIcon
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon
import it.inps.sirio.ui.table.cell.SirioTableComponentCommon
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

@Composable
internal fun SirioTableHeaderCommon(
    title: String,
    height: Int,
    horizontalPadding: Int,
    alignment: SirioTableContentAlignment,
    scroll: Boolean = false,
    withCheckBox: Boolean = false,
    checked: Boolean = false,
    iconButton: FaIconType? = null,
    onCheckedChange: (Boolean) -> Unit,
    onIconClick: () -> Unit,
) {
    SirioTableComponentCommon(scroll = scroll) {
        Surface(
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth(),
            color = SirioTheme.colors.table.header.background,
            contentColor = SirioTheme.colors.table.header.title,
        ) {
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .padding(start = horizontalPadding.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (withCheckBox) {
                    SirioCheckboxCommon(checked = checked, onCheckedChange = onCheckedChange)
                    Spacer(modifier = Modifier.width(tableHeaderSpacingCheckBoxTitle.dp))
                }
                SirioTextCommon(
                    text = title,
                    modifier = Modifier.weight(1f),
                    color = LocalContentColor.current,
                    textAlign = if (alignment == SirioTableContentAlignment.START || scroll) TextAlign.Start else TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.table.header.title,
                )
                iconButton?.let {
                    Spacer(modifier = Modifier.width(tableHeaderSpacingTitleIcon.dp))
                    IconButton(onClick = onIconClick) {
                        SirioIcon(
                            faIcon = it,
                            iconColor = SirioTheme.colors.table.header.icon,
                            size = tableHeaderIconSize.dp,
                        )
                    }
                } ?: Spacer(modifier = Modifier.width(horizontalPadding.dp))
            }
        }
    }
}

enum class SirioTableContentSize {
    EXTRASMALL,
    SMALL,
    MEDIUM,
    LARGE,
}

enum class SirioTableContentAlignment {
    START,
    END,
}

@Keep
data class SirioTableHeaderColors(
    val background: Color,
    val title: Color,
    val icon: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableHeaderColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            icon = Color.Unspecified,
        )
    }
}

@Keep
data class SirioTableHeaderTypography(
    val title: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioTableHeaderTypography(
            title = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioTableHeaderCommonPreview() {
    Column {
        SirioTheme {
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.START,
                withCheckBox = true,
                iconButton = FaIcons.Sort,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                iconButton = FaIcons.Sort,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.START,
                scroll = true,
                withCheckBox = true,
                iconButton = FaIcons.Sort,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
        SirioTheme(darkTheme = true) {
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.START,
                withCheckBox = true,
                iconButton = FaIcons.Sort,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.END,
                withCheckBox = true,
                iconButton = FaIcons.Sort,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.START,
                scroll = true,
                withCheckBox = true,
                iconButton = FaIcons.Sort,
                onCheckedChange = {},
                onIconClick = {},
            )
            SirioTableHeaderCommon(
                title = "Header",
                height = tableComponentHeightLarge,
                horizontalPadding = tableComponentPaddingHorizontalLarge,
                alignment = SirioTableContentAlignment.END,
                scroll = true,
                withCheckBox = true,
                onCheckedChange = {},
                onIconClick = {},
            )
        }
    }
}
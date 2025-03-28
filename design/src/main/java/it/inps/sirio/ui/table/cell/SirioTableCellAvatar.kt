//
// SirioTableCellAvatar.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tableCellAvatarImageTextSpacing
import it.inps.sirio.ui.table.SirioTableCellType
import it.inps.sirio.ui.table.SirioTableContentSize
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

@Composable
fun RowScope.SirioTableCellAvatar(
    icon: FaIconType,
    title: String,
    subtitle: String,
    size: SirioTableContentSize,
    weight: Float = 1f,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, weight = weight, scroll = scroll) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SirioIcon(faIcon = icon, iconColor = Color.Unspecified)
            Spacer(modifier = Modifier.width(tableCellAvatarImageTextSpacing.dp))
            Column(verticalArrangement = Arrangement.spacedBy((-6).dp)) {
                SirioTextCommon(
                    text = title,
                    color = SirioTheme.colors.table.cell.avatarTitle,
                    typography = SirioTheme.typography.table.cell.avatarTitle,
                )
                SirioTextCommon(
                    text = subtitle,
                    color = SirioTheme.colors.table.cell.avatarSubtitle,
                    typography = SirioTheme.typography.table.cell.avatarSubtitle,
                )
            }
        }
    }
}

@Composable
internal fun RowScope.SirioTableCellAvatar(data: SirioTableCellType.Avatar, weight: Float = 1f) {
    SirioTableCellAvatar(
        icon = data.icon,
        title = data.title,
        subtitle = data.subtitle,
        weight = weight,
        size = data.size,
        scroll = data.scroll,
    )
}

@Preview
@Composable
private fun SirioTableCellAvatarPreview() {
    SirioTheme {
        Column(Modifier.width(200.dp)) {
            val icon = FaIcons.User
            val title = "Avatar Name"
            val subtitle = "email@mail.com"
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = "$title\n$title",
                    subtitle = subtitle,
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.LARGE,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.MEDIUM,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.SMALL,
                    scroll = false,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.LARGE,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.MEDIUM,
                    scroll = true,
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableCellAvatar(
                    icon = icon,
                    title = title,
                    subtitle = subtitle,
                    size = SirioTableContentSize.SMALL,
                    scroll = true,
                )
            }
        }
    }
}
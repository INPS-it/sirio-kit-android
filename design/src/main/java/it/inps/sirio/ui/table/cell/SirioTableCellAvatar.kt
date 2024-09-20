//
// SirioTableCellAvatar.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.cell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
fun SirioTableCellAvatar(
    icon: FaIconType,
    title: String,
    subtitle: String,
    size: SirioTableContentSize,
    scroll: Boolean = false,
) {
    SirioTableCellCommon(size = size, scroll = scroll) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SirioIcon(faIcon = icon, iconColor = Color.Unspecified)
            Spacer(modifier = Modifier.width(tableCellAvatarImageTextSpacing.dp))
            Column(verticalArrangement = Arrangement.spacedBy((-6).dp)) {
                SirioTextCommon(
                    text = title,
                    color = SirioTheme.colors.table.cell.avatarTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.table.cell.avatarTitle,
                )
                SirioTextCommon(
                    text = subtitle,
                    color = SirioTheme.colors.table.cell.avatarSubtitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.table.cell.avatarSubtitle,
                )
            }
        }
    }
}

@Composable
internal fun SirioTableCellAvatar(data: SirioTableCellType.Avatar) {
    SirioTableCellAvatar(
        icon = data.icon,
        title = data.title,
        subtitle = data.subtitle,
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
            SirioTableCellAvatar(
                icon = icon,
                title = title,
                subtitle = subtitle,
                size = SirioTableContentSize.LARGE,
                scroll = false,
            )
            SirioTableCellAvatar(
                icon = icon,
                title = title,
                subtitle = subtitle,
                size = SirioTableContentSize.MEDIUM,
                scroll = false,
            )
            SirioTableCellAvatar(
                icon = icon,
                title = title,
                subtitle = subtitle,
                size = SirioTableContentSize.SMALL,
                scroll = false,
            )
            SirioTableCellAvatar(
                icon = icon,
                title = title,
                subtitle = subtitle,
                size = SirioTableContentSize.LARGE,
                scroll = true,
            )
            SirioTableCellAvatar(
                icon = icon,
                title = title,
                subtitle = subtitle,
                size = SirioTableContentSize.MEDIUM,
                scroll = true,
            )
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
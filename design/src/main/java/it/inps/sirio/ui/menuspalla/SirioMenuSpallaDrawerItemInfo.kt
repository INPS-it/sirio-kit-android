//
// SirioMenuSpallaDrawerItemInfo.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.menuspalla

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.menuSpallaDrawerItemInfoBorderWidth
import it.inps.sirio.theme.menuSpallaDrawerItemInfoHeight
import it.inps.sirio.theme.menuSpallaDrawerItemInfoIconSize
import it.inps.sirio.theme.menuSpallaDrawerItemInfoPaddingHorizontal
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.border

@Composable
fun SirioMenuSpallaDrawerItemInfo(
    title: String,
    subtitle: String,
    icon: FaIconType,
) {
    Surface(
        modifier = Modifier
            .height(menuSpallaDrawerItemInfoHeight.dp)
            .fillMaxWidth()
            .border(
                bottom = Border(
                    menuSpallaDrawerItemInfoBorderWidth.dp,
                    SirioTheme.colors.menuSpalla.drawerItemInfo.border,
                )
            ),
        color = SirioTheme.colors.menuSpalla.drawerItemInfo.background,
        contentColor = SirioTheme.colors.menuSpalla.drawerItemInfo.content,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = menuSpallaDrawerItemInfoPaddingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioIcon(
                faIcon = icon,
                iconColor = LocalContentColor.current,
                size = menuSpallaDrawerItemInfoIconSize.dp
            )
            Spacer(modifier = Modifier.width(menuSpallaDrawerItemInfoPaddingHorizontal.dp))
            Column {
                SirioTextCommon(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.menuSpalla.drawerItemInfo.title,
                )
                SirioTextCommon(
                    text = subtitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    typography = SirioTheme.typography.menuSpalla.drawerItemInfo.subtitle,
                )
            }
        }
    }
}

@Keep
data class SirioMenuSpallaDrawerItemInfoColors(
    val background: Color,
    val border: Color,
    val content: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioMenuSpallaDrawerItemInfoColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            content = Color.Unspecified,
        )
    }
}

@Keep
data class SirioMenuSpallaDrawerItemInfoTypography(
    val title: TextStyle,
    val subtitle: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioMenuSpallaDrawerItemInfoTypography(
            title = TextStyle.Default,
            subtitle = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioMenuSpallaDrawerItemInfoPreview() {
    SirioTheme {
        SirioMenuSpallaDrawerItemInfo(
            title = "Mario Rossi",
            subtitle = "Profilo Cittadino",
            icon = FaIcons.User,
        )
    }
}
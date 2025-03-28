package it.inps.sirio.ui.titlebar

import androidx.annotation.Keep
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.titleBarHeight
import it.inps.sirio.theme.titleBarIconSize
import it.inps.sirio.theme.titleBarItemPadding
import it.inps.sirio.theme.titleBarItemSpacing
import it.inps.sirio.theme.titleBarPaddingHorizontal
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio component for displaying a title bar with a title and optional items.
 *
 * @param title The title to be displayed in the title bar.
 * @param items A list of [SirioTitleBarItemData] objects representing the items to be displayed in the title bar.
 */
@Composable
internal fun SirioTitleBarCommon(
    title: String,
    items: List<SirioTitleBarItemData> = emptyList(),
) {
    Surface(
        color = SirioTheme.colors.titleBar.container,
        contentColor = SirioTheme.colors.titleBar.content,
    ) {
        Row(
            modifier = Modifier
                .height(titleBarHeight.dp)
                .padding(start = titleBarPaddingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioTextCommon(
                text = title,
                typography = SirioTheme.typography.titleBar.title,
            )
            Spacer(modifier = Modifier.weight(1f))
            items.forEach {
                Row(
                    modifier = Modifier.clickable(role = Role.Button, onClick = it.action),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SirioIcon(
                        faIcon = it.icon,
                        iconColor = LocalContentColor.current,
                        size = titleBarIconSize.dp,
                        contentDescription = it.contentDescription,
                    )
                    if (it.text.isNotBlank()) {
                        Spacer(modifier = Modifier.width(titleBarItemPadding.dp))
                        SirioText(
                            text = it.text,
                            color = SirioTheme.colors.titleBar.content,
                            typography = SirioTheme.typography.titleBar.iconText,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(titleBarItemSpacing.dp))
            }
        }
    }
}

@Keep
data class SirioTitleBarTypography(
    val title: TextStyle,
    val iconText: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioTitleBarTypography(
            title = TextStyle.Default,
            iconText = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioTitleBarCommonPreview() {
    Column {
        val title = "Titolo"
        val items = listOf(
            SirioTitleBarItemData(
                icon = FaIcons.Filter,
                text = "Mostra filtri",
                contentDescription = null,
                action = {},
            ),
            SirioTitleBarItemData(
                icon = FaIcons.EllipsisH,
                text = "Mostra altro",
                contentDescription = null,
                action = {},
            )
        )
        SirioTheme {
            SirioTitleBarCommon(
                title = title,
                items = items,
            )
        }
        SirioTheme(darkTheme = true) {
            SirioTitleBarCommon(
                title = title,
                items = items,
            )
        }
    }
}
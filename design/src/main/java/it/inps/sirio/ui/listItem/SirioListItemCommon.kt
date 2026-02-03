//
// SirioListItemCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.listItem

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.listItemDividerWidth
import it.inps.sirio.theme.listItemIconSize
import it.inps.sirio.theme.listItemIndicatorSize
import it.inps.sirio.theme.listItemInfoSize
import it.inps.sirio.theme.listItemPaddingHorizontal
import it.inps.sirio.theme.listItemPaddingVertical
import it.inps.sirio.theme.listItemSpacingHorizontal
import it.inps.sirio.theme.listItemSpacingVertical
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.Border
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.border
import it.inps.sirio.utils.ifElse

/**
 * A common list item component.
 *
 * It displays a title and/or a description, with an optional icon on the left, an optional indicator
 * on the right (defaults to a chevron), and a bottom divider. The whole item is clickable.
 * At least one of title and description must be provided.
 *
 * @param title The optional item title.
 * @param description The optional item description.
 * @param indicator The optional icon on the right, typically an action indicator. A chevron-right is used by default. Can be `null`.
 * @param type The type of the list item, which determines the icon on the left. See [SirioListItemType].
 * @param showDivider Whether a divider should be displayed at the bottom of the item. Default to `true`.
 * @param onClick The callback to be invoked when this item is clicked.
 */
@Composable
internal fun SirioListItemCommon(
    title: String? = null,
    description: String? = null,
    indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
    type: SirioListItemType = SirioListItemType.Default,
    showDivider: Boolean = true,
    onClick: () -> Unit,
) {
    require(title != null || description != null) { "SirioListItemCommon: at least one of title and description must be not-null" }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(SirioTheme.colors.listItem.background)
            .padding(horizontal = listItemPaddingHorizontal.dp)
            .ifElse(
                condition = showDivider,
                ifTrueModifier = Modifier.border(
                    bottom = Border(
                        strokeWidth = listItemDividerWidth.dp,
                        color = SirioTheme.colors.listItem.divider,
                    )
                )
            )
            .padding(vertical = listItemPaddingVertical.dp),
        horizontalArrangement = Arrangement.spacedBy(listItemSpacingHorizontal.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SirioListItemIcon(type)
        SirioListItemBody(title, description)
        SirioListItemIndicator(indicator)
    }
}

@Composable
private fun SirioListItemIcon(type: SirioListItemType) {
    when (type) {
        SirioListItemType.Default -> Unit
        is SirioListItemType.Icon -> {
            SirioIcon(
                icon = type.icon,
                iconColor = SirioTheme.colors.listItem.icon,
                size = listItemIconSize.dp,
            )
        }

        is SirioListItemType.Info -> {
            SirioIcon(
                icon = SirioIconSource.FaIcon(FaIcons.InfoCircle),
                iconColor = SirioTheme.colors.listItem.info,
                size = listItemInfoSize.dp,
                onClick = type.onInfoClick,
            )
        }
    }
}

@Composable
private fun RowScope.SirioListItemBody(
    title: String?,
    description: String?,
) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(listItemSpacingVertical.dp),
    ) {
        title?.let {
            SirioText(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                color = SirioTheme.colors.listItem.title,
                typography = SirioTheme.foundationTypography.headlineSmMiddle,
            )
        }
        description?.let {
            SirioText(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                color = SirioTheme.colors.listItem.description,
                typography = SirioTheme.foundationTypography.bodySmRegular,
            )
        }
    }
}

@Composable
private fun SirioListItemIndicator(indicator: SirioIconSource?) {
    indicator?.let {
        SirioIcon(
            icon = it,
            iconColor = SirioTheme.colors.listItem.indicator,
            size = listItemIndicatorSize.dp,
        )
    }
}

internal sealed class SirioListItemType() {
    data object Default : SirioListItemType()
    data class Icon(val icon: SirioIconSource) : SirioListItemType()
    data class Info(val onInfoClick: () -> Unit) : SirioListItemType()
}

@Immutable
sealed class SirioListItemData(
    open val title: String? = null,
    open val description: String? = null,
    open val indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
    open val onClick: () -> Unit,
) {
    data class Default(
        override val title: String? = null,
        override val description: String? = null,
        override val indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
        override val onClick: () -> Unit,
    ) : SirioListItemData(title, description, indicator, onClick)

    data class Icon(
        val icon: SirioIconSource,
        override val title: String? = null,
        override val description: String? = null,
        override val indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
        override val onClick: () -> Unit,
    ) : SirioListItemData(title, description, indicator, onClick)

    data class Info(
        override val title: String? = null,
        override val description: String? = null,
        val infoDialogTitle: String,
        val infoDialogText: String,
        override val indicator: SirioIconSource? = SirioIconSource.FaIcon(FaIcons.ChevronRight),
        override val onClick: () -> Unit = {},
    ) : SirioListItemData(title, description, indicator, onClick)
}

@Keep
@Immutable
data class SirioListItemColors(
    val background: Color,
    val icon: Color,
    val info: Color,
    val title: Color,
    val description: Color,
    val indicator: Color,
    val divider: Color,
) {
    companion object {
        val Unspecified = SirioListItemColors(
            background = Color.Unspecified,
            icon = Color.Unspecified,
            info = Color.Unspecified,
            title = Color.Unspecified,
            description = Color.Unspecified,
            indicator = Color.Unspecified,
            divider = Color.Unspecified,
        )
    }
}

internal val listItemLightColors = SirioListItemColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    icon = FoundationColor.colorAliasTextColorSecondaryDark100,
    info = FoundationColor.colorGlobalSemanticInfo100,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    description = FoundationColor.colorAliasTextColorSecondaryDark100,
    indicator = FoundationColor.colorGlobalSecondary100,
    divider = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
)

internal val listItemDarkColors = listItemLightColors

@Preview
@Composable
private fun SirioListItemCommonPreview() {
    SirioTheme {
        Column {
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Default,
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Icon(SirioIconSource.FaIcon(FaIcons.Cube)),
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Info(onInfoClick = {}),
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                type = SirioListItemType.Default,
                onClick = {},
            )
            SirioListItemCommon(
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Default,
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Default,
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Default,
                indicator = null,
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Default,
                showDivider = false,
                onClick = {},
            )
            SirioListItemCommon(
                title = "Titolo list item",
                description = "Lorem ipsum dolor sit amet",
                type = SirioListItemType.Default,
                indicator = SirioIconSource.FaIcon(FaIcons.InfoCircle),
                onClick = {},
            )
        }
    }
}
//
// SirioAppNavigationCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationActionButtonSize
import it.inps.sirio.theme.appNavigationActionContentSize
import it.inps.sirio.theme.appNavigationActionIconPadding
import it.inps.sirio.theme.appNavigationActionIconSize
import it.inps.sirio.theme.appNavigationMaxLines
import it.inps.sirio.theme.appNavigationTitleLongMaxLines
import it.inps.sirio.theme.appNavigationTitleMaxLines
import it.inps.sirio.ui.badge.SirioBadgeBox
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
* Displays the title within the Sirio app's navigation bar.
*
* @param title The text to be displayed as the navigation title.
* @param typography The `TextStyle` to be applied to the title. Defaults to `SirioTheme.foundationTypography.labelMdMiddle`.
*/
@Composable
internal fun SirioAppNavigationTitle(
    title: String,
    typography: TextStyle = SirioTheme.foundationTypography.labelMdMiddle,
    maxLines: Int = appNavigationMaxLines,
) {
    SirioTextCommon(
        text = title,
        color = SirioTheme.colors.appNavigation.text,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        typography = typography,
    )
}

/**
 * The title of the app navigation
 *
 * @param title The text to show
 * @param long Whether the app navigation is long type
 * @param big Whether the app navigation is big type
 */
@Composable
internal fun SirioAppNavigationTitleLegacy(title: String, long: Boolean = false, big: Boolean = false) {
    SirioTextCommon(
        text = title,
        maxLines = if (long) appNavigationTitleLongMaxLines else appNavigationTitleMaxLines,
        typography = if (big) SirioTheme.typography.appNavigation.titleBig else SirioTheme.typography.appNavigation.title,
    )
}

/**
 * The actions on the right side of the app navigation
 *
 * @param rightFirstItem An [SirioAppNavigationItemData] for the first item
 * @param rightSecondItem An [SirioAppNavigationItemData] for the second item
 * @param rightThirdItem An [SirioAppNavigationItemData] for the third item
 */
@Composable
internal fun sirioAppNavigationActions(
    rightFirstItem: SirioAppNavigationItemData? = null,
    rightSecondItem: SirioAppNavigationItemData? = null,
    rightThirdItem: SirioAppNavigationItemData? = null,
): @Composable (RowScope.() -> Unit) =
    {
        rightFirstItem?.let { SirioAppNavigationButton(it) }
        rightSecondItem?.let { SirioAppNavigationButton(it) }
        rightThirdItem?.let { SirioAppNavigationButton(it) }
    }

/**
 * A button on the app navigation.
 *
 * This Composable renders a button that can either display a username or an
 * icon, based on the data provided. If a username is provided, it renders
 * a username button; otherwise, it renders an icon button.
 * It uses the provided [SirioAppNavigationItemData] to get data.
 *
 * @param data An [SirioAppNavigationItemData] with the content of the button
 */
@Composable
internal fun SirioAppNavigationButton(
    data: SirioAppNavigationItemData,
) {
    if (data.username.isNotBlank())
        SirioAppNavigationUsernameButton(
            username = data.username,
            contentDescription = data.contentDescription,
            action = data.action
        )
    else
        SirioAppNavigationIconButton(
            icon = data.icon,
            contentDescription = data.contentDescription,
            badge = data.badge,
            action = data.action
        )
}

/**
 * A button with an icon.
 *
 * This Composable renders a button that displays an icon. It supports an
 * optional badge and a click action. The icon is displayed using the
 * [SirioIcon] Composable, and the button has customizable colors and
 * ripple effects.
 *
 * @param icon The icon to show
 * @param contentDescription The content description for the item
 * @param badge If the icon has a badge
 * @param action The click action callback
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SirioAppNavigationIconButton(
    icon: FaIconType,
    contentDescription: String? = null,
    badge: Boolean = false,
    action: () -> Unit,
) {
    val sirioAppNavigationButtonRipple =
        RippleConfiguration(
            color = SirioTheme.colors.appNavigation.actionContainerPressed,
            rippleAlpha = null,
        )
    CompositionLocalProvider(LocalRippleConfiguration provides sirioAppNavigationButtonRipple) {
        IconButton(
            onClick = action,
            modifier = Modifier
                .size(appNavigationActionButtonSize.dp)
                .padding(appNavigationActionIconPadding.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = SirioTheme.colors.appNavigation.actionContainer,
                contentColor = SirioTheme.colors.appNavigation.actionContent,
            ),
        ) {
            SirioBadgeBox(hasBadge = badge) {
                SirioIcon(
                    icon = SirioIconSource.FaIcon(icon),
                    iconColor = LocalContentColor.current,
                    size = appNavigationActionIconSize.dp,
                    contentDescription = contentDescription,
                )
            }
        }
    }
}

/**
 * A button with the username's first two letter.
 *
 * This Composable renders a button that displays the first two letters of a
 * username. It's intended for use in the app navigation bar as a user
 * profile indicator.
 *
 * @param username The username
 * @param contentDescription The content description for the item
 * @param action The callback at click
 */
@Composable
internal fun SirioAppNavigationUsernameButton(
    username: String,
    contentDescription: String? = null,
    action: () -> Unit,
) {
    val semantics =
        if (contentDescription != null) {
            Modifier.semantics { this.contentDescription = contentDescription }
        } else {
            Modifier
        }
    TextButton(
        onClick = action,
        modifier = Modifier
            .size(appNavigationActionButtonSize.dp)
            .padding(appNavigationActionIconPadding.dp)
            .then(semantics),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp),
    ) {
        Box(
            modifier = Modifier
                .size(appNavigationActionContentSize.dp)
                .background(
                    color = SirioTheme.colors.appNavigation.avatarBackground,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            SirioTextCommon(
                text = username.take(2).uppercase(),
                color = SirioTheme.colors.appNavigation.avatarText,
                typography = SirioTheme.foundationTypography.labelMdHeavy,
            )
        }
    }
}

@Keep
data class SirioAppNavigationColors(
    val background: Color,
    val actionContent: Color,
    val actionContainer: Color,
    val actionContainerPressed: Color,
    val searchTextFieldBackground: Color,
    val searchBackground: Color,
    val searchContent: Color,
    val searchText: Color,
    val text: Color,
    val avatarBackground: Color,
    val avatarText: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAppNavigationColors(
            background = Color.Unspecified,
            actionContent = Color.Unspecified,
            actionContainer = Color.Unspecified,
            actionContainerPressed = Color.Unspecified,
            searchTextFieldBackground = Color.Unspecified,
            searchBackground = Color.Unspecified,
            searchContent = Color.Unspecified,
            searchText = Color.Unspecified,
            text = Color.Unspecified,
            avatarBackground = Color.Unspecified,
            avatarText = Color.Unspecified,
        )
    }
}

internal val appNavigationLightColors = SirioAppNavigationColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    actionContent = FoundationColor.colorAliasAppInteractiveSecondaryDefault,
    actionContainer = Color.Transparent,
    actionContainerPressed = FoundationColor.colorAliasOverlay15Secondary100,
    searchTextFieldBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    searchBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    searchContent = FoundationColor.colorAliasAppInteractiveSecondaryDefault,
    searchText = FoundationColor.colorAliasTextColorDisabled,
    text = FoundationColor.colorAliasTextColorPrimaryDark110,
    avatarBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    avatarText = FoundationColor.colorAliasInteractivePrimaryDefault,
)

internal val appNavigationDarkColors = SirioAppNavigationColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryDark120,
    actionContent = FoundationColor.colorAliasTextColorPrimaryLight0,
    actionContainer = Color.Transparent,
    actionContainerPressed = FoundationColor.colorAliasOverlay25Primary000,
    searchTextFieldBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    searchBackground = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    searchContent = FoundationColor.colorAliasAppInteractiveSecondaryDefault,
    searchText = FoundationColor.colorAliasTextColorDisabled,
    text = FoundationColor.colorAliasTextColorPrimaryLight50,
    avatarBackground = FoundationColor.colorAliasBackgroundColorPrimaryDark115,
    avatarText = FoundationColor.colorAliasAppInteractivePrimary000Default,
)

@Keep
data class SirioAppNavigationTypography(
    val search: TextStyle,
    val searchPlaceholder: TextStyle,
    val title: TextStyle,
    val titleBig: TextStyle,
    val avatar: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioAppNavigationTypography(
            search = TextStyle.Default,
            searchPlaceholder = TextStyle.Default,
            title = TextStyle.Default,
            titleBig = TextStyle.Default,
            avatar = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun SirioAppNavigationTitlePreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            val title = "Titolo Grande"
            SirioTheme(darkTheme = false) {
                SirioAppNavigationTitle(title = title)
            }
            SirioTheme(darkTheme = true) {
                SirioAppNavigationTitle(title = title)
            }
            SirioTheme(darkTheme = false) {
                SirioAppNavigationIconButton(icon = FaIcons.Home, badge = false) {}
            }
            SirioTheme(darkTheme = true) {
                SirioAppNavigationIconButton(icon = FaIcons.Home, badge = false) {}
            }
            SirioTheme(darkTheme = false) {
                SirioAppNavigationIconButton(icon = FaIcons.User, badge = true) {}
            }
            SirioTheme(darkTheme = true) {
                SirioAppNavigationIconButton(icon = FaIcons.User, badge = true) {}
            }
            SirioTheme(darkTheme = false) {
                SirioAppNavigationUsernameButton(
                    username = "MC",
                    action = {},
                )
            }
            SirioTheme(darkTheme = true) {
                SirioAppNavigationUsernameButton(
                    username = "MC",
                    action = {},
                )
            }
        }
    }
}

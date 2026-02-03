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
import androidx.compose.ui.platform.testTag
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
        rightFirstItem?.let { SirioAppNavigationButton(it, "buttonRightFirstItem") }
        rightSecondItem?.let { SirioAppNavigationButton(it, "buttonRightSecondItem") }
        rightThirdItem?.let { SirioAppNavigationButton(it, "buttonRightThirdItem") }
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
    testTag: String? = null,
) {
    if (data.username.isNotBlank())
        SirioAppNavigationAvatarButton(
            username = data.username,
            contentDescription = data.contentDescription,
            testTag = testTag,
            action = data.action,
        )
    else
        SirioAppNavigationIconButton(
            icon = data.icon,
            contentDescription = data.contentDescription,
            badge = data.badge,
            testTag = testTag,
            action = data.action,
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
    testTag: String? = null,
    action: () -> Unit,
) {
    val sirioAppNavigationButtonRipple =
        RippleConfiguration(
            color = SirioTheme.colors.appNavigation.action.containerPressed,
            rippleAlpha = null,
        )
    CompositionLocalProvider(LocalRippleConfiguration provides sirioAppNavigationButtonRipple) {
        IconButton(
            onClick = action,
            modifier = Modifier
                .size(appNavigationActionButtonSize.dp)
                .padding(appNavigationActionIconPadding.dp)
                .testTag(testTag.orEmpty()),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = SirioTheme.colors.appNavigation.action.container,
                contentColor = SirioTheme.colors.appNavigation.action.content,
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
internal fun SirioAppNavigationAvatarButton(
    username: String,
    contentDescription: String? = null,
    testTag: String? = null,
    action: () -> Unit,
) {
    val semantics =
        if (contentDescription != null) {
            Modifier.semantics { this.contentDescription = contentDescription }
        } else {
            Modifier
        }
    val sirioAvatarButtonRipple =
        RippleConfiguration(
            color = SirioTheme.colors.appNavigation.avatar.containerPressed,
            rippleAlpha = null,
        )
    CompositionLocalProvider(LocalRippleConfiguration provides sirioAvatarButtonRipple) {
        TextButton(
            onClick = action,
            modifier = Modifier
                .size(appNavigationActionButtonSize.dp)
                .padding(appNavigationActionIconPadding.dp)
                .testTag(testTag.orEmpty())
                .then(semantics),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(appNavigationActionContentSize.dp)
                    .background(
                        color = SirioTheme.colors.appNavigation.avatar.container,
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                SirioTextCommon(
                    text = username.take(2).uppercase(),
                    color = SirioTheme.colors.appNavigation.avatar.content,
                    typography = SirioTheme.foundationTypography.labelMdHeavy,
                )
            }
        }
    }
}

@Keep
data class SirioAppNavigationColors(
    val background: Color,
    val action: SirioAppNavigationActionColors,
    val avatar: SirioAppNavigationAvatarColors,
    val logo: SirioAppNavigationLogoColors,
    val search: SirioAppNavigationSearchColors,
    val text: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAppNavigationColors(
            background = Color.Unspecified,
            action = SirioAppNavigationActionColors.Unspecified,
            avatar = SirioAppNavigationAvatarColors.Unspecified,
            logo = SirioAppNavigationLogoColors.Unspecified,
            search = SirioAppNavigationSearchColors.Unspecified,
            text = Color.Unspecified,
        )
    }
}

@Keep
data class SirioAppNavigationActionColors(
    val content: Color,
    val container: Color,
    val containerPressed: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAppNavigationActionColors(
            content = Color.Unspecified,
            container = Color.Unspecified,
            containerPressed = Color.Unspecified,
        )
    }
}

@Keep
data class SirioAppNavigationAvatarColors(
    val content: Color,
    val container: Color,
    val containerPressed: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAppNavigationAvatarColors(
            content = Color.Unspecified,
            container = Color.Unspecified,
            containerPressed = Color.Unspecified,
        )
    }
}

val appNavigationActionLightColors = SirioAppNavigationActionColors(
    content = FoundationColor.colorAliasAppInteractiveSecondaryDefault,
    container = Color.Transparent,
    containerPressed = FoundationColor.colorAliasOverlay15Secondary100,
)

val appNavigationActionDarkColors = SirioAppNavigationActionColors(
    content = FoundationColor.colorAliasTextColorPrimaryLight0,
    container = Color.Transparent,
    containerPressed = FoundationColor.colorAliasOverlay25Primary000,
)

val appNavigationAvatarLightColors = SirioAppNavigationAvatarColors(
    container = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    containerPressed = FoundationColor.colorAliasOverlay15Secondary100,
    content = FoundationColor.colorAliasInteractivePrimaryDefault,
)

val appNavigationAvatarDarkColors = appNavigationAvatarLightColors

internal val appNavigationLightColors = SirioAppNavigationColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    action = appNavigationActionLightColors,
    avatar = appNavigationAvatarLightColors,
    logo = appNavigationLogoLightColors,
    search = appNavigationSearchLightColors,
    text = FoundationColor.colorAliasTextColorPrimaryDark110,
)

internal val appNavigationDarkColors = SirioAppNavigationColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryDark120,
    action = appNavigationActionDarkColors,
    avatar = appNavigationAvatarDarkColors,
    logo = appNavigationLogoDarkColors,
    search = appNavigationSearchDarkColors,
    text = FoundationColor.colorAliasTextColorPrimaryLight50,
)

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
                SirioAppNavigationAvatarButton(
                    username = "MC",
                    action = {},
                )
            }
            SirioTheme(darkTheme = true) {
                SirioAppNavigationAvatarButton(
                    username = "MC",
                    action = {},
                )
            }
        }
    }
}

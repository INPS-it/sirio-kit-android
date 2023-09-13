//
// AppNavigationCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.appnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.appNavigationIconSize
import it.inps.sirio.theme.appNavigationTitleLongMaxLines
import it.inps.sirio.theme.appNavigationTitleMaxLines
import it.inps.sirio.theme.appNavigationUsernameButtonSize
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * The title of the app navigation
 *
 * @param title The text to show
 * @param long Whether the app navigation is long type
 * @param big Whether the app navigation is big type
 */
@Composable
internal fun AppNavigationTitle(title: String, long: Boolean = false, big: Boolean = false) {
    SirioTextCommon(
        text = title,
        maxLines = if (long) appNavigationTitleLongMaxLines else appNavigationTitleMaxLines,
        typography = if (big) SirioTheme.typography.appNavigationTitleBig else SirioTheme.typography.appNavigationTitle,
    )
}

/**
 * The actions on the right side of the app navigation
 *
 * @param rightFirstItem An [AppNavigationItemData] for the first item
 * @param rightSecondItem An [AppNavigationItemData] for the second item
 * @param rightThirdItem An [AppNavigationItemData] for the third item
 */
@Composable
internal fun appNavigationActions(
    rightFirstItem: AppNavigationItemData? = null,
    rightSecondItem: AppNavigationItemData? = null,
    rightThirdItem: AppNavigationItemData? = null,
): @Composable (RowScope.() -> Unit) =
    {
        rightFirstItem?.let { AppNavigationButton(it) }
        rightSecondItem?.let { AppNavigationButton(it) }
        rightThirdItem?.let { AppNavigationButton(it) }
    }

/**
 * A button on the app navigation
 * It shows the username, if provided, or the icon
 *
 * @param data An [AppNavigationItemData] with the content of the button
 */
@Composable
internal fun AppNavigationButton(
    data: AppNavigationItemData,
) {
    CompositionLocalProvider(
        LocalRippleTheme provides AppNavigationButtonRippleTheme,
    ) {
        if (data.username.isNotBlank())
            AppNavigationUsernameButton(
                username = data.username,
                contentDescription = data.contentDescription,
                action = data.action
            )
        else
            AppNavigationIconButton(
                icon = data.icon,
                contentDescription = data.contentDescription,
                action = data.action
            )
    }
}

/**
 * A button with an icon
 *
 * @param icon The icon to show
 * @param action The click action callback
 */
@Composable
internal fun AppNavigationIconButton(
    icon: FaIconType,
    contentDescription: String? = null,
    action: () -> Unit,
) {
    IconButton(onClick = action) {
        SirioIcon(
            faIcon = icon,
            iconColor = SirioTheme.colors.appNavigationIcon,
            size = appNavigationIconSize,
            contentDescription = contentDescription,
        )
    }
}

/**
 * A button with the username's first two letter
 *
 * @param username The username
 * @param contentDescription The content description for the item
 * @param action The callback at click
 */
@Composable
internal fun AppNavigationUsernameButton(
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
            .size(appNavigationUsernameButtonSize)
            .then(semantics),
        colors = ButtonDefaults.textButtonColors(containerColor = SirioTheme.colors.appNavigationUsernameBackground),
        contentPadding = PaddingValues(0.dp),
        shape = CircleShape
    ) {
        SirioTextCommon(
            text = username.take(2).uppercase(),
            color = SirioTheme.colors.appNavigationUsernameText,
            typography = SirioTheme.typography.appNavigationUsername,
        )
    }
}

internal object AppNavigationButtonRippleTheme : RippleTheme {

    // Here you should return the ripple color you want
    // and not use the defaultRippleColor extension on RippleTheme.
    // Using that will override the ripple color set in DarkMode
    // or when you set light parameter to false
    @Composable
    override fun defaultColor(): Color = SirioTheme.colors.appNavigationIconPressed

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Black,
        lightTheme = !isSystemInDarkTheme()
    )
}

@Preview(showSystemUi = true)
@Composable
private fun AppNavigationTitlePreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            val title = "Titolo Grande"
            SirioTheme(darkTheme = false) {
                AppNavigationTitle(title = title)
            }
            SirioTheme(darkTheme = true) {
                AppNavigationTitle(title = title)
            }
            AppNavigationIconButton(icon = FaIcons.Home) {}
        }
    }
}
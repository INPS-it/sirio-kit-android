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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.*
import it.inps.sirio.theme.*
import it.inps.sirio.utils.FaIconCentered

/**
 * The title of the app navigation
 *
 * @param title The text to show
 * @param long Whether the app navigation is long type
 * @param big Whether the app navigation is big type
 */
@Composable
internal fun AppNavigationTitle(title: String, long: Boolean = false, big: Boolean = false) {
    Text(
        text = title,
        style = if (big) SirioTheme.typography.appNavigationTitleBig else SirioTheme.typography.appNavigationTitle,
        maxLines = if (long) appNavigationTitleLongMaxLines else appNavigationTitleMaxLines,
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
            AppNavigationUsernameButton(username = data.username, action = data.action)
        else
            AppNavigationIconButton(icon = data.icon, action = data.action)
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
    action: () -> Unit,
) {
    IconButton(onClick = action) {
        FaIconCentered(
            icon = icon,
            iconColor = SirioTheme.colors.appNavigationIcon,
            size = appNavigationIconSize,
        )
    }
}

/**
 * A button with the username's first two letter
 *
 * @param username The username
 * @param action The callback at click
 */
@Composable
internal fun AppNavigationUsernameButton(
    username: String,
    action: () -> Unit,
) {
    TextButton(
        onClick = action,
        modifier = Modifier.size(appNavigationUsernameButtonSize),
        colors = ButtonDefaults.textButtonColors(containerColor = SirioTheme.colors.appNavigationUsernameBackground),
        contentPadding = PaddingValues(0.dp),
        shape = CircleShape
    ) {
        Text(
            text = username.take(2).uppercase(),
            color = SirioTheme.colors.appNavigationUsernameText,
            style = SirioTheme.typography.appNavigationUsername,
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
        }
    }
}
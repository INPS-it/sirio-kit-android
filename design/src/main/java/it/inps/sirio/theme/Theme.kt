//
// Theme.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import it.inps.sirio.ui.accordion.SirioAccordionColors
import it.inps.sirio.ui.badge.SirioBadgeColors
import it.inps.sirio.ui.card.SirioCardsColors
import it.inps.sirio.ui.card.SirioCardsTypography
import it.inps.sirio.ui.dialog.SirioDialogColors
import it.inps.sirio.ui.pagination.SirioPaginationColors
import it.inps.sirio.ui.searchbar.SirioSearchBarColors
import it.inps.sirio.ui.tabs.SirioTabsColors
import it.inps.sirio.ui.tag.SirioTagsColors
import it.inps.sirio.ui.textfield.SirioTextFieldColors

@Composable
fun SirioTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

//    val sysUiController = rememberSystemUiController()
//    SideEffect {
//        sysUiController.setSystemBarsColor(
//            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
//        )
//    }

    CompositionLocalProvider(
        LocalSirioColors provides colors,
        LocalSirioTypography provides Typography,
        LocalRippleTheme provides ClearRippleTheme,
//        LocalSirioElevation provides Elevation,
        content = content
    )
}

object SirioTheme {
    val colors: SirioColors
        @Composable
        get() = LocalSirioColors.current

    val typography: SirioTypography
        @Composable
        get() = LocalSirioTypography.current

//    val elevation: SirioElevation
//        @Composable
//        get() = LocalSirioElevation.current
}

@Immutable
data class SirioTypography(
    val appNavigationTitle: TextStyle,
    val appNavigationTitleBig: TextStyle,
    val appNavigationUsername: TextStyle,
    val appNavigationSearchPlaceholder: TextStyle,
    val appNavigationSearch: TextStyle,
    val fabText: TextStyle,
    val tabBarItemText: TextStyle,
    val accordionText: TextStyle,
    val chipText: TextStyle,
    val tagText: TextStyle,
    val checkboxLabelText: TextStyle,
    val radioLabelText: TextStyle,
    val toggleLabelText: TextStyle,
    val sliderTitle: TextStyle,
    val sliderText: TextStyle,
    val sliderNumber: TextStyle,
    val progressBarLabel: TextStyle,
    val progressBarNumber: TextStyle,
    val buttonText: TextStyle,
    val fileUploadTitle: TextStyle,
    val fileUploadText: TextStyle,
    val notificationInlineTitle: TextStyle,
    val notificationInlineText: TextStyle,
    val notificationToastTitle: TextStyle,
    val notificationToastText: TextStyle,
    val textFieldText: TextStyle,
    val textFieldPlaceholder: TextStyle,
    val textFieldLabel: TextStyle,
    val textFieldHelperText: TextStyle,
    val textFieldDropdownLabel: TextStyle,
    val paginationTileNumber: TextStyle,
    val tabTextSelected: TextStyle,
    val tabTextDefault: TextStyle,
    val dialogTitle: TextStyle,
    val dialogText: TextStyle,
    val card: SirioCardsTypography,
)

/**
 * Sirio custom Color Palette
 */
@Immutable
data class SirioColors(
    val brand: Color,
    val fabDefaultBackground: Color,
    val fabHoverBackground: Color,
    val fabFocusBackground: Color,
    val fabPressedBackground: Color,
    val fabBorderFocus: Color,
    val fabContent: Color,
    val appNavigationBackground: Color,
    val appNavigationIcon: Color,
    val appNavigationIconPressed: Color,
    val appNavigationText: Color,
    val appNavigationUsernameBackground: Color,
    val appNavigationUsernameText: Color,
    val appNavigationSearchBackground1: Color,
    val appNavigationSearchBackground2: Color,
    val appNavigationSearchText: Color,
    val appNavigationSearchIcon: Color,
    val tabBarBackground: Color,
    val tabBarContent: Color,
    val tabBarActive: Color,
    val tag: SirioTagsColors,
    val accordion: SirioAccordionColors,
    val chipDefaultBackground: Color,
    val chipHoverBackground: Color,
    val chipFocusBackground: Color,
    val chipWithClosePressedBackground: Color,
    val chipWithCloseDisabledBackground: Color,
    val chipWithCloseDefaultBackground: Color,
    val chipWithCloseHoverBackground: Color,
    val chipWithCloseFocusBackground: Color,
    val chipPressedBackground: Color,
    val chipDisabledBackground: Color,
    val chipDefaultButtonBackground: Color,
    val chipHoverButtonBackground: Color,
    val chipFocusButtonBackground: Color,
    val chipPressedButtonBackground: Color,
    val chipDisabledButtonBackground: Color,
    val chipButtonBorder: Color,
    val chipFocusBorder: Color,
    val chipContent: Color,
    val chipDisabledContent: Color,
    val chipDisactiveBackground: Color,
    val checkboxBackground: Color,
    val checkboxDefaultContent: Color,
    val checkboxHoverContent: Color,
    val checkboxFocusContent: Color,
    val checkboxFocusBorder: Color,
    val checkboxPressed: Color,
    val checkboxPressedText: Color,
    val checkboxDisabled: Color,
    val checkboxDisabledContent: Color,
    val radioBackground: Color,
    val radioDefault: Color,
    val radioHover: Color,
    val radioFocus: Color,
    val radioFocusBorder: Color,
    val radioPressed: Color,
    val radioDisabled: Color,
    val radioDisabledBorder: Color,
    val toggleBackground: Color,
    val toggleDefaultOff: Color,
    val toggleDefaultOn: Color,
    val toggleHoverOff: Color,
    val toggleHoverOn: Color,
    val toggleFocusOff: Color,
    val toggleFocusOn: Color,
    val toggleFocusExtraBorder: Color,
    val toggleDisabled: Color,
    val toggleDisabledBorder: Color,
    val sliderThumb: Color,
    val sliderPressedThumb: Color,
    val sliderDisabled: Color,
    val sliderInactive: Color,
    val sliderActive: Color,
    val sliderFocusActive: Color,
    val sliderPressedActive: Color,
    val sliderTitle: Color,
    val sliderText: Color,
    val sliderNumbers: Color,
    val sliderDefaultTextFieldBackground: Color,
    val sliderDisabledTextFieldBackground: Color,
    val sliderDefaultTextFieldBorder: Color,
    val sliderDefaultTextFieldText: Color,
    val sliderHoverTextFieldBorder: Color,
    val sliderFocusTextFieldExtraBorder: Color,
    val sliderHoverTextFieldText: Color,
    val sliderFocusTextFieldBorder: Color,
    val sliderFocusTextFieldText: Color,
    val sliderPressedTextFieldBorder: Color,
    val sliderPressedTextFieldText: Color,
    val sliderDisabledTextFieldBorder: Color,
    val sliderDisabledTextFieldText: Color,
    val progressBarLabel: Color,
    val progressBarNumber: Color,
    val progressBarBackground: Color,
    val progressBarGradient: List<Color>,
    val buttons: ButtonColors,
    val fileUploadTitle: Color,
    val fileUploadText: Color,
    val notificationColors: NotificationColors,
    val textField: SirioTextFieldColors,
    val pagination: SirioPaginationColors,
    val tabs: SirioTabsColors,
    val searchBar: SirioSearchBarColors,
    val dialog: SirioDialogColors,
    val card: SirioCardsColors,
    val badge: SirioBadgeColors,
    val isDark: Boolean,
)

@Immutable
data class SirioElevation(
    val default: Dp,
    val pressed: Dp,
)

@Keep
data class SirioColorState(
    val default: Color,
    val disabled: Color,
    val focused: Color,
    val hovered: Color,
    val pressed: Color,
    val alert: Color,
    val warning: Color,
    val success: Color,
    val info: Color,
) {
    constructor(
        default: Color,
        disabled: Color,
        focused: Color,
        hovered: Color,
        pressed: Color,
    ) : this(
        default = default,
        disabled = disabled,
        focused = focused,
        hovered = hovered,
        pressed = pressed,
        alert = Color.Unspecified,
        warning = Color.Unspecified,
        success = Color.Unspecified,
        info = Color.Unspecified,
    )

    companion object {
        @Stable
        val Unspecified = SirioColorState(
            Color.Unspecified,
            Color.Unspecified,
            Color.Unspecified,
            Color.Unspecified,
            Color.Unspecified,
        )

        fun all(color: Color, disabled: Color? = null) =
            SirioColorState(
                default = color,
                disabled = disabled ?: color,
                focused = color,
                hovered = color,
                pressed = color,
                alert = color,
                warning = color,
                success = color,
                info = color,
            )
    }
}

private val LocalSirioColors = staticCompositionLocalOf {
    SirioColors(
        brand = Color.Unspecified,
        fabDefaultBackground = Color.Unspecified,
        fabHoverBackground = Color.Unspecified,
        fabFocusBackground = Color.Unspecified,
        fabPressedBackground = Color.Unspecified,
        fabBorderFocus = Color.Unspecified,
        fabContent = Color.Unspecified,
        appNavigationBackground = Color.Unspecified,
        appNavigationIcon = Color.Unspecified,
        appNavigationIconPressed = Color.Unspecified,
        appNavigationText = Color.Unspecified,
        appNavigationUsernameBackground = Color.Unspecified,
        appNavigationUsernameText = Color.Unspecified,
        appNavigationSearchBackground1 = Color.Unspecified,
        appNavigationSearchBackground2 = Color.Unspecified,
        appNavigationSearchText = Color.Unspecified,
        appNavigationSearchIcon = Color.Unspecified,
        tabBarBackground = Color.Unspecified,
        tabBarContent = Color.Unspecified,
        tabBarActive = Color.Unspecified,
        tag = SirioTagsColors.Unspecified,
        accordion = SirioAccordionColors.Unspecified,
        chipDefaultBackground = Color.Unspecified,
        chipHoverBackground = Color.Unspecified,
        chipFocusBackground = Color.Unspecified,
        chipPressedBackground = Color.Unspecified,
        chipDisabledBackground = Color.Unspecified,
        chipWithCloseDefaultBackground = Color.Unspecified,
        chipWithCloseHoverBackground = Color.Unspecified,
        chipWithCloseFocusBackground = Color.Unspecified,
        chipWithClosePressedBackground = Color.Unspecified,
        chipWithCloseDisabledBackground = Color.Unspecified,
        chipDefaultButtonBackground = Color.Unspecified,
        chipHoverButtonBackground = Color.Unspecified,
        chipFocusButtonBackground = Color.Unspecified,
        chipPressedButtonBackground = Color.Unspecified,
        chipDisabledButtonBackground = Color.Unspecified,
        chipButtonBorder = Color.Unspecified,
        chipFocusBorder = Color.Unspecified,
        chipContent = Color.Unspecified,
        chipDisabledContent = Color.Unspecified,
        chipDisactiveBackground = Color.Unspecified,
        checkboxBackground = Color.Unspecified,
        checkboxDefaultContent = Color.Unspecified,
        checkboxHoverContent = Color.Unspecified,
        checkboxFocusContent = Color.Unspecified,
        checkboxFocusBorder = Color.Unspecified,
        checkboxPressed = Color.Unspecified,
        checkboxPressedText = Color.Unspecified,
        checkboxDisabled = Color.Unspecified,
        checkboxDisabledContent = Color.Unspecified,
        radioBackground = Color.Unspecified,
        radioDefault = Color.Unspecified,
        radioHover = Color.Unspecified,
        radioFocus = Color.Unspecified,
        radioFocusBorder = Color.Unspecified,
        radioPressed = Color.Unspecified,
        radioDisabled = Color.Unspecified,
        radioDisabledBorder = Color.Unspecified,
        toggleBackground = Color.Unspecified,
        toggleDefaultOff = Color.Unspecified,
        toggleDefaultOn = Color.Unspecified,
        toggleHoverOff = Color.Unspecified,
        toggleHoverOn = Color.Unspecified,
        toggleFocusOff = Color.Unspecified,
        toggleFocusOn = Color.Unspecified,
        toggleFocusExtraBorder = Color.Unspecified,
        toggleDisabled = Color.Unspecified,
        toggleDisabledBorder = Color.Unspecified,
        sliderThumb = Color.Unspecified,
        sliderPressedThumb = Color.Unspecified,
        sliderDisabled = Color.Unspecified,
        sliderInactive = Color.Unspecified,
        sliderActive = Color.Unspecified,
        sliderFocusActive = Color.Unspecified,
        sliderPressedActive = Color.Unspecified,
        sliderTitle = Color.Unspecified,
        sliderText = Color.Unspecified,
        sliderNumbers = Color.Unspecified,
        sliderDefaultTextFieldBackground = Color.Unspecified,
        sliderDisabledTextFieldBackground = Color.Unspecified,
        sliderDefaultTextFieldBorder = Color.Unspecified,
        sliderDefaultTextFieldText = Color.Unspecified,
        sliderHoverTextFieldBorder = Color.Unspecified,
        sliderHoverTextFieldText = Color.Unspecified,
        sliderFocusTextFieldBorder = Color.Unspecified,
        sliderFocusTextFieldExtraBorder = Color.Unspecified,
        sliderFocusTextFieldText = Color.Unspecified,
        sliderPressedTextFieldBorder = Color.Unspecified,
        sliderPressedTextFieldText = Color.Unspecified,
        sliderDisabledTextFieldBorder = Color.Unspecified,
        sliderDisabledTextFieldText = Color.Unspecified,
        progressBarLabel = Color.Unspecified,
        progressBarNumber = Color.Unspecified,
        progressBarBackground = Color.Unspecified,
        progressBarGradient = listOf(Color.Unspecified),
        buttons = ButtonColors.Unspecified,
        fileUploadTitle = Color.Unspecified,
        fileUploadText = Color.Unspecified,
        notificationColors = NotificationColors.Unspecified,
        textField = SirioTextFieldColors.Unspecified,
        pagination = SirioPaginationColors.Unspecified,
        tabs = SirioTabsColors.Unspecified,
        searchBar = SirioSearchBarColors.Unspecified,
        dialog = SirioDialogColors.Unspecified,
        card = SirioCardsColors.Unspecified,
        badge = SirioBadgeColors.Unspecified,
        isDark = false,
    )
}

internal val LocalSirioTypography = staticCompositionLocalOf {
    SirioTypography(
        appNavigationTitle = TextStyle.Default,
        appNavigationTitleBig = TextStyle.Default,
        appNavigationUsername = TextStyle.Default,
        appNavigationSearchPlaceholder = TextStyle.Default,
        appNavigationSearch = TextStyle.Default,
        fabText = TextStyle.Default,
        tabBarItemText = TextStyle.Default,
        accordionText = TextStyle.Default,
        chipText = TextStyle.Default,
        tagText = TextStyle.Default,
        checkboxLabelText = TextStyle.Default,
        radioLabelText = TextStyle.Default,
        toggleLabelText = TextStyle.Default,
        sliderTitle = TextStyle.Default,
        sliderText = TextStyle.Default,
        sliderNumber = TextStyle.Default,
        progressBarLabel = TextStyle.Default,
        progressBarNumber = TextStyle.Default,
        buttonText = TextStyle.Default,
        fileUploadTitle = TextStyle.Default,
        fileUploadText = TextStyle.Default,
        notificationInlineTitle = TextStyle.Default,
        notificationInlineText = TextStyle.Default,
        notificationToastTitle = TextStyle.Default,
        notificationToastText = TextStyle.Default,
        textFieldText = TextStyle.Default,
        textFieldPlaceholder = TextStyle.Default,
        textFieldLabel = TextStyle.Default,
        textFieldHelperText = TextStyle.Default,
        textFieldDropdownLabel = TextStyle.Default,
        paginationTileNumber = TextStyle.Default,
        tabTextDefault = TextStyle.Default,
        tabTextSelected = TextStyle.Default,
        dialogTitle = TextStyle.Default,
        dialogText = TextStyle.Default,
        card = SirioCardsTypography.Default
    )
}

val LocalSirioElevation = staticCompositionLocalOf {
    SirioElevation(
        default = Dp.Unspecified,
        pressed = Dp.Unspecified
    )
}

internal object ClearRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}

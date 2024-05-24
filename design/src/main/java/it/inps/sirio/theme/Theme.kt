//
// Theme.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import it.inps.sirio.ui.accordion.SirioAccordionColors
import it.inps.sirio.ui.badge.SirioBadgeColors
import it.inps.sirio.ui.card.SirioCardsColors
import it.inps.sirio.ui.card.SirioCardsTypography
import it.inps.sirio.ui.carousel.SirioCarouselColors
import it.inps.sirio.ui.checkbox.SirioCheckboxColors
import it.inps.sirio.ui.checkbox.SirioCheckboxTypography
import it.inps.sirio.ui.dialog.SirioDialogColors
import it.inps.sirio.ui.hero.SirioHeroColors
import it.inps.sirio.ui.hero.SirioHeroTypography
import it.inps.sirio.ui.pagination.SirioPaginationColors
import it.inps.sirio.ui.radiobutton.SirioRadioButtonColors
import it.inps.sirio.ui.radiobutton.SirioRadioButtonTypography
import it.inps.sirio.ui.searchbar.SirioSearchBarColors
import it.inps.sirio.ui.tabs.SirioTabsColors
import it.inps.sirio.ui.tag.SirioTagsColors
import it.inps.sirio.ui.textarea.SirioTextAreaColors
import it.inps.sirio.ui.textfield.SirioTextFieldColors
import it.inps.sirio.ui.toggle.SirioToggleColors
import it.inps.sirio.ui.toggle.SirioToggleTypography

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
        localSirioColors provides colors,
        LocalSirioTypography provides Typography,
        LocalRippleTheme provides ClearRippleTheme,
//        LocalSirioElevation provides Elevation,
        content = content
    )
}

object SirioTheme {
    val colors: SirioColors
        @Composable
        get() = localSirioColors.current

    val typography: SirioTypography
        @Composable
        get() = LocalSirioTypography.current

//    val elevation: SirioElevation
//        @Composable
//        get() = LocalSirioElevation.current
}

@Immutable
data class SirioTypography(
    val accordionText: TextStyle,
    val appNavigationSearch: TextStyle,
    val appNavigationSearchPlaceholder: TextStyle,
    val appNavigationTitle: TextStyle,
    val appNavigationTitleBig: TextStyle,
    val appNavigationUsername: TextStyle,
    val buttonText: TextStyle,
    val card: SirioCardsTypography,
    val checkbox: SirioCheckboxTypography,
    val chipText: TextStyle,
    val dialogText: TextStyle,
    val dialogTitle: TextStyle,
    val fabText: TextStyle,
    val fileUploadText: TextStyle,
    val fileUploadTitle: TextStyle,
    val hero: SirioHeroTypography,
    val notificationInlineText: TextStyle,
    val notificationInlineTitle: TextStyle,
    val notificationToastText: TextStyle,
    val notificationToastTitle: TextStyle,
    val paginationTileNumber: TextStyle,
    val progressBarLabel: TextStyle,
    val progressBarNumber: TextStyle,
    val radio: SirioRadioButtonTypography,
    val sliderNumber: TextStyle,
    val sliderText: TextStyle,
    val sliderTitle: TextStyle,
    val tabBarItemText: TextStyle,
    val tabTextDefault: TextStyle,
    val tabTextSelected: TextStyle,
    val tagText: TextStyle,
    val textAreaHelperText: TextStyle,
    val textAreaLabel: TextStyle,
    val textAreaPlaceholder: TextStyle,
    val textAreaText: TextStyle,
    val textFieldDropdownLabel: TextStyle,
    val textFieldHelperText: TextStyle,
    val textFieldLabel: TextStyle,
    val textFieldPlaceholder: TextStyle,
    val textFieldText: TextStyle,
    val toggle: SirioToggleTypography,
)

/**
 * Sirio custom Color Palette
 */
@Immutable
data class SirioColors(
    val brand: Color,
    val accordion: SirioAccordionColors,
    val appNavigationBackground: Color,
    val appNavigationIcon: Color,
    val appNavigationIconPressed: Color,
    val appNavigationSearchBackground1: Color,
    val appNavigationSearchBackground2: Color,
    val appNavigationSearchIcon: Color,
    val appNavigationSearchText: Color,
    val appNavigationText: Color,
    val appNavigationUsernameBackground: Color,
    val appNavigationUsernameText: Color,
    val badge: SirioBadgeColors,
    val buttons: ButtonColors,
    val card: SirioCardsColors,
    val carousel: SirioCarouselColors,
    val checkbox: SirioCheckboxColors,
    val chipButtonBorder: Color,
    val chipContent: Color,
    val chipDefaultBackground: Color,
    val chipDefaultButtonBackground: Color,
    val chipDisabledBackground: Color,
    val chipDisabledButtonBackground: Color,
    val chipDisabledContent: Color,
    val chipDisactiveBackground: Color,
    val chipFocusBackground: Color,
    val chipFocusBorder: Color,
    val chipFocusButtonBackground: Color,
    val chipHoverBackground: Color,
    val chipHoverButtonBackground: Color,
    val chipPressedBackground: Color,
    val chipPressedButtonBackground: Color,
    val chipWithCloseDefaultBackground: Color,
    val chipWithCloseDisabledBackground: Color,
    val chipWithCloseFocusBackground: Color,
    val chipWithCloseHoverBackground: Color,
    val chipWithClosePressedBackground: Color,
    val dialog: SirioDialogColors,
    val fabBorderFocus: Color,
    val fabContent: Color,
    val fabDefaultBackground: Color,
    val fabFocusBackground: Color,
    val fabHoverBackground: Color,
    val fabPressedBackground: Color,
    val fileUploadText: Color,
    val fileUploadTitle: Color,
    val hero: SirioHeroColors,
    val notificationColors: NotificationColors,
    val pagination: SirioPaginationColors,
    val progressBarBackground: Color,
    val progressBarGradient: List<Color>,
    val progressBarLabel: Color,
    val progressBarNumber: Color,
    val radio: SirioRadioButtonColors,
    val searchBar: SirioSearchBarColors,
    val sliderActive: Color,
    val sliderDefaultTextFieldBackground: Color,
    val sliderDefaultTextFieldBorder: Color,
    val sliderDefaultTextFieldText: Color,
    val sliderDisabled: Color,
    val sliderDisabledTextFieldBackground: Color,
    val sliderDisabledTextFieldBorder: Color,
    val sliderDisabledTextFieldText: Color,
    val sliderFocusActive: Color,
    val sliderFocusTextFieldBorder: Color,
    val sliderFocusTextFieldExtraBorder: Color,
    val sliderFocusTextFieldText: Color,
    val sliderHoverTextFieldBorder: Color,
    val sliderHoverTextFieldText: Color,
    val sliderInactive: Color,
    val sliderNumbers: Color,
    val sliderPressedActive: Color,
    val sliderPressedTextFieldBorder: Color,
    val sliderPressedTextFieldText: Color,
    val sliderPressedThumb: Color,
    val sliderText: Color,
    val sliderThumb: Color,
    val sliderTitle: Color,
    val tabBarActive: Color,
    val tabBarBackground: Color,
    val tabBarContent: Color,
    val tabs: SirioTabsColors,
    val tag: SirioTagsColors,
    val textArea: SirioTextAreaColors,
    val textField: SirioTextFieldColors,
    val toggle: SirioToggleColors,
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

    fun get(disabled: Boolean, focused: Boolean, pressed: Boolean, hovered: Boolean): Color =
        when {
            disabled -> this.disabled
            focused -> this.focused
            pressed -> this.pressed
            hovered -> this.hovered
            else -> this.default
        }

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

private val localSirioColors = staticCompositionLocalOf {
    SirioColors(
        brand = Color.Unspecified,
        accordion = SirioAccordionColors.Unspecified,
        appNavigationBackground = Color.Unspecified,
        appNavigationIcon = Color.Unspecified,
        appNavigationIconPressed = Color.Unspecified,
        appNavigationSearchBackground1 = Color.Unspecified,
        appNavigationSearchBackground2 = Color.Unspecified,
        appNavigationSearchIcon = Color.Unspecified,
        appNavigationSearchText = Color.Unspecified,
        appNavigationText = Color.Unspecified,
        appNavigationUsernameBackground = Color.Unspecified,
        appNavigationUsernameText = Color.Unspecified,
        badge = SirioBadgeColors.Unspecified,
        buttons = ButtonColors.Unspecified,
        card = SirioCardsColors.Unspecified,
        carousel = SirioCarouselColors.Unspecified,
        checkbox = SirioCheckboxColors.Unspecified,
        chipButtonBorder = Color.Unspecified,
        chipContent = Color.Unspecified,
        chipDefaultBackground = Color.Unspecified,
        chipDefaultButtonBackground = Color.Unspecified,
        chipDisabledBackground = Color.Unspecified,
        chipDisabledButtonBackground = Color.Unspecified,
        chipDisabledContent = Color.Unspecified,
        chipDisactiveBackground = Color.Unspecified,
        chipFocusBackground = Color.Unspecified,
        chipFocusBorder = Color.Unspecified,
        chipFocusButtonBackground = Color.Unspecified,
        chipHoverBackground = Color.Unspecified,
        chipHoverButtonBackground = Color.Unspecified,
        chipPressedBackground = Color.Unspecified,
        chipPressedButtonBackground = Color.Unspecified,
        chipWithCloseDefaultBackground = Color.Unspecified,
        chipWithCloseDisabledBackground = Color.Unspecified,
        chipWithCloseFocusBackground = Color.Unspecified,
        chipWithCloseHoverBackground = Color.Unspecified,
        chipWithClosePressedBackground = Color.Unspecified,
        dialog = SirioDialogColors.Unspecified,
        fabBorderFocus = Color.Unspecified,
        fabContent = Color.Unspecified,
        fabDefaultBackground = Color.Unspecified,
        fabFocusBackground = Color.Unspecified,
        fabHoverBackground = Color.Unspecified,
        fabPressedBackground = Color.Unspecified,
        fileUploadText = Color.Unspecified,
        fileUploadTitle = Color.Unspecified,
        hero = SirioHeroColors.Unspecified,
        notificationColors = NotificationColors.Unspecified,
        pagination = SirioPaginationColors.Unspecified,
        progressBarBackground = Color.Unspecified,
        progressBarGradient = listOf(Color.Unspecified),
        progressBarLabel = Color.Unspecified,
        progressBarNumber = Color.Unspecified,
        radio = SirioRadioButtonColors.Unspecified,
        searchBar = SirioSearchBarColors.Unspecified,
        sliderActive = Color.Unspecified,
        sliderDefaultTextFieldBackground = Color.Unspecified,
        sliderDefaultTextFieldBorder = Color.Unspecified,
        sliderDefaultTextFieldText = Color.Unspecified,
        sliderDisabled = Color.Unspecified,
        sliderDisabledTextFieldBackground = Color.Unspecified,
        sliderDisabledTextFieldBorder = Color.Unspecified,
        sliderDisabledTextFieldText = Color.Unspecified,
        sliderFocusActive = Color.Unspecified,
        sliderFocusTextFieldBorder = Color.Unspecified,
        sliderFocusTextFieldExtraBorder = Color.Unspecified,
        sliderFocusTextFieldText = Color.Unspecified,
        sliderHoverTextFieldBorder = Color.Unspecified,
        sliderHoverTextFieldText = Color.Unspecified,
        sliderInactive = Color.Unspecified,
        sliderNumbers = Color.Unspecified,
        sliderPressedActive = Color.Unspecified,
        sliderPressedTextFieldBorder = Color.Unspecified,
        sliderPressedTextFieldText = Color.Unspecified,
        sliderPressedThumb = Color.Unspecified,
        sliderText = Color.Unspecified,
        sliderThumb = Color.Unspecified,
        sliderTitle = Color.Unspecified,
        tabBarActive = Color.Unspecified,
        tabBarBackground = Color.Unspecified,
        tabBarContent = Color.Unspecified,
        tabs = SirioTabsColors.Unspecified,
        tag = SirioTagsColors.Unspecified,
        textArea = SirioTextAreaColors.Unspecified,
        textField = SirioTextFieldColors.Unspecified,
        toggle = SirioToggleColors.Unspecified,
        isDark = false,
    )
}

internal val LocalSirioTypography = staticCompositionLocalOf {
    SirioTypography(
        accordionText = TextStyle.Default,
        appNavigationSearch = TextStyle.Default,
        appNavigationSearchPlaceholder = TextStyle.Default,
        appNavigationTitle = TextStyle.Default,
        appNavigationTitleBig = TextStyle.Default,
        appNavigationUsername = TextStyle.Default,
        buttonText = TextStyle.Default,
        card = SirioCardsTypography.Default,
        checkbox = SirioCheckboxTypography.Default,
        chipText = TextStyle.Default,
        dialogText = TextStyle.Default,
        dialogTitle = TextStyle.Default,
        fabText = TextStyle.Default,
        fileUploadText = TextStyle.Default,
        fileUploadTitle = TextStyle.Default,
        hero = SirioHeroTypography.Default,
        notificationInlineText = TextStyle.Default,
        notificationInlineTitle = TextStyle.Default,
        notificationToastText = TextStyle.Default,
        notificationToastTitle = TextStyle.Default,
        paginationTileNumber = TextStyle.Default,
        progressBarLabel = TextStyle.Default,
        progressBarNumber = TextStyle.Default,
        radio = SirioRadioButtonTypography.Default,
        sliderNumber = TextStyle.Default,
        sliderText = TextStyle.Default,
        sliderTitle = TextStyle.Default,
        tabBarItemText = TextStyle.Default,
        tabTextDefault = TextStyle.Default,
        tabTextSelected = TextStyle.Default,
        tagText = TextStyle.Default,
        textAreaHelperText = TextStyle.Default,
        textAreaLabel = TextStyle.Default,
        textAreaPlaceholder = TextStyle.Default,
        textAreaText = TextStyle.Default,
        textFieldDropdownLabel = TextStyle.Default,
        textFieldHelperText = TextStyle.Default,
        textFieldLabel = TextStyle.Default,
        textFieldPlaceholder = TextStyle.Default,
        textFieldText = TextStyle.Default,
        toggle = SirioToggleTypography.Default,
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

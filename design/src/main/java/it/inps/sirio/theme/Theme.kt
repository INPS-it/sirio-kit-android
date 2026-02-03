//
// Theme.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import it.inps.sirio.foundation.LocalSirioFoundationTypography
import it.inps.sirio.foundation.SirioFoundationTypography
import it.inps.sirio.foundation.foundationTypography
import it.inps.sirio.ui.accordion.SirioAccordionColors
import it.inps.sirio.ui.appnavigation.SirioAppNavigationColors
import it.inps.sirio.ui.avviso.SirioAvvisoColors
import it.inps.sirio.ui.badge.SirioBadgeColors
import it.inps.sirio.ui.button.SirioButtonColors
import it.inps.sirio.ui.card.SirioCardsColors
import it.inps.sirio.ui.carousel.SirioCarouselColors
import it.inps.sirio.ui.checkbox.SirioCheckboxColors
import it.inps.sirio.ui.chip.SirioChipColors
import it.inps.sirio.ui.dialog.SirioDialogColors
import it.inps.sirio.ui.dropdown.SirioDropdownColors
import it.inps.sirio.ui.dropdownmenu.SirioDropdownMenuColors
import it.inps.sirio.ui.fileupload.SirioFileUploadColors
import it.inps.sirio.ui.filter.SirioFilterColors
import it.inps.sirio.ui.hero.SirioHeroColors
import it.inps.sirio.ui.listItem.SirioListItemColors
import it.inps.sirio.ui.loader.SirioLoaderColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaColors
import it.inps.sirio.ui.notification.SirioNotificaColors
import it.inps.sirio.ui.pagination.SirioPaginationColors
import it.inps.sirio.ui.popover.SirioPopoverColors
import it.inps.sirio.ui.progressbar.SirioProgressBarColors
import it.inps.sirio.ui.radiobutton.SirioRadioButtonColors
import it.inps.sirio.ui.searchbar.SirioSearchBarColors
import it.inps.sirio.ui.segmentedcontrols.SirioSegmentedControlsColors
import it.inps.sirio.ui.slider.SirioSliderColors
import it.inps.sirio.ui.stepprogressbar.SirioStepProgressBarColors
import it.inps.sirio.ui.tab.SirioTabColors
import it.inps.sirio.ui.tabbar.SirioTabBarColors
import it.inps.sirio.ui.table.SirioTableColors
import it.inps.sirio.ui.tag.SirioTagsColors
import it.inps.sirio.ui.textarea.SirioTextAreaColors
import it.inps.sirio.ui.textfield.SirioTextFieldColors
import it.inps.sirio.ui.toggle.SirioToggleColors
import kotlin.math.min

private const val MAX_FONT_SCALE = 1.4f

@Composable
fun SirioTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val configuration = LocalConfiguration.current
    val clampedFontScale = min(configuration.fontScale, MAX_FONT_SCALE)

    CompositionLocalProvider(
        localSirioColors provides colors,
        LocalSirioFoundationTypography provides foundationTypography,
//        LocalSirioElevation provides Elevation,
        LocalDensity provides Density(
            density = LocalDensity.current.density,
            fontScale = clampedFontScale
        ),
        content = content
    )
}

object SirioTheme {
    val colors: SirioColors
        @Composable
        get() = localSirioColors.current

    val foundationTypography: SirioFoundationTypography
        @Composable
        get() = LocalSirioFoundationTypography.current

//    val elevation: SirioElevation
//        @Composable
//        get() = LocalSirioElevation.current
}

/**
 * Sirio custom Color Palette
 */
@Immutable
data class SirioColors(
    val brand: Color,
    val accordion: SirioAccordionColors,
    val appNavigation: SirioAppNavigationColors,
    val avviso: SirioAvvisoColors,
    val badge: SirioBadgeColors,
    val button: SirioButtonColors,
    val card: SirioCardsColors,
    val carousel: SirioCarouselColors,
    val checkbox: SirioCheckboxColors,
    val chip: SirioChipColors,
    val dialog: SirioDialogColors,
    val dropdown: SirioDropdownColors,
    val dropdownMenu: SirioDropdownMenuColors,
    val fab: SirioBaseStateColors,
    val fabBorderFocus: Color,
    val fabContent: Color,
    val fabDefaultBackground: Color,
    val fabFocusBackground: Color,
    val fabHoverBackground: Color,
    val fabPressedBackground: Color,
    val fileUpload: SirioFileUploadColors,
    val filter: SirioFilterColors,
    val hero: SirioHeroColors,
    val listItem: SirioListItemColors,
    val loader: SirioLoaderColors,
    val menuSpalla: SirioMenuSpallaColors,
    val notifica: SirioNotificaColors,
    val pagination: SirioPaginationColors,
    val popover: SirioPopoverColors,
    val progressBar: SirioProgressBarColors,
    val radio: SirioRadioButtonColors,
    val searchBar: SirioSearchBarColors,
    val segmentedControls: SirioSegmentedControlsColors,
    val slider: SirioSliderColors,
    val stepProgressBar: SirioStepProgressBarColors,
    val tabBar: SirioTabBarColors,
    val table: SirioTableColors,
    val tab: SirioTabColors,
    val tag: SirioTagsColors,
    val textArea: SirioTextAreaColors,
    val textField: SirioTextFieldColors,
    val titleBar: SirioBaseColors,
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
    @Deprecated("Focused state is no longer supported")
    val focused: Color,
    @Deprecated("Hovered state is no longer supported")
    val hovered: Color,
    val pressed: Color,
    val valued: Color,
    val alert: Color,
    val warning: Color,
    val success: Color,
    val info: Color,
) {
    constructor(
        default: Color = Color.Unspecified,
        disabled: Color = Color.Unspecified,
        pressed: Color = Color.Unspecified,
        valued: Color = Color.Unspecified,
        alert: Color = Color.Unspecified,
        warning: Color = Color.Unspecified,
        success: Color = Color.Unspecified,
    ) : this(
        default = default,
        disabled = disabled,
        focused = Color.Unspecified,
        hovered = Color.Unspecified,
        pressed = pressed,
        valued = valued,
        alert = alert,
        warning = warning,
        success = success,
        info = Color.Unspecified
    )

    constructor(
        default: Color = Color.Unspecified,
        disabled: Color = Color.Unspecified,
        focused: Color = Color.Unspecified,
        hovered: Color = Color.Unspecified,
        pressed: Color = Color.Unspecified,
        valued: Color = Color.Unspecified,
    ) : this(
        default = default,
        disabled = disabled,
        focused = focused,
        hovered = hovered,
        pressed = pressed,
        valued = valued,
        alert = Color.Unspecified,
        warning = Color.Unspecified,
        success = Color.Unspecified,
        info = Color.Unspecified,
    )

    constructor(
        default: Color = Color.Unspecified,
        pressed: Color = Color.Unspecified,
        disabled: Color = Color.Unspecified,
    ) : this(
        default = default,
        disabled = disabled,
        focused = Color.Unspecified,
        hovered = Color.Unspecified,
        pressed = pressed,
        valued = Color.Unspecified,
        alert = Color.Unspecified,
        warning = Color.Unspecified,
        success = Color.Unspecified,
        info = Color.Unspecified,
    )

    fun get(
        disabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
        hovered: Boolean,
        valued: Boolean = false,
    ): Color =
        when {
            disabled -> this.disabled
            focused -> this.focused
            pressed -> this.pressed
            hovered -> this.hovered
            valued -> this.valued
            else -> this.default
        }

    fun get(
        pressed: Boolean = false,
        disabled: Boolean = false,
        valued: Boolean = false,
    ): Color =
        when {
            disabled -> this.disabled
            pressed -> this.pressed
            valued -> this.valued
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
                valued = color,
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
        appNavigation = SirioAppNavigationColors.Unspecified,
        avviso = SirioAvvisoColors.Unspecified,
        badge = SirioBadgeColors.Unspecified,
        button = SirioButtonColors.Unspecified,
        card = SirioCardsColors.Unspecified,
        carousel = SirioCarouselColors.Unspecified,
        checkbox = SirioCheckboxColors.Unspecified,
        chip = SirioChipColors.Unspecified,
        dialog = SirioDialogColors.Unspecified,
        dropdown = SirioDropdownColors.Unspecified,
        dropdownMenu = SirioDropdownMenuColors.Unspecified,
        fab = SirioBaseStateColors.Unspecified,
        fabBorderFocus = Color.Unspecified,
        fabContent = Color.Unspecified,
        fabDefaultBackground = Color.Unspecified,
        fabFocusBackground = Color.Unspecified,
        fabHoverBackground = Color.Unspecified,
        fabPressedBackground = Color.Unspecified,
        fileUpload = SirioFileUploadColors.Unspecified,
        filter = SirioFilterColors.Unspecified,
        hero = SirioHeroColors.Unspecified,
        listItem = SirioListItemColors.Unspecified,
        loader = SirioLoaderColors.Unspecified,
        menuSpalla = SirioMenuSpallaColors.Unspecified,
        notifica = SirioNotificaColors.Unspecified,
        pagination = SirioPaginationColors.Unspecified,
        popover = SirioPopoverColors.Unspecified,
        progressBar = SirioProgressBarColors.Unspecified,
        radio = SirioRadioButtonColors.Unspecified,
        searchBar = SirioSearchBarColors.Unspecified,
        segmentedControls = SirioSegmentedControlsColors.Unspecified,
        slider = SirioSliderColors.Unspecified,
        stepProgressBar = SirioStepProgressBarColors.Unspecified,
        tabBar = SirioTabBarColors.Unspecified,
        table = SirioTableColors.Unspecified,
        tab = SirioTabColors.Unspecified,
        tag = SirioTagsColors.Unspecified,
        textArea = SirioTextAreaColors.Unspecified,
        textField = SirioTextFieldColors.Unspecified,
        titleBar = SirioBaseColors.Unspecified,
        toggle = SirioToggleColors.Unspecified,
        isDark = false,
    )
}

val LocalSirioElevation = staticCompositionLocalOf {
    SirioElevation(
        default = Dp.Unspecified,
        pressed = Dp.Unspecified
    )
}

enum class SirioThemeMode {
    Light,
    Dark
}

@Composable
internal fun SirioTheme(themeMode: SirioThemeMode?, content: @Composable () -> Unit) {
    val darkTheme = when (themeMode) {
        SirioThemeMode.Dark -> true
        SirioThemeMode.Light -> false
        null -> isSystemInDarkTheme()
    }
    SirioTheme(darkTheme = darkTheme, content = content)
}

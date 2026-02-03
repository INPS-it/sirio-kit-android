//
// Color.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.ui.accordion.accordionDarkColors
import it.inps.sirio.ui.accordion.accordionLightColors
import it.inps.sirio.ui.appnavigation.appNavigationDarkColors
import it.inps.sirio.ui.appnavigation.appNavigationLightColors
import it.inps.sirio.ui.avviso.avvisoDarkColors
import it.inps.sirio.ui.avviso.avvisoLightColors
import it.inps.sirio.ui.badge.badgeDarkColors
import it.inps.sirio.ui.badge.badgeLightColors
import it.inps.sirio.ui.button.buttonDarkColors
import it.inps.sirio.ui.button.buttonLightColors
import it.inps.sirio.ui.card.cardDarkColors
import it.inps.sirio.ui.card.cardLightColors
import it.inps.sirio.ui.carousel.carouselDarkColors
import it.inps.sirio.ui.carousel.carouselLightColors
import it.inps.sirio.ui.checkbox.checkboxDarkColors
import it.inps.sirio.ui.checkbox.checkboxLightColors
import it.inps.sirio.ui.chip.chipDarkColors
import it.inps.sirio.ui.chip.chipLightColors
import it.inps.sirio.ui.dialog.dialogDarkColors
import it.inps.sirio.ui.dialog.dialogLightColors
import it.inps.sirio.ui.dropdown.dropdownDarkColors
import it.inps.sirio.ui.dropdown.dropdownLightColors
import it.inps.sirio.ui.dropdownmenu.dropdownMenuDarkColors
import it.inps.sirio.ui.dropdownmenu.dropdownMenuLightColors
import it.inps.sirio.ui.fab.fabDarkColors
import it.inps.sirio.ui.fab.fabLightColors
import it.inps.sirio.ui.fileupload.fileUploadDarkColors
import it.inps.sirio.ui.fileupload.fileUploadLightColors
import it.inps.sirio.ui.filter.filterDarkColors
import it.inps.sirio.ui.filter.filterLightColors
import it.inps.sirio.ui.hero.heroDarkColors
import it.inps.sirio.ui.hero.heroLightColors
import it.inps.sirio.ui.listItem.listItemDarkColors
import it.inps.sirio.ui.listItem.listItemLightColors
import it.inps.sirio.ui.loader.loaderDarkColors
import it.inps.sirio.ui.loader.loaderLightColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemInfoColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemTitleSectionColors
import it.inps.sirio.ui.notification.notificaDarkColors
import it.inps.sirio.ui.notification.notificaLightColors
import it.inps.sirio.ui.pagination.paginationDarkColors
import it.inps.sirio.ui.pagination.paginationLightColors
import it.inps.sirio.ui.popover.popoverDarkColors
import it.inps.sirio.ui.popover.popoverLightColors
import it.inps.sirio.ui.progressbar.progressBarDarkColors
import it.inps.sirio.ui.progressbar.progressBarLightColors
import it.inps.sirio.ui.radiobutton.radioButtonDarkColors
import it.inps.sirio.ui.radiobutton.radioButtonLightColors
import it.inps.sirio.ui.searchbar.searchBarDarkColors
import it.inps.sirio.ui.searchbar.searchBarLightColors
import it.inps.sirio.ui.segmentedcontrols.segmentedControlsDarkColors
import it.inps.sirio.ui.segmentedcontrols.segmentedControlsLightColors
import it.inps.sirio.ui.slider.sliderDarkColors
import it.inps.sirio.ui.slider.sliderLightColors
import it.inps.sirio.ui.stepprogressbar.stepProgressBarDarkColors
import it.inps.sirio.ui.stepprogressbar.stepProgressBarLightColors
import it.inps.sirio.ui.tab.tabDarkColors
import it.inps.sirio.ui.tab.tabLightColors
import it.inps.sirio.ui.tabbar.tabBarDarkColors
import it.inps.sirio.ui.tabbar.tabBarLightColors
import it.inps.sirio.ui.table.tableDarkColors
import it.inps.sirio.ui.table.tableLightColors
import it.inps.sirio.ui.tag.tagDarkColors
import it.inps.sirio.ui.tag.tagLightColors
import it.inps.sirio.ui.textarea.textAreaDarkColors
import it.inps.sirio.ui.textarea.textAreaLightColors
import it.inps.sirio.ui.textfield.textFieldDarkColors
import it.inps.sirio.ui.textfield.textFieldLightColors
import it.inps.sirio.ui.toggle.toggleDarkColors
import it.inps.sirio.ui.toggle.toggleLightColors

private val menuSpallaLightColors = SirioMenuSpallaColors(
    background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    drawerItem = SirioMenuSpallaDrawerItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        content = SirioColorState.all(StyleDictionaryColor.colorAliasTextColorPrimaryLight0)
    ),
    drawerItemInfo = SirioMenuSpallaDrawerItemInfoColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryDark110,
    ),
    itemTitleSection = SirioMenuSpallaItemTitleSectionColors(
        background = Color.Transparent,
        border = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        content = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    ),
    itemPrimary = SirioMenuSpallaItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        indicator = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        divider = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        tag = tagLightColors.green,
        tagDisabled = tagLightColors.gray,
    ),
    itemSecondary = SirioMenuSpallaItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        indicator = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        divider = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
    ),
    itemTertiary = SirioMenuSpallaItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        indicator = SirioColorState(
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        divider = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
    ),
)

internal val LightColorPalette = SirioColors(
    brand = StyleDictionaryColor.colorGlobalPrimary100,
    accordion = accordionLightColors,
    appNavigation = appNavigationLightColors,
    avviso = avvisoLightColors,
    badge = badgeLightColors,
    button = buttonLightColors,
    card = cardLightColors,
    carousel = carouselLightColors,
    checkbox = checkboxLightColors,
    chip = chipLightColors,
    dialog = dialogLightColors,
    dropdown = dropdownLightColors,
    dropdownMenu = dropdownMenuLightColors,
    fab = fabLightColors,
    fabBorderFocus = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    fabContent = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    fabDefaultBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    fabFocusBackground = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
    fabHoverBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    fabPressedBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
    fileUpload = fileUploadDarkColors,
    filter = filterLightColors,
    hero = heroLightColors,
    listItem = listItemLightColors,
    loader = loaderLightColors,
    menuSpalla = menuSpallaLightColors,
    notifica = notificaLightColors,
    pagination = paginationLightColors,
    popover = popoverLightColors,
    progressBar = progressBarLightColors,
    radio = radioButtonLightColors,
    searchBar = searchBarLightColors,
    segmentedControls = segmentedControlsLightColors,
    slider = sliderLightColors,
    stepProgressBar = stepProgressBarLightColors,
    tabBar = tabBarLightColors,
    table = tableLightColors,
    tab = tabLightColors,
    tag = tagLightColors,
    textArea = textAreaLightColors,
    textField = textFieldLightColors,
    titleBar = SirioBaseColors(
        container = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        content = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    ),
    toggle = toggleLightColors,
    isDark = false,
)

private val menuSpallaDarkColors = SirioMenuSpallaColors(
    background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    drawerItem = SirioMenuSpallaDrawerItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        content = SirioColorState.all(StyleDictionaryColor.colorAliasTextColorPrimaryLight0)
    ),
    drawerItemInfo = SirioMenuSpallaDrawerItemInfoColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryDark110,
    ),
    itemTitleSection = SirioMenuSpallaItemTitleSectionColors(
        background = Color.Transparent,
        border = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        content = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    ),
    itemPrimary = SirioMenuSpallaItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        indicator = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        divider = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        tag = tagLightColors.green,
        tagDisabled = tagLightColors.gray,
    ),
    itemSecondary = SirioMenuSpallaItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        indicator = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        divider = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
    ),
    itemTertiary = SirioMenuSpallaItemColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        ),
        border = SirioColorState(
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        ),
        indicator = SirioColorState(
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        divider = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
    ),
)

internal val DarkColorPalette = SirioColors(
    brand = StyleDictionaryColor.colorGlobalPrimary000,
    accordion = accordionDarkColors,
    appNavigation = appNavigationDarkColors,
    avviso = avvisoDarkColors,
    badge = badgeDarkColors,
    button = buttonDarkColors,
    card = cardDarkColors,
    carousel = carouselDarkColors,
    checkbox = checkboxDarkColors,
    chip = chipDarkColors,
    dialog = dialogDarkColors,
    dropdown = dropdownDarkColors,
    dropdownMenu = dropdownMenuDarkColors,
    fab = fabDarkColors,
    fabBorderFocus = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    fabContent = StyleDictionaryColor.colorAliasTextColorPrimaryDark110,
    fabDefaultBackground = StyleDictionaryColor.colorAliasAppInteractivePrimary000Default,
    fabFocusBackground = StyleDictionaryColor.colorAliasAppInteractivePrimary000Default,
    fabHoverBackground = StyleDictionaryColor.colorAliasInteractiveAccentHover,
    fabPressedBackground = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
    fileUpload = fileUploadLightColors,
    filter = filterDarkColors,
    hero = heroDarkColors,
    listItem = listItemDarkColors,
    loader = loaderDarkColors,
    menuSpalla = menuSpallaDarkColors,
    notifica = notificaDarkColors,
    pagination = paginationDarkColors,
    popover = popoverDarkColors,
    progressBar = progressBarDarkColors,
    radio = radioButtonDarkColors,
    segmentedControls = segmentedControlsDarkColors,
    searchBar = searchBarDarkColors,
    slider = sliderDarkColors,
    stepProgressBar = stepProgressBarDarkColors,
    tabBar = tabBarDarkColors,
    table = tableDarkColors,
    tab = tabDarkColors,
    tag = tagDarkColors,
    textArea = textAreaDarkColors,
    textField = textFieldDarkColors,
    titleBar = SirioBaseColors(
        container = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        content = StyleDictionaryColor.colorAliasInteractivePrimary000Default,
    ),
    toggle = toggleDarkColors,
    isDark = true,
)

@Keep
data class SirioBaseColors(
    val container: Color,
    val content: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioBaseColors(
            container = Color.Unspecified,
            content = Color.Unspecified,
        )
    }
}

@Keep
data class SirioBaseStateColors(
    val container: SirioColorState,
    val content: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioBaseStateColors(
            container = SirioColorState.Unspecified,
            content = SirioColorState.Unspecified,
        )
    }
}

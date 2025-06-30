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
import it.inps.sirio.ui.button.ButtonLegacyColors
import it.inps.sirio.ui.button.SirioButtonLegacyColors
import it.inps.sirio.ui.button.buttonDarkColors
import it.inps.sirio.ui.button.buttonLightColors
import it.inps.sirio.ui.card.cardDarkColors
import it.inps.sirio.ui.card.cardLightColors
import it.inps.sirio.ui.carousel.SirioCarouselColors
import it.inps.sirio.ui.carousel.SirioCarouselIndicatorColors
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
import it.inps.sirio.ui.filter.SirioFilterColors
import it.inps.sirio.ui.hero.SirioHeroColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemInfoColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemTitleSectionColors
import it.inps.sirio.ui.notification.NotificationColors
import it.inps.sirio.ui.notification.notificaDarkColors
import it.inps.sirio.ui.notification.notificaLightColors
import it.inps.sirio.ui.pagination.SirioPaginationColors
import it.inps.sirio.ui.progressbar.progressBarDarkColors
import it.inps.sirio.ui.progressbar.progressBarLightColors
import it.inps.sirio.ui.radiobutton.radioButtonDarkColors
import it.inps.sirio.ui.radiobutton.radioButtonLightColors
import it.inps.sirio.ui.searchbar.SirioSearchBarColors
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
import it.inps.sirio.ui.table.SirioTableColors
import it.inps.sirio.ui.table.SirioTableHeaderColors
import it.inps.sirio.ui.table.cell.SirioTableCellColors
import it.inps.sirio.ui.table.cell.SirioTableComponentColors
import it.inps.sirio.ui.table.drawer.SirioTableDrawerColors
import it.inps.sirio.ui.table.vertical.SirioTableVerticalColors
import it.inps.sirio.ui.tag.SirioTagsColors
import it.inps.sirio.ui.tag.tagDarkColors
import it.inps.sirio.ui.textarea.textAreaDarkColors
import it.inps.sirio.ui.textarea.textAreaLightColors
import it.inps.sirio.ui.textfield.textFieldDarkColors
import it.inps.sirio.ui.textfield.textFieldLightColors
import it.inps.sirio.ui.toggle.toggleDarkColors
import it.inps.sirio.ui.toggle.toggleLightColors

private val buttonLegacyLightColors = ButtonLegacyColors(
    focusExtraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    primary = SirioButtonLegacyColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        border = null,
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    danger = SirioButtonLegacyColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveAlertDefault,
            hovered = StyleDictionaryColor.colorAliasInteractiveAlertHover,
            focused = StyleDictionaryColor.colorAliasInteractiveAlertFocus,
            pressed = StyleDictionaryColor.colorAliasInteractiveAlertPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        border = null,
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    secondary = SirioButtonLegacyColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        border = null,
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    tertiary = SirioButtonLegacyColors(
        background = SirioColorState.all(Color.Transparent),
        border = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    ghost = SirioButtonLegacyColors(
        background = SirioColorState(
            default = Color.Transparent,
            focused = Color.Transparent,
            hovered = Color.Transparent,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
            disabled = Color.Transparent,
        ),
        border = SirioColorState.all(Color.Transparent),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
)

private val sirioFilterLightColors = SirioFilterColors(
    background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    close = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    header = StyleDictionaryColor.colorGlobalDarkPrimary115,
    info = StyleDictionaryColor.colorGlobalSemanticInfo100,
    selectedBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    title = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
)

private val tagLightColors = SirioTagsColors(
    gray = SirioBaseColors(
        container = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    blue = SirioBaseColors(
        container = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    red = SirioBaseColors(
        container = StyleDictionaryColor.colorAliasInteractiveAlertDefault,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    orange = SirioBaseColors(
        container = StyleDictionaryColor.colorGlobalSemanticWarning80,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    green = SirioBaseColors(
        container = StyleDictionaryColor.colorGlobalSemanticSuccess100,
        content = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    white = SirioBaseColors(
        container = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        content = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    ),
)

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
    buttonLegacy = buttonLegacyLightColors,
    card = cardLightColors,
    carousel = SirioCarouselColors(
        backgroundLight = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundMedium = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        indicator = SirioCarouselIndicatorColors(
            dotUnselected = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            dotSelected = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        )
    ),
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
    filter = sirioFilterLightColors,
    hero = SirioHeroColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        title = StyleDictionaryColor.colorGlobalDarkPrimary115,
        subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        borderBottom = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
    ),
    menuSpalla = menuSpallaLightColors,
    notifica = notificaLightColors,
    notification = NotificationColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark120,
        title = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        link = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
        icon = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        close = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
        info = StyleDictionaryColor.colorGlobalSemanticInfo100,
        warning = StyleDictionaryColor.colorGlobalSemanticWarning100,
        success = StyleDictionaryColor.colorGlobalSemanticSuccess100,
    ),
    pagination = SirioPaginationColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        tile = SirioColorState(
            default = Color.Transparent,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = Color.Transparent,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        tileBorder = SirioColorState(
            default = Color.Transparent,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        number = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        )
    ),
    progressBar = progressBarLightColors,
    radio = radioButtonLightColors,
    searchBar = SirioSearchBarColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    segmentedControls = segmentedControlsLightColors,
    slider = sliderLightColors,
    stepProgressBar = stepProgressBarLightColors,
    tabBar = tabBarLightColors,
    table = SirioTableColors(
        cell = SirioTableCellColors(
            avatarSubtitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            avatarTitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark130,
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            icon = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            link = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            number = StyleDictionaryColor.colorAliasTextColorSecondaryDark130,
            title = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        component = SirioTableComponentColors(
            border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
            scrollIndicator = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
        ),
        drawer = SirioTableDrawerColors(
            actionsText = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
            title = StyleDictionaryColor.colorAliasTextColorPrimary100,
            iconsBackground = StyleDictionaryColor.colorGlobalPrimary100,
            itemTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            itemText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            itemNumber = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            itemLink = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        ),
        header = SirioTableHeaderColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            icon = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            title = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        vertical = SirioTableVerticalColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
            itemTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            itemText = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            itemNumber = StyleDictionaryColor.colorAliasTextColorSecondaryDark130,
            itemLink = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        ),
    ),
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

private val buttonLegacyDarkColors = ButtonLegacyColors(
    focusExtraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    primary = SirioButtonLegacyColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        border = null,
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    danger = SirioButtonLegacyColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveAlertDefault,
            hovered = StyleDictionaryColor.colorAliasInteractiveAlertHover,
            focused = StyleDictionaryColor.colorAliasInteractiveAlertFocus,
            pressed = StyleDictionaryColor.colorAliasInteractiveAlertPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        border = null,
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    secondary = SirioButtonLegacyColors(
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        border = null,
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    tertiary = SirioButtonLegacyColors(
        background = SirioColorState.all(Color.Transparent),
        border = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
            hovered = StyleDictionaryColor.colorAliasInteractiveAccentHover,
            focused = StyleDictionaryColor.colorAliasInteractiveAccentFocus,
            pressed = StyleDictionaryColor.colorAliasInteractiveAccentPressed,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
            hovered = StyleDictionaryColor.colorAliasInteractiveAccentHover,
            focused = StyleDictionaryColor.colorAliasInteractiveAccentFocus,
            pressed = StyleDictionaryColor.colorAliasInteractiveAccentPressed,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
    ghost = SirioButtonLegacyColors(
        background = SirioColorState(
            default = Color.Transparent,
            focused = Color.Transparent,
            hovered = Color.Transparent,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
            disabled = Color.Transparent,
        ),
        border = SirioColorState.all(Color.Transparent),
        content = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
    ),
)

private val sirioFilterDarkColors = SirioFilterColors(
    background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    close = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    header = StyleDictionaryColor.colorGlobalDarkPrimary115,
    info = StyleDictionaryColor.colorGlobalSemanticInfo100,
    selectedBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    title = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
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
    buttonLegacy = buttonLegacyDarkColors,
    card = cardDarkColors,
    carousel = SirioCarouselColors(
        backgroundLight = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundMedium = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        indicator = SirioCarouselIndicatorColors(
            dotUnselected = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            dotSelected = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        )
    ),
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
    filter = sirioFilterDarkColors,
    hero = SirioHeroColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        title = StyleDictionaryColor.colorGlobalDarkPrimary115,
        subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        borderBottom = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
    ),
    menuSpalla = menuSpallaDarkColors,
    notifica = notificaDarkColors,
    notification = NotificationColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark120,
        title = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        link = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
        icon = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        close = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
        info = StyleDictionaryColor.colorGlobalSemanticInfo100,
        warning = StyleDictionaryColor.colorGlobalSemanticWarning100,
        success = StyleDictionaryColor.colorGlobalSemanticSuccess100,
    ),
    pagination = SirioPaginationColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        tile = SirioColorState(
            default = Color.Transparent,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = Color.Transparent,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        tileBorder = SirioColorState(
            default = Color.Transparent,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        ),
        number = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        )
    ),
    progressBar = progressBarDarkColors,
    radio = radioButtonDarkColors,
    segmentedControls = segmentedControlsDarkColors,
    searchBar = SirioSearchBarColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    slider = sliderDarkColors,
    stepProgressBar = stepProgressBarDarkColors,
    tabBar = tabBarDarkColors,
    table = SirioTableColors(
        cell = SirioTableCellColors(
            avatarSubtitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            avatarTitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark130,
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            icon = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            link = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            number = StyleDictionaryColor.colorAliasTextColorSecondaryDark130,
            title = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        component = SirioTableComponentColors(
            border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
            scrollIndicator = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
        ),
        drawer = SirioTableDrawerColors(
            actionsText = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
            iconsBackground = StyleDictionaryColor.colorGlobalPrimary100,
            title = StyleDictionaryColor.colorAliasTextColorPrimary100,
            itemTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            itemText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            itemNumber = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            itemLink = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        ),
        header = SirioTableHeaderColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark115,
            icon = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
            title = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        ),
        vertical = SirioTableVerticalColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
            itemTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            itemText = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            itemNumber = StyleDictionaryColor.colorAliasTextColorSecondaryDark130,
            itemLink = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        )
    ),
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

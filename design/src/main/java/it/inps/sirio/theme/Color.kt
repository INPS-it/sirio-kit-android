//
// Color.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.ui.accordion.SirioAccordionColors
import it.inps.sirio.ui.avviso.SirioAvvisoColors
import it.inps.sirio.ui.badge.SirioBadgeColors
import it.inps.sirio.ui.button.SirioButtonColors
import it.inps.sirio.ui.card.SirioCardColors
import it.inps.sirio.ui.card.SirioCardsColors
import it.inps.sirio.ui.carousel.SirioCarouselColors
import it.inps.sirio.ui.carousel.SirioCarouselIndicatorColors
import it.inps.sirio.ui.checkbox.SirioCheckboxColors
import it.inps.sirio.ui.dialog.SirioDialogColors
import it.inps.sirio.ui.dropdown.SirioDropdownColors
import it.inps.sirio.ui.dropdown.SirioDropdownOptionColors
import it.inps.sirio.ui.filter.SirioFilterColors
import it.inps.sirio.ui.hero.SirioHeroColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemInfoColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemColors
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemTitleSectionColors
import it.inps.sirio.ui.notification.NotificationColors
import it.inps.sirio.ui.pagination.SirioPaginationColors
import it.inps.sirio.ui.radiobutton.SirioRadioButtonColors
import it.inps.sirio.ui.searchbar.SirioSearchBarColors
import it.inps.sirio.ui.stepprogressbar.SirioStepControlsColors
import it.inps.sirio.ui.stepprogressbar.SirioStepProgressBarColors
import it.inps.sirio.ui.stepprogressbar.SirioStepSelectionColors
import it.inps.sirio.ui.table.SirioTableColors
import it.inps.sirio.ui.table.SirioTableHeaderColors
import it.inps.sirio.ui.table.cell.SirioTableCellColors
import it.inps.sirio.ui.table.cell.SirioTableComponentColors
import it.inps.sirio.ui.table.drawer.SirioTableDrawerColors
import it.inps.sirio.ui.table.vertical.SirioTableVerticalColors
import it.inps.sirio.ui.tabs.SirioTabsColors
import it.inps.sirio.ui.tag.SirioTagColors
import it.inps.sirio.ui.tag.SirioTagsColors
import it.inps.sirio.ui.textarea.SirioTextAreaColors
import it.inps.sirio.ui.textfield.SirioTextFieldColors
import it.inps.sirio.ui.titlebar.SirioTitleBarColors
import it.inps.sirio.ui.toggle.SirioToggleColors

private val buttonsLightColors = ButtonColors(
    focusExtraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    primary = SirioButtonColors(
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
    danger = SirioButtonColors(
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
    secondary = SirioButtonColors(
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
    tertiary = SirioButtonColors(
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
    ghost = SirioButtonColors(
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
    gray = SirioTagColors(
        background = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    blue = SirioTagColors(
        background = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    red = SirioTagColors(
        background = StyleDictionaryColor.colorAliasInteractiveAlertDefault,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    orange = SirioTagColors(
        background = StyleDictionaryColor.colorGlobalSemanticWarning80,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    green = SirioTagColors(
        background = StyleDictionaryColor.colorGlobalSemanticSuccess100,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    white = SirioTagColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        text = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
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
    accordion = SirioAccordionColors(
        activedBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        activedBorder = Color.Transparent,
        contentBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        contentBorder = StyleDictionaryColor.colorGlobalLightPrimary50,
        background = SirioColorState(
            default = StyleDictionaryColor.colorGlobalPrimary000,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            pressed = StyleDictionaryColor.colorGlobalPrimary000,
        ),
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryDark110,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = StyleDictionaryColor.colorGlobalLightPrimary50,
            disabled = Color.Transparent,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = StyleDictionaryColor.colorGlobalLightPrimary50,
            pressed = Color.Transparent,
        ),
    ),
    appNavigationBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    appNavigationIcon = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
    appNavigationIconPressed = StyleDictionaryColor.colorAliasOverlay15Secondary100,
    appNavigationSearchBackground1 = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    appNavigationSearchBackground2 = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    appNavigationSearchIcon = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
    appNavigationSearchText = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
    appNavigationText = StyleDictionaryColor.colorAliasTextColorPrimaryDark110,
    appNavigationUsernameBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
    appNavigationUsernameText = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    avviso = SirioAvvisoColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        borderBottom = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        icon = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorGlobalDarkPrimary130,
        title = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
    ),
    badge = SirioBadgeColors(
        background = StyleDictionaryColor.colorGlobalSemanticAlert100,
        border = StyleDictionaryColor.colorGlobalPrimary000
    ),
    buttons = buttonsLightColors,
    card = SirioCardsColors(
        editorial = SirioCardColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            category = tagLightColors.gray,
            icon = StyleDictionaryColor.colorGlobalSecondary100,
            date = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            title = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            signature = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            button = buttonsLightColors.tertiary,
            iconButton = buttonsLightColors.ghost,
        ),
        process = SirioCardColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            category = tagLightColors.gray,
            icon = StyleDictionaryColor.colorGlobalSecondary100,
            date = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            title = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            signature = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            button = buttonsLightColors.tertiary,
            iconButton = buttonsLightColors.ghost,
        ),
    ),
    carousel = SirioCarouselColors(
        backgroundLight = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundMedium = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        indicator = SirioCarouselIndicatorColors(
            dotUnselected = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            dotSelected = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        )
    ),
    checkbox = SirioCheckboxColors(
        background = SirioColorState.all(
            StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            StyleDictionaryColor.colorAliasBackgroundColorDisabled
        ),
        borderFocusExtra = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
        ),
        check = SirioColorState.all(StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
        ),
    ),
    chipButtonBorder = StyleDictionaryColor.colorGlobalMidPrimary70,
    chipContent = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    chipDefaultBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    chipDefaultButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    chipDisabledBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    chipDisabledButtonBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    chipDisabledContent = StyleDictionaryColor.colorAliasTextColorDisabled,
    chipDisactiveBackground = Color.Transparent,
    chipFocusBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    chipFocusBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    chipFocusButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    chipHoverBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    chipHoverButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    chipPressedBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
    chipPressedButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
    chipWithCloseDefaultBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    chipWithCloseDisabledBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    chipWithCloseFocusBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
    chipWithCloseHoverBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
    chipWithClosePressedBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryPressed,
    dialog = SirioDialogColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        semanticDefault = StyleDictionaryColor.colorGlobalSemanticInfo100,
        semanticAlert = StyleDictionaryColor.colorGlobalSemanticAlert100,
        semanticWarning = StyleDictionaryColor.colorGlobalSemanticWarning100,
        title = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
    ),
    dropdown = SirioDropdownColors(
        option = SirioDropdownOptionColors(
            background = SirioColorState(
                default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
                disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
                focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
                hovered = StyleDictionaryColor.colorSpecificOptionBackgroundColorHover,
                pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
                valued = StyleDictionaryColor.colorSpecificOptionBackgroundColorValued,
            ),
            border = SirioColorState(
                default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
                disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
                focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
                hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
                pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
                valued = StyleDictionaryColor.colorSpecificOptionBackgroundColorValued,
            ),
            content = SirioColorState(
                default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
                disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
                focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
                hovered = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
                pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
                valued = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            )
        )
    ),
    fabBorderFocus = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    fabContent = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    fabDefaultBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    fabFocusBackground = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
    fabHoverBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    fabPressedBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
    fileUploadText = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    fileUploadTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
    filter = sirioFilterLightColors,
    hero = SirioHeroColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        title = StyleDictionaryColor.colorGlobalDarkPrimary115,
        subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        borderBottom = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
    ),
    menuSpalla = menuSpallaLightColors,
    notificationColors = NotificationColors(
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
    progressBarBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    progressBarGradient = StyleDictionaryColor.colorSpecificProgressbarBackgroundColor,
    progressBarLabel = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
    progressBarNumber = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    radio = SirioRadioButtonColors(
        background = SirioColorState.all(
            StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            StyleDictionaryColor.colorAliasBackgroundColorDisabled
        ),
        borderFocusExtra = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
        ),
        dot = SirioColorState.all(
            StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
        ),
    ),
    searchBar = SirioSearchBarColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    sliderActive = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    sliderDefaultTextFieldBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    sliderDefaultTextFieldBorder = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
    sliderDefaultTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
    sliderDisabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderDisabledTextFieldBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderDisabledTextFieldBorder = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderDisabledTextFieldText = StyleDictionaryColor.colorAliasTextColorDisabled,
    sliderFocusActive = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    sliderFocusTextFieldBorder = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
    sliderFocusTextFieldExtraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    sliderFocusTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
    sliderHoverTextFieldBorder = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
    sliderHoverTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
    sliderInactive = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderNumbers = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
    sliderPressedActive = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
    sliderPressedTextFieldBorder = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
    sliderPressedTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
    sliderPressedThumb = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
    sliderText = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    sliderThumb = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    sliderTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
    stepProgressBar = SirioStepProgressBarColors(
        action = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
        controls = SirioStepControlsColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            next = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            previous = StyleDictionaryColor.colorGlobalSecondary100,
        ),
        selection = SirioStepSelectionColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            progress = StyleDictionaryColor.colorGlobalSecondary100,
            currentStep = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        )
    ),
    tabBarActive = StyleDictionaryColor.colorAliasAppInteractivePrimaryActive,
    tabBarBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    tabBarContent = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
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
    tabs = SirioTabsColors(
        backgroundBottomSelection = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundTopSelection = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        backgroundSelected = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundBottomSelectionDisabled = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundTopSelectionDisabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        text = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        icon = SirioColorState(
            default = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasAppInteractivePrimaryActive,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        selection = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
    ),
    tag = tagLightColors,
    textArea = SirioTextAreaColors(
        background = SirioColorState.all(Color.Transparent),
        extraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
        ),
        dropdown = SirioColorState.all(StyleDictionaryColor.colorAliasInteractiveSecondaryDefault),
        optionBackground = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificOptionBackgroundColorFocus,
            hovered = StyleDictionaryColor.colorSpecificOptionBackgroundColorHover,
            pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
        ),
        optionText = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            hovered = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        ),
        label = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryLabelColorAlert,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryLabelColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        icon = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        helperText = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorGlobalSemanticWarning100,
            success = StyleDictionaryColor.colorGlobalSemanticSuccess100,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        placeholder = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
    ),
    textField = SirioTextFieldColors(
        background = SirioColorState.all(Color.Transparent),
        extraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
        ),
        dropdown = SirioColorState.all(StyleDictionaryColor.colorAliasInteractiveSecondaryDefault),
        optionBackground = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificOptionBackgroundColorFocus,
            hovered = StyleDictionaryColor.colorSpecificOptionBackgroundColorHover,
            pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
        ),
        optionText = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            hovered = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        ),
        label = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryLabelColorAlert,
            warning = StyleDictionaryColor.colorSpecificDataEntryLabelColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryLabelColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        icon = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        helperText = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorGlobalSemanticWarning100,
            success = StyleDictionaryColor.colorGlobalSemanticSuccess100,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        placeholder = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            warning = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            success = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
    ),
    titleBar = SirioTitleBarColors(
        container = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        content = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    ),
    toggle = SirioToggleColors(
        background = SirioColorState.all(
            StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        borderFocusExtra = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
        off = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryFocus,
            hovered = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryFocus,
        ),
        on = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
        ),
    ),
    isDark = false,
)

private val buttonsDarkColors = ButtonColors(
    focusExtraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    primary = SirioButtonColors(
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
    danger = SirioButtonColors(
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
    secondary = SirioButtonColors(
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
    tertiary = SirioButtonColors(
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
    ghost = SirioButtonColors(
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

private val tagDarkColors = SirioTagsColors(
    gray = SirioTagColors(
        background = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    blue = SirioTagColors(
        background = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    red = SirioTagColors(
        background = StyleDictionaryColor.colorAliasInteractiveAlertDefault,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    orange = SirioTagColors(
        background = StyleDictionaryColor.colorGlobalSemanticWarning100,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    green = SirioTagColors(
        background = StyleDictionaryColor.colorGlobalSemanticSuccess100,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    ),
    white = SirioTagColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        text = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    ),
)


internal val DarkColorPalette = SirioColors(
    brand = StyleDictionaryColor.colorGlobalPrimary000,
    accordion = SirioAccordionColors(
        activedBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
        activedBorder = Color.Transparent,
        contentBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        contentBorder = StyleDictionaryColor.colorGlobalLightPrimary50,
        background = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        ),
        content = SirioColorState.all(
            color = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        border = SirioColorState(
            default = StyleDictionaryColor.colorGlobalLightPrimary50,
            disabled = Color.Transparent,
            focused = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
            hovered = Color.Transparent,
            pressed = Color.Transparent,
        ),
    ),
    appNavigationBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark120,
    appNavigationIcon = StyleDictionaryColor.colorAliasAppInteractivePrimary000Default,
    appNavigationIconPressed = StyleDictionaryColor.colorAliasOverlay25Primary000,
    appNavigationSearchBackground1 = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    appNavigationSearchBackground2 = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    appNavigationSearchIcon = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
    appNavigationSearchText = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
    appNavigationText = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
    appNavigationUsernameBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark115,
    appNavigationUsernameText = StyleDictionaryColor.colorAliasInteractivePrimary000Default,
    avviso = SirioAvvisoColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark115,
        borderBottom = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight50,
        icon = StyleDictionaryColor.colorAliasTextColorPrimaryLight40,
        text = StyleDictionaryColor.colorAliasTextColorPrimaryLight40,
        title = StyleDictionaryColor.colorAliasTextColorPrimaryLight40,
    ),
    badge = SirioBadgeColors(
        background = StyleDictionaryColor.colorGlobalSemanticAlert100,
        border = StyleDictionaryColor.colorGlobalPrimary000
    ),
    buttons = buttonsDarkColors,
    card = SirioCardsColors(
        editorial = SirioCardColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            category = tagDarkColors.gray,
            icon = StyleDictionaryColor.colorGlobalSecondary100,
            date = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            title = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            signature = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
            button = buttonsDarkColors.tertiary,
            iconButton = buttonsDarkColors.ghost,
        ),
        process = SirioCardColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryDark115,
            category = tagDarkColors.white,
            icon = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            date = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            title = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            subtitle = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            text = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            signature = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            button = buttonsDarkColors.tertiary,
            iconButton = buttonsDarkColors.tertiary.copy(border = SirioColorState.all(Color.Transparent)),
        ),
    ),
    carousel = SirioCarouselColors(
        backgroundLight = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundMedium = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        indicator = SirioCarouselIndicatorColors(
            dotUnselected = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            dotSelected = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        )
    ),
    checkbox = SirioCheckboxColors(
        background = SirioColorState.all(
            StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            StyleDictionaryColor.colorAliasBackgroundColorDisabled
        ),
        borderFocusExtra = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
        ),
        check = SirioColorState.all(StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
        ),
    ),
    chipButtonBorder = StyleDictionaryColor.colorGlobalMidPrimary70,
    chipContent = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
    chipDefaultBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    chipDefaultButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    chipDisabledBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    chipDisabledButtonBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    chipDisabledContent = StyleDictionaryColor.colorAliasTextColorDisabled,
    chipDisactiveBackground = Color.Transparent,
    chipFocusBackground = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    chipFocusBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    chipFocusButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    chipHoverBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    chipHoverButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
    chipPressedBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
    chipPressedButtonBackground = StyleDictionaryColor.colorAliasInteractivePrimaryPressed,
    chipWithCloseDefaultBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    chipWithCloseDisabledBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    chipWithCloseFocusBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
    chipWithCloseHoverBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
    chipWithClosePressedBackground = StyleDictionaryColor.colorAliasInteractiveSecondaryPressed,
    dialog = SirioDialogColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        semanticDefault = StyleDictionaryColor.colorGlobalSemanticInfo100,
        semanticAlert = StyleDictionaryColor.colorGlobalSemanticAlert100,
        semanticWarning = StyleDictionaryColor.colorGlobalSemanticWarning100,
        title = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
    ),
    dropdown = SirioDropdownColors(
        option = SirioDropdownOptionColors(
            background = SirioColorState(
                default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
                disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
                focused = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
                hovered = StyleDictionaryColor.colorSpecificOptionBackgroundColorHover,
                pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
                valued = StyleDictionaryColor.colorSpecificOptionBackgroundColorValued,
            ),
            border = SirioColorState(
                default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
                disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
                focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
                hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
                pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
                valued = StyleDictionaryColor.colorSpecificOptionBackgroundColorValued,
            ),
            content = SirioColorState(
                default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
                disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
                focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
                hovered = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
                pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
                valued = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            )
        )
    ),
    fabBorderFocus = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    fabContent = StyleDictionaryColor.colorAliasTextColorPrimaryDark110,
    fabDefaultBackground = StyleDictionaryColor.colorAliasAppInteractivePrimary000Default,
    fabFocusBackground = StyleDictionaryColor.colorAliasAppInteractivePrimary000Default,
    fabHoverBackground = StyleDictionaryColor.colorAliasInteractiveAccentHover,
    fabPressedBackground = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
    fileUploadText = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    fileUploadTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
    filter = sirioFilterDarkColors,
    hero = SirioHeroColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        title = StyleDictionaryColor.colorGlobalDarkPrimary115,
        subtitle = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        text = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
        borderBottom = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
    ),
    menuSpalla = menuSpallaDarkColors,
    notificationColors = NotificationColors(
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
    progressBarBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
    progressBarGradient = StyleDictionaryColor.colorSpecificProgressbarBackgroundColor,
    progressBarLabel = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
    progressBarNumber = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    radio = SirioRadioButtonColors(
        background = SirioColorState.all(
            StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            StyleDictionaryColor.colorAliasBackgroundColorDisabled
        ),
        borderFocusExtra = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
        ),
        dot = SirioColorState.all(
            StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
        ),
    ),
    searchBar = SirioSearchBarColors(
        background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    ),
    sliderActive = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    sliderDefaultTextFieldBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    sliderDefaultTextFieldBorder = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
    sliderDefaultTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
    sliderDisabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderDisabledTextFieldBackground = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderDisabledTextFieldBorder = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderDisabledTextFieldText = StyleDictionaryColor.colorAliasTextColorDisabled,
    sliderFocusActive = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    sliderFocusTextFieldBorder = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
    sliderFocusTextFieldExtraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
    sliderFocusTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
    sliderHoverTextFieldBorder = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
    sliderHoverTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
    sliderInactive = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
    sliderNumbers = StyleDictionaryColor.colorAliasTextColorSecondaryDark100,
    sliderPressedActive = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
    sliderPressedTextFieldBorder = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
    sliderPressedTextFieldText = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
    sliderPressedThumb = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
    sliderText = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
    sliderThumb = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    sliderTitle = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
    stepProgressBar = SirioStepProgressBarColors(
        action = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
        controls = SirioStepControlsColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            next = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            previous = StyleDictionaryColor.colorGlobalSecondary100,
        ),
        selection = SirioStepSelectionColors(
            background = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            progress = StyleDictionaryColor.colorGlobalSecondary100,
            currentStep = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        ),
    ),
    tabBarActive = StyleDictionaryColor.colorAliasAppInteractivePrimaryActive,
    tabBarBackground = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
    tabBarContent = StyleDictionaryColor.colorAliasAppInteractiveSecondaryDefault,
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
    tabs = SirioTabsColors(
        backgroundBottomSelection = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundTopSelection = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
        backgroundSelected = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundBottomSelectionDisabled = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
        backgroundTopSelectionDisabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        text = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        icon = SirioColorState(
            default = StyleDictionaryColor.colorAliasTextColorPrimaryLight50,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasAppInteractivePrimaryActive,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
        ),
        selection = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight40,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            pressed = StyleDictionaryColor.colorAliasAppInteractivePrimary000Default,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        )
    ),
    tag = tagDarkColors,
    textArea = SirioTextAreaColors(
        background = SirioColorState.all(Color.Transparent),
        extraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
        ),
        dropdown = SirioColorState.all(StyleDictionaryColor.colorAliasInteractiveSecondaryDefault),
        optionBackground = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificOptionBackgroundColorFocus,
            hovered = StyleDictionaryColor.colorSpecificOptionBackgroundColorHover,
            pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
        ),
        optionText = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            hovered = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        ),
        label = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryLabelColorAlert,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryLabelColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        icon = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        helperText = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorGlobalSemanticWarning100,
            success = StyleDictionaryColor.colorGlobalSemanticSuccess100,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        placeholder = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
    ),
    textField = SirioTextFieldColors(
        background = SirioColorState.all(Color.Transparent),
        extraBorder = StyleDictionaryColor.colorAliasInteractiveBorderFocus,
        border = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryBorderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryBorderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
        ),
        dropdown = SirioColorState.all(StyleDictionaryColor.colorAliasInteractiveSecondaryDefault),
        optionBackground = SirioColorState(
            default = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            disabled = StyleDictionaryColor.colorAliasBackgroundColorDisabled,
            focused = StyleDictionaryColor.colorSpecificOptionBackgroundColorFocus,
            hovered = StyleDictionaryColor.colorSpecificOptionBackgroundColorHover,
            pressed = StyleDictionaryColor.colorSpecificOptionBackgroundColorPressed,
        ),
        optionText = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            hovered = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
            pressed = StyleDictionaryColor.colorAliasTextColorPrimaryLight0,
        ),
        label = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryLabelColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryLabelColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryLabelColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryLabelColorAlert,
            warning = StyleDictionaryColor.colorSpecificDataEntryLabelColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryLabelColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryLabelColorDefault,
        ),
        icon = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        helperText = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            valued = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorGlobalSemanticWarning100,
            success = StyleDictionaryColor.colorGlobalSemanticSuccess100,
            info = StyleDictionaryColor.colorAliasInteractiveSecondaryDefault,
        ),
        placeholder = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            warning = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            success = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
        text = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorFocus,
            hovered = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorHover,
            pressed = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            valued = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorValued,
            alert = StyleDictionaryColor.colorGlobalSemanticAlert100,
            warning = StyleDictionaryColor.colorSpecificDataEntryBorderColorWarning,
            success = StyleDictionaryColor.colorSpecificDataEntryBorderColorSuccess,
            info = StyleDictionaryColor.colorSpecificDataEntryPlaceholderColorDefault,
        ),
    ),
    titleBar = SirioTitleBarColors(
        container = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
        content = StyleDictionaryColor.colorAliasInteractivePrimary000Default,
    ),
    toggle = SirioToggleColors(
        background = SirioColorState.all(
            StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight0,
            StyleDictionaryColor.colorAliasBackgroundColorDisabled,
        ),
        borderFocusExtra = StyleDictionaryColor.colorSpecificDataEntryBorderColorFocus,
        off = SirioColorState(
            default = StyleDictionaryColor.colorSpecificDataEntryBorderColorDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractiveSecondaryFocus,
            hovered = StyleDictionaryColor.colorAliasInteractiveSecondaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractiveSecondaryFocus,
        ),
        on = SirioColorState(
            default = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
            disabled = StyleDictionaryColor.colorAliasTextColorDisabled,
            focused = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
            hovered = StyleDictionaryColor.colorAliasInteractivePrimaryHover,
            pressed = StyleDictionaryColor.colorAliasInteractivePrimaryFocus,
        ),
    ),
    isDark = true,
)

@Keep
@Immutable
data class ButtonColors(
    val focusExtraBorder: Color,
    val primary: SirioButtonColors,
    val danger: SirioButtonColors,
    val secondary: SirioButtonColors,
    val tertiary: SirioButtonColors,
    val ghost: SirioButtonColors,
) {
    companion object {
        @Stable
        val Unspecified = ButtonColors(
            focusExtraBorder = Color.Unspecified,
            primary = SirioButtonColors.Unspecified,
            danger = SirioButtonColors.Unspecified,
            secondary = SirioButtonColors.Unspecified,
            tertiary = SirioButtonColors.Unspecified,
            ghost = SirioButtonColors.Unspecified,
        )
    }
}

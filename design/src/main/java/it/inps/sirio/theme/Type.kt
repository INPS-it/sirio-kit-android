//
// Type.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.styleDictionary.StyleDictionarySize
import it.inps.sirio.styleDictionary.StyleDictionaryTypography
import it.inps.sirio.ui.card.SirioCardTypography
import it.inps.sirio.ui.card.SirioCardsTypography
import it.inps.sirio.ui.checkbox.SirioCheckboxTypography
import it.inps.sirio.ui.hero.SirioHeroTypography
import it.inps.sirio.ui.radiobutton.SirioRadioButtonTypography
import it.inps.sirio.ui.toggle.SirioToggleTypography

internal val Titillium_Web = TitilliumWebFamily
internal val Lora = LoraFamily
internal val Roboto_Mono = RobotoMonoFamily

val appNavigationTitleMd = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH6MdSize05.sp,
    lineHeight = StyleDictionarySize.typographySpecificAppNavigationTitleMdLineHeight.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

val appNavigationTitleXl = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographySpecificAppNavigationTitleXlSize.sp,
    lineHeight = StyleDictionarySize.typographySpecificAppNavigationTitleXlLineHeight.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

val labelXl400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXlSize06.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight06.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

val labelMd700 = TextStyle(
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
//    platformStyle = PlatformTextStyle(includeFontPadding = false),
//    lineHeightStyle = LineHeightStyle(
//        alignment = LineHeightStyle.Alignment.Center,
//        trim = LineHeightStyle.Trim.Both
//    ),
)

val labelMd600 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontStyle(),
)

val tabBarLabelXs = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    textAlign = TextAlign.Center,
)

val helperTextXs400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    platformStyle = PlatformTextStyle(includeFontPadding = false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both
    ),
)

val labelMdNumber400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyAliasFontFamilyNumber03,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    textAlign = TextAlign.Center,
)

val labelProgressBarNumber = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyAliasFontFamilyNumber03,
    fontSize = StyleDictionarySize.typographyGlobalFontSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

val pMd01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

val linkPMd01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    color = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
    textDecoration = TextDecoration.Underline
)

val linkH4Md01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLinkH4MdSize07.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkH4MdLineHeight07.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    color = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    textDecoration = TextDecoration.Underline
)

val mPMd01_700 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
)

val labelMd400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

val placeholderMd400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = 1.em,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    platformStyle = PlatformTextStyle(includeFontPadding = false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both
    ),
)

val h2Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH2MdSize11.sp,
    lineHeight = StyleDictionarySize.typographyAliasH2MdLineHeight11.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasH2FontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasH2FontWeight700.getFontStyle(),
)

val h4Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH4MdSize07.sp,
    lineHeight = StyleDictionarySize.typographyAliasH4MdLineHeight07.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
)

val h5Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH5MdSize06.sp,
    lineHeight = StyleDictionarySize.typographyAliasH5MdLineHeight06.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasH5FontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasH5FontWeight600.getFontStyle(),
)

val h6Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH6MdSize05.sp,
    lineHeight = StyleDictionarySize.typographyAliasH6MdLineHeight05.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontStyle(),
)

val signaturePMd02 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily02,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyGlobalFontWeights700I.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyGlobalFontWeights700I.getFontStyle(),
)

internal val Typography = SirioTypography(
    accordionText = labelMd600,
    appNavigationSearch = labelMd600,
    appNavigationSearchPlaceholder = labelMd600,
    appNavigationTitle = appNavigationTitleMd,
    appNavigationTitleBig = appNavigationTitleXl,
    appNavigationUsername = labelMd700,
    buttonText = labelMd700,
    card = SirioCardsTypography(
        editorial = SirioCardTypography(
            date = labelMdNumber400,
            title = linkH4Md01,
            subtitle = h6Md,
            text = pMd01,
            signature = signaturePMd02,
        ),
        process = SirioCardTypography(
            date = labelMdNumber400,
            title = h4Md,
            subtitle = h6Md,
            text = pMd01,
            signature = signaturePMd02,
        ),
    ),
    checkbox = SirioCheckboxTypography(text = labelMd400),
    chipText = labelMd700,
    dialogText = pMd01,
    dialogTitle = h4Md,
    fabText = labelMd700,
    fileUploadText = helperTextXs400,
    fileUploadTitle = labelMd600,
    hero = SirioHeroTypography(
        title = h2Md,
        subtitle = h4Md,
        text = pMd01,
    ),
    notificationInlineText = pMd01,
    notificationInlineTitle = mPMd01_700,
    notificationToastText = pMd01,
    notificationToastTitle = labelMd700,
    paginationTileNumber = labelMdNumber400,
    progressBarLabel = labelMd600,
    progressBarNumber = labelProgressBarNumber,
    radio = SirioRadioButtonTypography(text = labelMd400),
    sliderNumber = labelMdNumber400,
    sliderText = helperTextXs400,
    sliderTitle = labelMd600,
    tabBarItemText = tabBarLabelXs,
    tabTextDefault = labelMd400,
    tabTextSelected = labelMd700,
    tagText = labelMd700,
    textAreaHelperText = helperTextXs400,
    textAreaLabel = labelMd600,
    textAreaPlaceholder = placeholderMd400,
    textAreaText = placeholderMd400,
    textFieldDropdownLabel = placeholderMd400,
    textFieldHelperText = helperTextXs400,
    textFieldLabel = labelMd600,
    textFieldPlaceholder = placeholderMd400,
    textFieldText = placeholderMd400,
    toggle = SirioToggleTypography(text = labelMd400),
)

@Keep
enum class TypographyStyle {
    Regular {
        override fun getFontWeight(): FontWeight = FontWeight.Normal
        override fun getFontStyle(): FontStyle? = null
    },
    Bold {
        override fun getFontWeight(): FontWeight = FontWeight.Bold
        override fun getFontStyle(): FontStyle? = null
    },
    Light {
        override fun getFontWeight(): FontWeight = FontWeight.Light
        override fun getFontStyle(): FontStyle? = null
    },
    Italic {
        override fun getFontWeight(): FontWeight? = null
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    Medium {
        override fun getFontWeight(): FontWeight = FontWeight.Medium
        override fun getFontStyle(): FontStyle? = null
    },
    SemiBold {
        override fun getFontWeight(): FontWeight = FontWeight.SemiBold
        override fun getFontStyle(): FontStyle? = null
    },
    LightItalic {
        override fun getFontWeight(): FontWeight = FontWeight.Light
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    BoldItalic {
        override fun getFontWeight(): FontWeight = FontWeight.Bold
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    SemiBoldItalic {
        override fun getFontWeight(): FontWeight = FontWeight.SemiBold
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    MediumItalic {
        override fun getFontWeight(): FontWeight = FontWeight.Medium
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    };

    abstract fun getFontWeight(): FontWeight?
    abstract fun getFontStyle(): FontStyle?
}

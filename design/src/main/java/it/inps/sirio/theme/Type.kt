//
// Type.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.styleDictionary.StyleDictionarySize
import it.inps.sirio.styleDictionary.StyleDictionaryTypography

internal val Titillium_Web = TitilliumWebFamily
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
    fontSize = StyleDictionarySize.typographySpecificAppNavigationTitleMdSize.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
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
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

val h4Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH4MdSize07.sp,
    lineHeight = StyleDictionarySize.typographyAliasH4MdLineHeight07.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
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
    appNavigationTitle = appNavigationTitleMd,
    appNavigationTitleBig = appNavigationTitleXl,
    appNavigationUsername = labelMd700,
    appNavigationSearchPlaceholder = labelMd600,
    appNavigationSearch = labelMd600,
    fabText = labelMd700,
    tabBarItemText = tabBarLabelXs,
    accordionText = labelMd600,
    chipText = labelMd700,
    tagText = labelMd700,
    checkboxLabelText = labelMd400,
    radioLabelText = labelMd400,
    toggleLabelText = labelMd400,
    sliderTitle = labelMd600,
    sliderText = helperTextXs400,
    sliderNumber = labelMdNumber400,
    progressBarLabel = labelMd600,
    progressBarNumber = labelProgressBarNumber,
    buttonText = labelMd700,
    fileUploadTitle = labelMd600,
    fileUploadText = helperTextXs400,
    notificationToastTitle = labelMd700,
    notificationToastText = pMd01,
    notificationInlineTitle = mPMd01_700,
    notificationInlineText = pMd01,
    textFieldLabel = labelMd600,
    textFieldPlaceholder = placeholderMd400,
    textFieldText = placeholderMd400,
    textFieldHelperText = helperTextXs400,
    textFieldDropdownLabel = placeholderMd400,
    paginationTileNumber = labelMdNumber400,
    tabTextDefault = labelMd400,
    tabTextSelected = labelMd700,
    dialogTitle = h4Md,
    dialogText = pMd01,
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
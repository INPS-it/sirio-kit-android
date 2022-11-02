//
// Type.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.theme

import androidx.annotation.Keep
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.styleDictionary.StyleDictionarySize
import it.inps.sirio.styleDictionary.StyleDictionaryTypography

internal val Titillium_Web = TitilliumWebFamily
internal val Roboto_Mono = RobotoMonoFamily

internal val appNavigationTitleMd = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH6MdSize05.sp,
    lineHeight = StyleDictionarySize.typographySpecificAppNavigationTitleMdLineHeight.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

internal val appNavigationTitleXl = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographySpecificAppNavigationTitleXlSize.sp,
    lineHeight = StyleDictionarySize.typographySpecificAppNavigationTitleXlLineHeight.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

internal val labelXl400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXlSize06.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelXlLineHeight03.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

@ExperimentalTextApi
internal val labelMd700 = TextStyle(
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    platformStyle = PlatformTextStyle(includeFontPadding = false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both
    ),
)

internal val labelMd600 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographySpecificAppNavigationTitleMdSize.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

internal val tabBarLabelXs = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    textAlign = TextAlign.Center,
)

internal val helperTextXs400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

internal val labelMdNumber400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyAliasFontFamilyNumber03,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    textAlign = TextAlign.Center,
)

internal val labelProgressBarNumber = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyAliasFontFamilyNumber03,
    fontSize = StyleDictionarySize.typographyGlobalFontSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

internal val pMd01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)
internal val linkPMd01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    color = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
    textDecoration = TextDecoration.Underline
)

internal val mPMd01_700 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
)

internal val labelMd400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

internal val placeholderMd400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

internal val h4Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH4MdSize07.sp,
    lineHeight = StyleDictionarySize.typographyAliasH4MdLineHeight07.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
)

@OptIn(ExperimentalTextApi::class)
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
internal enum class TypographyStyle {
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
    Light_Italic {
        override fun getFontWeight(): FontWeight = FontWeight.Light
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    Bold_Italic {
        override fun getFontWeight(): FontWeight = FontWeight.Bold
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    SemiBold_Italic {
        override fun getFontWeight(): FontWeight = FontWeight.SemiBold
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    },
    Medium_Italic {
        override fun getFontWeight(): FontWeight = FontWeight.Medium
        override fun getFontStyle(): FontStyle = FontStyle.Italic
    };

    abstract fun getFontWeight(): FontWeight?
    abstract fun getFontStyle(): FontStyle?
}
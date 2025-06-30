//
// Type.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
import it.inps.sirio.ui.appnavigation.SirioAppNavigationTypography
import it.inps.sirio.ui.filter.SirioFilterTypography
import it.inps.sirio.ui.hero.SirioHeroTypography
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemInfoTypography
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaDrawerItemTypography
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemTitleSectionTypography
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaItemTypography
import it.inps.sirio.ui.menuspalla.SirioMenuSpallaTypography
import it.inps.sirio.ui.notification.SirioNotificationTypography
import it.inps.sirio.ui.table.SirioTableHeaderTypography
import it.inps.sirio.ui.table.SirioTableTypography
import it.inps.sirio.ui.table.cell.SirioTableCellTypography
import it.inps.sirio.ui.table.drawer.SirioTableDrawerTypography
import it.inps.sirio.ui.table.vertical.SirioTableVerticalTypography
import it.inps.sirio.ui.titlebar.SirioTitleBarTypography

internal val Titillium_Web = TitilliumWebFamily
internal val Lora = LoraFamily
internal val Roboto_Mono = RobotoMonoFamily

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineSmMiddle",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val appNavigationTitleMd = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH6MdSize05.sp,
    lineHeight = StyleDictionarySize.typographySpecificAppNavigationTitleMdLineHeight.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineLgMiddle",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val appNavigationTitleXl = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographySpecificAppNavigationTitleXlSize.sp,
    lineHeight = StyleDictionarySize.typographySpecificAppNavigationTitleXlLineHeight.sp,
    fontWeight = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographySpecificAppNavigationTitleFontWeight600.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineMdHeavy",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val labelXl600 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXlSize06.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight06.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineMdRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val labelXl400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXlSize06.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight06.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

@Deprecated("Use foundationTypography instead")
val labelLg700 = TextStyle(
    fontSize = StyleDictionarySize.typographyAliasLabelLgSize05.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    lineHeight = StyleDictionarySize.typographyAliasPLgLineHeight05.sp,
//    platformStyle = PlatformTextStyle(includeFontPadding = false),
//    lineHeightStyle = LineHeightStyle(
//        alignment = LineHeightStyle.Alignment.Center,
//        trim = LineHeightStyle.Trim.Both
//    ),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.labelMdHeavy",
        "it.inps.sirio.theme.SirioTheme"
    )
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

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.labelMdMiddle",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val labelMd600 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.tabbarLabelXsRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val tabBarLabelXs = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight01.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    textAlign = TextAlign.Center,
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.labelSmRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
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

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.labelNumberMdRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val labelMdNumber400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyAliasFontFamilyNumber03,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
    textAlign = TextAlign.Center,
)

@Deprecated("Use foundationTypography instead")
val labelProgressBarNumber = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyAliasFontFamilyNumber03,
    fontSize = StyleDictionarySize.typographyGlobalFontSize02.sp,
    lineHeight = StyleDictionarySize.typographyAliasLabelXsSize02.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

@Deprecated("Use foundationTypography instead")
val pLg01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelLgSize05.sp,
    lineHeight = StyleDictionarySize.typographyAliasPLgLineHeight05.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasPFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasPFontWeight400.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.bodyMdRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val pMd01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.labelMdHeavy.copy(textDecoration = TextDecoration.Underline,)",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val linkPLg01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelLgSize05.sp,
    lineHeight = StyleDictionarySize.typographyAliasPLgLineHeight05.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLinkFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLinkFontWeight700.getFontStyle(),
    textDecoration = TextDecoration.Underline,
)

@Deprecated("Use foundationTypography instead")
val linkPMd01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyGlobalFontSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    color = StyleDictionaryColor.colorAliasInteractiveAccentDefault,
    textDecoration = TextDecoration.Underline
)

@Deprecated("Use foundationTypography instead")
val linkH4Md01 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLinkH4MdSize07.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkH4MdLineHeight07.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
    color = StyleDictionaryColor.colorAliasInteractivePrimaryDefault,
    textDecoration = TextDecoration.Underline
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.labelMdRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val labelMd400 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight400.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.bodyMdRegular",
        "it.inps.sirio.theme.SirioTheme"
    )
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

@Deprecated("Use foundationTypography instead")
val h2Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH2MdSize11.sp,
    lineHeight = StyleDictionarySize.typographyAliasH2MdLineHeight11.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasH2FontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasH2FontWeight700.getFontStyle(),
)

@Deprecated("Use foundationTypography instead")
val h4Lg = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH4LgSize08.sp,
    lineHeight = StyleDictionarySize.typographyAliasH4LgLineHeight08.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasH4FontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasH4FontWeight700.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineLgHeavy",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val h4Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH4MdSize07.sp,
    lineHeight = StyleDictionarySize.typographyAliasH4MdLineHeight07.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight700.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineMdMiddle",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val h5Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH5MdSize06.sp,
    lineHeight = StyleDictionarySize.typographyAliasH5MdLineHeight06.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasH5FontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasH5FontWeight600.getFontStyle(),
)

@Deprecated(
    "Use foundationTypography instead",
    replaceWith = ReplaceWith(
        "SirioTheme.foundationTypography.headlineSmMiddle",
        "it.inps.sirio.theme.SirioTheme"
    )
)
val h6Md = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily01,
    fontSize = StyleDictionarySize.typographyAliasH6MdSize05.sp,
    lineHeight = StyleDictionarySize.typographyAliasH6MdLineHeight05.sp,
    fontWeight = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyAliasLabelFontWeight600.getFontStyle(),
)

@Deprecated("Use foundationTypography instead")
val signaturePMd02 = TextStyle(
    fontFamily = StyleDictionaryTypography.typographyGlobalFontFamily02,
    fontSize = StyleDictionarySize.typographyAliasLabelMdSize04.sp,
    lineHeight = StyleDictionarySize.typographyAliasLinkPMdLineHeight04.sp,
    fontWeight = StyleDictionaryTypography.typographyGlobalFontWeights700I.getFontWeight(),
    fontStyle = StyleDictionaryTypography.typographyGlobalFontWeights700I.getFontStyle(),
)

@Deprecated("Use foundationTypography instead")
internal val Typography = SirioTypography(
    accordionText = labelMd600,
    appNavigation = SirioAppNavigationTypography(
        search = labelMd600,
        searchPlaceholder = labelMd600,
        title = appNavigationTitleMd,
        titleBig = appNavigationTitleXl,
        avatar = labelMd700,
    ),
    buttonText = labelMd700,
    dialogText = pMd01,
    dialogTitle = h4Md,
    filter = SirioFilterTypography(header = h4Lg, title = labelLg700),
    hero = SirioHeroTypography(
        title = h2Md,
        subtitle = h4Md,
        text = pMd01,
    ),
    menuSpalla = SirioMenuSpallaTypography(
        drawerItem = SirioMenuSpallaDrawerItemTypography(
            title = labelLg700,
            subtitle = labelMd400,
        ),
        drawerItemInfo = SirioMenuSpallaDrawerItemInfoTypography(
            title = labelLg700,
            subtitle = labelMd400,
        ),
        itemTitleSection = SirioMenuSpallaItemTitleSectionTypography(title = labelXl600),
        item = SirioMenuSpallaItemTypography(title = labelMd600),
    ),
    notification = SirioNotificationTypography(
        title = labelMd700,
        text = pMd01,
        link = linkPMd01,
    ),
    paginationTileNumber = labelMdNumber400,
    table = SirioTableTypography(
        cell = SirioTableCellTypography(
            text = labelMd400,
            number = labelMdNumber400,
            link = linkPMd01,
            avatarTitle = placeholderMd400,
            avatarSubtitle = helperTextXs400
        ),
        drawer = SirioTableDrawerTypography(
            actionsText = labelMd700,
            title = h5Md,
            itemTitle = labelMd600,
            itemText = pMd01,
            itemNumber = labelMdNumber400,
            itemLink = linkPMd01,
        ),
        header = SirioTableHeaderTypography(title = labelMd600),
        vertical = SirioTableVerticalTypography(
            itemTitle = labelMd700,
            itemText = pMd01,
            itemNumber = labelMdNumber400,
            itemLink = linkPMd01,
        )
    ),
    tabTextDefault = labelMd400,
    tabTextSelected = labelMd700,
    tagText = labelMd700,
    titleBar = SirioTitleBarTypography(title = h5Md, iconText = labelMd600),
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

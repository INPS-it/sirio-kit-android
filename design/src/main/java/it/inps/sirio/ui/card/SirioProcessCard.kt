//
// SirioProcessCard.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.card

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.styleDictionary.StyleDictionaryBoxShadow
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.cardButtonPaddingTop
import it.inps.sirio.theme.cardIconPaddingStart
import it.inps.sirio.theme.cardIconPaddingTop
import it.inps.sirio.theme.cardIconSize
import it.inps.sirio.theme.cardPaddingHorizontal
import it.inps.sirio.theme.cardPaddingVertical
import it.inps.sirio.theme.cardSubtitlePaddingTop
import it.inps.sirio.theme.cardTextPaddingTop
import it.inps.sirio.ui.appnavigation.SirioAppNavigation
import it.inps.sirio.ui.appnavigation.SirioAppNavigationItemData
import it.inps.sirio.ui.appnavigation.SirioFunction
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.dropdownmenu.SirioDropdownItemData
import it.inps.sirio.ui.dropdownmenu.SirioMoreAction
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.ifElse

/**
 * Process cards are dedicated to in-app services and features.
 * They differ in the visible CTA, which is always available, can be either primary or secondary,
 * and can accommodate an icon instead of the text label.
 *
 * @param title The title text.
 * @param modifier The [Modifier] to be applied to this card.
 * @param icon The optional icon to display.
 * @param subtitle The optional subtitle text to display below the title.
 * @param text The optional text to display below the subtitle.
 * @param firstAction The optional primary action to display on the card.
 * @param secondAction The optional secondary action to display on the card.
 * @param moreActions The list of other actions to display on the card.
 * @param color The [SirioCardColor] of the card. Default is [SirioCardColor.DARK].
 * @param cta The call to action style. Default is [SirioProcessCardCTA.INLINE].
 * @param onIconClick The callback to be invoked when the icon is clicked.
 * @param onClickCard The callback to be invoked when the card is clicked.
 */
@Composable
fun SirioProcessCard(
    title: String,
    modifier: Modifier = Modifier,
    icon: SirioIconSource? = null,
    subtitle: String? = null,
    text: String? = null,
    firstAction: SirioProcessCardItemData? = null,
    secondAction: SirioProcessCardItemData? = null,
    moreActions: List<SirioProcessCardItemData> = emptyList(),
    color: SirioCardColor = SirioCardColor.DARK,
    cta: SirioProcessCardCTA = SirioProcessCardCTA.INLINE,
    onIconClick: (() -> Unit)? = null,
    onClickCard: () -> Unit,
) {
    val elevation =
        with(LocalDensity.current) { StyleDictionaryBoxShadow.elevationElevation01.blurRadius.toDp() }

    SirioTheme(darkTheme = color == SirioCardColor.DARK) {
        Card(
            onClick = onClickCard,
            modifier = Modifier
                .shadow(
                    ambientColor = StyleDictionaryBoxShadow.elevationElevation01.color,
                    elevation = elevation
                )
                .then(modifier),
            shape = Shapes.small,
            colors = CardDefaults.cardColors(containerColor = SirioTheme.colors.card.process.background),
        ) {
            Column(
                Modifier.padding(
                    horizontal = cardPaddingHorizontal.dp,
                    vertical = cardPaddingVertical.dp,
                )
            ) {
                Row {
                    SirioText(
                        text = title,
                        color = SirioTheme.colors.card.process.title,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (subtitle == null) 2 else 1,
                        typography = SirioTheme.foundationTypography.headlineSmHeavy
                    )
                    icon?.let {
                        Spacer(Modifier.width(cardIconPaddingStart.dp))
                        Box(Modifier.padding(top = cardIconPaddingTop.dp)) {
                            SirioIcon(
                                icon = it,
                                iconColor = SirioTheme.colors.card.process.icon,
                                size = cardIconSize.dp,
                                onclick = onIconClick,
                            )
                        }
                    }
                }
                subtitle?.let {
                    Spacer(Modifier.height(cardSubtitlePaddingTop.dp))
                    SirioText(
                        text = it,
                        color = SirioTheme.colors.card.process.subtitle,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        typography = SirioTheme.foundationTypography.labelMdMiddle,
                    )
                }
                text?.let {
                    Spacer(Modifier.height(cardTextPaddingTop.dp))
                    SirioText(
                        text = it,
                        color = SirioTheme.colors.card.process.text,
                        overflow = TextOverflow.Ellipsis,
                        typography = SirioTheme.foundationTypography.bodyMdRegular,
                    )
                }
                firstAction?.let {
                    Spacer(Modifier.height(cardButtonPaddingTop.dp))
                    CardButtons(
                        it,
                        secondAction,
                        moreActions,
                        cta = cta,
                    )
                }
            }
        }
    }
}

@Composable
private fun CardButtons(
    firstAction: SirioProcessCardItemData,
    secondAction: SirioProcessCardItemData?,
    moreActions: List<SirioProcessCardItemData>,
    cta: SirioProcessCardCTA,
) {
    if (moreActions.isNotEmpty()) {
        Row(Modifier.fillMaxWidth()) {
            SirioButton(
                size = SirioButtonSize.Medium,
                hierarchy = SirioTheme.colors.card.process.button,
                text = firstAction.text,
                onClick = firstAction.action,
            )
            Spacer(Modifier.weight(1f))
            SirioMoreAction(
                hierarchy = SirioButtonHierarchy.Ghost,
                items = moreActions.map {
                    SirioDropdownItemData(
                        value = it.text,
                        contentDescription = it.contentDescription,
                        action = it.action,
                    )
                },
            )
        }
    } else {
        when (cta) {
            SirioProcessCardCTA.INLINE -> Row {
                SirioButton(
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioButtonHierarchy.Ghost,
                    modifier = Modifier.ifElse(
                        condition = secondAction != null,
                        ifTrueModifier = Modifier.weight(1f),
                    ),
                    text = firstAction.text,
                    onClick = firstAction.action,
                )
                secondAction?.let {
                    SirioButton(
                        size = SirioButtonSize.Medium,
                        hierarchy = SirioTheme.colors.card.process.button,
                        modifier = Modifier.weight(1f),
                        text = it.text,
                        onClick = it.action,
                    )
                }
            }

            SirioProcessCardCTA.BLOCK -> Column {
                SirioButton(
                    size = SirioButtonSize.Medium,
                    hierarchy = SirioTheme.colors.card.process.button,
                    modifier = Modifier.fillMaxWidth(),
                    text = firstAction.text,
                    onClick = firstAction.action,
                )
                secondAction?.let {
                    SirioButton(
                        size = SirioButtonSize.Medium,
                        hierarchy = SirioButtonHierarchy.Ghost,
                        modifier = Modifier.fillMaxWidth(),
                        text = it.text,
                        onClick = it.action,
                    )
                }
            }
        }
    }
}

/**
 * Possible call to action styles for the [SirioProcessCard].
 */
enum class SirioProcessCardCTA {
    /** Call to actions are displayed inline. */
    INLINE,

    /** Call to actions are displayed stacked. */
    BLOCK,
}

/**
 * Defines the colors for the [SirioProcessCard].
 * @param background The background color.
 */
@Keep
data class SirioProcessCardColors(
    val background: Color,
    val icon: Color,
    val title: Color,
    val subtitle: Color,
    val text: Color,
    val button: SirioButtonHierarchy,
) {
    companion object {
        @Stable
                /** Default unspecified [SirioProcessCard] colors. */
        val Unspecified = SirioProcessCardColors(
            background = Color.Unspecified,
            icon = Color.Unspecified,
            title = Color.Unspecified,
            subtitle = Color.Unspecified,
            text = Color.Unspecified,
            button = SirioButtonHierarchy.Ghost,
        )
    }
}

/** Light theme [SirioProcessCard] colors. */
internal val cardProcessLightColors = SirioProcessCardColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    icon = FoundationColor.colorAliasInteractivePrimaryDefault,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    subtitle = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    button = SirioButtonHierarchy.TertiaryLight,
)

/** Dark theme [SirioProcessCard] colors. */
internal val cardProcessDarkColors = SirioProcessCardColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryDark115,
    icon = FoundationColor.colorAliasInteractiveAccentDefault,
    title = FoundationColor.colorAliasTextColorPrimaryLight0,
    subtitle = FoundationColor.colorAliasTextColorPrimaryLight0,
    text = FoundationColor.colorAliasTextColorPrimaryLight0,
    button = SirioButtonHierarchy.TertiaryDark,
)

@Preview(widthDp = 800, heightDp = 1000)
@Composable
fun SirioProcessCardPreview() {
    val title = "Titolo della card"
    val subtitle = "Sottotitolo"
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
    val firstAction = SirioProcessCardItemData(text = "Text", action = {})
    val secondAction = SirioProcessCardItemData(text = "Text2", action = {})
    val moreActions = listOf(
        SirioProcessCardItemData(text = "Action #1", action = {}),
        SirioProcessCardItemData(text = "Action #2", action = {}),
        SirioProcessCardItemData(text = "Action #3", action = {}),
    )
    val icon = SirioIconSource.FaIcon(FaIcons.InfoCircle)
    val space = 20.dp
    Column {
        SirioTheme {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(space)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(space * 2)) {
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        color = SirioCardColor.LIGHT,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        color = SirioCardColor.DARK,
                        onClickCard = {},
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space * 2)) {
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        secondAction = secondAction,
                        cta = SirioProcessCardCTA.INLINE,
                        color = SirioCardColor.LIGHT,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        secondAction = secondAction,
                        cta = SirioProcessCardCTA.INLINE,
                        color = SirioCardColor.DARK,
                        onClickCard = {},
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space * 2)) {
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        secondAction = secondAction,
                        cta = SirioProcessCardCTA.BLOCK,
                        color = SirioCardColor.LIGHT,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        secondAction = secondAction,
                        cta = SirioProcessCardCTA.BLOCK,
                        color = SirioCardColor.DARK,
                        onClickCard = {},
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space * 2)) {
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        secondAction = secondAction,
                        moreActions = moreActions,
                        cta = SirioProcessCardCTA.BLOCK,
                        color = SirioCardColor.LIGHT,
                        onClickCard = {},
                    )
                    SirioProcessCard(
                        title = title,
                        icon = icon,
                        subtitle = subtitle,
                        text = text,
                        modifier = Modifier.weight(1f),
                        firstAction = firstAction,
                        secondAction = secondAction,
                        moreActions = moreActions,
                        cta = SirioProcessCardCTA.BLOCK,
                        color = SirioCardColor.DARK,
                        onClickCard = {},
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(space))
    }
}

@Preview
@Composable
private fun SirioProcessCardPlaygroundPreview() {
    SirioTheme {
        Scaffold(
            topBar = {
                SirioAppNavigation(
                    title = "Titolo del servizio",
                    leftItem = SirioAppNavigationItemData(
                        icon = FaIcons.ChevronLeft,
                        action = {},
                    ),
                    rightFirstItem = SirioAppNavigationItemData(
                        icon = FaIcons.User,
                        action = {},
                    ),
                    rightSecondItem = SirioAppNavigationItemData(
                        icon = FaIcons.Bell,
                        action = {},
                    )
                )
            }
        ) {
            Column(Modifier.padding(it)) {
                SirioTheme(darkTheme = true) { SirioFunction("Titolo  funzione") }
                LazyColumn(
                    modifier = Modifier.background(Color.White),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(5) {
                        SirioProcessCard(
                            title = "Titolo della card",
                            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ",
                            firstAction = SirioProcessCardItemData(text = "Text", action = {}),
                            color = SirioCardColor.LIGHT,
                            onClickCard = {},
                        )
                    }
                }
            }
        }
    }
}

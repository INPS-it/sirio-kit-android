//
// SirioEditorialCard.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.card

import android.graphics.drawable.Drawable
import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.styleDictionary.StyleDictionaryBoxShadow
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.cardActionsPaddingTop
import it.inps.sirio.theme.cardImageHeight
import it.inps.sirio.theme.cardPaddingHorizontal
import it.inps.sirio.theme.cardPaddingVertical
import it.inps.sirio.theme.cardSubtitlePaddingTop
import it.inps.sirio.theme.cardTextPaddingTop
import it.inps.sirio.theme.cardTitlePaddingTopDefault
import it.inps.sirio.theme.cardTitlePaddingTopSpaced
import it.inps.sirio.ui.appnavigation.SirioAppNavigation
import it.inps.sirio.ui.appnavigation.SirioAppNavigationItemData
import it.inps.sirio.ui.appnavigation.SirioFunction
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.tag.SirioTag
import it.inps.sirio.ui.tag.SirioTagColor
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeWords

/**
 * Sirio Editorial Card component
 * @param title The title of the card
 * @param modifier The [Modifier] to be applied to the card
 * @param imageUrl The url of the image to display in the card
 * @param imageDrawable The drawable of the image to display in the card
 * @param imageContentDescriptor The content description for the image
 * @param category The category of the card
 * @param date The date of the card
 * @param subtitle The subtitle of the card
 * @param text The text of the card
 * @param actions The list of actions for the card, displayed as [SirioButton]
 * @param onClickCard The callback to be invoked when the card is clicked
 *
 */
@Composable
fun SirioEditorialCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    imageUrl: (String)? = null,
    imageDrawable: Drawable? = null,
    imageContentDescriptor: String? = null,
    category: String? = null,
    date: String? = null,
    subtitle: String? = null,
    text: String? = null,
    actions: List<SirioEditorialCardItemData> = emptyList(),
    style: SirioEditorialCardStyle = SirioEditorialCardStyle.Default,
    onClickCard: () -> Unit,
) {
    require(
        !title.isNullOrBlank() || !subtitle.isNullOrBlank() || !text.isNullOrBlank()
    ) { "SirioEditorialCard: at least one of title, subtitle or text must be not null or blank" }

    val elevation =
        with(LocalDensity.current) { StyleDictionaryBoxShadow.elevationElevation01.blurRadius.toDp() }

    Card(
        onClick = onClickCard,
        modifier = Modifier
            .testTag("card${title.takeWords(4)}")
            .shadow(
                ambientColor = StyleDictionaryBoxShadow.elevationElevation01.color,
                elevation = elevation
            )
            .then(modifier),
        shape = Shapes.small,
        colors = CardDefaults.cardColors(containerColor = SirioTheme.colors.card.editorial.background),
    ) {
        imageDrawable?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageDrawable)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = imageContentDescriptor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight.dp),
                contentScale = ContentScale.FillWidth,
            )
        } ?: imageUrl?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = imageContentDescriptor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight.dp),
                contentScale = ContentScale.FillWidth,
            )
        }
        Column(
            Modifier
                .wrapContentHeight()
                .padding(
                    horizontal = cardPaddingHorizontal.dp,
                    vertical = cardPaddingVertical.dp
                )
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                category?.let {
                    SirioTag(
                        text = it,
                        color = SirioTheme.colors.card.editorial.tag,
                    )
                }
                date?.let {
                    SirioText(
                        text = it,
                        color = SirioTheme.colors.card.editorial.date,
                        maxLines = 1,
                        typography = SirioTheme.foundationTypography.labelNumberSmRegular,
                    )
                }
            }
            title?.let {
                val paddingTop =
                    when (style) {
                        SirioEditorialCardStyle.Default -> cardTitlePaddingTopDefault
                        else -> cardTitlePaddingTopSpaced
                    }
                val maxLines = when {
                    style == SirioEditorialCardStyle.Spaced -> 2
                    subtitle == null -> 2
                    else -> 1
                }
                SirioText(
                    text = it,
                    modifier = Modifier.padding(top = paddingTop.dp),
                    color = SirioTheme.colors.card.editorial.title,
                    textDecoration = TextDecoration.Underline,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = maxLines,
                    typography = SirioTheme.foundationTypography.linkLgHeavy,
                )
            }
            subtitle?.let {
                SirioText(
                    text = it,
                    modifier = Modifier.padding(top = cardSubtitlePaddingTop.dp),
                    color = SirioTheme.colors.card.editorial.subtitle,
                    maxLines = 1,
                    typography = SirioTheme.foundationTypography.labelMdMiddle,
                )
            }
            text?.let {
                val maxLines = when {
                    style == SirioEditorialCardStyle.Spaced -> 2
                    else -> Int.MAX_VALUE
                }
                SirioText(
                    text = it,
                    modifier = Modifier.padding(top = cardTextPaddingTop.dp),
                    color = SirioTheme.colors.card.editorial.text,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = maxLines,
                    typography = SirioTheme.foundationTypography.bodyMdRegular,
                )
            }
            if (actions.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = cardActionsPaddingTop.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    actions.forEachIndexed { index, action ->
                        SirioButton(
                            modifier = Modifier.testTag("cardButton$index"),
                            size = SirioButtonSize.Small,
                            hierarchy = SirioButtonHierarchy.GhostLight,
                            icon = action.icon,
                            iconContentDescription = action.contentDescription,
                            onClick = action.action,
                        )
                    }
                }
            }
        }
    }
}

sealed interface SirioEditorialCardStyle {
    object Default : SirioEditorialCardStyle
    object Spaced : SirioEditorialCardStyle
}

@Keep
data class SirioEditorialCardColors(
    val background: Color,
    val tag: SirioTagColor,
    val date: Color,
    val title: Color,
    val subtitle: Color,
    val text: Color,
    val button: SirioButtonHierarchy,
) {
    companion object {
        @Stable
        val Unspecified = SirioEditorialCardColors(
            background = Color.Unspecified,
            tag = SirioTagColor.Light,
            date = Color.Unspecified,
            title = Color.Unspecified,
            subtitle = Color.Unspecified,
            text = Color.Unspecified,
            button = SirioButtonHierarchy.GhostDark,
        )
    }
}

internal val cardEditorialLightColors = SirioEditorialCardColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    tag = SirioTagColor.Light,
    date = FoundationColor.colorAliasTextColorSecondaryDark100,
    title = FoundationColor.colorAliasTextColorPrimary100,
    subtitle = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    button = SirioButtonHierarchy.GhostDark,
)

internal val cardEditorialDarkColors = cardEditorialLightColors

@Preview
@Composable
private fun SirioEditorialCardNewsPreview() {
    SirioTheme {
        Column {
            SirioEditorialCard(
                date = "30 Ott 2025",
                title = "Decreto-legge sicurezza sul lavoro: le dichiarazioni di Fava",
                text = "Per il Presidente dell’Istituto rappresenta un cambio di passo nella tutela dei lavoratori.",
                style = SirioEditorialCardStyle.Spaced,
                onClickCard = {}
            )
            SirioEditorialCard(
                date = "30 Ott 2025",
                title = "Decreto-legge sicurezza sul lavoro: le dichiarazioni di Fava",
                text = "Per il Presidente dell’Istituto rappresenta un cambio di passo nella tutela dei lavoratori.",
                onClickCard = {}
            )
            SirioEditorialCard(
                date = "9 Ott 2025",
                title = "INPS, gli impegni istituzionali dall' 8 al 13 dicembre",
                subtitle = "Comunicato Stampa",
                onClickCard = {}
            )
            SirioEditorialCard(
                date = "9 Ott 2025",
                title = "INPS, gli impegni istituzionali dall' 8 al 13 dicembre",
                subtitle = "Comunicato Stampa",
                style = SirioEditorialCardStyle.Spaced,
                onClickCard = {}
            )
        }
    }
}

@Preview
@Composable
private fun SirioEditorialCardPreview() {
    val category = "Categoria"
    val date = "13 Nov 2021"
    val title = "Titolo della card"
    val subtitle = "Sottotitolo"
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    val share =
        SirioEditorialCardItemData(
            icon = SirioIconSource.FaIcon(FaIcons.ShareAlt),
            action = {},
            contentDescription = "Share"
        )
    val like = SirioEditorialCardItemData(
        icon = SirioIconSource.FaIcon(FaIcons.Bookmark),
        action = {},
        contentDescription = "Like"
    )
    SirioTheme {
        SirioEditorialCard(
            category = category,
            date = date,
            title = title,
            subtitle = subtitle,
            text = text,
            actions = listOf(share, like),
            onClickCard = {}
        )
    }
}

@Preview
@Composable
private fun SirioEditorialCardPlaygroundPreview() {
    val category = "Categoria"
    val date = "13 Nov 2021"
    val title = "Titolo della card"
    val subtitle = "Sottotitolo"
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    val share =
        SirioEditorialCardItemData(
            icon = SirioIconSource.FaIcon(FaIcons.ShareAlt),
            action = {},
            contentDescription = "Share"
        )
    val like = SirioEditorialCardItemData(
        icon = SirioIconSource.FaIcon(FaIcons.Bookmark),
        action = {},
        contentDescription = "Like"
    )
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
                        SirioEditorialCard(
                            category = category,
                            date = date,
                            title = title,
                            subtitle = subtitle,
                            text = text,
                            actions = listOf(share, like),
                            onClickCard = {},
                        )
                    }
                }
            }
        }
    }
}

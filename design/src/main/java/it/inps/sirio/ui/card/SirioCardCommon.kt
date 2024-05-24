//
// SirioCardCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.card

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.styleDictionary.StyleDictionaryBoxShadow
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.cardButtonPaddingHorizontal
import it.inps.sirio.theme.cardButtonPaddingTop
import it.inps.sirio.theme.cardIconSize
import it.inps.sirio.theme.cardImageHeight
import it.inps.sirio.theme.cardImagePaddingBottom
import it.inps.sirio.theme.cardPaddingHorizontal
import it.inps.sirio.theme.cardPaddingVertical
import it.inps.sirio.theme.cardSignaturePaddingTop
import it.inps.sirio.theme.cardSubtitlePaddingTop
import it.inps.sirio.theme.cardTextPaddingTop
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.SirioButtonColors
import it.inps.sirio.ui.button.SirioButtonCommon
import it.inps.sirio.ui.tag.SirioTagColors
import it.inps.sirio.ui.tag.SirioTagCommon
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio Card common implementation
 * @param title The title of the card.
 * @param text The text content of the card.
 * @param colors The color scheme of the card.
 * @param typography The typography scheme of the card.
 * @param type The [SirioCardType] of the card.
 * @param modifier The [Modifier] for the card (optional).
 * @param imageUrl The url of the remote image.
 * @param imageContentDescriptor The content descriptor associated with the image
 * @param category The category of the card (optional).
 * @param icon The FA icon of the card (optional), used if no category is provided.
 * @param iconResId The resource id of the image to be used as the icon if no [icon] is provided
 * @param date The date of the card (optional).
 * @param subtitle The subtitle of the card (optional).
 * @param signature The signature of the card (optional).
 * @param buttonText The text for the button (optional).
 * @param onClickButton The action to be performed when the button is clicked (optional).
 * @param items The list of additional buttons to be displayed on the card (optional).
 * @param onClickCard The action to be performed when the card is clicked (optional).
 */
@Composable
internal fun SirioCardCommon(
    title: String,
    text: String,
    colors: SirioCardColors,
    typography: SirioCardTypography,
    type: SirioCardType,
    modifier: Modifier = Modifier,
    imageUrl: (String)? = null,
    imageContentDescriptor: String? = null,
    category: String? = null,
    icon: FaIconType? = null,
    @DrawableRes iconResId: Int? = null,
    date: String? = null,
    subtitle: String? = null,
    signature: String? = null,
    buttonText: String? = null,
    onClickButton: () -> Unit = {},
    items: List<SirioCardItemData> = listOf(),
    onClickCard: () -> Unit = {},
) {
    Card(
        onClick = onClickCard,
        modifier = Modifier
            .shadow(
                ambientColor = StyleDictionaryBoxShadow.elevationElevation01.color,
                elevation = StyleDictionaryBoxShadow.elevationElevation01.blurRadius.dp
            )
            .then(modifier),
        shape = Shapes.small,
        colors = CardDefaults.cardColors(containerColor = colors.background)
    ) {
        imageUrl?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = imageContentDescriptor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(cardImagePaddingBottom.dp))
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
                    SirioTagCommon(
                        text = category,
                        colors = colors.category,
                    )
                } ?: run {
                    if (icon != null || iconResId != null) {
                        SirioIcon(
                            faIcon = icon,
                            iconResId = iconResId,
                            iconColor = colors.icon,
                            size = cardIconSize.dp,
                        )
                    }
                }
                date?.let {
                    SirioText(
                        text = date,
                        color = colors.date,
                        maxLines = 1,
                        typography = typography.date,
                    )
                }
            }
            SirioText(
                text = title,
                modifier = Modifier.padding(top = cardTextPaddingTop.dp),
                color = colors.title,
                maxLines = 2,
                typography = typography.title,
            )
            subtitle?.let {
                SirioText(
                    text = subtitle,
                    modifier = Modifier.padding(top = cardSubtitlePaddingTop.dp),
                    color = colors.subtitle,
                    maxLines = 1,
                    typography = typography.subtitle,
                )
            }
            SirioText(
                text = text,
                modifier = Modifier.padding(top = cardTextPaddingTop.dp),
                color = colors.text,
                maxLines = 4,
                typography = typography.text,
            )
            signature?.let {
                SirioText(
                    text = signature,
                    modifier = Modifier.padding(top = cardSignaturePaddingTop.dp),
                    color = colors.signature,
                    maxLines = 1,
                    typography = typography.signature,
                )
            }
            if (buttonText != null || items.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = cardButtonPaddingTop.dp),
                    horizontalArrangement = if (type == SirioCardType.PROCESS) Arrangement.SpaceBetween else Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    buttonText?.let {
                        SirioButtonCommon(
                            size = ButtonSize.Large,
                            colors = colors.button,
                            text = buttonText,
                            onClick = onClickButton,
                        )
                    }
                    items.forEachIndexed { index, cardItemData ->
                        SirioButtonCommon(
                            size = ButtonSize.Large,
                            colors = colors.iconButton,
                            faIcon = cardItemData.icon,
                            iconContentDescription = cardItemData.contentDescription,
                            onClick = cardItemData.action,
                        )
                        if (index == 0 && items.size > 1)
                            Spacer(modifier = Modifier.width(cardButtonPaddingHorizontal.dp))
                    }
                }
            }
        }
    }
}

enum class SirioCardType {
    EDITORIAL,
    PROCESS
}

@Keep
data class SirioCardsColors(
    val editorial: SirioCardColors,
    val process: SirioCardColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioCardsColors(
            editorial = SirioCardColors.Unspecified,
            process = SirioCardColors.Unspecified,
        )
    }
}

@Keep
data class SirioCardColors(
    val background: Color,
    val category: SirioTagColors,
    val icon: Color,
    val date: Color,
    val title: Color,
    val subtitle: Color,
    val text: Color,
    val signature: Color,
    val button: SirioButtonColors,
    val iconButton: SirioButtonColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioCardColors(
            background = Color.Unspecified,
            category = SirioTagColors.Unspecified,
            icon = Color.Unspecified,
            date = Color.Unspecified,
            title = Color.Unspecified,
            subtitle = Color.Unspecified,
            text = Color.Unspecified,
            signature = Color.Unspecified,
            button = SirioButtonColors.Unspecified,
            iconButton = SirioButtonColors.Unspecified,
        )
    }
}

@Keep
data class SirioCardsTypography(
    val editorial: SirioCardTypography,
    val process: SirioCardTypography,
) {
    companion object {
        @Stable
        val Default = SirioCardsTypography(
            editorial = SirioCardTypography.Default,
            process = SirioCardTypography.Default,
        )
    }
}

@Keep
data class SirioCardTypography(
    val date: TextStyle,
    val title: TextStyle,
    val subtitle: TextStyle,
    val text: TextStyle,
    val signature: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioCardTypography(
            date = TextStyle.Default,
            title = TextStyle.Default,
            subtitle = TextStyle.Default,
            text = TextStyle.Default,
            signature = TextStyle.Default,
        )
    }
}

@Preview(showSystemUi = true, backgroundColor = 0xFF888888)
@Composable
fun SirioCardCommonPreview() {
    SirioTheme(darkTheme = true) {
        SirioCardCommon(
            title = "Title",
            text = "Text",
            type = SirioCardType.PROCESS,
            imageUrl = "https://www.google.it/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png",
            category = "categoria",
            date = "13 Nov 2021",
            colors = SirioTheme.colors.card.process,
            typography = SirioTheme.typography.card.process,
            onClickCard = {},
            subtitle = "Subtitle",
            buttonText = "Text",
            items = listOf(
                SirioCardItemData(
                    icon = FaIcons.Share,
                    contentDescription = null,
                    action = {})
            )
        )
    }
}

//
// SirioHeroCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.hero

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.heroImageHeight
import it.inps.sirio.theme.heroImagePaddingBottomLong
import it.inps.sirio.theme.heroImagePaddingBottomShort
import it.inps.sirio.theme.heroPaddingHorizontal
import it.inps.sirio.theme.heroPaddingVertical
import it.inps.sirio.theme.heroSubtitlePaddingTop
import it.inps.sirio.theme.heroTextPaddingBottom
import it.inps.sirio.theme.heroTextPaddingTop
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.text.SirioText

/**
 * Sirio Hero common implementation
 * @param title The title of the hero.
 * @param text The text content of the hero.
 * @param modifier The [Modifier] for the hero (optional).
 * @param imageUrl The url of the remote image.
 * @param imageContentDescriptor The content descriptor associated with the image
 * @param subtitle The subtitle of the hero (optional).
 * @param buttonText The text for the button (optional).
 * @param onButtonClick The action to be performed when the button is clicked (optional).
 * @param onHeroClick The action to be performed when the hero is clicked (optional).
 */
@Composable
internal fun SirioHeroCommon(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    imageUrl: (String)? = null,
    imageContentDescriptor: String? = null,
    subtitle: String? = null,
    buttonText: String? = null,
    onButtonClick: () -> Unit = {},
    onHeroClick: () -> Unit = {},
) {
    Card(
        onClick = onHeroClick,
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = SirioTheme.colors.hero.background),
    ) {
        Column(
            Modifier
                .padding(
                    start = heroPaddingHorizontal.dp,
                    end = heroPaddingHorizontal.dp,
                    top = heroPaddingVertical.dp,
                )
        ) {
            SirioText(
                text = title,
                color = SirioTheme.colors.hero.title,
                maxLines = 2,
                typography = SirioTheme.typography.hero.title,
            )
            subtitle?.let {
                SirioText(
                    text = subtitle,
                    modifier = Modifier.padding(top = heroSubtitlePaddingTop.dp),
                    color = SirioTheme.colors.hero.subtitle,
                    maxLines = 1,
                    typography = SirioTheme.typography.hero.subtitle,
                )
            }
            SirioText(
                text = text,
                modifier = Modifier.padding(
                    top = heroTextPaddingTop.dp,
                    bottom = heroTextPaddingBottom.dp
                ),
                color = SirioTheme.colors.hero.text,
                maxLines = 4,
                typography = SirioTheme.typography.hero.text,
            )
            buttonText?.let {
                SirioButton(
                    modifier = Modifier.padding(bottom = if (imageUrl != null) heroImagePaddingBottomShort.dp else heroImagePaddingBottomLong.dp),
                    text = it,
                    size = ButtonSize.Large,
                    style = ButtonStyle.Tertiary,
                    onClick = onButtonClick,
                )
            }
            imageUrl?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = imageContentDescriptor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heroImageHeight.dp),
                    contentScale = ContentScale.Fit,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SirioTheme.colors.hero.borderBottom)
        )
    }
}


@Keep
data class SirioHeroColors(
    val background: Color,
    val title: Color,
    val subtitle: Color,
    val text: Color,
    val borderBottom: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioHeroColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            subtitle = Color.Unspecified,
            text = Color.Unspecified,
            borderBottom = Color.Unspecified,
        )
    }
}

@Keep
data class SirioHeroTypography(
    val title: TextStyle,
    val subtitle: TextStyle,
    val text: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioHeroTypography(
            title = TextStyle.Default,
            subtitle = TextStyle.Default,
            text = TextStyle.Default,
        )
    }
}

@Preview(showSystemUi = true, backgroundColor = 0xFF888888)
@Composable
private fun SirioHeroCommonPreview() {
    SirioTheme {
        val heroTitleValue = "Titolo Hero"
        val heroSubtitleValue = "Sottotitolo"
        val heroTextValue =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        val heroButtonText = "Text"
        SirioHeroCommon(
            title = heroTitleValue,
            text = heroTextValue,
            imageUrl = "https://www.google.it/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png",
            onHeroClick = {},
            subtitle = heroSubtitleValue,
            buttonText = heroButtonText,
        )
    }
}
//
// SirioHeroCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.hero

import android.graphics.drawable.Drawable
import androidx.annotation.Keep
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import it.inps.sirio.R
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.heroDividerHeight
import it.inps.sirio.theme.heroImageHeight
import it.inps.sirio.theme.heroLinkPaddingBottom
import it.inps.sirio.theme.heroLinkPaddingTop
import it.inps.sirio.theme.heroPaddingHorizontal
import it.inps.sirio.theme.heroPaddingTop
import it.inps.sirio.theme.heroSubtitlePaddingTop
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.takeTwoWords

/**
 * The internal common implementation for the hero component
 *
 * @param modifier The [Modifier] to be applied to the component.
 * @param onHeroClick The callback to be invoked when the hero is clicked.
 * @param title The optional hero title text.
 * @param subtitle The optional hero subtitle text.
 * @param link The optional hero link text.
 * @param imageUrl The optional hero image from a URL.
 * @param imageDrawable The optional hero image from a drawable.
 * @param imageContentDescriptor The content descriptor for the image.
 * @param imageContentScale The content scale for the image, defaults to [ContentScale.FillWidth].
 * @param onLinkClick The callback to be invoked when the link is clicked.
 */
@Composable
internal fun SirioHeroCommon(
    modifier: Modifier = Modifier,
    onHeroClick: (() -> Unit)? = null,
    title: String? = null,
    subtitle: String? = null,
    link: String? = null,
    imageUrl: (String)? = null,
    imageDrawable: Drawable? = null,
    imageContentDescriptor: String? = null,
    imageContentScale: ContentScale = ContentScale.FillWidth,
    onLinkClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onHeroClick != null) {
        modifier.clickable { onHeroClick() }
    } else {
        modifier
    }

    Surface(
        modifier = clickableModifier.testTag("hero${title.takeTwoWords()}"),
        color = SirioTheme.colors.hero.background,
    ) {
        HeroContent(
            title = title,
            subtitle = subtitle,
            link = link,
            imageUrl = imageUrl,
            imageDrawable = imageDrawable,
            imageContentDescriptor = imageContentDescriptor,
            imageContentScale = imageContentScale,
            onLinkClick = onLinkClick,
        )
    }
}

@Composable
private fun HeroContent(
    title: String?,
    subtitle: String?,
    link: String?,
    imageDrawable: Drawable?,
    imageContentDescriptor: String?,
    imageContentScale: ContentScale,
    imageUrl: String?,
    onLinkClick: (() -> Unit)?,
) {
    Column {
        HeroTexts(title = title, subtitle = subtitle, link = link, onLinkClick = onLinkClick)
        HeroImage(
            imageDrawable = imageDrawable,
            imageContentDescriptor = imageContentDescriptor,
            imageContentScale = imageContentScale,
            imageUrl = imageUrl
        )
        HorizontalDivider(
            thickness = heroDividerHeight.dp,
            color = SirioTheme.colors.hero.borderBottom
        )
    }
}

@Composable
private fun HeroTexts(
    title: String?,
    subtitle: String?,
    link: String?,
    onLinkClick: (() -> Unit)?,
) {
    Column(
        Modifier
            .padding(
                start = heroPaddingHorizontal.dp,
                end = heroPaddingHorizontal.dp,
                top = heroPaddingTop.dp,
            )
    ) {
        title?.let {
            SirioText(
                text = it,
                color = SirioTheme.colors.hero.title,
                maxLines = 1,
                typography = SirioTheme.foundationTypography.displaySmHeavy,
            )
        }
        subtitle?.let {
            SirioText(
                text = subtitle,
                modifier = Modifier.padding(top = heroSubtitlePaddingTop.dp),
                color = SirioTheme.colors.hero.subtitle,
                maxLines = 1,
                typography = SirioTheme.foundationTypography.headlineMdMiddle,
            )
        }
        link?.let {
            SirioText(
                text = it,
                modifier = Modifier
                    .padding(
                        top = heroLinkPaddingTop.dp,
                        bottom = heroLinkPaddingBottom.dp,
                    )
                    .clickable(onLinkClick != null, onClick = { onLinkClick?.invoke() }),
                color = SirioTheme.colors.hero.link,
                textDecoration = TextDecoration.Underline,
                maxLines = 1,
                typography = SirioTheme.foundationTypography.linkMdHeavy,
            )
        }
    }
}

@Composable
private fun HeroImage(
    imageDrawable: Drawable?,
    imageContentDescriptor: String?,
    imageContentScale: ContentScale,
    imageUrl: String?,
) {
    val imageData = imageDrawable ?: imageUrl
    imageData?.let {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageData)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = imageContentDescriptor,
            modifier = Modifier
                .fillMaxWidth()
                .height(heroImageHeight.dp),
            alignment = Alignment.BottomCenter,
            contentScale = imageContentScale,
        )
    }
}

@Keep
data class SirioHeroColors(
    val background: Color,
    val title: Color,
    val subtitle: Color,
    val link: Color,
    val borderBottom: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioHeroColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            subtitle = Color.Unspecified,
            link = Color.Unspecified,
            borderBottom = Color.Unspecified,
        )
    }
}

internal val heroLightColors = SirioHeroColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryDark110,
    title = FoundationColor.colorAliasTextColorPrimaryLight0,
    subtitle = FoundationColor.colorAliasTextColorPrimaryLight50,
    link = FoundationColor.colorAliasInteractiveAccentDefault,
    borderBottom = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
)

internal val heroDarkColors = heroLightColors

@Preview(showSystemUi = true, backgroundColor = 0xFF888888)
@Composable
private fun SirioHeroCommonPreview() {
    SirioTheme {
        val heroTitleValue = "Titolo "
        val heroSubtitleValue = "Sottotitolo"
        val heroLinkText = "Link"
        SirioHeroCommon(
            title = heroTitleValue,
            link = heroLinkText,
            imageDrawable = ContextCompat.getDrawable(LocalContext.current, R.drawable.hero),
            subtitle = heroSubtitleValue,
        )
    }
}

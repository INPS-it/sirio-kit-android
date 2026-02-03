//
// SirioHero.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.hero

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import it.inps.sirio.R
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio Hero component, a banner with title, subtitle, image and an optional link
 *
 * @param modifier The [Modifier] to be applied to the component.
 * @param onHeroClick The callback to be invoked when the hero is clicked.
 * @param title The optional hero title.
 * @param subtitle The optional hero subtitle.
 * @param link The optional link text.
 * @param imageUrl The optional image url.
 * @param imageDrawable The optional image drawable.
 * @param imageContentDescriptor The optional image content description.
 * @param imageContentScale The content scale for the image.
 * @param onLinkClick The callback to be invoked when the link is clicked.
 */
@Composable
fun SirioHero(
    modifier: Modifier = Modifier,
    onHeroClick: (() -> Unit)? = null,
    title: String? = null,
    subtitle: String? = null,
    link: String? = null,
    imageUrl: (String)? = null,
    imageDrawable: Drawable? = if (imageUrl != null) null else
        ContextCompat.getDrawable(LocalContext.current, R.drawable.hero),
    imageContentDescriptor: String? = null,
    imageContentScale: ContentScale = ContentScale.FillWidth,
    onLinkClick: (() -> Unit)? = null,
) {
    SirioHeroCommon(
        modifier = modifier,
        onHeroClick = onHeroClick,
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


@Preview(showSystemUi = true, backgroundColor = 0xFF888888)
@Composable
private fun SirioHeroPreview() {
    SirioTheme(darkTheme = true) {
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

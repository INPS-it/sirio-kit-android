//
// SirioHero.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.hero

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * Sirio Hero implementation
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
fun SirioHero(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    imageUrl: (String)? = null,
    imageContentDescriptor: String? = null,
    subtitle: String? = null,
    buttonText: String? = null,
    onButtonClick: () -> Unit,
    onHeroClick: () -> Unit,
) {
    SirioHeroCommon(
        title = title,
        text = text,
        modifier = modifier,
        imageUrl = imageUrl,
        imageContentDescriptor = imageContentDescriptor,
        subtitle = subtitle,
        buttonText = buttonText,
        onButtonClick = onButtonClick,
        onHeroClick = onHeroClick,
    )
}


@Preview(showSystemUi = true, backgroundColor = 0xFF888888)
@Composable
private fun SirioHeroPreview() {
    SirioTheme(darkTheme = true) {
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

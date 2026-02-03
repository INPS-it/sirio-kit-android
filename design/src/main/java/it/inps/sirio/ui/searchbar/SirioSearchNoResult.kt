//
// SirioSearchNoResult.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.searchbar

import androidx.annotation.Keep
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.R
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.searchBarNoResultDividerHeight
import it.inps.sirio.theme.searchBarNoResultPaddingHorizontal
import it.inps.sirio.theme.searchBarNoResultPaddingVertical
import it.inps.sirio.theme.searchBarNoResultSpacer
import it.inps.sirio.theme.searchBarNoResultSpacerImage
import it.inps.sirio.ui.text.SirioText

@Composable
fun SirioSearchNoResult(
    title: String,
    text: String? = null,
) {
    Column(
        modifier = Modifier
            .background(SirioTheme.colors.searchBar.noResult.background)
            .padding(
                vertical = searchBarNoResultPaddingVertical.dp,
                horizontal = searchBarNoResultPaddingHorizontal.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SirioSearchNoResultTitle(title)
        SirioSearchNoResultImage()
        SirioSearchNoResultText(text)
    }
}

@Composable
private fun SirioSearchNoResultImage() {
    Image(
        painter = painterResource(R.drawable.illustrazione_no_result),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
    )
    HorizontalDivider(
        thickness = searchBarNoResultDividerHeight.dp,
        color = SirioTheme.colors.searchBar.noResult.divider,
    )
}

@Composable
private fun SirioSearchNoResultText(text: String?) {
    Spacer(Modifier.height(searchBarNoResultSpacer.dp))
    text?.let { SirioText(it, typography = SirioTheme.foundationTypography.bodyMdRegular) }
    Spacer(Modifier.height(searchBarNoResultSpacer.dp))
}

@Composable
private fun SirioSearchNoResultTitle(title: String) {
    Spacer(Modifier.height(searchBarNoResultSpacer.dp))
    SirioText(title, typography = SirioTheme.foundationTypography.displaySmHeavy)
    Spacer(Modifier.height(searchBarNoResultSpacerImage.dp))
}

@Keep
data class SirioSearchNoResultColors(
    val background: Color,
    val title: Color,
    val text: Color,
    val divider: Color,
) {
    companion object {
        val Unspecified = SirioSearchNoResultColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            divider = Color.Unspecified,
        )
    }
}

internal val searchNoResultLightColors = SirioSearchNoResultColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    divider = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
)

internal val searchNoResultDarkColors = searchNoResultLightColors

@Preview
@Composable
private fun SirioSearchNoResultPreview() {
    SirioTheme {
        SirioSearchNoResult(
            title = "Nessun risultato trovato",
            text = "Prova ad effettuare una nuova ricerca",
        )
    }
}

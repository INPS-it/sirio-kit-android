//
// SirioCarouselCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.carousel

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.carouselPaddingBottom
import it.inps.sirio.theme.carouselPaddingPagination
import it.inps.sirio.theme.carouselPaddingTop
import it.inps.sirio.theme.carouselPagePartialView
import it.inps.sirio.theme.carouselPageSpacing
import it.inps.sirio.ui.card.SirioProcessCard
import it.inps.sirio.ui.card.SirioProcessCardItemData
import it.inps.sirio.ui.card.SirioProcessCardType

/**
 * A Composable function that renders a carousel with the provided items
 *
 * @param items A list of items to be displayed in the carousel.
 * @param index The index of the initially selected item. Default is 0.
 * @param themeMode The theme mode for the component, Light or Dark.
 * @param key a stable and unique key representing the item. When you specify the key the scroll
 * position will be maintained based on the key, which means if you add/remove items before the
 * current visible item the item with the given key will be kept as the first visible one.
 * @param content The Composable function to render each item in the carousel.
 */
@Composable
internal fun <T> SirioCarouselCommon(
    items: List<T>,
    index: Int = 0,
    themeMode: SirioThemeMode? = null,
    key: ((index: Int) -> Any)? = null,
    content: @Composable (T) -> Unit,
) {
    SirioTheme(themeMode = themeMode) {
        Column(
            modifier = Modifier
                .background(SirioTheme.colors.carousel.background)
                .padding(top = carouselPaddingTop.dp, bottom = carouselPaddingBottom.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val pagerState = rememberPagerState(
                initialPage = index,
                initialPageOffsetFraction = 0f,
                pageCount = { items.size }
            )
            val contentPadding by remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> PaddingValues(
                            start = carouselPageSpacing.dp,
                            end = (2 * carouselPagePartialView + carouselPageSpacing).dp,
                        )

                        pagerState.pageCount - 1 -> PaddingValues(
                            start = (2 * carouselPagePartialView + carouselPageSpacing).dp,
                            end = carouselPageSpacing.dp,
                        )

                        else -> PaddingValues(
                            horizontal = (carouselPageSpacing + carouselPagePartialView).dp
                        )
                    }
                }
            }
            HorizontalPager(
                modifier = Modifier.testTag("carousel"),
                state = pagerState,
                contentPadding =
                    contentPadding,
                pageSpacing = carouselPageSpacing.dp,
                key = key,
            ) { content(items[it]) }
            Spacer(modifier = Modifier.height(carouselPaddingPagination.dp))
            SirioCarouselPagination(pagerState)
        }
    }
}

@Keep
data class SirioCarouselColors(
    val item: SirioCarouselItemColors,
    val background: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioCarouselColors(
            item = SirioCarouselItemColors.Unspecified,
            background = Color.Unspecified,
        )
    }
}

val carouselLightColors = SirioCarouselColors(
    item = carouselItemLightColors,
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
)

val carouselDarkColors = SirioCarouselColors(
    item = carouselItemDarkColors,
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
)

@Preview(showSystemUi = true)
@Composable
private fun SirioCarouselCommonPreview() {
    data class CarouselSampleData(
        val icon: FaIconType,
        val date: String,
        val title: String,
        val subtitle: String,
        val text: String,
        val button: String,
    )

    val samples = listOf(
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 1",
            subtitle = "Sottotitolo 1",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
            button = "Text"
        ),
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 2",
            subtitle = "Sottotitolo 2",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            button = "Text"
        ),
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 3",
            subtitle = "Sottotitolo 3",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            button = "Text"
        ),
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 4",
            subtitle = "Sottotitolo 4",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            button = "Text"
        ),
    )
    SirioTheme {
        SirioCarouselCommon(
            items = samples,
            index = 1,
            themeMode = SirioThemeMode.Light,
        ) { sample ->
            SirioProcessCard(
                title = sample.title,
                type = SirioProcessCardType.Standard(
                    firstAction = SirioProcessCardItemData(text = sample.button, action = {}),
                ),
                modifier = Modifier.height(300.dp),
                subtitle = sample.subtitle,
                text = sample.text,
                onClickCard = {},
            )
        }
    }
}

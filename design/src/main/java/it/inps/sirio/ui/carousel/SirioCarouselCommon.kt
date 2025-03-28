//
// SirioCarouselCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.carousel

import androidx.annotation.Keep
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.carouselContentPaddingHorizontal
import it.inps.sirio.theme.carouselIndicatorPaddingInner
import it.inps.sirio.theme.carouselIndicatorPaddingIntra
import it.inps.sirio.theme.carouselIndicatorSize
import it.inps.sirio.theme.carouselPaddingBottom
import it.inps.sirio.theme.carouselPaddingIntra
import it.inps.sirio.theme.carouselPaddingTop
import it.inps.sirio.theme.carouselPageSpacing
import it.inps.sirio.ui.card.SirioCardItemData
import it.inps.sirio.ui.card.SirioProcessCard
import kotlinx.coroutines.launch

/**
 * A Composable function that renders a carousel with the provided items
 *
 * @param items A list of items to be displayed in the carousel.
 * @param index The index of the initially selected item. Default is 0.
 * @param background The background style for the carousel.
 * @param key a stable and unique key representing the item. When you specify the key the scroll
 * position will be maintained based on the key, which means if you add/remove items before the
 * current visible item the item with the given key will be kept as the first visible one. @see [HorizontalPager]
 * @param content The Composable function to render each item in the carousel.
 */
@Composable
internal fun <T> SirioCarouselCommon(
    items: List<T>,
    index: Int = 0,
    background: SirioCarouselBackground,
    key: ((index: Int) -> Any)? = null,
    content: @Composable (T) -> Unit,
) {
    val backgroundColor = when (background) {
        SirioCarouselBackground.LIGHT -> SirioTheme.colors.carousel.backgroundLight
        SirioCarouselBackground.MEDIUM -> SirioTheme.colors.carousel.backgroundMedium
    }
    Column(
        Modifier
            .background(backgroundColor)
            .padding(top = carouselPaddingTop.dp, bottom = carouselPaddingBottom.dp),
    ) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(
            initialPage = index,
            initialPageOffsetFraction = 0f,
            pageCount = { items.size }
        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = carouselContentPaddingHorizontal.dp),
            key = key,
            pageSpacing = carouselPageSpacing.dp,
        ) { content(items[it]) }
        Spacer(modifier = Modifier.height(carouselPaddingIntra.dp))
        SirioCarouselIndicator(pagerState) {
            coroutineScope.launch { pagerState.animateScrollToPage(it) }
        }
    }
}

@Composable
private fun SirioCarouselIndicator(pagerState: PagerState, onIndexClick: (Int) -> Unit) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(
            carouselIndicatorPaddingIntra.dp,
            Alignment.CenterHorizontally,
        ),
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val (color, width) =
                if (pagerState.currentPage == iteration) {
                    Pair(
                        SirioTheme.colors.carousel.indicator.dotSelected,
                        2 * carouselIndicatorSize,
                    )
                } else {
                    Pair(SirioTheme.colors.carousel.indicator.dotUnselected, carouselIndicatorSize)
                }
            Box(
                modifier = Modifier
                    .clickable { onIndexClick(iteration) }
                    .padding(carouselIndicatorPaddingInner.dp)
                    .clip(CircleShape)
                    .background(color)
                    .animateContentSize()
                    .size(height = carouselIndicatorSize.dp, width = width.dp)
            )
        }
    }
}

@Keep
data class SirioCarouselColors(
    val indicator: SirioCarouselIndicatorColors,
    val backgroundLight: Color,
    val backgroundMedium: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioCarouselColors(
            indicator = SirioCarouselIndicatorColors.Unspecified,
            backgroundLight = Color.Unspecified,
            backgroundMedium = Color.Unspecified,
        )
    }
}

@Keep
data class SirioCarouselIndicatorColors(
    val dotUnselected: Color,
    val dotSelected: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioCarouselIndicatorColors(
            dotUnselected = Color.Unspecified,
            dotSelected = Color.Unspecified,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SirioCarouselCommonPreview() {
    data class CarouselSampleData(
        val icon: FaIconType,
        val date: String,
        val title: String,
        val text: String,
        val button: String,
    )

    val samples = listOf(
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 1",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor",
            button = "Text"
        ),
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 2",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            button = "Text"
        ),
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 3",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            button = "Text"
        ),
        CarouselSampleData(
            icon = FaIcons.Book,
            date = "13 Nov 2021",
            title = "Titolo della card 4",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.",
            button = "Text"
        ),
    )
    SirioTheme {
        SirioCarouselCommon(
            items = samples,
            index = 0,
            background = SirioCarouselBackground.MEDIUM,
        ) { sample ->
            SirioProcessCard(
                modifier = Modifier.height(300.dp),
                title = sample.title,
                text = sample.text,
                buttonText = sample.button,
                icon = sample.icon,
                onClickButton = {},
                date = sample.date,
                item = SirioCardItemData(icon = FaIcons.EllipsisH, action = {}),
                onClickCard = {},
            )
        }
    }
}
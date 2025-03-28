//
// SirioCarousel.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.carousel

import android.util.Log
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

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
fun <T> SirioCarousel(
    items: List<T>,
    index: Int = 0,
    background: SirioCarouselBackground,
    key: ((index: Int) -> Any)? = null,
    content: @Composable (T) -> Unit,
) {
    LaunchedEffect(items) {
        if (items.size > 6)
            Log.e("SirioCarousel", "Max elements allowed is 6")
    }
    val pages: List<T> by remember(items) {
        derivedStateOf { items.take(6) }
    }
    SirioCarouselCommon(
        items = pages,
        index = index,
        background = background,
        key = key,
        content = content
    )
}

enum class SirioCarouselBackground {
    LIGHT,
    MEDIUM,
}
//
// SirioCarouselPagination.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.carousel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

/**
 * The pagination indicator for the carousel
 *
 * @param pagerState The state of the pager, used to obtain the current page and the total page count
 */
@Composable
internal fun SirioCarouselPagination(pagerState: PagerState) {
    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        repeat(pagerState.pageCount) { iteration ->
            SirioCarouselItem(active = pagerState.currentPage == iteration)
        }
    }
}

@Preview
@Composable
private fun SirioCarouselPaginationPreview() {
    SirioTheme {
        val pagerState = rememberPagerState { 6 }
        SirioCarouselPagination(pagerState)
    }
}
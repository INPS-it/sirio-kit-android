//
// Pagination.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.pagination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme

/**
 * Pagination component
 *
 * @param numberOfPages The total number of pages to handle
 * @param selectedPage The index of the selected page, starting from 0
 * @param onPageChanged The callback when a page change occur. It returns the new selected index
 * @param enabled Whether the component allow page changes
 */
@Composable
fun Pagination(
    numberOfPages: Int,
    selectedPage: Int = 0,
    onPageChanged: (Int) -> Unit,
    enabled: Boolean = true,
) {
    PaginationCommon(
        numberOfPages = numberOfPages,
        selectedPage = selectedPage,
        onPageChanged = onPageChanged,
        enabled = enabled,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PaginationPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Pagination(numberOfPages = 6, onPageChanged = {})
        }
    }
}
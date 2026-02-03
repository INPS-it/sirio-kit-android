//
// SirioPaginationCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.pagination

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseColors
import it.inps.sirio.theme.SirioBaseStateColors
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.paginationButtonChangePageWidth
import it.inps.sirio.theme.paginationButtonNumberPageWidth
import it.inps.sirio.theme.paginationButtonSpacing
import it.inps.sirio.theme.paginationPadding
import it.inps.sirio.utils.SirioIconSource
import java.lang.Integer.min

/**
 * Pagination common component
 *
 * @param numberOfPages The total number of pages to handle
 * @param selectedPage The index of the selected page, starting from 0
 * @param enabled Whether the component allow page changes
 * @param onPageChanged The callback when a page change occur
 */
@Composable
internal fun SirioPaginationCommon(
    numberOfPages: Int,
    selectedPage: Int = 0,
    enabled: Boolean = true,
    onPageChanged: (page: Int) -> Unit,
) {
    require(selectedPage >= 0 && numberOfPages >= 1) {
        "Invalid arguments: selectedPage=$selectedPage, numberOfPages=$numberOfPages (selectedPage >= 0, numberOfPages >= 1)"
    }
    require(selectedPage < numberOfPages) {
        "Selected page ($selectedPage) is out of range (0..${numberOfPages - 1})"
    }

    val changeBtnWidth = paginationButtonChangePageWidth.dp
    val numberBtnWidth = paginationButtonNumberPageWidth.dp
    val spacing = paginationButtonSpacing.dp
    val outerPadding = paginationPadding.dp

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(SirioTheme.colors.pagination.background)
            .padding(outerPadding),
    ) {
        val maxWidth = this.maxWidth

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(SirioTheme.colors.pagination.background)
                .padding(paginationPadding.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SirioPaginationButtonChangePage(
                icon = SirioIconSource.FaIcon(FaIcons.AngleLeft),
                enabled = enabled && selectedPage > 0,
                onClick = { onPageChanged(selectedPage - 1) },
            )
            Spacer(modifier = Modifier.width(spacing))
            if (maxWidth > 0.dp) {
                val numberPageButtonWidth =
                    numberBtnWidth + spacing
                val maxButtons =
                    (maxWidth - (changeBtnWidth * 2 + spacing)) / numberPageButtonWidth
                val numberOfButtons: Int = min(maxButtons.toInt(), numberOfPages)
                val previousButtons: Int = numberOfButtons / 2
                val followingButtons: Int = (numberOfButtons - 1) / 2

                val lastIndex = numberOfPages - 1
                var startIndex = selectedPage - previousButtons
                var endIndex = selectedPage + followingButtons

                if (startIndex < 0) {
                    endIndex += 0 - startIndex
                    startIndex = 0
                }

                if (endIndex > lastIndex) {
                    if (startIndex > 0) {
                        startIndex -= endIndex - lastIndex
                    }
                    endIndex = lastIndex
                }

                val tileDataArray = ArrayList<PaginationTileData>()
                (startIndex..endIndex).forEach { tileDataArray.add(PaginationTileData.number(it)) }
                if (startIndex != 0) {
                    tileDataArray[0] = PaginationTileData.number(0)
                    tileDataArray[1] = PaginationTileData.dots()
                }
                if (endIndex != lastIndex) {
                    tileDataArray[numberOfButtons - 2] = PaginationTileData.dots()
                    tileDataArray[numberOfButtons - 1] = PaginationTileData.number(lastIndex)
                }

                tileDataArray.forEach { tiledata ->
                    if (tiledata.dots) {
                        SirioPaginationDots()
                    } else {
                        SirioPaginationButtonNumberPage(
                            number = "${tiledata.number + 1}",
                            selected = tiledata.number == selectedPage,
                            enabled = enabled,
                            onClick = { onPageChanged(tiledata.number) },
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing))
                }
            }
            SirioPaginationButtonChangePage(
                icon = SirioIconSource.FaIcon(FaIcons.AngleRight),
                enabled = enabled && selectedPage < numberOfPages - 1,
                onClick = { onPageChanged(selectedPage + 1) })
        }
    }
}

/**
 * Internal representation of a single tile data
 *
 * @param number The page index, -1 if dots
 * @param dots  Whether the tile is a dots one and not clickable
 */
@Keep
private data class PaginationTileData(
    val number: Int = -1,
    val dots: Boolean = false,
) {
    companion object {
        fun number(n: Int) = PaginationTileData(number = n, dots = false)
        fun dots() = PaginationTileData(dots = true)
    }
}

@Keep
data class SirioPaginationColors(
    val background: Color,
    val buttonChange: SirioBaseStateColors,
    val buttonNumber: SirioBaseStateColors,
    val dots: SirioBaseColors,
) {
    companion object Companion {
        @Stable
        val Unspecified = SirioPaginationColors(
            background = Color.Unspecified,
            buttonChange = SirioBaseStateColors.Unspecified,
            buttonNumber = SirioBaseStateColors.Unspecified,
            dots = SirioBaseColors.Unspecified,
        )
    }
}

internal val paginationLightColors = SirioPaginationColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    buttonChange = paginationButtonChangePageLightColors,
    buttonNumber = paginationButtonNumberPageLightColors,
    dots = paginationDotsLightColors,
)

internal val paginationDarkColors = SirioPaginationColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    buttonChange = paginationButtonChangePageDarkColors,
    buttonNumber = paginationButtonNumberPageDarkColors,
    dots = paginationDotsDorkColors,
)

@Preview(showSystemUi = true)
@Composable
private fun PaginationCommonPreview() {
    SirioTheme {
        SirioPaginationCommon(numberOfPages = 10, onPageChanged = {})
    }
}
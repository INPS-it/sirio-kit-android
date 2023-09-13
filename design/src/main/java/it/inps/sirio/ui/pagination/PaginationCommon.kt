//
// PaginationCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.pagination

import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.paginationNavigationButtonWidth
import it.inps.sirio.theme.paginationTileBorderWidth
import it.inps.sirio.theme.paginationTileHeight
import it.inps.sirio.theme.paginationTileShape
import it.inps.sirio.theme.paginationTileWidth
import it.inps.sirio.theme.paginationVerticalPadding
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.text.SirioTextCommon
import java.lang.Integer.min

/**
 * Pagination common component
 *
 * @param numberOfPages The total number of pages to handle
 * @param selectedPage The index of the selected page, starting from 0
 * @param onPageChanged The callback when a page change occur
 * @param enabled Whether the component allow page changes
 */
@Composable
internal fun PaginationCommon(
    numberOfPages: Int,
    selectedPage: Int = 0,
    onPageChanged: (Int) -> Unit,
    enabled: Boolean = true,
) {
    require(selectedPage >= 0 && numberOfPages >= 1) { "Check the values assigned to 'selectedPage' and 'numberOfPages" }
    require(selectedPage < numberOfPages) { "Selected page number($selectedPage) is out of range (0-$numberOfPages)" }
    val localDensity = LocalDensity.current
    var width by remember { mutableStateOf(0.dp) }
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(SirioTheme.colors.pagination.background)
            .onGloballyPositioned { coordinates ->
                width = with(localDensity) { coordinates.size.width.toDp() }
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SirioButton(
            size = ButtonSize.Small,
            style = ButtonStyle.Ghost,
            icon = FaIcons.AngleLeft,
            enabled = enabled && selectedPage > 0,
            onClick = { onPageChanged(selectedPage - 1) })

        if (width > 0.dp) {
            val maxButtons: Int =
                min(
                    ((width - (paginationNavigationButtonWidth * 2)) / paginationTileWidth).toInt(),
                    numberOfPages
                )
            val previousButtons: Int = maxButtons / 2
            val followingButtons: Int = (maxButtons - 1) / 2

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
                tileDataArray[maxButtons - 2] = PaginationTileData.dots()
                tileDataArray[maxButtons - 1] = PaginationTileData.number(lastIndex)
            }

            tileDataArray.forEach { tiledata ->
//                if (tiledata.hidden) {} else
                if (tiledata.dots) {
                    PaginationTile(
                        text = "..",
                        isSelected = false,
                        enabled = enabled,
                        onClick = {},
                    )
                } else {
                    PaginationTile(
                        text = "${tiledata.number + 1}",
                        isSelected = tiledata.number == selectedPage,
                        enabled = enabled,
                        onClick = { onPageChanged(tiledata.number) },
                    )
                }
            }
        }
        SirioButton(
            size = ButtonSize.Small,
            style = ButtonStyle.Ghost,
            icon = FaIcons.AngleRight,
            enabled = enabled && selectedPage < numberOfPages - 1,
            onClick = { onPageChanged(selectedPage + 1) })
    }
}

/**
 * A single page button object
 *
 * @param text The number on the tile
 * @param isSelected If the tile is currently selected
 * @param enabled If the tile can be selected
 * @param onClick The callback when tile is pressed
 */
@Composable
private fun PaginationTile(
    text: String,
    isSelected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val paginationParams = getPaginationParams(enabled, isFocused, isPressed, isHovered, isSelected)

    Surface(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = paginationVerticalPadding)
            .width(paginationTileWidth)
            .height(paginationTileHeight),
        enabled = enabled,
        shape = paginationTileShape,
        color = paginationParams.tileBackgroundColor,
        contentColor = paginationParams.numberColor,
        border = BorderStroke(
            width = paginationTileBorderWidth,
            color = paginationParams.tileBorderColor
        ),
        interactionSource = interactionSource,
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SirioTextCommon(
                text = text,
                color = paginationParams.numberColor,
                typography = SirioTheme.typography.paginationTileNumber,
            )
        }
    }
}

/**
 * Internal representation of a single tile data
 *
 * @param number The tile number
 * @param hidden  Whether the tile is hidden in the pagination
 * @param dots  Whether the tile is dots one and not clickable
 */
@Keep
private data class PaginationTileData(
    val number: Int = -1,
    val hidden: Boolean = true,
    val dots: Boolean = false,
) {
    companion object {
        fun number(n: Int) = PaginationTileData(number = n, hidden = false, dots = false)
        fun dots() = PaginationTileData(hidden = false, dots = true)
        fun hidden() = PaginationTileData(hidden = true, dots = false)
    }
}

/**
 * Pagination tile colors based on current state
 */
@Composable
private fun getPaginationParams(
    enabled: Boolean,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    isSelected: Boolean,
): PaginationParams = when {
    !enabled && isSelected -> PaginationParams(
        tileBackgroundColor = SirioTheme.colors.pagination.tile.disabled,
        tileBorderColor = SirioTheme.colors.pagination.tileBorder.disabled,
        numberColor = SirioTheme.colors.pagination.number.disabled,
    )
    isFocused -> PaginationParams(
        tileBackgroundColor = SirioTheme.colors.pagination.tile.focused,
        tileBorderColor = SirioTheme.colors.pagination.tileBorder.focused,
        numberColor = SirioTheme.colors.pagination.number.focused,
    )
    isPressed || isSelected -> PaginationParams(
        tileBackgroundColor = SirioTheme.colors.pagination.tile.pressed,
        tileBorderColor = SirioTheme.colors.pagination.tileBorder.pressed,
        numberColor = SirioTheme.colors.pagination.number.pressed,
    )
    isHovered -> PaginationParams(
        tileBackgroundColor = SirioTheme.colors.pagination.tile.hovered,
        tileBorderColor = SirioTheme.colors.pagination.tileBorder.hovered,
        numberColor = SirioTheme.colors.pagination.number.hovered,
    )
    else -> PaginationParams(
        tileBackgroundColor = SirioTheme.colors.pagination.tile.default,
        tileBorderColor = SirioTheme.colors.pagination.tileBorder.default,
        numberColor = SirioTheme.colors.pagination.number.default,
    )
}

data class PaginationParams(
    val tileBackgroundColor: Color,
    val tileBorderColor: Color,
    val numberColor: Color,
)

@Keep
data class SirioPaginationColors(
    var background: Color,
    var tile: SirioColorState,
    var number: SirioColorState,
    var tileBorder: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioPaginationColors(
            background = Color.Unspecified,
            tile = SirioColorState.Unspecified,
            number = SirioColorState.Unspecified,
            tileBorder = SirioColorState.Unspecified,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PaginationCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            PaginationCommon(numberOfPages = 10, onPageChanged = {})
        }
    }
}
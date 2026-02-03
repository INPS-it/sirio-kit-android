//
// SirioCarouselItem.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.carousel

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.carouselItemBorderWidth
import it.inps.sirio.theme.carouselItemCornerRadius
import it.inps.sirio.theme.carouselItemHeight
import it.inps.sirio.theme.carouselItemPadding
import it.inps.sirio.theme.carouselItemWidthActive
import it.inps.sirio.theme.carouselItemWidthDefault

/**
 * The single item of the carousel indicator
 *
 * @param active Whether the item is the one of the current page
 */
@Composable
internal fun SirioCarouselItem(active: Boolean) {
    val background = when {
        active -> SirioTheme.colors.carousel.item.active
        else -> SirioTheme.colors.carousel.item.default
    }

    val width = when {
        active -> carouselItemWidthActive.dp
        else -> carouselItemWidthDefault.dp
    }

    Box(
        modifier = Modifier
            .padding(carouselItemPadding.dp)
            .clip(RoundedCornerShape(carouselItemCornerRadius.dp))
            .border(
                width = carouselItemBorderWidth.dp,
                color = background,
                shape = RoundedCornerShape(carouselItemCornerRadius.dp),
            )
            .background(background)
            .animateContentSize()
            .size(width = width, height = carouselItemHeight.dp)
    )
}

data class SirioCarouselItemColors(
    val default: Color,
    val active: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioCarouselItemColors(
            default = Color.Unspecified,
            active = Color.Unspecified,
        )
    }
}

internal val carouselItemLightColors = SirioCarouselItemColors(
    default = FoundationColor.colorAliasInteractiveSecondaryDefault,
    active = FoundationColor.colorAliasInteractivePrimaryDefault,
)

internal val carouselItemDarkColors = carouselItemLightColors

@Preview
@Composable
private fun SirioCarouselItemPreview() {
    SirioTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SirioCarouselItem(true)
            SirioCarouselItem(false)
        }
    }
}
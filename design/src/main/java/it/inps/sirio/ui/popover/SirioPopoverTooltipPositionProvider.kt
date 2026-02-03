//
// SirioPopoverTooltipPositionProvider.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.popover

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import it.inps.sirio.theme.popoverScreenPadding
import it.inps.sirio.theme.popoverSpacingBetweenTooltipAndAnchor

/**
 * Remembers a [PopupPositionProvider] used to position Sirio popover tooltips.
 *
 * The returned provider positions the tooltip relative to the anchor content using
 * window coordinates, ensuring consistent behavior regardless of how the hosting
 * screen handles padding, insets, or layout structure.
 *
 * Positioning rules:
 * - Horizontal alignment is centered on the anchor when possible.
 * - Vertical alignment prefers placement below the anchor.
 * - The tooltip is constrained to remain fully visible within the screen bounds.
 */
@Composable
internal fun rememberSirioPopoverTooltipPositionProvider(): PopupPositionProvider {
    val density = LocalDensity.current

    // Spacing between the anchor content and the tooltip, expressed in pixels.
    val tooltipAnchorSpacing =
        with(density) { popoverSpacingBetweenTooltipAndAnchor.dp.roundToPx() }

    // Minimum padding between the tooltip and the screen edges, expressed in pixels.
    val tooltipBorderSpacing = with(density) { popoverScreenPadding.dp.roundToPx() }

    // Recreate the provider only when spacing values change (e.g. density or
    // configuration changes).
    return remember(tooltipAnchorSpacing, tooltipBorderSpacing) {
        TooltipPositionProviderImpl(tooltipAnchorSpacing, tooltipBorderSpacing)
    }
}

/**
 * [PopupPositionProvider] implementation used to calculate the position of a Sirio popover.
 *
 * The provider applies the following layout strategy:
 * - The tooltip is horizontally centered relative to the anchor when space allows.
 * - The tooltip is vertically positioned below the anchor by default.
 * - If positioning below would cause the tooltip to exceed the screen bounds,
 *   it is positioned above the anchor instead.
 * - Final coordinates are clamped to ensure the tooltip remains fully visible
 *   within the available screen area.
 */
private class TooltipPositionProviderImpl(
    val anchorSpacing: Int,
    val borderSpacing: Int,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {

        /*
         * Horizontal positioning
         *
         * The tooltip is initially centered relative to the anchor content.
         * The resulting position is then clamped to prevent overflow beyond
         * the left or right edges of the screen.
         */
        var x = anchorBounds.left + (anchorBounds.width - popupContentSize.width) / 2
        val minX = borderSpacing
        val maxX = (windowSize.width - popupContentSize.width - borderSpacing).coerceAtLeast(minX)
        x = x.coerceIn(minX, maxX)

        /*
         * Vertical positioning
         *
         * The tooltip is preferably positioned below the anchor content.
         * If this would cause it to exceed the bottom edge of the screen,
         * it is positioned above the anchor instead.
         *
         * The final position is clamped to ensure full visibility in all
         * configurations (e.g. small screens or multi-window mode).
         */
        var y = anchorBounds.bottom + anchorSpacing

        if (y + popupContentSize.height > windowSize.height - borderSpacing) {
            y = anchorBounds.top - popupContentSize.height - anchorSpacing
        }

        val minY = borderSpacing
        val maxY = (windowSize.height - popupContentSize.height - borderSpacing).coerceAtLeast(minY)
        y = y.coerceIn(minY, maxY)

        return IntOffset(x, y)
    }
}

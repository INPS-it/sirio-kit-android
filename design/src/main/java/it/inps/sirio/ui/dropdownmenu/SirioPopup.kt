//
// SirioPopup.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdownmenu

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownMenuBorderWidth
import it.inps.sirio.theme.dropdownMenuCornerSize

/**
 * Class that defines the state of a popup.
 *
 * @property isVisible Boolean that defines whether the popup is currently visible or not.
 */
@Stable
class SirioPopupState(
    isVisible: Boolean = false,
) {
    /**
     * Horizontal alignment from which the popup will expand from and shrink to.
     */
    var horizontalAlignment: Alignment.Horizontal by mutableStateOf(Alignment.CenterHorizontally)

    /**
     * Boolean that defines whether the popup is displayed above or below the anchor.
     */
    var isTop: Boolean by mutableStateOf(false)

    /**
     * Boolean that defines whether the popup is currently visible or not.
     */
    var isVisible: Boolean by mutableStateOf(isVisible)
}


/**
 * Composable for displaying a custom popup.
 *
 * @param sirioPopupState the [SirioPopupState] used to track whether the popup is visible
 * @param onDismissRequest callback to be invoked when the popup is dismissed
 * @param modifier [Modifier] used to modify the composable's layout and behavior
 * @param offset the [DpOffset] to apply to the popup's position
 * @param properties the [PopupProperties] used to configure the popup
 * @param content the [ColumnScope] composable that defines the popup's content
 */
@Composable
fun SirioPopup(
    sirioPopupState: SirioPopupState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit,
) {
    // Create a transition state to track whether the popup is expanded.
    val expandedStates = remember { MutableTransitionState(false) }
    expandedStates.targetState = sirioPopupState.isVisible

    // Only show the popup if it's visible.
    if (expandedStates.currentState || expandedStates.targetState) {
        val density = LocalDensity.current

        // Instantiate a CustomPopupPositionProvider with the given offset.
        val popupPositionProvider = SirioPopupPositionProvider(
            contentOffset = offset,
            density = density
        ) { alignment, isTop ->
            // Update the PopupState's alignment and direction.
            sirioPopupState.horizontalAlignment = alignment
            sirioPopupState.isTop = isTop
        }

        // Display the popup using the Popup composable.
        Popup(
            onDismissRequest = onDismissRequest,
            popupPositionProvider = popupPositionProvider,
            properties = properties
        ) {
            // Display the popup's content using the CustomPopupContent composable.
            SirioPopupContent(
                expandedStates = expandedStates,
                transformOrigin = TransformOrigin(
                    pivotFractionX = when (sirioPopupState.horizontalAlignment) {
                        Alignment.Start -> 0f
                        Alignment.CenterHorizontally -> 0.5f
                        else -> 1f
                    },
                    pivotFractionY = if (sirioPopupState.isTop) 1f else 0f
                ),
                modifier = modifier,
                content = content
            )
        }
    }
}

/**
 * A custom implementation of PopupPositionProvider.
 * This calculates the position of the popup relative to the anchor
 *
 * @property contentOffset The offset of the popup content from the anchor position.
 * @property density The density of the display.
 * @property onPopupPositionFound A callback that is called once the popup position is found.
 */
@Immutable
private data class SirioPopupPositionProvider(
    val contentOffset: DpOffset,
    val density: Density,
    val onPopupPositionFound: (Alignment.Horizontal, Boolean) -> Unit,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        // The content offset specified using the dropdown offset parameter.
        val contentOffsetX = with(density) { contentOffset.x.roundToPx() }
        // The content offset specified using the dropdown offset parameter.
        val contentOffsetY = with(density) { contentOffset.y.roundToPx() }

        val isFitEnd =
            (anchorBounds.left + contentOffsetX + popupContentSize.width) < windowSize.width
        val isFitStart = (anchorBounds.left - contentOffsetX - popupContentSize.width) > 0
        val popupHalfWidth = popupContentSize.width / 2
        val halfAnchor = (anchorBounds.right - anchorBounds.left) / 2
        val isFitCenter =
            ((anchorBounds.left + halfAnchor + popupHalfWidth) < windowSize.width) &&
                    ((anchorBounds.left + halfAnchor - popupHalfWidth) > 0)

        val endPlacementOffset = anchorBounds.left - contentOffsetX
        val centerPlacementOffset = anchorBounds.left - popupHalfWidth + contentOffsetX
        val startPlacementOffset = anchorBounds.right + contentOffsetX - popupContentSize.width

        val bottomCoordinatesY = anchorBounds.bottom + contentOffsetY + popupContentSize.height
        val isFitBottom = bottomCoordinatesY <= windowSize.height
        val topCoordinatesY = anchorBounds.top - contentOffsetY - popupContentSize.height
        val isFitTop = topCoordinatesY > 0 || anchorBounds.top > windowSize.height

        // Compute vertical position.
        val toBottom = anchorBounds.bottom + contentOffsetY
        val toTop = anchorBounds.top - contentOffsetY - popupContentSize.height
        val toCenter = anchorBounds.top - popupContentSize.height / 2
        val toDisplayBottom = windowSize.height - popupContentSize.height
        val yOffset = sequenceOf(
            if (isFitTop) toTop else toBottom,
            toCenter,
            toDisplayBottom
        ).firstOrNull {
            it + popupContentSize.height <= windowSize.height
        } ?: toTop

        val horizontalAndOffset = getHorizontalOffset(
            isFitsStart = isFitStart,
            isFitsEnd = isFitEnd,
            isFitsCenter = isFitCenter,
            endPlacementOffset = endPlacementOffset,
            startPlacementOffset = startPlacementOffset,
            centerPlacementOffset = centerPlacementOffset
        )

        onPopupPositionFound(horizontalAndOffset.first, isFitTop)
        return IntOffset(horizontalAndOffset.second, yOffset)
    }
}


@Suppress("ModifierParameter")
/**
 * Composable that defines custom animations for a popup and applies them to a [Surface].
 *
 * @param expandedStates [MutableTransitionState] that determines whether the popup is expanding or shrinking.
 * @param transformOrigin [TransformOrigin] determining from which position the popup (dis)appears from.
 * @param modifier [Modifier] for this composable.
 * @param content content that will be displayed within the popup.
 */
@Composable
private fun SirioPopupContent(
    expandedStates: MutableTransitionState<Boolean>,
    transformOrigin: TransformOrigin,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    // Menu open/close animation.
    val transition = rememberTransition(expandedStates, "Popup")

    // Scale animation.
    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded.
                tween(durationMillis = 200)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 200)
            }
        },
        label = "Popup Scale"
    ) {
        if (it) {
            // Popup is expanded.
            1f
        } else {
            // Popup is dismissed.
            0f
        }
    }

    // Alpha animation.
    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded.
                tween(durationMillis = 200)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = 200)
            }
        },
        label = "Popup Alpha"
    ) {
        if (it) {
            // Popup is expanded.
            1f
        } else {
            // Popup is dismissed.
            0f
        }
    }

    // Helper function for applying animations to graphics layer.
    fun GraphicsLayerScope.graphicsLayerAnim() {
        scaleX = scale
        scaleY = scale
        this.alpha = alpha
        this.transformOrigin = transformOrigin
    }

    Surface(
        modifier = Modifier
            .graphicsLayer {
                graphicsLayerAnim()
            },
        shape = RoundedCornerShape(dropdownMenuCornerSize.dp),
        border = BorderStroke(
            width = dropdownMenuBorderWidth.dp,
            color = SirioTheme.colors.dropdownMenu.option.border.default
        )
    ) {
        Column(
            modifier = modifier
                .width(IntrinsicSize.Max)
                .verticalScroll(rememberScrollState()),
            content = content
        )
    }
}

/**
 * Get the horizontal offset of the popup based on fitting and placement.
 *
 * @param isFitsStart    Whether the popup fits when placed at the start.
 * @param isFitsEnd      Whether the popup fits when placed at the end.
 * @param isFitsCenter   Whether the popup fits when placed at the center.
 * @param endPlacementOffset     The offset when the popup is placed at the end.
 * @param startPlacementOffset   The offset when the popup is placed at the start.
 * @param centerPlacementOffset  The offset when the popup is placed at the center.
 * @return A pair consisting of the horizontal alignment and the horizontal offset.
 */
private fun getHorizontalOffset(
    isFitsStart: Boolean,
    isFitsEnd: Boolean,
    isFitsCenter: Boolean,
    endPlacementOffset: Int,
    startPlacementOffset: Int,
    centerPlacementOffset: Int,
): Pair<Alignment.Horizontal, Int> {

    // Check which alignment fits the best.
    val alignments = listOf(
        Alignment.Start,
        Alignment.CenterHorizontally,
        Alignment.End
    )

    // Check the corresponding offsets.
    val offsets = listOf(
        endPlacementOffset,
        centerPlacementOffset,
        startPlacementOffset
    )

    // Check which alignment and offset fits the best.
    val fallbacks = mutableListOf<Pair<Alignment.Horizontal, Int>>()
    for (index in 0..2) {
        if (listOf(isFitsEnd, isFitsCenter, isFitsStart)[index]) {
            fallbacks.add(Pair(alignments[index], offsets[index]))
        }
    }

    // If there is a fallback, choose it as the alignment and offset.
    val fallback = fallbacks.firstOrNull()
    if (fallback != null) {
        return fallback
    }

    // If there is no fallback, calculate the horizontal offset.
    val finalHorizontalFallback = if (isFitsStart) {
        0
    } else if (isFitsEnd) {
        2
    } else {
        1
    }
    val fallbackHorizontalOffset = offsets[finalHorizontalFallback]
    return Pair(alignments[finalHorizontalFallback], fallbackHorizontalOffset)
}
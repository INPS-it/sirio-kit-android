//
// SirioTabGroupCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabs

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabEdgePadding
import it.inps.sirio.theme.tabIndicatorHeight

/**
 * Sirio tab group implementation
 *
 * @param items List of tab [TabItemData] contained in group
 * @param selectedIndex The current selected index
 * @param selection A [TabSelectionIndicatorPosition] for top or bottom selection indicator
 * @param onTabSelected The tab selection callback
 */
@Composable
internal fun SirioTabGroupCommon(
    items: List<TabItemData>,
    selectedIndex: Int,
    selection: TabSelectionIndicatorPosition,
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit,
) {
    require(items.isNotEmpty()) { "TabGroup cannot be empty" }
    require(selectedIndex <= items.size) { "SelectedIndex out of items range" }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        edgePadding = tabEdgePadding,
        containerColor = if (selection == TabSelectionIndicatorPosition.TOP) Color.Transparent else Color.White,
        indicator = @Composable { tabPositions ->
            if (selection == TabSelectionIndicatorPosition.BOTTOM) {
                Box(
                    Modifier
                        .wrapContentSize(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(tabIndicatorHeight)
                        .background(color = SirioTheme.colors.tabs.selection.default)
                )
            }
            SirioTabIndicator(
                Modifier.sirioTabIndicatorOffset(selection, tabPositions[selectedIndex])
            )
        },
        divider = {}
    ) {
        items.forEachIndexed { index, item ->
            SirioTabCommon(
                label = item.label,
                icon = item.icon,
                enabled = item.enabled,
                selected = index == selectedIndex,
                selection = selection,
                onSelect = { onTabSelected(index) }
            )
        }
    }
}

@Composable
internal fun SirioTabIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(tabIndicatorHeight)
            .clip(RoundedCornerShape(corner = CornerSize(4.dp)))
            .background(color = SirioTheme.colors.tabs.selection.pressed)
    )
}

/**
 * [Modifier] that takes up all the available width inside the [TabRow], and then animates
 * the offset of the indicator it is applied to, depending on the [currentTabPosition].
 *
 * @param currentTabPosition [TabPosition] of the currently selected tab. This is used to
 * calculate the offset of the indicator this modifier is applied to, as well as its width.
 */
internal fun Modifier.sirioTabIndicatorOffset(
    selection: TabSelectionIndicatorPosition,
    currentTabPosition: TabPosition,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "tabWidth",
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "indicatorOffset",
    )
    val align =
        if (selection == TabSelectionIndicatorPosition.BOTTOM) Alignment.BottomStart
        else Alignment.TopStart
    fillMaxWidth()
        .wrapContentSize(align)
        .offset { IntOffset(indicatorOffset.roundToPx(), 0) }
        .width(currentTabWidth)
}

@Preview(showSystemUi = true)
@Composable
private fun TabsCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SirioTabGroupCommon(
                items = listOf(
                    TabItemData(
                        label = "Label tab 1",
                        icon = FaIcons.Check,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 2",
//                        icon = FaIcons.File,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 3",
                        icon = FaIcons.Globe,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 4",
                        icon = FaIcons.Lightbulb,
                        enabled = false
                    ),
                    TabItemData(
                        label = "Label tab 5",
                        icon = FaIcons.PiggyBank,
                        enabled = true
                    ),
                ),
                selection = TabSelectionIndicatorPosition.BOTTOM,
                selectedIndex = 0,
                onTabSelected = {})
            SirioTabGroupCommon(
                items = listOf(
                    TabItemData(
                        label = "Label tab 1",
                        icon = FaIcons.Check,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 2",
//                        icon = FaIcons.File,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 3",
                        icon = FaIcons.Globe,
                        enabled = true
                    ),
                    TabItemData(
                        label = "Label tab 4",
                        icon = FaIcons.Lightbulb,
                        enabled = false
                    ),
                    TabItemData(
                        label = "Label tab 5",
                        icon = FaIcons.PiggyBank,
                        enabled = true
                    ),
                ),
                selection = TabSelectionIndicatorPosition.TOP,
                selectedIndex = 0,
                onTabSelected = {})
        }
    }
}

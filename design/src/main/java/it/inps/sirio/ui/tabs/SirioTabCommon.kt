//
// SirioTabCommon.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tabs

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LeadingIconTab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.*
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio tab implementation
 *
 * @param label The tab text
 * @param icon The tab optional FA icon [FaIcons]
 * @param enabled Whether the tab can be selected by user
 * @param selected Whether the tab is the selected one
 * @param selection A [TabSelectionIndicator] for top or bottom selection indicator
 * @param onSelect The selection callback
 */
@Composable
internal fun SirioTabCommon(
    label: String,
    icon: FaIconType?,
    enabled: Boolean,
    selected: Boolean,
    selection: TabSelectionIndicator,
    onSelect: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val tabsParams = getTabsParams(enabled, isFocused, isPressed, isHovered, selected)
    val backgroundColor = when {
        selected -> SirioTheme.colors.tabs.backgroundSelected
        !enabled && selection == TabSelectionIndicator.BOTTOM -> SirioTheme.colors.tabs.backgroundBottomSelectionDisabled
        !enabled && selection == TabSelectionIndicator.TOP -> SirioTheme.colors.tabs.backgroundTopSelectionDisabled
        selection == TabSelectionIndicator.BOTTOM -> SirioTheme.colors.tabs.backgroundBottomSelection
        selection == TabSelectionIndicator.TOP -> SirioTheme.colors.tabs.backgroundTopSelection
        else -> SirioTheme.colors.tabs.backgroundSelected
    }

    Box(
        modifier = Modifier
            .height(tabHeight)
            .width(IntrinsicSize.Max),
        contentAlignment = if (selection == TabSelectionIndicator.TOP) Alignment.TopCenter else Alignment.BottomCenter
    ) {
        LeadingIconTab(
            selected = selected,
            onClick = onSelect,
            text = {
                SirioTextCommon(
                    text = label,
                    color = tabsParams.labelColor,
                    typography = if (selected) SirioTheme.typography.tabTextSelected else SirioTheme.typography.tabTextDefault
                )
            },
            icon = {
                SirioIcon(
                    faIcon = icon,
                    iconColor = tabsParams.iconColor,
                    size = tabIconSize
                )
            },
            modifier = Modifier
                .height(tabHeight)
                .wrapContentWidth()
                .background(backgroundColor),
            enabled = enabled,
            interactionSource = interactionSource,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(tabIndicatorHeight)
                .background(color = tabsParams.selectionColor)
        )
    }
}

/**
 * Tab colors based on current state
 */
@Composable
private fun getTabsParams(
    enabled: Boolean,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    isSelected: Boolean,
): TabsParams = when {
    !enabled -> TabsParams(
        labelColor = SirioTheme.colors.tabs.text.disabled,
        iconColor = SirioTheme.colors.tabs.icon.disabled,
        selectionColor = SirioTheme.colors.tabs.selection.disabled,
    )
    isFocused -> TabsParams(
        labelColor = SirioTheme.colors.tabs.text.focused,
        iconColor = SirioTheme.colors.tabs.icon.focused,
        selectionColor = SirioTheme.colors.tabs.selection.focused,
    )
    isPressed || isSelected -> TabsParams(
        labelColor = SirioTheme.colors.tabs.text.pressed,
        iconColor = SirioTheme.colors.tabs.icon.pressed,
        selectionColor = SirioTheme.colors.tabs.selection.pressed,
    )
    isHovered -> TabsParams(
        labelColor = SirioTheme.colors.tabs.text.hovered,
        iconColor = SirioTheme.colors.tabs.icon.hovered,
        selectionColor = SirioTheme.colors.tabs.selection.hovered,
    )
    else -> TabsParams(
        labelColor = SirioTheme.colors.tabs.text.default,
        iconColor = SirioTheme.colors.tabs.icon.default,
        selectionColor = SirioTheme.colors.tabs.selection.default,
    )
}

data class TabsParams(
    val labelColor: Color,
    val iconColor: Color,
    val selectionColor: Color,
)

@Keep
data class SirioTabsColors(
    var backgroundBottomSelection: Color,
    var backgroundTopSelection: Color,
    var backgroundSelected: Color,
    var backgroundBottomSelectionDisabled: Color,
    var backgroundTopSelectionDisabled: Color,
    var text: SirioColorState,
    var icon: SirioColorState,
    var selection: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioTabsColors(
            backgroundBottomSelection = Color.Unspecified,
            backgroundTopSelection = Color.Unspecified,
            backgroundSelected = Color.Unspecified,
            backgroundBottomSelectionDisabled = Color.Unspecified,
            backgroundTopSelectionDisabled = Color.Unspecified,
            text = SirioColorState.Unspecified,
            icon = SirioColorState.Unspecified,
            selection = SirioColorState.Unspecified,
        )
    }
}

/**
 * Indicate if selection is placed on top or bottom of tab item
 */
enum class TabSelectionIndicator {
    TOP,
    BOTTOM,
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
            SirioTabCommon(
                label = "Label tab 1",
                icon = FaIcons.Check,
                enabled = true,
                selected = true,
                onSelect = {},
                selection = TabSelectionIndicator.TOP,
            )
        }
    }
}

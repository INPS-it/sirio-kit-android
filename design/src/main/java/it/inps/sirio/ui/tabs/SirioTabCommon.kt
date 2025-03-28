//
// SirioTabCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
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
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabHeight
import it.inps.sirio.theme.tabHorizontalPadding
import it.inps.sirio.theme.tabIconSize
import it.inps.sirio.theme.tabWithIconHorizontalSpacing
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio tab implementation
 *
 * @param label The tab text
 * @param icon The tab optional FA icon [FaIcons]
 * @param enabled Whether the tab can be selected by user
 * @param selected Whether the tab is the selected one
 * @param selection A [TabSelectionIndicatorPosition] for top or bottom selection indicator
 * @param onSelect The selection callback
 */
@Composable
internal fun SirioTabCommon(
    label: String,
    icon: FaIconType?,
    enabled: Boolean,
    selected: Boolean,
    selection: TabSelectionIndicatorPosition,
    onSelect: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val labelColor =
        SirioTheme.colors.tabs.text.get(enabled.not(), isFocused, isPressed || selected, isHovered)
    val iconColor =
        SirioTheme.colors.tabs.icon.get(enabled.not(), isFocused, isPressed || selected, isHovered)

    val backgroundColor = when {
        selected -> SirioTheme.colors.tabs.backgroundSelected
        !enabled && selection == TabSelectionIndicatorPosition.BOTTOM -> SirioTheme.colors.tabs.backgroundBottomSelectionDisabled
        !enabled && selection == TabSelectionIndicatorPosition.TOP -> SirioTheme.colors.tabs.backgroundTopSelectionDisabled
        selection == TabSelectionIndicatorPosition.BOTTOM -> SirioTheme.colors.tabs.backgroundBottomSelection
        selection == TabSelectionIndicatorPosition.TOP -> SirioTheme.colors.tabs.backgroundTopSelection
        else -> SirioTheme.colors.tabs.backgroundSelected
    }

    Tab(
        selected = selected,
        onClick = onSelect,
        modifier = Modifier
            .height(tabHeight)
            .wrapContentWidth()
            .background(backgroundColor),
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = tabHorizontalPadding.dp),
            horizontalArrangement = Arrangement
                .spacedBy(tabWithIconHorizontalSpacing.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                SirioIcon(
                    faIcon = icon,
                    iconColor = iconColor,
                    size = tabIconSize
                )
            }
            SirioTextCommon(
                text = label,
                color = labelColor,
                typography = if (selected) SirioTheme.typography.tabTextSelected else SirioTheme.typography.tabTextDefault
            )
        }
    }
}

@Keep
data class SirioTabsColors(
    val backgroundBottomSelection: Color,
    val backgroundTopSelection: Color,
    val backgroundSelected: Color,
    val backgroundBottomSelectionDisabled: Color,
    val backgroundTopSelectionDisabled: Color,
    val text: SirioColorState,
    val icon: SirioColorState,
    val selection: SirioColorState,
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
enum class TabSelectionIndicatorPosition {
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
                selection = TabSelectionIndicatorPosition.TOP,
            )
        }
    }
}
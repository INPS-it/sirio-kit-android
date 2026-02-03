//
// SirioTabCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.tab

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.tabGroupDividerHeight
import it.inps.sirio.theme.tabHorizontalSpacing
import it.inps.sirio.theme.tabIconSize
import it.inps.sirio.theme.tabIndicatorCornerRadiusBottom
import it.inps.sirio.theme.tabIndicatorCornerRadiusTop
import it.inps.sirio.theme.tabIndicatorHeight
import it.inps.sirio.theme.tabPadding
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

/**
 * Sirio tab common implementation.
 *
 * This composable function provides the common structure and styling for a Sirio tab item.
 * It handles the display of the label, an optional icon, and the selection indicator.
 * The appearance of the tab (text color, icon color, indicator color) changes based on
 * its enabled state, selected state, and whether it's being pressed.
 *
 * @param label The text to be displayed on the tab.
 * @param icon The optional icon to be displayed on the tab. It can be a [SirioIconSource.FaIcon] or [SirioIconSource.Drawable].
 * @param enabled Whether the tab can be selected by the user. If `false`, the tab will appear disabled and cannot be interacted with.
 * @param selected Whether the tab is currently selected. This affects the visual style, such as the indicator visibility and text weight.
 * @param onSelect The callback to be invoked when the tab is clicked by the user. This is typically used to change the selected state.
 */
@Composable
internal fun SirioTabCommon(
    label: String,
    icon: SirioIconSource?,
    enabled: Boolean,
    selected: Boolean,
    onSelect: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val labelColor =
        SirioTheme.colors.tab.item.text.get(
            pressed = isPressed || selected,
            disabled = enabled.not(),
        )
    val iconColor =
        SirioTheme.colors.tab.item.icon.get(
            pressed = isPressed || selected,
            disabled = enabled.not(),
        )
    val indicatorColor =
        SirioTheme.colors.tab.item.indicator.get(
            pressed = isPressed || selected,
            disabled = enabled.not(),
        )

    Tab(
        selected = selected,
        onClick = onSelect,
        modifier = Modifier
            .background(SirioTheme.colors.tab.item.background)
            .testTag("tab${label.takeTwoWords()}"),
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        Layout(
            content = {
                Row(
                    modifier = Modifier.padding(tabPadding.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = tabHorizontalSpacing.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    icon?.let {
                        SirioIcon(
                            icon = icon,
                            iconColor = iconColor,
                            size = tabIconSize.dp
                        )
                    }
                    SirioText(
                        text = label,
                        color = labelColor,
                        typography = if (selected)
                            SirioTheme.foundationTypography.labelMdHeavy
                        else
                            SirioTheme.foundationTypography.labelMdRegular
                    )
                }

                Box(
                    modifier = Modifier
                        .height(tabIndicatorHeight.dp)
                        .background(
                            color = indicatorColor,
                            shape = RoundedCornerShape(
                                topStart = tabIndicatorCornerRadiusTop.dp,
                                topEnd = tabIndicatorCornerRadiusTop.dp,
                                bottomStart = tabIndicatorCornerRadiusBottom.dp,
                                bottomEnd = tabIndicatorCornerRadiusBottom.dp,
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .height(tabGroupDividerHeight.dp)
                        .background(SirioTheme.colors.tab.group.divider)
                )
            }
        ) { measurables, constraints ->

            val rowMeasurable = measurables[0]
            val indicatorMeasurable = measurables[1]
            val dividerMeasurable = measurables[2]

            val rowPlaceable = rowMeasurable.measure(constraints)

            val indicatorPlaceable = indicatorMeasurable.measure(
                constraints.copy(
                    minWidth = rowPlaceable.width,
                    maxWidth = rowPlaceable.width
                )
            )

            val dividerPlaceable = dividerMeasurable.measure(
                constraints.copy(
                    minWidth = rowPlaceable.width,
                    maxWidth = rowPlaceable.width
                )
            )

            val tabWidth = rowPlaceable.width
            val tabHeight = rowPlaceable.height +
                    indicatorPlaceable.height +
                    dividerPlaceable.height

            layout(tabWidth, tabHeight) {

                var y = 0

                rowPlaceable.place(0, y)
                y += rowPlaceable.height

                indicatorPlaceable.place(0, y)
                y += indicatorPlaceable.height

                dividerPlaceable.place(0, y)
            }
        }
    }
}

@Keep
data class SirioTabItemColors(
    val background: Color,
    val text: SirioColorState,
    val icon: SirioColorState,
    val indicator: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioTabItemColors(
            background = Color.Unspecified,
            text = SirioColorState.Unspecified,
            icon = SirioColorState.Unspecified,
            indicator = SirioColorState.Unspecified,
        )
    }
}

internal val tabItemLightColors = SirioTabItemColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    text = SirioColorState(
        default = FoundationColor.colorAliasInteractiveSecondaryDefault,
        pressed = FoundationColor.colorAliasInteractiveSecondaryDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    icon = SirioColorState(
        default = FoundationColor.colorAliasInteractiveSecondaryDefault,
        pressed = FoundationColor.colorAliasInteractiveSecondaryDefault,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    ),
    indicator = SirioColorState(
        default = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        pressed = FoundationColor.colorAliasInteractivePrimaryDefault,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
)

internal val tabItemDarkColors = tabItemLightColors

@Preview(showSystemUi = true)
@Composable
private fun TabsCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SirioTabCommon(
                label = "Label tab",
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                enabled = true,
                selected = false,
                onSelect = {},
            )
            SirioTabCommon(
                label = "Label tab",
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                enabled = true,
                selected = true,
                onSelect = {},
            )
            SirioTabCommon(
                label = "Label tab",
                icon = SirioIconSource.FaIcon(FaIcons.Cube),
                enabled = false,
                selected = false,
                onSelect = {},
            )
        }
    }
}
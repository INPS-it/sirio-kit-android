//
// SirioDropdownMenu.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdownmenu

import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownMenuBorderWidth
import it.inps.sirio.theme.dropdownMenuCornerSize
import it.inps.sirio.theme.dropdownMenuItemHeight
import it.inps.sirio.theme.dropdownMenuVisibleitems
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.utils.SirioIconSource
import kotlin.math.min

/**
 * A dropdown menu with an icon button to view more actions that can be done.
 * The button shows the ellipsis icon if the text is null, otherwise it shows the text and the chevron down icon.
 * The dropdown menu is placed on the top of the button.
 *
 * @param text The optional text to display on the button. If null, only an ellipsis icon is shown.
 * @param hierarchy The [SirioButtonHierarchy] of the button.
 * @param items The list of [SirioDropdownItemData] with actions.
 * @see SirioDropdownMenu
 */
@Composable
fun SirioMoreAction(
    text: String? = null,
    isTop: Boolean = true,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    hierarchy: SirioButtonHierarchy = SirioButtonHierarchy.Primary,
    offset: DpOffset = DpOffset(0.dp, 8.dp),
    items: List<SirioDropdownItemData>,
) {
    val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
    sirioPopupState.isTop = isTop
    sirioPopupState.horizontalAlignment = horizontalAlignment
    val icon = if (text == null) SirioIconSource.FaIcon(FaIcons.EllipsisV)
    else SirioIconSource.FaIcon(FaIcons.ChevronDown)
    SirioButton(
        size = SirioButtonSize.Medium,
        hierarchy = hierarchy,
        text = text,
        icon = icon,
    ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
    SirioPopup(
        state = sirioPopupState,
        onDismissRequest = { sirioPopupState.isVisible = false },
        offset = offset,
    ) {
        SirioDropdownMenuContent(items = items)
    }
}

/**
 * A Sirio dropdown menu. A list of items that can be opened and closed by the caller.
 * The menu is placed in a [SirioPopup].
 * @param popupState The [SirioPopupState] to control the menu visibility and position.
 * @param onDismissRequest Callback called when the user requests to dismiss the menu, such as by tapping outside the menu or pressing the back button.
 * @param items The list of [SirioDropdownItemData] to show in the menu.
 */
@Composable
fun SirioDropdownMenu(
    popupState: SirioPopupState,
    offset: DpOffset = DpOffset(0.dp, 8.dp),
    onDismissRequest: () -> Unit,
    items: List<SirioDropdownItemData>,
) {
    SirioPopup(
        state = popupState,
        onDismissRequest = onDismissRequest,
        offset = offset,
    ) {
        SirioDropdownMenuContent(items = items)
    }
}

/**
 * The content of the Sirio dropdown menu
 *
 * @param items The list of [SirioDropdownItemData] to show in the menu.
 */
@Composable
fun SirioDropdownMenuContent(
    items: List<SirioDropdownItemData>,
) {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(dropdownMenuCornerSize.dp),
        border = BorderStroke(
            width = dropdownMenuBorderWidth.dp,
            color = SirioTheme.colors.dropdownMenu.border,
        )
    ) {
        val height = min(items.count(), dropdownMenuVisibleitems) * dropdownMenuItemHeight
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(height.dp)
                .background(SirioTheme.colors.dropdownMenu.background)
                .verticalScroll(rememberScrollState()),
        ) {
            items.forEachIndexed { index, item ->
                SirioDropdownMenuItem(item)
                if (index < items.lastIndex) {
                    HorizontalDivider(
                        thickness = dropdownMenuBorderWidth.dp,
                        color = SirioTheme.colors.dropdownMenu.border,
                    )
                }
            }
        }
    }
}

/**
 * The set of colors for the [SirioDropdownMenu]
 *
 * @param border The border color
 * @param item The colors of items, see [SirioDropdownMenuItemColors]
 */
@Keep
data class SirioDropdownMenuColors(
    val background: Color,
    val border: Color,
    val item: SirioDropdownMenuItemColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownMenuColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            item = SirioDropdownMenuItemColors.Unspecified,
        )
    }
}

/**
 * The light colors for the [SirioDropdownMenu]
 */
internal val dropdownMenuLightColors = SirioDropdownMenuColors(
    background = FoundationColor.colorAliasBackgroundColorSecondaryDark110,
    border = FoundationColor.colorAliasBackgroundColorSecondaryDark110,
    item = dropdownMenuItemLightColors,
)

/**
 * The dark colors for the [SirioDropdownMenu]
 */
internal val dropdownMenuDarkColors = SirioDropdownMenuColors(
    background = FoundationColor.colorAliasBackgroundColorSecondaryDark110,
    border = FoundationColor.colorAliasBackgroundColorSecondaryDark110,
    item = dropdownMenuItemDarkColors,
)

@Preview(showSystemUi = false)
@Composable
private fun SirioDropdownMenuPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                Modifier.weight(1f)
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomEnd)
                ) {
                    val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                    sirioPopupState.isTop = true
                    sirioPopupState.horizontalAlignment = Alignment.Start
                    SirioButton(
                        icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                        size = SirioButtonSize.Medium,
                        hierarchy = SirioButtonHierarchy.Primary,
                    ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                    SirioDropdownMenu(
                        popupState = sirioPopupState,
                        onDismissRequest = { sirioPopupState.isVisible = false },
                        offset = DpOffset((-4).dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomStart)
                ) {
                    val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                    sirioPopupState.isTop = true
                    sirioPopupState.horizontalAlignment = Alignment.End
                    SirioButton(
                        icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                        size = SirioButtonSize.Medium,
                        hierarchy = SirioButtonHierarchy.Primary,
                    ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                    SirioDropdownMenu(
                        popupState = sirioPopupState,
                        onDismissRequest = { sirioPopupState.isVisible = false },
                        offset = DpOffset(4.dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        )
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.TopEnd)
                ) {
                    val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                    sirioPopupState.isTop = false
                    sirioPopupState.horizontalAlignment = Alignment.Start
                    SirioButton(
                        icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                        size = SirioButtonSize.Medium,
                        hierarchy = SirioButtonHierarchy.Primary,
                    ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                    SirioDropdownMenu(
                        popupState = sirioPopupState,
                        onDismissRequest = { sirioPopupState.isVisible = false },
                        offset = DpOffset((-4).dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        )
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.TopStart)
                ) {
                    val sirioPopupState: SirioPopupState = remember { SirioPopupState(false) }
                    sirioPopupState.isTop = false
                    sirioPopupState.horizontalAlignment = Alignment.End
                    SirioButton(
                        icon = SirioIconSource.FaIcon(FaIcons.EllipsisV),
                        size = SirioButtonSize.Medium,
                        hierarchy = SirioButtonHierarchy.Primary,
                    ) { sirioPopupState.isVisible = sirioPopupState.isVisible.not() }
                    SirioDropdownMenu(
                        popupState = sirioPopupState,
                        onDismissRequest = { sirioPopupState.isVisible = false },
                        offset = DpOffset(4.dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        )
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
private fun SirioMoreActionPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                Modifier.weight(1f)
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomEnd)
                ) {
                    SirioMoreAction(
                        offset = DpOffset((-4).dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomStart)
                ) {
                    SirioMoreAction(
                        isTop = true,
                        horizontalAlignment = Alignment.End,
                        offset = DpOffset(4.dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.TopEnd)
                ) {
                    SirioMoreAction(
                        isTop = false,
                        horizontalAlignment = Alignment.Start,
                        offset = DpOffset((-4).dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.TopStart)
                ) {
                    SirioMoreAction(
                        isTop = false,
                        horizontalAlignment = Alignment.End,
                        offset = DpOffset(4.dp, 8.dp),
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
private fun SirioMoreActionTextPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomEnd)
                ) {
                    SirioMoreAction(
                        text = "Text",
                        isTop = true,
                        horizontalAlignment = Alignment.Start,
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomStart)
                ) {
                    SirioMoreAction(
                        text = "Text",
                        isTop = true,
                        horizontalAlignment = Alignment.End,
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.TopEnd)
                ) {
                    SirioMoreAction(
                        text = "Text",
                        isTop = false,
                        horizontalAlignment = Alignment.Start,
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.TopStart)
                ) {
                    SirioMoreAction(
                        text = "Text",
                        isTop = false,
                        horizontalAlignment = Alignment.End,
                        items = listOf(
                            SirioDropdownItemData("Action #1") {},
                            SirioDropdownItemData("Action #2") {},
                            SirioDropdownItemData("Action #3") {},
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SirioDropdownMenuContentPreview() {
    SirioTheme {
        SirioDropdownMenuContent(
            items = listOf(
                SirioDropdownItemData("Action #1") {},
                SirioDropdownItemData("Action #2") {},
                SirioDropdownItemData("Action #3") {},
                SirioDropdownItemData("Action #4") {},
            )
        )
    }
}

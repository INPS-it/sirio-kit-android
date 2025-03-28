//
// SirioDropdownMenu.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdownmenu

import androidx.annotation.Keep
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownMenuBorderWidth
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize

@Composable
fun SirioDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    scrollState: ScrollState = rememberScrollState(),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
            .crop(vertical = 8.dp)
            .border(
                width = dropdownMenuBorderWidth.dp,
                color = SirioTheme.colors.dropdownMenu.option.border.default,
                shape = RoundedCornerShape(4.dp)
            )
//            .clip(RoundedCornerShape(4.dp))
        ,
        offset = offset,
        scrollState = scrollState,
        properties = properties,
        content = content
    )
}

fun Modifier.crop(
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp,
): Modifier = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    fun Dp.toPxInt(): Int = this.toPx().toInt()

    layout(
        placeable.width - (horizontal * 2).toPxInt(),
        placeable.height - (vertical * 2).toPxInt()
    ) {
        placeable.placeRelative(-horizontal.toPx().toInt(), -vertical.toPx().toInt())
    }
}

@Keep
data class SirioDropdownMenuColors(
    val option: SirioDropdownMenuOptionColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownMenuColors(
            option = SirioDropdownMenuOptionColors.Unspecified,
        )
    }
}

@Keep
data class SirioDropdownMenuTypography(
    val option: SirioDropdownMenuOptionTypography,
) {
    companion object {
        @Stable
        val Default = SirioDropdownMenuTypography(
            option = SirioDropdownMenuOptionTypography.Default,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SirioDropdownMenuPreview() {
    SirioTheme {
        var show by remember { mutableStateOf(false) }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(Modifier.wrapContentSize(align = Alignment.TopEnd)) {
                SirioButton(text = "Show", size = SirioButtonSize.Large, style = ButtonStyle.Primary) {
                    show = true
                }
                SirioDropdownMenu(show, { show = false }) {
                    val text = "Option Value"
                    SirioDropdownMenuOptionItem(
                        text = text,
                        selected = false,
                        enabled = true,
                        onCLick = {})
                    SirioDropdownMenuOptionItem(
                        text = text,
                        selected = true,
                        enabled = true,
                        onCLick = {})
                    SirioDropdownMenuOptionItem(
                        text = text,
                        selected = false,
                        enabled = false,
                        onCLick = {})
                    SirioDropdownMenuOptionItem(
                        text = text,
                        selected = true,
                        enabled = false,
                        onCLick = {})
                }
            }
        }
    }
}
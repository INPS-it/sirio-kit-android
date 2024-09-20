//
// SirioDropdown.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.dropdown

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
import it.inps.sirio.theme.dropdownBorderWidth
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton

@Composable
fun SirioDropdown(
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
                width = dropdownBorderWidth.dp,
                color = SirioTheme.colors.dropdown.option.border.default,
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
data class SirioDropdownColors(
    val option: SirioDropdownOptionColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownColors(
            option = SirioDropdownOptionColors.Unspecified,
        )
    }
}

@Keep
data class SirioDropdownTypography(
    val option: SirioDropdownOptionTypography,
) {
    companion object {
        @Stable
        val Default = SirioDropdownTypography(
            option = SirioDropdownOptionTypography.Default,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SirioDropdownPreview() {
    SirioTheme {
        var show by remember { mutableStateOf(false) }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(Modifier.wrapContentSize(align = Alignment.TopEnd)) {
                SirioButton(text = "Show", size = ButtonSize.Large, style = ButtonStyle.Primary) {
                    show = true
                }
                SirioDropdown(show, { show = false }) {
                    val text = "Option Value"
                    SirioDropdownOptionItem(
                        text = text,
                        selected = false,
                        enabled = true,
                        onCLick = {})
                    SirioDropdownOptionItem(
                        text = text,
                        selected = true,
                        enabled = true,
                        onCLick = {})
                    SirioDropdownOptionItem(
                        text = text,
                        selected = false,
                        enabled = false,
                        onCLick = {})
                    SirioDropdownOptionItem(
                        text = text,
                        selected = true,
                        enabled = false,
                        onCLick = {})
                }
            }
        }
    }
}
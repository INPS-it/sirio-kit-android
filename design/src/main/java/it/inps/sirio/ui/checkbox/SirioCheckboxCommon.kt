//
// SirioCheckboxCommon.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.*
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * Sirio checkbox implementation
 *
 * @param text The string on the checkbox right
 * @param checked Whether the checkbox is checked
 * @param enabled Whether the checkbox is enabled
 * @param onCheckedChange The callback when the checkbox state change
 */
@Composable
internal fun SirioCheckboxCommon(
    text: String? = null,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val borderColor = if (!enabled) SirioTheme.colors.checkboxDisabled else when {
        isFocused -> SirioTheme.colors.checkboxFocusContent
        isPressed -> SirioTheme.colors.checkboxPressed
        isHovered -> SirioTheme.colors.checkboxHoverContent
        else -> SirioTheme.colors.checkboxPressed
    }
    val textColor =
        if (!enabled) SirioTheme.colors.checkboxDisabledContent else if (checked) SirioTheme.colors.checkboxPressedText else borderColor
    val checkColor =
        if (enabled) SirioTheme.colors.checkboxPressed else SirioTheme.colors.checkboxDisabledContent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .focusable(enabled = enabled, interactionSource = interactionSource)
            .toggleable(
                value = checked,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Checkbox,
                enabled = enabled
            ) {
                onCheckedChange(it)
            }
    )
    {
        CustomCheckbox(
            checked = checked,
            checkColor = checkColor,
            isFocused = isFocused,
            borderColor = borderColor
        )
        text?.let {
            SirioTextCommon(
                text = it,
                color = textColor,
                typography = SirioTheme.typography.checkboxLabelText,
            )
        }
    }
}

/**
 * The custom checkbox
 *
 * @param checked Whether the check is checked
 * @param isFocused Whether the check is focused
 * @param checkColor The color of the inner check
 * @param borderColor The color of the border
 */
@Composable
private fun CustomCheckbox(
    checked: Boolean,
    isFocused: Boolean,
    checkColor: Color,
    borderColor: Color,
) {
    val focusPadding = remember {
        checkboxFocusBorderPadding + checkboxFocusExtraBorderWidth
    }

    val size = Modifier
        .padding(checkboxSafeAreaPadding)
        .size(checkboxSize)
    Box(
        modifier = if (isFocused) {
            size
                .border(
                    checkboxFocusExtraBorderWidth,
                    color = SirioTheme.colors.checkboxFocusBorder,
                    shape = RoundedCornerShape(checkboxCornerRadius),
                )
                .padding(focusPadding)
        } else size,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(checkboxCornerRadius))
                .fillMaxSize()
                .border(
                    width = checkboxBorderWidth,
                    color = borderColor,
                    shape = RoundedCornerShape(checkboxCornerRadius)
                )
                .background(SirioTheme.colors.checkboxBackground),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                SirioIcon(
                    faIcon = FaIcons.Check,
                    size = checkboxCheckSize,
                    iconColor = checkColor,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CheckboxCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color(0xFFE5E5E5))) {
            val text = "Title"
            SirioCheckboxCommon(checked = false, onCheckedChange = {}, enabled = true)
            SirioCheckboxCommon(checked = true, onCheckedChange = {}, enabled = true)
            SirioCheckboxCommon(checked = false, onCheckedChange = {}, enabled = false)
            SirioCheckboxCommon(checked = true, onCheckedChange = {}, enabled = false)
            SirioCheckboxCommon(text = text, checked = false, onCheckedChange = {}, enabled = true)
            SirioCheckboxCommon(text = text, checked = true, onCheckedChange = {}, enabled = true)
            SirioCheckboxCommon(text = text, checked = false, onCheckedChange = {}, enabled = false)
            SirioCheckboxCommon(text = text, checked = true, onCheckedChange = {}, enabled = false)
        }
    }
}
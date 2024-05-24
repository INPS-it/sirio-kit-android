//
// SirioCheckboxCommon.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.checkbox

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.checkboxBorderWidth
import it.inps.sirio.theme.checkboxCheckSize
import it.inps.sirio.theme.checkboxCornerRadius
import it.inps.sirio.theme.checkboxFocusBorderPadding
import it.inps.sirio.theme.checkboxFocusExtraBorderWidth
import it.inps.sirio.theme.checkboxPaddingText
import it.inps.sirio.theme.checkboxSize
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
    checked: Boolean,
    text: String? = null,
    enabled: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxWithText(
        checked = checked,
        text = text,
        enabled = enabled,
        overflow = overflow,
        maxLines = maxLines,
        onCheckedChange = onCheckedChange,
    )
}

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
    checked: Boolean,
    text: AnnotatedString,
    enabled: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxWithText(
        checked = checked,
        annotatedText = text,
        enabled = enabled,
        overflow = overflow,
        maxLines = maxLines,
        onCheckedChange = onCheckedChange,
    )
}

@Composable
private fun SirioCheckboxWithText(
    checked: Boolean,
    text: String? = null,
    annotatedText: AnnotatedString? = null,
    enabled: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onCheckedChange: (Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor =
        SirioTheme.colors.checkbox.background.get(enabled.not(), isFocused, isPressed, isHovered)
    val borderColor =
        SirioTheme.colors.checkbox.border.get(enabled.not(), isFocused, isPressed, isHovered)
    val textColor =
        SirioTheme.colors.checkbox.text.get(enabled.not(), isFocused, isPressed, isHovered)
    val checkColor =
        SirioTheme.colors.checkbox.check.get(enabled.not(), isFocused, isPressed, isHovered)

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
            backgroundColor = backgroundColor,
            checked = checked,
            checkColor = checkColor,
            isFocused = isFocused,
            borderColor = borderColor
        )
        text?.let {
            Spacer(modifier = Modifier.width(checkboxPaddingText.dp))
            SirioTextCommon(
                text = it,
                color = textColor,
                overflow = overflow,
                maxLines = maxLines,
                typography = SirioTheme.typography.checkbox.text,
            )
        } ?: annotatedText?.let {
            SirioTextCommon(
                text = it,
                color = textColor,
                overflow = overflow,
                maxLines = maxLines,
                typography = SirioTheme.typography.checkbox.text,
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
    backgroundColor: Color,
    checked: Boolean,
    isFocused: Boolean,
    checkColor: Color,
    borderColor: Color,
) {
    val focusPadding = remember {
        checkboxFocusBorderPadding + checkboxFocusExtraBorderWidth
    }

    val size = Modifier
        .size(checkboxSize.dp)
        .minimumInteractiveComponentSize()
    Box(
        modifier = if (isFocused) {
            size
                .border(
                    checkboxFocusExtraBorderWidth.dp,
                    color = SirioTheme.colors.checkbox.borderFocusExtra,
                    shape = RoundedCornerShape(checkboxCornerRadius.dp),
                )
                .padding(focusPadding.dp)
        } else size,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(checkboxCornerRadius.dp))
                .fillMaxSize()
                .border(
                    width = checkboxBorderWidth.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(checkboxCornerRadius.dp)
                )
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                SirioIcon(
                    faIcon = FaIcons.Check,
                    size = checkboxCheckSize.dp,
                    iconColor = checkColor,
                )
            }
        }
    }
}

@Keep
data class SirioCheckboxColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val borderFocusExtra: Color,
    val check: SirioColorState,
    val text: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioCheckboxColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            borderFocusExtra = Color.Unspecified,
            check = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
        )
    }
}

@Keep
data class SirioCheckboxTypography(
    val text: TextStyle,
) {
    companion object {
        @Stable
        val Default = SirioCheckboxTypography(
            text = TextStyle.Default,
        )
    }
}

@Preview
@Composable
private fun CheckboxCommonPreview() {
    SirioTheme {
        Column(Modifier.background(Color.White)) {
            val text = "Title"
            SirioCheckboxCommon(checked = false, enabled = true) {}
            SirioCheckboxCommon(checked = true, enabled = true) {}
            SirioCheckboxCommon(checked = false, enabled = false) {}
            SirioCheckboxCommon(checked = true, enabled = false) {}
            SirioCheckboxCommon(checked = false, text = text, enabled = true) {}
            SirioCheckboxCommon(checked = true, text = text, enabled = true) {}
            SirioCheckboxCommon(checked = false, text = text, enabled = false) {}
            SirioCheckboxCommon(checked = true, text = text, enabled = false) {}
        }
    }
}

@Preview
@Composable
private fun SirioCustomCheckboxPreview() {
    SirioTheme {
        Column {
        }
    }
}

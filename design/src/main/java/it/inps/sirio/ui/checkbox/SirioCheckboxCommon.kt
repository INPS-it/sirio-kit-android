//
// SirioCheckboxCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.checkbox

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.checkboxBorderWidth
import it.inps.sirio.theme.checkboxCheckSize
import it.inps.sirio.theme.checkboxCornerRadius
import it.inps.sirio.theme.checkboxPaddingText
import it.inps.sirio.theme.checkboxSize
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

/**
 * Sirio checkbox implementation
 *
 * @param text The string on the checkbox right
 * @param modifier the Modifier to be applied to this checkbox
 * @param checked Whether the checkbox is checked
 * @param enabled Whether the checkbox is enabled
 * @param onCheckedChange The callback when the checkbox state change
 */
@Composable
internal fun SirioCheckboxCommon(
    checked: Boolean,
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxWithText(
        checked = checked,
        modifier = modifier,
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
 * @param modifier the Modifier to be applied to this checkbox
 * @param checked Whether the checkbox is checked
 * @param enabled Whether the checkbox is enabled
 * @param onCheckedChange The callback when the checkbox state change
 */
@Composable
internal fun SirioCheckboxCommon(
    checked: Boolean,
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onCheckedChange: (Boolean) -> Unit,
) {
    SirioCheckboxWithText(
        checked = checked,
        modifier = modifier,
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
    modifier: Modifier = Modifier,
    text: String? = null,
    annotatedText: AnnotatedString? = null,
    enabled: Boolean = true,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onCheckedChange: (Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val backgroundColor = SirioTheme.colors.checkbox.background.get(disabled = enabled.not())
    val borderColor = SirioTheme.colors.checkbox.border.get(disabled = enabled.not())
    val textColor = SirioTheme.colors.checkbox.label.get(disabled = enabled.not())
    val checkColor = SirioTheme.colors.checkbox.check.get(disabled = enabled.not())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .testTag(
                "checkbox${
                    if (!text.isNullOrEmpty()) text.takeTwoWords()
                    else (annotatedText ?: "").toString().takeTwoWords()
                }"
            )
            .toggleable(
                value = checked,
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Checkbox,
            ) {
                onCheckedChange(it)
            },
    )
    {
        CustomCheckbox(
            backgroundColor = backgroundColor,
            checked = checked,
            checkColor = checkColor,
            borderColor = borderColor
        )
        text?.let {
            Spacer(modifier = Modifier.width(checkboxPaddingText.dp))
            SirioTextCommon(
                text = it,
                color = textColor,
                overflow = overflow,
                maxLines = maxLines,
                typography = SirioTheme.foundationTypography.labelMdRegular,
            )
        } ?: annotatedText?.let {
            Spacer(modifier = Modifier.width(checkboxPaddingText.dp))
            SirioTextCommon(
                text = it,
                color = textColor,
                overflow = overflow,
                maxLines = maxLines,
                typography = SirioTheme.foundationTypography.labelMdRegular,
            )
        }
    }
}


/**
 * The custom checkbox
 *
 * @param checked Whether the check is checked
 * @param checkColor The color of the inner check
 * @param borderColor The color of the border
 */
@Composable
private fun CustomCheckbox(
    backgroundColor: Color,
    checked: Boolean,
    checkColor: Color,
    borderColor: Color,
) {
    Box(
        modifier = Modifier
            .size(checkboxSize.dp)
            .clip(RoundedCornerShape(checkboxCornerRadius.dp))
            .border(
                width = checkboxBorderWidth.dp,
                color = borderColor,
                shape = RoundedCornerShape(checkboxCornerRadius.dp)
            )
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            SirioIcon(
                icon = SirioIconSource.FaIcon(FaIcons.Check),
                size = checkboxCheckSize.dp,
                iconColor = checkColor,
            )
        }
    }
}

@Keep
data class SirioCheckboxColors(
    val background: SirioColorState,
    val border: SirioColorState,
    val check: SirioColorState,
    val label: SirioColorState,
) {
    companion object {
        @Stable
        val Unspecified = SirioCheckboxColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            check = SirioColorState.Unspecified,
            label = SirioColorState.Unspecified,
        )
    }
}

internal val checkboxLightColors = SirioCheckboxColors(
    background = SirioColorState.all(
        color = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    border = SirioColorState.all(
        color = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    check = SirioColorState.all(color = FoundationColor.colorAliasInteractiveSecondaryDefault),
    label = SirioColorState.all(color = FoundationColor.colorSpecificDataEntryLabelColorDefault),
)

internal val checkboxDarkColors = checkboxLightColors

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
            SirioCheckboxCommon(
                checked = false,
                modifier = Modifier.fillMaxWidth(),
                text = text,
                enabled = true
            ) {}
            SirioCheckboxCommon(checked = true, text = text, enabled = true) {}
            SirioCheckboxCommon(checked = false, text = text, enabled = false) {}
            SirioCheckboxCommon(checked = true, text = text, enabled = false) {}
        }
    }
}

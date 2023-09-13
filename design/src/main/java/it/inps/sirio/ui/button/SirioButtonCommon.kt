//
// ButtonCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.button

import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.Shapes
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.buttonBorderWidth
import it.inps.sirio.theme.buttonCornerRadius
import it.inps.sirio.theme.buttonFocusedBorderPadding
import it.inps.sirio.theme.buttonFocusedExtraBorderWidth
import it.inps.sirio.theme.buttonIconSize
import it.inps.sirio.theme.buttonLargeWithTextHorizontalPadding
import it.inps.sirio.theme.buttonLargeWithTextVerticalPadding
import it.inps.sirio.theme.buttonLargeWithoutTextHorizontalPadding
import it.inps.sirio.theme.buttonLargeWithoutTextVerticalPadding
import it.inps.sirio.theme.buttonMediumWithTextHorizontalPadding
import it.inps.sirio.theme.buttonMediumWithTextVerticalPadding
import it.inps.sirio.theme.buttonMediumWithoutTextHorizontalPadding
import it.inps.sirio.theme.buttonMediumWithoutTextVerticalPadding
import it.inps.sirio.theme.buttonSmallWithTextHorizontalPadding
import it.inps.sirio.theme.buttonSmallWithTextVerticalPadding
import it.inps.sirio.theme.buttonSmallWithoutTextHorizontalPadding
import it.inps.sirio.theme.buttonSmallWithoutTextVerticalPadding
import it.inps.sirio.theme.buttonTextIconSpacerWidth
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon

/**
 * The Sirio Button common component
 *
 * @param size The [ButtonSize]
 * @param colors The colors depends on [ButtonStyle]
 * @param modifier the Modifier to be applied to this button
 * @param text The string on the button
 * @param faIcon The font awesome icon
 * @param iconResId Resources object to query the image file from
 * @param enabled Whether the button is enabled
 * @param iconContentDescription The content description for the icon
 * @param onClick The callback when the button is clicked
 */
@Composable
internal fun SirioButtonCommon(
    size: ButtonSize,
    colors: SirioButtonColors,
    modifier: Modifier = Modifier,
    text: String? = null,
    faIcon: FaIconType? = null,
    @DrawableRes iconResId: Int? = null,
    enabled: Boolean = true,
    iconContentDescription: String? = null,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val buttonParams = getButtonParams(enabled, colors, isFocused, isPressed, isHovered)

    Box(
        modifier = if (isFocused) {
            modifier
                .border(
                    width = buttonFocusedExtraBorderWidth,
                    color = SirioTheme.colors.buttons.focusExtraBorder,
                    shape = Shapes.small,
                )
                .padding(buttonFocusedBorderPadding)
        } else {
            modifier
        },
    ) {
        OutlinedButton(
            onClick = {
                onClick()
            },
            modifier = modifier.focusable(true, interactionSource = interactionSource),
            enabled = enabled,
            interactionSource = interactionSource,
            shape = RoundedCornerShape(buttonCornerRadius),
            border = BorderStroke(width = buttonBorderWidth, color = buttonParams.borderColor),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = buttonParams.backgroundColor,
                disabledContainerColor = buttonParams.backgroundColor,
            ),
            contentPadding = getContentPadding(size, isFocused, text.isNullOrEmpty()),
            content = {
                ButtonContent(
                    text = text,
                    textColor = buttonParams.textColor,
                    icon = faIcon,
                    iconResId = iconResId,
                    iconColor = buttonParams.iconColor,
                    iconContentDescription = iconContentDescription,
                )
            }
        )
    }
}

/**
 * The button colors
 *
 * @param enabled Whether the button is enabled
 * @param colors The colors depends on [ButtonStyle]
 * @param isFocused Whether the button is focused
 * @param isPressed Whether the button is pressed
 * @param isHovered Whether the button is hovered
 */
@Composable
private fun getButtonParams(
    enabled: Boolean,
    colors: SirioButtonColors,
    isFocused: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
): ButtonParams = when {
    !enabled -> ButtonParams(
        backgroundColor = colors.background.disabled,
        borderColor = colors.border?.disabled ?: colors.background.disabled,
        textColor = colors.text.disabled,
        iconColor = colors.icon.disabled,
    )

    isFocused -> ButtonParams(
        backgroundColor = colors.background.focused,
        borderColor = colors.border?.focused ?: colors.background.focused,
        textColor = colors.text.focused,
        iconColor = colors.icon.focused,
    )

    isPressed -> ButtonParams(
        backgroundColor = colors.background.pressed,
        borderColor = colors.border?.pressed ?: colors.background.pressed,
        textColor = colors.text.pressed,
        iconColor = colors.icon.pressed,
    )

    isHovered -> ButtonParams(
        backgroundColor = colors.background.hovered,
        borderColor = colors.border?.hovered ?: colors.background.hovered,
        textColor = colors.text.hovered,
        iconColor = colors.icon.hovered,
    )

    else -> ButtonParams(
        backgroundColor = colors.background.default,
        borderColor = colors.border?.default ?: colors.background.default,
        textColor = colors.text.default,
        iconColor = colors.icon.default,
    )
}

/**
 * The internal padding depends on size, focused state and text in button
 *
 * @param size The [ButtonSize]
 * @param isFocused Whether the button has focus
 * @param noText Whether the button is only icon
 */
@Composable
private fun getContentPadding(
    size: ButtonSize,
    isFocused: Boolean,
    noText: Boolean,
): PaddingValues =
    when (size) {
        ButtonSize.Large -> {
            if (isFocused)
                if (noText) PaddingValues(
                    horizontal = buttonLargeWithoutTextHorizontalPadding - buttonFocusedBorderPadding,
                    vertical = buttonLargeWithoutTextVerticalPadding - buttonFocusedBorderPadding,
                ) else PaddingValues(
                    horizontal = buttonLargeWithTextHorizontalPadding - buttonFocusedBorderPadding,
                    vertical = buttonLargeWithTextVerticalPadding - buttonFocusedBorderPadding,
                )
            else {
                if (noText) PaddingValues(
                    horizontal = buttonLargeWithoutTextHorizontalPadding,
                    vertical = buttonLargeWithoutTextVerticalPadding
                ) else PaddingValues(
                    horizontal = buttonLargeWithTextHorizontalPadding,
                    vertical = buttonLargeWithTextVerticalPadding
                )
            }
        }

        ButtonSize.Medium -> {
            if (isFocused)
                if (noText) PaddingValues(
                    horizontal = buttonMediumWithoutTextHorizontalPadding - buttonFocusedBorderPadding,
                    vertical = buttonMediumWithoutTextVerticalPadding - buttonFocusedBorderPadding,
                ) else PaddingValues(
                    horizontal = buttonMediumWithTextHorizontalPadding - buttonFocusedBorderPadding,
                    vertical = buttonMediumWithTextVerticalPadding - buttonFocusedBorderPadding,
                )
            else {
                if (noText) PaddingValues(
                    horizontal = buttonMediumWithoutTextHorizontalPadding,
                    vertical = buttonMediumWithoutTextVerticalPadding
                ) else PaddingValues(
                    horizontal = buttonMediumWithTextHorizontalPadding,
                    vertical = buttonMediumWithTextVerticalPadding
                )
            }
        }

        ButtonSize.Small -> {
            if (isFocused)
                if (noText) PaddingValues(
                    horizontal = buttonSmallWithoutTextHorizontalPadding - buttonFocusedBorderPadding,
                    vertical = buttonSmallWithoutTextVerticalPadding - buttonFocusedBorderPadding,
                ) else PaddingValues(
                    horizontal = buttonSmallWithTextHorizontalPadding - buttonFocusedBorderPadding,
                    vertical = buttonSmallWithTextVerticalPadding - buttonFocusedBorderPadding,
                )
            else {
                if (noText) PaddingValues(
                    horizontal = buttonSmallWithoutTextHorizontalPadding,
                    vertical = buttonSmallWithoutTextVerticalPadding
                ) else PaddingValues(
                    horizontal = buttonSmallWithTextHorizontalPadding,
                    vertical = buttonSmallWithTextVerticalPadding
                )
            }
        }
    }

/**
 * The button content in row
 *
 * @param text The string in the button
 * @param textColor The text color
 * @param icon The FA icon
 * @param iconResId Resources object to query the image file from
 * @param iconColor The icon color
 * @param iconContentDescription The content description for the icon
 */
@Composable
private fun ButtonContent(
    text: String?,
    textColor: Color,
    icon: FaIconType?,
    @DrawableRes iconResId: Int?,
    iconColor: Color,
    iconContentDescription: String? = null,
) {
    val withIcon = icon != null || iconResId != null
    if (!text.isNullOrEmpty()) {
        SirioTextCommon(
            text = text,
            color = textColor,
            typography = SirioTheme.typography.buttonText,
        )
    }
    if (!text.isNullOrEmpty() && withIcon) {
        Spacer(Modifier.requiredWidth(buttonTextIconSpacerWidth))
    }
    if (withIcon) {
        SirioIcon(
            faIcon = icon,
            iconResId = iconResId,
            iconColor = iconColor,
            size = buttonIconSize,
            contentDescription = iconContentDescription,
        )
    }
}

data class ButtonParams(
    val backgroundColor: Color,
    val textColor: Color,
    val iconColor: Color,
    val borderColor: Color,
)

@Keep
data class SirioButtonColors(
    var background: SirioColorState,
    var border: SirioColorState? = null,
    var icon: SirioColorState,
    var text: SirioColorState,
) {
    constructor(
        background: SirioColorState,
        border: SirioColorState? = null,
        content: SirioColorState,
    ) : this(background = background, border = border, icon = content, text = content)

    companion object {
        @Stable
        val Unspecified = SirioButtonColors(
            background = SirioColorState.Unspecified,
            border = SirioColorState.Unspecified,
            icon = SirioColorState.Unspecified,
            text = SirioColorState.Unspecified,
        )
    }
}

/**
 * Button style, when Dark/Light is provided it force the related theme, device theme is used otherwise
 */
enum class ButtonStyle {
    Primary,
    Secondary,
    Tertiary,
    TertiaryDark,
    TertiaryLight,
    Ghost,
    Danger,
}

enum class ButtonSize {
    Large,
    Medium,
    Small,
}

@Preview(showSystemUi = true, heightDp = 2000)
@Composable
private fun ButtonCommonPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
                .verticalScroll(rememberScrollState())
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ButtonCommonPreviewContent(size = ButtonSize.Large)
            ButtonCommonPreviewContent(size = ButtonSize.Medium)
            ButtonCommonPreviewContent(size = ButtonSize.Small)

        }
    }
}

@Composable
private fun ButtonCommonPreviewContent(size: ButtonSize) {
    val text = "Text"
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SirioButtonCommon(
            text = text,
            colors = SirioTheme.colors.buttons.primary,
            size = size,
        ) {}
        SirioButtonCommon(
            text = text,
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.primary,
            size = size,
        ) {}
        SirioButtonCommon(
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.primary,
            size = size,
        ) {}
    }
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SirioButtonCommon(
            text = text,
            colors = SirioTheme.colors.buttons.primary,
            enabled = false,
            size = size,
        ) {}
        SirioButtonCommon(
            text = text,
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.primary,
            enabled = false,
            size = size,
        ) {}
        SirioButtonCommon(
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.primary,
            enabled = false,
            size = size,
        ) {}
    }
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SirioButtonCommon(
            text = text,
            colors = SirioTheme.colors.buttons.tertiary,
            size = size,
        ) {}
        SirioButtonCommon(
            text = text,
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.tertiary,
            size = size,
        ) {}
        SirioButtonCommon(
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.tertiary,
            size = size,
        ) {}
    }
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SirioButtonCommon(
            text = text,
            colors = SirioTheme.colors.buttons.tertiary,
            enabled = false,
            size = size,
        ) {}
        SirioButtonCommon(
            text = text,
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.tertiary,
            enabled = false,
            size = size,
        ) {}
        SirioButtonCommon(
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.tertiary,
            enabled = false,
            size = size,
        ) {}
    }
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SirioButtonCommon(
            text = text,
            colors = SirioTheme.colors.buttons.ghost,
            size = size,
        ) {}
        SirioButtonCommon(
            text = text,
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.ghost,
            size = size,
        ) {}
        SirioButtonCommon(
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.ghost,
            size = size,
        ) {}
    }
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SirioButtonCommon(
            text = text,
            colors = SirioTheme.colors.buttons.ghost,
            enabled = false,
            size = size,
        ) {}
        SirioButtonCommon(
            text = text,
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.ghost,
            enabled = false,
            size = size,
        ) {}
        SirioButtonCommon(
            faIcon = FaIcons.ArrowRight,
            colors = SirioTheme.colors.buttons.ghost,
            enabled = false,
            size = size,
        ) {}
    }
}
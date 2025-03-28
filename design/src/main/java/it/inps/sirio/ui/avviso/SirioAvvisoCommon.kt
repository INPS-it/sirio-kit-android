//
// SirioAvvisoCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.avviso

import androidx.annotation.Keep
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.avvisoContainerCornerRadius
import it.inps.sirio.theme.avvisoIconSize
import it.inps.sirio.theme.avvisoPadding
import it.inps.sirio.theme.avvisoSpacingHorizontal
import it.inps.sirio.theme.avvisoSpacingVertical
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource


/**
 * A composable for displaying an informational notice.
 *
 * This function creates a visually consistent container to display a title, a descriptive text,
 * and an optional link.
 *
 * @param title The title of the notice, displayed in a headline style.
 * @param text The main text content of the notice, providing details or context.
 * @param link An optional link text. If provided, it's displayed as a clickable element.
 * @param onLinkClick An optional callback function that is invoked when the link is clicked.
 *                    If null, the link will be displayed as static text.
 *
 * Example Usage:
 * ```
 * SirioAvvisoCommon(
 *     title = "Important Notice",
 *     text = "Please review the latest terms and conditions.",
 *     link = "Learn More",
 *     onLinkClick = {
 *         // Handle link click, e.g., navigate to a different screen
 *     }
 * )
 * ```
 **/
@Composable
internal fun SirioAvvisoCommon(
    title: String,
    text: String,
    link: String?,
    onLinkClick: (() -> Unit)?,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(avvisoContainerCornerRadius.dp),
        color = SirioTheme.colors.avviso.background,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(avvisoPadding.dp),
            horizontalArrangement = Arrangement.spacedBy(avvisoSpacingHorizontal.dp)
        ) {
            SirioIcon(
                iconData = SirioIconData(
                    icon = SirioIconSource.FaIcon(FaIcons.InfoCircle),
                    iconColor = SirioTheme.colors.avviso.icon,
                    size = avvisoIconSize.dp,
                )
            )
            Column(verticalArrangement = Arrangement.spacedBy(avvisoSpacingVertical.dp)) {
                SirioTextCommon(
                    text = title,
                    color = SirioTheme.colors.avviso.title,
                    typography = SirioTheme.foundationTypography.headlineSmHeavy,
                )
                SirioTextCommon(
                    text = text,
                    color = SirioTheme.colors.avviso.text,
                    typography = SirioTheme.foundationTypography.bodySmRegular,
                )
                link?.let {
                    SirioText(
                        text = it,
                        modifier = Modifier.clickable(
                            enabled = onLinkClick != null,
                            onClick = onLinkClick!!,
                        ),
                        color = SirioTheme.colors.avviso.link,
                        textDecoration = TextDecoration.Underline,
                        typography = SirioTheme.foundationTypography.linkSmHeavy,
                    )
                }
            }
        }
    }
}

@Keep
data class SirioAvvisoColors(
    val background: Color,
    val title: Color,
    val text: Color,
    val link: Color,
    val icon: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioAvvisoColors(
            background = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
            link = Color.Unspecified,
            icon = Color.Unspecified,
        )
    }
}

internal val avvisoLightColors = SirioAvvisoColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
    link = FoundationColor.colorAliasInteractivePrimaryDefault,
    icon = FoundationColor.colorGlobalSemanticInfo100,
)

internal val avvisoDarkColors = avvisoLightColors

@Preview
@Composable
private fun SirioAvvisoCommonPreview() {
    SirioTheme {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            val title = "Titolo avviso"
            val text =
                "Lorem ipsum dolor sit amet consectetur. Amet mollis vestibulum in et ante tempor."
            val linkText = "Link opzionale"
            val onLinkClicked = {}
            SirioAvvisoCommon(
                title = title,
                text = text,
                link = linkText,
                onLinkClick = onLinkClicked,
            )
            SirioAvvisoCommon(
                title = title,
                text = text,
                link = null,
                onLinkClick = null,
            )
        }
    }
}
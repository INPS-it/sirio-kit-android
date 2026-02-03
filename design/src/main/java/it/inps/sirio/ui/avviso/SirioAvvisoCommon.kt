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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
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
import it.inps.sirio.utils.takeTwoWords

/**
 * The internal common implementation for the notice component.
 *
 * @param title The string for the title.
 * @param text The string for the main text content.
 * @param link An optional string for the link. If null, the link is not displayed.
 * @param icon The [SirioIconSource] of the icon.
 * @param titleTypography The [TextStyle] for the title.
 * @param textTypography The [TextStyle] for the main text.
 * @param onClick A callback that will be called when the component is clicked. If null, the component will not be clickable.
 * @param onLinkClick A callback that will be called when the link is clicked.
 */
@Composable
internal fun SirioAvvisoCommon(
    title: String,
    text: String,
    link: String?,
    icon: SirioIconSource = SirioIconSource.FaIcon(FaIcons.InfoCircle),
    titleTypography: TextStyle = SirioTheme.foundationTypography.headlineSmHeavy,
    textTypography: TextStyle = SirioTheme.foundationTypography.bodyMdRegular,
    onClick: (() -> Unit)? = null,
    onLinkClick: (() -> Unit)?,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = onClick != null,
                onClick = { onClick?.invoke() },
            ),
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
                    icon = icon,
                    iconColor = SirioTheme.colors.avviso.icon,
                    size = avvisoIconSize.dp,
                )
            )
            Column(verticalArrangement = Arrangement.spacedBy(avvisoSpacingVertical.dp)) {
                SirioTextCommon(
                    text = title,
                    color = SirioTheme.colors.avviso.title,
                    typography = titleTypography,
                )
                SirioTextCommon(
                    text = text,
                    color = SirioTheme.colors.avviso.text,
                    typography = textTypography,
                )
                link?.let {
                    SirioText(
                        text = it,
                        modifier = Modifier
                            .testTag("link${it.takeTwoWords()}")
                            .clickable(
                                enabled = onLinkClick != null,
                                onClick = onLinkClick!!,
                            ),
                        color = SirioTheme.colors.avviso.link,
                        textDecoration = TextDecoration.Underline,
                        typography = SirioTheme.foundationTypography.linkMdHeavy,
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

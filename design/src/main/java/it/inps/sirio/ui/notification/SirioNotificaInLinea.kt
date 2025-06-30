//
// SirioNotificaInLinea.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.notificaCloseSize
import it.inps.sirio.theme.notificaContentInLineaSacing
import it.inps.sirio.theme.notificaContentPaddingHorizontal
import it.inps.sirio.theme.notificaContentPaddingVertical
import it.inps.sirio.theme.notificaContentSpacing
import it.inps.sirio.theme.notificaIconSize
import it.inps.sirio.theme.notificaInLineaBorderBottom
import it.inps.sirio.theme.notificaInLineaBorderEnd
import it.inps.sirio.theme.notificaInLineaBorderStart
import it.inps.sirio.theme.notificaInLineaBorderTop
import it.inps.sirio.theme.notificaInLineaCornerRadius
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioNotificaInLinea(
    state: SirioNotificaState,
    closeContentDescription: String? = null,
    title: String,
    text: String,
    link: String? = null,
    onClose: (() -> Unit)? = null,
    onLinkClick: (() -> Unit)? = null,
) {
    val stateColor = state.toColor()
    val iconColor = SirioTheme.colors.notifica.inLinea.icon ?: stateColor
    val closeColor = SirioTheme.colors.notifica.inLinea.close
    Column(
        Modifier
            .clip(RoundedCornerShape(notificaInLineaCornerRadius.dp))
            .background(stateColor)
            .padding(
                start = notificaInLineaBorderStart.dp,
                end = notificaInLineaBorderEnd.dp,
                top = notificaInLineaBorderTop.dp,
                bottom = notificaInLineaBorderBottom.dp,
            )
            .clip(
                RoundedCornerShape(
                    topEnd = notificaInLineaCornerRadius.dp,
                    bottomEnd = notificaInLineaCornerRadius.dp,
                )
            )
            .background(SirioTheme.colors.notifica.inLinea.background)
            .padding(
                horizontal = notificaContentPaddingHorizontal.dp,
                vertical = notificaContentPaddingVertical.dp,
            )
    ) {
        SirioNotificaInLineaHeader(
            icon = state.toIcon(),
            iconColor = iconColor,
            closeColor = closeColor,
            closeContentDescription = closeContentDescription,
            onClose = onClose
        )
        Spacer(Modifier.height(notificaContentInLineaSacing.dp))
        SirioNotificaInLineaContent(
            title = title,
            text = text,
            link = link,
            onLinkClick = onLinkClick,
        )
    }
}

@Composable
private fun SirioNotificaInLineaHeader(
    icon: FaIconType,
    iconColor: Color,
    closeColor: Color,
    closeContentDescription: String?,
    onClose: (() -> Unit)?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SirioIcon(
            iconData = SirioIconData(
                icon = SirioIconSource.FaIcon(icon),
                iconColor = iconColor,
                size = notificaIconSize.dp,
            )
        )
        Spacer(Modifier.weight(1f))
        onClose?.let {
            SirioIcon(
                iconData = SirioIconData(
                    icon = SirioIconSource.FaIcon(FaIcons.Times),
                    iconColor = closeColor,
                    size = notificaCloseSize.dp,
                    contentDescription = closeContentDescription,
                    onclick = onClose,
                )
            )
        }
    }
}

@Composable
private fun SirioNotificaInLineaContent(
    title: String,
    text: String,
    link: String?,
    onLinkClick: (() -> Unit)?,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(notificaContentSpacing.dp)
    ) {
        SirioText(
            text = title,
            color = SirioTheme.colors.notifica.pagina.title,
            typography = SirioTheme.foundationTypography.headlineSmHeavy,
        )
        SirioText(
            text = text,
            color = SirioTheme.colors.notifica.pagina.text,
            typography = SirioTheme.foundationTypography.bodyMdRegular,
        )
        link?.let {
            SirioText(
                text = it,
                modifier = Modifier.clickable(onClick = onLinkClick ?: {}),
                color = SirioTheme.colors.notifica.pagina.link,
                textDecoration = TextDecoration.Underline,
                typography = SirioTheme.foundationTypography.linkMdHeavy,
            )
        }
    }
}

@Preview(heightDp = 1000)
@Composable
private fun SirioNotificaInLineaPreview() {
    SirioTheme {
        Column(verticalArrangement = Arrangement.spacedBy(50.dp)) {
            val title = "Titolo notifica"
            val text =
                "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor."
            val link = "Link opzionale"
            SirioNotificaInLinea(
                state = SirioNotificaState.Alert,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
            SirioNotificaInLinea(
                state = SirioNotificaState.Info,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
            SirioNotificaInLinea(
                state = SirioNotificaState.Warning,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
            SirioNotificaInLinea(
                state = SirioNotificaState.Success,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
        }
    }
}

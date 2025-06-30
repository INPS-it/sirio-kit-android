//
// SirioNotificaPagina.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.notification

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.notificaCloseSize
import it.inps.sirio.theme.notificaContentPaddingHorizontal
import it.inps.sirio.theme.notificaContentPaddingPaginaBottom
import it.inps.sirio.theme.notificaContentPaddingVertical
import it.inps.sirio.theme.notificaContentSpacing
import it.inps.sirio.theme.notificaIconSize
import it.inps.sirio.theme.notificaPaddingHorizontal
import it.inps.sirio.theme.notificaPaginaHeaderHeight
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioNotificaPaginaDialog(
    state: SirioNotificaState,
    title: String,
    text: String,
    link: String? = null,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
    onLinkClick: (() -> Unit)? = null,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onClose,
    ) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.BottomCenter,
        ) {
            SirioNotificaPagina(
                state = state,
                title = title,
                text = text,
                link = link,
                closeContentDescription = closeContentDescription,
                onClose = onClose,
                onLinkClick = onLinkClick
            )
        }
    }
}

@Composable
fun SirioNotificaPagina(
    state: SirioNotificaState,
    title: String,
    text: String,
    link: String? = null,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
    onLinkClick: (() -> Unit)? = null,
) {
    val stateColor = state.toColor()
    val iconColor = SirioTheme.colors.notifica.pagina.icon ?: stateColor
    val closeColor = SirioTheme.colors.notifica.pagina.close
    Column(Modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))) {
        SirioNotificaPaginaHeader(
            stateColor = stateColor,
            icon = state.toIcon(),
            iconColor = iconColor,
            closeColor = closeColor,
            closeContentDescription = closeContentDescription,
            onClose = onClose
        )
        SirioNotificaPaginaContent(
            title = title,
            text = text,
            link = link,
            onLinkClick = onLinkClick,
        )
    }
}

@Composable
private fun SirioNotificaPaginaHeader(
    stateColor: Color,
    icon: FaIconType,
    iconColor: Color,
    closeColor: Color,
    closeContentDescription: String?,
    onClose: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(notificaPaginaHeaderHeight.dp)
            .background(stateColor)
            .padding(horizontal = notificaPaddingHorizontal.dp),
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

@Composable
private fun SirioNotificaPaginaContent(
    title: String,
    text: String,
    link: String?,
    onLinkClick: (() -> Unit)?,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.notifica.pagina.background)
            .padding(
                start = notificaContentPaddingHorizontal.dp,
                end = notificaContentPaddingHorizontal.dp,
                top = notificaContentPaddingVertical.dp,
                bottom = notificaContentPaddingPaginaBottom.dp
            ),
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
private fun SirioNotificaPaginaPreview() {
    SirioTheme {
        Column(verticalArrangement = Arrangement.spacedBy(50.dp)) {
            val title = "Titolo notifica"
            val text =
                "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor."
            val link = "Link opzionale"
            SirioNotificaPagina(
                state = SirioNotificaState.Alert,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
            SirioNotificaPagina(
                state = SirioNotificaState.Info,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
            SirioNotificaPagina(
                state = SirioNotificaState.Warning,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
            SirioNotificaPagina(
                state = SirioNotificaState.Success,
                onClose = {},
                title = title,
                text = text,
                link = link
            )
        }
    }
}

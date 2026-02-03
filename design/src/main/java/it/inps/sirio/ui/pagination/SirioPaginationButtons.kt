package it.inps.sirio.ui.pagination

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseColors
import it.inps.sirio.theme.SirioBaseStateColors
import it.inps.sirio.theme.SirioColorState
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.paginationButtonChangePageWidth
import it.inps.sirio.theme.paginationButtonCornerRadius
import it.inps.sirio.theme.paginationButtonHeight
import it.inps.sirio.theme.paginationButtonIconSize
import it.inps.sirio.theme.paginationButtonNumberPageWidth
import it.inps.sirio.theme.paginationDotsWidth
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource

/**
 * A pagination button with an icon to change page
 *
 * @param icon The button icon
 * @param enabled True if the button is enabled, false otherwise
 * @param onClick The callback for the button click
 */
@Composable
internal fun SirioPaginationButtonChangePage(
    icon: SirioIconSource,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val containerColor = SirioTheme.colors.pagination.buttonChange
        .container.get(pressed = isPressed, disabled = enabled.not())
    val contentColor = SirioTheme.colors.pagination.buttonChange
        .content.get(pressed = isPressed, disabled = enabled.not())

    Surface(
        onClick = onClick,
        modifier = Modifier.requiredSize(
            paginationButtonChangePageWidth.dp,
            paginationButtonHeight.dp
        ),
        enabled = enabled,
        shape = RoundedCornerShape(paginationButtonCornerRadius.dp),
        color = containerColor,
        contentColor = contentColor,
        interactionSource = interactionSource,
    ) {
        SirioIcon(
            icon = icon,
            iconColor = contentColor,
            size = paginationButtonIconSize.dp,
        )
    }
}

/**
 * A pagination button with a number to go to a specific page
 *
 * @param number The page number
 * @param selected True if the page is the current page, false otherwise
 * @param enabled True if the button is enabled, false otherwise
 * @param onClick The callback for the button click
 */
@Composable
internal fun SirioPaginationButtonNumberPage(
    number: String,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val containerColor = SirioTheme.colors.pagination.buttonNumber
        .container.get(pressed = selected, disabled = enabled.not())
    val contentColor = SirioTheme.colors.pagination.buttonNumber
        .content.get(pressed = selected, disabled = enabled.not())

    Surface(
        onClick = onClick,
        modifier = Modifier.testTag("pagination$number").requiredSize(
            paginationButtonNumberPageWidth.dp,
            paginationButtonHeight.dp,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(paginationButtonCornerRadius.dp),
        color = containerColor,
        contentColor = contentColor,
    ) {
        Box(contentAlignment = Alignment.Center) {
            SirioText(
                text = number,
                color = contentColor,
                textAlign = TextAlign.Center,
                typography = SirioTheme.foundationTypography.labelNumberMdRegular,
            )
        }
    }
}

/**
 * The dots element of the pagination
 */
@Composable
internal fun SirioPaginationDots() {
    val containerColor = SirioTheme.colors.pagination.dots.container
    val contentColor = SirioTheme.colors.pagination.dots.content

    Surface(
        modifier = Modifier.requiredSize(paginationDotsWidth.dp, paginationButtonHeight.dp),
        shape = RoundedCornerShape(paginationButtonCornerRadius.dp),
        color = containerColor,
        contentColor = contentColor,
    ) {
        Box(contentAlignment = Alignment.Center) {
            SirioText(
                text = "...",
                color = contentColor,
                textAlign = TextAlign.Center,
                typography = SirioTheme.foundationTypography.labelMdRegular,
            )
        }
    }
}

internal val paginationButtonNumberPageLightColors = SirioBaseStateColors(
    container = SirioColorState(
        default = Color.Transparent,
        pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
        disabled = FoundationColor.colorAliasBackgroundColorDisabled,
    ),
    content = SirioColorState(
        default = FoundationColor.colorAliasInteractiveSecondaryDefault,
        pressed = FoundationColor.colorAliasInteractivePrimary000Default,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    )
)

internal val paginationButtonNumberPageDarkColors = paginationButtonNumberPageLightColors

internal val paginationButtonChangePageLightColors = SirioBaseStateColors(
    container = SirioColorState(
        default = Color.Transparent,
        pressed = FoundationColor.colorAliasInteractivePrimaryPressed,
        disabled = Color.Transparent,
    ),
    content = SirioColorState(
        default = FoundationColor.colorAliasInteractivePrimaryDefault,
        pressed = FoundationColor.colorAliasInteractivePrimary000Default,
        disabled = FoundationColor.colorAliasTextColorDisabled,
    )
)

internal val paginationButtonChangePageDarkColors = paginationButtonChangePageLightColors

internal val paginationDotsLightColors = SirioBaseColors(
    container = Color.Transparent,
    content = FoundationColor.colorAliasTextColorDisabled,
)

internal val paginationDotsDorkColors = paginationDotsLightColors

@Preview
@Composable
private fun SirioPaginationButtonChangePagePreview() {
    SirioTheme {
        Column {
            SirioPaginationButtonChangePage(
                icon = SirioIconSource.FaIcon(FaIcons.AngleLeft),
                onClick = {},
            )
            SirioPaginationButtonChangePage(
                icon = SirioIconSource.FaIcon(FaIcons.AngleLeft),
                enabled = false,
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
private fun SirioPaginationButtonNumberPagePreview() {
    SirioTheme {
        Column {
            SirioPaginationButtonNumberPage(
                number = "1",
                selected = false,
                enabled = true,
                onClick = {},
            )
            SirioPaginationButtonNumberPage(
                number = "1",
                selected = true,
                enabled = true,
                onClick = {},
            )
            SirioPaginationButtonNumberPage(
                number = "2",
                selected = false,
                enabled = false,
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
private fun SirioPaginationDotsPreview() {
    SirioTheme {
        SirioPaginationDots()
    }
}
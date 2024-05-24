package it.inps.sirio.ui.badge

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.badgeBorderSize
import it.inps.sirio.theme.badgeSize

@Composable
internal fun SirioBadgeCommon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(badgeSize.dp)
            .clip(CircleShape)
            .background(SirioTheme.colors.badge.border)
            .padding(badgeBorderSize.dp)
            .clip(CircleShape)
            .background(SirioTheme.colors.badge.background)
    )
}

@Keep
data class SirioBadgeColors(
    val background: Color,
    val border: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioBadgeColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
        )
    }
}


@Preview
@Composable
internal fun SirioBadgeCommonPreview() {
    SirioTheme {
        SirioBadgeCommon()
    }
}

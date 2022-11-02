package it.inps.sirio.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType

@Composable
internal fun FaIconCentered(
    icon: FaIconType?,
    iconColor: Color,
    size: Dp = 24.dp,
) {
    icon?.let {
        Box(Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
            SirioFaIcon(
                faIcon = it,
                size = size,
                tint = iconColor
            )
        }
    }
}
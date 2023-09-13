package it.inps.sirio.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIconType

/**
 * Creates a custom icon with the specified parameters
 * @param faIcon The font awesome icon to be used. This parameter accepts an object of type FaIconType
 * @param iconResId The resource id of the image to be used as the icon if no [faIcon] is provided
 * @param iconColor The color of the icon
 * @param size The size of the icon in dp
 * @param contentDescription The content description for the icon
 * @return The custom icon
 */
@Composable
fun SirioIcon(
    faIcon: FaIconType? = null,
    @DrawableRes iconResId: Int? = null,
    iconColor: Color,
    size: Dp = 24.dp,
    contentDescription: String? = null,
) {
    require(faIcon != null || iconResId != null) { "At least one of faIcon or iconResId must be non-null" }
    Box(Modifier.requiredSize(size), contentAlignment = Alignment.Center) {
        faIcon?.let {
            SirioFaIcon(
                faIcon = it,
                size = size,
                tint = iconColor,
                contentDescription = contentDescription,
            )
        } ?: iconResId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = contentDescription,
                modifier = Modifier.size(size),
                tint = iconColor,
            )
        }
    }
}
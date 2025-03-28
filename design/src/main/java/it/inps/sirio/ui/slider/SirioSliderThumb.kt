package it.inps.sirio.ui.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.sliderThumbBorderSize
import it.inps.sirio.theme.sliderThumbBorderWidth
import it.inps.sirio.theme.sliderThumbPadding

/**
 *  Slider Thumb
 *  @param enabled set the Thumb enable/disable state.
 */
@Composable
internal fun SirioSliderThumb(
    enabled: Boolean,
) {
    val color = SirioTheme.colors.slider.thumb.get(disabled = !enabled)

    Box(
        modifier = Modifier
            .size(sliderThumbBorderSize.dp)
            .border(
                width = sliderThumbBorderWidth.dp,
                color = color,
                shape = CircleShape,
            )
            .padding((sliderThumbPadding + sliderThumbBorderWidth).dp)
            .size(sliderThumbBorderSize.dp)
            .background(color , CircleShape)
    )
}

@Preview
@Composable
private fun SirioSliderThumbPrev() {
    SirioTheme {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SirioSliderThumb(true)
            SirioSliderThumb(false)
        }
    }
}
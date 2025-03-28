package it.inps.sirio.ui.segmentedcontrols

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.utils.SirioIconSource

@Composable
fun RowScope.SirioSegmentedButton(
    selected: Boolean,
    text: String? = null,
    onClick: () -> Unit,
) {
    SirioSegmentedButtonCommon(
        selected = selected,
        text = text,
        onClick = onClick
    )
}

@Composable
fun RowScope.SirioSegmentedButton(
    selected: Boolean,
    icon: SirioIconSource? = null,
    iconContentDescription: String? = null,
    onClick: () -> Unit,
) {
    SirioSegmentedButtonCommon(
        selected = selected,
        icon = icon,
        iconContentDescription = iconContentDescription,
        onClick = onClick
    )
}

@Preview
@Composable
private fun SirioSegmentedButtonPreview() {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(36.dp)) {
            SingleChoiceSegmentedButtonRow {
                SirioSegmentedButton(selected = true, text = "Label", onClick = {})
            }
            SingleChoiceSegmentedButtonRow {
                SirioSegmentedButton(
                    selected = false,
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    onClick = {},
                )
            }
        }
    }
}
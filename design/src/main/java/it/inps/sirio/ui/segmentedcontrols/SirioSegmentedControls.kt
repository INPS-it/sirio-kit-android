package it.inps.sirio.ui.segmentedcontrols

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioBaseStateColors
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.segmentedControlsBorderCornerRadius
import it.inps.sirio.theme.segmentedControlsBorderPadding
import it.inps.sirio.theme.segmentedControlsBorderWidth
import it.inps.sirio.theme.segmentedControlsSeparatorCornerRadius
import it.inps.sirio.theme.segmentedControlsSeparatorHeight
import it.inps.sirio.theme.segmentedControlsSeparatorWidth
import it.inps.sirio.utils.SirioIconSource

@Composable
fun SirioSegmentedControls(
    labels: List<String>,
    selectedIndex: Int = 0,
    onSelectionChanged: (Int) -> Unit,
) {
    require(labels.size in 2..5) { "SirioSegmentedControls accepts between 2 and 5 items" }
    SirioSegmentedControlsRow {
        labels.forEachIndexed { index, label ->
            val selected = index == selectedIndex
            SirioSegmentedButton(
                selected = selected,
                text = label,
                onClick = { onSelectionChanged(index) }
            )
            if (!selected && index != selectedIndex - 1 && index != labels.lastIndex) {
                SirioSegmentedControlsSeparator()
            }
        }
    }
}

@Composable
private fun SirioSegmentedControlsRow(content: @Composable (RowScope.() -> Unit)) {
    Row(
        modifier = Modifier
            .selectableGroup()
            .fillMaxWidth()
            .background(
                color = SirioTheme.colors.segmentedControls.background,
                shape = RoundedCornerShape(segmentedControlsBorderCornerRadius.dp),
            )
            .border(
                width = segmentedControlsBorderWidth.dp,
                color = SirioTheme.colors.segmentedControls.border,
                shape = RoundedCornerShape(segmentedControlsBorderCornerRadius.dp),
            )
            .padding((segmentedControlsBorderWidth + segmentedControlsBorderPadding).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
private fun SirioSegmentedControlsSeparator() {
    VerticalDivider(
        modifier = Modifier
            .height(segmentedControlsSeparatorHeight.dp)
            .clip(RoundedCornerShape(segmentedControlsSeparatorCornerRadius)),
        thickness = segmentedControlsSeparatorWidth.dp,
        color = SirioTheme.colors.segmentedControls.separator,
    )
}

@Composable
fun SirioSegmentedControls(
    icons: List<SirioIconSource>,
    selectedIndex: Int = 0,
    contentDescriptions: List<String?> = List(icons.size) { null },
    onSelectionChanged: (Int) -> Unit,
) {
    require(icons.size in 2..5) { "SirioSegmentedControls accepts between 2 and 5 items" }
    SirioSegmentedControlsRow {
        icons.forEachIndexed { index, icon ->
            val selected = index == selectedIndex
            SirioSegmentedButton(
                selected = selected,
                icon = icon,
                iconContentDescription = contentDescriptions[index],
                onClick = { onSelectionChanged(index) }
            )
            if (!selected && index != selectedIndex - 1 && index != icons.lastIndex) {
                SirioSegmentedControlsSeparator()
            }
        }
    }
}

@Keep
data class SirioSegmentedControlsColors(
    val background: Color,
    val border: Color,
    val separator: Color,
    val button: SirioBaseStateColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioSegmentedControlsColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            separator = Color.Unspecified,
            button = SirioBaseStateColors.Unspecified,
        )
    }
}

internal val segmentedControlsLightColors = SirioSegmentedControlsColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight40,
    border = FoundationColor.colorGlobalMidPrimary70,
    separator = FoundationColor.colorAliasBackgroundColorSecondaryLight50,
    button = segmentedControlsButtonLightColors
)

internal val segmentedControlsDarkColors =
    segmentedControlsLightColors.copy(button = segmentedControlsButtonDarkColors)

@Preview(widthDp = 800)
@Composable
private fun SirioSegmentedControlsPreview() {
    SirioTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(92.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                var selectedIndex by remember { mutableIntStateOf(0) }
                SirioSegmentedControls(
                    labels = List(5) { "Label" },
                    selectedIndex = selectedIndex,
                    onSelectionChanged = { selectedIndex = it }
                )
                SirioSegmentedControlsPreviewDemo(2, true)
                SirioSegmentedControlsPreviewDemo(3, true)
                SirioSegmentedControlsPreviewDemo(4, true)
                SirioSegmentedControlsPreviewDemo(5, true)
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                var selectedIndex by remember { mutableIntStateOf(0) }
                SirioSegmentedControls(
                    icons = List(5) { SirioIconSource.FaIcon(FaIcons.Cube) },
                    selectedIndex = selectedIndex,
                    onSelectionChanged = { selectedIndex = it }
                )
                SirioSegmentedControlsPreviewDemo(2, false)
                SirioSegmentedControlsPreviewDemo(3, false)
                SirioSegmentedControlsPreviewDemo(4, false)
                SirioSegmentedControlsPreviewDemo(5, false)
            }
        }
    }
}

@Composable
private fun SirioSegmentedControlsPreviewDemo(elements: Int, text: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (i in 0 until elements) {
            if (text)
                SirioSegmentedControls(
                    labels = List(elements) { "Label" },
                    selectedIndex = i,
                    onSelectionChanged = { }
                )
            else
                SirioSegmentedControls(
                    icons = List(elements) { SirioIconSource.FaIcon(FaIcons.Cube) },
                    selectedIndex = i,
                    onSelectionChanged = { }
                )
        }
    }
}
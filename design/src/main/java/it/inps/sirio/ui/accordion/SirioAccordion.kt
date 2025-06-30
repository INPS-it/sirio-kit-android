//
// SirioAccordion.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.utils.SirioIconSource

/**
 * Displays collapsible content panels for presenting information in a limited amount of space
 *
 * @param data [SirioAccordionData] The content of the component
 * @param color [SirioAccordionColor] The color of the component
 */
@Composable
fun SirioAccordion(
    data: SirioAccordionData,
    color: SirioAccordionColor = SirioAccordionColor.PRIMARY,
) {
    SirioAccordionCommon(
        title = data.title,
        color = color,
        icon = data.icon,
        tag = data.tag,
        text = data.text,
        open = data.open,
        enabled = data.enabled,
        onTapAccordion = data.onTapAccordion,
        content = data.content
    )
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AccordionPreview() {
    val content = LoremIpsum(30).values.joinToString()
    SirioTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SirioAccordion(
                data = SirioAccordionData.WithIcon(
                    title = "Accordion Item #1\nRiga 2",
                    icon = SirioIconSource.FaIcon(FaIcons.Cube),
                    open = false,
                    enabled = true,
                    onTapAccordion = {},
                ) { Text(text = content) })
            SirioAccordion(
                data = SirioAccordionData.Default(
                    title = "Accordion Item #1",
                    open = true,
                    enabled = true,
                    onTapAccordion = {},
                ) { Text(text = content) })
            SirioAccordion(
                data = SirioAccordionData.Default(
                    title = "Accordion Item #1",
                    open = false,
                    enabled = false,
                    onTapAccordion = {},
                ) { Text(text = content) })
        }
    }
}
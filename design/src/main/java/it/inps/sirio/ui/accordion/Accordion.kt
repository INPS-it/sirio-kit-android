//
// Accordion.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
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
import it.inps.sirio.theme.SirioTheme

/**
 * Displays collapsible content panels for presenting information in a limited amount of space
 *
 * @param data [AccordionData] The content of the component
 */
@Composable
fun Accordion(data: AccordionData) {
    AccordionCommon(
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
            Accordion(
                data = AccordionData(
                    "Accordion Item #1",
                    open = false,
                    enabled = true,
                    onTapAccordion = {}) { Text(text = content) })
            Accordion(
                data = AccordionData(
                    "Accordion Item #1",
                    open = true,
                    enabled = true,
                    onTapAccordion = {}) { Text(text = content) })
            Accordion(
                data = AccordionData(
                    "Accordion Item #1",
                    open = false,
                    enabled = false,
                    onTapAccordion = {}) { Text(text = content) })
        }
    }
}

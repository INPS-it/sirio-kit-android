//
// AccordionGroup.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import it.inps.sirio.theme.SirioTheme


/**
 * A group of accordions in column
 *
 * @param data A list of [AccordionData] each with the content of one accordion
 */
@Composable
fun AccordionGroup(data: List<AccordionData>) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(data) { itemData ->
            AccordionCommon(
                text = itemData.text,
                open = itemData.open,
                enabled = itemData.enabled,
                onTapAccordion = itemData.onTapAccordion,
                content = itemData.content
            )
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AccordionGroupPreview() {
    val content = LoremIpsum(30).values.joinToString()
    SirioTheme {
        AccordionGroup(
            data = listOf(
                AccordionData(
                    "Accordion Item #1",
                    open = false,
                    enabled = true,
                    onTapAccordion = {}) { Text(text = content) },
                AccordionData(
                    "Accordion Item #2",
                    open = false,
                    enabled = true,
                    onTapAccordion = {}) { Text(text = content) },
                AccordionData(
                    "Accordion Item #3",
                    open = true,
                    enabled = true,
                    onTapAccordion = {}) {
                    Column {
                        for (i in 1..5) {
                            Text(
                                text = "Content $i",
                                Modifier.background(Color.Red)
                            )
                        }
                    }
                },
                AccordionData(
                    "Accordion Item #4",
                    open = false,
                    enabled = false,
                    onTapAccordion = {}) { Text(text = content) },
            )
        )
    }
}
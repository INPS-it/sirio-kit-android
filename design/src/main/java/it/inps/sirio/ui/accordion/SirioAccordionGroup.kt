//
// SirioAccordionGroup.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.accordion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.appnavigation.SirioAppNavigation
import it.inps.sirio.ui.appnavigation.SirioAppNavigationItemData
import it.inps.sirio.ui.appnavigation.SirioFunction
import it.inps.sirio.utils.SirioIconSource

/**
 * A group of accordions displayed in a column.
 *
 * @param data A list of [SirioAccordionData] objects, where each object defines the content and properties of an individual accordion within the group.
 * @param color The [SirioAccordionColor] to be applied to all accordions in the group. Defaults to [SirioAccordionColor.PRIMARY].
 */
@Composable
fun SirioAccordionGroup(
    data: List<SirioAccordionData>,
    color: SirioAccordionColor = SirioAccordionColor.PRIMARY,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        data.forEach { itemData ->
            SirioAccordionCommon(
                title = itemData.title,
                color = color,
                icon = itemData.icon,
                tag = itemData.tag,
                text = itemData.text,
                open = itemData.open,
                onTapAccordion = itemData.onTapAccordion,
                content = itemData.content
            )
        }
    }
}

private val demoContent = LoremIpsum(30).values.joinToString()
private val demoIcon = SirioIconSource.FaIcon(FaIcons.Cube)

@Preview
@Composable
private fun AccordionGroupDefaultPrimaryPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.PRIMARY,
        data = listOf(
            SirioAccordionData.Default(
                title = "Accordion title #1",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.Default(
                title = "Accordion title #2",
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.Default(
                title = "Accordion title #3",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.Default(
                title = "Accordion title #4",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupDefaultLightPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.LIGHT, listOf(
            SirioAccordionData.Default(
                title = "Accordion title #1",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.Default(
                title = "Accordion title #2",
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.Default(
                title = "Accordion title #3",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.Default(
                title = "Accordion title #4",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupIconPrimaryPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.PRIMARY,
        listOf(
            SirioAccordionData.WithIcon(
                title = "Accordion title #1",
                icon = demoIcon,
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithIcon(
                title = "Accordion title #2",
                icon = demoIcon,
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithIcon(
                title = "Accordion title #3",
                icon = demoIcon,
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithIcon(
                title = "Accordion title #4",
                icon = demoIcon,
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupIconLightPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.LIGHT,
        listOf(
            SirioAccordionData.WithIcon(
                title = "Accordion title #1",
                icon = demoIcon,
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithIcon(
                title = "Accordion title #2",
                icon = demoIcon,
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithIcon(
                title = "Accordion title #3",
                icon = demoIcon,
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithIcon(
                title = "Accordion title #4",
                icon = demoIcon,
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupTagPrimaryPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.PRIMARY,
        listOf(
            SirioAccordionData.WithTag(
                title = "Accordion title #1",
                tag = "Tag",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithTag(
                title = "Accordion title #2",
                tag = "Tag",
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithTag(
                title = "Accordion title #3",
                tag = "Tag",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithTag(
                title = "Accordion title #4",
                tag = "Tag",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupTagLightPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.LIGHT,
        listOf(
            SirioAccordionData.WithTag(
                title = "Accordion title #1",
                tag = "Tag",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithTag(
                title = "Accordion title #2",
                tag = "Tag",
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithTag(
                title = "Accordion title #3",
                tag = "Tag",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithTag(
                title = "Accordion title #4",
                tag = "Tag",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupTextPrimaryPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.PRIMARY,
        listOf(
            SirioAccordionData.WithText(
                title = "Accordion title #1",
                text = "( Text )",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithText(
                title = "Accordion title #2",
                text = "( Text )",
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithText(
                title = "Accordion title #3",
                text = "( Text )",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithText(
                title = "Accordion title #4",
                text = "( Text )",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Preview
@Composable
private fun AccordionGroupTextLightPreview() {
    AccordionGroupPreview(
        color = SirioAccordionColor.LIGHT,
        listOf(
            SirioAccordionData.WithText(
                title = "Accordion title #1",
                text = "( Text )",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithText(
                title = "Accordion title #2",
                text = "( Text )",
                open = true,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithText(
                title = "Accordion title #3",
                text = "( Text )",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
            SirioAccordionData.WithText(
                title = "Accordion title #4",
                text = "( Text )",
                open = false,
                onTapAccordion = {},
            ) { Text(text = demoContent) },
        )
    )
}

@Composable
private fun AccordionGroupPreview(color: SirioAccordionColor, data: List<SirioAccordionData>) {
    SirioTheme {
        Scaffold(
            topBar = {
                SirioAppNavigation(
                    title = "Titolo del servizio",
                    leftItem = SirioAppNavigationItemData(
                        icon = FaIcons.ChevronLeft,
                        action = {},
                    ),
                    rightFirstItem = SirioAppNavigationItemData(
                        icon = FaIcons.User,
                        action = {},
                    ),
                    rightSecondItem = SirioAppNavigationItemData(
                        icon = FaIcons.Bell,
                        action = {},
                    )
                )
            }
        ) {
            Column(Modifier.padding(it)) {
                SirioTheme(darkTheme = true) { SirioFunction("Titolo  funzione") }
                Box(Modifier.padding(16.dp)) {
                    SirioAccordionGroup(
                        color = color,
                        data = data,
                    )
                }
            }
        }
    }
}
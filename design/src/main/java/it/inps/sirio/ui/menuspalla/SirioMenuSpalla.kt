//
// SirioMenuSpalla.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.menuspalla

import android.util.Log
import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme

@Composable
fun SirioMenuSpalla(
    title: String,
    subtitle: String,
    sections: List<SirioMenuSpallaSectionItemData>,
    open: Boolean = false,
    onStateChange: (open: Boolean) -> Unit,
) {
    LaunchedEffect(sections) {
        sections.any { section -> section.items.any { item -> item.hasInvalidDeep() } }
            .let { if (it) throw IllegalArgumentException("Menu spalla invalid deep > 3") }
    }
    var isOpen by remember { mutableStateOf(open) }
    var selectedId by remember { mutableStateOf("") }
    Column(modifier = Modifier.background(SirioTheme.colors.menuSpalla.background)) {
        SirioMenuSpallaDrawerItem(
            title = title,
            subtitle = subtitle,
            open = isOpen,
            onStateChange = { newOpenState ->
                if (newOpenState.not()) selectedId = ""
                isOpen = newOpenState
                onStateChange(newOpenState)
            }
        )
        if (isOpen) {
            sections.forEach {
                SirioMenuSpallaSection(
                    title = it.title,
                    items = it.items,
                    selectedId = selectedId,
                    onChildSelected = { newSelectedId -> selectedId = newSelectedId }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SirioMenuSpallaPreview() {
    SirioTheme {
        SirioMenuSpalla(
            title = "Title",
            subtitle = "Subtitle",
            sections = listOf(
                SirioMenuSpallaSectionItemData(
                    title = "Titolo sezione",
                    items = listOf(
                        SirioMenuSpallaItemData(
                            title = "Label tag",
                            tag = "3",
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label tag"
                                )
                            },
                        ),
                        SirioMenuSpallaItemData(
                            title = "Label 1",
                            tag = "1",
                            children = listOf(
                                SirioMenuSpallaItemData(
                                    title = "Label 1.1",
                                    tag = "1.1",
                                    onClick = {
                                        Log.d(
                                            "SirioMenuSpallaPreview",
                                            "SirioMenuSpallaPreview: label 1.1"
                                        )
                                    }
                                ),
                                SirioMenuSpallaItemData(
                                    title = "Label 1.2",
                                    tag = "1.2",
                                    children = listOf(
                                        SirioMenuSpallaItemData(
                                            title = "Label 1.2.1",
                                            tag = "1.2.1",
                                            onClick = {
                                                Log.d(
                                                    "SirioMenuSpallaPreview",
                                                    "SirioMenuSpallaPreview: label 1.2.1"
                                                )
                                            }
                                        ),
                                        SirioMenuSpallaItemData(
                                            title = "Label 1.2.2",
                                            tag = "1.2.2",
                                            onClick = {
                                                Log.d(
                                                    "SirioMenuSpallaPreview",
                                                    "SirioMenuSpallaPreview: label 1.2.2"
                                                )
                                            }
                                        )
                                    ),
                                    onClick = {
                                        Log.d(
                                            "SirioMenuSpallaPreview",
                                            "SirioMenuSpallaPreview: label 1.2"
                                        )
                                    }
                                )
                            ),
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label 1"
                                )
                            },
                        ),
                        SirioMenuSpallaItemData(
                            title = "Label 2",
                            tag = "2",
                            children = listOf(
                                SirioMenuSpallaItemData(
                                    title = "Label 2.1",
                                    tag = "2.1",
                                    onClick = {
                                        Log.d(
                                            "SirioMenuSpallaPreview",
                                            "SirioMenuSpallaPreview: label 2.1"
                                        )
                                    }
                                )
                            ),
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label 2"
                                )
                            },
                        ),
                        SirioMenuSpallaItemData(
                            title = "Label 3",
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label 3"
                                )
                            },
                        ),
                    ),
                ),
                SirioMenuSpallaSectionItemData(
                    title = "Titolo sezione",
                    items = listOf(
                        SirioMenuSpallaItemData(
                            title = "Label tag",
                            tag = "3",
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label tag"
                                )
                            },
                        ),
                        SirioMenuSpallaItemData(
                            title = "Label 1",
                            tag = "1",
                            children = listOf(
                                SirioMenuSpallaItemData(
                                    title = "Label 1.1",
                                    tag = "1.1",
                                    onClick = {
                                        Log.d(
                                            "SirioMenuSpallaPreview",
                                            "SirioMenuSpallaPreview: label 1.1"
                                        )
                                    }
                                ),
                                SirioMenuSpallaItemData(
                                    title = "Label 1.2",
                                    tag = "1.2",
                                    children = listOf(
                                        SirioMenuSpallaItemData(
                                            title = "Label 1.2.1",
                                            tag = "1.2.1",
                                            onClick = {
                                                Log.d(
                                                    "SirioMenuSpallaPreview",
                                                    "SirioMenuSpallaPreview: label 1.2.1"
                                                )
                                            }
                                        ),
                                        SirioMenuSpallaItemData(
                                            title = "Label 1.2.2",
                                            tag = "1.2.2",
                                            onClick = {
                                                Log.d(
                                                    "SirioMenuSpallaPreview",
                                                    "SirioMenuSpallaPreview: label 1.2.2"
                                                )
                                            }
                                        )
                                    ),
                                    onClick = {
                                        Log.d(
                                            "SirioMenuSpallaPreview",
                                            "SirioMenuSpallaPreview: label 1.2"
                                        )
                                    }
                                )
                            ),
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label 1"
                                )
                            },
                        ),
                        SirioMenuSpallaItemData(
                            title = "Label 2",
                            tag = "2",
                            children = listOf(
                                SirioMenuSpallaItemData(
                                    title = "Label 2.1",
                                    tag = "2.1",
                                    onClick = {
                                        Log.d(
                                            "SirioMenuSpallaPreview",
                                            "SirioMenuSpallaPreview: label 2.1"
                                        )
                                    }
                                )
                            ),
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label 2"
                                )
                            },
                        ),
                        SirioMenuSpallaItemData(
                            title = "Label 3",
                            onClick = {
                                Log.d(
                                    "SirioMenuSpallaPreview",
                                    "SirioMenuSpallaPreview: label 3"
                                )
                            },
                        ),
                    ),
                )

            ),
            onStateChange = {},
        )
    }
}

@Keep
data class SirioMenuSpallaColors(
    val background: Color,
    val drawerItem: SirioMenuSpallaDrawerItemColors,
    val drawerItemInfo: SirioMenuSpallaDrawerItemInfoColors,
    val itemTitleSection: SirioMenuSpallaItemTitleSectionColors,
    val itemPrimary: SirioMenuSpallaItemColors,
    val itemSecondary: SirioMenuSpallaItemColors,
    val itemTertiary: SirioMenuSpallaItemColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioMenuSpallaColors(
            background = Color.Unspecified,
            drawerItem = SirioMenuSpallaDrawerItemColors.Unspecified,
            drawerItemInfo = SirioMenuSpallaDrawerItemInfoColors.Unspecified,
            itemTitleSection = SirioMenuSpallaItemTitleSectionColors.Unspecified,
            itemPrimary = SirioMenuSpallaItemColors.Unspecified,
            itemSecondary = SirioMenuSpallaItemColors.Unspecified,
            itemTertiary = SirioMenuSpallaItemColors.Unspecified,
        )
    }
}

@Keep
data class SirioMenuSpallaTypography(
    val drawerItem: SirioMenuSpallaDrawerItemTypography,
    val drawerItemInfo: SirioMenuSpallaDrawerItemInfoTypography,
    val itemTitleSection: SirioMenuSpallaItemTitleSectionTypography,
    val item: SirioMenuSpallaItemTypography,
) {
    companion object {
        @Stable
        val Default = SirioMenuSpallaTypography(
            drawerItem = SirioMenuSpallaDrawerItemTypography.Default,
            drawerItemInfo = SirioMenuSpallaDrawerItemInfoTypography.Default,
            itemTitleSection = SirioMenuSpallaItemTitleSectionTypography.Default,
            item = SirioMenuSpallaItemTypography.Default,
        )
    }
}
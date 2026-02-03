//
// SirioTableHeaderCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.table.header

import androidx.annotation.Keep
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.SirioThemeMode
import it.inps.sirio.theme.tableComponentPaddingHorizontalLarge
import it.inps.sirio.theme.tableHeaderIconSize
import it.inps.sirio.theme.tableHeaderPaddingVerticalLarge
import it.inps.sirio.theme.tableHeaderSpacingCheckBoxTitle
import it.inps.sirio.theme.tableHeaderSpacingTitleIcon
import it.inps.sirio.ui.checkbox.SirioCheckboxCommon
import it.inps.sirio.ui.table.cell.SirioTableComponentCommon
import it.inps.sirio.ui.table.cell.SirioTableContentAlignment
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

@Composable
internal fun RowScope.SirioTableHeaderCommon(
    text: String,
    verticalPadding: Int,
    horizontalPadding: Int,
    alignment: SirioTableContentAlignment,
    themeMode: SirioThemeMode? = null,
    weight: Float = 1f,
    withCheckBox: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    showBorderEnd: Boolean = true,
    sortable: Boolean = false,
    sortDirection: SirioTableSortDirection = SirioTableSortDirection.None,
    sortContentDescriptionAsc: String = "Ordine crescente",
    sortContentDescriptionDesc: String = "Ordine decrescente",
    sortContentDescriptionNone: String = "Nessun ordinamento",
    sortStateDescriptionAsc: String = "Ordinamento: crescente",
    sortStateDescriptionDesc: String = "Ordinamento: decrescente",
    sortStateDescriptionNone: String = "Ordinamento: nessuno",
    onSortClick: (SirioTableSortDirection) -> Unit,
) {
    SirioTheme(themeMode) {
        SirioTableComponentCommon(
            borderColor = SirioTheme.colors.table.header.border,
            weight = weight,
            showBorderEnd = showBorderEnd,
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = SirioTheme.colors.table.header.background,
                contentColor = SirioTheme.colors.table.header.title,
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = horizontalPadding.dp)
                        .padding(vertical = verticalPadding.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (withCheckBox) {
                        SirioCheckboxCommon(checked = checked, onCheckedChange = onCheckedChange)
                        Spacer(modifier = Modifier.width(tableHeaderSpacingCheckBoxTitle.dp))
                    }
                    SirioTextCommon(
                        text = text,
                        modifier = Modifier.weight(1f),
                        color = LocalContentColor.current,
                        textAlign = if (alignment == SirioTableContentAlignment.Start) TextAlign.Start else TextAlign.End,
                        typography = SirioTheme.foundationTypography.labelMdHeavy,
                    )
                    if (sortable) {
                        Spacer(modifier = Modifier.width(tableHeaderSpacingTitleIcon.dp))

                        val iconSelectedColor = SirioTheme.colors.table.header.iconSelected
                        val iconDefaultColor = SirioTheme.colors.table.header.icon

                        val upColor = when (sortDirection) {
                            SirioTableSortDirection.Asc -> iconSelectedColor
                            else -> iconDefaultColor
                        }
                        val downColor = when (sortDirection) {
                            SirioTableSortDirection.Desc -> iconSelectedColor
                            else -> iconDefaultColor
                        }

                        val next = sortDirection.next()

                        val actionLabel = when (next) {
                            SirioTableSortDirection.Asc -> sortContentDescriptionAsc
                            SirioTableSortDirection.Desc -> sortContentDescriptionDesc
                            SirioTableSortDirection.None -> sortContentDescriptionNone
                        }

                        val currentStateLabel = when (sortDirection) {
                            SirioTableSortDirection.Asc -> sortStateDescriptionAsc
                            SirioTableSortDirection.Desc -> sortStateDescriptionDesc
                            SirioTableSortDirection.None -> sortStateDescriptionNone
                        }

                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .testTag("tableHeaderButton${text.takeTwoWords()}")
                                .semantics(mergeDescendants = true) {
                                    contentDescription = actionLabel
                                    stateDescription = currentStateLabel
                                }
                                .clickable(
                                    role = Role.Button,
                                    onClick = { onSortClick(next) }
                                )
                        ) {
                            SirioIcon(
                                icon = SirioIconSource.FaIcon(FaIcons.SortUp),
                                iconColor = upColor,
                                size = tableHeaderIconSize.dp,
                            )
                            SirioIcon(
                                icon = SirioIconSource.FaIcon(FaIcons.SortDown),
                                iconColor = downColor,
                                size = tableHeaderIconSize.dp,
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class SirioTableSortDirection {
    Asc, Desc, None;

    /**
     * Cycles to the next sort state in the order: None -> Asc -> Desc -> None.
     */
    fun next(): SirioTableSortDirection = when (this) {
        None -> Asc
        Asc -> Desc
        Desc -> None
    }
}

@Keep
data class SirioTableHeaderColors(
    val background: Color,
    val border: Color,
    val title: Color,
    val icon: Color,
    val iconSelected: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioTableHeaderColors(
            background = Color.Unspecified,
            border = Color.Unspecified,
            title = Color.Unspecified,
            icon = Color.Unspecified,
            iconSelected = Color.Unspecified,
        )
    }
}

internal val tableHeaderLightColors = SirioTableHeaderColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight50,
    border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    icon = FoundationColor.colorAliasInteractivePrimaryDefault,
    iconSelected = FoundationColor.colorAliasInteractivePrimary000Default,
)

internal val tableHeaderDarkColors = SirioTableHeaderColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryDark115,
    border = StyleDictionaryColor.colorAliasBackgroundColorPrimaryLight60,
    title = FoundationColor.colorAliasTextColorPrimaryLight0,
    icon = FoundationColor.colorAliasInteractiveAccentDefault,
    iconSelected = FoundationColor.colorAliasInteractivePrimary000Default,
)

@Preview
@Composable
private fun SirioTableHeaderCommonPreview() {
    SirioTheme {
        Column {
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header\nheader\nheader",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.End,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    themeMode = SirioThemeMode.Dark,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.End,
                    themeMode = SirioThemeMode.Dark,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.None,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.Asc,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = true,
                    sortDirection = SirioTableSortDirection.Desc,
                    onSortClick = {},
                )
            }
            Row(Modifier.height(IntrinsicSize.Max)) {
                SirioTableHeaderCommon(
                    text = "Header",
                    verticalPadding = tableHeaderPaddingVerticalLarge,
                    horizontalPadding = tableComponentPaddingHorizontalLarge,
                    alignment = SirioTableContentAlignment.Start,
                    withCheckBox = true,
                    onCheckedChange = {},
                    sortable = false,
                    sortDirection = SirioTableSortDirection.Desc,
                    onSortClick = {},
                )
            }
        }
    }
}

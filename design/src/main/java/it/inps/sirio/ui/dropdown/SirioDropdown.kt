//
// SirioDropdown.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.dropdown

import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownBorderWidth
import it.inps.sirio.theme.dropdownCornerSize
import it.inps.sirio.ui.popover.SirioPopoverData
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.textfield.SirioTextFieldCommon
import it.inps.sirio.ui.textfield.TextFieldState
import it.inps.sirio.utils.SirioIconSource
import it.inps.sirio.utils.takeTwoWords

/**
 * A dropdown component that allows users to select a value from a list.
 *
 * The dropdown is implemented using a [SirioTextFieldCommon] as its anchor, which displays
 * the current selection and a dropdown menu that appears on click.
 *
 * @param values The list of string values to be displayed in the dropdown menu.
 * @param modifier The [Modifier] to be applied to the dropdown.
 * @param selectedValue The currently selected value to be displayed in the text field.
 * @param onValueChange A callback that is triggered when a new value is selected from the dropdown menu.
 * @param placeholder The optional text to be displayed when no value is selected.
 * @param iconContentDescription The content description for the dropdown's chevron icon, for accessibility.
 * @param label An optional label displayed above the dropdown field.
 * @param popoverData Optional data to display a [SirioPopover] next to the label for providing extra information.
 * @param helperText Optional descriptive text displayed below the dropdown.
 * @param error A boolean indicating whether the dropdown should be displayed in an error state.
 * @param enabled A boolean indicating whether the dropdown is enabled for user interaction.
 *
 * @see SirioTextFieldCommon
 * @see SirioPopover
 */
@Composable
fun SirioDropdown(
    values: List<String>,
    modifier: Modifier = Modifier,
    selectedValue: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String? = null,
    iconContentDescription: String? = null,
    label: String? = null,
    popoverData: SirioPopoverData? = null,
    helperText: String? = null,
    error: Boolean = false,
    enabled: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    @OptIn(ExperimentalMaterial3Api::class)
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        SirioTextFieldCommon(
            modifier = modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .testTag("dropdown${label.takeTwoWords()}"),
            text = selectedValue,
            onValueChange = {},
            placeholder = placeholder,
            icon = SirioIconSource.FaIcon(FaIcons.ChevronDown),
            iconContentDescription = iconContentDescription,
            label = label,
            popoverData = popoverData,
            helperText = helperText,
            state = if (error) TextFieldState.Alert else null,
            enabled = enabled,
            onTextFieldClick = { expanded = values.isNotEmpty() },
        )
        ExposedDropdownMenu(
            modifier = Modifier.semantics{ testTagsAsResourceId = true },
            expanded = expanded,
            onDismissRequest = { expanded = false },
            matchAnchorWidth = true, //https://issuetracker.google.com/issues/205589613?pli=1
            shape = RoundedCornerShape(dropdownCornerSize.dp),
            containerColor = SirioTheme.colors.dropdown.item.container,
            border = BorderStroke(
                width = dropdownBorderWidth.dp,
                color = SirioTheme.colors.dropdown.item.border,
            )
        ) {
            values.forEachIndexed { index, selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.testTag("itemDropdown$index"),
                    text = {
                        SirioText(
                            text = selectionOption,
                            typography = SirioTheme.foundationTypography.labelMdRegular,
                        )
                    },
                    colors = MenuDefaults.itemColors(textColor = SirioTheme.colors.dropdown.item.content),
                    onClick = {
                        expanded = false
                        onValueChange(selectionOption)
                    }
                )
            }
        }
    }
}

@Keep
data class SirioDropdownColors(
    val item: SirioDropdownItemColors,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownColors(
            item = SirioDropdownItemColors.Unspecified,
        )
    }
}

@Keep
data class SirioDropdownItemColors(
    val container: Color,
    val border: Color,
    val content: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioDropdownItemColors(
            container = Color.Unspecified,
            border = Color.Unspecified,
            content = Color.Unspecified,
        )
    }
}

internal val dropdownLightColors = SirioDropdownColors(
    item = SirioDropdownItemColors(
        container = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
        border = FoundationColor.colorSpecificDataEntryBorderColorDefault,
        content = FoundationColor.colorSpecificDataEntryPlaceholderColorDefault,
    )
)

internal val dropdownDarkColors = dropdownLightColors

@Preview(showSystemUi = true)
@Composable
private fun SirioDropdownPreview() {
    SirioTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val values by remember {
                mutableStateOf(
                    listOf(
                        "Value 1\n2 lines",
                        "Value 2",
                        "Value 3"
                    )
                )
            }
            var text by remember { mutableStateOf("") }
            SirioDropdown(
                values = values,
                selectedValue = text,
                onValueChange = { text = it },
                placeholder = "Placeholder",
                label = "Label",
                popoverData = SirioPopoverData(text = "Popover text",),
                helperText = "Helper text",
                error = true,
            )
            SirioDropdown(
                values = emptyList(),
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
                enabled = false,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = true,
            )
            SirioDropdown(
                values = emptyList(),
                selectedValue = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
                popoverData = SirioPopoverData(text = "Popover text",),
            )
        }
    }
}

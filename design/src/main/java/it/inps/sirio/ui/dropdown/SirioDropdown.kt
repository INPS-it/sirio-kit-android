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
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dropdownBorderWidth
import it.inps.sirio.theme.dropdownCornerSize
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.textfield.SirioTextFieldCommon
import it.inps.sirio.ui.textfield.TextFieldState

/**
 * A dropdown component that displays a list of selectable values.
 *
 * @param values The array of string values to be displayed in the dropdown.
 * @param modifier Modifier to be applied to the dropdown layout.
 * @param text The currently selected text or the text input in the dropdown.
 * @param onValueChange A callback function invoked when the value changes.
 * @param placeholder Placeholder text to be displayed when no value is selected.
 * @param iconContentDescription Content description for the dropdown icon.
 * @param label Label text for the dropdown field.
 * @param onInfoClick Callback function invoked when the info icon is clicked.
 * @param infoContentDescription Content description for the info icon.
 * @param helperText Helper text to be displayed below the dropdown.
 * @param error Whether the dropdown is in an error state.
 * @param enabled Whether the dropdown is enabled or disabled.
 *
 * @see SirioTextFieldCommon
 */
@Composable
fun SirioDropdown(
    values: Array<String>,
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String? = null,
    iconContentDescription: String? = null,
    label: String? = null,
    onInfoClick: (() -> Unit)? = null,
    infoContentDescription: String? = null,
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
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            text = text,
            onValueChange = {},
            placeholder = placeholder,
            icon = FaIcons.ChevronDown,
            iconContentDescription = iconContentDescription,
            label = label,
            onInfoClick = onInfoClick,
            infoContentDescription = infoContentDescription,
            helperText = helperText,
            state = if (error) TextFieldState.Alert else null,
            enabled = enabled,
            onTextFieldClick = { expanded = values.isNotEmpty() },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(dropdownCornerSize.dp),
            containerColor = SirioTheme.colors.dropdown.item.container,
            border = BorderStroke(
                width = dropdownBorderWidth.dp,
                color = SirioTheme.colors.dropdown.item.border,
            )
        ) {
            values.forEach { selectionOption ->
                DropdownMenuItem(
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
                    arrayOf(
                        "Value 1\n2 lines",
                        "Value 2",
                        "Value 3"
                    )
                )
            }
            var text by remember { mutableStateOf("") }
            SirioDropdown(
                values = values,
                text = text,
                onValueChange = { text = it },
                placeholder = "Placeholder",
                label = "Label",
                onInfoClick = {},
                helperText = "Helper text",
                error = true,
            )
            SirioDropdown(
                values = emptyArray(),
                placeholder = "Placeholder",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
            )
            SirioDropdown(
                values = emptyArray(),
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
            )
            SirioDropdown(
                values = emptyArray(),
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
                enabled = false,
            )
            SirioDropdown(
                values = emptyArray(),
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = true,
            )
            SirioDropdown(
                values = emptyArray(),
                text = "Text",
                onValueChange = { },
                label = "Label",
                helperText = "Helper text",
                error = false,
                onInfoClick = {},
            )
        }
    }
}
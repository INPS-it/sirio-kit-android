//
// SirioFilterDrawerDate.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.filterDrawerDatePaddingHorizontal
import it.inps.sirio.theme.filterDrawerDatePaddingVertical
import it.inps.sirio.ui.textfield.SirioTextFieldCommon
import it.inps.sirio.utils.SirioIconSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SirioFilterDrawerDate(
    dateFormat: String,
    label: String? = null,
    selectedDate: String? = null,
    placeholder: String? = null,
    iconContentDescription: String? = null,
    minDateMillis: Long? = null,
    maxDateMillis: Long? = null,
    confirmButtonText: String = "OK",
    dismissButtonText: String = "Annulla",
    allowedYears: List<Int>? = null,
    onDateSelected: (String) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    val sdf = remember { SimpleDateFormat(dateFormat, Locale.getDefault()) }

    // Se ho già una data selezionata, la converto in millis
    val initialMillis = remember(selectedDate) {
        selectedDate?.let {
            try {
                sdf.parse(it)?.time
            } catch (e: Exception) {
                null
            }
        }
    }

    SirioTextFieldCommon(
        modifier = Modifier
            .fillMaxWidth()
            .background(SirioTheme.colors.filter.background)
            .padding(
                horizontal = filterDrawerDatePaddingHorizontal.dp,
                vertical = filterDrawerDatePaddingVertical.dp,
            ),
        text = selectedDate.orEmpty(),
        onValueChange = {},
        placeholder = placeholder,
        icon = SirioIconSource.FaIcon(FaIcons.CalendarDay),
        iconContentDescription = iconContentDescription,
        label = label,
        onIconClick = { openDialog = true },
        onTextFieldClick = { openDialog = true },
    )

    if (openDialog) {
        @OptIn(ExperimentalMaterial3Api::class)
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialMillis,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val afterMin = minDateMillis?.let { utcTimeMillis >= it } ?: true
                    val beforeMax = maxDateMillis?.let { utcTimeMillis <= it } ?: true
                    return afterMin && beforeMax
                }

                override fun isSelectableYear(year: Int): Boolean {
                    return allowedYears?.contains(year) ?: true
                }
            }
        )

        @OptIn(ExperimentalMaterial3Api::class)
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatted = sdf.format(Date(millis))
                            onDateSelected(formatted)
                        }
                        openDialog = false
                    }
                ) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text(dismissButtonText)
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false,
            )
        }
    }
}

@Preview
@Composable
private fun SirioFilterDrawerDatePreview() {
    SirioTheme {
        var date by remember { mutableStateOf<String?>(null) }
        SirioFilterDrawerDate(
            label = "Label",
            placeholder = "Text",
            dateFormat = "dd/MM/yyyy",
            selectedDate = date,
            onDateSelected = {
                date = it
            },
        )
    }
}
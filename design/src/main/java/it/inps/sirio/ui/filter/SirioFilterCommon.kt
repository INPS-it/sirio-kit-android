//
// SirioFilterCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
package it.inps.sirio.ui.filter

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.styleDictionary.StyleDictionaryColor
import it.inps.sirio.ui.button.SirioButtonHierarchy
import kotlinx.parcelize.Parcelize

/**
 * Represents the different kinds of filters supported by [SirioFilterDrawer].
 *
 * Each subclass defines its own UI type and data.
 */
@Parcelize
sealed class SirioFilterDrawerType(open val id: String) : Parcelable {

    abstract fun isActive(): Boolean
    abstract fun getDisplayValue(): String?
    abstract fun reset(): SirioFilterDrawerType

    @Parcelize
    data class Checkbox(
        override val id: String,
        val checked: Boolean,
        val text: String? = null,
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = checked
        override fun getDisplayValue() = if (checked) text else null
        override fun reset() = copy(checked = false)
    }

    @Parcelize
    data class Chip(
        override val id: String,
        val values: List<String>,
        val selectedValues: Set<String> = emptySet(),
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = selectedValues.isNotEmpty()
        override fun getDisplayValue() =
            if (selectedValues.isNotEmpty()) selectedValues.joinToString(", ") else null

        override fun reset() = copy(selectedValues = emptySet())
    }

    @Parcelize
    data class Date(
        override val id: String,
        val dateFormat: String,
        val label: String? = null,
        val selectedDate: String? = null,
        val placeholder: String? = null,
        val iconContentDescription: String? = null,
        val minDateMillis: Long? = null,
        val maxDateMillis: Long? = null,
        val confirmButtonText: String = "OK",
        val dismissButtonText: String = "Annulla",
        val allowedYears: List<Int>? = null,
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = !selectedDate.isNullOrBlank()
        override fun getDisplayValue() =
            selectedDate?.let { if (!label.isNullOrBlank()) "$label: $it" else it }

        override fun reset() = copy(selectedDate = null)
    }

    @Parcelize
    data class RadioGroup(
        override val id: String,
        val values: List<String>,
        val selectedValue: String? = null,
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = !selectedValue.isNullOrBlank()
        override fun getDisplayValue() = selectedValue
        override fun reset() = copy(selectedValue = null)
    }

    @Parcelize
    data class Select(
        override val id: String,
        val values: List<String>,
        val label: String? = null,
        val selectedValue: String? = null,
        val placeholder: String? = null,
        val iconContentDescription: String? = null,
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = !selectedValue.isNullOrBlank()
        override fun getDisplayValue() =
            selectedValue?.let { if (!label.isNullOrBlank()) "$label: $it" else it }

        override fun reset() = copy(selectedValue = null)
    }

    @Parcelize
    data class Title(
        override val id: String,
        val title: String? = null,
        val text: String? = null,
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = false
        override fun getDisplayValue() = null
        override fun reset() = this // non cambia
    }

    @Parcelize
    data class Toggle(
        override val id: String,
        val checked: Boolean,
        val text: String? = null,
    ) : SirioFilterDrawerType(id) {
        override fun isActive() = checked
        override fun getDisplayValue() = if (checked) text else null
        override fun reset() = copy(checked = false)
    }

    fun copyDeep(): SirioFilterDrawerType = when (this) {
        is Checkbox -> copy()
        is Chip -> copy(selectedValues = selectedValues.toSet())
        is Date -> copy()
        is RadioGroup -> copy()
        is Select -> copy()
        is Title -> copy()
        is Toggle -> copy()
    }
}

@Keep
data class SirioFilterColors(
    val background: Color,
    val close: SirioButtonHierarchy,
    val header: Color,
    val info: Color,
    val sectionTitle: Color,
    val title: Color,
    val text: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioFilterColors(
            background = Color.Unspecified,
            close = SirioButtonHierarchy.GhostLight,
            header = Color.Unspecified,
            info = Color.Unspecified,
            sectionTitle = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
        )
    }
}

internal val filterLightColors = SirioFilterColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    close = SirioButtonHierarchy.GhostLight,
    header = FoundationColor.colorAliasTextColorPrimaryDark110,
    info = StyleDictionaryColor.colorGlobalSemanticInfo100,
    sectionTitle = FoundationColor.colorAliasTextColorSecondaryDark100,
    title = FoundationColor.colorSpecificDataEntryLabelColorDefault,
    text = FoundationColor.colorSpecificDataEntryLabelColorDefault,
)

internal val filterDarkColors = filterLightColors

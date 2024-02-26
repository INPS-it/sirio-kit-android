//
// SirioDialog.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.dialog

import androidx.compose.runtime.Composable
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dialogHeightPercentage

/**
 * The Sirio dialog. It shows up in the [dialogHeightPercentage] bottom of the screen
 *
 * @param title The title of the dialog
 * @param text The text of the dialog
 * @param icon Whether the dialog has top icon
 * @param semantic The semantic of the dialog. See [DialogSemantic]
 * @param firstInputTitle The label above the first input field. Provide a title to show the input field
 * @param firstInputText The first input field text
 * @param secondInputTitle The label above the second input field. Provide a title to show the input field
 * @param secondInputText The second input field text
 * @param positiveButtonText The text on the colored button. The color depends on [semantic]. If null the button is hidden
 * @param neutralButtonText The text on the ghost button. If null the button is hidden
 * @param closeContentDescription The content description of the close button
 * @param onPositive The callback when the colored button is pressed. It return a [Pair] whit the input field values if present
 * @param onNeutral The callback when the colored button is pressed. It return a [Pair] whit the input field values if present
 * @param onDismiss The callback when the dialog is dismissed
 */
@Composable
fun SirioDialog(
    title: String,
    text: String? = null,
    icon: Boolean = true,
    semantic: DialogSemantic = DialogSemantic.INFO,
    firstInputTitle: String? = null,
    firstInputText: String? = null,
    secondInputTitle: String? = null,
    secondInputText: String? = null,
    positiveButtonText: String? = null,
    neutralButtonText: String? = null,
    closeContentDescription: String? = null,
    onPositive: (Pair<String?, String?>) -> Unit = {},
    onNeutral: (Pair<String?, String?>) -> Unit = {},
    onDismiss: () -> Unit,
) {
    val (semanticIcon, semanticIconColor, buttonColor) = when (semantic) {
        DialogSemantic.INFO -> Triple(
            if (icon) FaIcons.InfoCircle else null,
            SirioTheme.colors.dialog.semanticDefault,
            SirioTheme.colors.buttons.primary,
        )
        DialogSemantic.WARNING -> Triple(
            if (icon) FaIcons.ExclamationCircle else null,
            SirioTheme.colors.dialog.semanticWarning,
            SirioTheme.colors.buttons.primary,
        )
        DialogSemantic.ALERT -> Triple(
            if (icon) FaIcons.ExclamationTriangle else null,
            SirioTheme.colors.dialog.semanticAlert,
            SirioTheme.colors.buttons.danger,
        )
    }
    SirioDialogCommon(
        title = title,
        text = text,
        semanticIcon = semanticIcon,
        semanticIconColor = semanticIconColor,
        firstInputTitle = firstInputTitle,
        firstInputText = firstInputText,
        secondInputTitle = secondInputTitle,
        secondInputText = secondInputText,
        positiveButtonText = positiveButtonText,
        positiveButtonColor = buttonColor,
        neutralButtonText = neutralButtonText,
        closeContentDescription = closeContentDescription,
        onPositive = onPositive,
        onNeutral = onNeutral,
        onDismiss = onDismiss
    )
}

enum class DialogSemantic {
    INFO,
    WARNING,
    ALERT,
}
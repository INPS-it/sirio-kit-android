//
// SirioDialog.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.SirioButtonHierarchy

/**
 * Sirio Dialog
 * 
 * @param title The optional dialog title
 * @param text The optional dialog body text
 * @param withIcon Whether the icon based on semantic must be displayed or not, default is true
 * @param semantic The dialog semantic: [SirioDialogSemantic.INFO], [SirioDialogSemantic.WARNING], [SirioDialogSemantic.ALERT]. Default is [SirioDialogSemantic.INFO]
 * @param firstInputTitle The optional first input field title
 * @param firstInputPlaceholder The optional first input field placeholder
 * @param firstInputText The optional first input field text
 * @param secondInputTitle The optional second input field title
 * @param secondInputPlaceholder The optional second input field placeholder.
 * @param secondButtonHierarchy The [SirioButtonHierarchy] for the second button, default is [SirioButtonHierarchy.Secondary]
 * @param secondInputText The optional second input field text
 * @param firstButtonText The optional text of the positive button
 * @param secondButtonText The optional text of the neutral button
 * @param closeContentDescription The content description for the close button icon. If null, the button will not be shown
 * @param onFirstButtonClick The callback with the first input value as first field of the pair and the second input value as second field of the pair that is invoked when the positive button is pressed
 * @param onSecondButtonClick The callback that is invoked when the neutral button is pressed
 * @param onDismiss The callback that is invoked when the close button or the scrim area is pressed
 */
@Composable
fun SirioDialog(
    title: String? = null,
    text: String? = null,
    withIcon: Boolean = true,
    semantic: SirioDialogSemantic = SirioDialogSemantic.INFO,
    firstInputTitle: String? = null,
    firstInputPlaceholder: String? = null,
    firstInputText: String? = null,
    secondInputTitle: String? = null,
    secondInputPlaceholder: String? = null,
    secondInputText: String? = null,
    firstButtonText: String? = null,
    onFirstButtonClick: (Pair<String?, String?>) -> Unit = {},
    secondButtonText: String? = null,
    onSecondButtonClick: (Pair<String?, String?>) -> Unit = {},
    secondButtonHierarchy: SirioButtonHierarchy = SirioButtonHierarchy.Secondary,
    closeContentDescription: String? = null,
    onDismiss: () -> Unit,
) {
    SirioDialogCommon(
        title = title,
        text = text,
        withIcon = withIcon,
        semantic = semantic,
        firstInputTitle = firstInputTitle,
        firstInputPlaceholder = firstInputPlaceholder,
        firstInputText = firstInputText,
        secondInputTitle = secondInputTitle,
        secondInputPlaceholder = secondInputPlaceholder,
        secondInputText = secondInputText,
        firstButtonText = firstButtonText,
        onFirstButtonClick = onFirstButtonClick,
        secondButtonText = secondButtonText,
        secondButtonHierarchy = secondButtonHierarchy,
        onSecondButtonClick = onSecondButtonClick,
        closeContentDescription = closeContentDescription,
        onDismiss = onDismiss
    )
}

/**
 * The semantic of SirioDialog
 */
enum class SirioDialogSemantic {
    /**
     * Info semantic
     */
    INFO,

    /**
     * Warning semantic
     */
    WARNING,

    /**
     * Alert semantic
     */
    ALERT,
}

@Preview
@Composable
private fun SirioDialogStandardFullPreview() {
    SirioTheme {
        Box(Modifier.fillMaxSize()) {
            SirioDialog(
                title = "Titolo",
                text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                firstInputTitle = "Label",
                firstInputText = "Text",
                secondInputTitle = "Label",
                secondInputText = "Text",
                firstButtonText = "Text",
                secondButtonText = "Text",
                onFirstButtonClick = {},
                onSecondButtonClick = {},
                onDismiss = {},
            )
        }
    }
}

@Preview
@Composable
private fun SirioDialogStandardPreview() {
    SirioTheme {
        Box(Modifier.fillMaxSize()) {
            SirioDialog(
                title = "Titolo",
                text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                onDismiss = {},
            )
        }
    }
}

@Preview
@Composable
private fun SirioDialogSemanticAlertPreview() {
    SirioTheme {
        Box(Modifier.fillMaxSize()) {
            SirioDialog(
                title = "Titolo",
                text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                semantic = SirioDialogSemantic.ALERT,
                firstInputTitle = "Label",
                firstInputText = "Text",
                secondInputTitle = "Label",
                secondInputText = "Text",
                firstButtonText = "Text",
                secondButtonText = "Text",
                onFirstButtonClick = {},
                onSecondButtonClick = {},
                onDismiss = {},
            )
        }
    }
}

@Preview
@Composable
private fun SirioDialogSemanticWarningPreview() {
    SirioTheme {
        Box(Modifier.fillMaxSize()) {
            SirioDialog(
                title = "Titolo",
                text = "Lorem ipsum dolor sit amet consectetur. Amet nollis vestibulum in et ante tempor.",
                semantic = SirioDialogSemantic.WARNING,
                firstInputTitle = "Label",
                firstInputText = "Text",
                secondInputTitle = "Label",
                secondInputText = "Text",
                firstButtonText = "Text",
                secondButtonText = "Text",
                onFirstButtonClick = {},
                onSecondButtonClick = {},
                onDismiss = {},
            )
        }
    }
}

//
// SirioDialogCommon.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.dialog

import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dialogHeightPercentage
import it.inps.sirio.theme.dialogInputTopPadding
import it.inps.sirio.theme.dialogNeutralButtonTopPadding
import it.inps.sirio.theme.dialogPadding
import it.inps.sirio.theme.dialogPositiveButtonTopPadding
import it.inps.sirio.theme.dialogSemanticIconBottomPadding
import it.inps.sirio.theme.dialogShape
import it.inps.sirio.theme.dialogTextTopPadding
import it.inps.sirio.ui.button.ButtonSize
import it.inps.sirio.ui.button.SirioButtonColors
import it.inps.sirio.ui.button.SirioButtonCommon
import it.inps.sirio.ui.text.SirioTextCommon
import it.inps.sirio.ui.textfield.SirioTextFieldCommon
import it.inps.sirio.utils.SirioIcon

/**
 * The Sirio dialog implementation. It shows up in the [dialogHeightPercentage] bottom of the screen
 *
 * @param title The title of the dialog
 * @param text The text of the dialog
 * @param semanticIcon The FA icon on top-left of the dialog
 * @param semanticIconColor The color of the semantic icon
 * @param firstInputTitle The label above the first input field. Provide a title to show the input field
 * @param firstInputText The first input field text
 * @param secondInputTitle The label above the second input field. Provide a title to show the input field
 * @param secondInputText The second input field text
 * @param positiveButtonText The text on the colored button. If null the button is hidden
 * @param positiveButtonColor The color of the positive button
 * @param neutralButtonText The text on the ghost button. If null the button is hidden
 * @param closeContentDescription The content description of the close button
 * @param onPositive The callback when the colored button is pressed. It return a [Pair] whit the input field values if present
 * @param onNeutral The callback when the colored button is pressed. It return a [Pair] whit the input field values if present
 * @param onDismiss The callback when the dialog is dismissed
 */
@Composable
internal fun SirioDialogCommon(
    title: String,
    text: String?,
    semanticIcon: FaIconType?,
    semanticIconColor: Color,
    firstInputTitle: String?,
    firstInputText: String?,
    secondInputTitle: String?,
    secondInputText: String?,
    positiveButtonText: String?,
    positiveButtonColor: SirioButtonColors,
    neutralButtonText: String?,
    closeContentDescription: String? = null,
    onPositive: (Pair<String?, String?>) -> Unit,
    onNeutral: (Pair<String?, String?>) -> Unit,
    onDismiss: () -> Unit,
) {
    var firstInput by remember { mutableStateOf(firstInputText) }
    var secondInput by remember { mutableStateOf(secondInputText) }
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .placeToBottom()
                .fillMaxHeight(dialogHeightPercentage),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = dialogShape,
                color = SirioTheme.colors.dialog.background,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dialogPadding)
                        .verticalScroll(rememberScrollState()),
                ) {
                    SirioButtonCommon(
                        modifier = Modifier.align(Alignment.End),
                        size = ButtonSize.Large,
                        colors = SirioTheme.colors.buttons.ghost,
                        faIcon = FaIcons.Times,
                        iconContentDescription = closeContentDescription,
                        onClick = onDismiss,
                    )
                    Column(modifier = Modifier.semantics(mergeDescendants = true) {}) {
                        semanticIcon?.let {
                            SirioIcon(faIcon = semanticIcon, iconColor = semanticIconColor)
                            Spacer(modifier = Modifier.height(dialogSemanticIconBottomPadding))
                        }
                        SirioTextCommon(
                            text = title,
                            typography = SirioTheme.typography.dialogTitle,
                            color = SirioTheme.colors.dialog.title,
                        )
                        text?.let {
                            Spacer(modifier = Modifier.height(dialogTextTopPadding))
                            SirioTextCommon(
                                text = text,
                                typography = SirioTheme.typography.dialogText,
                                color = SirioTheme.colors.dialog.text,
                            )
                        }
                    }
                    firstInputTitle?.let { title ->
                        Spacer(modifier = Modifier.height(dialogInputTopPadding))
                        SirioTextFieldCommon(
                            text = firstInput.orEmpty(),
                            onValueChange = { value -> firstInput = value },
                            label = title,
                        )
                    }
                    secondInputTitle?.let { title ->
                        Spacer(modifier = Modifier.height(dialogInputTopPadding))
                        SirioTextFieldCommon(
                            text = secondInput.orEmpty(),
                            onValueChange = { value -> secondInput = value },
                            label = title,
                        )
                    }
                    positiveButtonText?.let {
                        Spacer(modifier = Modifier.height(dialogPositiveButtonTopPadding))
                        SirioButtonCommon(
                            size = ButtonSize.Large,
                            colors = positiveButtonColor,
                            modifier = Modifier.fillMaxWidth(),
                            text = it,
                            onClick = { onPositive(Pair(firstInput, secondInput)) },
                        )
                    }
                    neutralButtonText?.let {
                        Spacer(modifier = Modifier.height(dialogNeutralButtonTopPadding))
                        SirioButtonCommon(
                            size = ButtonSize.Large,
                            colors = SirioTheme.colors.buttons.ghost,
                            modifier = Modifier.fillMaxWidth(),
                            text = it,
                            onClick = { onNeutral(Pair(firstInput, secondInput)) },
                        )
                    }
                }
            }
        }
    }
}

/**
 * Custom modifier to place dialog on bottom of screen
 */
private fun Modifier.placeToBottom() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(constraints.maxWidth, constraints.maxHeight) {
        placeable.place(0, constraints.maxHeight - placeable.height, 10f)
    }
}

@Keep
data class SirioDialogColors(
    var background: Color,
    var semanticDefault: Color,
    var semanticAlert: Color,
    var semanticWarning: Color,
    var title: Color,
    var text: Color,
) {
    companion object {
        @Stable
        val Unspecified = SirioDialogColors(
            background = Color.Unspecified,
            semanticDefault = Color.Unspecified,
            semanticAlert = Color.Unspecified,
            semanticWarning = Color.Unspecified,
            title = Color.Unspecified,
            text = Color.Unspecified,
        )
    }
}

@Preview(showSystemUi = false, name = "default", device = Devices.DEFAULT)
@Preview(showSystemUi = false, name = "tablet", device = Devices.PIXEL_C)
@Composable
fun SirioDialogPreview() {
    SirioTheme {
        val title = "Titolo finestra di dialogo"
        val text =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        val firstInputTitle = "Label"
        val firstInputText = "Text"
        val secondInputTitle = "Label"
        val secondInputText = "Text"
        val positiveButtonText = "Text"
        val neutralButtonText = "Text"
        SirioDialogCommon(
            title = title,
            text = text,
            semanticIcon = FaIcons.InfoCircle,
            semanticIconColor = SirioTheme.colors.dialog.semanticDefault,
            firstInputTitle = firstInputTitle,
            firstInputText = firstInputText,
            secondInputTitle = secondInputTitle,
            secondInputText = secondInputText,
            positiveButtonText = positiveButtonText,
            positiveButtonColor = SirioTheme.colors.buttons.primary,
            neutralButtonText = neutralButtonText,
            onPositive = {},
            onNeutral = {},
            onDismiss = {},
        )
    }
}
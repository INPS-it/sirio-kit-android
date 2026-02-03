//
// SirioDialogCommon.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.sirio.ui.dialog

import android.view.Gravity
import androidx.annotation.Keep
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.foundation.FoundationColor
import it.inps.sirio.styleDictionary.StyleDictionaryBoxShadow
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.theme.dialogButtonFirstPaddingTop
import it.inps.sirio.theme.dialogButtonSecondPaddingTop
import it.inps.sirio.theme.dialogCornerRadiusBottom
import it.inps.sirio.theme.dialogCornerRadiusTop
import it.inps.sirio.theme.dialogHeaderPaddingBottom
import it.inps.sirio.theme.dialogHeightPercentage
import it.inps.sirio.theme.dialogIconSize
import it.inps.sirio.theme.dialogInputFirstPaddingTop
import it.inps.sirio.theme.dialogInputSecondPaddingTop
import it.inps.sirio.theme.dialogPaddingBottom
import it.inps.sirio.theme.dialogPaddingHorizontal
import it.inps.sirio.theme.dialogPaddingTop
import it.inps.sirio.theme.dialogTextPaddingTop
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonHierarchy
import it.inps.sirio.ui.button.SirioButtonSize
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.textfield.SirioTextField
import it.inps.sirio.utils.SirioIcon
import it.inps.sirio.utils.SirioIconData
import it.inps.sirio.utils.SirioIconSource

/**
 * Composable function that represents the common structure of a Sirio dialog.
 *
 * @param title The title of the dialog.
 * @param text The descriptive text of the dialog. Nullable.
 * @param withIcon Whether to display an icon in the dialog.
 * @param semantic The semantic type of the dialog, influencing icon and button styles.
 * @param firstInputTitle The title for the first input field. Nullable.
 * @param firstInputText The initial text for the first input field. Nullable.
 * @param firstInputPlaceholder The placeholder for the first input field. Nullable.
 * @param secondInputTitle The title for the second input field. Nullable.
 * @param secondInputText The initial text for the second input field. Nullable.
 * @param secondInputPlaceholder The placeholder for the second input field. Nullable.
 * @param firstButtonText The text for the positive action button. Nullable.
 * @param secondButtonText The text for the neutral action button. Nullable.
 * @param closeContentDescription Optional content description for the close button for accessibility.
 * @param onFirstButtonClick Callback invoked when the positive button is clicked. It receives a Pair of Strings representing the values of the two input fields.
 * @param onSecondButtonClick Callback invoked when the neutral button is clicked. It receives a Pair of Strings representing the values of the two input fields.
 * @param onDismiss Callback invoked when the dialog is dismissed (e.g., by clicking the close button or pressing the back button).
 */
@Composable
internal fun SirioDialogCommon(
    title: String?,
    text: String?,
    withIcon: Boolean,
    semantic: SirioDialogSemantic,
    firstInputTitle: String?,
    firstInputText: String?,
    firstInputPlaceholder: String?,
    secondInputTitle: String?,
    secondInputText: String?,
    secondInputPlaceholder: String?,
    firstButtonText: String?,
    onFirstButtonClick: (Pair<String?, String?>) -> Unit,
    secondButtonText: String?,
    onSecondButtonClick: (Pair<String?, String?>) -> Unit,
    secondButtonHierarchy: SirioButtonHierarchy,
    closeContentDescription: String? = null,
    onDismiss: () -> Unit,
) {
    val (semanticIcon, semanticIconColor, firstButtonHierarchy) = when (semantic) {
        SirioDialogSemantic.INFO -> Triple(
            if (withIcon) FaIcons.InfoCircle else null,
            SirioTheme.colors.dialog.semanticDefault,
            SirioButtonHierarchy.Primary,
        )

        SirioDialogSemantic.WARNING -> Triple(
            if (withIcon) FaIcons.InfoCircle else null,
            SirioTheme.colors.dialog.semanticWarning,
            SirioButtonHierarchy.Primary,
        )

        SirioDialogSemantic.ALERT -> Triple(
            if (withIcon) FaIcons.ExclamationTriangle else null,
            SirioTheme.colors.dialog.semanticAlert,
            SirioButtonHierarchy.Danger,
        )
    }
    var firstInput by remember { mutableStateOf(firstInputText) }
    var secondInput by remember { mutableStateOf(secondInputText) }

    val elevation =
        with(LocalDensity.current) { StyleDictionaryBoxShadow.elevationElevation04.blurRadius.toDp() }
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
        Box(
            modifier = Modifier
                .fillMaxHeight(dialogHeightPercentage)
                .semantics { testTagsAsResourceId = true },
            contentAlignment = Alignment.BottomCenter,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .shadow(
                        ambientColor = StyleDictionaryBoxShadow.elevationElevation04.color,
                        elevation = elevation
                    ),
                shape = RoundedCornerShape(
                    topStart = dialogCornerRadiusTop.dp,
                    topEnd = dialogCornerRadiusTop.dp,
                    bottomStart = dialogCornerRadiusBottom.dp,
                    bottomEnd = dialogCornerRadiusBottom.dp
                ),
                shadowElevation = elevation,
                color = SirioTheme.colors.dialog.background,
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = dialogPaddingHorizontal.dp)
                        .padding(top = dialogPaddingTop.dp, bottom = dialogPaddingBottom.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    SirioDialogHeader(
                        icon = semanticIcon?.let { SirioIconSource.FaIcon(it) },
                        iconColor = semanticIconColor,
                        closeContentDescription = closeContentDescription,
                        onClose = onDismiss
                    )
                    SirioDialogContainer(title = title, text = text)
                    if (semantic == SirioDialogSemantic.INFO) {
                        SirioDialogInput(
                            firstInputTitle = firstInputTitle,
                            firstInput = firstInput,
                            firstInputPlaceholder = firstInputPlaceholder,
                            onFirstInputChange = { value -> firstInput = value },
                            secondInputTitle = secondInputTitle,
                            secondInput = secondInput,
                            secondInputPlaceholder = secondInputPlaceholder,
                        ) { value -> secondInput = value }
                    }
                    SirioDialogButtons(
                        firstButtonText = firstButtonText,
                        firstButtonHierarchy = firstButtonHierarchy,
                        onFirstButtonClick = { onFirstButtonClick(Pair(firstInput, secondInput)) },
                        secondButtonText = secondButtonText,
                        secondButtonHierarchy = secondButtonHierarchy,
                    ) {
                        onSecondButtonClick(
                            Pair(
                                firstInput,
                                secondInput
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * Internal composable for rendering the header section of a Sirio dialog.
 *
 * @param icon An optional [SirioIconSource] to display in the header.
 * @param iconColor The color of the [icon].
 * @param closeContentDescription Optional content description for the close button, for accessibility.
 * @param onClose A callback function invoked when the close button is clicked.
 */
@Composable
private fun ColumnScope.SirioDialogHeader(
    icon: SirioIconSource?,
    iconColor: Color,
    closeContentDescription: String? = null,
    onClose: () -> Unit,
) {
    SirioButton(
        size = SirioButtonSize.Medium,
        hierarchy = SirioButtonHierarchy.GhostLight,
        modifier = Modifier
            .align(alignment = Alignment.End)
            .testTag("buttonClose"),
        icon = SirioIconSource.FaIcon(FaIcons.Times),
        iconContentDescription = closeContentDescription,
        onClick = onClose
    )
    icon?.let {
        SirioIcon(
            iconData = SirioIconData(
                icon = it,
                iconColor = iconColor,
                size = dialogIconSize.dp,
            )
        )
    }
    Spacer(Modifier.height(dialogHeaderPaddingBottom.dp))
}

/**
 * Internal composable for rendering the main content area (title and text) of a Sirio dialog.
 *
 * @param title The main title of the dialog.
 * @param text An optional descriptive text to be displayed below the title.
 */
@Composable
private fun SirioDialogContainer(title: String?, text: String? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {},
    ) {
        title?.let {
            SirioText(
                text = it,
                color = SirioTheme.colors.dialog.title,
                typography = SirioTheme.foundationTypography.headlineSmHeavy,
            )
        }
        text?.let {
            Spacer(Modifier.height(dialogTextPaddingTop.dp))
            SirioText(
                text = it,
                color = SirioTheme.colors.dialog.text,
                typography = SirioTheme.foundationTypography.bodyMdRegular,
            )
        }
    }
}

/**
 * Internal composable for rendering the input fields within a Sirio dialog.
 *
 * @param firstInputTitle An optional title for the first input field.
 * @param firstInput The current text of the first input field.
 * @param onFirstInputChange A callback function invoked when the text of the first input field changes.
 * @param secondInputTitle An optional title for the second input field.
 * @param secondInput The current text of the second input field.
 * @param onSecondInputChange A callback function invoked when the text of the second input field changes.
 */
@Composable
private fun SirioDialogInput(
    firstInputTitle: String?,
    firstInput: String?,
    firstInputPlaceholder: String?,
    onFirstInputChange: (String) -> Unit,
    secondInputTitle: String?,
    secondInput: String?,
    secondInputPlaceholder: String?,
    onSecondInputChange: (String) -> Unit,
) {
    firstInput?.let {
        Spacer(Modifier.height(dialogInputFirstPaddingTop.dp))
        SirioTextField(
            text = it,
            modifier = Modifier.testTag("textFieldFirstInput"),
            onValueChange = onFirstInputChange,
            placeholder = firstInputPlaceholder,
            label = firstInputTitle,
        )
    }
    secondInput?.let {
        Spacer(Modifier.height(dialogInputSecondPaddingTop.dp))
        SirioTextField(
            text = it,
            modifier = Modifier.testTag("textFieldSecondInput"),
            onValueChange = onSecondInputChange,
            placeholder = secondInputPlaceholder,
            label = secondInputTitle,
        )
    }
}

/**
 * Internal composable for rendering the action buttons (positive and neutral) at the bottom of a Sirio dialog.
 *
 * @param firstButtonText The text for the positive action button. If null, the button is not shown.
 * @param firstButtonHierarchy The [SirioButtonHierarchy] for the positive action button.
 * @param onFirstButtonClick A callback function invoked when the positive action button is clicked.
 * @param secondButtonText The text for the neutral action button. If null, the button is not shown.
 * @param onSecondButtonClick A callback function invoked when the neutral action button is clicked.
 */
@Composable
private fun SirioDialogButtons(
    firstButtonText: String?,
    firstButtonHierarchy: SirioButtonHierarchy,
    onFirstButtonClick: () -> Unit,
    secondButtonText: String?,
    secondButtonHierarchy: SirioButtonHierarchy,
    onSecondButtonClick: () -> Unit,
) {
    firstButtonText?.let {
        Spacer(modifier = Modifier.height(dialogButtonFirstPaddingTop.dp))
        SirioButton(
            size = SirioButtonSize.Large,
            hierarchy = firstButtonHierarchy,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("buttonFirst"),
            text = it,
            onClick = onFirstButtonClick,
        )
    }
    secondButtonText?.let {
        Spacer(modifier = Modifier.height(dialogButtonSecondPaddingTop.dp))
        SirioButton(
            size = SirioButtonSize.Large,
            hierarchy = secondButtonHierarchy,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("buttonSecond"),
            text = it,
            onClick = onSecondButtonClick,
        )
    }
}

/**
 * Data class representing the various colors used in a Sirio dialog.
 *
 * @param background The background color of the dialog.
 * @param semanticDefault The color for default semantic icons (e.g., information).
 * @param semanticAlert The color for alert semantic icons (e.g., error).
 * @param semanticWarning The color for warning semantic icons.
 * @param title The color of the dialog title text.
 * @param text The color of the dialog descriptive text.
 */
@Keep
data class SirioDialogColors(
    val background: Color,
    val semanticDefault: Color,
    val semanticAlert: Color,
    val semanticWarning: Color,
    val title: Color,
    val text: Color,
) {
    companion object {
        /**
         * Represents a state where no specific colors are defined for the dialog.
         */
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

/**
 * Default light theme colors for Sirio dialogs.
 */
internal val dialogLightColors = SirioDialogColors(
    background = FoundationColor.colorAliasBackgroundColorPrimaryLight0,
    semanticDefault = FoundationColor.colorGlobalSemanticInfo100,
    semanticAlert = FoundationColor.colorGlobalSemanticAlert100,
    semanticWarning = FoundationColor.colorGlobalSemanticWarning100,
    title = FoundationColor.colorAliasTextColorSecondaryDark100,
    text = FoundationColor.colorAliasTextColorSecondaryDark100,
)

/**
 * Default dark theme colors for Sirio dialogs. Currently, these are the same as the light theme colors.
 */
internal val dialogDarkColors = dialogLightColors

@Preview(showSystemUi = true, name = "default", device = Devices.DEFAULT)
@Preview(showSystemUi = true, name = "tablet", device = Devices.PIXEL_C)
@Composable
fun SirioDialogCommonPreview() {
    SirioTheme {
        val title = "Titolo finestra di dialogo"
        val text =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        val firstInputTitle = "Label"
        val firstInputText = "Text"
        val secondInputTitle = "Label2"
        val secondInputText = "Text2"
        val firstButtonText = "Text"
        val secondButtonText = "Text"
        Box(Modifier.fillMaxSize()) {
            SirioDialogCommon(
                title = title,
                text = text,
                withIcon = true,
                semantic = SirioDialogSemantic.INFO,
                firstInputTitle = firstInputTitle,
                firstInputPlaceholder = null,
                firstInputText = firstInputText,
                secondInputTitle = secondInputTitle,
                secondInputText = secondInputText,
                secondInputPlaceholder = null,
                firstButtonText = firstButtonText,
                onFirstButtonClick = {},
                secondButtonText = secondButtonText,
                secondButtonHierarchy = SirioButtonHierarchy.Secondary,
                onSecondButtonClick = {},
                onDismiss = {},
            )
        }
    }
}

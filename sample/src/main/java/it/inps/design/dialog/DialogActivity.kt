//
// DialogActivity.kt
//
// SPDX-FileCopyrightText: 2023 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.dialog.DialogSemantic
import it.inps.sirio.ui.dialog.SirioDialog
import it.inps.design.ui.DemoMenuItem

class DialogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                DialogMenuDemo()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DialogMenuDemo() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dialog") }, backgroundColor = SirioTheme.colors.brand,
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
        ) {
            var dialogType by remember { mutableStateOf(DemoDialogType.NONE) }
//            DemoMenuItem("Dialog Action Text 2 Input + Icon") {
//                dialogType = DemoDialogType.ACTION_ICON_TITLE_TEXT_2_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Action Text 2 Input") {
//                dialogType = DemoDialogType.ACTION_TITLE_TEXT_2_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Action Text 1 Input + Icon") {
//                dialogType = DemoDialogType.ACTION_ICON_TITLE_TEXT_1_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Action Text 1 Input") {
//                dialogType = DemoDialogType.ACTION_TITLE_TEXT_1_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Action Text + Icon") {
//                dialogType = DemoDialogType.ACTION_ICON_TITLE_TEXT
//            }
//            Divider()
//            DemoMenuItem("Dialog Action Text") {
//                dialogType = DemoDialogType.ACTION_TITLE_TEXT
//            }
//            Divider()
//            DemoMenuItem("Dialog Action + Icon") {
//                dialogType = DemoDialogType.ACTION_ICON_TITLE
//            }
//            Divider()
//            DemoMenuItem("Dialog Action") {
//                dialogType = DemoDialogType.ACTION_TITLE
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm Text 2 Input + Icon") {
//                dialogType = DemoDialogType.CONFIRM_ICON_TITLE_TEXT_2_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm Text 2 Input") {
//                dialogType = DemoDialogType.CONFIRM_TITLE_TEXT_2_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm Text 1 Input + Icon") {
//                dialogType = DemoDialogType.CONFIRM_ICON_TITLE_TEXT_1_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm Text 1 Input") {
//                dialogType = DemoDialogType.CONFIRM_TITLE_TEXT_1_INPUT
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm Text + Icon") {
//                dialogType = DemoDialogType.CONFIRM_ICON_TITLE_TEXT
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm Text") {
//                dialogType = DemoDialogType.CONFIRM_TITLE_TEXT
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm + Icon") {
//                dialogType = DemoDialogType.CONFIRM_ICON_TITLE
//            }
//            Divider()
//            DemoMenuItem("Dialog Confirm") {
//                dialogType = DemoDialogType.CONFIRM_TITLE
//            }
//            Divider()
//            DemoMenuItem("Dialog Passive + Icon") {
//                dialogType = DemoDialogType.PASSIVE_ICON
//            }
//            Divider()
//            DemoMenuItem("Dialog Passive") {
//                dialogType = DemoDialogType.PASSIVE
//            }
//            Divider()
//            DemoMenuItem("Dialog Alert Text + Icon") {
//                dialogType = DemoDialogType.SEMANTIC_ALERT_ICON
//            }
//            Divider()
//            DemoMenuItem("Dialog Alert Text") {
//                dialogType = DemoDialogType.SEMANTIC_ALERT
//            }
//            Divider()
//            DemoMenuItem("Dialog Warning Text + Icon") {
//                dialogType = DemoDialogType.SEMANTIC_WARNING_ICON
//            }
//            Divider()
//            DemoMenuItem("Dialog Warning Text") {
//                dialogType = DemoDialogType.SEMANTIC_WARNING
//            }
//            Divider()
            DemoMenuItem("Dialog 1") {
                dialogType = DemoDialogType.ACTION_ICON_TITLE_TEXT_2_INPUT
            }
            Divider()
            DemoMenuItem("Dialog 2") {
                dialogType = DemoDialogType.ACTION_ICON_TITLE_TEXT_1_INPUT
            }
            Divider()
            DemoMenuItem("Dialog 3") {
                dialogType = DemoDialogType.ACTION_ICON_TITLE_TEXT
            }
            Divider()
            DemoMenuItem("Dialog 4") {
                dialogType = DemoDialogType.ACTION_ICON_TITLE
            }
            Divider()
            DemoMenuItem("Dialog 5") {
                dialogType = DemoDialogType.PASSIVE_ICON
            }
            Divider()
            DemoMenuItem("Dialog Alert") {
                dialogType = DemoDialogType.SEMANTIC_ALERT_ICON
            }
            Divider()
            DemoMenuItem("Dialog Warning") {
                dialogType = DemoDialogType.SEMANTIC_WARNING_ICON
            }
            Divider()
            val title = "Titolo finestra di dialogo"
            val text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            val firstInputTitle = "Label"
            val firstInputText = "Text"
            val secondInputTitle = "Label"
            val secondInputText = "Text"
            val positiveButtonText = "Text"
            val neutralButtonText = "Text"
            when (dialogType) {
                DemoDialogType.NONE -> {}
                DemoDialogType.ACTION_ICON_TITLE_TEXT_2_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        secondInputTitle = secondInputTitle,
                        secondInputText = secondInputText,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_TITLE_TEXT_2_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        secondInputTitle = secondInputTitle,
                        secondInputText = secondInputText,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_ICON_TITLE_TEXT_1_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_TITLE_TEXT_1_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_ICON_TITLE_TEXT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_TITLE_TEXT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_ICON_TITLE ->
                    SirioDialog(
                        title = title,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.ACTION_TITLE ->
                    SirioDialog(
                        title = title,
                        icon = false,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_ICON_TITLE_TEXT_2_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        secondInputTitle = secondInputTitle,
                        secondInputText = secondInputText,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_TITLE_TEXT_2_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        secondInputTitle = secondInputTitle,
                        secondInputText = secondInputText,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_ICON_TITLE_TEXT_1_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_TITLE_TEXT_1_INPUT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        firstInputTitle = firstInputTitle,
                        firstInputText = firstInputText,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_ICON_TITLE_TEXT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_TITLE_TEXT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_ICON_TITLE ->
                    SirioDialog(
                        title = title,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.CONFIRM_TITLE ->
                    SirioDialog(
                        title = title,
                        icon = false,
                        positiveButtonText = positiveButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.PASSIVE_ICON ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.INFO,
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.PASSIVE ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.SEMANTIC_ALERT_ICON ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.ALERT,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.SEMANTIC_ALERT ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        semantic = DialogSemantic.ALERT,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )

                DemoDialogType.SEMANTIC_WARNING_ICON ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = true,
                        semantic = DialogSemantic.WARNING,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
                DemoDialogType.SEMANTIC_WARNING ->
                    SirioDialog(
                        title = title,
                        text = text,
                        icon = false,
                        semantic = DialogSemantic.WARNING,
                        positiveButtonText = positiveButtonText,
                        neutralButtonText = neutralButtonText,
                        onPositive = { dialogType = DemoDialogType.NONE },
                        onNeutral = { dialogType = DemoDialogType.NONE },
                        onDismiss = { dialogType = DemoDialogType.NONE },
                    )
            }
        }
    }
}

enum class DemoDialogType {
    NONE,
    ACTION_ICON_TITLE_TEXT_2_INPUT,
    ACTION_TITLE_TEXT_2_INPUT,
    ACTION_ICON_TITLE_TEXT_1_INPUT,
    ACTION_TITLE_TEXT_1_INPUT,
    ACTION_ICON_TITLE_TEXT,
    ACTION_TITLE_TEXT,
    ACTION_ICON_TITLE,
    ACTION_TITLE,
    CONFIRM_ICON_TITLE_TEXT_2_INPUT,
    CONFIRM_TITLE_TEXT_2_INPUT,
    CONFIRM_ICON_TITLE_TEXT_1_INPUT,
    CONFIRM_TITLE_TEXT_1_INPUT,
    CONFIRM_ICON_TITLE_TEXT,
    CONFIRM_TITLE_TEXT,
    CONFIRM_ICON_TITLE,
    CONFIRM_TITLE,
    PASSIVE_ICON,
    PASSIVE,
    SEMANTIC_ALERT_ICON,
    SEMANTIC_ALERT,
    SEMANTIC_WARNING_ICON,
    SEMANTIC_WARNING,
}

@Preview
@Composable
private fun DialogDemoViewPreview() {
    Column(
        Modifier.fillMaxSize()
    ) {
        SirioTheme(darkTheme = false) {
            DialogMenuDemo()
        }
    }
}
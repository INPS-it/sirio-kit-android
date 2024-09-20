package it.inps.sirio.ui.avviso

import androidx.compose.runtime.Composable

@Composable
fun SirioAvviso(
    title: String,
    text: String,
    buttonText: String,
    onButtonClick: () -> Unit,
) {
    SirioAvvisoCommon(
        title = title,
        text = text,
        buttonText = buttonText,
        onButtonClick = onButtonClick,
    )
}
package it.inps.design

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.button.ButtonStyle
import it.inps.sirio.ui.button.SirioButton
import it.inps.sirio.ui.button.SirioButtonSize

@Composable
fun Example() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SirioButton(text = "Text", size = SirioButtonSize.Medium, style = ButtonStyle.Primary) {}
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ExamplePreview() {
    SirioTheme {
        Example()
    }
}

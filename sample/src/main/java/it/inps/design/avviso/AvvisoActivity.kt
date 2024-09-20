@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.avviso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.avviso.SirioAvviso

class AvvisoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                AvvisoDemoContent()
            }
        }
    }
}


@Composable
fun AvvisoDemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Avviso") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val title = "Titolo avviso"
            val text =
                "Ac iaculis posuere turpis diam mi non viverra tempus eget. Nunc volutpat nunc erat risus eleifend convallis viverra bibendum. Mattis ante mauris sit montes. Scelerisque dui arcu tempus proin massa massa ultricies nunc duis."
            val buttonText = "Text"
            val onButtonClick = {}
            SirioTheme {
                SirioAvviso(
                    title = title,
                    text = text,
                    buttonText = buttonText,
                    onButtonClick = onButtonClick,
                )
            }
            SirioTheme(darkTheme = true) {
                SirioAvviso(
                    title = title,
                    text = text,
                    buttonText = buttonText,
                    onButtonClick = onButtonClick,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AvvisoActivityPreview() {
    SirioTheme {
        AvvisoDemoContent()
    }
}
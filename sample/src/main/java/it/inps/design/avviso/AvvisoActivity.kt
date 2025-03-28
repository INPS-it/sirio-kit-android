@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.avviso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            val title = "Titolo avviso"
            val text = "Lorem ipsum dolor sit amet consectetur. Amet mollis vestibulum in et ante tempor."
            val buttonText = "Link opzionale"
            val onButtonClick = {}
            SirioTheme {
                SirioAvviso(
                    title = title,
                    text = text,
                )
            }
            SirioTheme(darkTheme = true) {
                SirioAvviso(
                    title = title,
                    text = text,
                    linkText = buttonText,
                    onLinkClick = onButtonClick,
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
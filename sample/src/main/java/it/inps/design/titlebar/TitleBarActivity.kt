@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.titlebar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.fontawesomecomposelib.FaIcons
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.titlebar.SirioTitleBar
import it.inps.sirio.ui.titlebar.SirioTitleBarItemData

class TitleBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                TitleBarDemoView()
            }
        }
    }
}

@Composable
private fun TitleBarDemoView() {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "TitleBar") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            val title = "Titolo"
            val items = listOf(
                SirioTitleBarItemData(
                    icon = FaIcons.Filter,
                    text = "",
                    contentDescription = null,
                    action = {},
                ),
                SirioTitleBarItemData(
                    icon = FaIcons.EllipsisH,
                    text = "",
                    contentDescription = null,
                    action = {},
                )
            )
            Text(
                text = "Light",
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
            SirioTheme(darkTheme = false) {
                SirioTitleBar(title = title, items = items)
            }
            Text(
                text = "Dark",
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
            SirioTheme(darkTheme = true) {
                SirioTitleBar(title = title, items = items)
            }
        }
    }
}

@Preview
@Composable
private fun TitleBarActivityPreview() {
    TitleBarDemoView()
}
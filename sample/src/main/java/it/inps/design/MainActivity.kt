//
// MainActivity.kt
//
// SPDX-FileCopyrightText: 2022 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

package it.inps.design

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.inps.sirio.theme.SirioTheme
import it.inps.design.accordion.AccordionActivity
import it.inps.design.appnavigation.AppNavigationActivity
import it.inps.design.button.ButtonActivity
import it.inps.design.checkbox.CheckboxActivity
import it.inps.design.chip.ChipActivity
import it.inps.design.dialog.DialogActivity
import it.inps.design.fab.FabActivity
import it.inps.design.fileupload.FileUploadActivity
import it.inps.design.notification.NotificationActivity
import it.inps.design.pagination.PaginationActivity
import it.inps.design.tabs.TabActivity
import it.inps.design.progressbar.ProgressBarActivity
import it.inps.design.radiobutton.RadioButtonActivity
import it.inps.design.searchbar.SearchBarActivity
import it.inps.design.slider.SliderActivity
import it.inps.design.tabbar.TabBarActivity
import it.inps.design.tag.TagActivity
import it.inps.design.textfield.TextFieldActivity
import it.inps.design.toggle.ToggleActivity
import it.inps.design.ui.DemoMenuItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SirioTheme {
                // A surface container using the 'background' color from the theme
                DemoContent()
            }
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DemoContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("INPS Sirio Kit") },
                backgroundColor = SirioTheme.colors.brand
            )
        },
        content = {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .padding(0.dp, 16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                DemoMenuItem("Accordion") {
                    context.startActivity(Intent(context, AccordionActivity::class.java))
                }
                Divider()
                DemoMenuItem("App Navigation") {
                    context.startActivity(Intent(context, AppNavigationActivity::class.java))
                }
                Divider()
                DemoMenuItem("Buttons") {
                    context.startActivity(Intent(context, ButtonActivity::class.java))
                }
                Divider()
                DemoMenuItem("Checkbox") {
                    context.startActivity(Intent(context, CheckboxActivity::class.java))
                }
                Divider()
                DemoMenuItem("Chips") {
                    context.startActivity(Intent(context, ChipActivity::class.java))
                }
                Divider()
                DemoMenuItem("Dialog") {
                    context.startActivity(Intent(context, DialogActivity::class.java))
                }
                Divider()
                DemoMenuItem(title = "Fab") {
                    context.startActivity(Intent(context, FabActivity::class.java))
                }
                Divider()
                DemoMenuItem("File upload") {
                    context.startActivity(Intent(context, FileUploadActivity::class.java))
                }
                Divider()
                DemoMenuItem("Notification") {
                    context.startActivity(Intent(context, NotificationActivity::class.java))
                }
                Divider()
//                DemoMenuItem("Notification inline") {
//                    context.startActivity(Intent(context, NotificationInlineActivity::class.java))
//                }
//                Divider()
//                DemoMenuItem("Notification toast") {
//                    context.startActivity(Intent(context, NotificationToastActivity::class.java))
//                }
//                Divider()
                DemoMenuItem("Pagination") {
                    context.startActivity(Intent(context, PaginationActivity::class.java))
                }
                Divider()
                DemoMenuItem("Progress bar") {
                    context.startActivity(Intent(context, ProgressBarActivity::class.java))
                }
                Divider()
                DemoMenuItem("Radio button") {
                    context.startActivity(Intent(context, RadioButtonActivity::class.java))
                }
                Divider()
                DemoMenuItem("Search Bar") {
                    context.startActivity(Intent(context, SearchBarActivity::class.java))
                }
                Divider()
                DemoMenuItem("Slider") {
                    context.startActivity(Intent(context, SliderActivity::class.java))
                }
                Divider()
                DemoMenuItem("Tab") {
                    context.startActivity(Intent(context, TabActivity::class.java))
                }
                Divider()
                DemoMenuItem("Tab Bar") {
                    context.startActivity(Intent(context, TabBarActivity::class.java))
                }
                Divider()
                DemoMenuItem("Tag") {
                    context.startActivity(Intent(context, TagActivity::class.java))
                }
                Divider()
                DemoMenuItem("TextField") {
                    context.startActivity(Intent(context, TextFieldActivity::class.java))
                }
                Divider()
                DemoMenuItem("Toggle") {
                    context.startActivity(Intent(context, ToggleActivity::class.java))
                }
//                Divider()
//                DemoMenuItem("Demo") {
//                    context.startActivity(Intent(context, DemoActivity::class.java))
//                }
            }
        }
    )
}

@Preview(device = Devices.NEXUS_10)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SirioTheme {
        DemoContent()
    }
}
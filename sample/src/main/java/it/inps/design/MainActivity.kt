//
// MainActivity.kt
//
// SPDX-FileCopyrightText: 2024 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//

@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import it.inps.design.accordion.AccordionActivity
import it.inps.design.appnavigation.AppNavigationActivity
import it.inps.design.button.ButtonActivity
import it.inps.design.card.CardActivity
import it.inps.design.carousel.CarouselActivity
import it.inps.design.checkbox.CheckboxActivity
import it.inps.design.chip.ChipActivity
import it.inps.design.dialog.DialogActivity
import it.inps.design.fab.FabActivity
import it.inps.design.fileupload.FileUploadActivity
import it.inps.design.hero.HeroActivity
import it.inps.design.notification.NotificationActivity
import it.inps.design.pagination.PaginationActivity
import it.inps.design.progressbar.ProgressBarActivity
import it.inps.design.radiobutton.RadioButtonActivity
import it.inps.design.searchbar.SearchBarActivity
import it.inps.design.slider.SliderActivity
import it.inps.design.tabbar.TabBarActivity
import it.inps.design.tabs.TabActivity
import it.inps.design.tag.TagActivity
import it.inps.design.textarea.TextAreaActivity
import it.inps.design.textfield.TextFieldActivity
import it.inps.design.toggle.ToggleActivity
import it.inps.design.ui.DemoMenuItem
import it.inps.sirio.theme.SirioTheme

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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand),
            )
        },
        content = {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
            ) {
                DemoMenuItem("Accordion") {
                    context.startActivity(Intent(context, AccordionActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("App Navigation") {
                    context.startActivity(Intent(context, AppNavigationActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Buttons") {
                    context.startActivity(Intent(context, ButtonActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Card") {
                    context.startActivity(Intent(context, CardActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Carousel") {
                    context.startActivity(Intent(context, CarouselActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Checkbox") {
                    context.startActivity(Intent(context, CheckboxActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Chips") {
                    context.startActivity(Intent(context, ChipActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Dialog") {
                    context.startActivity(Intent(context, DialogActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem(title = "Fab") {
                    context.startActivity(Intent(context, FabActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("File upload") {
                    context.startActivity(Intent(context, FileUploadActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Hero") {
                    context.startActivity(Intent(context, HeroActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Notification") {
                    context.startActivity(Intent(context, NotificationActivity::class.java))
                }
                HorizontalDivider()
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
                HorizontalDivider()
                DemoMenuItem("Progress bar") {
                    context.startActivity(Intent(context, ProgressBarActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Radio button") {
                    context.startActivity(Intent(context, RadioButtonActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Search Bar") {
                    context.startActivity(Intent(context, SearchBarActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Slider") {
                    context.startActivity(Intent(context, SliderActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Tab") {
                    context.startActivity(Intent(context, TabActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Tab Bar") {
                    context.startActivity(Intent(context, TabBarActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("Tag") {
                    context.startActivity(Intent(context, TagActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("TextArea") {
                    context.startActivity(Intent(context, TextAreaActivity::class.java))
                }
                HorizontalDivider()
                DemoMenuItem("TextField") {
                    context.startActivity(Intent(context, TextFieldActivity::class.java))
                }
                HorizontalDivider()
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

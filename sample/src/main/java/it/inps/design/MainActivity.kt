//
// MainActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
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
import it.inps.design.avviso.AvvisoActivity
import it.inps.design.button.ButtonActivity
import it.inps.design.card.CardActivity
import it.inps.design.carousel.CarouselActivity
import it.inps.design.checkbox.CheckboxActivity
import it.inps.design.chip.ChipActivity
import it.inps.design.dialog.DialogActivity
import it.inps.design.dropdown.DropdownActivity
import it.inps.design.dropdownmenu.DropdownMenuActivity
import it.inps.design.fab.FabActivity
import it.inps.design.fileupload.FileUploadActivity
import it.inps.design.filter.FilterActivity
import it.inps.design.foundation.FoundationActivity
import it.inps.design.hero.HeroActivity
import it.inps.design.listitem.ListItemActivity
import it.inps.design.loader.LoaderActivity
import it.inps.design.menuspalla.MenuSpallaActivity
import it.inps.design.notifiche.NotificheActivity
import it.inps.design.pagination.PaginationActivity
import it.inps.design.popover.PopoverActivity
import it.inps.design.progressbar.ProgressBarActivity
import it.inps.design.radiobutton.RadioButtonActivity
import it.inps.design.searchbar.SearchBarActivity
import it.inps.design.segmentedcontrols.SegmentedControlsActivity
import it.inps.design.slider.SliderActivity
import it.inps.design.stepprogressbar.StepProgressBarActivity
import it.inps.design.tab.TabActivity
import it.inps.design.tabbar.TabBarActivity
import it.inps.design.table.TableActivity
import it.inps.design.tag.TagActivity
import it.inps.design.textarea.TextAreaActivity
import it.inps.design.textfield.TextFieldActivity
import it.inps.design.toggle.ToggleActivity
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.listItem.SirioListItem

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
                SirioListItem("Foundation") {
                    context.startActivity(Intent(context, FoundationActivity::class.java))
                }
                SirioListItem("Accordion") {
                    context.startActivity(Intent(context, AccordionActivity::class.java))
                }
                SirioListItem("App Navigation") {
                    context.startActivity(Intent(context, AppNavigationActivity::class.java))
                }
                SirioListItem("Avviso") {
                    context.startActivity(Intent(context, AvvisoActivity::class.java))
                }
                SirioListItem("Buttons") {
                    context.startActivity(Intent(context, ButtonActivity::class.java))
                }
                SirioListItem("Card") {
                    context.startActivity(Intent(context, CardActivity::class.java))
                }
                SirioListItem("Carousel") {
                    context.startActivity(Intent(context, CarouselActivity::class.java))
                }
                SirioListItem("Checkbox") {
                    context.startActivity(Intent(context, CheckboxActivity::class.java))
                }
                SirioListItem("Chips") {
                    context.startActivity(Intent(context, ChipActivity::class.java))
                }
                SirioListItem("Dialog") {
                    context.startActivity(Intent(context, DialogActivity::class.java))
                }
                SirioListItem("Dropdown") {
                    context.startActivity(Intent(context, DropdownActivity::class.java))
                }
                SirioListItem("Dropdown Menu") {
                    context.startActivity(Intent(context, DropdownMenuActivity::class.java))
                }
                SirioListItem(title = "Fab") {
                    context.startActivity(Intent(context, FabActivity::class.java))
                }
                SirioListItem("File upload") {
                    context.startActivity(Intent(context, FileUploadActivity::class.java))
                }
                SirioListItem("Filtri") {
                    context.startActivity(Intent(context, FilterActivity::class.java))
                }
                SirioListItem("Hero") {
                    context.startActivity(Intent(context, HeroActivity::class.java))
                }
                SirioListItem("List item") {
                    context.startActivity(Intent(context, ListItemActivity::class.java))
                }
                SirioListItem("Loader") {
                    context.startActivity(Intent(context, LoaderActivity::class.java))
                }
                SirioListItem("Menu spalla") {
                    context.startActivity(Intent(context, MenuSpallaActivity::class.java))
                }
                SirioListItem("Notifiche") {
                    context.startActivity(Intent(context, NotificheActivity::class.java))
                }
                SirioListItem("Pagination") {
                    context.startActivity(Intent(context, PaginationActivity::class.java))
                }
                SirioListItem("Popover") {
                    context.startActivity(Intent(context, PopoverActivity::class.java))
                }
                SirioListItem("Progress bar") {
                    context.startActivity(Intent(context, ProgressBarActivity::class.java))
                }
                SirioListItem("Radio button") {
                    context.startActivity(Intent(context, RadioButtonActivity::class.java))
                }
                SirioListItem("Search") {
                    context.startActivity(Intent(context, SearchBarActivity::class.java))
                }
                SirioListItem("Slider") {
                    context.startActivity(Intent(context, SliderActivity::class.java))
                }
                SirioListItem("Step progress bar") {
                    context.startActivity(Intent(context, StepProgressBarActivity::class.java))
                }
                SirioListItem("Segmented controls") {
                    context.startActivity(Intent(context, SegmentedControlsActivity::class.java))
                }
                SirioListItem("Tab") {
                    context.startActivity(Intent(context, TabActivity::class.java))
                }
                SirioListItem("Tab Bar") {
                    context.startActivity(Intent(context, TabBarActivity::class.java))
                }
                SirioListItem("Table") {
                    context.startActivity(Intent(context, TableActivity::class.java))
                }
                SirioListItem("Tag") {
                    context.startActivity(Intent(context, TagActivity::class.java))
                }
                SirioListItem("TextArea") {
                    context.startActivity(Intent(context, TextAreaActivity::class.java))
                }
                SirioListItem("TextField") {
                    context.startActivity(Intent(context, TextFieldActivity::class.java))
                }
                SirioListItem("Toggle", showDivider = false) {
                    context.startActivity(Intent(context, ToggleActivity::class.java))
                }
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

//
// StepProgressBarActivity.kt
//
// SPDX-FileCopyrightText: 2025 Istituto Nazionale Previdenza Sociale
//
// SPDX-License-Identifier: BSD-3-Clause
//
@file:OptIn(ExperimentalMaterial3Api::class)

package it.inps.design.stepprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import it.inps.sirio.theme.SirioTheme
import it.inps.sirio.ui.stepprogressbar.SirioStepControlData
import it.inps.sirio.ui.stepprogressbar.SirioStepData
import it.inps.sirio.ui.stepprogressbar.SirioStepPointType
import it.inps.sirio.ui.stepprogressbar.SirioStepProgressBar
import it.inps.sirio.ui.text.SirioText
import it.inps.sirio.ui.textfield.SirioTextField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StepProgressBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SirioTheme {
                Scaffold(
                    Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Step progress bar") },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = SirioTheme.colors.brand)
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        MobileStepProgressBarDemo()
                    }
                }
            }
        }
    }
}

internal data class State(
    val index: Int = 0,
    val steps: List<SirioStepData> = listOf(
        SirioStepData(type = SirioStepPointType.TODO, stepText = "Step 1"),
        SirioStepData(type = SirioStepPointType.TODO, stepText = "Step 2"),
        SirioStepData(type = SirioStepPointType.TODO, stepText = "Step 3"),
        SirioStepData(type = SirioStepPointType.TODO, stepText = "Step 4"),
    ),
    val back: SirioStepControlData = SirioStepControlData(
        text = "Indietro",
        enabled = false,
        action = {},
    ),
    val next: SirioStepControlData = SirioStepControlData(
        text = "Avanti",
        enabled = true,
        action = {},
    ),
    val other: List<SirioStepControlData> = emptyList(),
    val label: String = "",
    val text: String = "",
    val summary: String = "",
)

internal class VM(
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private val reply: MutableMap<Int, String> = mutableMapOf()

    init {
        loadContent(state.value.index)
    }

    fun onValueChange(value: String) {
        val index = state.value.index
        reply[index] = value
        _state.update {
            it.copy(text = value, next = it.next.copy(enabled = canGoNext(index)))
        }
    }

    fun onStepSelected(index: Int): Boolean {
        return when {
            index <= state.value.index -> {
                loadContent(index)
                true
            }

            else -> false
        }
    }

    private fun loadContent(index: Int) {
        _state.update {
            it.copy(
                index = index,
                back = loadBackData(index),
                next = loadNextData(index),
                other = loadOtherData(index),
                label = "Label ${index + 1}*",
                text = reply[index] ?: "",
                summary = loadSummary(index),
            )
        }
    }

    private fun loadSummary(index: Int): String {
        return if (index == 3) reply.values.joinToString("\n")
        else ""
    }

    private fun loadBackData(index: Int): SirioStepControlData {
        return SirioStepControlData(
            text = "Indietro",
            enabled = index > 0,
            action = { loadContent(index - 1) }
        )
    }

    private fun loadNextData(index: Int): SirioStepControlData {
        return if (index == 3)
            SirioStepControlData(
                text = "Ricomincia",
                enabled = true,
                action = {
                    reply.clear()
                    loadContent(0)
                }
            ) else
            SirioStepControlData(
                text = "Avanti",
                enabled = true,
                action = {
                    if (canGoNext(index))
                        loadContent(index + 1)
                }
            )
    }

    private fun loadOtherData(index: Int): List<SirioStepControlData> {
        return if (index > 0) emptyList()
        else listOf(
            SirioStepControlData(
                text = "Test",
                enabled = true,
                action = {},
            ),
        )
    }

    private fun canGoNext(index: Int): Boolean {
        val content = reply[index]
        return !content.isNullOrBlank().also { _ ->
            val newSteps =
                state.value.steps.mapIndexed { i, it -> if (i == index) it.copy(type = SirioStepPointType.DONE) else it }
                    .toList()
            _state.update { it.copy(steps = newSteps) }
        }
    }
}

@Composable
private fun MobileStepProgressBarDemo() {
    val vm: VM = viewModel()
    val state by vm.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
    ) {
        SirioStepProgressBar(
            steps = state.steps,
            currentStepIndex = state.index,
            back = state.back,
            next = state.next,
            other = state.other,
            onStepSelected = vm::onStepSelected,
        ) {
            AnimatedContent(state.index, label = "step content") {
                when (it) {
                    0, 1, 2 -> Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        SirioTextField(
                            label = state.label,
                            text = state.text,
                            onValueChange = vm::onValueChange
                        )
                    }

                    3 -> Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SirioText(state.summary)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun StepProgressBarActivityPreview() {
    SirioTheme {
        MobileStepProgressBarDemo()
    }
}

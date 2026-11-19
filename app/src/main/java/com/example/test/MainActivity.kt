package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WorkoutScreen()
                }
            }
        }
    }
}

class WorkoutViewModel : ViewModel() {

    private val _repCount = MutableStateFlow(0)

    val repCount: StateFlow<Int> = _repCount.asStateFlow()

    fun repcount() {
        _repCount.value++
    }

    fun reset() {
        _repCount.value = 0
    }
}

@Composable
fun WorkoutScreen(
    viewModel: WorkoutViewModel = viewModel()
) {

    val count by viewModel.repCount.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Repetition Count: $count",
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.repcount()
            }
        ) {
            Text("Increase Count")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.reset()
            }
        ) {
            Text("Reset")
        }
    }
}
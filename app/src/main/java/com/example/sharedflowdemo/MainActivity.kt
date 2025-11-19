package com.example.sharedflowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.SharedFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold { innerPadding ->
                ScreenSetup(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun ScreenSetup(
    modifier: Modifier = Modifier,
    viewModel: DemoViewModel = viewModel()
) {
    MainScreen(modifier, viewModel.sharedFlow)
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    sharedFlow: SharedFlow<Int>
) {
    val messages = remember { mutableStateListOf<Int>() }

    LazyColumn(modifier = modifier) {
        items(messages) { value ->
            Text(
                text = "Collected Value = $value",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

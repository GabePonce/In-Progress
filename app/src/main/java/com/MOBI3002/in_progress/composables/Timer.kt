package com.MOBI3002.in_progress.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun Timer(){
    var timeLeft by remember { mutableStateOf(0) }
    var isPaused by remember { mutableStateOf(true) }

    LaunchedEffect (key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }



    fun resetTimer() {
        timeLeft = 0
        isPaused = false
    }

    Column (){
        Text(text = "Time left: $timeLeft")
        Button(onClick = { isPaused = !isPaused }) {
            Text(text = if (isPaused) "Resume" else "Pause")
        }
        Button(onClick = { resetTimer() }) {
            Text(text = "Reset")
        }
    }

}

@Composable
@Preview
fun checkTimer(){
    Timer()
}

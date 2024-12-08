package com.MOBI3002.in_progress.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MOBI3002.in_progress.R
import kotlinx.coroutines.delay
import kotlin.reflect.typeOf
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@Composable
fun Timer(){
    var timeLeft by remember { mutableStateOf(0) }
    var timerMinutes by remember { mutableStateOf("")}
    var isPaused by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("")}

    LaunchedEffect (key1 = timeLeft, key2 = isPaused) {
        while (timeLeft > 0 && !isPaused) {

            delay(1000L)
            timeLeft--

        }
    }



    fun resetTimer() {
        timeLeft = 0
        isPaused = false
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(74, 170, 255)), // Blue background color
            ) {
                Text(
                    text = "In-Progress",
                    Modifier.padding(80.dp, 10.dp, 0.dp, 10.dp),
                    fontSize = 35.sp,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .size(65.dp),
                    painter = painterResource(id = R.drawable.duck_logo),
                    contentDescription = "DUCK"
                )
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(240, 240, 240))
                .paint(
                    painterResource(id = R.drawable.duck_logo),
                    contentScale = ContentScale.None
                )
                .padding(16.dp)
                .padding(top = 175.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            TextField(
                value = timerMinutes,
                onValueChange = {
                    timerMinutes = it
                    if (timerMinutes.toIntOrNull() != null){
                        timeLeft = timerMinutes.toInt() * 60;
                    }else{
                        timeLeft = 0
                        errorMessage = "Put in only the amount of minutes you wish to set this timer for"
                    }

                },
                modifier = Modifier.width(100.dp),
                label = { Text(text = errorMessage)}
            )

            Text(text = if (timeLeft == 0) "|--:--|" else "$timeLeft Sec", fontSize = 40.sp)
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { isPaused = !isPaused },
                colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = if (isPaused) "Resume" else "Pause" , fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { resetTimer() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Reset", fontSize = 20.sp)
            }
        }
    }
}

@Composable
@Preview
fun checkTimer(){
    Timer()
}

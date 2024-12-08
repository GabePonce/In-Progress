package com.MOBI3002.in_progress.composables

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
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
import com.MOBI3002.in_progress.classes.Users
import com.MOBI3002.in_progress.data.DBHelper
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask(dbHelper: DBHelper, user: Users?) {

    var description by remember { mutableStateOf("") }
    var descriptionError by remember { mutableStateOf(" ") }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1733862949398)
    var taskDate by remember { mutableStateOf("") }
    var success by remember { mutableStateOf("")}

    // Wrapping the entire content in a scrollable column
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Make the entire column scrollable
    ) {
        Column {
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

        Column( // Main column for the task screen content
            Modifier
                .fillMaxSize()
                .background(Color(240, 240, 240))
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.duck_logo),
                    contentScale = ContentScale.None
                )
                .padding(16.dp)
        ) {

            Text(
                text = "Add New Task",
                Modifier
                    .padding(0.dp, 10.dp, 8.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 30.sp,
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Set Due Date (optional)",
                fontSize = 20.sp,
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

            val selectedDate = remember(datePickerState.selectedDateMillis) {
                datePickerState.selectedDateMillis?.let { millis ->
                    val adjustedMillis = millis + (24 * 60 * 60 * 1000) //adjusting for weird one day behind thing
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CANADA)
                    sdf.timeZone = TimeZone.getDefault()
                    sdf.format(Date(adjustedMillis))
                } ?: "No date selected"
            }
            taskDate = selectedDate

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Set Task Description",
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp),
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            TextField(
                modifier = Modifier
                    .width(400.dp)
                    .height(150.dp)
                    .align(Alignment.CenterHorizontally),
                value = description,
                onValueChange = {
                    description = it
                    descriptionError =
                        if (description.isEmpty()) "Please enter your task." else ""
                },
                label = { Text(text = "description", fontSize = 25.sp) }
            )

            if (descriptionError.isNotEmpty()) {
                Text(
                    text = descriptionError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    val valid = descriptionError.isEmpty()

                    if (valid) {
                        if (user != null && taskDate != "No date selected") {
                            dbHelper.insertTask(description, taskDate, user.userId)
                            success = "Successfully added task"
                        } else if (user != null)  {
                            dbHelper.insertTask(description, null, user.userId)
                            success = "Successfully added task"
                        }

                    }else {
                        success = "Something went wrong"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                modifier = Modifier
                    .width(250.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Add Task", fontSize = 25.sp)
            }
            Text(text = success)
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
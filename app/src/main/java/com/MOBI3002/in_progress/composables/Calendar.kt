package com.MOBI3002.in_progress.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import com.MOBI3002.in_progress.classes.Task
import com.MOBI3002.in_progress.classes.Users
import com.MOBI3002.in_progress.data.DBHelper
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

// composable for Displaying the tasks with due dates in an organized manner
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(dbHelper: DBHelper, user: Users?){

    // grabs the current date
    val current = LocalDateTime.now();
    // formats to match the due date for tasks
    val formattedCurrent = current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))


    // list of all the tasks that are due today
    var dueTasks by remember { mutableStateOf(mutableListOf<Task>()) }


    // list of all the tasks with a due date but not due today, organized by closest due dat first
    var upcomingTasks by remember { mutableStateOf(mutableListOf<Task>()) }
    var organizedUpcomingTasks by remember { mutableStateOf(mutableListOf<Task>()) }


    if (user != null) {

        for (task in dbHelper.getDateTasks(user.userId)){
            //if the task due date is the same as the current day add to according list
            if (task.taskDueDate == formattedCurrent){
                dueTasks += task
            }else{
                upcomingTasks += task
            }
        }

        //date format to be able to organize the tasks array based on task due date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CANADA)

        //organizes the array by the closest due date to current local time
        organizedUpcomingTasks += upcomingTasks.sortedBy {
                task ->
            task.taskDueDate?.let { dateFormat.parse(it)?.time }
        }

            Column(
                Modifier
                    .fillMaxSize()
            ) {// header composable bit
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

                Column( // Main column for the calendar task screen content
                    Modifier
                        .fillMaxSize()
                        .background(Color(240, 240, 240))
                        .paint(
                            painterResource(id = R.drawable.duck_logo),
                            contentScale = ContentScale.None
                        )
                ) {

                    Spacer(modifier = Modifier.height(25.dp))

                    Column( // Due Tasks section header
                        Modifier
                            .width(400.dp)
                            .height(300.dp)
                            .background(Color(74, 170, 255))
                    ) {
                        Row {
                            Image(
                                modifier = Modifier
                                    .size(65.dp),
                                painter = painterResource(id = R.drawable.duck_logo),
                                contentDescription = "DUCK"
                            )
                            Text(
                                text = "Due Tasks",
                                Modifier
                                    .padding(10.dp),
                                fontSize = 30.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default
                            )

                        }

                        LazyColumn {//Lazy Column that holds the composable parts for displaying the tasks due on the current day
                            items(dueTasks) { task -> //creates for each task in the list
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                BorderStroke(4.dp, Color(74, 170, 255)),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .background(
                                                Color(210, 210, 210),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .fillMaxHeight(.12f)
                                            .width(250.dp)
                                    ) {
                                        Text( // displays task description
                                            text = task.taskDesc,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Normal,
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .fillMaxSize()
                                        )
                                    }
                                    task.taskDueDate?.let {// displays the task due date at the right edge of the row
                                        Text(text = it,
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                                .padding(5.dp, 0.dp, 0.dp, 0.dp),
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Default,
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Column( // Upcoming Tasks section header
                        Modifier
                            .width(400.dp)
                            .height(300.dp)
                            .background(Color(74, 170, 255))
                    ) {
                        Row {
                            Image(
                                modifier = Modifier
                                    .size(65.dp),
                                painter = painterResource(id = R.drawable.duck_logo),
                                contentDescription = "DUCK"
                            )
                            Text(
                                text = "Upcoming Tasks",
                                Modifier
                                    .padding(10.dp),
                                fontSize = 30.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default
                            )

                        }

                        LazyColumn {//Lazy Column that holds the composable parts for displaying the upcoming tasks
                            items(organizedUpcomingTasks) { task ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                BorderStroke(4.dp, Color(74, 170, 255)),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .background(
                                                Color(210, 210, 210),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .fillMaxHeight(.12f)
                                            .width(250.dp)
                                    ) {
                                        Text(
                                            text = task.taskDesc, //displays task description
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Normal,
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .fillMaxSize()
                                        )
                                    }
                                    task.taskDueDate?.let {// displays the task due date at the right edge of the row
                                        Text(text = it,
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                                .padding(5.dp, 0.dp, 0.dp, 0.dp),
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Default,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}


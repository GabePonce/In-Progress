package com.MOBI3002.in_progress.composables

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ComponentActivity
import com.MOBI3002.in_progress.R
import com.MOBI3002.in_progress.classes.Task
import com.MOBI3002.in_progress.classes.Users
import com.MOBI3002.in_progress.data.DBHelper


@Composable
fun Tasks(dbHelper: DBHelper, user: Users?) {

    var isChecked by remember { mutableStateOf(false) }
    var tasks = remember { mutableListOf<Task>() }

    if (user != null) {
        tasks += dbHelper.getTasks(user.userId)
    }




    Column(
        Modifier
            .fillMaxSize()
    ) {
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

        LazyColumn( // Main column for the task screen content
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
            item {
                Spacer(modifier = Modifier.height(20.dp))

                for (task in tasks) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                .fillMaxHeight(.12f) // Fill 20% column height
                                .fillMaxWidth(.80f) // Fill column width
                        ) {
                            Text(
                                text = task.taskDesc,
                                fontSize = 22.sp,  // Set font size
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize()
                            )
                        }

                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(74, 170, 255),
                                uncheckedColor = Color(210, 210, 210),
                                checkmarkColor = Color.White
                            ),
                            modifier = Modifier
                                .scale(2f)
                                .padding(11.dp, 7.dp, 0.dp, 10.dp)
                        )
                    }
                }
            }
        }
    }
}


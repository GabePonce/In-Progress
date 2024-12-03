package com.MOBI3002.in_progress.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun Tasks(){
    Column( // Main column for the task screen content
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Tan background color
        horizontalAlignment = Alignment.CenterHorizontally // Horizontally center the content
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(74, 170, 255)), // Blue background color
        ){
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
}

@Preview
@Composable
fun Display() {
    Tasks()
}
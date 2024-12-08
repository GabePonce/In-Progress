package com.MOBI3002.in_progress.composables


import android.content.Intent
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MOBI3002.in_progress.R
import com.MOBI3002.in_progress.data.DBHelper

/*
    Authors: Samuel Cook, Kendra MacKenzie, Gabe Ponce
    Date: December 2, 2024
    Filename: Login.kt
    Purpose: Composable function for the login screen.
*/

@Composable
fun LoginScreen(context : ComponentActivity) {


    val dbHelper = DBHelper(context)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var passwordError by remember { mutableStateOf(" ") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(74, 170, 255))
    ) {

        Column(modifier = Modifier.padding(32.dp)) {

            Text(
                text = "Welcome to In-Progress",
                Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                fontSize = 35.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp
            )

            Image(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.duck_logo),
                contentDescription = "DUCK"
            )

        }

        Column(
            Modifier
                .fillMaxSize()
                .background(Color(240, 240, 240))
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.duck_logo),
                    contentScale = ContentScale.None)
                .padding(16.dp)
        ) {

            Text(
                text = "Login",
                Modifier.padding(0.dp, 10.dp, 8.dp, 0.dp),
                fontSize = 30.sp,
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                modifier = Modifier.width(400.dp)
                    .align(Alignment.CenterHorizontally),
                value = email,
                onValueChange = {
                    email = it

                },
                label = { Text(text="email", fontSize = 25.sp) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                // This sets up the keyboard for email entry. It's nice for the user.
            )
            // If the user leaves the email empty, an error message will display.

            Spacer(modifier = Modifier.height(15.dp))

            // Textfield for password entry.
            TextField(
                modifier = Modifier.width(400.dp)
                    .align(Alignment.CenterHorizontally),
                value = password,
                onValueChange = {
                    password = it

                },
                label = { Text(text="password", fontSize = 25.sp) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                // arranges the keyboard for password entry.
            )
            if (passwordError.isNotEmpty()) Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error
            )
            // Display an error message if the password field is empty.

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {

                    if (dbHelper.retrieveUser(email, password) != null){
                        val navigate = Intent(context, NavBar::class.java)
                        navigate.putExtra("email", email)
                        navigate.putExtra("password", password)
                        context.startActivity(navigate)
                    }else{
                        passwordError = "account not found, email or password wrong"
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                modifier = Modifier.width(200.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text="Login", fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.height(55.dp))

            Text(
                text = "Don't have an account?",
                Modifier.padding(0.dp, 30.dp, 8.dp, 0.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 30.sp,
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    val navigate = Intent(context, Register::class.java)
                    context.startActivity(navigate)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                modifier = Modifier.width(200.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text="Sign Up!", fontSize = 30.sp)
            }
        }
    }
}

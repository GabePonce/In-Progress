package com.MOBI3002.in_progress.composables
/*
Author: Kendra MacKenzie & Samuel Cook & Gabriel Ponce
Date: December 02/24
Filename: Register.kt
Purpose: Final Project
 */

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MOBI3002.in_progress.MainActivity
import com.MOBI3002.in_progress.R
import com.MOBI3002.in_progress.data.DBHelper
import com.MOBI3002.in_progress.ui.theme.InProgressTheme

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InProgressTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterScreen(this)
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(context : ComponentActivity){

    var dbHelper: DBHelper = DBHelper(context)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(" ") }
    var emailError by remember { mutableStateOf(" ") }
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
                    contentScale = ContentScale.None
                )
                .padding(16.dp)
        ) {

            Text(
                text = "Sign Up",
                Modifier.padding(0.dp, 10.dp, 8.dp, 0.dp),
                fontSize = 30.sp,
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                modifier = Modifier
                    .width(400.dp)
                    .align(Alignment.CenterHorizontally),
                value = name,
                onValueChange = {
                    name = it
                    nameError = if (name.isEmpty()) "Please enter your name." else ""
                },
                label = { Text(text="name", fontSize = 25.sp) }
            )
            if (nameError.isNotEmpty()) Text(
                text = nameError,
                color = MaterialTheme.colorScheme.error
            )
            // If the user leaves the email empty, an error message will display.

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                modifier = Modifier
                    .width(400.dp)
                    .align(Alignment.CenterHorizontally),
                value = email,
                onValueChange = {
                    email = it
                    emailError =
                        if (!Patterns.EMAIL_ADDRESS.matcher(it)
                                .matches()
                        ) "Invalid email!" else ""
                    // This checks to make sure the email follows the email format.
                },
                label = { Text(text="email", fontSize = 25.sp) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                // This sets up the keyboard for email entry. It's nice for the user.
            )
            if (emailError.isNotEmpty()) Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error
            )
            // If the user leaves the email empty, an error message will display.

            Spacer(modifier = Modifier.height(15.dp))

            // Textfield for password entry.
            TextField(
                modifier = Modifier
                    .width(400.dp)
                    .align(Alignment.CenterHorizontally),
                value = password,
                onValueChange = {
                    password = it
                    val passwordPattern = Regex("^[a-zA-Z0-9@_]+$")
                    // regular expression to force the user to design their password in a specific pattern.

                    passwordError = when {
                        it.length < 8 -> "Password must be at least 8 characters long." // display error message if password is too short
                        !passwordPattern.matches(it) -> "Must use only letters, numbers, @, and _" // display error message if password deviates from the rules (regex)
                        else -> ""
                    }
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
                    val checked = emailError.isEmpty() && passwordError.isEmpty()

                    if (checked) {
                        dbHelper.insertUser(email, name, password)
                        val navigate = Intent(context, MainActivity::class.java)
                        context.startActivity(navigate)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(74, 170, 255)),
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text="Sign Up", fontSize = 30.sp)
            }
        }
    }
}
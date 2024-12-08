package com.MOBI3002.in_progress.composables

import android.util.Patterns
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MOBI3002.in_progress.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask() {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(" ") }
    var emailError by remember { mutableStateOf(" ") }
    var passwordError by remember { mutableStateOf(" ") }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1733862949398)

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
                text = "Sign Up",
                Modifier.padding(0.dp, 10.dp, 8.dp, 0.dp),
                fontSize = 30.sp,
                color = Color(150, 150, 150),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default
            )


            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Pre-select a date for December 10, 2024
                var currentDate = datePickerState
                DatePicker(state = currentDate, modifier = Modifier.padding(16.dp))
                Text(text = "$currentDate")
            }


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
                    val valid = emailError.isEmpty() && passwordError.isEmpty()

                    if (valid) {
                        // SEND INFORMATION TO DATABASE
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



@Preview
@Composable
fun DisplayTaskScreen() {
    AddTask()
}